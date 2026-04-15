package com.example.activity;

import java.util.ArrayList;
import java.util.Arrays;

import com.example.MyPopWindow.exitPopupWindow;
import com.example.search.SearchAutoAdapter;
import com.example.search.SearchAutoData;
import com.example.util.Constant;
import com.example.util.MyToast;
import com.example.util.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class SearchActivity extends Activity implements OnClickListener {

	PopupWindow pop;
	TextView mbbt; // 菜谱、随拍按钮
	EditText et;
	ImageView search_btn; // 查询按钮
	final int MENU = 0;
	final int RANDOM = 1;
	int state = MENU;
	TextView clearHistory; // 清除历史
	private SearchAutoAdapter mSearchAutoAdapter;
	MyToast toast;
	PopupWindow exit;	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lay_search);
		mbbt = (TextView) this.findViewById(R.id.search_pop_text);
		mbbt.setOnClickListener(this);
		ImageView search_back = (ImageView) findViewById(R.id.search_back);
		search_back.setOnClickListener(this);
		ImageView iv = (ImageView) this.findViewById(R.id.search_delete);
		iv.setOnClickListener(this);
		// 输入框
		et = (EditText) this.findViewById(R.id.search_et);
		search_btn = (ImageView) this.findViewById(R.id.search_btn);
		// 查询按钮
		search_btn.setOnClickListener(this);
		clearHistory = (TextView) findViewById(R.id.search_clearHistory);
		clearHistory.setOnClickListener(this);
		toast = new MyToast(this);
		initView();

	}

	public void initView() {
		pop = new PopupWindow();
		pop.setWidth(LayoutParams.WRAP_CONTENT);
		pop.setHeight(LayoutParams.WRAP_CONTENT);
		pop.setBackgroundDrawable(new BitmapDrawable());
		pop.setFocusable(true);
		pop.setOutsideTouchable(true);
		ColorDrawable dw = new ColorDrawable(0xa0000000);
		pop.setBackgroundDrawable(dw);
		View view = getLayoutInflater().inflate(R.layout.listviewpopwindow,
				null);
		pop.setContentView(view);
		TextView menu = (TextView) view.findViewById(R.id.menu);
		TextView pai = (TextView) view.findViewById(R.id.random);
		menu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mbbt.setText(getResources().getString(R.string.menu));
				state = MENU;
				pop.dismiss();
				init();

			}
		});
		pai.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mbbt.setText(getResources().getString(R.string.random));
				state = RANDOM;
				pop.dismiss();
				init();

			}
		});
		init();
	}

	private void init(){
		mSearchAutoAdapter = new SearchAutoAdapter(this, -1,state);
		ListView mAutoListView = (ListView) findViewById(R.id.search_content_listview);
		mAutoListView.setAdapter(mSearchAutoAdapter);
		mAutoListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long arg3) {
				SearchAutoData data = (SearchAutoData) mSearchAutoAdapter
						.getItem(position);
				et.setText(data.getContent());
				search_btn.performClick();
			}
		});
		et.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				mSearchAutoAdapter.performFiltering(s);
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.search_back:
			this.finish();
			break;
		case R.id.search_pop_text:
			pop.showAsDropDown(v);
			break;
		case R.id.search_delete:
			et.setText("");
			break;
		case R.id.search_btn:
			if (!Utils.isNewWork(this)) {
				toast.showToast(this.getResources()
						.getString(R.string.net_fail));
			}
			String selecitionArgs = et.getText().toString();
			search(selecitionArgs);
			saveSearchHistory(state);
			mSearchAutoAdapter.initSearchHistory(state);
			break;
		case R.id.search_clearHistory:
			((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
					.hideSoftInputFromWindow(
							getCurrentFocus().getWindowToken(),
							InputMethodManager.HIDE_NOT_ALWAYS);
			// 清除历史记录
			String type=(state==MENU?"菜品":"随拍");
			exit = new exitPopupWindow(this, "您确认要清除"+type+"搜索历史记录吗？",
					new OnClickListener() {
						@Override
						public void onClick(View v) {
							if (v.getId() == R.id.ok_bt) {
								SharedPreferences sp = getSharedPreferences(
										SearchAutoAdapter.SEARCH_HISTORY, 0);
								Editor et = sp.edit();
								et.putString(""+state, "");
								et.commit();
								mSearchAutoAdapter.clearHistory();
								exit.dismiss();
							}
						}
					});
			exit.showAtLocation(this.findViewById(R.id.parent), Gravity.CENTER,
					0, 0);
			break;
		}
	}

	public void search(String selectionArgs) {
		Intent intent = null;
		switch (state) {
		case MENU:
			intent = new Intent(this, MenuChooseListActivity.class);
			intent.putExtra("title", selectionArgs);
			intent.putExtra("type", Constant.MENU_LIKE_NAME);
			intent.putExtra("args", selectionArgs);
			startActivity(intent);
			break;
		case RANDOM:
			intent = new Intent(this, RandomListViewActivity.class);
			intent.putExtra("type", Constant.RANDOM_LIKE_INTRODUCE);
			intent.putExtra("title", selectionArgs);
			intent.putExtra("arg", selectionArgs);
			startActivity(intent);
			break;
		}

	}

	/*
	 * 保存搜索记录
	 */
	private void saveSearchHistory(int state) {
		String text = et.getText().toString().trim();
		if (text.length() < 1) {
			return;
		}
		SharedPreferences sp = getSharedPreferences(
				SearchAutoAdapter.SEARCH_HISTORY, 0);
		String longhistory = sp.getString(""+state, "");
		String[] tmpHistory = longhistory.split(",");
		ArrayList<String> history = new ArrayList<String>(
				Arrays.asList(tmpHistory));
		if (history.size() > 0) {
			int i;
			for (i = 0; i < history.size(); i++) {
				if (text.equals(history.get(i))) {
					history.remove(i);
					break;
				}
			}
			history.add(0, text);
		}
		if (history.size() > 0) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < history.size(); i++) {
				sb.append(history.get(i) + ",");
			}
			sp.edit()
					.putString(""+state, sb.toString())
					.commit();
		} else {
			sp.edit().putString(""+state, text + ",")
					.commit();
		}
	}
}