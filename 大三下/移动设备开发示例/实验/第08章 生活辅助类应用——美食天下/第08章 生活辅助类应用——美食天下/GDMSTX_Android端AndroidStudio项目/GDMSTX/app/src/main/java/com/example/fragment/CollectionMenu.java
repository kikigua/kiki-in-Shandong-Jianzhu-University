package com.example.fragment;

import java.util.ArrayList;
import java.util.List;

import com.example.MyListView.MenuListItem;
import com.example.MyListView.MyListView;
import com.example.MyListView.MyListView.OnRefreshListener;
import com.example.activity.Menushow;
import com.example.activity.R;
import com.example.downLoader.ImageDownLoader;
import com.example.util.Constant;
import com.example.util.NetInfoUtil;
import com.example.util.StrListChange;
import com.example.util.UploadUtil;
import com.example.util.Utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager.LayoutParams;
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

public class CollectionMenu extends Fragment {
	MyListView lv;
	final int END = 2; // 到底啦
	final int LOADED = 1; // 加载啦
	final int NO_NET = 0; // 没有网络
	final int NO_MESSAGE = 3;// 没有收藏
	Handler mHandler;
	List<MenuListItem> list;
	LinearLayout ll;
	CollectionMenuAdapter ba;
	List<String[]> strList;
	String lastId=Constant.NO_MESSAGE;
	ImageDownLoader imageDownLoader;
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
				case NO_NET:
					String noNet = getResources().getString(R.string.net_fail);
					noNetWork(noNet);
					break;
				case LOADED:
					onLoareMore();
					ba.notifyDataSetChanged();
					lv.nowLoad=false;
					break;
				case END:
					TextView tv = (TextView) lv.findViewById(R.id.foot_tip);
					tv.setText("到底啦");
					break;
				case NO_MESSAGE:
					String noMessage = getResources().getString(
							R.string.noMenuC);
					noNetWork(noMessage);
					break;
				}
			}
		};
		imageDownLoader=new ImageDownLoader();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ll = (LinearLayout) inflater.inflate(R.layout.loaded_listview, null);
		lv = (MyListView) ll.getChildAt(0);
		lv.setonRefreshListener(new MyOnRefreshListener());
		list = new ArrayList<MenuListItem>();
		ba = new CollectionMenuAdapter(list, getActivity(),imageDownLoader);
		lv.setAdapter(ba);
		lv.setResable(false);
		return ll;
	}

	private void onLoareMore()
	{
		for (String[] str : strList) {
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
				String[] content = s.split("\\|");
				material.append(content[content.length - 1] + "、");
			}
			item.setMaterial(material.toString().trim());
			item.setCollection(str[6]);
			item.setPinglun(str[7]);
			item.setUploadTime(str[8]);
			list.add(item);
		}
		lastId=strList.get(strList.size()-1)[0];
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

	class LoreMore extends Thread {
		@Override
		public void run() {
			try {
				String str = NetInfoUtil
						.getMenuC(lastId,Utils.getUser(getActivity())[0]);
				strList = StrListChange.StrToList(str);
				if (!strList.get(0)[0].equals(Constant.NO_MESSAGE)) {			
					mHandler.sendEmptyMessage(LOADED);
				} else if (list.size() > 0) {
					mHandler.sendEmptyMessage(END);
				} else {
					mHandler.sendEmptyMessage(NO_MESSAGE);
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
		imageDownLoader.cancelTaskNow();
	}
	private class CollectionMenuAdapter extends BaseAdapter implements OnClickListener {
		List<MenuListItem> list = new ArrayList<MenuListItem>();
		Context context;
		View view;
		public CollectionMenuAdapter(List<MenuListItem> list, Context context,ImageDownLoader imageDownLoader) {
			this.list = list;
			this.context = context;
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
			final HorizontalScrollView hSView = (HorizontalScrollView) convertView.findViewById(R.id.hsv);
				View content=convertView.findViewById(R.id.ll_content);
				final View action=convertView.findViewById(R.id.ll_action);
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
				Button delete = (Button) convertView
						.findViewById(R.id.delete);
				
				 // 设置内容view的大小为屏幕宽度,这样按钮就正好被挤出屏幕外
				content = convertView.findViewById(R.id.ll_content);
	            android.view.ViewGroup.LayoutParams lp = content.getLayoutParams();
	            lp.width = Constant.ScreenWidth;


			delete.setTag(position);
			content.setTag(position);
			delete.setOnClickListener(this);
			content.setOnClickListener(this);
			
			imageDownLoader.thumbnailExcute(iv, list.get(position).getPic());
			cname.setText(list.get(position).getName());
			introduce.setText(list.get(position).getMaterial());
			like.setText(list.get(position).getLike());
			collection.setText(list.get(position).getCollection());
			pinglun.setText(list.get(position).getPinglun());
			String time = list.get(position).getUploadTime();
			int indexOf = time.lastIndexOf(":");
			uploadTime.setText(time.substring(0, indexOf));

			convertView.setOnTouchListener(
	        		new View.OnTouchListener()
	        {
	            @Override
	            public boolean onTouch(View v, MotionEvent event)
	            {
	                switch (event.getAction())
	                {
	                    case MotionEvent.ACTION_DOWN:
	                    case MotionEvent.ACTION_MOVE:                 
	                    	break;
	                    case MotionEvent.ACTION_UP:
	                        // 获得ViewHolder
	                        if (view != null) {
	                        	HorizontalScrollView hSView=(HorizontalScrollView) view.findViewById(R.id.hsv);
	                            hSView.smoothScrollTo(0, 0);
	                        }
	                        view = v;
	                        // 获得HorizontalScrollView滑动的水平方向值.
	                        int scrollX = hSView.getScrollX();

	                        // 获得操作区域的长度
	                        int actionW = action.getWidth();
	                        System.out.println("scrollW "+actionW);
	                        System.out.println("scrollX "+scrollX);

	                        // 注意使用smoothScrollTo,这样效果看起来比较圆滑,不生硬
	                        // 如果水平方向的移动值<操作区域的长度的一半,就复原
	                        if (scrollX < actionW / 2)
	                        {
	                            hSView.smoothScrollTo(0, 0);
	                        }
	                        else// 否则的话显示操作区域
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
			int position=(Integer) v.getTag();
			switch(v.getId())
			{
			case R.id.delete:
				new UploadUtil(context).cancelMenuC(Utils.getUser(context)[0], list.get(position).getId());
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
				intent.putExtra("isLoaded", false);
				context.startActivity(intent);
				break;
			}		
		}	
	}
}