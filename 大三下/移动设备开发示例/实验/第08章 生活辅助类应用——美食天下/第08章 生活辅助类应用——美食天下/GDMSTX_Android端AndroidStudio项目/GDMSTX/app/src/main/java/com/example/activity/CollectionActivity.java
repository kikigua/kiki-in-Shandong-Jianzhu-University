package com.example.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioButton;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import android.widget.TabHost.OnTabChangeListener;
/**
 * 
 * @author Administrator
 * 	收藏界面
 */
public class CollectionActivity extends FragmentActivity implements OnClickListener 
{
	TabHost tabHost;
	TabWidget tabWidget;
	Button menu;
	Button random;
	ImageView last;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.collection);
		last=(ImageView) this.findViewById(R.id.loaded_last);
		menu=(Button) this.findViewById(R.id.loaded_menu);
		menu.setTextColor(Color.RED);
		random=(Button) this.findViewById(R.id.loaded_random);
		menu.setOnClickListener(this);
		random.setOnClickListener(this);
		last.setOnClickListener(this);
		tabHost = (TabHost) findViewById(R.id.loaded_tabhost);            
        tabHost.setup();
        tabWidget=tabHost.getTabWidget();  	        
      
        tabHost.addTab(tabHost.newTabSpec("one").setIndicator("菜谱").setContent(R.id.loaded_tab01));
        tabHost.addTab(tabHost.newTabSpec("two").setIndicator("随拍").setContent(R.id.Loaded_tab02));
     
        //指示符底部的分隔线是否绘制
        tabWidget.setStripEnabled(false);
	}
	@Override
	public void onClick(View v) {
		View left=findViewById(R.id.left);
		View right=findViewById(R.id.right);
		if(v==menu&&tabHost.getCurrentTab()!=0)
		{
			tabHost.setCurrentTab(0);
			menu.setTextColor(Color.RED);
			random.setTextColor(Color.BLACK);
			left.setBackgroundColor(Color.RED);
			right.setBackgroundColor(Color.BLACK);
			

		}else if(v==random&&tabHost.getCurrentTab()!=1)
		{
			tabHost.setCurrentTab(1);
			random.setTextColor(Color.RED);
			menu.setTextColor(Color.BLACK);		
			left.setBackgroundColor(Color.BLACK);
			right.setBackgroundColor(Color.RED);

		}else if(v==last)
		{
			System.out.println("last");
			this.finish();
		}
	}
	
	
}