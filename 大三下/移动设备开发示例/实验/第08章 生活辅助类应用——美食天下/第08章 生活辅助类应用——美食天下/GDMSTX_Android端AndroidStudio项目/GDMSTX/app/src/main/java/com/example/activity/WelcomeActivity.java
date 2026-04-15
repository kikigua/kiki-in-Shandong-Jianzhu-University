package com.example.activity;

import com.example.MySQLite.DatabaseUtil;
import com.example.chat.FaceUtils;
import com.example.downLoader.ImageDownLoader;
import com.example.util.BitmapCache;
import com.example.util.Constant;
import com.example.util.FileUtils;
import com.example.util.BitmapUtils;
import com.example.util.NetInfoUtil;
import com.example.util.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class WelcomeActivity extends Activity {

	Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.anim_start);
		context=this;
		// 初始化
		new Thread() {
			@Override
			public void run() {
				try {					
					//第一次安装时的操作
					isFirstStart(context);
					// 加载表情图片
					FaceUtils.getInstace().getFileText(getApplicationContext());
					//加载图片Cache
					BitmapCache.initCache();
					//加载屏幕信息
					Constant.init(context);
					//加载文件信息
					new FileUtils(context);					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();		
		Animation aa = AnimationUtils.loadAnimation(this, R.anim.start_al);
		ImageView iv = (ImageView) this.findViewById(R.id.start_bg);
		iv.startAnimation(aa);
		aa.setAnimationListener(new myAnimationListener());
	}
	private void isFirstStart(Context context){
		SharedPreferences sp = getSharedPreferences("init",
				MODE_WORLD_READABLE);
		Editor editor = sp.edit();
		int count = sp.getInt("count", 0);
		System.out.println("isFirstStart");
		if (count == 0) {			
				try {	
					//将标签数据插入数据库
					String[] str=FileUtils.loadFromSDFile(context, "label.txt").split(",");
					DatabaseUtil.insertLabels(context, str);
				} catch (Exception e) {
					e.printStackTrace();
				} 
		}		
		editor.putInt("count", ++count);
		editor.commit();
	}
	private class myAnimationListener implements AnimationListener {
		@Override
		public void onAnimationEnd(Animation animation) {
			startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
			finish();
		}

		@Override
		public void onAnimationRepeat(Animation animation) {
		}

		@Override
		public void onAnimationStart(Animation animation) {
		}
	}
}
