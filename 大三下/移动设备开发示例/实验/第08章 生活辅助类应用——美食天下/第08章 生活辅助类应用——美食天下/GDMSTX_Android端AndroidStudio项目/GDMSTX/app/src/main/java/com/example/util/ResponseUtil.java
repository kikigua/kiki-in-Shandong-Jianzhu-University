package com.example.util;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.content.Context;
import android.content.SharedPreferences;

/*
 * 菜谱、随拍菜单 工具类
 */
public class ResponseUtil {
	static ExecutorService singleThreadExecutor = Executors
			.newSingleThreadExecutor();

	// 喜好菜品
	public static void likeMenu(final String uid, final String menuId) {
		if (singleThreadExecutor.isShutdown() || singleThreadExecutor == null) {
			singleThreadExecutor = Executors.newSingleThreadExecutor();
		}
		singleThreadExecutor.execute(new Runnable() {
			@Override
			public void run() {
				NetInfoUtil.likeMenu(uid, menuId);
			}
		});
	}

	// 收藏菜品
	public static void collectionMenu(final String uid, final String menuId) {
		if (singleThreadExecutor.isShutdown() || singleThreadExecutor == null) {
			singleThreadExecutor = Executors.newSingleThreadExecutor();
		}
		singleThreadExecutor.execute(new Runnable() {
			@Override
			public void run() {
				NetInfoUtil.collectionMenu(uid, menuId);
			}
		});
	}

	// 取消收藏菜品
	public static void cancellCollectionMenu(final String uid,
			final String menuId) {
		if (singleThreadExecutor.isShutdown() || singleThreadExecutor == null) {
			singleThreadExecutor = Executors.newSingleThreadExecutor();
		}
		singleThreadExecutor.execute(new Runnable() {
			@Override
			public void run() {
				NetInfoUtil.cancelCollectionM(uid, menuId);
			}
		});
	}

	// 关注
	public static void attention(final Context context,final String user, final String target) {
		if (singleThreadExecutor.isShutdown() || singleThreadExecutor == null) {
			singleThreadExecutor = Executors.newSingleThreadExecutor();
		}
		singleThreadExecutor.execute(new Runnable() {
			@Override
			public void run() {
				NetInfoUtil.addAttention(user, target);
				List<String> list=NetInfoUtil.getUser(user);
				ResponseUtil.writeUserInfo(context,list);			}
		});
	}

	// 取消关注
	public static void cancelAttention(final Context context,final String user, final String target) {
		if (singleThreadExecutor.isShutdown() || singleThreadExecutor == null) {
			singleThreadExecutor = Executors.newSingleThreadExecutor();
		}
		singleThreadExecutor.execute(new Runnable() {
			@Override
			public void run() {
				NetInfoUtil.cancelAttention(user, target);
				List<String> list=NetInfoUtil.getUser(user);
				ResponseUtil.writeUserInfo(context,list);	
				
			}
		});
	}

	// 喜好随拍
	public static void likeRandom(final String uid, final String randomId) {
		if (singleThreadExecutor.isShutdown() || singleThreadExecutor == null) {
			singleThreadExecutor = Executors.newSingleThreadExecutor();
		}
		singleThreadExecutor.execute(new Runnable() {
			@Override
			public void run() {
				NetInfoUtil.likeRandom(uid, randomId);
			}
		});
	}

	// 收藏随拍
	public static void collectionRandom(final String uid, final String randomId) {
		if (singleThreadExecutor.isShutdown() || singleThreadExecutor == null) {
			singleThreadExecutor = Executors.newSingleThreadExecutor();
		}
		singleThreadExecutor.execute(new Runnable() {
			@Override
			public void run() {
				NetInfoUtil.collectionRandom(uid, randomId);
			}
		});
	}

	// 取消随拍收藏
	public static void cancelCollectionRandom(final String uid,
			final String randomId) {
		if (singleThreadExecutor.isShutdown() || singleThreadExecutor == null) {
			singleThreadExecutor = Executors.newSingleThreadExecutor();
		}
		singleThreadExecutor.execute(new Runnable() {
			@Override
			public void run() {
				NetInfoUtil.cancelCollectionR(uid, randomId);
			}
		});
	}

	public static void cancelTask() {
		if (singleThreadExecutor != null || !singleThreadExecutor.isShutdown()) {
			singleThreadExecutor.shutdown();
			singleThreadExecutor = null;
		}
	}

	// /写入用户名和密码及各项信息
	public static void writeUserInfo(Context context, List<String> list) {
		int k = 0;
		SharedPreferences sp = context.getSharedPreferences("mstx",
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.putString("username", list.get(k++));
		editor.putString("password", list.get(k++));
		editor.putString("sculpture", list.get(k++));
		editor.putString("attention", list.get(k++));
		editor.putString("fans", list.get(k++));
		editor.putString("menu", list.get(k++));
		editor.putString("random", list.get(k++));
		editor.putString("menuc", list.get(k++));
		editor.putString("randomc", list.get(k++));
		editor.commit();
	}
}