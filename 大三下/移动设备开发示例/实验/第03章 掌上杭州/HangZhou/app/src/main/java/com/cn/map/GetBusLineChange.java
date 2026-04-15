package com.cn.map;

import java.util.ArrayList;
import java.util.List;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteLine;
import com.baidu.mapapi.search.route.TransitRoutePlanOption;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.baidu.mapapi.search.route.TransitRoutePlanOption.TransitPolicy;

public class GetBusLineChange implements OnGetRoutePlanResultListener {
	private RoutePlanSearch mSearch = null;// 路径规划搜索接口
	private PlanNode lineStart = null; // 起点节点
	private PlanNode lineEnd = null; // 终点节点
	//private Context mContext = null; // 上下文
	public  static List<TransitRouteLine> mTransitRouteLine = null;
	public  boolean isFinish=false;
//	public GetBusLineChange(Context context, String lineStart, String lineEnd) {
//		this.lineStart = PlanNode.withCityNameAndPlaceName("唐山",
//				lineStart);// 城市加名字 建立开始节点
//		this.lineEnd = PlanNode.withCityNameAndPlaceName("唐山",
//				lineEnd);// 城市加名字 建立终点节点
//		this.mContext = context;
//		mSearch = RoutePlanSearch.newInstance();// 接口
//		mSearch.setOnGetRoutePlanResultListener(this);// 设置接口监听
//		searchBusLine();
//	}

	public GetBusLineChange( LatLng lineStart, LatLng lineEnd) {
		this.lineStart = PlanNode.withLocation(lineStart);// 坐标方法 建立开始节点
		this.lineEnd = PlanNode.withLocation(lineEnd); // 坐标方法建立结束节点
		//this.mContext = context;
		mSearch = RoutePlanSearch.newInstance();// 接口
		mSearch.setOnGetRoutePlanResultListener(this);// 设置接口监听
		searchBusLine();
	}

	public void searchBusLine() {
		mTransitRouteLine = new ArrayList<TransitRouteLine>();
		TransitRoutePlanOption myTRP = new TransitRoutePlanOption();// 换乘路线规划参数
		myTRP.policy(TransitPolicy.EBUS_NO_SUBWAY); // 不含地铁
		System.out.println("发起");
		mSearch.transitSearch((myTRP).from(lineStart).city("杭州")
				.to(lineEnd));// 发起查询
	
	}

	@Override
	public void onGetDrivingRouteResult(DrivingRouteResult arg0) {

	}
	
	@Override
	public void onGetWalkingRouteResult(WalkingRouteResult arg0) {
	}
	
	@Override
	public void onGetTransitRouteResult(TransitRouteResult result) {
		
		System.out.println("**********************");
		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
			
			//Toast.makeText(mContext, "抱歉，未找到结果", Toast.LENGTH_SHORT).show();
		}
		if (result.error == SearchResult.ERRORNO.NO_ERROR) {
			mTransitRouteLine=result.getRouteLines();
		}
		isFinish=true;
	}
}
