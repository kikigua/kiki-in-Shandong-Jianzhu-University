package com.king.photo.util;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

public class PublicWay {
	
	public static List<Activity> activityList = new ArrayList<Activity>();
	public static int num;				//当前允许最多图片张数
	public static int SELECT_PREVIEW=-2;	//浏览已选择的图片
	public static int ALL_PIC_PREVIEW=-1;	//浏览所有图片
}
