package com.example.tabFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.activity.R;
import com.example.activity.RandomListViewActivity;
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
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class RandomFragment extends Fragment{
	MyToast toast;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		GridView random_grid = (GridView)inflater.inflate
				(R.layout.select_grid, container, false);
		random_grid.setSelector(new ColorDrawable(Color.TRANSPARENT));
		SimpleAdapter sa = new SimpleAdapter(				//数据适配器
					this.getActivity(), 					
					generateDataList(), 
					R.layout.grid_item, 		//行对应layout id
				    new String[]{"col1","col2"}, //列名列表
				    new int[]{R.id.ItemImage,R.id.ItemText}//列对应控件id列表
				);
		toast=new MyToast(getActivity());		
		random_grid.setAdapter(sa);
		random_grid.setOnItemClickListener(							//设置监听器
			new OnItemClickListener() {	
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {					
					if(!Utils.isNewWork(getActivity()))	{		
						toast.showToast(getResources().getString(R.string.net_fail));
						return;
					}
					RelativeLayout rl = (RelativeLayout)arg1;
					TextView tv = (TextView) rl.getChildAt(1);
					String text = tv.getText().toString();
					Intent intent = new Intent(getActivity(), RandomListViewActivity.class);
					intent.putExtra("title", text);
					intent.putExtra("type", Constant.RANDOM_LIKE[arg2]);
					startActivity(intent);								//切换界面到二级随拍条件界面
				}});		
		return random_grid;
	}	
	public List<? extends Map<String, ?>> generateDataList() {
		ArrayList<Map<String,Object>> list=new ArrayList<Map<String,Object>>();;
		int rowCounter = Constant.random_imgs.length;				//得到表格的行数
		for(int i=0;i<rowCounter;i++){								//循环生成每行的包含对应各个列数据的Map；col1、col2为列名
			HashMap<String,Object> hmap=new HashMap<String,Object>();
			hmap.put("col1", Constant.random_imgs[i]);   			//第一列为图片 		
			hmap.put("col2", this.getResources().getString(Constant.random_text[i]));//第二项标题
			list.add(hmap);
		}    	
		return list;
	}	
}