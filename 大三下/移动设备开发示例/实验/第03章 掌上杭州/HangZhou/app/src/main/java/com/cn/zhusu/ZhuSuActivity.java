package com.cn.zhusu;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;

import com.cn.hangzhou.R;
import com.cn.util.BitmapIOUtil;
import com.cn.util.FontManager;
import com.cn.util.PubMethod;

public class ZhuSuActivity extends Activity
{
	private String[] infor=new String[40];//文件内容,获取图片名和菜名
	private String[] jiePath=new String[20];      //品牌介绍的路径
	private String[] imgPath=new String[20];      //路径
	private String[] namePP=new String[20];      //名字
	private Bitmap[] imgBp=new Bitmap[20];         //图片数组
	
	 @Override
	 public void onCreate(Bundle savedInstanceState) 
	 {
		 super.onCreate(savedInstanceState);
	     setContentView(R.layout.zhusu_main);
	     
	   //用自定义的字体方法
	        FontManager.changeFonts(FontManager.getContentView(this),this);
	     GridView gridview = (GridView) findViewById(R.id.gv_zhusu);
	     
	        String information=PubMethod.loadFromFile("zhusu/name.txt");   //文本内容，获取图片名和菜名
	        infor=information.split("\\|");
	        final int count=infor.length/2;    //有多少道菜
	         //获取图片和菜名的路径
	         for(int i=0;i<count;i++)
	         {
	        	 namePP[i]=infor[i*2];
	        	 imgPath[i]="zhusu/"+infor[i*2+1]+".jpg";
	        	 jiePath[i]="zhusu/"+infor[i*2+1]+".txt";
	         }
	         
	         //获取图片   
		      for(int i=0;i<count;i++)
		      {
		    	  imgBp[i]=BitmapIOUtil.getSBitmap(imgPath[i]);		    	
		      }
		      
		      //生成动态数组，并且转入数据  
		      ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();  
		      for(int i=0;i<count;i++)  
		      {  
			       HashMap<String, Object> map = new HashMap<String, Object>();  
			       map.put("ItemImage",imgBp[i]);//添加图像资源的ID  
			       map.put("ItemText",namePP[i]);//按序号做ItemText    namePath[i]
			       lstImageItem.add(map);  
		      }  
		      //生成适配器的ImageItem <====> 动态数组的元素，两者一一对应  
		      SimpleAdapter saImageItems = new SimpleAdapter(this, //没什么解释  
		                                                lstImageItem,//数据来源   
		                                                R.layout.gouwu_item,//night_item的XML实现  
		                                                //动态数组与ImageItem对应的子项          
		                                                new String[] {"ItemImage","ItemText"},   
		                                                //ImageItem的XML文件里面的一个ImageView,两个TextView ID  
		                                               new int[] {R.id.ItemImage,R.id.ItemText}); 
		      //实现接口
		      saImageItems.setViewBinder(new ViewBinder(){

					public boolean setViewValue(View view, Object data,
							String textRepresentation) {
						if( (view instanceof ImageView) & (data instanceof Bitmap) ) {
							ImageView iv = (ImageView) view;
							Bitmap bm = (Bitmap) data;
							iv.setImageBitmap(bm);
							return true;
							}
							return false;
					}
		    		   
		    	   });
		      //添加并且显示  
		      gridview.setAdapter(saImageItems);  
		      //添加消息处理  
		      gridview.setOnItemClickListener(new ItemClickListener());  
		      
		      //返回按钮
		      ImageView iback=(ImageView)this.findViewById(R.id.fl_back);
		      iback.setOnClickListener(
		    		  new OnClickListener()
		    		  {
		    			  @Override
		    			  public void onClick(View v)
		    			  {
	                          finish();
		    			  }
		    		  }
		    		  );
	 }
	 
	//当AdapterView被单击(触摸屏或者键盘)，则返回的Item单击事件  
		class  ItemClickListener implements OnItemClickListener  
		{  
		       public void onItemClick(AdapterView<?> arg0,//The AdapterView where the click happened   
		                                 View arg1,//The view within the AdapterView that was clicked  
		                                 int arg2,//The position of the view in the adapter  
		                                 long arg3//The row id of the item that was clicked  
		                                  ) {  
		    //在本例中arg2=arg3  
		    //HashMap<String, Object> item=(HashMap<String, Object>) arg0.getItemAtPosition(arg2);  
		    
		    //选中项目，跳转到下一界面
		    Intent intent=new Intent(ZhuSuActivity.this,ZyActivity.class);
			intent.putExtra("namePP",namePP[arg2]);         //品牌名称
			intent.putExtra("imgPath",imgPath[arg2]);       //图片路径
			intent.putExtra("jiePath",jiePath[arg2]);       //介绍路径
			startActivity(intent);
		    
		    }
		}  

}
	     