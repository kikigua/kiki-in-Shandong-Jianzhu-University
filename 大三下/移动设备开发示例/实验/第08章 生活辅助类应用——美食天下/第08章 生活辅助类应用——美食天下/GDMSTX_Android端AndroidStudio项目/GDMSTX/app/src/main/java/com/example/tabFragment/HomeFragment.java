package com.example.tabFragment;

import java.util.ArrayList;
import java.util.List;
import com.example.MyGridView.MyGridView;
import com.example.activity.MenuChooseListActivity;
import com.example.activity.Menushow;
import com.example.activity.R;
import com.example.activity.RandomListViewActivity;
import com.example.activity.Randomshow;
import com.example.activity.UploadActivity;
import com.example.activity.UploadPaiActivity;
import com.example.adpter.ViewPagerAdapter;
import com.example.downLoader.ImageDownLoader;
import com.example.util.Constant;
import com.example.util.MyToast;
import com.example.util.NetInfoUtil;
import com.example.util.Utils;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.pull_to_refresh.PullToRefreshView;
import com.example.pull_to_refresh.PullToRefreshView.OnHeaderRefreshListener;
import com.king.photo.util.Bimp;

public class HomeFragment extends Fragment implements OnClickListener,
OnHeaderRefreshListener {
	private ViewPager viewPager; // /广告栏
	private LinearLayout ll_point; // /提示点的布局
	private FrameLayout frameLayout;
	private int frameheight; // ViewPager父布局的高
	private ArrayList<ImageView> imageViews; // 广告栏中点对象
	private TextView tip; // 广告栏中的文字提示对象
	private View headview; // /ListView的HeaderView对象
	private int newPoint; // /当前图片索引
	private List<String[]> infos; // viewPager里的推荐信息 编号，名字 ，介绍
	String timediver = "now()"; // 查询时间点
	private Handler mHandler; // //handlrer更新
	final int DISCON = 0;	//断网
	final int CLOCK = 4; // 定时器
	final int REFRESH = 5;	//刷新
	List<String[]> menuInfo; // 菜谱推荐信息
	List<String[]> randomInfo;// 随拍推荐信息
	ImageDownLoader imageLoader;
	private TextView pic_random; // 中相机的父布局
	private TextView menu_add;
	private MyGridView menu_ll;
	private MyGridView random_ll;
	private TextView more_menu;
	private TextView more_random;
	private PullToRefreshView mPullToRefreshView;
	private FrameLayout fl;
	private ProgressBar pb;
	private LinearLayout again_ll;
	MyToast toast;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				switch (msg.what) {
				case CLOCK: // 按时走广告
					viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
					break;
				case REFRESH:		//刷新
					pb.setVisibility(View.VISIBLE);
					again_ll.setVisibility(View.GONE);
					mPullToRefreshView.onHeaderRefreshComplete();
					initHead(); // 刷新Viewpager
					initExcellentMenu();
					initExcellentRandom();	
					fl.setVisibility(View.GONE);
					break;
				case DISCON:
					toast.showToast("网络已中断,请联网后重试");
					fl.setVisibility(View.VISIBLE);
					pb.setVisibility(View.GONE);
					again_ll.setVisibility(View.VISIBLE);
					break;
				}
			}
		};
		new Thread() {
			public void run() {
				while (true) {
					try {
						sleep(2000);
						mHandler.sendEmptyMessage(CLOCK);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		FrameLayout  home_ll=(FrameLayout) inflater.inflate(R.layout.home,
				null);
		toast=new MyToast(getActivity());
		imageLoader = new ImageDownLoader();
		mPullToRefreshView = (PullToRefreshView)home_ll.findViewById(R.id.main_pull_refresh_view); 
		mPullToRefreshView.lockFooter();	//锁住上拉
		mPullToRefreshView.lockHead();
		headview=home_ll.findViewById(R.id.headPager);
		viewPager = (ViewPager) headview.findViewById(R.id.viewpager);
		ll_point = (LinearLayout) headview.findViewById(R.id.ll_point);
		tip = (TextView) headview.findViewById(R.id.viewPager_tip);
		frameLayout = (FrameLayout) headview.findViewById(R.id.fl_main);
		pic_random = (TextView) mPullToRefreshView.findViewById(R.id.add_random);
		pic_random.setOnClickListener(this);
		menu_ll = (MyGridView) mPullToRefreshView.findViewById(R.id.menu_ll);
		random_ll = (MyGridView) mPullToRefreshView.findViewById(R.id.random_ll);
		menu_add=(TextView) mPullToRefreshView.findViewById(R.id.add_menu);
		menu_add.setOnClickListener(this);
		more_menu=(TextView) mPullToRefreshView.findViewById(R.id.more_menu);
		more_menu.setOnClickListener(this);
		more_random=(TextView) mPullToRefreshView.findViewById(R.id.more_random);
		more_random.setOnClickListener(this);
		mPullToRefreshView = (PullToRefreshView) mPullToRefreshView.findViewById(R.id.main_pull_refresh_view);
		mPullToRefreshView.setOnHeaderRefreshListener(this);
		again_ll=(LinearLayout) home_ll.findViewById(R.id.again_ll);
		pb=(ProgressBar) home_ll.findViewById(R.id.pb);
		fl=(FrameLayout) home_ll.findViewById(R.id.home_nonet);
		Button again=(Button) home_ll.findViewById(R.id.nonet_again);
		again.setOnClickListener(
				new OnClickListener()
				{
					@Override
					public void onClick(View v) {
						if (Utils.isNewWork(getActivity())) {
							new Refresh().start();
						}else
						{
							mHandler.sendEmptyMessage(DISCON);
						}
					}					
				});
		again.performClick();
		return home_ll;
	}

	public void initHead() {
		// 加载vipager中的图片
		frameheight = 380;
		// 加载vipager上层的信息
		initHeadImage();
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int arg0) {
				imageViews.get(newPoint).setImageResource(R.drawable.indicator);
				int count = infos.size();
				newPoint = count > 0 ? arg0 % infos.size() : 0;
				imageViews.get(newPoint).setImageResource(
						R.drawable.indicator_focused);
				tip.setText(infos.get(newPoint)[2]);
			}
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}
			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}

	/***
	 * 初始化 HeadImage的信息
	 */
	void initHeadImage() {
		initPoint();
		LayoutParams layoutParams = frameLayout.getLayoutParams();
		layoutParams.height = frameheight;
		frameLayout.setLayoutParams(layoutParams);
		viewPager.setAdapter(new ViewPagerAdapter(getActivity(), infos,imageLoader));
		viewPager.setCurrentItem(100*infos.size());
		imageViews.get(0).setImageResource(R.drawable.indicator_focused);
		tip.setText(infos.get(0)[2]);
	}

	/***
	 * 初始化 point
	 */
	void initPoint() {
		imageViews = new ArrayList<ImageView>();
		ImageView imageView;
		if (ll_point.getChildCount() > 0)
			ll_point.removeAllViews();
		for (int i = 0; i < infos.size(); i++) {
			imageView = new ImageView(getActivity());
			imageView.setBackgroundResource(R.drawable.indicator);
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
					new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT,
							LayoutParams.WRAP_CONTENT));
			layoutParams.leftMargin = 10;
			layoutParams.rightMargin = 10;
			ll_point.addView(imageView, layoutParams);
			imageViews.add(imageView);
		}
	}

	// /刷新线程
	private class Refresh extends Thread {
		@Override
		public void run() {
			try {
				timediver = "now()";
				infos = NetInfoUtil.getRecommend();
				menuInfo = NetInfoUtil.getExcellentMenu();
				randomInfo=NetInfoUtil.getExcellentRandom();
				mHandler.sendEmptyMessage(REFRESH);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	//加载精品菜谱
	private void initExcellentMenu()
	{
		BaseAdapter ba=new BaseAdapter()
		{
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return menuInfo.size();
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
			public View getView(int position, View convertView, ViewGroup parent) {
				String[] strs=menuInfo.get(position);
				String name=strs[1];
				String primaryPic=strs[2];
				String like=strs[3];
				String collection=strs[4];
				String pinglun=strs[5];				
				LinearLayout menu_item=(LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.home_menu_item, null);
				int width=Utils.getScreenWidth(getActivity())*7/15;
				LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(width, width);
				ImageView iv=(ImageView) menu_item.findViewById(R.id.image);
				iv.setLayoutParams(params);
				imageLoader.thumbnailExcute(iv, primaryPic);
				TextView cname=(TextView) menu_item.findViewById(R.id.name);
				cname.setText(name);
				TextView slike=(TextView) menu_item.findViewById(R.id.like);
				slike.setText(like);
				TextView coll=(TextView) menu_item.findViewById(R.id.collection);
				coll.setText(collection);
				TextView ping=(TextView) menu_item.findViewById(R.id.pinglun);
				ping.setText(pinglun);				
				return menu_item;
			}
			
		};
		menu_ll.setAdapter(ba);
		menu_ll.setSelector(new ColorDrawable(Color.TRANSPARENT));
		menu_ll.setOnItemClickListener(
				new OnItemClickListener()
				{
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						String[] strs=menuInfo.get(arg2);
						String id=strs[0];
						Intent intent = new Intent(getActivity(),
								Menushow.class);
						intent.putExtra("menu_id", id);
						startActivity(intent);												
					}					
				});
	}
	//加载精品随拍
	private void initExcellentRandom()
	{
		BaseAdapter ba=new BaseAdapter()
		{

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return randomInfo.size();
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
			public View getView(int position, View convertView, ViewGroup parent) {
				String[] strs=randomInfo.get(position);
				String introduce=strs[1];
				String primaryPic=strs[2].split("%")[0];
				String like=strs[3];
				String collection=strs[4];
				String pinglun=strs[5];
				LinearLayout menu_item=(LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.home_menu_item, null);
				int width=Utils.getScreenWidth(getActivity())*7/15;
				LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(width, width);
				ImageView iv=(ImageView) menu_item.findViewById(R.id.image);
				iv.setLayoutParams(params);
				imageLoader.thumbnailExcute(iv, primaryPic);
				TextView intr=(TextView) menu_item.findViewById(R.id.name);
				intr.setText(introduce);
				TextView slike=(TextView) menu_item.findViewById(R.id.like);
				slike.setText(like);
				TextView coll=(TextView) menu_item.findViewById(R.id.collection);
				coll.setText(collection);
				TextView ping=(TextView) menu_item.findViewById(R.id.pinglun);
				ping.setText(pinglun);
				return menu_item;
			}
			
		};
		random_ll.setAdapter(ba);
		random_ll.setSelector(new ColorDrawable(Color.WHITE));
		random_ll.setOnItemClickListener(
				new OnItemClickListener()
				{
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						String[] strs=randomInfo.get(arg2);
						String id=strs[0];
						Intent intent = new Intent(getActivity(),
								Randomshow.class);
						intent.putExtra("random_id", id);
						startActivity(intent);						
					}					
				});
	}
	@Override
	public void onClick(View v) {
		if (v == pic_random) {
			Bimp.tempSelectBitmap.clear();
			Intent intent = new Intent(getActivity(), UploadPaiActivity.class);
			startActivity(intent);
		}
		if(v==menu_add)
		{
			Bimp.tempSelectBitmap.clear();
			Intent intent=new Intent();
			intent.setClass(getActivity(), UploadActivity.class);
        	startActivity(intent);
		}
		if(v==more_random)
		{
			Intent intent = new Intent(getActivity(), RandomListViewActivity.class);
			intent.putExtra("title", "更多幸福");
			intent.putExtra("type", Constant.RANDOM_LIKE[0]);
			startActivity(intent);
		}
		if(v==more_menu)
		{
			Intent intent=new Intent(getActivity(),MenuChooseListActivity.class);
			intent.putExtra("title", "更多精品菜谱"	);
			intent.putExtra("type",Constant.MENU_LIKE[0]);
			intent.putExtra("args", Constant.MENU_ARGS[0][0]);
			startActivity(intent);
		}
	}
	@Override
	public void onHeaderRefresh(PullToRefreshView view) {
		mPullToRefreshView.postDelayed(new Runnable() {
			@Override
			public void run() {
				if (Utils.isNewWork(getActivity())) {
					new Refresh().start();
				}else
				{
					mHandler.sendEmptyMessage(DISCON);
				}				
			}
		}, 1000);
	}	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		imageLoader.cancelTask();
	}
}