package com.example.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import com.example.MyListView.MyListView;
import com.example.MyListView.MyListView.OnLoadedImage;
import com.example.adpter.PinglunAdapter;
import com.example.chat.ChatBottomLayout;
import com.example.chat.ChatUtils;
import com.example.chat.ListItem;
import com.example.downLoader.ImageDownLoader;
import com.example.util.Constant;
import com.example.util.NetInfoUtil;
import com.example.util.UploadUtil;
import com.example.util.Utils;
import com.king.photo.util.Bimp;
import com.king.photo.util.PublicWay;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager.LayoutParams;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
public class Pinglun extends Activity implements OnClickListener {
	InputMethodManager imm;
	PopupWindow pop;
	String picPath;
	String id;
	EditText et;
	Boolean isMenu = true;
	MyListView lv;
	List<ListItem> list;
	Handler mHandler;
	PinglunAdapter ba;
	String timediver = "now()";
	final int REFRESH = 0;
	final int LOAD = 1;
	final int NO_MESSAGE = 3;
	final int THE_END = 4;
	final int NO_NET = 6;
	ImageDownLoader imageDownLoader;
	UploadUtil utils;
	ChatBottomLayout viewll;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.pinglun);
		Bundle bundle = this.getIntent().getExtras();
		if (null == (id = bundle.getString("menuId", null))) {
			id = bundle.getString("randomId");
			isMenu = false;
		}
		utils = new UploadUtil(this);
		viewll=(ChatBottomLayout) this.findViewById(R.id.pinglun_include);
		imageDownLoader = new ImageDownLoader();
		ImageButton back=(ImageButton) this.findViewById(R.id.btback);
		back.setOnClickListener(this);		
		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				LinearLayout ll = null;
				RelativeLayout nocontent = null;
				TextView tv = null;
				LinearLayout.LayoutParams params = null;
				switch (msg.what) {
				case REFRESH:
					lv.onRefreshComplete();
					ba.notifyDataSetChanged();
					lv.refreshNow = false;
					ba.isInit = true;
					lv.nowLoad = false;
					break;
				case LOAD:
					ba.notifyDataSetChanged();
					timediver = list.get(list.size() - 1).getDate();
					lv.nowLoad = false;
					break;
				case THE_END:
					TextView foot_tip = (TextView) lv
							.findViewById(R.id.foot_tip);
					foot_tip.setText("到底了");
					break;
				case NO_MESSAGE:
					ll = (LinearLayout) findViewById(R.id.myListView);
					ll.removeAllViews();
					nocontent = (RelativeLayout) LayoutInflater.from(
							Pinglun.this).inflate(R.layout.noloaded, null);
					params = new LinearLayout.LayoutParams(
							LayoutParams.MATCH_PARENT,
							LayoutParams.MATCH_PARENT);
					nocontent.setLayoutParams(params);
					tv = (TextView) nocontent.getChildAt(0);
					tv.setText("暂时没有评论");
					ll.addView(nocontent);
					break;
				case NO_NET:
					ll = (LinearLayout) findViewById(R.id.myListView);
					ll.removeAllViews();
					nocontent = (RelativeLayout) LayoutInflater.from(
							Pinglun.this).inflate(R.layout.noloaded, null);
					params = new LinearLayout.LayoutParams(
							LayoutParams.MATCH_PARENT,
							LayoutParams.MATCH_PARENT);
					nocontent.setLayoutParams(params);
					tv = (TextView) nocontent.getChildAt(0);
					tv.setText(getResources().getString(R.string.net_fail));
					ll.addView(nocontent);
					break;
				}}};
		init();
	}

	private void init() {
		initPop();
		imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		ChatUtils.handlerInput = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == ChatUtils.CLOSE_INPUT) {
					imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
				} else if (msg.what == ChatUtils.OPEN_INPUT) {
					imm.showSoftInput(et, InputMethodManager.SHOW_FORCED);
				}
			}
		};
		lv = (MyListView) findViewById(R.id.listview);
		lv.setonRefreshListener(new MyOnRefresh());
		list = new ArrayList<ListItem>();
		ba = new PinglunAdapter(Pinglun.this, list, imageDownLoader);
		lv.setAdapter(ba);
		lv.setSelector(new ColorDrawable(Color.TRANSPARENT));
		lv.setOnLoadImage(new OnLoadedImage() {
			@Override
			public void onLoadImage() {
				int start = lv.getFirstVisiblePosition() - 1;
				start = (start < 0 ? 0 : start);
				int end = lv.getLastVisiblePosition();
				while (start < end && start < list.size()) {
					String prefix=list.get(start).getPicName();
					ImageView imageView = (ImageView) lv.findViewWithTag(prefix+ start);
					String picName = list.get(start).getPicName();
					if (!picName.equals("null")) {
					//	imageView.setImageResource(R.drawable.recipe_defult_img);
						imageDownLoader.thumbnailExcute(imageView, picName);
					}
					prefix=list.get(start).getSculture();
					ImageView sculture = (ImageView) lv
							.findViewWithTag(prefix	+ start);
					imageDownLoader.thumbnailExcute(sculture, list.get(start)
							.getSculture());
					start++;
				}
			}
			@Override
			public void onCancell() {
				ba.isInit = false;
				imageDownLoader.cancelTask();
			}
		});

		findViewById(R.id.take_ph).setOnClickListener(this);
		findViewById(R.id.btn_send).setOnClickListener(this);
		et = (EditText) findViewById(R.id.et_sendmessage);
		//et.requestFocus();
	}

	private void initPop() {
		pop = new PopupWindow();
		pop.setWidth(LayoutParams.MATCH_PARENT);
		pop.setHeight(LayoutParams.WRAP_CONTENT);
		ColorDrawable dw = new ColorDrawable(0xa0000000);
		pop.setBackgroundDrawable(dw);
		pop.setFocusable(true);
		pop.setOutsideTouchable(true);
		View view = getLayoutInflater().inflate(R.layout.take_pic_popwindow,
				null);
		pop.setContentView(view);
		View parent = view.findViewById(R.id.parent);
		parent.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				pop.dismiss();
			}
		});
		TextView take = (TextView) view.findViewById(R.id.take_pic_camera);
		take.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(camera, Constant.CAMERA_SIMPLE);
				pop.dismiss();
			}

		});
		TextView lib = (TextView) view.findViewById(R.id.lib);
		lib.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Pinglun.this, AlbumActivity.class);
				Bimp.tempSelectBitmap.clear();
				PublicWay.num = Bimp.SINGLE_PIC;
				startActivityForResult(intent, Constant.PICTURE_SIMLE);
				overridePendingTransition(R.anim.activity_translate_in,
						R.anim.activity_translate_out);
				pop.dismiss();
			}

		});
		TextView cancel = (TextView) view.findViewById(R.id.cancel);
		cancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				pop.dismiss();
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.take_ph: // 插入图片
			ChatUtils.handlerInput.sendEmptyMessage(ChatUtils.CLOSE_INPUT);
			pop.showAtLocation(findViewById(R.id.fr), Gravity.BOTTOM
					| Gravity.CENTER_HORIZONTAL, 0, 0);
			break;
		case R.id.btn_send: // 发送信息
			String uid = Utils.getUser(getApplicationContext())[0];
			String content = TextUtils.isEmpty(et.getText().toString().trim()) ? ""
					: et.getText().toString().trim();
			if (uid != null	&& ((picPath != null && !picPath.isEmpty()) || content
							.length() > 0)) {
				if (isMenu) {
					utils.uploadMenuComment(this, id, uid, content, picPath);
				} else {
					utils.uploadRandomComment(this, id, uid, content, picPath);
			}}
			viewll.closeView();
			ChatUtils.handlerInput.sendEmptyMessage(ChatUtils.CLOSE_INPUT);
			break;
		case R.id.btback:
				finish();
			break;
		}}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// 图库中
		if (requestCode == Constant.PICTURE_SIMLE) {
			picPath = Bimp.tempSelectBitmap.get(0).getImagePath();
			ImageButton ib = (ImageButton) findViewById(R.id.take_ph);
			Bitmap bm = BitmapFactory.decodeFile(picPath);
			ib.setImageBitmap(bm);
		}
		if (requestCode == Constant.CAMERA_SIMPLE
				&& resultCode == Activity.RESULT_OK && null != data) {
			String sdState = Environment.getExternalStorageState();
			if (!sdState.equals(Environment.MEDIA_MOUNTED)) {
				return;
			}
			String name = DateFormat.format("yyyyMMdd_hhmmss",
					Calendar.getInstance(Locale.CHINA))
					+ ".jpg";
			Bundle bundle = data.getExtras();
			if (bundle == null)
				return;
			Bitmap bitmap = (Bitmap) bundle.get("data");
			FileOutputStream fout = null;
			File file = new File("/sdcard/pintu/");
			if (file.exists()) {
				file.delete();
			}
			file.mkdirs();
			String filename = file.getPath() + name;
			try {
				fout = new FileOutputStream(filename);
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fout);
				ImageButton ib = (ImageButton) findViewById(R.id.take_ph);
				ib.setImageBitmap(bitmap);
				picPath = filename;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				try {
					fout.flush();
					fout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 加载更多
	private class LoadedMore extends Thread {
		@Override
		public void run() {
			try {
				List<String[]> content = null;
				if (isMenu) {
					content = NetInfoUtil.getCommentM(id, timediver);
				} else {
					content = NetInfoUtil.getCommentR(id, timediver);
				}
				if (null != content
						&& !Constant.NO_MESSAGE.equals(content.get(0)[0])) {
					for (String[] str : content) {
						int i = 0;
						ListItem item = new ListItem();
						item.setId(str[i++]);
						item.setUid(str[i++]);
						item.setDate(str[i++]);
						item.setPicName(str[i++]);
						item.setContent(str[i++]);
						item.setSculture(str[i++]);
						list.add(item);
					}
					timediver = list.get(list.size() - 1).getDate();
					mHandler.sendEmptyMessage(LOAD);

				} else {
					if (list.size() <= 0) {
						mHandler.sendEmptyMessage(NO_MESSAGE);
					} else {
						mHandler.sendEmptyMessage(THE_END);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private class Refresh extends Thread {
		@Override
		public void run() {
			try {
				timediver = "now()";
				List<String[]> content = null;
				if (isMenu) {
					content = NetInfoUtil.getCommentM(id, timediver);
				} else {
					content = NetInfoUtil.getCommentR(id, timediver);
				}
				System.out.println("content" + content.get(0)[4]);
				if (null != content
						&& !Constant.NO_MESSAGE.equals(content.get(0)[0])) {
					list.clear();
					for (String[] str : content) {
						int i = 0;
						ListItem item = new ListItem();
						item.setId(str[i++]);
						item.setUid(str[i++]);
						item.setDate(str[i++]);
						item.setPicName(str[i++]);
						item.setContent(str[i++]);
						item.setSculture(str[i++]);
						list.add(item);
					}
					timediver = list.get(list.size() - 1).getDate();
					mHandler.sendEmptyMessage(REFRESH);
				} else {
					if (list.size() <= 0)
						mHandler.sendEmptyMessage(NO_MESSAGE);
					else
						mHandler.sendEmptyMessage(THE_END);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private class MyOnRefresh implements MyListView.OnRefreshListener {
		@Override
		public void onRefresh() {
			lv.refreshNow = true;
			mHandler.postDelayed(new Runnable() {
				@Override
				public void run() {
					new Refresh().start();
				}
			}, 500);
		}

		@Override
		public void loadMore() {
			lv.nowLoad = true;
			mHandler.postDelayed(new Runnable() {
				@Override
				public void run() {
					new LoadedMore().start();
				}
			}, 500);
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
		imageDownLoader.cancelTaskNow();
	}
}