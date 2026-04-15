package com.cn.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.route.TransitRouteLine;
import com.baidu.mapapi.search.route.TransitRouteLine.TransitStep;
import com.baidu.mapapi.search.route.TransitRouteLine.TransitStep.TransitRouteStepType;
import com.cn.hangzhou.R;
import com.cn.util.Constant;

public class BusLineActivity  extends Activity {

	private Handler handler = null;
	public static String endStation = null;
	public static String startStation = null;
	public static List<HashMap<String, String>> myMapArray = null;
	public static int planIndex=-1;
	
    int eX;                     //经度
    int eY;                      //纬度
	
    GetBusLineChange busLineProvide ;
   public static  BusLineActivity  ma;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		SDKInitializer.initialize(getApplicationContext());
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏

		ma=this;
		
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				switch (msg.what) {
				case Constant.INFO_MYSQL:
					break;
				case Constant.INFO_NEARBYSTATIO:
					initLineChangeData();
					break;
				}
			}
		};
	
		
    	//接受经度
		eX=this.getIntent().getIntExtra("longN",12016984);
		eY=this.getIntent().getIntExtra("latN",3027673);

		float longF=(float)(eX * 1e-5);
  		float latF=(float)(eY * 1e-5);
		
		//起点
		LatLng Lstar=new LatLng(30.276734,120.169848);             
       
   	  //终点           
  	   LatLng Lend=new LatLng(latF,longF);
		
	  busLineProvide = new GetBusLineChange(Lstar, Lend);
		
      //起点，终点名称
	  startStation="武林广场";           
	  endStation=this.getIntent().getStringExtra("nameStr");
	  
		// 创建新的线程判断 返回数据是否完成
		new Thread(new Runnable() {

			@Override
			public void run() {
				boolean bz = true;
				while (bz) {
					if (busLineProvide.isFinish) {
						// 回到 ui线程
						Message message = new Message();
					    message.what = Constant.INFO_NEARBYSTATIO;
						handler.sendMessage(message);
						bz = false;
					
					}
					try {
						Thread.sleep(1);// 每5ns 检查一次
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
		setContentView(R.layout.map_bus_line);

	}

	@SuppressWarnings("static-access")
	public void initLineChangeData() {
		
		
		myMapArray = new ArrayList<HashMap<String, String>>();
		for (int index = 0; index < busLineProvide.mTransitRouteLine.size(); index++) {
			HashMap<String, String> myMap = new HashMap<String, String>();
			TransitRouteLine line = busLineProvide.mTransitRouteLine
					.get(index);
			String str = "";
			ArrayList<TransitStep> steps = (ArrayList<TransitStep>) line
					.getAllStep();
			String bus = "";
			String station = "";
			int stepsIndex = 0;
			int change = -1;
			int allStation = 0;
		
			for (TransitStep step : steps) {
				/*
				 * System.out.println(step.getInstructions());
				 * System.out.println
				 * (step.getEntrace().getLocation().toString());
				 * System.out.println(step.getExit().getLocation().toString());
				 */
				str = str + step.getInstructions() + "->";
				String instructions = step.getInstructions().toString();
				station = station
						+ instructions.substring(
								instructions.indexOf("到达") + 2,
								instructions.length());
				if (stepsIndex != steps.size() - 1) {
					station = station + "->";
				}
				if (step.getStepType() == TransitRouteStepType.BUSLINE) {
					allStation = allStation
							+ Integer
									.parseInt(instructions.substring(
											instructions.indexOf("经过") + 2,
											(instructions
													.indexOf(",", instructions
															.indexOf("经过") + 2) - 1)));
					if (instructions.indexOf(")") != -1) {
						bus = bus
								+ instructions.substring(
										instructions.indexOf("乘坐") + 2,
										instructions.indexOf(")") + 1);
					} else {
						bus = bus
								+ instructions.substring(
										instructions.indexOf("乘坐") + 2,
										instructions.indexOf(","));

					}
					if (stepsIndex != steps.size() - 1) {
						bus = bus + "->";
					}

					change++;
				}

				if (step.getStepType() == TransitRouteStepType.WAKLING) {

				}
				stepsIndex++;
			}

			myMap.put("station", station);
			myMap.put("bus", bus);
			myMap.put("change", change + "");
			myMap.put("allStation", allStation + "");
			System.out.println(str);
			System.out.println(station);
			System.out.println(bus);
			System.out.println(change);
			System.out.println(allStation);
			myMapArray.add(myMap);
		}
		initListView();
	}
	
	


	public void initListView()
	{
		ListView lv=(ListView) this.findViewById(R.id.lvChangeFangAn);//初始化Listview
		ChangeLineAdapter cla=new ChangeLineAdapter(this, myMapArray);//新建适配器
		lv.setAdapter(cla);//设置适配器
		lv.setOnItemClickListener(
				new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						Intent intent = new Intent(BusLineActivity.this,ChangePlanActivity.class);//建立一个新的消息
						intent.putExtra("startStation",startStation);//添加内容
						intent.putExtra("endStation", endStation);//添加内容
						intent.putExtra("index",arg2);//添加内容
						intent.putExtra("nameStr", endStation);
						intent.putExtra("longN",eX);
						intent.putExtra("latN", eY);
						BusLineActivity.this.startActivity(intent);
						BusLineActivity.this.finish();//结束本界面
						
						

						
						
					}
				}
				);
		
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
		super.onDestroy();
	}

	@Override
	protected void onStop() {
		super.onStop();
		this.finish();
	}

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

