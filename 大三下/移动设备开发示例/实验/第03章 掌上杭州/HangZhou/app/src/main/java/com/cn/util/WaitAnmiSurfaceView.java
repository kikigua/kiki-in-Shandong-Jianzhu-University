package com.cn.util;

import com.cn.hangzhou.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class WaitAnmiSurfaceView extends View 
{
	Paint paint;
	float distance=0;
	float viewWidth=80;
	float viewHeight=80; 
	float scaleSpeedSpan=200;
	Bitmap bitmapTmp;
	float picWidth;
	float picHeight;
	public float angle=90;

	public WaitAnmiSurfaceView(Context activity,AttributeSet as) 
	{
		super(activity,as);
		paint = new Paint();
		paint.setAntiAlias(true);
		
		bitmapTmp=BitmapFactory.decodeResource(activity.getResources(),R.drawable.star);
		picWidth=bitmapTmp.getWidth();
		picHeight=bitmapTmp.getHeight();
	} 

	@SuppressLint("DrawAllocation")
	public void onDraw(Canvas canvas)
	{		
		
		paint.setColor(Color.WHITE);
		
		float left=(viewWidth-picWidth)/2+80;
		float top=(viewHeight-picHeight)/2+80;      
		Matrix m1=new Matrix();
		m1.setTranslate(left,top);
		Matrix m3=new Matrix();
		m3.setRotate(angle, viewWidth/2+80, viewHeight/2+80);
		Matrix mzz=new Matrix();
		mzz.setConcat(m3, m1);
		canvas.drawBitmap(bitmapTmp, mzz, paint);
	}
	
	
    public void repaint()
    {
		this.invalidate();
	}
}