package com.cn.yiliao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cn.hangzhou.R;
import com.cn.map.MapActivity;
import com.cn.util.Constant;
import com.cn.util.FontManager;
import com.cn.util.PubMethod;



public class YyActivity extends Activity
{
	String cx_bt; 
	int longitude;
	 int latitude;
	public void onCreate(Bundle savedInstanceState) {  
	      super.onCreate(savedInstanceState);  
	      requestWindowFeature(Window.FEATURE_NO_TITLE);
	      setContentView(R.layout.yiliao_jie);
	    //用自定义的字体方法
	        FontManager.changeFonts(FontManager.getContentView(this),this);
	      //接受医院名称
	      cx_bt=this.getIntent().getStringExtra("yy_mc");
	      
	      System.out.println(cx_bt+"==========");
	      
	      //设置标题
	      TextView tv_name=(TextView)this.findViewById(R.id.yl_ym);
	      tv_name.setText(cx_bt);
	      
	      
	        //接受介绍路径
		    String jiePath=this.getIntent().getStringExtra("yy_js");
		
		    //接受经纬度
		    longitude=this.getIntent().getIntExtra("longitude", 12016984);
		    latitude=this.getIntent().getIntExtra("latitude", 3027673);
		    
//		    String strMap=jiePath.substring(0, jiePath.length()-4);
//		    String in=PubMethod.loadFromFile(strMap+"_map.txt");
//		    System.out.println("--------------------"+in);
//		    mapinfor=in.split("\\|");
//		    final int count=mapinfor.length/2;  
//		    
//		    System.out.println("count="+count);
//		    longitude=new int[count];
//		    latitude=new int[count];
//		    for(int i=0;i<count;i++)
//		    {
//		    	 longitude[i]=Integer.valueOf(mapinfor[i]);
//		    	 System.out.println("____________"+mapinfor[i*2]);
//		    	  latitude[i]=Integer.valueOf(mapinfor[i+1]);
//		    	  System.out.println("____________"+mapinfor[i*2+1]);
//		    }
		  
		    
		    
		    
		    
		    //设置介绍文本
		    TextView jie=(TextView)this.findViewById(R.id.yl_js);
		    String information=PubMethod.loadFromFile(jiePath);   //文本内容，获取美食详细介绍
		    jie.setText(information);
		    jie.setTextSize(Constant.TEXT_SIZE);
		    jie.setTextColor(YyActivity.this.getResources().getColor(Constant.COLOR));
	      
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
	      Button yl_bt=(Button)this.findViewById(R.id.yl_dt);
	      yl_bt.setOnClickListener(
	    		  new OnClickListener()
	    		  {
	    			  @Override
	    			  public void onClick(View v)
	    			  {
	    				 
	    				    Intent intent=new Intent(YyActivity.this,MapActivity.class);
	    					intent.putExtra("longN", longitude);
	    					intent.putExtra("latN", latitude);
	    					intent.putExtra("name", cx_bt);
	    					startActivity(intent);
	    				  
	    				 //Toast.makeText(YyActivity.this,"点击的是"+cx_bt, Toast.LENGTH_LONG).show();
	    			  }
	    		  }
	    		  );
	      
	      
	}
}