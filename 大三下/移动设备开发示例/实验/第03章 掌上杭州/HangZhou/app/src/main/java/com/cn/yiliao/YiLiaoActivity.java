package com.cn.yiliao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.cn.hangzhou.R;
import com.cn.util.FontManager;
import com.cn.util.PubMethod;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;


public class YiLiaoActivity extends Activity
{
	
	public int count;   //有多少条记录
	public String[] yy_mc=new String[20];      //医院名称
    public String[] yy_js=new String[20];      //医院介绍
    int[] longitude;
	int[] latitude;
	public List<? extends Map<String,?>> generateDataList()
  			{
  		            ArrayList<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
  		            for(int i=0;i<count;i++)
  		            {
  		            	HashMap<String,Object> hmp=new HashMap<String,Object>();
  		            	hmp.put("col1",yy_mc[i]);  //存促销标题
  		            	list.add(hmp);
  		            }
  		            return list;
  			}
	
	
	
	 @Override
	 public void onCreate(Bundle savedInstanceState) 
	 {
		 super.onCreate(savedInstanceState);
	     setContentView(R.layout.yiliao_main);
	     
	   //用自定义的字体方法
	        FontManager.changeFonts(FontManager.getContentView(this),this);
	     //获取标题
		 String[] infor=new String[40];   //文件内容,获取标题和介绍
		 String information=PubMethod.loadFromFile("yiliao/yiliaoname.txt");   //文本内容，获取图片名和菜名
	     infor=information.split("\\|");
	     count=infor.length/4;
	      longitude=new int[count];
	      latitude=new int[count];
	     //获取图片和菜名的路径
	        for(int i=0;i<count;i++)
	        {
	        	yy_mc[i]=infor[i*4];   
	        	yy_js[i]="yiliao/"+infor[i*4+1]+".txt";	 
	            longitude[i]=Integer.valueOf(infor[i*4+2]);
		        latitude[i]=Integer.valueOf(infor[i*4+3]);
	        }
	        
	        for(int i=0;i<count;i++)
	        {
	        	System.out.println(yy_mc[i]);
	           System.out.println(yy_js[i]);
	           System.out.println(longitude[i]);
	           System.out.println(latitude[i]);
	        }
	     
	     
	     
	     //设置医院列表
	     GridView gv=(GridView)this.findViewById(R.id.gv_yl);
	     //设置适配器
	     SimpleAdapter sca=new SimpleAdapter
	                (
	                  this,
	                  generateDataList(), //数据List
	                  R.layout.gouwu_row, //行对应layout id
	                  new String[]{"col1"},  //列名列表
	                  new int[]{R.id.row_bt}//列对应控件id列表
	                );
	     gv.setAdapter(sca);
	     //为列表添加监听
	     gv.setOnItemClickListener(
	        		new OnItemClickListener()
	        		{
	        			@Override
	        			public void onItemClick(AdapterView<?> arg0,View arg1,int arg2,long arg3)
	        			{
	        				
	        				System.out.println(yy_mc[arg2]+"-------------");
	        				System.out.println(yy_js[arg2]+"-------------");
	        				
	        				
	        				Intent intent=new Intent(YiLiaoActivity.this,YyActivity.class);
	        				intent.putExtra("yy_mc", yy_mc[arg2]);
	        				intent.putExtra("yy_js", yy_js[arg2]);
	        				intent.putExtra("longitude", longitude[arg2]);
	        				intent.putExtra("latitude", latitude[arg2]);
	        				startActivity(intent);
	        				
	        				
	        			}
	        		}
	        		);
	     
	     
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
}