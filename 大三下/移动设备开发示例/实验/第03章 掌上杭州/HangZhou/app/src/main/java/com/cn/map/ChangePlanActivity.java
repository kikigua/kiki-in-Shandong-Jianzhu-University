package com.cn.map;

import java.util.ArrayList;

import com.baidu.lbsapi.auth.LBSAuthManagerListener;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.search.route.TransitRouteLine;
import com.baidu.mapapi.search.route.TransitRouteLine.TransitStep;
import com.baidu.mapapi.search.route.TransitRouteLine.TransitStep.TransitRouteStepType;
import com.baidu.navisdk.BaiduNaviManager;
import com.baidu.navisdk.BNaviEngineManager.NaviEngineInitListener;
import com.cn.hangzhou.R;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ChangePlanActivity extends Activity {
	private LayoutInflater inflater = null;// 导入
	private Bundle extras = null;
	private String startStation = null;// 起点
	private String endStation = null;// 终点
	private int planNum = -1;
	LinearLayout ll;
	RelativeLayout rlLayout;
	ArrayList<TransitStep> stepsWalk = null;

	  int eX;                     //经度
	 int eY;                      //纬度
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		SDKInitializer.initialize(this.getApplication());

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
						Toast.makeText(ChangePlanActivity.this, str,
								Toast.LENGTH_LONG).show();
					}
				});

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_change_plan);
		// 初始化导航引擎

		stepsWalk = new ArrayList<TransitStep>();

		inflater = LayoutInflater.from(this);
		ll = (LinearLayout) this.findViewById(R.id.llChangePlan);
		extras = getIntent().getExtras();
		startStation = extras.getString("startStation");
		endStation = extras.getString("endStation");
		planNum = extras.getInt("index");

    	//接受经度
		eX=this.getIntent().getIntExtra("longN",12016984);
		eY=this.getIntent().getIntExtra("latN",3027673);
		
		rlLayout = (RelativeLayout) this.findViewById(R.id.rlLayout);

		TextView tv = (TextView) this.findViewById(R.id.tvTitle);
		tv.setText("解决方案" + (planNum + 1));
		TextView tvPlanTitle = (TextView) this.findViewById(R.id.tvPlanTitle);
		tvPlanTitle.setText(startStation + "->" + endStation);
		tvPlanTitle.setTextColor(Color.BLACK);
		initData();

	}

	public void initData() {

		ll.addView(getStartEndView(startStation));
		TransitRouteLine line = GetBusLineChange.mTransitRouteLine.get(planNum);                         //重点
		ArrayList<TransitStep> steps = (ArrayList<TransitStep>) line
				.getAllStep();
		int stepNum = 0;
		int walkIndex = 0;
		for (TransitStep step : steps) {
			String instructions = step.getInstructions().toString();

			if (step.getStepType() == TransitRouteStepType.BUSLINE) {
				if (instructions.indexOf(")") != -1) {
					ll.addView(getBusView(instructions.substring(
							instructions.indexOf("乘坐") + 2,
							instructions.indexOf(")") + 1)));
				} else {
					ll.addView(getBusView(instructions.substring(
							instructions.indexOf("乘坐") + 2,
							instructions.indexOf(","))));

				}
			}
			if (step.getStepType() == TransitRouteStepType.WAKLING) {
				String station = instructions.substring(
						instructions.indexOf("到达") + 2, instructions.length());//获得该节点终点
				ll.addView(getWalkView(
						instructions.substring(0, instructions.indexOf(",")),
						walkIndex,station));//建立walkview
				walkIndex++;//walkview节点数目加1
				stepsWalk.add(step);//存储该节点 用于导航
			}
			ll.addView(getStartEndView(instructions.substring(
					instructions.indexOf("到达") + 2, instructions.length())));
			stepNum++;
		}
		LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) rlLayout
				.getLayoutParams();
		// 取控件rlLayout当前的布局参数
		linearParams.height = (stepNum * 2 + 1) * 80 + 100; // 当控件的高强制设成象素
		rlLayout.setLayoutParams(linearParams); // 使设置好的布局参数应用到控件rlLayout
	}

	public View getStartEndView(String str) {
		View v = inflater.inflate(R.layout.view_text_w, null);// 建立起点textview
		TextView tvStart = (TextView) v.findViewById(R.id.tvStartEnd);
		tvStart.setText(str);
		return v;
	}

	public View getWalkView(String str, final int index,final String stationName) {
		View v = inflater.inflate(R.layout.view_wark, null);// 建立起点textview
		TextView tvStart = (TextView) v.findViewById(R.id.tvWalk);
		tvStart.setText(str);

		return v;
	}

	public View getBusView(String str) {
		View v = inflater.inflate(R.layout.view_bus, null);// 建立起点textview
		TextView tvStart = (TextView) v.findViewById(R.id.tvbus);
		tvStart.setText(str);
		return v;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onBackPressed() {
		System.exit(0);
		android.os.Process.killProcess(android.os.Process.myUid());
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
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
	protected void onStop() {
		super.onStop();
		this.finish();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent=new Intent(ChangePlanActivity.this,BusLineActivity.class);
			
			intent.putExtra("nameStr", endStation);
			intent.putExtra("longN",eX);
			intent.putExtra("latN", eY);
			
			this.startActivity(intent);
			ChangePlanActivity.this.finish();
		}
		return super.onKeyDown(keyCode, event);
	}

	private NaviEngineInitListener mNaviEngineInitListener = new NaviEngineInitListener() {
		public void engineInitSuccess() {
		}

		public void engineInitStart() {
		}

		public void engineInitFail() {
		}
	};

	private String getSdcardDir() {
		if (Environment.getExternalStorageState().equalsIgnoreCase(
				Environment.MEDIA_MOUNTED)) {
			return Environment.getExternalStorageDirectory().toString();
		}
		return null;
	}
}
