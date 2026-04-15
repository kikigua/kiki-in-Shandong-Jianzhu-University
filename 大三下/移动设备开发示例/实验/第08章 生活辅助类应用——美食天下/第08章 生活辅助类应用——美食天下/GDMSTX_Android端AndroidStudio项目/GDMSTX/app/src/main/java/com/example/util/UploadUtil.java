package com.example.util;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.king.photo.util.ImageItem;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.widget.Toast;
/*
 * 后台上传工具类
 * 	
 */
public class UploadUtil {
	Handler mhandler;
	Context context;
	static ExecutorService singleThreadExecutor = Executors
			.newSingleThreadExecutor();
	public UploadUtil(Context context) {
		this.context = context;
		mhandler = new Handler();
	}

	// 上传图片
	public static String uploadPic(String path) {
		String name = null;
		try {
			Bitmap bitmap = BitmapUtils.revitionImageSize(path);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int options = 100;
			bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
			while (baos.toByteArray().length / 1024 > 100) {
				baos.reset();
				options -= 10;
				bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
			}
			byte[] bb = baos.toByteArray();
			name=NetInfoUtil.uploadPic( bb);
			if (name!=null&&name.equals(Constant.NO_MESSAGE)) {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}

	// 上传随拍
	public void uploadRandom(final String[] contents,
			final List<ImageItem> items) {
		if (singleThreadExecutor == null || singleThreadExecutor.isShutdown()) {
			singleThreadExecutor = Executors.newCachedThreadPool();
		}
		singleThreadExecutor.execute(new Runnable() {
			String tip = "随拍上传失败";

			@Override
			public void run() {
				try {
					List<String> picpaths = new ArrayList<String>();
					for (ImageItem item : items) {
						String name = uploadPic(item.getImagePath());
						picpaths.add(name);
					}
					if (picpaths != null && picpaths.size() > 0) {
						StringBuffer sb = new StringBuffer();
						for (String s : picpaths) {
							sb.append(s + "%");
						}
						contents[4] = sb.substring(0, sb.length() - 1);
						if (NetInfoUtil.insertRandom(contents))
							tip = "随拍上传成功";
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					mhandler.post(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(context, tip, Toast.LENGTH_SHORT)
									.show();
						}
					});
				}
			}
		});
	}

	// 上传菜谱
	public void uploadMenu(final LinkedList<String> content,
			final List<String> picPaths, final List<String> introduces,
			final String primary) {

		if (singleThreadExecutor == null || singleThreadExecutor.isShutdown()) {
			singleThreadExecutor = Executors.newCachedThreadPool();
		}
		singleThreadExecutor.execute(new Runnable() {
			String tip = "菜品上传失败";

			@Override
			public void run() {
				try {
					String primaryName = uploadPic(primary);
					List<String> picpaths = new ArrayList<String>();
					for (String path : picPaths) {
						String name = uploadPic(path);
						picpaths.add(name);
					}
					if (picpaths != null) { // id,uid,primary,flavour,craft,ctime,tools,diffictult,tips
						int id = NetInfoUtil.uploadPro(picpaths, introduces);
						LinkedList<String> contents = new LinkedList<String>(
								content);
						contents.addFirst(primaryName);
						contents.addFirst(id + "");
						if (NetInfoUtil.uploadMenu(contents))
							tip = "菜品上传成功";
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					mhandler.post(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(context, tip, Toast.LENGTH_SHORT)
									.show();
						}
					});
				}
			}
		});
	}

	// 上传随拍评论
	public void uploadRandomComment(final Context context,final String id,final String uid,final String content,final String picPath)
	{
		if (singleThreadExecutor == null || singleThreadExecutor.isShutdown()) {
			singleThreadExecutor = Executors.newCachedThreadPool();
		}
		singleThreadExecutor.execute(new Runnable() {
			String tip=null;

			@Override
			public void run() {
				try {
					String picName=uploadPic(picPath);
					Boolean b = NetInfoUtil.commentRandom(id, uid, content,
							picName);
					tip = b ? "评论上传成功" : "评论上传失败";
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					mhandler.post(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(context, tip, Toast.LENGTH_SHORT)
									.show();
						}
					});
				}
			}
		});
	}
	// 上传菜品评论
		public void uploadMenuComment(final Context context,final String id,final String uid,final String content,final String picPath)
		{
			if (singleThreadExecutor == null || singleThreadExecutor.isShutdown()) {
				singleThreadExecutor = Executors.newCachedThreadPool();
			}
			singleThreadExecutor.execute(new Runnable() {
				String tip=null;
				@Override
				public void run() {
					try {
						String picName=uploadPic(picPath);
						Boolean b=NetInfoUtil.commentMenu(id, uid, content, picName);
						tip=b?"评论上传成功":"评论上传失败";
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						mhandler.post(new Runnable() {
							@Override
							public void run() {
								Toast.makeText(context, tip, Toast.LENGTH_SHORT)
										.show();
							}
						});
					}
				}
			});
		}
	// 取消菜品收藏
	public void cancelMenuC(final String uid, final String menuId) {
		if (singleThreadExecutor == null || singleThreadExecutor.isShutdown()) {
			singleThreadExecutor = Executors.newCachedThreadPool();
		}
		singleThreadExecutor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					NetInfoUtil.cancelCollectionM(uid, menuId);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// 取消随拍收藏
	public void cancelRandomcC(final String uid, final String randId) {
		if (singleThreadExecutor == null || singleThreadExecutor.isShutdown()) {
			singleThreadExecutor = Executors.newCachedThreadPool();
		}
		singleThreadExecutor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					NetInfoUtil.cancelCollectionR(uid, randId);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}