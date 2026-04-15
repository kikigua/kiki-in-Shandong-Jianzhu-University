package com.king.photo.util;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.example.util.BitmapCache;
import com.example.util.BitmapUtils;
import com.example.util.Constant;
import com.king.photo.zoom.PhotoView;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.ImageView;
/*
 * 图库加载类
 */
public class Bimp extends Activity {
	public static int SINGLE_PIC = 1;		//只许选择一张图片
	 static int PAI_COUNT = 20;			//随拍最大上传张数
	public static int PROCESS_COUNT = 20;		//菜谱制作过程最大步骤
	static String thumnail="thumnail/";
	static Handler h = new Handler();
	static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
	
	//加载随拍图片初始信息
	public static void initPai(int has)
	{				
		Bimp.tempSelectBitmap.clear();//最多可加载数量
		PublicWay.num=PAI_COUNT-has;
	}
	//加载菜品过程初始信息
	public static void initMenu(int has)
	{
		PublicWay.num=PROCESS_COUNT-has;		//最多可加载数量
		Bimp.tempSelectBitmap.clear();
	}
	//加载主图或者评论 只允许加载一张
	public static void initSingleBimp()
	{
		PublicWay.num=SINGLE_PIC;
		Bimp.tempSelectBitmap.clear();
	}
	public static ArrayList<ImageItem> tempSelectBitmap = new ArrayList<ImageItem>();
	//加载缩略图 相册
	public static void displayBmp(final ImageView iv, final String thumbPath,
			final String sourcePath,final ImageItem item) {
		if (TextUtils.isEmpty(thumbPath) && TextUtils.isEmpty(sourcePath)) {
			return;
		}

		final String path;
		final boolean isThumbPath;
		if (!TextUtils.isEmpty(thumbPath)) {
			path = thumbPath;
			isThumbPath = true;
		} else if (!TextUtils.isEmpty(sourcePath)) {
			path = sourcePath;
			isThumbPath = false;
		} else {
			iv.setImageBitmap(null);
			return;
		}

		if (BitmapCache.getBitmapFromMemCache(path) != null) {
			Bitmap bmp = BitmapCache.getBitmapFromMemCache(path);
			if (bmp != null) {
				if(iv.getTag()==sourcePath)
				iv.setImageBitmap(bmp);
				return;
			}
		}
		iv.setImageBitmap(null);
		if(fixedThreadPool==null)
		{
			fixedThreadPool = Executors.newFixedThreadPool(3);
		}
		fixedThreadPool.execute(new Runnable() {
			Bitmap thumb = null;

			@Override
			public void run() {
				try {
					if (isThumbPath) {
						thumb = BitmapFactory.decodeFile(thumbPath);
						if (thumb == null) {
							thumb = BitmapUtils.decodeSampleBitmapFromFile(
									sourcePath, Constant.ScreenWidth/4, Constant.ScreenWidth/4);
						}
						BitmapCache.addBitmapToMemoryCache(thumbPath, thumb);
					} else {
						thumb = BitmapUtils.decodeSampleBitmapFromFile(
								sourcePath, Constant.ScreenWidth/4, Constant.ScreenWidth/4);
						item.thumbnailPath=thumnail+sourcePath;
						BitmapCache.addBitmapToMemoryCache(item.thumbnailPath, thumb);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (thumb!=null&&iv!=null) {
					h.post(new Runnable() {
						@Override
						public void run() {
							if(iv.getTag()==sourcePath)
							iv.setImageBitmap(thumb);
						}
					});
				}
			}
		
		});
	}
	// 异步加载多点触控图片 图片浏览 从本地 相册
	public static void displayPVBmpfromFile(final PhotoView iv,
			final String sourcePath) {
		Bitmap bm = null;
		if (TextUtils.isEmpty(sourcePath)) {
			return;
		}
		bm = BitmapCache.showCacheBitmap(sourcePath);
		if (bm != null) {
			iv.setImageBitmap(bm);
			return;
		}
		if(fixedThreadPool==null)
		{
			fixedThreadPool = Executors.newFixedThreadPool(3);
		}
		fixedThreadPool.execute(new Runnable() {
			Bitmap bm = null;
			@Override
			public void run() {				
				try {
					bm = BitmapUtils.revitionImageSize(sourcePath);
					h.post(new Runnable() {
						@Override
						public void run() {
							iv.setImageBitmap(bm);
						}
					});
					BitmapCache.addBitmapToMemoryCache(sourcePath, bm);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	// 异步加载  从本地 
		public static void displayBmpfromFile(final ImageView iv,
				final String sourcePath) {
			Bitmap bm = null;
			if (TextUtils.isEmpty(sourcePath)) {
				return;
			}
			bm = BitmapCache.showCacheBitmap(sourcePath);
			if (bm != null) {
				iv.setImageBitmap(bm);
				return;
			}
			if(fixedThreadPool==null)
			{
				fixedThreadPool = Executors.newFixedThreadPool(3);
			}
			fixedThreadPool.execute(new Runnable() {
				Bitmap bm = null;
				@Override
				public void run() {				
					try {
						bm = BitmapUtils.revitionImageSize(sourcePath);
						h.post(new Runnable() {
							@Override
							public void run() {
								iv.setImageBitmap(bm);
							}
						});
						BitmapCache.addBitmapToMemoryCache(sourcePath, bm);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
	public static void cancelTask()
	{
		if(fixedThreadPool!=null)
		{
			fixedThreadPool.shutdown();
			fixedThreadPool=null;
		}
	}
	
}
