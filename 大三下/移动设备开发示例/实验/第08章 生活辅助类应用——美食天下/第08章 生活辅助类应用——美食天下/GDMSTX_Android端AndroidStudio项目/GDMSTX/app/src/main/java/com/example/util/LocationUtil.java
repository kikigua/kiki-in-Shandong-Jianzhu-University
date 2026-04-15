package com.example.util;

import android.content.Context;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.activity.UploadPaiActivity;

public class LocationUtil {
	public LocationClient mLocationClient = null;
	public LocationUtil(Context context) {
		mLocationClient = new LocationClient(context); 			//声明LocationClient类
		mLocationClient.registerLocationListener(new MyLocationListener()); // 注册监听函数
		setLocationOption();
		mLocationClient.start();								//启动定位功能
	}
	private void setLocationOption(){
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);							//启用GPS
		option.setIsNeedAddress(true);						//返回的定位结果包含地址信息
		option.setCoorType("bd09ll");						//返回的定位结果是百度经纬度,默认值gcj02
		option.setScanSpan(5000); 							//设置发起定位请求的间隔时间为5000ms息
		mLocationClient.setLocOption(option);
	}
	private class MyLocationListener implements BDLocationListener {	 //实现实时位置回调监听
		@Override
		public void onReceiveLocation(BDLocation location) {			
			if (location == null) 
			{mLocationClient.stop();return;}
			try {
				UploadPaiActivity.tv_adress.setText(location.getCity());	//加载位置信息
			} catch (Exception e) {	
				e.printStackTrace();					//没有定位，设置为空
				UploadPaiActivity.tv_adress.setHint("定位失败");
			}
			mLocationClient.stop();					
		}
	}
}
