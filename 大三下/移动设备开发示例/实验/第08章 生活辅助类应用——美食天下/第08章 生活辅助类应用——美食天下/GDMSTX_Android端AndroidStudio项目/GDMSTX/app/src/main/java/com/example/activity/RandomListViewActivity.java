package com.example.activity;

import java.util.ArrayList;
import java.util.List;

import com.example.MyListView.MyListView;
import com.example.MyListView.MyListView.OnLoadedImage;
import com.example.MyListView.MyListView.OnRefreshListener;
import com.example.MyListView.RandomListItem;
import com.example.MyRoundedImageView.RoundImageView;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class RandomListViewActivity extends Activity {
	MyListView lv;
	int type;
	ArrayList<RandomListItem> itemList;
	RandomSearchAdapter adapter;
	Handler mHandler;
	final int REFRESH = 1;
	final int LOAD_MORE = 2;
	final int NO_MESSAGE = 3;
	final int NO_NET = 4;
	final int THE_END = 5;
	String timeDiver = "now()";
	String arg = "%";
	List<String[]> list;
	ImageDownLoader imageDownLoader;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_listview);		
		type = this.getIntent().getExtras().getInt("type", 0);
		arg = this.getIntent().getExtras().getString("arg", "%");
		initView();
		imageDownLoader=new ImageDownLoader();
		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				switch (msg.what) {
				case REFRESH:					
					lv.onRefreshComplete();
					itemList = new ArrayList<RandomListItem>();
					addListItem(list);
					adapter = new RandomSearchAdapter(itemList,
							RandomListViewActivity.this);;
					adapter.isInit=true;
					lv.setAdapter(adapter);
					lv.refreshNow = false;
					lv.nowLoad = false;
					break;
				case LOAD_MORE:
					addListItem(list);
					adapter.notifyDataSetChanged();
					lv.nowLoad = false;
					break;
				case THE_END:
					TextView tv = (TextView) lv.findViewById(R.id.foot_tip);
					tv.setText("已经到底了");
					lv.nowLoad=true;
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
				}
			}
		};
	}

	private void initView() {
		RelativeLayout rl = (RelativeLayout) findViewById(R.id.menu_head);
		TextView tv_head = (TextView) rl.findViewById(R.id.head_title);
		String title = this.getIntent().getExtras().getString("title");
		tv_head.setText(title);
		ImageButton iv_back = (ImageButton) rl.findViewById(R.id.btback);
		iv_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		lv = (MyListView) findViewById(R.id.menu_listview);
		lv.setonRefreshListener(new MyOnRefreshListener());
		itemList = new ArrayList<RandomListItem>();
		adapter = new RandomSearchAdapter(itemList,
				RandomListViewActivity.this);
		lv.setAdapter(adapter);
		lv.setOnLoadImage(
				new OnLoadedImage()
				{
					@Override
					public void onLoadImage() {
						int start=lv.getFirstVisiblePosition()-1;
						start=(start<0?0:start);
						int end=lv.getLastVisiblePosition();
						while(start<end&&start<itemList.size())
						{
							String prefix=itemList.get(start).getImg();
							ImageView iv=(ImageView) lv.findViewWithTag(prefix+start);
							imageDownLoader.thumbnailExcute(iv, itemList.get(start).getImg());
							prefix=itemList.get(start).getHead();
							RoundImageView head=(RoundImageView) lv.findViewWithTag(prefix+start);
							imageDownLoader.roundImageViewExcute(head, itemList.get(start).getHead());
							start++;
						}						
					}
					@Override
					public void onCancell() {
						adapter.isInit=false;
						imageDownLoader.cancelTask();						
					}					
				});
		}

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

	// /刷新接口
	private class MyOnRefreshListener implements OnRefreshListener {

		@Override
		// 刷新
		public void onRefresh() {
			if (!Utils.isNewWork(RandomListViewActivity.this)) {
				mHandler.sendEmptyMessage(NO_NET);
				return;
			}
			lv.refreshNow = true;
			mHandler.postDelayed(new Runnable() {
				@Override
				public void run() {

					new Refresh().start();
				}
			}, 500);
		}

		@Override
		// 加载更多
		public void loadMore() {
			if (!Utils.isNewWork(RandomListViewActivity.this)) {
				mHandler.sendEmptyMessage(NO_NET);
				return;
			}
			lv.nowLoad = true;
			mHandler.postDelayed(new Runnable() {
				@Override
				public void run() {
					new LoadMore().start();
				}
			}, 500);
		}
	}

	private class Refresh extends Thread {
		@Override
		public void run() {
			try {
				timeDiver = "now()";
				list = NetInfoUtil.getRanLike(timeDiver, type,
						arg);
				
				if(!list.get(0)[0].equals(Constant.NO_MESSAGE))
				{
					mHandler.sendEmptyMessage(REFRESH);
				}else
				{					
					mHandler.sendEmptyMessage(NO_MESSAGE);
				}
			} catch (Exception e) {
				e.printStackTrace();
				lv.refreshNow = false;
			}
		}
	}

	private void addListItem(List<String[]> list) {
		String time = list.get(list.size() - 1)[7];
		int indexOf = time.lastIndexOf(".");
		timeDiver = time.substring(0, indexOf);
		for (String[] str : list) {
//			select id,picPath,uid,introduce,slike"
//					+ ",collection,pinglun,uploadTime,sculture from
			String cname=str[2];
			String collection=str[5];
			String head=str[8];
			String id=str[0];
			String img=str[1].split("%")[0];
			String introduce=str[3];
			String like=str[4];
			String pinglun=str[6];
			String uploadTime=str[7];
			RandomListItem item=new RandomListItem();
			item.setCname(cname);
			item.setCollection(collection);
			item.setHead(head);
			item.setId(id);
			item.setImg(img);
			item.setIntroduce(introduce);
			item.setLike(like);
			item.setName(cname);
			item.setPinglun(pinglun);
			item.setUploadTime(uploadTime);
			itemList.add(item);
		}
	}

	private class LoadMore extends Thread {
		@Override
		public void run() {
			try {
				list = NetInfoUtil.getRanLike(timeDiver, type, arg);
				System.out.println("load more");
				if (!list.get(0)[0].equals(Constant.NO_MESSAGE)) {
					mHandler.sendEmptyMessage(LOAD_MORE);
				} else if (itemList.size() <= 0) {
					mHandler.sendEmptyMessage(NO_MESSAGE);
				} else {
					mHandler.sendEmptyMessage(THE_END);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public class RandomSearchAdapter extends BaseAdapter implements OnClickListener {
		private ArrayList<RandomListItem> list;
		private Context context;
		public Boolean isInit=true;
		public RandomSearchAdapter(ArrayList<RandomListItem> list, Context context) {
			this.list = list;
			this.context = context;
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			if (convertView== null) {
				convertView = LayoutInflater.from(context).inflate(
						R.layout.random_listview_item, null);
			} 
			HorizontalScrollView hSView=(HorizontalScrollView) convertView.findViewById(R.id.hsv);
			View content=convertView.findViewById(R.id.ll_content);			
			ImageView iv = (ImageView) convertView.findViewById(R.id.loaded_random_image);
			RoundImageView head = (RoundImageView) convertView.findViewById(R.id.Loaded_random_head);
			TextView cname = (TextView) convertView.findViewById(R.id.loaded_random_user);
			TextView introduce = (TextView) convertView
						.findViewById(R.id.loaded_random_introduce);
			TextView like = (TextView) convertView.findViewById(R.id.loaded_like_number);
			TextView collection = (TextView) convertView
						.findViewById(R.id.loaded_collection_number);
			TextView pinglun = (TextView) convertView
						.findViewById(R.id.loaded_pinglun_number);
			TextView uploadTime = (TextView) convertView.findViewById(R.id.loaded_random_time);
			TextView delete = (Button) convertView.findViewById(R.id.delete);		
				
				 // 设置内容view的大小为屏幕宽度,这样按钮就正好被挤出屏幕外
	        android.view.ViewGroup.LayoutParams lp = content.getLayoutParams();
	        lp.width = Constant.ScreenWidth;			
			Bitmap img=BitmapCache.getBitmapFromMemCache(ImageDownLoader.thumbnail+list.get(position).getImg());
			if(img!=null)
			{
				System.out.println("cache");
				iv.setImageBitmap(img);
			}else
			{
				iv.setImageResource(R.drawable.recipe_defult_img);
				if(isInit)
				{
					imageDownLoader.thumbnailExcute(iv, list.get(position).getImg());
				}
			}
			img=BitmapCache.getBitmapFromMemCache(list.get(position).getHead());
			if(img!=null)
			{
				head.setImageBitmap(img);
			}else
			{
				head.setImageResource(R.drawable.recipe_defult_img);
				if(isInit)
				{
					imageDownLoader.roundImageViewExcute(head, list.get(position).getHead());
				}
			}
			head.setTag(list.get(position).getHead()+position);
			iv.setTag(list.get(position).getImg()+position);
			head.setTag(list.get(position).getHead()+position);		
			cname.setText(list.get(position).getName());
			introduce.setText(list.get(position).getIndtroduce());
			like.setText(list.get(position).getlike());
			collection.setText(list.get(position).getCollection());
			pinglun.setText(list.get(position).getPinglun());
			uploadTime.setText(list.get(position).getUploadTime());
			delete.setVisibility(View.GONE);
			content.setTag(position);
			content.setOnClickListener(this);
			 // 这里防止删除一条item后,ListView处于操作状态,直接还原
	        if (hSView.getScrollX() != 0) {
	        	hSView.scrollTo(0, 0);
	        }
			return convertView;
		}
		@Override
		public void onClick(View v) {
			int position=(Integer) v.getTag();
			switch(v.getId())
			{
			case R.id.ll_content:
				Intent intent = new Intent(context,
						Randomshow.class);
				String random_Id = list.get(position).getId();
				intent.putExtra("random_id", random_Id);
				context.startActivity(intent);
				break;
			}
		}
	}
	@Override
	protected void onStop() {
		super.onStop();
		imageDownLoader.cancelTaskNow();
	}
}
