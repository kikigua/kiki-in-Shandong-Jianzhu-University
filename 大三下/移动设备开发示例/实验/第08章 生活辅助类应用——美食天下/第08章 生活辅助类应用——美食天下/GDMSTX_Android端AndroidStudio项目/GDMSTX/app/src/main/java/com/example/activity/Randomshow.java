package com.example.activity;

import com.example.MyRoundedImageView.RoundImageView;
import com.example.MySQLite.DatabaseUtil;
import com.example.MyScrollView.MyRandomScrollView;
import com.example.downLoader.ImageDownLoader;
import com.example.util.MyToast;
import com.example.util.OnLoadUtil;
import com.example.util.NetInfoUtil;
import com.example.util.ResponseUtil;
import com.example.util.Utils;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

public class Randomshow extends Activity implements OnClickListener {

	String random_Id; // random的id
	private Handler mHandler;
	private ToggleButton attentionBt; // 关注按钮
	LayoutInflater inflater; // /放置随拍图片的TableLayout
	private ImageView[] ivs; // 随拍图片
	boolean isLocal = false;
	String uid;				//随拍上传者
	private int screenWidth;
	private Button bt_more;
	private FrameLayout fl_desc;
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
		super.onCreate(savedInstanceState);
		imageDownLoader=new ImageDownLoader();
		inflater = LayoutInflater.from(Randomshow.this);
		onLoadUtil=new OnLoadUtil(this);
		final Bundle bundle = this.getIntent().getExtras();
		if (bundle != null) {
			random_Id = bundle.getString("random_id");
			isLocal = bundle.getBoolean("isLoaded", false);
		}
		toast=new MyToast(this);
		screenWidth=Utils.getScreenWidth(this);
		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				Bundle bundle = null;
					bundle = msg.getData();
					String[] info=bundle.getStringArray("info");
					init(info);					
					if (!info[6].isEmpty()) // 标签不是空
					{
						String[] lables = info[6].split("%");
						LinearLayout randomLabelLl = (LinearLayout) findViewById(R.id.random_label_ll);
						for (String s : lables) {
							// /加入几次临时压入几次，
							TextView tv = new TextView(Randomshow.this);
							LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
									LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
							params.setMargins(20, 0, 0, 0);
							tv.setLayoutParams(params);
							tv.setText(s);
							tv.setGravity(Gravity.CENTER);
							randomLabelLl.addView(tv);
						}
						randomLabelLl.setVisibility(View.VISIBLE);
					}
					//城市不是空
					if (!info[7].isEmpty()) {
						LinearLayout randomCityll = (LinearLayout) findViewById(R.id.random_city_ll);
						TextView randomCity = (TextView) findViewById(R.id.random_city);
						randomCity.setText(info[7]);
						randomCityll.setVisibility(View.VISIBLE);

					}
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
					//初始图片
					final String[] randomPicName=info[8].split("%");
					int count=randomPicName.length;
					LinearLayout ll = (LinearLayout) findViewById(R.id.random_pic_tbl);
					ll.removeAllViews();
					ivs = new ImageView[count];
					if (count != 0) {
						ll.removeAllViews();
						int k = 0;
						for ( int j = 0; j < count;) {
							LinearLayout l=new LinearLayout(Randomshow.this);
							l.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
							l.setOrientation(LinearLayout.HORIZONTAL);
							while (k++ < 3 && j < count) {
								ImageView iv = new ImageView(Randomshow.this);
								iv.setScaleType(ScaleType.CENTER_CROP);
								LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(screenWidth/4	, screenWidth/4);
								lp.setMargins(screenWidth/24, screenWidth/24, screenWidth/24, screenWidth/24);
								iv.setLayoutParams(lp);
								ivs[j] = iv;
								iv.setTag(j);
								System.out.println("picName "+randomPicName[j]);
								iv.setImageResource(R.drawable.recipe_defult_img);
								imageDownLoader.imgExcute(iv, randomPicName[j]);
								l.addView(ivs[j]);
								ivs[j].setTag(j);
								ivs[j].setOnClickListener(
										new OnClickListener() {											
									@Override
									public void onClick(View v) {
										Intent intent=new Intent(Randomshow.this,PictureBrowsing.class);
										intent.putExtra("picNames", randomPicName);
										int k=(Integer) v.getTag();
										intent.putExtra("order",k);
										startActivity(intent);
										overridePendingTransition(R.anim.fade, R.anim.hold);
									}
								}); 
								j++;								
							}
							k = 0;
							ll.addView(l);
						}
					}					
					imageDownLoader.cancelTask();
			}
		};
		setContentView(R.layout.randomshow);
		LinearLayout ll = (LinearLayout) this.findViewById(R.id.random_bottom_menu);
		MyRandomScrollView mslv = (MyRandomScrollView) this
				.findViewById(R.id.mrsv_xml);
		mslv.setBottomMenu(ll);
		ImageButton back=(ImageButton) this.findViewById(R.id.btback);
		back.setOnClickListener(this);
		TextView title=(TextView) this.findViewById(R.id.head_title);
		title.setText("随拍详情");
		for (int i = 0; i < ll.getChildCount(); i++) {
			LinearLayout home_ll = (LinearLayout) ll.getChildAt(i);
			home_ll.setOnClickListener(this);
		}
		if (DatabaseUtil.isLoadedRandom(Randomshow.this,
				new String[] { random_Id })) {
			LinearLayout home_ll = (LinearLayout) ll.getChildAt(3);
			TextView tv = (TextView) home_ll.getChildAt(1);
			tv.setText("已离线");
			ImageView iv = (ImageView) home_ll.getChildAt(0);
			iv.setImageResource(R.drawable.loaded_selecte);
			home_ll.setEnabled(false);

		}
		attentionBt = (ToggleButton) this.findViewById(R.id.attention_bt);
		attentionBt.setOnClickListener(this);
		fl_desc = (FrameLayout) findViewById(R.id.fl_desc);
		bt_more = (Button) findViewById(R.id.bt_more);
		bt_more.setOnClickListener(this);
		iv_more_line = (ImageView) findViewById(R.id.iv_more_line);
	
		if (bundle != null) {
			// 图片名
			new Thread() {
				@Override
				public void run() {
					try {
						String[] rmsg = null;
						if (isLocal) {
							System.out.println("loaded info");
							rmsg = DatabaseUtil.getRandomById(Randomshow.this,
									random_Id);
						} else {
							String info = NetInfoUtil
									.getRandomDetailC(random_Id);
							rmsg = info.split("<#>");
						}
						Message msg = new Message();
						Bundle bundle = new Bundle();
						bundle.putStringArray("info", rmsg);
						msg.setData(bundle);
						// 显示文字信息
						mHandler.sendMessage(msg);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}.start();
		}
		System.out.println("onCreate");

	}
	public void init(String[] info)
	{
		RoundImageView headIv = (RoundImageView) findViewById(R.id.uid_randomshow);
		System.out.println();
		imageDownLoader.roundImageViewExcute(headIv, info[9]);
		headIv.setTag(info[9]);
		headIv.setOnClickListener(this);
		
		TextView uidt = (TextView) findViewById(R.id.uid_randomshow_tv);
		TextView time= (TextView) findViewById(R.id.time_randomshow_tv);
		tv_desc_short= (TextView) findViewById(R.id.tv_desc_short);
		TextView like= (TextView) findViewById(R.id.random_like_tv);
		TextView collection= (TextView) findViewById(R.id.random_collection_tv);
		TextView pinglun= (TextView) findViewById(R.id.random_pinglun_tv);
		tv_desc_long=(TextView) findViewById(R.id.tv_desc_long);
		uidt.setText(info[0]);
		uid=info[0];
		int index=info[1].indexOf('.')>0?info[1].indexOf('.'):info[1].length()-1;
		time.setText(info[1].substring(0, index));
		tv_desc_short.setText(info[2]);
		like.setText(info[3]);
		collection.setText(info[4]);
		pinglun.setText(info[5]);
		tv_desc_long.setText(info[2]);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btback:
			this.finish();
			break;
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
		case R.id.home_ll:
			Intent intent=new Intent(this,MainActivity.class);
			intent.putExtra("back", "back");
			startActivity(intent);
			break;
		case R.id.left_right_btn:
			this.finish();
			break;
		case R.id.uid_randomshow:
			String picName=(String) v.getTag();
			intent = new Intent(Randomshow.this, PreviewSingleImage.class);  
  
            intent.putExtra("picName", picName);
//            intent.putExtra("locationX", location[0]);//必须   
//            intent.putExtra("locationY", location[1]);//必须   
//            intent.putExtra("width", v.getWidth());//必须   
//            intent.putExtra("height", v.getHeight());//必须   
            startActivity(intent);  
//			overridePendingTransition(0, 0);
			overridePendingTransition(R.anim.fade, R.anim.hold);

			break;			
		}
		if (!Utils.isLand(this)) {
			Intent intent = new Intent(this, Land.class);
			startActivity(intent);
			return;
		} 
		if (!Utils.isNewWork(getApplicationContext())) {
			toast.showToast(getResources().getString(R.string.net_fail));
			return;
		}
		else
		{
			String parameter=null;
			ImageView iv=null;
			String user = Utils.getUser(this)[0];
			switch(v.getId())
			{
			case R.id.like_ll:
				System.out.println("paramgert"+parameter);
				iv = (ImageView) this.findViewById(R.id.random_like_iamge);
				iv.setImageResource(R.drawable.like_red);
				v.setEnabled(false);
				ResponseUtil.likeRandom(user, random_Id);
				break;
			case R.id.collection_ll:
				ResponseUtil.collectionRandom(user, random_Id);
				iv = (ImageView) this.findViewById(R.id.random_collection_iamge);
				iv.setImageResource(R.drawable.cang_red);
				v.setEnabled(false);
				break;
			case R.id.load_ll:
				onLoadUtil.onLoadRandom(random_Id);
				iv = (ImageView) this.findViewById(R.id.random_loaded_iamge);
				iv.setImageResource(R.drawable.loaded_selecte);
				v.setEnabled(false);
				break;				
			case R.id.pinglun_ll:
				Intent intent=new Intent(Randomshow.this,Pinglun.class);
				intent.putExtra("randomId", random_Id);
				startActivity(intent);
				break;
			case R.id.attention_bt:
				if(attentionBt.isChecked())
				{
					ResponseUtil.attention(this,user, uid);
				}else
				{
					ResponseUtil.cancelAttention(this,user, uid);
				}
				break;		
			
			}
		}
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
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		imageDownLoader.cancelTaskNow();
	}	
}