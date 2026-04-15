package com.cn.loading;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.cn.hangzhou.MainActivityGroup;
import com.cn.hangzhou.R;
public class LoginActivity extends Activity
{
	File f=new File("sdcard/zshz");
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.login); 
	        
	    Thread thread = new Thread() 
	    {
	        @Override
	        public void run() 
	        {
		        try
		        {
		            sleep(2000);
		        }
		        catch (InterruptedException e)
		        {
		            e.printStackTrace();
		        }
		        finish();
		        changeFaces();
		    }
	    };
	    thread.start();       		
	}
	  
    public void changeFaces()
	{
		Intent intent = new Intent(LoginActivity.this, LoadingActivity.class);
		intent.setClass(LoginActivity.this, LoadingActivity.class);
		if(!f.exists())
		{
			
			intent.setClass(LoginActivity.this, LoadingActivity.class);
		}
		else
		{
			intent.setClass(LoginActivity.this,MainActivityGroup.class);
		}
		startActivity(intent);
		finish();
	}
}
