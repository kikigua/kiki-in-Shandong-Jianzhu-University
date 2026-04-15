package com.example.MyPopWindow;

import com.example.activity.R;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;

public class LandPopWindow extends PopupWindow
{
	public LandPopWindow(Context context)
	{
		LayoutInflater inflater=LayoutInflater.from(context);
		View v=inflater.inflate(R.layout.land_popwindow, null);
		this.setContentView(v);
		this.setWidth(LayoutParams.MATCH_PARENT);
		this.setHeight(LayoutParams.MATCH_PARENT);
		this.setFocusable(false);
		 //实例化一个ColorDrawable颜色为半透明  
        ColorDrawable dw = new ColorDrawable(0xb0000000);  
        //设置SelectPicPopupWindow弹出窗体的背景  
        this.setBackgroundDrawable(dw);
		
	}
}