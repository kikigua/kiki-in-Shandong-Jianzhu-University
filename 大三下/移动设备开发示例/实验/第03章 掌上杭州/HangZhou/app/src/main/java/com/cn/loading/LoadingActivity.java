package com.cn.loading;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;

import com.cn.hangzhou.MainActivityGroup;
import com.cn.hangzhou.R;
import com.cn.util.Constant;
import com.cn.util.WaitAnmiSurfaceView;
import com.cn.util.ZipUtil;
 
public class LoadingActivity extends Activity 
{    
    Dialog waitDialog;
    WaitAnmiSurfaceView wasv;

	Handler hd=new Handler()
    {
    	@SuppressLint("HandlerLeak")
		public void handleMessage(Message msg)
    	{
    		switch(msg.what)
    		{
    		  case Constant.WAIT_DIALOG_REPAINT:	
    		      wasv.repaint();
    	      break;
    		}	
    	}
    };
	@Override
    public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        

        requestWindowFeature(Window.FEATURE_NO_TITLE); 
        
	
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);        
        setContentView(R.layout.login);
        extracted();
    }
	@SuppressWarnings("deprecation")
	private void extracted() 
	{
		showDialog(Constant.WAIT_DIALOG);
	}
	
	@Override
    public Dialog onCreateDialog(int id)
    {    	
    	Dialog result=null;
    	switch(id)
    	{
		    case Constant.WAIT_DIALOG:
		      	AlertDialog.Builder b=new AlertDialog.Builder(this); 
				b.setItems(null, null);
				b.setCancelable(false);
				waitDialog=b.create();
				result=waitDialog;	    		
		    break;  
    	}
    	return result;
    }
	@Override
    public void onPrepareDialog(int id, final Dialog dialog)
    {
  
    	if(id!=Constant.WAIT_DIALOG)return;
        dialog.setContentView(R.layout.loading);
        wasv=(WaitAnmiSurfaceView)dialog.findViewById(R.id.wasv);
        new Thread()
        {
			public void run()
        	{	        		
        		for(int i=0;i<200;i++)
        		{
        			wasv.angle=wasv.angle+5; 
        			hd.sendEmptyMessage(Constant.WAIT_DIALOG_REPAINT);
        			try
        			{
        				Thread.sleep(50);
        			}
        			catch(Exception e)
        			{
        				e.printStackTrace();
        			}
        		}
        		dialog.cancel();
        		unzipAndChange();
        	}	        	
        }.start();
    }
	public void unzipAndChange()
	{
		try
		{
			//瑙ｅ帇
			ZipUtil.unZip(LoadingActivity.this, "zshz.zip", "/sdcard/");				
		}
		catch(Exception e)
		{
			System.out.println("解压出错！");
		}
		Intent intent=new Intent();
		intent.setClass(LoadingActivity.this,MainActivityGroup.class);
		startActivity(intent);
		finish();
	}
}