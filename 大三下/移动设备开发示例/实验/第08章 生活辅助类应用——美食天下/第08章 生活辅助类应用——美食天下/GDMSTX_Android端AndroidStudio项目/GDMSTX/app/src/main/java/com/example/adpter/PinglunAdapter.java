package com.example.adpter;

import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.MyRoundedImageView.RoundImageView;
import com.example.activity.PreviewSingleImage;
import com.example.activity.R;
import com.example.chat.FaceUtils;
import com.example.chat.ListItem;
import com.example.downLoader.ImageDownLoader;
import com.example.util.BitmapCache;

	public class PinglunAdapter extends BaseAdapter {
		List<ListItem> list;
		Context context;
		ImageDownLoader imageDownLoader;
		public Boolean isInit = true;

		public PinglunAdapter(Context context, List<ListItem> list,ImageDownLoader imageDownLoader) {
			this.context = context;
			this.list = list;
			this.imageDownLoader = imageDownLoader;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
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
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			if (null == convertView) {
				convertView = LayoutInflater.from(this.context).inflate(
						R.layout.pinglun_item, null);
			}
			TextView uid = (TextView) convertView.findViewById(R.id.name);
			TextView time = (TextView) convertView.findViewById(R.id.date);
			
			RoundImageView sculture = (RoundImageView) convertView
					.findViewById(R.id.sculture);
			Bitmap img = BitmapCache.getBitmapFromMemCache(ImageDownLoader.thumbnail
							+ list.get(position).getSculture());
			if (img != null) {
				sculture.setImageBitmap(img);
			} else {
				sculture.setImageResource(R.drawable.recipe_defult_img);
				if (isInit) {
					imageDownLoader.thumbnailExcute(sculture, list
							.get(position).getSculture());
				}
			}
			sculture.setTag(list.get(position).getSculture()+ position);
			sculture.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {

					Intent intent = new Intent(context, PreviewSingleImage.class);  
					intent.putExtra("picName", list.get(position).getSculture());
		            context.startActivity(intent);  
		            ((Activity) context).overridePendingTransition(R.anim.fade, R.anim.hold);
				}
			});
			
			ImageView imageView = (ImageView) convertView
					.findViewById(R.id.pic);
			imageView.setVisibility(View.GONE);
			String picName = list.get(position).getPicName();
			if (!picName.equals("null")) {
				imageView.setVisibility(View.VISIBLE);
				imageView.setTag(list.get(position).getPicName()+ position);
				Bitmap pic = BitmapCache.getBitmapFromMemCache(ImageDownLoader.thumbnail
						+ list.get(position).getPicName());
				if (pic != null) {
					imageView.setImageBitmap(pic);
				} else {
					imageView.setImageResource(R.drawable.recipe_defult_img);
					if (isInit) {
						imageDownLoader.thumbnailExcute(imageView, list.get(position)
								.getPicName());
					}
				}
				imageView.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {

						Intent intent = new Intent(context, PreviewSingleImage.class);  
//						int[] location = new int[2];            
//			            v.getLocationOnScreen(location);  
			            intent.putExtra("picName", list.get(position).getPicName());
//			            intent.putExtra("locationX", location[0]);//必须   
//			            intent.putExtra("locationY", location[1]);//必须   
//			            intent.putExtra("width", v.getWidth());//必须   
//			            intent.putExtra("height", v.getHeight());//必须   
			            context.startActivity(intent);  
			//            ((Activity) context).overridePendingTransition(0, 0);
			            ((Activity) context).overridePendingTransition(R.anim.fade, R.anim.hold);
					}
				});
			}
		
			TextView content = (TextView) convertView
					.findViewById(R.id.content);
			uid.setText(list.get(position).getUid());
			time.setText(list.get(position).getDate());
			String con = list.get(position).getContent().equals("null") ? ""
					: list.get(position).getContent();
			FaceUtils util = FaceUtils.getInstace();
			SpannableString spannableString = util.getExpressionString(context,
					con);
			content.setText(spannableString);
			return convertView;
		}
	}