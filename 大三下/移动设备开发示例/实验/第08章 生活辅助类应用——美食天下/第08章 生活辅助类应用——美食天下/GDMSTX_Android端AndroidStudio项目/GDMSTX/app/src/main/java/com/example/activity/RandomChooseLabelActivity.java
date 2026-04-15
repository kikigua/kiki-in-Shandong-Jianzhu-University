package com.example.activity;

import java.util.List;

import com.example.MySQLite.DatabaseUtil;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
/**
 * @author Administrator
 *	随拍 标签选择的界面
 */
public class RandomChooseLabelActivity extends Activity 
implements OnClickListener{	
	RelativeLayout rl;
	TextView tv_head;
	TextView tv_submit;
	ImageView iv_back;
	EditText et_input;
	ListView lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.random_choose_tip);
		initView();
	}

	private void initView() {
		rl = (RelativeLayout)findViewById(R.id.include_head);
		tv_head = (TextView)rl.findViewById(R.id.head_title);
		tv_head.setText(getResources().getString(R.string.select_label));
		TextView tv_submit = (TextView)rl.findViewById(R.id.upload_submit);
		tv_submit.setText(getResources().getString(R.string.ok));
		tv_submit.setOnClickListener(this);
		iv_back = (ImageView)rl.findViewById(R.id.btback);
		iv_back.setOnClickListener(this);
		et_input = (EditText)findViewById(R.id.choose_tip_input);
		
		lv = (ListView)findViewById(R.id.choose_tip_listview);
		lv.setAdapter(new ChooseTipListAdapter(getApplicationContext()));
		lv.setOnItemClickListener(
				new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				TextView tv = (TextView)((RelativeLayout)arg1).getChildAt(0);
				String str = tv.getText().toString();
				finish();
				UploadPaiActivity.tv_tip.setText(str);
				
			}
			
		});
	}
	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.upload_submit:
			String custom_Tag = et_input.getText().toString();
			DatabaseUtil.insertLabel(getApplicationContext(), custom_Tag);
			finish();
			UploadPaiActivity.tv_tip.setText(custom_Tag);
			break;
		case R.id.btback:
			finish();
			break;
		}
	}
	private class ChooseTipListAdapter extends BaseAdapter{
		private List<String> labels;
		private LayoutInflater mInflater;
		public ChooseTipListAdapter(Context context) {
			this.labels = DatabaseUtil.getLabels(context);
			mInflater = LayoutInflater.from(context);
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return labels.size();
		}

		@Override
		public Object getItem(int position) {		
			// TODO Auto-generated method stub
			return labels.get(position);
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
				convertView = mInflater.inflate(R.layout.item_choose_tip, null);
				holder.tv = (TextView)convertView.findViewById(R.id.item_choosetip_tip);
				holder.tv.setText(labels.get(position));
				convertView.setTag(holder);
			}else {
				holder = (ViewHolder)convertView.getTag();
				holder.tv.setText(labels.get(position));
			}			
			return convertView;
		}
		private class ViewHolder {
			public TextView tv;
		}
	}
}
