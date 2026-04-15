package com.example.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
public class Utils {
	// 屏幕宽
	public static int getScreenWidth(Context context) {
		WindowManager manager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = manager.getDefaultDisplay();
		return display.getWidth();
	}

	// 屏幕高
	public static int getScreenHeight(Context context) {
		WindowManager manager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = manager.getDefaultDisplay();
		return display.getHeight();
	}

	public static float getScreenDensity(Context context) {
		try {
			DisplayMetrics dm = new DisplayMetrics();
			WindowManager manager = (WindowManager) context
					.getSystemService(Context.WINDOW_SERVICE);
			manager.getDefaultDisplay().getMetrics(dm);
			return dm.density;
		} catch (Exception ex) {

		}
		return 1.0f;
	}

	/*
	 * author GCK
	 */

	// /判断当前网络是否可用
	public static Boolean isNewWork(Context context) {
		ConnectivityManager connMgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();
		if (activeInfo != null && activeInfo.isConnected()) {
			return true;
		}
		return false;
	}

	// 判断网络类型
	public static String netType(Context context) {
		String netType = null;
		ConnectivityManager connMgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();
		if (activeInfo != null && activeInfo.isConnected()) {
			netType = activeInfo.getTypeName();
		}
		return netType;
	}

	// 是否登陆
	public static Boolean isLand(Context context) {
		SharedPreferences sp = context.getSharedPreferences("mstx",
				Context.MODE_PRIVATE);
		String name = sp.getString("username", "null");
		String passward = sp.getString("password", "null");
		if (name.equals("null") || passward.endsWith("null")) {
			return false;
		}
		return true;
	}

	// 获取本地上次登陆的用户名和密码
	public static String[] getUser(Context context) {
		SharedPreferences sp = context.getSharedPreferences("mstx",
				Context.MODE_PRIVATE);
		String name = sp.getString("username", "null");
		String passward = sp.getString("password", "null");
		if (!name.equals("null") && !passward.endsWith("null")) {
			return new String[] { name, passward };
		}
		return null;
	}
	
	// /与系统时间差
	public static String getTimeDiff(String date) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = null;
		try {
			d1 = sf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long l1 = d1.getTime();
		long l2 = new Date().getTime();
		long diff = l2 - l1;
		int days = (int) Math.floor((diff / (1000 * 60 * 60 * 24)));
		int hours = (int) Math.floor((diff / (1000 * 60 * 60)));
		int mm = (int) Math.floor((diff / (1000 * 60)));
		if (days > 0)
			return days + "天前";
		else if (hours > 0)
			return hours + "小时前";
		else if (mm > 0)
			return mm + "分钟前";
		else
			return "刚刚";
	}
}