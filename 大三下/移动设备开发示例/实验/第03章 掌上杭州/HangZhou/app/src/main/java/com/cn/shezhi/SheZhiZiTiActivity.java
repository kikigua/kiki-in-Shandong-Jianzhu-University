package com.cn.shezhi;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.cn.hangzhou.R;
import com.cn.util.Constant;
import com.cn.util.FontManager;

public class SheZhiZiTiActivity extends Activity {
	final int SHEZHI_YANSE=0;//各个对话框的ID
    final int SHEZHI_ZITI=1;
	final int SHEZHI_DAXIAO=2;    
	public String[] items=new String[]{"小字体","大字体","超大字体"};
    public String[] yanse=new String[]{"黑色","红色","蓝色","绿色"};
    public String[] yanshi=new String[]{"楷体","宋体","隶书","方正舒体","华文行楷","方正卡通简体"};
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);   //去掉标题栏
        setContentView(R.layout.shezhi_ziti);
        //用自定义的字体方法
        FontManager.changeFonts(FontManager.getContentView(this),this);
        System.out.println("走过！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
      //返回按钮
	      ImageView iback=(ImageView)this.findViewById(R.id.szzt_back);
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
		//下拉按钮
	      TextView daxiao=(TextView)this.findViewById(R.id.daxiao);
	      daxiao.setText("字体大小");
	      daxiao.setOnClickListener(new OnClickListener() 
			{
				@SuppressWarnings("deprecation")
				public void onClick(View v) 
				{
					showDialog(SHEZHI_DAXIAO);
				}
			});
	      TextView yanse=(TextView)this.findViewById(R.id.yanse);
	      yanse.setText("字体颜色");
	      yanse.setOnClickListener(new OnClickListener() 
			{
				@SuppressWarnings("deprecation")
				public void onClick(View v) 
				{
					showDialog(SHEZHI_YANSE);
				}
			});
	      TextView yanshi=(TextView)this.findViewById(R.id.yanshi);
	      yanshi.setText("字体样式");
	      yanshi.setOnClickListener(new OnClickListener() 
			{
				@SuppressWarnings("deprecation")
				public void onClick(View v) 
				{
					showDialog(SHEZHI_ZITI);
				}
			});
	    
	}
    @Override
    public Dialog onCreateDialog(int id)
    {
    	Dialog dialog=null;
    	
    	switch(id)
    	{    	  
    	  case SHEZHI_DAXIAO://生成单选列表对话框的代码
    		  Builder b=new AlertDialog.Builder(this);  
    		  b.setIcon(R.drawable.szzt);//设置图标
    		  b.setTitle("字体大小");//设置标题
    		  b.setSingleChoiceItems(
    		    items, 
    			 0,
    			 new DialogInterface.OnClickListener() 
        		 {
					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						System.out.println(items[which]);
						if(items[which].equals("小字体"))
						{
							if(Constant.TEXT_SIZE!=16)
							{
								Constant.TEXT_SIZE=16;
							}else{}
							
						}else if(items[which].equals("大字体")){
							if(Constant.TEXT_SIZE!=24)
							{
								Constant.TEXT_SIZE=24;
							}else{}
						}else if(items[which].equals("超大字体")){
							if(Constant.TEXT_SIZE!=32)
							{
								Constant.TEXT_SIZE=32;
							}else{}
						}
					}      			
        		 }
    		   );
    		  b.setPositiveButton//为对话框设置按钮
    		  (
    				"确定", 
    				new DialogInterface.OnClickListener()
	        		{
						@Override
						public void onClick(DialogInterface dialog, int which) {}      			
	        		}
    		  );
    		  dialog=b.create();    		  
    	  break;
    	  case SHEZHI_YANSE://生成单选列表对话框的代码
    		  b=new AlertDialog.Builder(this);  
    		  b.setIcon(R.drawable.szzt);//设置图标
    		  b.setTitle("字体颜色");//设置标题
    		  b.setSingleChoiceItems(
    	    		    yanse, 
    	    			 0,
    	    			 new DialogInterface.OnClickListener() 
    	        		 {
    						@Override
    						public void onClick(DialogInterface dialog, int which)
    						{
    							if(yanse[which].equals("红色"))
    							{
    								if(Constant.COLOR!=R.color.red)
    								{
    									Constant.COLOR=R.color.red;
    								}else{}
    								
    							}else if(yanse[which].equals("蓝色")){
    								if(Constant.COLOR!=R.color.blue)
    								{
    									Constant.COLOR=R.color.blue;
    								}else{}
    							}else if(yanse[which].equals("绿色")){
    								if(Constant.COLOR!=R.color.green)
    								{
    									Constant.COLOR=R.color.green;
    								}else{}
    							}else if(yanse[which].equals("黑色")){
    								if(Constant.COLOR!=R.color.black)
    								{
    									Constant.COLOR=R.color.black;
    								}else{}
    							}
    						}      			
    	        		 }
    	    		   );
    	    		  b.setPositiveButton//为对话框设置按钮
    	    		  (
    	    				"确定", 
    	    				new DialogInterface.OnClickListener()
    		        		{
    							@Override
    							public void onClick(DialogInterface dialog, int which) {}      			
    		        		}
    	    		  );
    		  dialog=b.create();
    		  break;
    	  case SHEZHI_ZITI://生成单选列表对话框的代码
    		  b=new AlertDialog.Builder(this);  
    		  b.setIcon(R.drawable.szzt);//设置图标
    		  b.setTitle("字体样式");//设置标题
    		  b.setSingleChoiceItems(
    	    		    yanshi, 
    	    			 0,
    	    			 new DialogInterface.OnClickListener() 
    	        		 {
    						@Override
    						public void onClick(DialogInterface dialog, int which)
    						{
    							if(yanshi[which].equals("楷体"))
    							{
    								System.out.println("++++++++++++++++++++=走过这个方法++++++++++++++++++");
    								FontManager.init(SheZhiZiTiActivity.this, "kaiti");   
    								
    							}
    							else if(yanshi[which].equals("宋体"))
    							{
    								System.out.println("***********************走过这个方法***********************");
    								FontManager.init(SheZhiZiTiActivity.this, "songti");
    								
    							}
    							else if(yanshi[which].equals("隶书"))
    							{
    								FontManager.init(SheZhiZiTiActivity.this, "lishu");
    							}
    							else if(yanshi[which].equals("方正舒体"))
    							{
    								FontManager.init(SheZhiZiTiActivity.this, "fangzhen");
    							}
								else if(yanshi[which].equals("华文行楷"))
								{
									FontManager.init(SheZhiZiTiActivity.this, "huanwen");
								}
								else if(yanshi[which].equals("方正卡通简体"))
								{
									FontManager.init(SheZhiZiTiActivity.this, "katong");
								}
    						}      			
    	        		 }
    	    		   );
    	    		  b.setPositiveButton//为对话框设置按钮
    	    		  (
    	    				"确定", 
    	    				new DialogInterface.OnClickListener()
    		        		{
    							@Override
    							public void onClick(DialogInterface dialog, int which) {}      			
    		        		}
    	    		  );
    		  dialog=b.create();
    		  break;
    	}   	
    	return dialog;    
    }
}
