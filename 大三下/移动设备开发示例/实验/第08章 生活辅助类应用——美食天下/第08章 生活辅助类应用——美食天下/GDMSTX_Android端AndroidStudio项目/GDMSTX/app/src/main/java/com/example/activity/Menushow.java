package com.example.activity;

import com.example.MyRoundedImageView.RoundImageView;
import com.example.MySQLite.DatabaseUtil;
import com.example.MyScrollView.MyRandomScrollView;
import com.example.downLoader.ImageDownLoader;
import com.example.util.Constant;
import com.example.util.MyToast;
import com.example.util.NetInfoUtil;
import com.example.util.OnLoadUtil;
import com.example.util.ResponseUtil;
import com.example.util.Utils;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.example.activity.Pinglun;

/*
 * 菜谱详细信息界面
 */
public class Menushow extends Activity implements OnClickListener {
	String menu_id;
	ToggleButton attention_bt;
	Handler mHandler;
	String uid;
	LinearLayout pro_ll;
	LinearLayout bottomMenu;
	private Boolean isLoaded;// 信息来源 网络,本地
	MyRandomScrollView mmsv;
	private Button bt_more;
	private TextView tv_desc_short;
	private TextView tv_desc_long;
	private boolean isInit = false;
	private boolean isShowShortText = true;
	private ImageView iv_more_line;
	ImageDownLoader imageDownLoader;
	OnLoadUtil onLoadUtil;
	MyToast toast;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menushow);
		Bundle bundle = this.getIntent().getExtras();
		menu_id = bundle.getString("menu_id", null);
		isLoaded = bundle.getBoolean("isLoaded", false);
		imageDownLoader = new ImageDownLoader();
		toast = new MyToast(this);
		initView();
		onLoadUtil = new OnLoadUtil(this);
		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				Bundle bundle = msg.getData();
				String[] str = bundle.getStringArray("str");
				String process = bundle.getString("process", null);
				if (process != null && str != null) {
					if (!str.equals(Constant.NO_MESSAGE)) {
						loadReady(str);
						if (!process.equals(Constant.NO_MESSAGE))
							loadPro(process, str[2]);
					}
				}
				imageDownLoader.cancelTask();
			}
		};
		new Thread() {
			@Override
			public void run() {
				try {
					String[] str = null; // 信息数组
					String process = null; // 制作过程
					if (isLoaded) // 本地
					{
						str = DatabaseUtil.getMenuById(getApplicationContext(),
								menu_id);
						process = DatabaseUtil.getPro(getApplicationContext(),
								menu_id);
						System.out.println("process " + process);

					} else {

						String mmsg = NetInfoUtil.getMenuDetC(menu_id);
						process = NetInfoUtil.getMenuProC(menu_id);
						str = mmsg.split("<#>");
					}
					Bundle bundle = new Bundle();
					bundle.putStringArray("str", str);
					bundle.putString("process", process);
					Message msg = new Message();
					msg.setData(bundle);
					mHandler.sendMessage(msg);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	private void initView() {
		ImageButton last = (ImageButton) this.findViewById(R.id.btback);
		last.setOnClickListener(this);
		bottomMenu = (LinearLayout) this.findViewById(R.id.buttom_menu_menu);
		mmsv = (MyRandomScrollView) this.findViewById(R.id.menu_scrollView);
		mmsv.setBottomMenu(bottomMenu);
		pro_ll = (LinearLayout) this.findViewById(R.id.proces);
		for (int i = 0; i < bottomMenu.getChildCount(); i++) {
			LinearLayout children = (LinearLayout) bottomMenu.getChildAt(i);
			children.setOnClickListener(this);
		}
		if (DatabaseUtil.isLoadedMenu(getApplicationContext(),
				new String[] { menu_id })) {
			LinearLayout children = (LinearLayout) bottomMenu.getChildAt(3);
			ImageView iv = (ImageView) children.getChildAt(0);
			iv.setImageDrawable(getResources().getDrawable(
					R.drawable.loaded_selecte));
			TextView tv = (TextView) children.getChildAt(1);
			tv.setText(getResources().getString(R.string.loaded));
			children.setEnabled(false);
		}
		bt_more = (Button) findViewById(R.id.bt_more);
		iv_more_line = (ImageView) findViewById(R.id.iv_more_line);
		bt_more.setOnClickListener(this);
		attention_bt = (ToggleButton) this.findViewById(R.id.attention);
		attention_bt.setOnClickListener(this);
	}

	private void loadReady(String[] str) {
		String tips = str[11];
		String primary = str[0];
		final String uidP = str[16];
		uid = str[1];
		String[] vice = new String[] { str[4], str[5], str[6], str[7], str[8] };
		String[] food = (str[9] + "%" + str[10]).split("%");
		loadVice(vice);
		loadFood(food);
		TextView title = (TextView) this.findViewById(R.id.head_title);
		TextView uid = (TextView) this.findViewById(R.id.uid_name);
		tv_desc_short = (TextView) this.findViewById(R.id.tv_desc_short);
		tv_desc_long = (TextView) this.findViewById(R.id.tv_desc_long);
		TextView menu_name = (TextView) this.findViewById(R.id.menu_name);
		TextView tip = (TextView) this.findViewById(R.id.tips);
		menu_name.setText(str[2]);
		title.setText(str[2]);
		tv_desc_short.setText(str[3]);
		tv_desc_long.setText(str[3]);
		uid.setText(str[1]);
		tip.setText(tips);
		FrameLayout fl_desc = (FrameLayout) findViewById(R.id.fl_desc);
		ViewTreeObserver vto = fl_desc.getViewTreeObserver();
		vto.addOnPreDrawListener(new OnPreDrawListener() {
			@Override
			public boolean onPreDraw() {
				if (isInit)
					return true;
				if (mesureDescription(tv_desc_short, tv_desc_long)) {
					iv_more_line.setVisibility(View.VISIBLE);
					bt_more.setVisibility(View.VISIBLE);
				}
				isInit = true;
				return true;
			}
		});

		ImageView primaryIv = (ImageView) this.findViewById(R.id.primary_iv);
		imageDownLoader.imgExcute(primaryIv, primary);
		RoundImageView uidPic = (RoundImageView) this.findViewById(R.id.uid_p);
		imageDownLoader.roundImageViewExcute(uidPic, uidP);
		uidPic.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Menushow.this,
						PreviewSingleImage.class);
				intent.putExtra("picName", uidP);
				startActivity(intent);
				overridePendingTransition(R.anim.fade, R.anim.hold);
			}
		});
	}

	// 口味等信息
	private void loadVice(String[] vice) {
		LinearLayout tl = (LinearLayout) this.findViewById(R.id.vice_info);
		String[] name = new String[] { "口味", "工艺", "厨具", "困难度", "时间" };
		for (int i = 0; i < vice.length;) {
			if (vice[i].equals("null")) {
				i++;
				continue;
			}
			LinearLayout ll = (LinearLayout) LayoutInflater.from(this).inflate(
					R.layout.text_text2, null);
			TextView name1 = (TextView) ll.findViewById(R.id.name);
			TextView value1 = (TextView) ll.findViewById(R.id.value);
			name1.setText(name[i]);
			value1.setText(vice[i]);
			i++;
			if (i < vice.length) {
				TextView name2 = (TextView) ll.findViewById(R.id.name2);
				TextView value2 = (TextView) ll.findViewById(R.id.value2);
				name2.setText(name[i]);
				value2.setText(vice[i++]);
			}
			tl.addView(ll);
		}
	}

	private void loadFood(String[] str) {
		LinearLayout tl = (LinearLayout) this.findViewById(R.id.food_info);
		for (int i = 0; i < str.length; i++) {
			LinearLayout ll = (LinearLayout) LayoutInflater.from(this).inflate(
					R.layout.text_text2, null);
			System.out.println("ss1 " + str[i]);
			String[] ss1 = str[i].split("\\|");
			TextView name1 = (TextView) ll.findViewById(R.id.name);
			TextView value1 = (TextView) ll.findViewById(R.id.value);
			name1.setText(ss1[0]);
			value1.setText(ss1[1]);
			i++;
			if (i < str.length) {
				String[] ss2 = str[i].split("\\|");
				TextView name2 = (TextView) ll.findViewById(R.id.name2);
				TextView value2 = (TextView) ll.findViewById(R.id.value2);
				name2.setText(ss2[0]);
				value2.setText(ss2[1]);
			}
			tl.addView(ll);
		}
	}

	private void loadPro(final String process, final String title) {
		System.out.println("process " + process);
		final String[] str = process.split("%");
		for (int i = 0; i < str.length; i++) {
			View v = LayoutInflater.from(this).inflate(R.layout.menu_pro_item,
					null);
			TextView order = (TextView) v.findViewById(R.id.order);
			TextView introduce = (TextView) v.findViewById(R.id.introduce);
			order.setText((i + 1) + "");
			introduce.setText(str[i].split("\\|")[1]);
			ImageView iv = (ImageView) v.findViewById(R.id.image);
			System.out.println("pic name" + str[i].split("\\|")[0]);
			imageDownLoader.imgExcute(iv, str[i].split("\\|")[0]);
			v.setTag(i);
			v.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					System.out.println("position"
							+ ((TextView) (v.findViewById(R.id.order)))
									.getText());
					Intent intent = new Intent(Menushow.this, MenuProshow.class);
					intent.putExtra("order", (Integer) v.getTag());
					intent.putExtra("title", title);
					intent.putExtra("process", process);
					startActivity(intent);
				}

			});
			pro_ll.addView(v);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btback:
			this.finish();
			return;
		case R.id.home_ll:
			Intent intent = new Intent(this, MainActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			return;
		case R.id.bt_more:
			if (isShowShortText) {
				tv_desc_short.setVisibility(View.GONE);
				tv_desc_long.setVisibility(View.VISIBLE);
			} else {
				tv_desc_short.setVisibility(View.VISIBLE);
				tv_desc_long.setVisibility(View.GONE);
			}
			toogleMoreButton(bt_more);
			isShowShortText = !isShowShortText;
			return;
		default:
		}
		if (!Utils.isNewWork(getApplicationContext())) {
			toast.showToast(getResources().getString(R.string.net_fail));
			return;
		}
		if (!Utils.isLand(this)) {
			Intent intent = new Intent(this, Land.class);
			startActivity(intent);
			return;
		}
		String user = Utils.getUser(getApplicationContext())[0];
		ImageView iv = null;
		switch (v.getId()) {
		case R.id.attention:
			if (attention_bt.isChecked()) {
				ResponseUtil.attention(this, user, uid);
			} else {
				ResponseUtil.cancelAttention(this, user, uid);
			}
			break;
		case R.id.like_ll: // 喜欢
			ResponseUtil.likeMenu(user, menu_id);
			iv = (ImageView) findViewById(R.id.random_like_iamge);
			iv.setImageDrawable(getResources().getDrawable(
					R.drawable.like_red));
			v.setEnabled(false);
			break;
		case R.id.collection_ll: // 收藏
			ResponseUtil.collectionMenu(user, menu_id);
			iv = (ImageView) findViewById(R.id.random_collection_iamge);
			iv.setImageDrawable(getResources().getDrawable(
					R.drawable.cang_red));
			v.setEnabled(false);
			break;
		case R.id.load_ll: // 下载
			iv = (ImageView) ((ViewGroup) v).getChildAt(0);
			iv.setImageDrawable(getResources().getDrawable(
					R.drawable.loaded_selecte));
			onLoadUtil.loadMenu(menu_id);
			v.setEnabled(false);
			break;
		case R.id.pinglun_ll: // 评论
			Intent intent = new Intent(Menushow.this, Pinglun.class);
			intent.putExtra("menuId", menu_id);
			startActivity(intent);
			break;
		}
	}

	/**
	 * 计算描述信息是否过长
	 */
	private boolean mesureDescription(TextView shortView, TextView longView) {
		final int shortHeight = shortView.getHeight();
		final int longHeight = longView.getHeight();
		if (longHeight > shortHeight) {
			shortView.setVisibility(View.VISIBLE);
			longView.setVisibility(View.GONE);
			return true;
		}
		shortView.setVisibility(View.GONE);
		longView.setVisibility(View.VISIBLE);
		return false;
	}

	/**
	 * 更改按钮【更多】的文本
	 */
	private void toogleMoreButton(Button btn) {

		String text = (String) btn.getText();
		String moreText = getString(R.string.label_more);
		String lessText = getString(R.string.label_less);
		if (moreText.equals(text)) {
			btn.setText(lessText);
		} else {
			btn.setText(moreText);
		}
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		imageDownLoader.cancelTaskNow();
	}
}