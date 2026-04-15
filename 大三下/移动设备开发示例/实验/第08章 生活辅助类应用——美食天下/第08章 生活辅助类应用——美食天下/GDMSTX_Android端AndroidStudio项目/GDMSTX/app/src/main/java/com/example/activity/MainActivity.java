package com.example.activity;

import com.example.util.Utils;
import com.king.photo.util.Bimp;
import com.king.photo.util.PublicWay;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.Toast;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class MainActivity extends FragmentActivity implements OnClickListener {
	PopupWindow pop; // 自定义的弹出框类
	TabHost tabHost;
	TabWidget tabWidget;
	ViewFlipper vf;
	ImageButton loaded; // 搜索按钮

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tabHost = (TabHost) findViewById(R.id.tabhost);
		tabHost.setup();
		tabWidget = tabHost.getTabWidget();
		tabHost.addTab(tabHost
				// 设置将标签页和标签内容
				.newTabSpec("one")
				.setIndicator(
						getResources().getString(R.string.home),
						getResources().getDrawable(
								R.drawable.image_selector_home))
				.setContent(R.id.tab01));
		tabHost.addTab(tabHost
				.newTabSpec("two")
				.setIndicator(
						getResources().getString(R.string.random),
						getResources().getDrawable(
								R.drawable.image_selector_random))
				.setContent(R.id.tab02));
		tabHost.addTab(tabHost
				.newTabSpec("three")
				.setIndicator(
						getResources().getString(R.string.menu),
						getResources().getDrawable(
								R.drawable.image_selector_menu))
				.setContent(R.id.tab03));
		tabHost.addTab(tabHost
				.newTabSpec("foure")
				.setIndicator(
						getResources().getString(R.string.me),
						getResources()
								.getDrawable(R.drawable.image_selector_me))
				.setContent(R.id.tab04));

		// 指示符底部的分隔线是否绘制
		tabWidget.setStripEnabled(false);
		upTabselected();
		tabHost.setOnTabChangedListener(new OnTabChangeListener() {
			@Override
			public void onTabChanged(String tabId) {
				upTabselected();
			}
		});
		initFuction(); // 设置监听器
		initPop(); // 初始化PopupWindow
	}

	private void initFuction() {
		ImageButton select_img = (ImageButton)findViewById(R.id.search_right_btn);
		select_img.setOnClickListener(
				new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						SearchActivity.class);
				startActivity(intent);
			}
		});
		loaded = (ImageButton) findViewById(R.id.left_right_btn);
		loaded.setOnClickListener(this);
	}

	private void initPop() {
		pop = new PopupWindow();
		pop.setWidth(LayoutParams.MATCH_PARENT);
		pop.setHeight(LayoutParams.WRAP_CONTENT);
		ColorDrawable dw = new ColorDrawable(0xa0000000);
		pop.setBackgroundDrawable(dw);
		pop.setFocusable(true);
		pop.setOutsideTouchable(true);
		View view = getLayoutInflater().inflate(R.layout.lay_pop_upload, null);
		pop.setContentView(view);
		View parent = view.findViewById(R.id.parent);
		parent.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				pop.dismiss();
			}
		});
		TextView pai = (TextView) view.findViewById(R.id.pop_upload_pai);
		TextView menu = (TextView) view.findViewById(R.id.pop_upload_recipe);
		TextView cancel = (TextView) view.findViewById(R.id.pop_upload_cancel);
		pai.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, UploadPaiActivity.class);
				startActivity(intent);
				pop.dismiss();
			}
		});
		menu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, UploadActivity.class);
				startActivity(intent);
				pop.dismiss();
			}
		});
		cancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				pop.dismiss();
			}
		});
		RelativeLayout rl = (RelativeLayout) findViewById(R.id.head_main);
		rl = (RelativeLayout) findViewById(R.id.head_main);
		ImageButton add_img = (ImageButton) rl
				.findViewById(R.id.upload_right_btn);
		add_img.setOnClickListener(new OnClickListener() {
			public void onClick(View v) { // 实例化SelectPicPopupWindow
				if (!Utils.isLand(getApplicationContext())) {
					Intent intent = new Intent(MainActivity.this, Land.class);
					startActivity(intent);
					return;
				}// 显示窗口
				pop.showAtLocation(MainActivity.this.findViewById(R.id.main),
						Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); // 设置layout在PopupWindow中显示的位置
			}
		});
	}

	private void upTabselected() {
		for (int i = 0; i < tabWidget.getChildCount(); i++) {
			RelativeLayout view = (RelativeLayout) tabWidget.getChildAt(i);
			view.setBackgroundColor(Color.WHITE);
			TextView tv = (TextView) view.findViewById(android.R.id.title);
			RelativeLayout rl = (RelativeLayout) findViewById(R.id.head_main);
			TextView tv_head = (TextView) rl.findViewById(R.id.head_title);
			if (tabHost.getCurrentTab() == i) {
				tv.setTextColor(Color.RED);
				tv_head.setText(tv.getText().toString());
			} else {
				tv.setTextColor(Color.GRAY);
			}
		}
	}

	@Override
	public void onClick(View v) {
		if (v == loaded) {
			Intent intent = new Intent(this, Loaded.class);
			startActivity(intent);
			overridePendingTransition(R.anim.push_left_in,
					R.anim.push_right_out);
		}
	}

	private long exitTime = 0;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exit();
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

	private void exit() {
		if ((System.currentTimeMillis() - exitTime) > 1500) {
			Toast.makeText(getApplicationContext(),
					getResources().getString(R.string.exit_again),
					Toast.LENGTH_SHORT).show();
			exitTime = System.currentTimeMillis();
		} else {
			finish();
			System.exit(0);
		}
	}
}
