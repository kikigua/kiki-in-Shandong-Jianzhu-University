package com.example.activity;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.ToggleButton;
import com.example.util.Constant;
import com.example.util.MyToast;
import com.king.photo.adapter.AlbumGridViewAdapter;
import com.king.photo.util.Bimp;
import com.king.photo.util.ImageBucket;
import com.king.photo.util.ImageItem;
import com.king.photo.util.PublicWay;
import com.king.photo.zoom.PhotoView;
import com.king.photo.zoom.ViewPagerFixed;
/**
 * 这个是用于进行图片浏览时的界面
 * 
 * @author king
 * @QQ:595163260
 * @version 2014年10月18日 下午11:47:53
 */
public class GalleryActivity extends Activity {
	private Intent intent;
	// 返回按钮
	private Button back_bt;
	// 发送按钮
	private Button send_bt;
	// 删除按钮
	private ToggleButton sel_bt;
	// 获取前一个activity传过来的position
	private int position;
	// 当前的位置
	private int location = 0;
	// 暂时要删除的图片
	private ViewPagerFixed pager;
	private ViewPagerFixedAdapter adapter;
	int index_CurrentBucket;
	ArrayList<ImageItem> list = null;// 当前页面item
	RelativeLayout photo_relativeLayout;
	MyToast toast;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.plugin_camera_gallery);// 切屏到主界面
		PublicWay.activityList.add(this);
		toast=new MyToast(this);
		back_bt = (Button) findViewById(R.id.gallery_back);
		send_bt = (Button) findViewById(R.id.send_button);
		sel_bt = (ToggleButton) findViewById(R.id.toggle_select);
		back_bt.setOnClickListener(new BackListener());
		send_bt.setOnClickListener(new GallerySendListener());
		sel_bt.setOnCheckedChangeListener(new SelListener());
		intent = getIntent();
		Bundle bundle = intent.getExtras();
		position = intent.getIntExtra("position", 0);
		pager = (ViewPagerFixed) findViewById(R.id.gallery01);
		pager.setOnPageChangeListener(pageChangeListener);
		index_CurrentBucket = bundle.getInt("index_CurrentBucket", -1);
		list = new ArrayList<ImageItem>();
		// 预览已选择的
		if (index_CurrentBucket == PublicWay.SELECT_PREVIEW) {
			list.addAll(Bimp.tempSelectBitmap);
			sel_bt.setChecked(true);
		}// 预览所有图片
		else if(index_CurrentBucket == PublicWay.ALL_PIC_PREVIEW)
		{
			list.addAll(AlbumActivity.dataList);
			if (Bimp.tempSelectBitmap.contains(list.get(position)))
				sel_bt.setChecked(true);
			
		}
		adapter = new ViewPagerFixedAdapter(this,list);
		pager.setAdapter(adapter);
		pager.setPageMargin((int) getResources().getDimensionPixelOffset(
				R.dimen.ui_10_dip));
		pager.setCurrentItem(position);
		isShowOkBt();
	}

	private OnPageChangeListener pageChangeListener = new OnPageChangeListener() {

		public void onPageSelected(int arg0) {
			location = arg0;
			if (Bimp.tempSelectBitmap.contains(list.get(location))) {
				sel_bt.setChecked(true);
			} else {
				sel_bt.setChecked(false);
			}
		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		public void onPageScrollStateChanged(int arg0) {

		}
	};
	// 返回按钮添加的监听器
	private class BackListener implements OnClickListener {

		public void onClick(View v) {
			Intent intent = new Intent("data.broadcast.action");
			sendBroadcast(intent);
			finish();
			overridePendingTransition(R.anim.fade, R.anim.hold);

		}
	}

	// 选择按钮添加的监听器
	private class SelListener implements OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
			
			if (arg1) {
				if (Bimp.tempSelectBitmap.contains(list.get(location))) {
					return;
				}
				if(Bimp.tempSelectBitmap.size()<PublicWay.num)
				{
					Bimp.tempSelectBitmap.add(list.get(location));
					if(PublicWay.num==Bimp.SINGLE_PIC)
					{
						send_bt.performClick();
						return;
					}
				}else
				{
					toast.showToast(getResources().getString(R.string.only_choose_num));
					sel_bt.setChecked(false);
				}
				isShowOkBt();
			} else {
				if (Bimp.tempSelectBitmap.contains(list.get(location))) {
					Bimp.tempSelectBitmap.remove(list.get(location));
					isShowOkBt();
				}

			}
		}

	}

	// 完成按钮的监听
	private class GallerySendListener implements OnClickListener {
		public void onClick(View v) {
			Intent intent = new Intent("data.broadcast.action");
			sendBroadcast(intent);
			finish();
			overridePendingTransition(R.anim.fade, R.anim.hold);
		}
	}

	public void isShowOkBt() {
		if (Bimp.tempSelectBitmap.size() > 0) {
			send_bt.setText(getResources().getString(R.string.finish) + "("
					+ Bimp.tempSelectBitmap.size() + "/" + PublicWay.num + ")");
			send_bt.setPressed(true);
			send_bt.setClickable(true);
			send_bt.setTextColor(Color.WHITE);
		} else {
			send_bt.setPressed(false);
			send_bt.setClickable(false);
			send_bt.setTextColor(Color.parseColor("#E1E0DE"));
		}
	}

	/**
	 * 监听返回按钮
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = new Intent("data.broadcast.action");
			sendBroadcast(intent);
			finish();
			overridePendingTransition(R.anim.fade, R.anim.hold);

		}
		return true;
	}	
	private class ViewPagerFixedAdapter extends PagerAdapter {

		List<ImageItem> list;
		Context context;
		public ViewPagerFixedAdapter(Context context,List<ImageItem> list) {
			this.context=context;
			this.list=list;
		}
		public int getCount() {
			return list.size();
		}

		public int getItemPosition(Object object) {
			return POSITION_NONE;
		}

		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPagerFixed) arg0).removeView((View) arg2);

		}
		public Object instantiateItem(View arg0, int arg1) {
			
			PhotoView pv=new PhotoView(context);
			try {
				pv.setBackgroundColor(0xff000000);
				pv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
						LayoutParams.MATCH_PARENT));
				ImageItem item=list.get(arg1);
				Bimp.displayPVBmpfromFile(pv,item.getImagePath());			
				((ViewPagerFixed) arg0).addView(pv);			
			} catch (Exception e) {
				e.printStackTrace();
			}
			return pv;
		}
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}
	}

}
