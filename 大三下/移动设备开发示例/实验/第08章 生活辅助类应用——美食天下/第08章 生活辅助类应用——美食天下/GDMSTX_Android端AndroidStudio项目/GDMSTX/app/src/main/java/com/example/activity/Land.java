package com.example.activity;

import java.util.List;

import com.example.MyPopWindow.LandPopWindow;
import com.example.util.BitmapUtils;
import com.example.util.NetInfoUtil;
import com.example.util.ResponseUtil;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Land extends Activity implements OnClickListener {

	Button bt; // 登陆
	EditText usenameEt; // 用户名
	EditText pwdEt; // /密码
	Boolean flag; // 是否登陆成功
	Handler handler;
	String sname;
	String password;
	LandPopWindow lpw;		
	Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		context=this;
		usenameEt = (EditText) this.findViewById(R.id.accounts);
		pwdEt = (EditText) this.findViewById(R.id.pwd);
		bt = (Button) this.findViewById(R.id.land_bt);
		bt.setOnClickListener(this);
		handler = new Handler() 
		{

			@Override
			public void handleMessage(Message msg) 
			{
				super.handleMessage(msg);
				switch (msg.what) 
				{
				case 0:					///登陆成功
					lpw.dismiss();
					if(flag)
					{
						Land.this.finish();						
					}else
					{
						Toast.makeText(Land.this, getResources().getString(R.string.land_fail), Toast.LENGTH_SHORT).show();
					}
				}
			}
		};

	}

	@Override
	public void onClick(View v) {
		if(v==bt)
		{			
			sname=usenameEt.getText().toString().trim();
			password=pwdEt.getText().toString().trim();
			System.out.println("sname password	"+sname+" "+password);
			if(sname.equals(""))
			{
				Toast.makeText(this, "登录名不能为空", Toast.LENGTH_SHORT).show();
				return;
			}else if(password.equals(""))
			{
				Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
				return;
			}
			lpw=new LandPopWindow(this);
			lpw.showAtLocation(this.findViewById(R.id.land), Gravity.CENTER, 0, 0);
			new Thread()
			{
				
				@Override
				public void run()
				{
					try{
						flag=NetInfoUtil.isUser(sname, password);
						if(flag)
						{
							List<String> list=NetInfoUtil.getUser(sname);
							ResponseUtil.writeUserInfo(context, list);
							String picName=list.get(2);
							BitmapUtils.savePic(picName);
						}
						Message msg=new Message();
						msg.what=0;
						handler.sendMessage(msg);
					}catch(Exception e)
					{
						e.printStackTrace();
					}
					
				}
				
			}.start();
			
			
		}
		
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==KeyEvent.KEYCODE_BACK)
		{
			if(lpw!=null&&lpw.isShowing())
			{
				lpw.dismiss();
				return true;
			}			
		}
		return super.onKeyDown(keyCode, event);
	} 
}