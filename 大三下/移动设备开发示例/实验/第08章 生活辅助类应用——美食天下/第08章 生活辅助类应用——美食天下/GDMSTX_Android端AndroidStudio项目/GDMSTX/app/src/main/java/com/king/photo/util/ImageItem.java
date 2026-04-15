package com.king.photo.util;

import java.io.IOException;
import java.io.Serializable;

import com.example.util.BitmapCache;
import com.example.util.BitmapUtils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
/*
 * 图片 在手机中的 id、缩略图路径 、原图路径、及Bitmap形式，
 */

public class ImageItem implements Serializable  {
	public String imageId;			
	public String thumbnailPath;	
	public String imagePath;
	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	public String getThumbnailPath() {
		return thumbnailPath;
	}
	public void setThumbnailPath(String thumbnailPath) {
		this.thumbnailPath = thumbnailPath;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
}
