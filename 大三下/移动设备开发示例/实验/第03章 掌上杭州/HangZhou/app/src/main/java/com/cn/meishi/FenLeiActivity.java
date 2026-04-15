package com.cn.meishi;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.TextView;

import com.cn.hangzhou.R;
import com.cn.util.BitmapIOUtil;
import com.cn.util.FontManager;
import com.cn.util.PubMethod;



public class FenLeiActivity extends Activity
{
	
	private String[] infor=new String[40];//文件内容,获取图片名和菜名
	private String[] nextPath=new String[20];     //用于向下一界面传递路径
	private String[] imgPath=new String[20];      //图片路径
	private String[] namePath=new String[20];      //菜名路径
	private Bitmap[] imgBp=new Bitmap[20];         //图片数组
	
	
	String cai_LM;      //确定当前菜的类别
	public void onCreate(Bundle savedInstanceState) {  
	      super.onCreate(savedInstanceState);  
	      requestWindowFeature(Window.FEATURE_NO_TITLE);
	      setContentView(R.layout.meishi_fenlei);  
	      GridView gridview = (GridView) findViewById(R.id.gridview);  
	        
	    //用自定义的字体方法
	        FontManager.changeFonts(FontManager.getContentView(this),this);
	        //接受分类的名称
	        cai_LM=this.getIntent().getStringExtra("LeiMing");
	        //设置标题
	        TextView f_title=(TextView)this.findViewById(R.id.fenlei_title);
	        if(cai_LM.equals("MingCai"))
	        {
	        	f_title.setText("风味名菜");
	        }
	        if(cai_LM.equals("XiaoChi"))
	        {
	        	f_title.setText("特色小吃");
	        }
	        if(cai_LM.equals("LaoWeiDao"))
	        {
	        	f_title.setText("杭帮老味道");
	        }
	        if(cai_LM.equals("NongJiaCai"))
	        {
	        	f_title.setText("美味农家菜");
	        }
	        
	        
	        String information=PubMethod.loadFromFile("food/"+cai_LM+"/foodname.txt");   //文本内容，获取图片名和菜名
	        infor=information.split("\\|");
	        final int count=infor.length/2;    //有多少道菜
	         //获取图片和菜名的路径
	         for(int i=0;i<count;i++)
	         {
	        	 imgPath[i]="food/"+cai_LM+"/img/"+infor[2*i+1];
	        	 nextPath[i]="food/img/"+infor[2*i+1];
	        	 namePath[i]=infor[2*i];        	 
	         }
	      //获取图片   
	      for(int i=0;i<count;i++)
	      {
	    	  imgBp[i]=BitmapIOUtil.getSBitmap(imgPath[i]);
	    	  System.out.println("--------------"+namePath[i]+"-------------------");
	      }
	      
	     
	      //生成动态数组，并且转入数据  
	      ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();  
	      for(int i=0;i<count;i++)  
	      {  
		       HashMap<String, Object> map = new HashMap<String, Object>();  
		       map.put("ItemImage",imgBp[i]);//添加图像资源的ID  
		       map.put("ItemText",namePath[i]);//按序号做ItemText    namePath[i]
		       lstImageItem.add(map);  
	      }  
	      //生成适配器的ImageItem <====> 动态数组的元素，两者一一对应  
	      SimpleAdapter saImageItems = new SimpleAdapter(this, //没什么解释  
	                                                lstImageItem,//数据来源   
	                                                R.layout.meishi_item,//night_item的XML实现  
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
	    @SuppressWarnings("unchecked")
		HashMap<String, Object> item=(HashMap<String, Object>) arg0.getItemAtPosition(arg2);  
	    
	    //选中项目，跳转到下一界面
	    Intent intent=new Intent(FenLeiActivity.this,DetailsActivity.class);
		intent.putExtra("dText",(String)item.get("ItemText"));
		intent.putExtra("imgPath",nextPath[arg2]);
		intent.putExtra("cai_lm",cai_LM);
		startActivity(intent);
	    
	    }
	}  
}



