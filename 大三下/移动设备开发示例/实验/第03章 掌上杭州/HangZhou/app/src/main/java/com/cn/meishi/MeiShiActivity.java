package com.cn.meishi;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.cn.hangzhou.R;
import com.cn.hangzhou.SlidingSwitcherView;
import com.cn.util.BitmapIOUtil;
import com.cn.util.FontManager;
import com.cn.util.PubMethod;


public class MeiShiActivity extends Activity implements OnClickListener 
{
    String[] imgPath=new String[20];      //图片路径
	String[] namePath=new String[20];      //菜名路径
	String[] nextPath=new String[20];     //用于向下一界面传递路径
	Bitmap[] imgBp=new Bitmap[4];    //图片数组
	ImageButton[] tv;
	PubMethod pub=new PubMethod(this);
	public String[] leiMing=new String[]{"风味名菜","特色小吃","杭帮老味道","美味农家菜"};
	public List<? extends Map<String,?>> generateDataList()
  			{
  		            ArrayList<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
  		            for(int i=0;i<4;i++)
  		            {
  		            	HashMap<String,Object> hmp=new HashMap<String,Object>();
  		            	hmp.put("col1",leiMing[i]);  //存菜名
  		            	list.add(hmp);
  		            }
  		            return list;
  			}
	
	String jianjie_str; 
	TextView jianjie_tv;		
	@SuppressWarnings("deprecation")
	public void onCreate(Bundle savedInstanceState) 
	{
		 super.onCreate(savedInstanceState);  
	     requestWindowFeature(Window.FEATURE_NO_TITLE);   //去掉标题栏
	     setContentView(R.layout.meishi_main); 
	   //用自定义的字体方法
	        FontManager.changeFonts(FontManager.getContentView(this),this);
	     //获取简介内容
	     jianjie_str=PubMethod.loadFromFile("food/jianjie.txt");
	     //设置简介文本
	     jianjie_tv=(TextView)this.findViewById(R.id.jianjie);
	     jianjie_tv.setText(jianjie_str);
	     
	     
	     //设置分类的菜名
	     GridView gv=(GridView)this.findViewById(R.id.meishi_gv);
	     //设置适配器
	     SimpleAdapter sca=new SimpleAdapter
	                (
	                  this,
	                  generateDataList(), //数据List
	                  R.layout.meishi_row, //行对应layout id
	                  new String[]{"col1"},  //列名列表
	                  new int[]{R.id.meishi_leiming}//列对应控件id列表
	                );
	     gv.setAdapter(sca);
	     //为列表添加监听
	     gv.setOnItemClickListener(
	        		new OnItemClickListener()
	        		{
	        			@Override
	        			public void onItemClick(AdapterView<?> arg0,View arg1,int arg2,long arg3)
	        			{
	        				if(arg2==0)
	        				{
	        					Intent intent=new Intent(MeiShiActivity.this,FenLeiActivity.class);
	        				    intent.putExtra("LeiMing","MingCai");
	    						startActivity(intent);
	        				}
	        				if(arg2==1)
	        				{
	        					Intent intent=new Intent(MeiShiActivity.this,FenLeiActivity.class);
	    						intent.putExtra("LeiMing","XiaoChi");
	    						startActivity(intent);
	        				}
	        				if(arg2==2)
	        				{
	        					Intent intent=new Intent(MeiShiActivity.this,FenLeiActivity.class);
	    						intent.putExtra("LeiMing","LaoWeiDao");
	    						startActivity(intent);
	        				}
	        				if(arg2==3)
	        				{
	        					Intent intent=new Intent(MeiShiActivity.this,FenLeiActivity.class);
	    						intent.putExtra("LeiMing","NongJiaCai");
	    						startActivity(intent);
	        				}
	        			}
	        		}
	        		);
	    
		 
         String[] infor=new String[40];//文件内容,获取图片名和菜名
         String information=PubMethod.loadFromFile("food/MingCai/foodname.txt");   //文本内容，获取图片名和菜名
	     infor=information.split("\\|");
	         //获取图片和菜名的路径
         for(int i=0;i<4;i++)
         {
        	 imgPath[i]="food/MingCai/img/"+infor[2*i+1];
        	 nextPath[i]="food/img/"+infor[2*i+1];
        	 namePath[i]=infor[2*i];        	 
         }
	      //获取图片   
	      for(int i=0;i<4;i++)
	      {
	    	  imgBp[i]=BitmapIOUtil.getSBitmap(imgPath[i]);
	    	  System.out.println("--------------"+namePath[i]+"-------------------");
	      }
         
	     //设置滑动图片  
	     tv= new ImageButton[4];
         tv[0]=(ImageButton) findViewById(R.id.mb01);
         tv[1]=(ImageButton) findViewById(R.id.mb02);
         tv[2]=(ImageButton) findViewById(R.id.mb03);
         tv[3]=(ImageButton) findViewById(R.id.mb04);
         for(int i=0;i<tv.length;i++)
         { 
         	 //tv[i].setImageBitmap(imgBp[i]);
			 BitmapDrawable bd= new BitmapDrawable(imgBp[i]);    
        	 tv[i].setBackground(bd);
         	 tv[i].setOnClickListener(this);       //添加监听
         }
	}

	@Override
	public void onClick(View v) 
	{
		if(Math.abs(SlidingSwitcherView.xDown-SlidingSwitcherView.xUp)<8)
		{
			if(v.getId()==R.id.mb01)
			{
				 Intent intent=new Intent(MeiShiActivity.this,DetailsActivity.class);
			     intent.putExtra("dText",namePath[0]);
				 intent.putExtra("imgPath",nextPath[0]);
				 intent.putExtra("cai_lm","MingCai");
			     startActivity(intent);
													
			}
			if(v.getId()==R.id.mb02)
			{
				 Intent intent=new Intent(MeiShiActivity.this,DetailsActivity.class);
			     intent.putExtra("dText",namePath[1]);
				 intent.putExtra("imgPath",nextPath[1]);
				 intent.putExtra("cai_lm","MingCai");
			     startActivity(intent);									
			}
			if(v.getId()==R.id.mb03)
			{
				 Intent intent=new Intent(MeiShiActivity.this,DetailsActivity.class);
			     intent.putExtra("dText",namePath[2]);
				 intent.putExtra("imgPath",nextPath[2]);
				 intent.putExtra("cai_lm","MingCai");
			     startActivity(intent);				
			}
			if(v.getId()==R.id.mb04)
			{
				 Intent intent=new Intent(MeiShiActivity.this,DetailsActivity.class);
			     intent.putExtra("dText",namePath[3]);
				 intent.putExtra("imgPath",nextPath[3]);
				 intent.putExtra("cai_lm","MingCai");
			     startActivity(intent);							
			}
		}
		
	}
	
}