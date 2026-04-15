package com.cn.map;

import java.util.HashMap;
import java.util.List;

import com.cn.hangzhou.R;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class ChangeLineAdapter extends BaseAdapter {

	private Context mContext = null;//上下文
	private List<HashMap<String, String>> mChangeList = null;//数据List
	private LayoutInflater inflater = null;//inflater

	public ChangeLineAdapter(Context context,
			List<HashMap<String, String>> mChangeList) {
		this.mContext = context;
		this.mChangeList = mChangeList;
		inflater = LayoutInflater.from(mContext);
	}

	@Override
	public int getCount() {//返回Listview 个数
		// TODO Auto-generated method stub
		return mChangeList.size();
	}

	@Override
	public Object getItem(int convertView) {

		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		//加载view
		if (convertView == null) {

			convertView = inflater.inflate(R.layout.listview_change, parent,
					false);
			holder = new ViewHolder();
			holder.changeNum = (TextView) convertView.findViewById(R.id.tv_changeNum);
			holder.changeBus = (TextView) convertView.findViewById(R.id.tv_changeBus);
			holder.changeStation = (TextView) convertView.findViewById(R.id.tv_changeStation);
			holder.changeAllStation = (TextView) convertView.findViewById(R.id.tv_changeAllStationNum);
			holder.ditu_bt=(Button)convertView.findViewById(R.id.btn);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		//设置文字
		holder.changeNum.setText(position+1+"");
		holder.changeBus.setText(mChangeList.get(position).get("bus"));
		holder.changeStation.setText(mChangeList.get(position).get("station"));
		if(mChangeList.get(position).get("change").equals("0"))
		{
			holder.changeAllStation.setText("直达，共"+mChangeList.get(position).get("allStation")+"站");
		}else
		{
			holder.changeAllStation.setText("换乘"+mChangeList.get(position).get("change")+"次，共"+mChangeList.get(position).get("allStation")+"站");
		}
		
	  addListener(convertView,position);
		
		return convertView;
	}
	
	public void addListener(View convertView,int arg) {
		final int arg2=arg;
		((Button)convertView.findViewById(R.id.btn)).setOnClickListener(
				new View.OnClickListener() {
					public void onClick(View v) {
					
						Intent intent = new Intent(BusLineActivity.ma,BusActivity.class);
						intent.putExtra("index",arg2);//添加内容
						BusLineActivity.ma.startActivity(intent);
						BusLineActivity.ma.finish();//结束本界面
						
					}
				});
	}
	
	//声明 view
	static class ViewHolder {
		TextView changeNum;
		TextView changeBus;
		TextView changeStation;
		TextView changeAllStation;
       Button   ditu_bt;
	}
}
