package com.example.activity;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.example.util.MyToast;
import com.king.photo.adapter.AlbumGridViewAdapter;
import com.king.photo.adapter.FileAdapter;
import com.king.photo.util.AlbumHelper;
import com.king.photo.util.Bimp;
import com.king.photo.util.ImageBucket;
import com.king.photo.util.ImageItem;
import com.king.photo.util.PublicWay;
/**
 * 进入相册显示所有图片的界面
 */
public class AlbumActivity extends Activity {
	// 显示手机里的所有图片的列表控件
	private GridView gridView;
	// 当手机里没有图片时，提示用户没有图片的控件
	private TextView tv;
	// gridView的adapter
	private AlbumGridViewAdapter gridImageAdapter;
	// 完成按钮
	private Button okButton;
	// 返回按钮
	private Button back;
	// 文件选择按钮
	private Button select;
	private Intent intent;
	// 预览按钮
	private Button preview;
	private Context mContext;
	// 所有文件夹及文件夹下的图片
	public static List<ImageBucket> contentList;
	// 当前屏幕上的所有图片
	public static List<ImageItem> dataList;
	private AlbumHelper helper;
	private View parentView;
	PopupWindow pop;
	ListView lv;
	int requestCode;
	Boolean isPai;
	Bitmap bitmap;
	MyToast toast;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		parentView = getLayoutInflater().inflate(
				R.layout.plugin_camera_album, null);
		setContentView(parentView);
		PublicWay.activityList.add(this);
		mContext = this;
		toast=new MyToast(this);
		// 注册一个广播，这个广播主要是用于在GalleryActivity进行预览时，防止当所有图片都删除完后，再回到该页面时被取消选中的图片仍处于选中状态
		IntentFilter filter = new IntentFilter("data.broadcast.action");
		registerReceiver(broadcastReceiver, filter);
		bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.plugin_camera_no_pictures);
		init();
		initListener();
		// 这个函数主要用来控制预览和完成按钮的状态
		isShowOkBt();
	}

	BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			gridImageAdapter.notifyDataSetChanged();
		}
	};

	// 预览按钮的监听
	private class PreviewListener implements OnClickListener {
		public void onClick(View v) {
			if (Bimp.tempSelectBitmap.size() > 0) {
				intent.putExtra("index_CurrentBucket", PublicWay.SELECT_PREVIEW);				
				intent.putExtra("position", 0);
				intent.setClass(AlbumActivity.this, GalleryActivity.class);
				startActivity(intent);
			}
		}

	}

	// 完成按钮的监听
	private class AlbumSendListener implements OnClickListener {
		public void onClick(View v) {
			finish();
			overridePendingTransition(R.anim.activity_translate_in,
					R.anim.activity_translate_out);
			
		}

	}

	// 返回按钮监听
	private class BackListener implements OnClickListener {
		public void onClick(View v) {			
			finish();
			overridePendingTransition(R.anim.activity_translate_in,
					R.anim.activity_translate_out);
		}
	}

	// 取消按钮的监听
	private class CancelListener implements OnClickListener {
		public void onClick(View v) {
			lv.startAnimation(AnimationUtils.loadAnimation(AlbumActivity.this,
					R.anim.activity_translate_in));
			pop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
		}
	}

	// 初始化，给一些对象赋值
	private void init() {		
		helper = AlbumHelper.getHelper();
		helper.init(this);
		contentList = helper.getImagesBucketList(false);
		dataList = new ArrayList<ImageItem>();
		for (int i = 0; i < contentList.size(); i++) {
			dataList.addAll(contentList.get(i).imageList);
		}

		back = (Button) findViewById(R.id.back);
		select = (Button) findViewById(R.id.select);
		select.setOnClickListener(new CancelListener());
		back.setOnClickListener(new BackListener());
		preview = (Button) findViewById(R.id.preview);

		preview.setOnClickListener(new PreviewListener());
		intent = getIntent();
		gridView = (GridView) findViewById(R.id.myGrid);
		gridView.setOnScrollListener(new MyOnScrollListener());
		gridImageAdapter = new AlbumGridViewAdapter(this, dataList,
				Bimp.tempSelectBitmap);
		gridView.setAdapter(gridImageAdapter);
		tv = (TextView) findViewById(R.id.myText);
		gridView.setEmptyView(tv);
		okButton = (Button) findViewById(R.id.ok_button);
		okButton.setText(R.string.finish + "("
				+ Bimp.tempSelectBitmap.size() + "/" + PublicWay.num + ")");
		pop = new PopupWindow(this);

		View view = getLayoutInflater().inflate(
				R.layout.item_picfile_popupwindows, null);

		lv = (ListView) view.findViewById(R.id.lv_pop);
		lv.setAdapter(new FileAdapter(this));
		lv.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				ImageBucket bucket=contentList.get(arg2);
				dataList.clear();
				dataList.addAll(bucket.imageList);	
				select.setText(bucket.bucketName);
				gridImageAdapter.notifyDataSetChanged();
				pop.dismiss();
				lv.clearAnimation();				
			}
		
		});
		pop.setWidth(LayoutParams.MATCH_PARENT);
		pop.setHeight(LayoutParams.WRAP_CONTENT);
		pop.setBackgroundDrawable(new BitmapDrawable());
		pop.setFocusable(true);
		pop.setOutsideTouchable(true);
		pop.setContentView(view);
		RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent);
		parent.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pop.dismiss();
				lv.clearAnimation();
			}
		});
	}

	private void initListener() {
		gridImageAdapter
				.setOnItemClickListener(new AlbumGridViewAdapter.OnItemClickListener() {

					@Override
					public void onItemClick(final ToggleButton toggleButton,
							int position, boolean isChecked,ImageView iv,ImageView dis) {
						if (isChecked) {
							if(Bimp.tempSelectBitmap.size() < PublicWay.num)
							{
								Bimp.tempSelectBitmap.add(dataList.get(position));
								if(PublicWay.num==Bimp.SINGLE_PIC)
								{
									okButton.performClick();
									return;
								}
								iv.setVisibility(View.VISIBLE);
								dis.setImageResource(R.drawable.ab9);
							}
							else if(Bimp.tempSelectBitmap.size()>=PublicWay.num)
							{
								toast.showToast("超过可选张数");
							}
							
						} else {
							if(Bimp.tempSelectBitmap.contains(dataList.get(position)))
							{
								Bimp.tempSelectBitmap.remove(dataList.get(position));
							}
							iv.setVisibility(View.GONE);
							dis.setImageResource(R.drawable.ab8);
						}
						isShowOkBt();
					}
				});

		okButton.setOnClickListener(new AlbumSendListener());

	}
	public void isShowOkBt() {
		okButton.setText(getResources().getString(R.string.finish) + "("
				+ Bimp.tempSelectBitmap.size() + "/" + PublicWay.num + ")");
		if (Bimp.tempSelectBitmap.size() > 0) {			
			preview.setPressed(true);
			okButton.setPressed(true);
			preview.setClickable(true);
			okButton.setClickable(true);
			okButton.setTextColor(Color.WHITE);
			preview.setTextColor(Color.WHITE);
		} else {			
			preview.setPressed(false);
			preview.setClickable(false);
			okButton.setPressed(false);
			okButton.setClickable(false);
			okButton.setTextColor(Color.parseColor("#E1E0DE"));
			preview.setTextColor(Color.parseColor("#E1E0DE"));
		}
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			overridePendingTransition(R.anim.hold,
					R.anim.fade);
			finish();

		}
		return false;
	}

	@Override
	protected void onRestart() {
		isShowOkBt();
		helper.hasBuildImagesBucketList=false;
		super.onRestart();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (broadcastReceiver != null) {
			this.unregisterReceiver(broadcastReceiver);
			broadcastReceiver = null;
		}		
	}

	@Override
	protected void onStop() {
		Bimp.cancelTask();
		
		super.onStop();
	}
	private class MyOnScrollListener implements OnScrollListener
	{
		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
			if(scrollState!=OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
			{
				int start=gridView.getFirstVisiblePosition();
				int end=gridView.getLastVisiblePosition();
				gridImageAdapter.isInit=false;
				System.out.println("start end "+start+" "+end);
				while(start<=end&&start<dataList.size())
				{
					ImageItem item=dataList.get(start);
					ImageView iv=(ImageView) gridView.findViewWithTag(item.imagePath);
					Bimp.displayBmp(iv, item.thumbnailPath, item.imagePath, item);
					start++;
				}
			}			
		}		
	}
}