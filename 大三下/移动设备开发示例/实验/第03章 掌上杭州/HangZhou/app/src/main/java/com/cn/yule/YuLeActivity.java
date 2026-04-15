package com.cn.yule;

import java.util.ArrayList;
import com.cn.hangzhou.R;
import com.cn.util.FontManager;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class YuLeActivity extends Activity implements OnClickListener, OnPageChangeListener
{
	TextView[] tv;
	ViewPager vp;
	ArrayList<View> al;
	
	View v_ktv;
	View v_bar;
	View v_foot;
	View v_club;
	
	 @Override
	 public void onCreate(Bundle savedInstanceState) 
	 {
		 super.onCreate(savedInstanceState);
	     setContentView(R.layout.yule_main);
	   //用自定义的字体方法
	        FontManager.changeFonts(FontManager.getContentView(this),this);
	     
	     tv=new TextView[4];
         tv[0]=(TextView) findViewById(R.id.tv_ktv);
         tv[1]=(TextView) findViewById(R.id.tv_bar);
         tv[2]=(TextView) findViewById(R.id.tv_foot);
         tv[3]=(TextView) findViewById(R.id.tv_club);
         vp=(ViewPager) findViewById(R.id.viewpager);
         
         tv[2].setText("影院");
         
         //一开始的选中项
        tv[0].setBackgroundColor(Color.GRAY);
        tv[1].setBackgroundColor(Color.LTGRAY);
		tv[2].setBackgroundColor(Color.LTGRAY);
		tv[3].setBackgroundColor(Color.LTGRAY);
         
		 //为按钮设置监听
		for(int i=0;i<4;i++)
		{
			tv[i].setOnClickListener(this);
		}
		
		 al=new ArrayList<View>();
	     LayoutInflater li=LayoutInflater.from(this);
		
	     v_ktv = li.inflate(R.layout.yule_ktv, null);
         v_bar=li.inflate(R.layout.yule_bar,null);
         v_foot=li.inflate(R.layout.yule_foot,null);
         v_club=li.inflate(R.layout.yule_club, null);
         
         al.add(v_ktv);
         al.add(v_bar);
         al.add(v_foot);
         al.add(v_club);
         
         //一开始选中的界面
         Ktv ktv=new Ktv(v_ktv,this,"ktv");
	     ktv.init();
         
         PagerAdapter pa=new PagerAdapter(){
				@Override
				public void destroyItem(View arg0, int arg1, Object arg2) {
					// TODO Auto-generated method stub
					((ViewPager)arg0).removeView((View)al.get(arg1));
				}
				@Override
				public void finishUpdate(View arg0) {
					// TODO Auto-generated method stub
				}
				@Override
				public int getCount() {
					// TODO Auto-generated method stub
					return al.size();
				}
				@Override
				public Object instantiateItem(View arg0, int arg1) {
					((ViewPager)arg0).addView((View)al.get(arg1), 0);
					return (View)al.get(arg1);
				}
				@Override
				public boolean isViewFromObject(View arg0, Object arg1) {
					// TODO Auto-generated method stub
					return arg0==arg1;
				}

				@Override
				public void restoreState(Parcelable arg0, ClassLoader arg1) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public Parcelable saveState() {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public void startUpdate(View arg0) {
					// TODO Auto-generated method stub
					
				}};
	        vp.setAdapter(pa);
	        vp.setCurrentItem(0);
			vp.setOnPageChangeListener(this);
			
			
			//返回按钮
		      ImageView iback=(ImageView)this.findViewById(R.id.fl_back);
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
	     
	 }
	 
	 
	 @Override
     public void onClick(View v)    //按钮监听
	 {       
			
			if(v.getId()==R.id.tv_ktv)
			{
				tv[0].setBackgroundColor(Color.GRAY);
				tv[1].setBackgroundColor(Color.LTGRAY);
				tv[2].setBackgroundColor(Color.LTGRAY);
				tv[3].setBackgroundColor(Color.LTGRAY);
				vp.setCurrentItem(0);
				
				Ktv ktv=new Ktv(v_ktv,this,"ktv");
				ktv.init();
				
			}
			if(v.getId()==R.id.tv_bar)
			{
				tv[1].setBackgroundColor(Color.GRAY);
				tv[0].setBackgroundColor(Color.LTGRAY);
				tv[2].setBackgroundColor(Color.LTGRAY);
				tv[3].setBackgroundColor(Color.LTGRAY);
				vp.setCurrentItem(1);
				
				Ktv ktv=new Ktv(v_bar,this,"bar");
				ktv.init();
			}
			if(v.getId()==R.id.tv_foot)
			{
				tv[2].setBackgroundColor(Color.GRAY);
				tv[0].setBackgroundColor(Color.LTGRAY);
				tv[1].setBackgroundColor(Color.LTGRAY);
				tv[3].setBackgroundColor(Color.LTGRAY);
				vp.setCurrentItem(2);
				
				Ktv ktv=new Ktv(v_foot,this,"foot");
				ktv.init();
			}
			if(v.getId()==R.id.tv_club)
			{
				tv[3].setBackgroundColor(Color.GRAY);
				tv[0].setBackgroundColor(Color.LTGRAY);
				tv[1].setBackgroundColor(Color.LTGRAY);
				tv[2].setBackgroundColor(Color.LTGRAY);
				vp.setCurrentItem(3);
				
				Ktv ktv=new Ktv(v_club,this,"club");
				ktv.init();
			}
	}


	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		
		for(int i=0;i<tv.length;i++)
		{
			if(i==arg0)
			{
				if(i==0)          //更改textView
				{
					tv[0].setBackgroundColor(Color.GRAY);
					tv[1].setBackgroundColor(Color.LTGRAY);
					tv[2].setBackgroundColor(Color.LTGRAY);
					tv[3].setBackgroundColor(Color.LTGRAY);
					
					Ktv ktv=new Ktv(v_ktv,this,"ktv");
					ktv.init();
				}
				if(i==1)
				{  
					tv[1].setBackgroundColor(Color.GRAY);
					tv[0].setBackgroundColor(Color.LTGRAY);
					tv[2].setBackgroundColor(Color.LTGRAY);
					tv[3].setBackgroundColor(Color.LTGRAY);
					
					Ktv ktv=new Ktv(v_bar,this,"bar");
					ktv.init();					
				}
				if(i==2)
				{
					tv[2].setBackgroundColor(Color.GRAY);
					tv[0].setBackgroundColor(Color.LTGRAY);
					tv[1].setBackgroundColor(Color.LTGRAY);
					tv[3].setBackgroundColor(Color.LTGRAY);
					
					Ktv ktv=new Ktv(v_foot,this,"foot");
					ktv.init();
				}
				if(i==3)
				{
					tv[3].setBackgroundColor(Color.GRAY);
					tv[0].setBackgroundColor(Color.LTGRAY);
					tv[1].setBackgroundColor(Color.LTGRAY);
					tv[2].setBackgroundColor(Color.LTGRAY);
					
					Ktv ktv=new Ktv(v_club,this,"club");
					ktv.init();
				}
			}
		}
	}
}

