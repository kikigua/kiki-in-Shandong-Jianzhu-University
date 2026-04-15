package com.cn.jingdian;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ZoomControls;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.InfoWindow.OnInfoWindowClickListener;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.model.LatLng;
import com.cn.hangzhou.R;
import com.cn.util.BitmapIOUtil;
import com.cn.util.Constant;
import com.cn.util.FontManager;
import com.cn.util.PubMethod;

public class JDMainAvtivity extends Activity {
	

 	
	public MapView mMapView;//地图界面
	BaiduMap mBaiduMap;//百度地图
	Marker[] mMarker;
	BitmapDescriptor bitmap;
//	BitmapDescriptor bitmaps;//添加在地图上的气球
	LocationManager lm;//位置管理器
	LocationListener ll;//位置变化监听器
 	private InfoWindow mInfoWindow;
 	public static String[] name;//景点名字
	public static String[] tpname;//景点的图片的名字
	public static int count;//数组长度
	public static double[] vdata;
	public static double[] jdata;
	String tou;
	String[] ftou;
	String sname;
	String szzy=Constant.snzy;
	int x=0;
	public String nearlyname=null;//存放最近且在范围内的景点的名字
	public String nearlyhm=null;
	PubMethod pub=new PubMethod(this);
	public void onCreate(Bundle savedInstanceState) 
	{
		 super.onCreate(savedInstanceState); 	     
	     SDKInitializer.initialize(getApplicationContext()); 
	     requestWindowFeature(Window.FEATURE_NO_TITLE);   //去掉标题栏
	     setContentView(R.layout.jingdian_main); 
	     //用自定义的字体方法
	        FontManager.changeFonts(FontManager.getContentView(this),this);
	     Constant.List=PubMethod.loadFromFile("jingdian/"+szzy+"/hname.txt");
		 Constant.ListArray=Constant.List.split("\\|");
			//获取数组长度
		 final int count=Constant.ListArray.length/4;//11
		 name=new String[count];//长度11
	     tpname=new String[count];//长度11
	     jdata=new double[count];
	     vdata=new double[count];
	     ftou=new String[count];
	     tou=PubMethod.loadFromFile("jingdian/"+szzy+"/tou.txt");
	     ftou=tou.split("\\|");
		 for(int i=0;i<count;i++)
	     {
	    	name[i]=Constant.ListArray[4*i];//名字(汉字)
	    	vdata[i]=Double.valueOf(Constant.ListArray[4*i+1]);//景点的纬度
	    	jdata[i]=Double.valueOf(Constant.ListArray[4*i+2]);//景点的经度
	    	tpname[i]=Constant.ListArray[4*i+3];//图片的名字
	    	
	     }
	     mMapView = (MapView) findViewById(R.id.bmapView);
		 mBaiduMap = mMapView.getMap();
		 mSetVisibility();
	     //普通地图  
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        mBaiduMap.setTrafficEnabled(true);
        

        LatLng nodeLocation=new LatLng(30.25046,120.15315);//杭州市120.15315, 30.25046
        //移动节点至中心
         mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(nodeLocation));
         float mZoomLevel = 13.0f;// 初始化地图 zoom值
 		 mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomTo(mZoomLevel));
 		 if(isGPSOpen())
 		 {
 			//若GPS已经打开则进入主界面
  			initGPSListener();
 		 }
 		 else
 		 {//若GPS未打开则进入设置界面	
 			 System.out.println("正在设置GPs=========");
		 	 gotoGPSSetting();
	 	 }
 		
	
        for(int i=0;i<count;i++)
        {
	        LatLng llup=new LatLng(vdata[i], jdata[i]);
	        BitmapDescriptor bitmaps = BitmapDescriptorFactory.fromResource(R.drawable.ballon); 
	        //LatLng llup=new LatLng(30.25750, 120.17051);  
			//在地图上添加该文字对象并显示  
			mMapView.getMap().addOverlay(new MarkerOptions()
			.position(llup)
			.title(tpname[i])//景点编号
			.icon(bitmaps));
        }
		OnMarkerClickListener listener = new OnMarkerClickListener() 
    	{
			@Override
			public boolean onMarkerClick(Marker arg0) 
			{	
				//经纬度
				//LatLng ll=arg0.getPosition();
				String ss=arg0.getTitle();
				System.out.println(ss); 
				for(int j=0;j<count;j++)
				{
					if(ss.equals(tpname[j]))
					{							
						x=j;							
					}						
				}
				sname=name[x];
				//拿到一个LayoutInflater
				LayoutInflater factory=LayoutInflater.from(JDMainAvtivity.this);
				View view=(View)factory.inflate(R.layout.jingdian_pop, null);
				//从自定义页面中拿到控件引用并赋值
				ImageView iv=(ImageView)view.findViewById(R.id.pictureiv);
					
				iv.setImageBitmap(BitmapIOUtil.getSBitmap("jingdian/pic/"+tpname[x]+"1.jpg"));//设置图片资源
				iv.setScaleType(ImageView.ScaleType.FIT_XY);
				TextView showTitle=(TextView)view.findViewById(R.id.jingdian_name);
				showTitle.setText(name[x]);//设置景点名称"@+id/snippet"
				TextView Title=(TextView)view.findViewById(R.id.snippet);
				Title.setText(ftou[x]);//设置景点名称"@+id/snippet"
			 	OnInfoWindowClickListener listener = null;
				
				LatLng llup=new LatLng(vdata[x]+0.002,jdata[x]);
				if(ss.equals(tpname[x]))
				{	
					listener = new OnInfoWindowClickListener() 
					{
						public void onInfoWindowClick() 
						{
							Intent intent=new Intent(JDMainAvtivity.this,JDNewActivity.class);
				    		intent.putExtra("nearlyname", tpname[x]);
				    		intent.putExtra("nearlyhm", sname);
				    		startActivity(intent);
						}
				    };						
				}
				mInfoWindow = new InfoWindow(view, llup, listener);
				mBaiduMap.showInfoWindow(mInfoWindow);					
				return false; 
			} 
    	};
    	
    	mMapView.getMap().setOnMarkerClickListener(listener);
	if(Constant.myLocation!=null){
       		
        	addTour(Constant.myLocation);
        }
    	//获得当前景点按钮的引用
        LinearLayout curbt=(LinearLayout)findViewById(R.id.curbutton);
        curbt.setOnClickListener(
        		new OnClickListener(){
					@Override
					public void onClick(View v) {
						if(Constant.curScenicId!=null){
				    		Intent intent=new Intent(JDMainAvtivity.this,JDNewActivity.class);
				    		intent.putExtra("nearlyname", Constant.curScenicId);
				    		intent.putExtra("nearlyhm", nearlyhm);
				    		startActivity(intent);
						}else{
							Toast.makeText(JDMainAvtivity.this, getResources().getString(R.string.sorrynocur), Toast.LENGTH_SHORT).show();
						}
					} 			
        		}
        		);
        //获得所有景点按钮的引用
        LinearLayout allbt=(LinearLayout)findViewById(R.id.allbutton);
        allbt.setOnClickListener(
        		new OnClickListener(){
					@Override
					public void onClick(View v) {
						Intent intent=new Intent(JDMainAvtivity.this,JDAllActivity.class);
//							intent.putExtra("filePath", filePath);
			    		startActivity(intent);
					} 			
        		}
        		);

	    //获得锁定位置按钮的引用
        LinearLayout lock=(LinearLayout)findViewById(R.id.suoding);
	    lock.setOnClickListener(
	    		new OnClickListener(){
					@Override
					public void onClick(View v) {
						if(Constant.myLocation!=null)
						{
							System.out.println("===========锁定位置========");
							LatLng latlng = new LatLng
							        (
							        		Math.round(Constant.myLocation.getLatitude()*10000)/10000.0,
						    				Math.round(Constant.myLocation.getLongitude()*10000)/10000.0 
							        );		
							addTour(Constant.myLocation);
							 //移动节点至中心
					         mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(latlng));
						}
						else
						{
							System.out.println("===========锁定失败========");
						  Toast.makeText(JDMainAvtivity.this,
							getResources().getString(R.string.GPSFailed),Toast.LENGTH_SHORT).show();
						}
					} 			
	    		}
	    		);
//		      获得拍照按钮的引用
	    LinearLayout camerabt=(LinearLayout)findViewById(R.id.camera);
	        camerabt.setOnClickListener(
	        		new OnClickListener(){
						@Override
						public void onClick(View v) {
							Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); 
			                startActivityForResult(intent, Constant.PHOTOHRAPH);
						} 			
	        		}
	        		);
	      //获得更多按钮的引用
	        LinearLayout morebt=(LinearLayout)findViewById(R.id.more);
	        morebt.setOnClickListener(
	        		new OnClickListener(){
						@SuppressWarnings("deprecation")
						@Override
						public void onClick(View v) {
							showDialog(Constant.SHOWMOREDIALOG);//显示更多功能对话框
						} 			
	        		}
	        		);		      
//		      //获得意见退出按钮的引用
	        LinearLayout exit=(LinearLayout)findViewById(R.id.exit);
	        exit.setOnClickListener(
	        		new OnClickListener(){
						@SuppressWarnings("deprecation")
						@Override
						public void onClick(View v) {
						  showDialog(Constant.EXIT_DIALOG);
						} 			
	        		}
	        		); 
	    	// 地图模式设置
			ImageButton mapType = (ImageButton) this.findViewById(R.id.ib);
			mapType.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (mBaiduMap.getMapType() == BaiduMap.MAP_TYPE_NORMAL) {
						mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);//卫星模式
					} else {
						mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);//普通模式

					}
				}
			});
     
	}
	
    protected void updateAndJudgement(Location location, BaiduMap mBaiduMap) 
    {
    	double latitude=location.getLatitude();//得到当前位置的纬度
    	double longitude=location.getLongitude();//得到当前位置的经度
    	double dis;
    	System.out.println("updateAndJudgement:"+location);
    	Constant.myLocation=location; //改变存储的游客位置
    	if(Constant.myLocation==null){
    		addTour(location);//添加游客图层
    	}else{
        	dis=Constant.jWD2M(latitude, longitude, Constant.myLocation.getLatitude(), Constant.myLocation.getLongitude());//计算与之前位置的距离
        	System.out.println("人移动距离"+dis);
        	if(dis>Constant.DISTANCE_USER){	
        		addTour(location);//改变游客图层
        	}
    	}
 //   	mBaidu.animateCamera(CameraUpdateFactory.zoomIn());//更新地图
    	//再判断是否到一个景点范围内
    	double nearlyLong=200E6;//创建变量并赋初值
    	//int nearlyID=0;//存放距离最近且到达范围的景点id
    	
    	for(int i=0;i<count;i++)
    	{  
    		//依次遍历景点
    		LatLng latlng=new LatLng(vdata[i], jdata[i]);
    		dis=Constant.jWD2M(latitude, longitude, (latlng.latitude*10000)/10000.0, (latlng.longitude*10000)/10000.0);
    		System.out.println("距景点距离"+dis);
    		if(dis<Constant.DISTANCE_SCENIC && dis<nearlyLong)
    		{//找到距离最近的景点
    			nearlyLong=dis;//将此景点距游客的距离值赋值给nearlyLong记录
    			//nearlyID=i;//将此景点编号赋值给nearlyID变量记录
    			nearlyname=tpname[i];
    			nearlyhm=name[i];
    		}
    	}
    	if(!nearlyname.equals(Constant.curScenicId)&&nearlyname!=null&&nearlyhm!=null)
    	{//若可以找到一个在其范围内又距离最近的景点
    		Intent intent=new Intent(JDMainAvtivity.this,JDNewActivity.class);
    		//intent.putExtra("nearlyID", nearlyID);//添加所进入景点编号的附加信息
     		intent.putExtra("nearlyname", nearlyname);//添加所进入景点名字的附加信息
     		intent.putExtra("nearlyhm", nearlyhm);//添加所进入景点汉语名字的附加信息
    		startActivity(intent);//开启景点介绍界面
    	}
		
	}
  //将之前的游客位置的层从图层列表中移除，添加新的位置的游客层
    public void addTour(Location location){//添加游客类层
    	LatLng latlng=new LatLng(Math.round(location.getLatitude()*10000)/10000.0,
				Math.round(location.getLongitude()*10000)/10000.0); 	   
		//mBaiduMap.clear();
		//showScenics();
		BitmapDescriptor bitmaps = BitmapDescriptorFactory.fromResource(R.drawable.ballon); 
		mMapView.getMap().addOverlay(new MarkerOptions()
		.position(latlng)
		.icon(bitmaps));
    	 mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(latlng));//移动节点至中心
    }	
	//初始化GPS
	private void initGPSListener() 
    {
		final LocationManager locationManager=(LocationManager)
    			this.getSystemService(Context.LOCATION_SERVICE);//获取位置管理器实例      
        LocationListener ll=new LocationListener()
        {//位置变化监听器
			@Override	//当位置变化时触发
			public void onLocationChanged(Location location)
			{
				if(location!=null)
				{
		    		try
		    		{
		    		
						updateAndJudgement(location,mBaiduMap);
						Constant.myLocation=location; //改变存储的游客位置
						
		    		}
		    		catch(Exception e)
		    		{
		    			e.printStackTrace();
		    		}
		    	}
			}
			@Override//Location Provider被禁用时更新
			public void onProviderDisabled(String provider){}
			@Override//Location Provider被启用时更新
			public void onProviderEnabled(String provider){}
			@Override//当Provider硬件状态变化时更新
			public void onStatusChanged(String provider, int status,Bundle extras){}       	
        };
     // 注册 位置改变的监听器 
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,0,ll); //添加位置变化监听器
    }
    	//判断GPS是否 	
		public boolean isGPSOpen()
	    {
	    	LocationManager alm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);  //获得位置管理对象 
	        if (!alm.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)) //如果GPS没开 
	        {
	        	return false;//返回false
	        }
	        else return true;//返回true
	    }
		//跳到GPS设置界面
	    public void gotoGPSSetting()
	    {
	   	 	 Intent intent = new Intent();  //创建Intent对象 
	         intent.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);//设置Intent的Action    
	         intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);   //设置Intent的flags
	         try
	         {   
	             startActivity(intent);//跳转到GPS设置界面方法   	                
	         }
	         catch(Exception e)
	         {
	        	 e.printStackTrace();
	         }
	    } 
		
	    @Override
		protected Dialog onCreateDialog(int id) {
			Dialog dialog=null;
			AlertDialog.Builder builder = new AlertDialog.Builder(this); 
			switch(id){
			case Constant.SHOWMOREDIALOG:
				dialog=new MoreDialog(this);//创建“更多”对话框
				break;
			case Constant.EXIT_DIALOG:
				builder = new AlertDialog.Builder(this); 
				builder.setMessage(getResources().getString(R.string.exitdialog)) 
				       .setCancelable(false) 
				       .setPositiveButton(
				    		   getResources().getString(R.string.yes),
				    		   new DialogInterface.OnClickListener() { 
				           			public void onClick(DialogInterface dialog, int id)
				           			{ 
				           				JDMainAvtivity.this.finish();//关闭主界面
				           			} 
				       			}
				       ) 
				       .setNegativeButton(
				    		   getResources().getString(R.string.no),
				    		   new DialogInterface.OnClickListener() { 
				    			   public void onClick(DialogInterface dialog, int id)
				    			   { 
				    				   dialog.cancel(); 
				    			   } 
				    		   }
				); 
				dialog = builder.create();//创建对话框
				break;
			}
			return dialog;
		}
	   
//	    @Override
//		public boolean onKeyDown(int keyCode, KeyEvent event) {
//	    	System.out.println("景点中的----->您摁下的是返回键！");
//			switch(keyCode){
//			case 4://所示返回键
//				
//				showDialog(Constant.EXIT_DIALOG);//显示退出询问对话框
//			}
//			return super.onKeyDown(keyCode, event);
//		}   	
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
	
	//隐藏 缩放按钮
	public void mSetVisibility() {
		int childCount = mMapView.getChildCount();
		View zoom = null;
		for (int i = 0; i < childCount; i++)
		{
			View child = mMapView.getChildAt(i);
			if (child instanceof ZoomControls) 
			{
				zoom = child;
				break;
			}
		}
		zoom.setVisibility(View.GONE);
	}
	
	
}
