package com.example.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.Intent;
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
import com.example.MyListView.RandomListItem;
import com.example.MyListView.MyListView.OnRefreshListener;
import com.example.MyRoundedImageView.RoundImageView;
import com.example.activity.R;
import com.example.activity.Randomshow;
import com.example.downLoader.ImageDownLoader;
import com.example.util.Constant;
import com.example.util.NetInfoUtil;
import com.example.util.StrListChange;
import com.example.util.UploadUtil;
import com.example.util.Utils;

public class CollectionRandom extends Fragment {
	LinearLayout ll;
	ArrayList<RandomListItem> list;
	boolean flag;
	MyListView lv;
	final int NO_MESSAGE = 3;
	final int THE_END = 2;
	final int LOADED = 1;
	final int NO_NET = 0;
	Handler mHandler;
	CollectionRandomAdapter ba;
	List<String[]> relist;
	String lastId = Constant.NO_MESSAGE;
	ImageDownLoader imageDownLoader;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				switch (msg.what) {
				case NO_NET:
					String tip = getResources().getString(R.string.net_fail);
					noNetWork(tip);
					break;
				case LOADED:
					for (String[] str : relist) {
						RandomListItem li = new RandomListItem(str[0],
								str[1].split("%")[0], str[8], str[2],
								str[3], str[4], str[5], str[6], str[7]);
						list.add(li);
					}
					ba.notifyDataSetChanged();
					lv.nowLoad = false;
					break;
				case THE_END:
					TextView tv = (TextView) ll.findViewById(R.id.foot_tip);
					tv.setText("到底啦");
					break;
				case NO_MESSAGE:
					String noRandoC = getResources().getString(
							R.string.noRandomC);
					noNetWork(noRandoC);
					break;
				}
			}
		};
		imageDownLoader = new ImageDownLoader();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ll = (LinearLayout) inflater.inflate(R.layout.loaded_listview, null);
		lv = (MyListView) ll.getChildAt(0);
		list = new ArrayList<RandomListItem>();
		ba = new CollectionRandomAdapter(list, getActivity());
		lv.setAdapter(ba);
		lv.setonRefreshListener(new MyOnRefreshListener());
		lv.setResable(false);
		return ll;
	}

	public void onLoreMore(String rmeg) {
		if (!rmeg.equals(Constant.NO_MESSAGE)) {
			relist = StrListChange.StrToList(rmeg);
			lastId = relist.get(relist.size() - 1)[0];
			mHandler.sendEmptyMessage(LOADED);
		} else if (list.size() <= 0) {
			mHandler.sendEmptyMessage(NO_MESSAGE);
		} else {
			mHandler.sendEmptyMessage(THE_END);
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

	class LoreMore extends Thread {
		@Override
		public void run() {
			try {
				String rmeg = NetInfoUtil.getRandomC(lastId,
						Utils.getUser(getActivity())[0]);
				onLoreMore(rmeg);
			} catch (Exception e) {
				e.printStackTrace();
				lv.nowLoad = false;
			}
		}
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
			if (!Utils.isNewWork(getActivity())) {
				mHandler.sendEmptyMessage(NO_NET);
				return;
			}
			lv.nowLoad = true;
			mHandler.postDelayed(new Runnable() {
				@Override
				public void run() {
					new LoreMore().start();
				}
			}, 500);
		}
	}

	public class CollectionRandomAdapter extends BaseAdapter implements
			OnClickListener {
		private ArrayList<RandomListItem> list;
		private Context context;
		View view;

		public CollectionRandomAdapter(ArrayList<RandomListItem> list,
				Context context) {
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
		public View getView(final int position, View convertView,
				ViewGroup parent) {
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
				Button delete = (Button) convertView
						.findViewById(R.id.delete);
				// 设置内容view的大小为屏幕宽度,这样按钮就正好被挤出屏幕外
				 content = convertView.findViewById(R.id.ll_content);
				android.view.ViewGroup.LayoutParams lp = content
						.getLayoutParams();
				lp.width = Constant.ScreenWidth;
			imageDownLoader.thumbnailExcute(iv, list.get(position)
					.getImg());
			imageDownLoader.roundImageViewExcute(head, list.get(position)
					.getHead());
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
					case MotionEvent.ACTION_MOVE:
						break;
					case MotionEvent.ACTION_UP:
						// 获得ViewHolder
						if (view != null) {
							HorizontalScrollView hSView =(HorizontalScrollView) view.findViewById(R.id.hsv);
							hSView.smoothScrollTo(0, 0);
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
				intent.putExtra("isLoaded", false);
				context.startActivity(intent);
				break;
			case R.id.delete:
				System.out.println("delete menu");
				new UploadUtil(context).cancelRandomcC(
						Utils.getUser(context)[0], list.get(position).getId());
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

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		imageDownLoader.cancelTask();
	}
}