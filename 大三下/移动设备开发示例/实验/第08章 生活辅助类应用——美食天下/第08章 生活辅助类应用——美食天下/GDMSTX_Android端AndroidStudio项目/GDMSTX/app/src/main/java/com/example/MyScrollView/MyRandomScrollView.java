package com.example.MyScrollView;
import com.example.activity.R;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ScrollView;

/*
 * 自定义向上滑动出现底部菜单的ScrollView
 */
public class MyRandomScrollView extends ScrollView 
{
	private GestureDetector mGestureDetector;
	private LinearLayout ll;
	private float STANDARD=25;	///底部菜单出现速度标准标准
	public MyRandomScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mGestureDetector=new GestureDetector(new mGestureDetectorListener());
	}
	
	public void setBottomMenu(LinearLayout ll)
	{
		this.ll=ll;
	}
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
			if(mGestureDetector.onTouchEvent(ev))
			{
				return true;
			}
		return super.onTouchEvent(ev);
	}	
	private class mGestureDetectorListener 
	extends GestureDetector.SimpleOnGestureListener
	{
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			if(distanceY>STANDARD)
			{
				if(ll.getVisibility()!=View.GONE)
					ll.setVisibility(View.GONE);
				//向上
			}else if(distanceY<-STANDARD)
			{
				if(ll.getVisibility()!=View.VISIBLE)
					ll.setVisibility(View.VISIBLE);
				//向下 出现底部菜单
			}				
			return super.onScroll(e1, e2, distanceX, distanceY);
		}
	}
}