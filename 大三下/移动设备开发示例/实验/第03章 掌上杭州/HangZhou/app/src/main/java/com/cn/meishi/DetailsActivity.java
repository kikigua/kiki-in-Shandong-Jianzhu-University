package com.cn.meishi;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.cn.hangzhou.R;
import com.cn.map.MapActivity;
import com.cn.util.BitmapIOUtil;
import com.cn.util.Constant;
import com.cn.util.FontManager;
import com.cn.util.PubMethod;

@SuppressWarnings("deprecation")
public class DetailsActivity extends Activity
{
	
	static Bitmap[] imageIDs;
	
	private String[] mapinfor=new String[40];//文件内容,获取图片名和菜名
	
	
	 String[] restaurant;                    //商家点的名称，地址
	 int[] longitude;
	 int[] latitude;
	
	public void onCreate(Bundle savedInstanceState) {  
	      super.onCreate(savedInstanceState);  
	      requestWindowFeature(Window.FEATURE_NO_TITLE);
	      setContentView(R.layout.meishi_detail);
	    //用自定义的字体方法
	        FontManager.changeFonts(FontManager.getContentView(this),this);
     
	      
	      //接受菜的类名
	      String  cai_LM=this.getIntent().getStringExtra("cai_lm");
	      System.out.println("```````````````"+cai_LM+"````````````");
	      //接收菜名
	      String str=this.getIntent().getStringExtra("dText");
	      //接受图片路径
	      String imgpath=this.getIntent().getStringExtra("imgPath");
	      
	      //设置菜名
	      TextView tv=(TextView)this.findViewById(R.id.cm);
	      tv.setText(str);
	      
	      
	      String yxPath=imgpath.substring(9,imgpath.length()-4);  //截取字符串,获取路径
	      String information=PubMethod.loadFromFile("food/"+cai_LM+"/jieshao/"+yxPath+".txt");   //文本内容，获取美食详细介绍
	      
	      System.out.println("+++++++++++++++++++++++"+imgpath+"+++++++++++++++");
	      System.out.println("======================="+yxPath+"================");
	      

	      imageIDs=new Bitmap[3];
	      for(int i=0,j=1;i<3;i++,j++)               //确定有三张图片
	      {
	    	  imageIDs[i]=BitmapIOUtil.getSBitmap("food/"+cai_LM+"/jieshao/"+yxPath+"/"+j+".jpg");
	      }
	      
	      
	      
	      //设置图片
	      Gallery gl=(Gallery)this.findViewById(R.id.Gallery01);
	      //设置适配器
	      BaseAdapter ba=new BaseAdapter()
	        {
				@Override
				public int getCount() {
					return 3;
				}

				@Override
				public Object getItem(int arg0) {			    
					return null;
				}

				@Override
				public long getItemId(int arg0) {
					// TODO Auto-generated method stub
					return 0;
				}

				@Override
				public View getView(int arg0, View arg1, ViewGroup arg2) {
					ImageView iv = new ImageView(DetailsActivity.this);
					iv.setImageBitmap(imageIDs[arg0]);
					iv.setScaleType(ImageView.ScaleType.FIT_XY);
					iv.setLayoutParams(new Gallery.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
					return iv;
				}        	
	        };
	        
	        //将适配器添加进控件
	        gl.setAdapter(ba);
	        gl.setSelection(1);       //选中第二张图片在中间，编号从0开始
	        gl.setOnItemClickListener
	        (
	        		new OnItemClickListener()
	        		{
						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1,int arg2, long arg3)
						{
							Gallery gl=(Gallery)findViewById(R.id.Gallery01);
							gl.setSelection(arg2);
//							Toast.makeText(DetailsActivity.this, "点击了第"+arg2+"张图片", Toast.LENGTH_LONG).show();
							
							 //选中项目，跳转到下一界面
							Intent intent=new Intent();
						    intent.setClass(DetailsActivity.this,MaxActivity.class);
							intent.putExtra("num",arg2);
							startActivity(intent);
						}        			
	        		}
	        );
	        
	      //详细介绍
	      TextView tvs=(TextView)this.findViewById(R.id.msdetail);
	      tvs.setText(information);
	      tvs.setTextSize(Constant.TEXT_SIZE);
	      tvs.setTextColor(DetailsActivity.this.getResources().getColor(Constant.COLOR));
	      
	      
	      String in=PubMethod.loadFromFile("food/"+cai_LM+"/jieshao/"+yxPath+"/map.txt");   //文本内容，获取图片名和菜名
	      mapinfor=in.split("\\|");
	      final int count=mapinfor.length/3;    //有多少家店
	      restaurant=new String[count];            //以此确定数组的长度
	      longitude=new int[count];
	      latitude=new int[count];
	      for(int i=0;i<count;i++)
	      {
	    	  restaurant[i]=mapinfor[i*3];     //获取店面的名字和经纬度
	    	  longitude[i]=Integer.valueOf(mapinfor[i*3+1]);
	    	  latitude[i]=Integer.valueOf(mapinfor[i*3+2]);
	      }
	      
	      
	      //下拉按钮
	      Button bt_xiala=(Button)this.findViewById(R.id.bt_xiala);
	      bt_xiala.setOnClickListener(new OnClickListener() 
			{
				public void onClick(View v) 
				{
					showDialog();
				}
			});
	     
	}
	
	
	
	public void showDialog() 
	{
		//创建对话框
		LinearLayout linearlayout_list_w = new LinearLayout(this);
		linearlayout_list_w.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		ListView listview = new ListView(DetailsActivity.this);
		listview.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		listview.setFadingEdgeLength(0);
		linearlayout_list_w.setBackgroundColor(getResources().getColor(
				R.color.gray));

		linearlayout_list_w.addView(listview);
		final AlertDialog dialog = new AlertDialog.Builder(
				DetailsActivity.this).create();
		WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
		params.width = 200;
		params.height = 400;
		dialog.setTitle("特色店推荐");  
		dialog.setIcon(R.drawable.tese_bg);
		dialog.setView(linearlayout_list_w);
		dialog.getWindow().setAttributes(params);
		dialog.show();
		
		//设置listview适配器
		BaseAdapter ba = new BaseAdapter() 
		{
			LayoutInflater inflater = LayoutInflater.from(DetailsActivity.this);
			
			@Override
			public int getCount() 
			{
				return restaurant.length;
			}

			@Override
			public Object getItem(int arg0) 
			{
				return null;
			}

			@Override
			public long getItemId(int arg0) 
			{
				return 0;
			}

			@Override
			public View getView(int arg0, View arg1, ViewGroup arg2) 
			{
				String musicName=restaurant[arg0];
				LinearLayout ll = (LinearLayout) inflater.inflate(R.layout.list_w,null);
				TextView tv = (TextView) ll.getChildAt(0);
				tv.setText(musicName);
				return ll;
			}
		};
		
		listview.setAdapter(ba);

		// 响应listview中的item的点击事件
		listview.setOnItemClickListener(new OnItemClickListener() 
		{
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) 
			{
				
				
			
				Intent intent=new Intent(DetailsActivity.this,MapActivity.class);
				intent.putExtra("longN", longitude[arg2]);
				intent.putExtra("latN", latitude[arg2]);
				intent.putExtra("name", restaurant[arg2]);
				startActivity(intent);
				
				dialog.cancel();
			}
		});
	}
	
	
//	private String getSdcardDir() {
//		if (Environment.getExternalStorageState().equalsIgnoreCase(
//				Environment.MEDIA_MOUNTED)) {
//			return Environment.getExternalStorageDirectory().toString();
//		}
//		return null;
//	}
//	private NaviEngineInitListener mNaviEngineInitListener = new NaviEngineInitListener() {
//		public void engineInitSuccess() {
//			mIsEngineInitSuccess = true;
//		}
//
//		public void engineInitStart() {
//		}
//
//		public void engineInitFail() {
//		}
//	};
	
	
}

