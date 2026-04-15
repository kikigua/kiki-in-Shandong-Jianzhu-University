package com.king.photo.adapter;

import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ToggleButton;
import com.king.photo.util.Bimp;
import com.king.photo.util.ImageItem;
import com.king.photo.util.PublicWay;
import com.example.activity.AlbumActivity;
import com.example.activity.GalleryActivity;
import com.example.activity.R;
import com.example.util.BitmapCache;
import com.example.util.Constant;

public class AlbumGridViewAdapter extends BaseAdapter{
	private AlbumActivity mContext;
	private List<ImageItem> dataList;
	private ArrayList<ImageItem> selectedDataList;
	public 	Boolean isInit=true;
	public AlbumGridViewAdapter(AlbumActivity c, List<ImageItem> dataList,
			ArrayList<ImageItem> selectedDataList) {
		mContext = c;
		this.dataList = dataList;
		this.selectedDataList = selectedDataList;
	}

	public int getCount() {
		return dataList.size();
	}

	public Object getItem(int position) {
		return dataList.get(position);
	}

	public long getItemId(int position) {
		return 0;
	}
	private class ViewHolder {
		public ImageView imageView;				//图片
		public ToggleButton toggleButton;	//选中标志
		public ImageView mengbai;		//蒙白
		public ImageView disp;			//点击框
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.plugin_camera_select_imageview, parent, false);
			viewHolder.imageView = (ImageView) convertView
					.findViewById(R.id.image_view);
			viewHolder.toggleButton = (ToggleButton) convertView
					.findViewById(R.id.toggle_button);
			viewHolder.mengbai = (ImageView) convertView
					.findViewById(R.id.mengbai);
			viewHolder.disp = (ImageView) convertView
					.findViewById(R.id.display);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		final ImageItem item = dataList.get(position);
		viewHolder.imageView.setTag(item.imagePath);
		Bitmap bm = BitmapCache.getBitmapFromMemCache(item.thumbnailPath);
		if (bm != null) {
			viewHolder.imageView.setImageBitmap(bm);
		} else {
			viewHolder.imageView.setImageResource(R.drawable.recipe_defult_img);
			if (isInit) {
				Bimp.displayBmp(viewHolder.imageView, item.thumbnailPath,
						item.imagePath, item);
			}
		}
		viewHolder.toggleButton.setTag(position);
		viewHolder.toggleButton.setOnClickListener(new ToggleClickListener(
				viewHolder.mengbai, viewHolder.disp));

		if (selectedDataList.contains(dataList.get(position))) {
			viewHolder.toggleButton.setChecked(true);
			viewHolder.disp.setImageResource(R.drawable.ab9);
			viewHolder.mengbai.setVisibility(View.VISIBLE);
		} else {
			viewHolder.toggleButton.setChecked(false);
			viewHolder.disp.setImageResource(R.drawable.ab8);
			viewHolder.mengbai.setVisibility(View.GONE);
		}
		viewHolder.disp.setVisibility(View.VISIBLE);
		viewHolder.imageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("index_CurrentBucket",
						PublicWay.ALL_PIC_PREVIEW);
				intent.putExtra("position", position);
				intent.setClass(mContext, GalleryActivity.class);
				mContext.startActivityForResult(intent,
						Constant.GALLERY_PREVIEWS);
				mContext.overridePendingTransition(R.anim.fade, R.anim.hold);
				
			}

		});
		return convertView;
	}
	private class ToggleClickListener implements OnClickListener{
		ImageView iv;
		ImageView dis;
		public ToggleClickListener(ImageView iv,ImageView dis)
		{
			this.iv=iv;
			this.dis=dis;
		}
		@Override
		public void onClick(View view) {
			if (view instanceof ToggleButton) {
				ToggleButton toggleButton = (ToggleButton) view;
				int position = (Integer) toggleButton.getTag();
				if (dataList != null && mOnItemClickListener != null
						&& position < dataList.size()) {
					mOnItemClickListener.onItemClick(toggleButton, position, toggleButton.isChecked(),iv,dis);
				}
			}
		}
	}
	

	private OnItemClickListener mOnItemClickListener;

	public void setOnItemClickListener(OnItemClickListener l) {
		mOnItemClickListener = l;
	}

	public interface OnItemClickListener {
		public void onItemClick(ToggleButton view, int position,
				boolean isChecked,ImageView iv,ImageView dis);
	}
}
