package com.example.activity;
import java.util.Arrays;
import java.util.List;
import com.example.downLoader.ImageDownLoader;
import com.example.util.BitmapCache;
import com.king.photo.util.Bimp;
import com.king.photo.zoom.PhotoView;
import com.king.photo.zoom.ViewPagerFixed;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
/*
 * 纯图片浏览
 */
public class PictureBrowsing extends Activity {
	ViewPagerFixedAdapter pa;
	Handler mHandler;
	ImageView[] dotos;
	ImageDownLoader imageDownLoaer;
	Boolean  isLocal=true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menupro_pic_detail);		
		imageDownLoaer=new ImageDownLoader();
		ViewPagerFixed vp = (ViewPagerFixed) this
				.findViewById(R.id.pic_preview_viewpager);
		Bundle bundle=this.getIntent().getExtras();		
		List<String> paths=bundle.getStringArrayList("paths");
		if(paths==null)
		{
			isLocal=false;
			String[] picNames=bundle.getStringArray("picNames");
			paths= Arrays.asList(picNames);
		}
		int order=bundle.getInt("order",0);
		int count = paths.size();
		LinearLayout ll=(LinearLayout) this.findViewById(R.id.bottom_doto);
		dotos=new ImageView[count];
		for (int i = 0; i < count; i++) {
			dotos[i] = new ImageView(this);
			dotos[i].setBackgroundResource(R.drawable.indicator);
			ll.addView(dotos[i]);
		}
		pa = new ViewPagerFixedAdapter(this,paths);
		vp.setAdapter(pa);
		vp.setCurrentItem(order);
		setCurrent(order);
		vp.setOnPageChangeListener(
				new OnPageChangeListener()
				{
					@Override
					public void onPageSelected(int arg0) {
						setCurrent(arg0);						
					}
					@Override
					public void onPageScrollStateChanged(int arg0) {}

					@Override
					public void onPageScrolled(int arg0, float arg1, int arg2) {}
					
				});
	}
	private void setCurrent(int current)
	{
		for(ImageView iv:dotos)
		{
			iv.setBackgroundResource(R.drawable.indicator);
		}
		dotos[current].setBackgroundResource(R.drawable.indicator_focused);
	}
	private class ViewPagerFixedAdapter extends PagerAdapter {

		List<String> list;
		Context context;
		public ViewPagerFixedAdapter(Context context,List<String> list) {
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
				String picName=list.get(arg1);
				Bitmap bm=BitmapCache.getBitmapFromMemCache(picName);
				if(bm!=null)
				{
					pv.setImageBitmap(bm);
				}else
				{
					pv.setImageResource(R.drawable.recipe_defult_img);
					if(isLocal)
					{
						Bimp.displayPVBmpfromFile(pv, picName);
					}else
					{
						imageDownLoaer.imageExecute(pv,picName);

					}
				}
				
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
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		imageDownLoaer.cancelTaskNow();
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.finish();
			overridePendingTransition(R.anim.fade, R.anim.hold);
		}
		return true;
	}	
}