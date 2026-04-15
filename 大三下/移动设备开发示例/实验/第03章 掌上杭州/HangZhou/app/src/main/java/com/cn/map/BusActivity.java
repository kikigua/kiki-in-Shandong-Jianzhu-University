package com.cn.map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ZoomControls;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.overlayutil.OverlayManager;
import com.baidu.mapapi.overlayutil.TransitRouteOverlay;
import com.baidu.mapapi.overlayutil.WalkingRouteOverlay;
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.route.DrivingRouteLine;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteLine;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteLine;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.cn.hangzhou.R;

/**
 * 此demo用来展示如何进行驾车、步行、公交路线搜索并在地图使用RouteOverlay、TransitOverlay绘制
 * 同时展示如何进行节点浏览并弹出泡泡
 */
public class BusActivity extends Activity implements BaiduMap.OnMapClickListener,
        OnGetRoutePlanResultListener {
    //浏览路线节点相关
    Button mBtnPre = null;//上一个节点
    Button mBtnNext = null;//下一个节点
    int nodeIndex = -2;//节点索引,供浏览节点时使用
    @SuppressWarnings("rawtypes")
	RouteLine route = null;
    OverlayManager routeOverlay = null;
    boolean useDefaultIcon = false;
    private TextView popupText = null;//泡泡view
    private View viewCache = null;

    //地图相关，使用继承MapView的MyRouteMapView目的是重写touch事件实现泡泡处理
    //如果不处理touch事件，则无需继承，直接使用MapView即可
    MapView mMapView = null;    // 地图View
    BaiduMap mBaidumap = null;
    //搜索相关
    RoutePlanSearch mSearch = null;    // 搜索模块，也可去掉地图模块独立使用
    
    String str;
    int eX;                     //经度
    int eY;                      //纬度

    int planNum;
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext()); 
        setContentView(R.layout.map_two);
        CharSequence titleLable = "路线规划功能";
        setTitle(titleLable);
        //初始化地图
        mMapView = (MapView) findViewById(R.id.map);
        mBaidumap = mMapView.getMap();
        //地图的缩放比
		MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(16.0f);
		mBaidumap.setMapStatus(msu);
        
        mBtnPre = (Button) findViewById(R.id.pre);
        mBtnNext = (Button) findViewById(R.id.next);
        mBtnPre.setVisibility(View.INVISIBLE);
        mBtnNext.setVisibility(View.INVISIBLE);
        //地图点击事件处理
        mBaidumap.setOnMapClickListener(this);
        // 初始化搜索模块，注册事件监听
        mSearch = RoutePlanSearch.newInstance();
        mSearch.setOnGetRoutePlanResultListener(this);
    	mSetVisibility();    //隐藏缩放按钮
    	//设置缩放比
//    	mBaidumap.setMapStatus(MapStatusUpdateFactory.zoomTo(18));

    	//接受路线编号
    	planNum =this.getIntent().getExtras().getInt("index");
    	
//    	//接受经度
//		eX=this.getIntent().getIntExtra("longN",12016984);
//		eY=this.getIntent().getIntExtra("latN",3027673);
//		str=this.getIntent().getStringExtra("str");
//		
//		float longF=(float)(eX * 1e-5);
//  		float latF=(float)(eY * 1e-5);
//  		//起点
//  	   LatLng ll=new LatLng(30.276734,120.169848);
//       PlanNode stNode=PlanNode.withLocation(ll);
//       //终点
//       LatLng kk=new LatLng(latF,longF);           
//       PlanNode enNode=PlanNode.withLocation(kk);
    	
    	
    //	BusLineActivity busAc=new BusLineActivity();
       
       route = GetBusLineChange.mTransitRouteLine.get(planNum);;
       mBtnPre.setVisibility(View.INVISIBLE);
       mBtnNext.setVisibility(View.INVISIBLE);
       mBaidumap.clear();
       
      
       
       nodeIndex = -1;
       mBtnPre.setVisibility(View.VISIBLE);
       mBtnNext.setVisibility(View.VISIBLE);
       TransitRouteOverlay overlay = new MyTransitRouteOverlay(mBaidumap);
       mBaidumap.setOnMarkerClickListener(overlay);
       routeOverlay = overlay;
       overlay.setData(GetBusLineChange.mTransitRouteLine.get(planNum));
       overlay.addToMap();
       overlay.zoomToSpan();
       
    }

    /**
     * 发起路线规划搜索示例
     *
     * @param v
     */
//    public void SearchButtonProcess(View v) {
//        //重置浏览节点的路线数据
//        route = null;
//        mBtnPre.setVisibility(View.INVISIBLE);
//        mBtnNext.setVisibility(View.INVISIBLE);
//        mBaidumap.clear();
//        
//        LatLng ll=new LatLng(30.276734,120.169848);
//        PlanNode stNode=PlanNode.withLocation(ll);
//        
//        LatLng kk=new LatLng(30.24922,120.17320);           
//        PlanNode enNode=PlanNode.withLocation(kk);
//
//        // 实际使用中请对起点终点城市进行正确的设定
//     if (v.getId() == R.id.transit) {
//            mSearch.transitSearch((new TransitRoutePlanOption())
//                    .from(stNode)
//                    .city("杭州")
//                    .to(enNode));
//        } else if (v.getId() == R.id.walk) {
//            mSearch.walkingSearch((new WalkingRoutePlanOption())
//                    .from(stNode)
//                    .to(enNode));
//        }
//    }

    /**
     * 节点浏览示例
     *
     * @param v
     */
    public void nodeClick(View v) {
        if (nodeIndex < -1 || route == null ||
                route.getAllStep() == null
                || nodeIndex > route.getAllStep().size()) {
            return;
        }
        //设置节点索引
        if (v.getId() == R.id.next && nodeIndex < route.getAllStep().size() - 1) {
            nodeIndex++;
        } else if (v.getId() == R.id.pre && nodeIndex > 1) {
            nodeIndex--;
        }
        if (nodeIndex < 0 || nodeIndex >= route.getAllStep().size()) {
            return;
        }

        //获取节结果信息
        LatLng nodeLocation = null;
        String nodeTitle = null;
        Object step = route.getAllStep().get(nodeIndex);
        if (step instanceof DrivingRouteLine.DrivingStep) {
            nodeLocation = ((DrivingRouteLine.DrivingStep) step).getEntrace().getLocation();
            nodeTitle = ((DrivingRouteLine.DrivingStep) step).getInstructions();
        } else if (step instanceof WalkingRouteLine.WalkingStep) {
            nodeLocation = ((WalkingRouteLine.WalkingStep) step).getEntrace().getLocation();
            nodeTitle = ((WalkingRouteLine.WalkingStep) step).getInstructions();
        } else if (step instanceof TransitRouteLine.TransitStep) {
            nodeLocation = ((TransitRouteLine.TransitStep) step).getEntrace().getLocation();
            nodeTitle = ((TransitRouteLine.TransitStep) step).getInstructions();
        }

        if (nodeLocation == null || nodeTitle == null) {
            return;
        }
        //移动节点至中心
        mBaidumap.setMapStatus(MapStatusUpdateFactory.newLatLng(nodeLocation));
        // show popup
        viewCache = getLayoutInflater().inflate(R.layout.custom_text_view, null);
        popupText = (TextView) viewCache.findViewById(R.id.textcache);
        popupText.setBackgroundResource(R.drawable.popup);
        popupText.setText(nodeTitle);
        mBaidumap.showInfoWindow(new InfoWindow(popupText, nodeLocation, null));

    }

    /**
     * 切换路线图标，刷新地图使其生效
     * 注意： 起终点图标使用中心对齐.
     */



    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onGetWalkingRouteResult(WalkingRouteResult result) {

    }

    @Override
    public void onGetTransitRouteResult(TransitRouteResult result) {

    }

    @Override
    public void onGetDrivingRouteResult(DrivingRouteResult result) {

    }



    @SuppressWarnings("unused")
	private class MyWalkingRouteOverlay extends WalkingRouteOverlay {

        public MyWalkingRouteOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public BitmapDescriptor getStartMarker() {
            if (useDefaultIcon) {
                return BitmapDescriptorFactory.fromResource(R.drawable.icon_st);
            }
            return null;
        }

        @Override
        public BitmapDescriptor getTerminalMarker() {
            if (useDefaultIcon) {
                return BitmapDescriptorFactory.fromResource(R.drawable.icon_en);
            }
            return null;
        }
    }

    private class MyTransitRouteOverlay extends TransitRouteOverlay {

        public MyTransitRouteOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public BitmapDescriptor getStartMarker() {
            if (useDefaultIcon) {
                return BitmapDescriptorFactory.fromResource(R.drawable.icon_st);
            }
            return null;
        }

        @Override
        public BitmapDescriptor getTerminalMarker() {
            if (useDefaultIcon) {
                return BitmapDescriptorFactory.fromResource(R.drawable.icon_en);
            }
            return null;
        }
    }

    @Override
    public void onMapClick(LatLng point) {
        mBaidumap.hideInfoWindow();
    }

    @Override
    public boolean onMapPoiClick(MapPoi poi) {
    	return false;
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
        mSearch.destroy();
        mMapView.onDestroy();
        super.onDestroy();
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

