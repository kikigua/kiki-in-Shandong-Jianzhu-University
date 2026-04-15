package com.example.fragment;

import java.util.ArrayList;
import java.util.List;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.MyListView.MyListView;
import com.example.MyListView.MyListView.OnLoadedImage;
import com.example.MyListView.RandomListItem;
import com.example.MyListView.MyListView.OnRefreshListener;
import com.example.MyRoundedImageView.RoundImageView;
import com.example.MySQLite.DatabaseUtil;
import com.example.activity.R;
import com.example.activity.Randomshow;
import com.example.downLoader.ImageDownLoader;
import com.example.util.BitmapCache;
import com.example.util.Constant;
import com.example.util.OnLoadUtil;

public class LoadedRandomFragment extends Fragment {
	LinearLayout ll;
	ArrayList<RandomListItem> list;
	boolean flag;
	MyListView lv;
	LoadeRandomAdapter ba;
	Handler mHandler;
	final int NO_MESSAGE = 0;
	final int THE_END = 1;;
	final int LOAD = 2;
	List<String[]> relist;
	ImageDownLoader imageDownLoader;
	String timeDiver=" datetime(CURRENT_TIMESTAMP,'localtime')";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				switch (msg.what) {
				case NO_MESSAGE:
					String noNet = getResources().getString(
							R.string.noRanLoaded);
					noNetWork(noNet);
					break;
				case THE_END:
					TextView tv = (TextView) lv.findViewById(R.id.foot_tip);
					String end = getResources().getString(R.string.the_end);
					tv.setText(end);
					lv.nowLoad=true;
					break;
				case LOAD:
					lv.nowLoad=false;
					getLocalRandom();
					ba.notifyDataSetChanged();
					break;
				}
			}

		};
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ll = (LinearLayout) inflater.inflate(R.layout.loaded_listview, null);
		lv = (MyListView) ll.getChildAt(0);
		list = new ArrayList<RandomListItem>();
		ba = new LoadeRandomAdapter(getActivity());
		imageDownLoader=new ImageDownLoader();
		lv.setAdapter(ba);
		lv.setResable(false);
		lv.setonRefreshListener(new MyOnRefreshListener());
		lv.setOnLoadImage(
				new OnLoadedImage()
				{

					@Override
					public void onLoadImage() {
						int start=lv.getFirstVisiblePosition();
						int end=lv.getLastVisiblePosition();
						while(start<end&&start<list.size()&&start<list.size())
						{
							String prefix=list.get(start).getImg();
							ImageView img=(ImageView) lv.findViewWithTag(prefix+start);
							imageDownLoader.thumbnailExcute(img, list.get(start).getImg());
							
							prefix=list.get(start).getHead();
							RoundImageView head=(RoundImageView) lv.findViewWithTag(prefix+start);
							imageDownLoader.roundImageViewExcute(head, list.get(start).getHead());
							start++;
						}
						
					}

					@Override
					public void onCancell() {
						ba.isInit=false;
						imageDownLoader.cancelTask();						
					}					
				});
		return ll;
	}
	

	public void getLocalRandom() {
		if (relist != null && relist.size() > 0) {
			timeDiver="'"+relist.get(relist.size()-1)[1]+"'";
			System.out.println("timeDiver"+timeDiver);
			for (String[] str : relist) {
				String cname = str[0];
				String introduce = str[2];
				String like = str[3];
				String collection = str[4];
				String pinglun = str[5];
				String uploadTime = str[1];
				String id=str[10];
				String sculture=str[9];
				String img=str[8].split("%")[0];
				
				RandomListItem item=new RandomListItem();
				item.setId(id);
				item.setImg(img);
				item.setHead(sculture);
				item.setCname(cname);
				item.setLike(like);
				item.setIntroduce(introduce);
				item.setCollection(collection);
				item.setPinglun(pinglun);
				item.setUploadTime(uploadTime);
				list.add(item);
			}
		}
	}

	private void noNetWork(String tip) {
		ll.removeAllViews();
		RelativeLayout nocontent = (RelativeLayout) LayoutInflater.from(
				getActivity()).inflate(R.layout.noloaded, null);
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
		}

		@Override
		// 加载更多
		public void loadMore() {
			lv.nowLoad = true;
			mHandler.postDelayed(new Runnable() {
				@Override
				public void run() {
					new LoadMore().start();
				}
			}, 500);
		}
	}

	class LoadMore extends Thread {
		@Override
		public void run() {
			try {
				relist = DatabaseUtil.getRandom(getActivity()
						.getApplicationContext(), timeDiver);
				if (relist != null) {
					mHandler.sendEmptyMessage(LOAD);
				} else {
					if(list.size()>0){
						mHandler.sendEmptyMessage(THE_END);
					}else
					{
						mHandler.sendEmptyMessage(NO_MESSAGE);
					}
				}
			} catch (Exception e) {
				lv.nowLoad = false;
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		imageDownLoader.cancelTask();
	}
	public class LoadeRandomAdapter extends BaseAdapter implements OnClickListener {
		private Context context;
		View view;
		public Boolean isInit = true;
		OnLoadUtil onLocalUtil;
		public LoadeRandomAdapter(Context context) {
			this.context = context;
			onLocalUtil=new OnLoadUtil(context);
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
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = LayoutInflater.from(context).inflate(
						R.layout.random_listview_item, null);
			}

			final HorizontalScrollView hSView = (HorizontalScrollView) convertView
					.findViewById(R.id.hsv);
			View content = convertView.findViewById(R.id.ll_content);
			final View action = convertView.findViewById(R.id.ll_action);

			ImageView iv = (ImageView) convertView
					.findViewById(R.id.loaded_random_image);
			RoundImageView head = (RoundImageView) convertView
					.findViewById(R.id.Loaded_random_head);
			TextView cname = (TextView) convertView
					.findViewById(R.id.loaded_random_user);
			TextView introduce = (TextView) convertView
					.findViewById(R.id.loaded_random_introduce);
			TextView like = (TextView) convertView
					.findViewById(R.id.loaded_like_number);
			TextView collection = (TextView) convertView
					.findViewById(R.id.loaded_collection_number);
			TextView pinglun = (TextView) convertView
					.findViewById(R.id.loaded_pinglun_number);
			TextView uploadTime = (TextView) convertView
					.findViewById(R.id.loaded_random_time);
			TextView delete = (Button) convertView.findViewById(R.id.delete);

			// 设置内容view的大小为屏幕宽度,这样按钮就正好被挤出屏幕外
			android.view.ViewGroup.LayoutParams lp = content.getLayoutParams();
			lp.width = Constant.ScreenWidth;
			Bitmap img=BitmapCache.showCacheBitmap(list.get(position).getImg());
			if(img!=null)
			{
				iv.setImageBitmap(img);
			}
			else
			{
				iv.setImageResource(R.drawable.recipe_defult_img);
				if(isInit)
				{
					imageDownLoader.thumbnailExcute(iv, list.get(position).getImg());
				}
			}
			iv.setTag(list.get(position).getImg()+position);
			Bitmap sculture=BitmapCache.showCacheBitmap(list.get(position).getHead());
			if(sculture!=null)
			{
				head.setImageBitmap(sculture);
			}else
			{
				head.setImageResource(R.drawable.recipe_defult_img);
				if(isInit)
				{
					imageDownLoader.roundImageViewExcute(head, list.get(position).getHead());
				}
			}
			head.setTag(list.get(position).getHead()+position);
			cname.setText(list.get(position).getName());
			introduce.setText(list.get(position).getIndtroduce());
			like.setText(list.get(position).getlike());
			collection.setText(list.get(position).getCollection());
			pinglun.setText(list.get(position).getPinglun());
			uploadTime.setText(list.get(position).getUploadTime());
			delete.setTag(position);
			delete.setOnClickListener(this);
			content.setTag(position);
			content.setOnClickListener(this);
			convertView.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
						System.out.println("ok");
						if (view != null) {
							HorizontalScrollView hSView1 = (HorizontalScrollView) view
									.findViewById(R.id.hsv);
							hSView1.smoothScrollTo(0, 0);
						}
						view = v;
						break;
					case MotionEvent.ACTION_MOVE:

						break;
					case MotionEvent.ACTION_UP:
						// 获得ViewHolder
						if (view != null) {
							HorizontalScrollView hSView1 = (HorizontalScrollView) view
									.findViewById(R.id.hsv);
							hSView1.smoothScrollTo(0, 0);
						}
						view = v;
						// 获得HorizontalScrollView滑动的水平方向值.
						int scrollX = hSView.getScrollX();

						// 获得操作区域的长度
						int actionW = action.getWidth();
						System.out.println("scrollW " + actionW);
						System.out.println("scrollX " + scrollX);

						// 注意使用smoothScrollTo,这样效果看起来比较圆滑,不生硬
						// 如果水平方向的移动值<操作区域的长度的一半,就复原
						if (scrollX < actionW / 2) {
							hSView.smoothScrollTo(0, 0);
						} else// 否则的话显示操作区域
						{
							hSView.smoothScrollTo(actionW, 0);
						}
						break;
					}
					return false;
				}
			});
			// 这里防止删除一条item后,ListView处于操作状态,直接还原
			if (hSView.getScrollX() != 0) {
				hSView.scrollTo(0, 0);
			}
			return convertView;
		}

		@Override
		public void onClick(View v) {
			int position = (Integer) v.getTag();
			switch (v.getId()) {
			case R.id.ll_content:
				Intent intent = new Intent(context, Randomshow.class);
				String radnom_id = list.get(position).getId();
				intent.putExtra("random_id", radnom_id);
				intent.putExtra("isLoaded", true);
				context.startActivity(intent);
				break;
			case R.id.delete:
				onLocalUtil.deleteRandom(list.get(position).getId());
				list.remove(position);
				notifyDataSetChanged();
				if(list.size()<=0)
				{
					mHandler.sendEmptyMessage(NO_MESSAGE);
				}
				break;
			}
		}
	}
}