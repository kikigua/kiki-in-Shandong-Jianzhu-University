package com.example.activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

/*
 * 本地
 */
public class Loaded extends FragmentActivity implements OnClickListener{
	Button menu;
	Button random;
	ImageView last;
	View fg1;
	View fg2;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loaded);
		last=(ImageView) this.findViewById(R.id.btback);
		TextView tv=(TextView) this.findViewById(R.id.head_title);
		tv.setText(getResources().getString(R.string.loaded_text));
		menu=(Button) this.findViewById(R.id.loaded_menu);
		menu.setTextColor(Color.RED);
		random=(Button) this.findViewById(R.id.loaded_random);
		menu.setOnClickListener(this);
		random.setOnClickListener(this);	
		last.setOnClickListener(this);				//设置监听器
		fg1=this.findViewById(R.id.loaded_tab01);	//设置监听器
		fg2=this.findViewById(R.id.Loaded_tab02);	//设置监听器
		fg2.setVisibility(View.INVISIBLE);
	}
	@Override
	public void onClick(View v) {
		View left=findViewById(R.id.left);
		View right=findViewById(R.id.right);
		if(v==menu){							//切换显示离线菜品信息
			fg1.setVisibility(View.VISIBLE);
			fg2.setVisibility(View.GONE);
			menu.setTextColor(Color.RED);
			random.setTextColor(Color.BLACK);
			left.setBackgroundColor(Color.RED);
			right.setBackgroundColor(Color.BLACK);
		}else if(v==random){					//切换显示离线随拍信息
			fg2.setVisibility(View.VISIBLE);
			fg1.setVisibility(View.GONE);
			random.setTextColor(Color.RED);
			menu.setTextColor(Color.BLACK);		
			left.setBackgroundColor(Color.BLACK);
			right.setBackgroundColor(Color.RED);
		}else if(v==last){
			this.finish();
		}}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.finish();
			overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);			
		}
		return true;
	}}