package com.example.activity;

import com.example.util.Constant;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 菜品查询二级条件ListView 界面
 * 
 */
public class MenuSearchCondition extends Activity implements OnClickListener {
	ImageButton back;										//定义变量
	BaseAdapter ba;
	Handler mHandler;
	ListView lv;
	final int LOAD = 0;
	final int NONET = 1;
	int type;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_condition);
		back = (ImageButton) findViewById(R.id.btback);
		back.setOnClickListener(this);					//返回按钮设置监听器
		lv = (ListView) this.findViewById(R.id.lv_condition);
		RelativeLayout rl = (RelativeLayout) this.findViewById(R.id.head);
		TextView tv = (TextView) rl.findViewById(R.id.head_title);
		Bundle bundle = this.getIntent().getExtras();
		String title = bundle.getString("title");
		tv.setText(title);
		type = bundle.getInt("type", 0);
		ba = new FlavoutAdapter(MenuSearchCondition.this,			//设置适配器
				Constant.MENU_ARGS[type]);
		lv.setAdapter(ba);
		lv.setOnItemClickListener(new OnItemClickListener() {		//设置监听器
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				String str = (String) ((TextView) arg1).getText();
				Intent intent = new Intent(MenuSearchCondition.this,
						MenuChooseListActivity.class);
				intent.putExtra("title", str);
				intent.putExtra("type", type);
				intent.putExtra("args", Constant.MENU_ARGS[type][arg2]);
				startActivity(intent);
		}});}

	@Override
	public void onClick(View v) {
		if (v == back) 
		{this.finish();}
	}
	private class FlavoutAdapter extends BaseAdapter {			//声明监听器
		String[] type;
		Context context;
		public FlavoutAdapter(Context context, String[] type) {
			this.type = type;
			this.context = context;
		}
		@Override
		public int getCount() {
			return type.length;
		}
		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LinearLayout view = (LinearLayout) LayoutInflater.from(context).inflate(
					R.layout.item_dishes_types, null);
			TextView tv=(TextView) view.findViewById(R.id.item_dishes_types_type);
			tv.setText(type[position]);								
			return view;												//返回TextView
		}}}