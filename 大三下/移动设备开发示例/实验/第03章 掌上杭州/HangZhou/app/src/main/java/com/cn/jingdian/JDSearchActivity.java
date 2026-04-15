package com.cn.jingdian;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ZoomControls;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.InfoWindow.OnInfoWindowClickListener;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.overlayutil.PoiOverlay;
import com.baidu.mapapi.search.core.CityInfo;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;
import com.cn.hangzhou.R;
import com.cn.util.FontManager;



public class JDSearchActivity extends FragmentActivity implements 
OnGetPoiSearchResultListener, OnGetSuggestionResultListener {
	    public MapView mMapView;//地图界面
		private PoiSearch mPoiSearch = null;
		private SuggestionSearch mSuggestionSearch = null;
		private BaiduMap mBaiduMap = null;
		Location location;//位置对象
		private InfoWindow mInfoWindow;
		/**
		 * 搜索关键字输入窗口
		 */
		private AutoCompleteTextView keyWorldsView = null;
		private ArrayAdapter<String> sugAdapter = null;
		private int load_Index = 0;
		String name;

	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        SDKInitializer.initialize(getApplicationContext()); 
	        requestWindowFeature(Window.FEATURE_NO_TITLE);   //去掉标题栏
	        setContentView(R.layout.jingdian_search);
	        //用自定义的字体方法
	        FontManager.changeFonts(FontManager.getContentView(this),this);
	     // 初始化搜索模块，注册搜索事件监听
			mPoiSearch = PoiSearch.newInstance();
			mPoiSearch.setOnGetPoiSearchResultListener(this);
			mSuggestionSearch = SuggestionSearch.newInstance();
			mSuggestionSearch.setOnGetSuggestionResultListener(this);
			keyWorldsView = (AutoCompleteTextView) findViewById(R.id.searchkey);
			sugAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_dropdown_item_1line);
			keyWorldsView.setAdapter(sugAdapter);
			 mMapView = (MapView) findViewById(R.id.bmapView);
			 mBaiduMap = mMapView.getMap();
			 mSetVisibility();
			// 地图模式设置
				Button mapType = (Button) this.findViewById(R.id.qie);
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

			/**
			 * 当输入关键字变化时，动态更新建议列表
			 */
			keyWorldsView.addTextChangedListener(new TextWatcher() {

				@Override
				public void afterTextChanged(Editable arg0) {

				}

				@Override
				public void beforeTextChanged(CharSequence arg0, int arg1,
						int arg2, int arg3) {

				}

				@Override
				public void onTextChanged(CharSequence cs, int arg1, int arg2,
						int arg3) {
					if (cs.length() <= 0) {
						return;
					}
					String city = ((EditText) findViewById(R.id.city)).getText()
							.toString();
					/**
					 * 使用建议搜索服务获取建议列表，结果在onSuggestionResult()中更新
					 */
					mSuggestionSearch
							.requestSuggestion((new SuggestionSearchOption())
									.keyword(cs.toString()).city(city));
				}
			});

		}

		@Override
		protected void onPause() {
			super.onPause();
		}

		@Override
		protected void onResume() {
			super.onResume();
		}

		@Override
		protected void onDestroy() {
			mPoiSearch.destroy();
			mSuggestionSearch.destroy();
			super.onDestroy();
		}

		@Override
		protected void onSaveInstanceState(Bundle outState) {
			super.onSaveInstanceState(outState);

		}

		@Override
		protected void onRestoreInstanceState(Bundle savedInstanceState) {
			super.onRestoreInstanceState(savedInstanceState);
		}

		/**
		 * 影响搜索按钮点击事件
		 * 
		 * @param v
		 */
		public void searchButtonProcess(View v) {
			EditText editCity = (EditText) findViewById(R.id.city);
			EditText editSearchKey = (EditText) findViewById(R.id.searchkey);
//			LocationManager locationManager;
//	    	
//	    	
//	    	locationManager=(LocationManager) this.getSystemService(Context.LOCATION_SERVICE);//获得位置管理器
////	    	try	
//// 		    {
////    		  location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER); // 通过GPS获取位置	    	
//// 		    }
////			catch(Exception e)
////    		{
////    			e.printStackTrace();
////    		}
//	    	
//	        if(null ==location){//若没有获得到位置信息，则通过网络获取
//	         	System.out.println("没有获取到gps位置，此处为网络获取");
//	         	location=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//	         }
//	        double latitude=location.getLatitude();//得到当前位置的纬度
//	    	double longitude=location.getLongitude();//得到当前位置的经度
//	        LatLng latlng=new LatLng(Math.round(latitude*10000)/10000.0,
//	        		Math.round(longitude*10000)/10000.0 ); 
//			mPoiSearch.searchNearby((new PoiNearbySearchOption())
//					.keyword(editSearchKey.getText().toString())
//					.radius(Integer.parseInt(editCity.getText().toString()))
//					.pageNum(load_Index)
//					.location(latlng)
//					);
			mPoiSearch.searchInCity((new PoiCitySearchOption())
					.city(editCity.getText().toString())
					.keyword(editSearchKey.getText().toString())
					.pageNum(load_Index));
		}

		public void goToNextPage(View v) {
			load_Index++;
			searchButtonProcess(null);
		}

		@Override
		public void onGetPoiResult(PoiResult result) {
			if (result == null
					|| result.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
				return;
			}
			if (result.error == SearchResult.ERRORNO.NO_ERROR) {
				mBaiduMap.clear();
				PoiOverlay overlay = new MyPoiOverlay(mBaiduMap);
				mBaiduMap.setOnMarkerClickListener(overlay);
				overlay.setData(result);
				overlay.addToMap();
				overlay.zoomToSpan();
				return;
			}
			if (result.error == SearchResult.ERRORNO.AMBIGUOUS_KEYWORD) {

				// 当输入关键字在本市没有找到，但在其他城市找到时，返回包含该关键字信息的城市列表
				String strInfo = "在";
				for (CityInfo cityInfo : result.getSuggestCityList()) {
					strInfo += cityInfo.city;
					strInfo += ",";
				}
				strInfo += "找到结果";
				Toast.makeText(JDSearchActivity.this, strInfo, Toast.LENGTH_LONG)
						.show();
			}
		}

		@Override
		public void onGetPoiDetailResult(PoiDetailResult result) {
			if (result.error != SearchResult.ERRORNO.NO_ERROR) {
				Toast.makeText(JDSearchActivity.this, "抱歉，未找到结果", Toast.LENGTH_SHORT)
						.show();
			} else {
//				name=result.getName();//名字
//				result.getAddress();//地址
//				result.getTelephone();//电话
//				result.getLocation();//经纬度
//				Toast.makeText(JDSearchActivity.this, 
//						"成功，查看"+"名字："+result.getName()+
//						"\n地址："+result.getAddress()+
//						"\n电话："+result.getTelephone()+
//						"\n经纬度："+result.getLocation(),
//						Toast.LENGTH_SHORT)
//						.show();
				//拿到一个LayoutInflater
				LayoutInflater factory=LayoutInflater.from(JDSearchActivity.this);
				View view=(View)factory.inflate(R.layout.jingdian_detail, null);
				TextView tx1=(TextView)view.findViewById(R.id.searchtx1);
				tx1.setText("名字：");
				TextView tv1=(TextView)view.findViewById(R.id.searchtv1);
				tv1.setText(result.getName());
				TextView tx2=(TextView)view.findViewById(R.id.searchtx2);
				tx2.setText("电话：");
				TextView tv2=(TextView)view.findViewById(R.id.searchtv2);
				tv2.setText(result.getTelephone());
				TextView tx3=(TextView)view.findViewById(R.id.searchtx3);
				tx3.setText("地址：");
				TextView tv3=(TextView)view.findViewById(R.id.searchtv3);
				tv3.setText(result.getAddress());
				LatLng llup=new LatLng(result.getLocation().latitude+0.002,result.getLocation().longitude);
				OnInfoWindowClickListener listener = null;
				listener = new OnInfoWindowClickListener() 
				{
					public void onInfoWindowClick() 
					{
						mBaiduMap.hideInfoWindow();
					}
			    };
			    mInfoWindow = new InfoWindow(view, llup, listener);
				mBaiduMap.showInfoWindow(mInfoWindow);		
			}
			
		}
		@Override
		public void onGetSuggestionResult(SuggestionResult res) {
			if (res == null || res.getAllSuggestions() == null) {
				return;
			}
			sugAdapter.clear();
			for (SuggestionResult.SuggestionInfo info : res.getAllSuggestions()) {
				if (info.key != null)
					sugAdapter.add(info.key);
			}
			sugAdapter.notifyDataSetChanged();
		}

		private class MyPoiOverlay extends PoiOverlay {

			public MyPoiOverlay(BaiduMap baiduMap) {
				super(baiduMap);
			}

			@Override
			public boolean onPoiClick(int index) {
				super.onPoiClick(index);
				PoiInfo poi = getPoiResult().getAllPoi().get(index);
//				if (poi.hasCaterDetails) {
					mPoiSearch.searchPoiDetail((new PoiDetailSearchOption())
							.poiUid(poi.uid));
//				}
				return true;
			}
		}
		//隐藏 缩放按钮
		public void mSetVisibility() {
			int childCount = mMapView.getChildCount();
			View zoom = null;
			for (int i = 0; i < childCount; i++) {
				View child = mMapView.getChildAt(i);
				if (child instanceof ZoomControls) {
					zoom = child;
					break;
				}
			}
			zoom.setVisibility(View.GONE);
		}
	}
