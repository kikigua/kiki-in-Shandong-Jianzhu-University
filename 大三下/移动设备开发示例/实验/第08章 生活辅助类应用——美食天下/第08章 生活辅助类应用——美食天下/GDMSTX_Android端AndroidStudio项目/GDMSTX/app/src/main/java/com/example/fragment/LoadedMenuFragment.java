package com.example.fragment;
import java.util.ArrayList;
import java.util.List;
import com.example.MyListView.MenuListItem;
import com.example.MyListView.MyListView;
import com.example.MyListView.MyListView.OnLoadedImage;
import com.example.MyListView.MyListView.OnRefreshListener;
import com.example.MySQLite.DatabaseUtil;
import com.example.activity.Menushow;
import com.example.activity.R;
import com.example.downLoader.ImageDownLoader;
import com.example.util.BitmapCache;
import com.example.util.Constant;
import com.example.util.OnLoadUtil;
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

public class LoadedMenuFragment extends Fragment {
	LinearLayout ll;
	MyListView lv;
	final int NO_MESSAGE = 0;
	final int THE_END = 1;
	final int LOAD = 2;
	Handler mHandler;
	List<MenuListItem> list;
	List<String[]> relist;
	LoadedMenuAdapter ba;
	ImageDownLoader imageDownLoader;
	String timeDiver=" datetime(CURRENT_TIMESTAMP,'localtime')";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mHandler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				switch (msg.what) {
				case NO_MESSAGE:
					String noNet = getActivity().getResources().getString(
							R.string.noMenuLoaded);
					noNetWork(noNet);
					break;
				case THE_END:
					TextView tv = (TextView) lv.findViewById(R.id.foot_tip);
					String end = getResources().getString(R.string.the_end);
					tv.setText(end);
					break;
				case LOAD:
					getItemList();
					ba.notifyDataSetChanged();
					lv.nowLoad=false;
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
		lv.setResable(false);
		imageDownLoader=new ImageDownLoader();
		list=new ArrayList<MenuListItem>();
		ba = new LoadedMenuAdapter(list, getActivity());
		lv.setAdapter(ba);
		lv.setonRefreshListener(new MyOnRefreshListener());
		lv.setOnLoadImage(
				new OnLoadedImage()
				{

					@Override
					public void onLoadImage() {
						int start=lv.getFirstVisiblePosition();
						int end=lv.getLastVisiblePosition();
						while(start<end&&start<list.size())
						{
							String prefix=list.get(start).getPic();
							ImageView iv=(ImageView) lv.findViewWithTag(prefix+start);
							imageDownLoader.thumbnailExcute(iv, list.get(start).getPic());
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

	private void getItemList() {
		timeDiver="'"+relist.get(relist.size()-1)[12]+"'";
		if (relist != null) {
			for (String[] str : relist) {
				MenuListItem item = new MenuListItem();
				item.setId(str[16]);
				item.setCollection(str[14]);
				item.setLike(str[13]);
				String[] ss = str[9].split("%");
				StringBuilder material = new StringBuilder();
				for (String s : ss) {
					material.append(s.split("\\|")[1] + "、");
				}
				item.setMaterial(material.toString());
				item.setName(str[2]);
				System.out.println("bm " + str[0]);
				item.setPic(str[0]);
				item.setPinglun(str[15]);
				item.setUid(str[2]);
				item.setUploadTime(str[12]);
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
					new LoreMore().start();
				}
			}, 500);
		}
	}

	class LoreMore extends Thread {
		@Override
		public void run() {
			try {
				relist = DatabaseUtil.getMenu(getActivity(),
						timeDiver);
				if(relist==null||relist.size()<=0)
				{
					if (list.size() <= 0) {
						mHandler.sendEmptyMessage(NO_MESSAGE);
					} else {
						mHandler.sendEmptyMessage(THE_END);
					}
				}else
				{
					mHandler.sendEmptyMessage(LOAD);
				}
				
			} catch (Exception e) {
				lv.nowLoad = false;
				e.printStackTrace();
			}
		}
	}
	public class LoadedMenuAdapter extends BaseAdapter implements OnClickListener {
		List<MenuListItem> list = new ArrayList<MenuListItem>();
		Context context;
		View view;
		public Boolean isInit = true;
		OnLoadUtil onLocalUtil;
		public LoadedMenuAdapter(List<MenuListItem> list, Context context) {
			this.list = list;
			this.context = context;
			onLocalUtil=new OnLoadUtil(context);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
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
		public View getView(final int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = LayoutInflater.from(context).inflate(
						R.layout.menu_listview_item, null);
			}

			final HorizontalScrollView hSView = (HorizontalScrollView) convertView
					.findViewById(R.id.hsv);
			View content = convertView.findViewById(R.id.ll_content);
			final View action = convertView.findViewById(R.id.ll_action);
			ImageView iv = (ImageView) convertView.findViewById(R.id.primaryPic);
			TextView cname = (TextView) convertView.findViewById(R.id.mname);
			TextView introduce = (TextView) convertView.findViewById(R.id.material);
			TextView like = (TextView) convertView.findViewById(R.id.like_number);
			TextView collection = (TextView) convertView
					.findViewById(R.id.collection_number);
			TextView pinglun = (TextView) convertView
					.findViewById(R.id.pinglun_number);
			TextView uploadTime = (TextView) convertView
					.findViewById(R.id.upload_time);
			Button delete = (Button) convertView.findViewById(R.id.delete);

			// 设置内容view的大小为屏幕宽度,这样按钮就正好被挤出屏幕外
			android.view.ViewGroup.LayoutParams lp = content.getLayoutParams();
			lp.width = Constant.ScreenWidth;
			delete.setTag(position);
			content.setTag(position);
			delete.setOnClickListener(this);
			content.setOnClickListener(this);

			Bitmap bitmap=BitmapCache.showCacheBitmap(list.get(position).getPic());
			if(bitmap!=null)
			{
				iv.setImageBitmap(bitmap);
			}
			else
			{
				iv.setImageResource(R.drawable.recipe_defult_img);
				if(isInit)
				{
					imageDownLoader.thumbnailExcute(iv, list.get(position).getPic());
				}
			}
			iv.setTag(list.get(position).getPic()+position);
			cname.setText(list.get(position).getName());
			introduce.setText(list.get(position).getMaterial());
			like.setText(list.get(position).getLike());
			collection.setText(list.get(position).getCollection());
			pinglun.setText(list.get(position).getPinglun());
			String time = list.get(position).getUploadTime();
			uploadTime.setText(time);

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
			case R.id.delete:
				onLocalUtil.deleteMenu(list.get(position).getId());
				list.remove(position);
				notifyDataSetChanged();
				if(list.size()<=0)
				{
					mHandler.sendEmptyMessage(NO_MESSAGE);
				}
				break;
			case R.id.ll_content:
				Intent intent = new Intent(context, Menushow.class);
				String menu_id = list.get(position).getId();
				intent.putExtra("menu_id", menu_id);
				intent.putExtra("isLoaded", true);
				context.startActivity(intent);
				break;
			}
		}
	}
	@Override
	public void onStop() {
		super.onStop();
		imageDownLoader.cancelTask();
	}
}