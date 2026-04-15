package com.example.activity;

import java.util.ArrayList;
import java.util.List;

import com.example.MyListView.MenuListItem;
import com.example.MyListView.MyListView;
import com.example.MyListView.MyListView.OnLoadedImage;
import com.example.MyListView.MyListView.OnRefreshListener;
import com.example.downLoader.ImageDownLoader;
import com.example.util.BitmapCache;
import com.example.util.Constant;
import com.example.util.NetInfoUtil;
import com.example.util.Utils;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
public class MenuChooseListActivity extends Activity {
	Handler mHandler;
	String args;
	int type;
	MyListView lv;
	MenuSearchAdapter lma;
	final int REFRESH = 0;
	final int LOADED = 1;
	final int NO_MESSAGE = 3;
	final int THE_END = 4;
	final int NO_NET = 5;
	List<MenuListItem> itemList;
	String timeDiver = "now()";
	List<String[]> list;
	ImageDownLoader imageDownLoader;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_listview);
		Bundle bundle = this.getIntent().getExtras();
		String title = bundle.getString("title");
		type = bundle.getInt("type", 0);
		args = bundle.getString("args","%");
		RelativeLayout head = (RelativeLayout) this
				.findViewById(R.id.menu_head);
		TextView tv = (TextView) head.findViewById(R.id.head_title);
		tv.setText(title);
		lv = (MyListView) this.findViewById(R.id.menu_listview);
		ImageButton iv = (ImageButton) head.findViewById(R.id.btback);
		imageDownLoader=new ImageDownLoader();
		iv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MenuChooseListActivity.this.finish();
		}});
		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				switch (msg.what) {
				case REFRESH:
					itemList = new ArrayList<MenuListItem>();
					loadItem(list);
					lma = new MenuSearchAdapter(itemList,
					MenuChooseListActivity.this);
					lv.setAdapter(lma);
					lv.onRefreshComplete();
					lv.refreshNow = false;
					lv.nowLoad=false;
					lma.isInit=true;
					break;
				case LOADED:
					loadItem(list);
					lma.notifyDataSetChanged();
					lv.nowLoad = false;
					break;
				case THE_END:
					TextView tv = (TextView) lv.findViewById(R.id.foot_tip);
					tv.setText(getResources().getString(R.string.the_end));
					break;
				case NO_MESSAGE:
					String noMessage = getResources()
							.getString(R.string.no_set);
					noNetWork(noMessage);
					break;
				case NO_NET:
					String noNet = getResources().getString(R.string.net_fail);
					noNetWork(noNet);
					break;
				}}};
		initView();
	}
	private void initView()	{
		itemList=new ArrayList<MenuListItem>();
		lma = new MenuSearchAdapter(itemList, MenuChooseListActivity.this);
		lv.setAdapter(lma);
		lv.setonRefreshListener(new MyOnRefreshListener());
		lv.setOnLoadImage(
				new OnLoadedImage(){
					@Override
					public void onLoadImage() {
						int start=lv.getFirstVisiblePosition()-1;
						start=(start<0?0:start);
						int end=lv.getLastVisiblePosition();
						while(start<end&&start<itemList.size())						{
							String prefix=itemList.get(start).getPic();
							ImageView iv=(ImageView) lv.findViewWithTag(prefix+start);
							imageDownLoader.thumbnailExcute(iv, itemList.get(start).getPic());
							start++;
						}						
					}
					@Override
					public void onCancell() {
						lma.isInit=false;
						imageDownLoader.cancelTask();						
			}});}
	private class LoadMore extends Thread {
		@Override
		public void run() {
			try {
				list = NetInfoUtil.getMenuLike(timeDiver, type, args);
				if (!list.get(0)[0].equals(Constant.NO_MESSAGE)) {

					mHandler.sendEmptyMessage(LOADED);
				} else {
					if (itemList.size() <= 0) {
						mHandler.sendEmptyMessage(NO_MESSAGE);
					} else {
						mHandler.sendEmptyMessage(THE_END);
				}}
			} catch (Exception e) {e.printStackTrace();lv.nowLoad = false;}
		}}
	private void loadItem(List<String[]> list) {		
		String time = list.get(list.size() - 1)[8];
		int indexOf = time.lastIndexOf(".");
		timeDiver = time.substring(0, indexOf);
		for (String[] str : list) {
			MenuListItem item = new MenuListItem();
			item.setId(str[0]);
			System.out.println("primary pic" + str[3]);
			item.setPic(str[3]);
			item.setName(str[1]);
			item.setUid(str[2]);
			item.setLike(str[5]);
			String[] ss = str[4].split("%");
			StringBuilder material = new StringBuilder();
			for (String s : ss) {
				String[] sss = s.split("\\|");
				System.out.println("s "+s);
				material.append(sss[sss.length-2] + "、");
			}
			item.setMaterial(material.substring(0, material.length()-1));
			item.setCollection(str[6]);
			item.setPinglun(str[7]);
			item.setUploadTime(str[8]);
			itemList.add(item);
		}}
	private void noNetWork(String tip) {
		LinearLayout ll = (LinearLayout) this.findViewById(R.id.content);
		ll.removeAllViews();
		RelativeLayout nocontent = (RelativeLayout) LayoutInflater.from(this)
				.inflate(R.layout.noloaded, null);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		nocontent.setLayoutParams(params);
		TextView tv = (TextView) nocontent.getChildAt(0);
		tv.setText(tip);
		ll.addView(nocontent);
	}
	private class Refresh extends Thread {
		@Override
		public void run() {
			try {
				timeDiver = "now()";
				lv.nowLoad = false;
				list = NetInfoUtil.getMenuLike(timeDiver, type, args);
				if (!list.get(0)[0].equals(Constant.NO_MESSAGE)) {
					mHandler.sendEmptyMessage(REFRESH);
				} else {
					if(list.size()<=0){
						mHandler.sendEmptyMessage(NO_MESSAGE);
					}else{
						mHandler.sendEmptyMessage(THE_END);
				}}
			} catch (Exception e) {e.printStackTrace();}
		}}	
	private class MyOnRefreshListener implements OnRefreshListener {// /刷新接口
		@Override
		public void onRefresh() {		// 刷新
			if (!Utils.isNewWork(MenuChooseListActivity.this)) {
				mHandler.sendEmptyMessage(NO_NET);
				return;
			}
			lv.refreshNow = true;
			mHandler.postDelayed(new Runnable() {
				@Override
				public void run() {
					new Refresh().start();
				}
			}, 500);}
		@Override		
		public void loadMore() {// 加载更多
			if (!Utils.isNewWork(MenuChooseListActivity.this)) {
				mHandler.sendEmptyMessage(NO_NET);
				return;
			}
			lv.nowLoad = true;
			mHandler.postDelayed(new Runnable() {
				@Override
				public void run() {
					new LoadMore().start();
				}
			}, 500);}}
	public class MenuSearchAdapter extends BaseAdapter implements OnClickListener
	{
		List<MenuListItem> list;
		Context context;
		public Boolean isInit=true;
		public MenuSearchAdapter(List<MenuListItem> list,Context context){
			this.list=list;
			this.context=context;
		}
		@Override
		public int getCount() {
			return list.size();
		}
		@Override
		public Object getItem(int position) {
			return null;
		}
		@Override
		public long getItemId(int position) {
			return 0;
		}
		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
				if(convertView==null){
					convertView = LayoutInflater.from(context).inflate(
						R.layout.menu_listview_item, null);
				}
				HorizontalScrollView hSView = (HorizontalScrollView) convertView.findViewById(R.id.hsv);
				View content=convertView.findViewById(R.id.ll_content);
				View action=convertView.findViewById(R.id.ll_action);
				ImageView iv = (ImageView) convertView
						.findViewById(R.id.primaryPic);
				TextView cname = (TextView) convertView.findViewById(R.id.mname);
				TextView introduce = (TextView) convertView.findViewById(R.id.material);
				TextView like = (TextView) convertView.findViewById(R.id.like_number);
				TextView collection = (TextView) convertView
						.findViewById(R.id.collection_number);
				TextView pinglun = (TextView) convertView
						.findViewById(R.id.pinglun_number);
				TextView uploadTime = (TextView) convertView
						.findViewById(R.id.upload_time);			
				android.view.ViewGroup.LayoutParams lp = content.getLayoutParams();	 // 设置内容view的大小为屏幕宽度,这样按钮就正好被挤出屏幕外
	            lp.width = Constant.ScreenWidth;
	            content.setTag(position);
			content.setOnClickListener(this);
			action.setVisibility(View.GONE);
			Bitmap bitmap = BitmapCache.showCacheBitmap(ImageDownLoader.thumbnail+list.get(position).getPic());
			if(bitmap!=null){
				iv.setImageBitmap(bitmap);
			}else{
				iv.setImageResource(R.drawable.recipe_defult_img);
				if(isInit){
					imageDownLoader.thumbnailExcute(iv, list.get(position).getPic());
				}}
			cname.setText(list.get(position).getName());
			introduce.setText(list.get(position).getMaterial());
			like.setText(list.get(position).getLike());
			collection.setText(list.get(position).getCollection());
			pinglun.setText(list.get(position).getPinglun());
			String time = list.get(position).getUploadTime();
			uploadTime.setText(time);
			iv.setTag(list.get(position).getPic()+position);
	        if (hSView.getScrollX() != 0) {			// 这里防止删除一条item后,ListView处于操作状态,直接还原
	        	hSView.scrollTo(0, 0);
	        }		
			return convertView;
		}
		@Override
		public void onClick(View v) {
			int position=(Integer) v.getTag();
			switch(v.getId()){
			case R.id.ll_content:
				String id = list.get(position).getId();
				Intent intent = new Intent(context,
						Menushow.class);
				intent.putExtra("menu_id", id);
				context.startActivity(intent);
				break;
			}}}
	@Override
	protected void onStop() {
		super.onStop();
		imageDownLoader.cancelTaskNow();
}}