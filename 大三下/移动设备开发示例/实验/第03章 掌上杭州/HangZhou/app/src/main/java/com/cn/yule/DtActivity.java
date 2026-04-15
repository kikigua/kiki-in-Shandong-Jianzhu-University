package com.cn.yule;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cn.hangzhou.R;
import com.cn.map.MapActivity;
import com.cn.util.BitmapIOUtil;
import com.cn.util.Constant;
import com.cn.util.FontManager;
import com.cn.util.PubMethod;

public class DtActivity extends Activity
{
	
	int longitude;
	int latitude;
	String PP_name;
	public void onCreate(Bundle savedInstanceState) {  
	      super.onCreate(savedInstanceState);  
	      requestWindowFeature(Window.FEATURE_NO_TITLE);
	      setContentView(R.layout.yule_yx);
	    //用自定义的字体方法
	        FontManager.changeFonts(FontManager.getContentView(this),this);
	      //接受品牌名称
	       PP_name=this.getIntent().getStringExtra("namePP");
	      //接受图片路径
	      String imgPath=this.getIntent().getStringExtra("imgPath");
	     //接受介绍路径
	      String jiePath=this.getIntent().getStringExtra("jiePath");
	    //接受经纬度
	    longitude=this.getIntent().getIntExtra("longitude", 12016984);
	    latitude=this.getIntent().getIntExtra("latitude", 3027673);
	      
	      //设置品牌名称
	      TextView tv_name=(TextView)this.findViewById(R.id.yl_name);
	      tv_name.setText(PP_name);
	      
	      //设置图片
	      Bitmap PP_img=BitmapIOUtil.getSBitmap(imgPath);
	      ImageView iv_pp=(ImageView)this.findViewById(R.id.yl_img);
	      iv_pp.setImageBitmap(PP_img);
	      
	      //设置介绍文本
	      TextView jie=(TextView)this.findViewById(R.id.yl_jie);
	      String information=PubMethod.loadFromFile(jiePath);   //文本内容，获取美食详细介绍
	      jie.setText(information);
	      jie.setTextSize(Constant.TEXT_SIZE);
		  jie.setTextColor(DtActivity.this.getResources().getColor(Constant.COLOR));
	      
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
	      
	      //地图按钮
	      Button yl_bt=(Button)this.findViewById(R.id.yule_dt);
	      yl_bt.setOnClickListener(
	    		  new OnClickListener()
	    		  {
	    			  @Override
	    			  public void onClick(View v)
	    			  {
	    				 
	    				    Intent intent=new Intent(DtActivity.this,MapActivity.class);
	    					intent.putExtra("longN", longitude);
	    					intent.putExtra("latN", latitude);
	    					intent.putExtra("name", PP_name);
	    					startActivity(intent);	    				 
	    			  }
	    		  }
	    		  );
	      
	}
}