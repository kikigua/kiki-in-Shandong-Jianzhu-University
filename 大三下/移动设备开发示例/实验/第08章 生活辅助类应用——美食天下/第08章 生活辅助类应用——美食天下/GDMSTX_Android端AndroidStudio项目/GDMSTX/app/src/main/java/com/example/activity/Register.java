package com.example.activity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import com.example.MyPopWindow.LandPopWindow;
import com.example.MyPopWindow.SexPopupwindow;
import com.example.MyPopWindow.SexPopupwindow.A;
import com.example.util.Constant;
import com.example.util.BitmapUtils;
import com.example.util.NetInfoUtil;
import com.example.util.ResponseUtil;
import com.example.util.UploadUtil;
import com.example.util.Utils;
import com.king.photo.util.Bimp;
import com.king.photo.util.ImageItem;
import com.king.photo.util.PublicWay;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends Activity implements OnClickListener {

	Button bt; // 登陆
	EditText usenameEt; // 用户名
	EditText pwdEt; // /密码
	TextView sextv;
	Boolean flag; // 是否登陆成功
	Handler handler;
	String sname;
	String password;
	String sex;
	LandPopWindow lpw;	
	ImageView sculture;
	RelativeLayout rl;
	PopupWindow pop;
	InputMethodManager imm;
	String pripath;
	PopupWindow popsex;
	Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		usenameEt = (EditText) this.findViewById(R.id.accounts);
		pwdEt = (EditText) this.findViewById(R.id.pwd);
		sextv=(TextView) this.findViewById(R.id.sex);
		sextv.setOnClickListener(this);
		bt = (Button) this.findViewById(R.id.ok_bt);
		bt.setOnClickListener(this);
		rl=(RelativeLayout) this.findViewById(R.id.rl_sculpture);
		rl.setOnClickListener(this);
		sculture=(ImageView) this.findViewById(R.id.sculpture);
		initView();
		context=this;
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
						
						Register.this.finish();						
					}else
					{
						Toast.makeText(Register.this, "注册失败", Toast.LENGTH_SHORT).show();
					}
					break;
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
			sex=sextv.getText().toString().trim();
			System.out.println("sname password	"+sname+" "+password);
			if(pripath==null||pripath.length()<1)
			{
				Toast.makeText(this, "请添加头像", Toast.LENGTH_SHORT).show();
				return;
			}
			else if(sname.equals(""))
			{
				Toast.makeText(this, "注册名不能为空", Toast.LENGTH_SHORT).show();
				return;
			}else if(password.equals(""))
			{
				Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
				return;
			}
			lpw=new LandPopWindow(this);
			lpw.showAtLocation(this.findViewById(R.id.parent), Gravity.CENTER, 0, 0);
			new Thread()
			{
				
				@Override
				public void run()
				{
					String picName=null;
						try {
							picName=UploadUtil.uploadPic(pripath);
							if (picName==null) {
								flag=false;
								return;
							}
						flag=NetInfoUtil.register(sname, password,picName,sex);
						if(flag)
						{
							List<String> list=NetInfoUtil.getUser(sname);
							ResponseUtil.writeUserInfo(context,list);
							String name=list.get(2);
							BitmapUtils.savePic(name);
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
		else if(v==rl)
		{
			pop.showAtLocation(findViewById(R.id.parent), Gravity.BOTTOM
					| Gravity.CENTER_HORIZONTAL, 0, 0); // 设置layout在PopupWindow中显示的位置
			imm.hideSoftInputFromWindow(Register.this.getCurrentFocus()
					.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}else if(v==sextv)
		{
			popsex.showAtLocation(findViewById(R.id.parent), Gravity.BOTTOM
					| Gravity.CENTER_HORIZONTAL, 0, 0);
		}
		
	}
	private void initView()
	{
		popsex=new SexPopupwindow(this,new A()
		{
			@Override
			public void onSelected(String mCurrentProviceName) {
				sextv.setText(mCurrentProviceName);
				popsex.dismiss();
			}			
		});		
		imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		pop = new PopupWindow();
		pop.setWidth(LayoutParams.MATCH_PARENT);
		pop.setHeight(LayoutParams.WRAP_CONTENT);
		ColorDrawable dw = new ColorDrawable(0xa0000000);  
        pop.setBackgroundDrawable(dw);
		pop.setFocusable(true);
		pop.setOutsideTouchable(true);
		View view = getLayoutInflater().inflate(R.layout.take_pic_popwindow,
				null);
		pop.setContentView(view);
		View parent = view.findViewById(R.id.parent);
		parent.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				pop.dismiss();
			}
		});
		TextView take = (TextView) view.findViewById(R.id.take_pic_camera);
		TextView lib = (TextView) view.findViewById(R.id.lib);
		TextView cancel = (TextView) view.findViewById(R.id.cancel);
		cancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				pop.dismiss();
			}
		});	
		PublicWay.num = 1;
		Bimp.tempSelectBitmap.clear();
		lib.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Register.this,
						AlbumActivity.class);
				Bimp.tempSelectBitmap.clear();
				intent.putExtra("requestCode", Constant.PICTURE_SIMLE);
				intent.putExtra("isPai", false);
				startActivityForResult(intent, Constant.PICTURE_SIMLE);
				overridePendingTransition(R.anim.activity_translate_in,
						R.anim.activity_translate_out);
				pop.dismiss();
			}
		});
		take.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				camera.putExtra("isPai", false);
				startActivityForResult(camera, Constant.CAMERA_SIMPLE);
				pop.dismiss();
			}
		});
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		//主图 照片
		System.out.println("result requestCode"+requestCode);
		System.out.println("result resultCode"+resultCode);
		if (requestCode == Constant.CAMERA_SIMPLE 
				&& resultCode == Activity.RESULT_OK && null != data) {
			String sdState = Environment.getExternalStorageState();
			if (!sdState.equals(Environment.MEDIA_MOUNTED)) {
				System.out.println("sd card unmount");
				return;
			}
			new DateFormat();
			String name = DateFormat.format("yyyyMMdd_hhmmss",
					Calendar.getInstance(Locale.CHINA))
					+ ".jpg";
			Bundle bundle = data.getExtras();
			// 获取相机返回的数据，并转换为图片格式
			Bitmap bitmap = (Bitmap) bundle.get("data");
			FileOutputStream fout = null;
			File file = new File("/sdcard/pintu/");
			file.mkdirs();
			pripath = file.getPath() + name;
			try {
				fout = new FileOutputStream(pripath);
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fout);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				try {
					fout.flush();
					fout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			sculture.setImageBitmap(bitmap);
		}
		//主图 图库
		if(resultCode==Constant.PICTURE_SIMLE)
		{
			if(Bimp.tempSelectBitmap.size()>=0)
			{
				pripath=Bimp.tempSelectBitmap.get(0).getImagePath();
				Bitmap bm=BitmapUtils.revitionImageSize(pripath);
				sculture.setImageBitmap(bm);
			}

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