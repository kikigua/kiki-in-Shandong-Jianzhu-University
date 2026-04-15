package com.example.activity;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
/**
 * 
 * @author Administrator
 *		上传菜谱 标签选择界面
 */
public class MenuChooseLabelActivity extends Activity 
implements OnClickListener,OnItemClickListener{
	
	RelativeLayout rl;
	TextView tv_head;
	ImageView iv_back;	
	ListView lv;
	String[] list;
	int order;
//	public static boolean flag = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_choose_label);
		rl = (RelativeLayout)findViewById(R.id.include_head);
		tv_head = (TextView)rl.findViewById(R.id.head_title);
		String title = getIntent().getStringExtra("title");
		order=getIntent().getIntExtra("count",0);
		tv_head.setText(title);
		iv_back = (ImageView)rl.findViewById(R.id.btback);
		iv_back.setOnClickListener(this);
		lv = (ListView)findViewById(R.id.dishes_types_list);
		list = getIntent().getStringArrayExtra("list");
		lv.setAdapter(new DishesTypesListAdapter(this, list));
		lv.setOnItemClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()) {
		case R.id.btback:
			finish();
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		String str=list[arg2];;
		this.finish();
		UploadActivity.tvs[order].setText(str);
	}
	class DishesTypesListAdapter extends BaseAdapter{
		private LayoutInflater mInflater;
		public DishesTypesListAdapter(Context context,String[] strs) {
			mInflater = LayoutInflater.from(context);
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.length;
		}

		@Override
		public Object getItem(int position) {		
			// TODO Auto-generated method stub
			return list[position];
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder holder = null;
			if(convertView == null) {
				holder = new ViewHolder();
				convertView = mInflater.inflate(R.layout.item_dishes_types, null);
				holder.tv = (TextView)convertView.findViewById(R.id.item_dishes_types_type);
				holder.tv.setText(list[position]);
				convertView.setTag(holder);
			}else {
				holder = (ViewHolder)convertView.getTag();
				holder.tv.setText(list[position]);
			}
			
			return convertView;
		}
		private class ViewHolder {
			public TextView tv;
		}
	}
}
