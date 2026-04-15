package com.example.tabFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.activity.MenuSearchCondition;
import com.example.activity.MenuChooseListActivity;
import com.example.activity.R;
import com.example.util.Constant;
import com.example.util.MyToast;
import com.example.util.Utils;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MenuFragment extends Fragment {
	MyToast toast;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		GridView menu_grid = (GridView) inflater.inflate(R.layout.select_grid,
				container, false);
		SimpleAdapter sa = new SimpleAdapter(this.getActivity(),	//GridView适配器
				generateDataList(), R.layout.grid_item, 			//行对应layout id
				new String[] { "col1", "col2" }, 					//列名列表
				new int[] { R.id.ItemImage, R.id.ItemText }			//列对应控件id列表
		);
		toast=new MyToast(getActivity());
		menu_grid.setAdapter(sa);									//给 GridView设置适配器
		menu_grid.setSelector(new ColorDrawable(Color.TRANSPARENT));
		menu_grid.setOnItemClickListener(new OnItemClickListener() {	//设置监听器
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if(!Utils.isNewWork(getActivity())){						//断网提示
					toast.showToast(getResources().getString(R.string.net_fail));
					return;
				}
				if(arg2<2){					
					Intent intent=new Intent(getActivity(),MenuChooseListActivity.class);
					intent.putExtra("title", Constant.MENU_ARGS[arg2][0]	);
					intent.putExtra("type",Constant.MENU_LIKE[arg2]);
					intent.putExtra("args", Constant.MENU_ARGS[arg2][0]);
					startActivity(intent);								//搜索结果界面
					return;
				}
				Intent intent=new Intent(getActivity(),MenuSearchCondition.class);
				TextView tv=(TextView) arg1.findViewById(R.id.ItemText);
				String title=tv.getText().toString();
				intent.putExtra("title", title);
				intent.putExtra("type", Constant.MENU_LIKE[arg2]);
				startActivity(intent);							//菜品二级条件
			}});
		return menu_grid;
	}
	public List<? extends Map<String, ?>> generateDataList() {
		ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		int rowCounter = Constant.menu_imgs.length;//得到表格的行数
		for (int i = 0; i < rowCounter; i++) {//循环生成每行的包含对应各个列数据的Map；col1、col2为列名
			HashMap<String, Object> hmap = new HashMap<String, Object>();
			hmap.put("col1", Constant.menu_imgs[i]); //第一列为图片
			hmap.put("col2",
					this.getResources().getString(Constant.menu_text[i]));// 第二例为姓名
			list.add(hmap);
		}
		return list;
	}}