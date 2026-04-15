package com.example.activity;

import com.example.MyRoundedImageView.SmoothImageView;
import com.example.downLoader.ImageDownLoader;
import com.example.util.BitmapUtils;
import com.king.photo.zoom.PhotoView;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.ImageView.ScaleType;

public class PreviewSingleImage extends  Activity
{
	Bitmap bm;
	ImageDownLoader imageDownLoader;
	private int mLocationX;
	private int mLocationY;
	private int mWidth;
	private int mHeight;
	SmoothImageView imageView = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.preview_image_single);
		PhotoView pv=(PhotoView) findViewById(R.id.pv);
		Bundle bundle=getIntent().getExtras();
		String pathName=bundle.getString("pathName",null);
		String picName=bundle.getString("picName",null);		
		imageDownLoader=new ImageDownLoader();
		try {
			if(null!=picName&&null==bm)
			{
				imageDownLoader.imgExcute(pv, picName);
			}
			else if(null==bm&&null!=pathName)
			{
				bm=BitmapUtils.revitionImageSize(pathName);
				pv.setImageBitmap(bm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		Bundle bundle=getIntent().getExtras();
//		mLocationX = bundle.getInt("locationX", 0);
//		mLocationY = bundle.getInt("locationY", 0);
//		mWidth = bundle.getInt("width", 0);
//		mHeight = bundle.getInt("height", 0);
//
//		imageView = new SmoothImageView(this);
//		imageView.setOriginalInfo(mWidth, mHeight, mLocationX, mLocationY);
//		imageView.transformIn();
//		imageView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
//		imageView.setScaleType(ScaleType.FIT_CENTER);
//		setContentView(imageView);
//		String pathName=bundle.getString("pathName",null);
//		String picName=bundle.getString("picName",null);
//		imageDownLoader=new ImageDownLoader();
//		try {
//			if(null!=picName&&null==bm)
//			{
//				imageDownLoader.imgExcute(imageView, picName);
//			}
//			else if(null==bm&&null!=pathName)
//			{
//				bm=BitmapUtils.revitionImageSize(pathName);
//				imageView.setImageBitmap(bm);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		imageDownLoader.cancelTaskNow();
	}
//	@Override
//	public void onBackPressed() {
//		imageView.setOnTransformListener(new SmoothImageView.TransformListener() {
//			@Override
//			public void onTransformComplete(int mode) {
//				if (mode == 2) {
//					finish();
//				}
//			}
//		});
//		imageView.transformOut();
//	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.finish();
			overridePendingTransition(R.anim.fade, R.anim.hold);
		}
		return true;
	}	
	@Override
	protected void onPause() {
		super.onPause();
		if (isFinishing()) {
			overridePendingTransition(0, 0);
		}
	}
}