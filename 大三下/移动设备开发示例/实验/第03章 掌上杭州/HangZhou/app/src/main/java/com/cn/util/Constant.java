package com.cn.util;

import android.R;
import android.location.Location;
import android.media.MediaPlayer;

public class Constant {
	public static final String ADD_PRE="/sdcard/zshz/"; 	 //"/sdcard/zshz/";
	public static final int WAIT_DIALOG_REPAINT=0;//等待对话框刷新消息编号
	public static final int WAIT_DIALOG=0;//等待对话框编号
	public static final int INFO_MYSQL=1;           //编号
	public static final int INFO_NEARBYSTATIO=2;
	public static  String[] ListArray;
	public static  String List;//文件内容
	public static int TEXT_SIZE=16;//文字的大小
    public static String snzy="zhongn";
	public static int COLOR=R.color.black;//字体颜色
	public static final int PHOTOHRAPH = 1;// 拍照，调用系统照相机时使用
	public static Location myLocation;//游客当前的经纬度位置
	public static final int SHOWMOREDIALOG=1;//显示更多对话框
	public static final int EXIT_DIALOG=2;//询问是否退出对话框
	public static String curSMP=null;//记录当前正在播报的景点名
	public static MediaPlayer mp;//景点介绍使用的播放器
	public static int isPlay=0;//0代表当前无景点介绍，1 代表正在播放，2 代表暂停
	public static String curScenicId=null;//记录当前显示景点的编号
	public static final int DISTANCE_SCENIC=200;//景点范围的阈值
	public static final double EARTH_RADIUS = 6378137.0; //地球半径
	public static final int DISTANCE_USER=100;//用户移动范围的阈值
	public static final int[] TEXT_SIZES={12,18,24,30,36};
	public static final String CONSUMER_KEY ="3137261270";// 鏇挎崲涓哄紑鍙戣�鐨刟ppkey锛屼緥濡�1646212960";
	public static final String CONSUMER_SECRET ="d97ff3893abb20f96231fcb7f5d35b43";
	
	public static final int FROMALBUM=0;//从手机图片库中获取照片的标志
	public static final int FROMCAMERA=1;//使用照相机拍照的标志
	//搜索周边兴趣点的范围,显示时以公里为单位
	public static final int RANGE_UNION=1000;//计算是乘以数组中的数换算成米
	public static final int[] RANGE_ITEMS={1,2,5,10};
	//通过两点经纬度计算距离的方法，返回单位为：米
		public static double jWD2M(double lat_a, double lng_a, double lat_b, double lng_b){
			double radLat1 = (lat_a*Math.PI/180.0); 
			double radLat2 = (lat_b*Math.PI/180.0); 
			double a = radLat1-radLat2; 
			double b = (lng_a- lng_b)* Math.PI/180.0; 
			double s = 2*Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) 
	          + Math.cos(radLat1)*Math.cos(radLat2)
	          * Math.pow(Math.sin(b/2),2)));
			s = s*EARTH_RADIUS; 
			s = Math.round(s*10000)/10000;
			return s; 
		}
}
