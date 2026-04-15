package com.king.photo.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Images.Thumbnails;

public class AlbumHelper {
	Context context;
	ContentResolver cr;
	
	HashMap<String, String> thumbnailList ;//= new HashMap<String, String>();
	HashMap<String, ImageBucket> bucketList = new HashMap<String, ImageBucket>();

	private static AlbumHelper instance;
	public boolean hasBuildImagesBucketList = false;
	public static AlbumHelper getHelper() {
		if (instance == null) {
			instance = new AlbumHelper();
		}
		return instance;
	}

	public void init(Context context) {
		if (this.context == null) {
			this.context = context;
			cr = context.getContentResolver();
		}
	}

	private void getThumbnail() {
		String[] projection = {Thumbnails.IMAGE_ID,
				Thumbnails.DATA };
		Cursor cursor = cr.query(Thumbnails.EXTERNAL_CONTENT_URI, projection,
				null, null, null);
		getThumbnailColumnData(cursor);
	}

	//得到缩略图的id 和路径 存入thumbnailList
	private void getThumbnailColumnData(Cursor cur) {
		if (cur.moveToFirst()) {
			int image_id;
			String image_path;
			int image_idColumn = cur.getColumnIndex(Thumbnails.IMAGE_ID);
			int dataColumn = cur.getColumnIndex(Thumbnails.DATA);
			thumbnailList = new HashMap<String, String>();
			do {
				image_id = cur.getInt(image_idColumn);
				image_path = cur.getString(dataColumn);
				thumbnailList.put("" + image_id, image_path);
			} while (cur.moveToNext());
		}
		cur.close();
	}
	
	void buildImagesBucketList() {
		getThumbnail();
		String columns[] = new String[] { Media._ID, Media.BUCKET_ID,
				 Media.DATA, Media.BUCKET_DISPLAY_NAME };
		Cursor cur = cr.query(Media.EXTERNAL_CONTENT_URI, columns, null, null,
				null);
		//初始化含有图片的文件夹的相关信息存入bucketList
		if (cur.moveToFirst()) {
			int photoIDIndex = cur.getColumnIndexOrThrow(Media._ID);
			int photoPathIndex = cur.getColumnIndexOrThrow(Media.DATA);
			int bucketDisplayNameIndex = cur
					.getColumnIndexOrThrow(Media.BUCKET_DISPLAY_NAME);
			int bucketIdIndex = cur.getColumnIndexOrThrow(Media.BUCKET_ID);
			do {
				String _id = cur.getString(photoIDIndex);
				String path = cur.getString(photoPathIndex);
				String bucketName = cur.getString(bucketDisplayNameIndex);
				String bucketId = cur.getString(bucketIdIndex);//含有图片的文件在手机中的id
				ImageBucket bucket = bucketList.get(bucketId);
				if (bucket == null) {
					bucket = new ImageBucket();
					bucketList.put(bucketId, bucket);
					bucket.imageList = new ArrayList<ImageItem>();
					bucket.bucketName = bucketName;
				}
				bucket.count++;
				ImageItem imageItem = new ImageItem();
				imageItem.imageId = _id;		
				imageItem.imagePath = path;
				imageItem.thumbnailPath = thumbnailList.get(_id);
				bucket.imageList.add(imageItem);
			} while (cur.moveToNext());
		}
		cur.close();
		hasBuildImagesBucketList = true;
	}

	
	public List<ImageBucket> getImagesBucketList(boolean refresh) {
		if (refresh || (!refresh && !hasBuildImagesBucketList)) {
			buildImagesBucketList();
		}
		List<ImageBucket> tmpList = new ArrayList<ImageBucket>();
		Iterator<Entry<String, ImageBucket>> itr = bucketList.entrySet()
				.iterator();
		while (itr.hasNext()) {
			Map.Entry<String, ImageBucket> entry = (Map.Entry<String, ImageBucket>) itr
					.next();
			tmpList.add(entry.getValue());
		}
		return tmpList;
	}
}
