package com.cn.jingdian;
import com.cn.hangzhou.R;
import com.cn.util.FontManager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class JDJYActivity extends Activity {
	//protected static final Activity MailSenderActivity = null;
	private String contentStr;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jianyi);
		 //用自定义的字体方法
        FontManager.changeFonts(FontManager.getContentView(this),this);
		Button submit=(Button)findViewById(R.id.submit);
		Button cancel=(Button)findViewById(R.id.cancel);
		submit.setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						EditText et=(EditText)findViewById(R.id.EditText01);
						contentStr=et.getText().toString();
						
						if (isConnect(JDJYActivity.this)==false) 
						{ 
						new AlertDialog.Builder(JDJYActivity.this) 
						.setTitle(getResources().getString(R.string.nettitle)) 
						.setMessage(getResources().getString(R.string.netmessage)) 
						.setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() { 
						@Override 
						public void onClick(DialogInterface arg0, int arg1) { 
						// TODO Auto-generated method stub 
						android.os.Process.killProcess(android.os.Process.myPid()); 
						System.exit(0); 
						} 
						}).show(); 
						} 
						else{
							sendThread t=new sendThread();
							t.start();
							Toast.makeText(JDJYActivity.this,
									getResources().getString(R.string.emailsuccess), Toast.LENGTH_SHORT).show();
							finish();
						}
						
					}
				}
		);
		cancel.setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						JDJYActivity.this.finish();
					}
				}
		);
	}
	public static boolean isConnect(Context context) { 

	// 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理） 
	try { 
	ConnectivityManager connectivity = (ConnectivityManager) context 
	.getSystemService(Context.CONNECTIVITY_SERVICE); 
	if (connectivity != null) { 

	// 获取网络连接管理的对象 
	NetworkInfo info = connectivity.getActiveNetworkInfo(); 

	if (info != null&& info.isConnected()) { 
	// 判断当前网络是否已经连接 
	if (info.getState() == NetworkInfo.State.CONNECTED) { 
	return true; 
	} 
	} 
	} 
	} catch (Exception e) { 
	e.printStackTrace(); 
	} 
	return false; 
	} 
	class sendThread extends Thread{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			MailSenderInfo mailInfo = new MailSenderInfo();
			mailInfo.setMailServerHost("smtp.163.com");
			mailInfo.setMailServerPort("25");
			mailInfo.setValidate(true);
			mailInfo.setUserName("m18712852082@163.com");
			mailInfo.setPassword("q15002233214");// 您的邮箱密码
			mailInfo.setFromAddress("m18712852082@163.com");
			mailInfo.setToAddress("m18712852082@163.com");
			mailInfo.setSubject("");
			mailInfo.setContent(contentStr);
			SimpleMailSender sms = new SimpleMailSender();
			sms.sendTextMail(mailInfo);
			
		}
		
	}
}
