package com.example.adpter;

import java.util.ArrayList;
import java.util.List;

import com.example.downLoader.ImageDownLoader;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;

///viewPager适配器  无限循环
public class ViewPagerAdapter extends PagerAdapter {
	List<String[]> views;
	private Context context;
	ImageDownLoader imageDownLoader;

	public ViewPagerAdapter(Context context, List<String[]> views,ImageDownLoader imageDownLoader) {
		this.views = views;
		this.context = context;
		this.imageDownLoader = imageDownLoader;
	}

	@Override
	public int getCount() {
		// 2^23-1
		return Integer.MAX_VALUE;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}

	/**
	 * 载入图片进去，用当前的position 除以 图片数组长度取余数!
	 */
	@Override
	public Object instantiateItem(View container, final int position) {
		ImageView imageView = new ImageView(context);
		imageView.setScaleType(ScaleType.FIT_XY);
		try {
			imageDownLoader.imgExcute(imageView, views.get(position%views.size())[1]);
			((ViewGroup) container).addView(imageView);
			imageView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Toast.makeText(context, "待开发",
							Toast.LENGTH_SHORT).show();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return imageView;
	}
}
