package com.example.activity;
import java.util.ArrayList;
import java.util.List;

import com.example.downLoader.ImageDownLoader;
import com.example.util.BitmapCache;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/*
 * 菜谱制作过程详细
 */
public class MenuProshow extends Activity implements OnClickListener {
	String menu_id;
	PagerAdapter pa;
	ViewPager vp;
	ImageDownLoader imageDownLoader;
	String[] picNames;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		imageDownLoader = new ImageDownLoader();
		setContentView(R.layout.pro_show);
		Bundle bundle = this.getIntent().getExtras();		//获取Bundler信息
		menu_id = bundle.getString("menu_id");
		int order = bundle.getInt("order", 0);
		String title = bundle.getString("title");
		TextView head = (TextView) findViewById(R.id.head_title);	//设置界面标题
		head.setText(title);
		String process = bundle.getString("process");
		ImageButton back=(ImageButton) this.findViewById(R.id.btback);	//设置返回按钮监听器
		back.setOnClickListener(this);
		vp = (ViewPager) this.findViewById(R.id.pro_viewpager);
		String[] str = process.split("%");
		pa = new ViewPagerAdapter(str, this);
		vp.setAdapter(pa);												//设置ViewPager监听器
		vp.setCurrentItem(order);
		picNames=new String[str.length];
		for(int j=0;j<str.length;j++)
		{picNames[j]=str[j].split("\\|")[0];}							//加载制作过程
	}
	//******
	private class ViewPagerAdapter extends PagerAdapter {
		String[] infos;
		Context context;
		public ViewPagerAdapter(String[] infos, Context context) {
			this.infos = infos;
			this.context = context;
		}
		@Override
		public int getCount() {
			return infos.length;							//返回每一个内部项的数目
		}
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {		//判断是否由对象生成界面
			return arg0 == arg1;
		}
		@Override
		public void destroyItem(View container, int position, Object object) {	//移出当前View
			((ViewPager) container).removeView((View) object);			
		}
		@Override
		public Object instantiateItem(View container, final int position) {		//返回页面呈现的控件
			LinearLayout ll = (LinearLayout) LayoutInflater.from(context)
					.inflate(R.layout.pro_viewpager_item, null);
			try {
				TextView num = (TextView) ll.findViewById(R.id.order);
				num.setText((position + 1) + "/" + infos.length);
				ImageView iv = (ImageView) ll.findViewById(R.id.iv);
				String picName = infos[position].split("\\|")[0];
				String introduce = infos[position].split("\\|")[1];
				TextView tv = (TextView) ll.findViewById(R.id.tv);
				tv.setText(introduce);
				Bitmap bm = BitmapCache.getBitmapFromMemCache(picName);
				if (bm != null){
					iv.setImageBitmap(bm);
				} else {
					iv.setImageResource(R.drawable.recipe_defult_img);
					imageDownLoader.thumbnailExcute(iv, picName);
				}
				iv.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						Intent intent = new Intent(MenuProshow.this,
								PictureBrowsing.class);
						intent.putExtra("order", vp.getCurrentItem());
						intent.putExtra("picNames", picNames);
						MenuProshow.this.startActivity(intent);
						overridePendingTransition(R.anim.fade, R.anim.hold);
					}});
				((ViewPager) container).addView(ll);
			} catch (Exception e) {	e.printStackTrace();}
			return ll;
		}}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		imageDownLoader.cancelTaskNow();
	}
	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.left_right_btn)
		finish();
	}
}