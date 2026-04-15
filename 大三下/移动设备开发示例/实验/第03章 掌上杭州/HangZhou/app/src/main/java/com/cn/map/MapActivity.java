package com.cn.map;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.lbsapi.auth.LBSAuthManagerListener;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.navisdk.BNaviEngineManager.NaviEngineInitListener;
import com.baidu.navisdk.BaiduNaviManager;
import com.cn.hangzhou.R;

public class MapActivity extends Activity {

	MapView mMapView=null;//地图界面
	BaiduMap mBaiduMap;//百度地图
	BitmapDescriptor bitmap;//添加在地图上的气球

	 int eX;                     //经度
     int eY;                      //纬度
     String nameStr; 
     
 	private InfoWindow mInfoWindow;
 	LatLng nodeLocation;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SDKInitializer.initialize(getApplicationContext()); 
		setContentView(R.layout.map_main);
		// 地图初始化		
		mMapView = (MapView) findViewById(R.id.bmapView);
		mBaiduMap = mMapView.getMap();
		

		 //普通地图  
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        mBaiduMap.setTrafficEnabled(true);
        
        BaiduNaviManager.getInstance().initEngine(this, getSdcardDir(),
                mNaviEngineInitListener, new LBSAuthManagerListener() {
                    @Override
                    public void onAuthResult(int status, String msg) {
                        String str = null;
                        if (0 == status) {
                            str = "key校验成功!";
                        } else {
                            str = "key校验失败, " + msg;
                        }
                        Toast.makeText(MapActivity.this, str,
                                Toast.LENGTH_LONG).show();
                    }
                });
        
        
      //接受经度
  		eX=this.getIntent().getIntExtra("longN",12016984);
  		eY=this.getIntent().getIntExtra("latN",3027673);
  	  //接受店名
  		nameStr=this.getIntent().getStringExtra("name");
  		
  		 float	longF=(float)(eX * 1e-5);
  		 float latF=(float)(eY * 1e-5);
        
     nodeLocation=new LatLng(latF,longF);
		//构建Marker图标  
        bitmap = BitmapDescriptorFactory.fromResource(R.drawable.ballon);  
        //构建MarkerOption，用于在地图上添加Marker  
        OverlayOptions option = new MarkerOptions()  
            .position(nodeLocation)  
            .icon(bitmap); 
        mBaiduMap.clear();//清除图标
        //在地图上添加Marker，并显示  
        mBaiduMap.addOverlay(option);
        //移动节点至中心
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(nodeLocation));
        
        
        //驾车按钮
       Button drive=(Button)this.findViewById(R.id.bt_drive);
        drive.setOnClickListener(
        		new  View.OnClickListener()
        		{
					@Override
					public void onClick(View v)
					{
						boolean isInitSuccess = BaiduNaviManager.getInstance().checkEngineStatus(getApplicationContext());
						if(!isInitSuccess){
							return ;
						}

						Intent intent=new Intent(MapActivity.this,RoutePlanDemo.class);
						intent.putExtra("longN",eX);
						intent.putExtra("latN", eY);
						startActivity(intent);
					}
				}
        		);

        //公交按钮
        Button bus=(Button)this.findViewById(R.id.bt_bus);
        bus.setOnClickListener(
        		new View.OnClickListener() 
        		{
					@Override
					public void onClick(View v)
					{
						
//						float longF=(float)(eX * 1e-5);
//				  		float latF=(float)(eY * 1e-5);
//						
//						//起点
//						LatLng Lstar=new LatLng(30.276734,120.169848);             
//				       
//				   	  //终点           
//				  	   LatLng Lend=new LatLng(latF,longF);
//						
//						GetBusLineChange busLineProvide = new GetBusLineChange(Lstar, Lend);
						
						
						
						Intent intent =new Intent(MapActivity.this,BusLineActivity.class);
						intent.putExtra("nameStr", nameStr);
						intent.putExtra("longN",eX);
						intent.putExtra("latN", eY);
						startActivity(intent);
					}
				}
        		);
        
        //步行按钮
        Button walk=(Button)this.findViewById(R.id.bt_walk);
        walk.setOnClickListener(
        		new View.OnClickListener() 
        		{
					@Override
					public void onClick(View v)
					{
						Intent intent =new Intent(MapActivity.this,WalkActivity.class);
						intent.putExtra("str", "walk");
						intent.putExtra("longN",eX);
						intent.putExtra("latN", eY);
						startActivity(intent);
					}
				}
        		);
        
        //弹出泡泡
    	mBaiduMap.setOnMarkerClickListener(new OnMarkerClickListener() 
    	{
    		public boolean onMarkerClick(final Marker marker)
    		{
    			Button button = new Button(getApplicationContext());
				button.setBackgroundResource(R.drawable.popup);
//				final LatLng ll = marker.getPosition();
//				Point p = mBaiduMap.getProjection().toScreenLocation(ll);
//				p.y -= 47;
				//LatLng llInfo = mBaiduMap.getProjection().fromScreenLocation(p);
				//OnInfoWindowClickListener listener = null;
    			
				button.setText(nameStr);
				button.setTextColor(Color.BLACK);
			
				mInfoWindow = new InfoWindow(button, nodeLocation, null);
				mBaiduMap.showInfoWindow(mInfoWindow);
				return true;
    		}
    	}
    	);
    	
        
	}


	@Override
	protected void onPause() {
		mMapView.onPause();
		super.onPause();
	}

	@Override
	protected void onResume() {
		mMapView.onResume();
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		mMapView.onDestroy();
		mMapView = null;
		super.onDestroy();
	}
	
	private String getSdcardDir() {
		if (Environment.getExternalStorageState().equalsIgnoreCase(
				Environment.MEDIA_MOUNTED)) {
			return Environment.getExternalStorageDirectory().toString();
		}
		return null;
	}
	private NaviEngineInitListener mNaviEngineInitListener = new NaviEngineInitListener() {
		public void engineInitSuccess() {
		}

		public void engineInitStart() {
		}

		public void engineInitFail() {
		}
	};
	
//	@Override
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		if (keyCode == KeyEvent.KEYCODE_BACK) {
//		
//			System.out.println("退出时  MapActivity"+ GetBusLineChange.mTransitRouteLine.size());
//			GetBusLineChange.mTransitRouteLine.clear();
//			System.out.println("退出时   MapActivity 处理后+++++++"+ GetBusLineChange.mTransitRouteLine.size());
//			
//		}
//		return super.onKeyDown(keyCode, event);
//	}
	

}
