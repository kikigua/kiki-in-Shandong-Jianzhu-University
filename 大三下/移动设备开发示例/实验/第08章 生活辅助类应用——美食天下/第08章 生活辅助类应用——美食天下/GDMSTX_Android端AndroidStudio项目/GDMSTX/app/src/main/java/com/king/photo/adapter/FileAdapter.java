package com.king.photo.adapter;
import com.example.activity.AlbumActivity;
import com.example.activity.R;
import com.king.photo.util.Bimp;
import com.king.photo.util.ImageItem;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FileAdapter extends BaseAdapter
{

	Context conext;
	public FileAdapter(Context conext)
	{		
		this.conext=conext;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return AlbumActivity.contentList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		
		if(convertView==null)
		{
			convertView=LayoutInflater.from(conext).inflate(R.layout.item_picfile_item, null);
		}
		ImageView imageView=(ImageView) convertView.findViewById(R.id.file_image);
		TextView fileName=(TextView) convertView.findViewById(R.id.file_name);
		TextView fileNum=(TextView) convertView.findViewById(R.id.num);
		
		String path;
		if (AlbumActivity.contentList.get(position).imageList != null) {

			path = AlbumActivity.contentList.get(position).imageList.get(0).imagePath;
			fileName.setText(AlbumActivity.contentList.get(position).bucketName);
			fileNum.setText("" + AlbumActivity.contentList.get(position).count);
			
		} else
			path = "android_hybrid_camera_default";
		if (path.contains("android_hybrid_camera_default"))
			imageView.setImageResource(R.drawable.plugin_camera_no_pictures);
		else {
			final ImageItem item = AlbumActivity.contentList.get(position).imageList.get(0);
			imageView.setTag(item.imagePath);
			Bimp.displayBmp(imageView, item.thumbnailPath, item.imagePath,
					item);
		}		
		return convertView;
	}
}