package com.cn.zhusu;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.cn.hangzhou.R;
import com.cn.util.BitmapIOUtil;
import com.cn.util.Constant;
import com.cn.util.FontManager;
import com.cn.util.PubMethod;

public class ZyActivity extends Activity
{
	public String duri;
	public void onCreate(Bundle savedInstanceState) {  
	      super.onCreate(savedInstanceState);  
	      requestWindowFeature(Window.FEATURE_NO_TITLE);
	      setContentView(R.layout.zhusu_yx);
	    //用自定义的字体方法
	        FontManager.changeFonts(FontManager.getContentView(this),this);
	      //接受品牌名称
	      String PP_name=this.getIntent().getStringExtra("namePP");
	      //接受图片路径
	      String imgPath=this.getIntent().getStringExtra("imgPath");
	     //接受介绍路径
	      String jiePath=this.getIntent().getStringExtra("jiePath");
	      if(jiePath.equals("zhusu/xgll.txt"))
		  {
	    	  System.out.println("输出酒店的名字："+PP_name);
	    	  duri="http://www.shangri-la.com/cn/";
		  }else if(PP_name.equals("锦江之星酒店")){
			  duri="http://www.jinjianginns.com/";  
		  }else if(PP_name.equals("汉庭酒店")){
			  duri="http://www.htinns.com/";    
		  }else if(PP_name.equals("7天连锁酒店")){
			  duri="http://www.7daysinn.cn/";     
		  }else if(PP_name.equals("如家酒店")){
			  duri="http://www.homeinns.com/";  
		  }else if(PP_name.equals("速8酒店")){
			  duri="http://www.super8.com.cn/";  
		  }
	      
	      //设置品牌名称
	      TextView tv_name=(TextView)this.findViewById(R.id.zs_name);
	      tv_name.setText(PP_name);
	      
	      //设置图片
	      Bitmap PP_img=BitmapIOUtil.getSBitmap(imgPath);
	      ImageView iv_pp=(ImageView)this.findViewById(R.id.zs_img);
	      iv_pp.setImageBitmap(PP_img);
	      
	      //设置介绍文本
	      TextView jie=(TextView)this.findViewById(R.id.zs_jie);
	      String information=PubMethod.loadFromFile(jiePath);   //文本内容，获取美食详细介绍
	      jie.setText(information);
	      jie.setTextSize(Constant.TEXT_SIZE);
		  jie.setTextColor(ZyActivity.this.getResources().getColor(Constant.COLOR));
	      
	     //返回按钮
	      ImageButton iback=(ImageButton)this.findViewById(R.id.fl_back);
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
	      //返回按钮
	      ImageButton ipost=(ImageButton)this.findViewById(R.id.fl_set);
	      ipost.setOnClickListener(
	    		  new OnClickListener()
	    		  {
	    			  @Override
	    			  public void onClick(View v)
	    			  {
//	    				 try
//	    				{
//	    				  Uri uri = Uri.parse(duri); 
//	    				  
//	    				  Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//	    				  startActivity(intent);
//	    				}
//	    				 catch(Exception e)
//	    					{
//	    						//System.out.println("对不起，没有找到指定文件！"+fileName);
//	    						Toast.makeText(ZyActivity.this, "对不起，您未联网！", Toast.LENGTH_SHORT).show();
//	    					}
	    				  
	    				  Uri uri = Uri.parse(duri); 
	    				  Intent intent = new Intent(Intent.ACTION_VIEW, uri);
	    				  startActivity(intent);
	    			  }
	    		  }
	    		  );
	      
	}
}