package com.example.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.example.MySQLite.DatabaseUtil;
import android.content.Context;
import android.os.Handler;
import android.widget.Toast;
/*
 * 后台下载工具类
 */
public class OnLoadUtil{ 
	Handler mhandler;
	Context context;
	static ExecutorService singleThreadExecutor = Executors
			.newSingleThreadExecutor();	
	public OnLoadUtil(Context context) {
		this.context = context;
		mhandler = new Handler();
	}
	// 随拍下载至本地
	public void onLoadRandom(final String randomId) {
		if (singleThreadExecutor == null || singleThreadExecutor.isShutdown()) {
			singleThreadExecutor = Executors.newSingleThreadExecutor();
		}
		singleThreadExecutor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					String info = NetInfoUtil.getRandomDetailL(randomId);
					String[] str = info.split("<#>");
					String[] pics = str[8].split("%");
					for (String s : pics) {
						BitmapUtils.savePic(s);
					}
					BitmapUtils.savePic(str[9]);//下载头像
					Object[] bindArgs = new Object[11];
					bindArgs[0] = randomId; // id
					bindArgs[1] = str[0]; // uid
					bindArgs[2] = str[9]; // uidName
					bindArgs[3] = str[1]; // uploadTime
					bindArgs[4] = str[2]; // introduce
					bindArgs[5] = str[3]; // slike
					bindArgs[6] = str[4]; // collection
					bindArgs[7] = str[5]; // pinglun
					bindArgs[8] = str[6]; // label
					bindArgs[9] = str[7]; // city
					bindArgs[10] = str[8]; // picPath
					DatabaseUtil.loadedRandom(context, bindArgs);
					mhandler.post(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(context, "随拍下载成功",
									Toast.LENGTH_SHORT).show();
						}

					});
				} catch (Exception e) {
					e.printStackTrace();
					mhandler.post(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(context, "随拍下载失败",
									Toast.LENGTH_SHORT).show();
						}
					});
				}
			}
		});
	}
	// 下载菜谱到本地
	public void loadMenu(final String menuId) {
		if (singleThreadExecutor == null || singleThreadExecutor.isShutdown()) {
			singleThreadExecutor = Executors.newSingleThreadExecutor();
		}
		singleThreadExecutor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					String info = NetInfoUtil.getMenuDetL(menuId);
					String[] str = info.split("<#>");
					Object[] bindArgs = new Object[] { menuId, str[2], str[1],
							str[0], str[7], str[4], str[8], str[6], str[5],
							str[9], str[10], str[3], str[11], str[13], str[14],
							str[15], str[12], str[16] };
					BitmapUtils.savePic(str[0]);		//下载主图
					BitmapUtils.savePic(str[16]);		//下载头像
					if (DatabaseUtil.loadedMenu(context, bindArgs)) {
						String string = NetInfoUtil.getMenuProL(menuId);
						Object[] bind = new Object[21];
						String[] sss = string.split("%");
						bind[0] = menuId;
						for (int i = 0; i < 20; i++) {
							if (i < sss.length) {
								String[] s = sss[i].split("\\|");
								BitmapUtils.savePic(s[0]);
								bind[i + 1] = sss[i];
							} else {
								bind[i + 1] = null;
							}
						}
						DatabaseUtil.loadProc(context, bind);
						mhandler.post(new Runnable() {
							@Override
							public void run() {
								Toast.makeText(context, "菜品下载成功",
										Toast.LENGTH_SHORT).show();
							}
						});
					}
				} catch (Exception e) {
					e.printStackTrace();
					mhandler.post(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(context, "菜品下载失败",
									Toast.LENGTH_SHORT).show();
						}
					});
				}
			}
		});
	}

	// 删除本地随拍
	public void deleteRandom(final String randomId) {
		if (singleThreadExecutor == null || singleThreadExecutor.isShutdown()) {
			singleThreadExecutor = Executors.newSingleThreadExecutor();
		}
		singleThreadExecutor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("deleterandomI() randomId " + randomId);
					String[] mes = DatabaseUtil
							.getRandomById(context, randomId);
					String picNames = mes[8];
					String uidP = mes[9];
					List<String> picList = new ArrayList<String>();
					picList.add(uidP);
					for (String s : picNames.split("%")) {
						picList.add(s);
					}
					DatabaseUtil.deleteRandom(context, randomId);
					for (String s : picList) {
						System.out.println("picName " + s);
						if (!DatabaseUtil.isOtherUse(context, s))
							BitmapUtils.deletePic(s);
					}
					mhandler.post(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(context, "随拍删除成功",
									Toast.LENGTH_SHORT).show();
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
					mhandler.post(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(context, "随拍删除失败",
									Toast.LENGTH_SHORT).show();
						}
					});
				}
			}
		});
	}

	// 删除本地菜谱
	public void deleteMenu(final String menuId) {
		if (singleThreadExecutor == null || singleThreadExecutor.isShutdown()) {
			singleThreadExecutor = Executors.newSingleThreadExecutor();
		}
		singleThreadExecutor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("id " + menuId);
					System.out.println("deletermenuId() randomId " + menuId);
					String[] mes = DatabaseUtil.getMenuById(context, menuId);
					String primaryPic = mes[0];
					String sculture = mes[17];
					List<String> picNames = new ArrayList<String>();
					picNames.add(primaryPic);
					picNames.add(sculture);

					String pro = DatabaseUtil.getPro(context, menuId);
					System.out.println("pro de" + pro);
					String[] str = pro.split("%");
					for (String s : str) {
						picNames.add(s.split("\\|")[0]);
					}
					DatabaseUtil.deleteMenu(context, menuId);
					DatabaseUtil.deletePro(context, menuId);
					for (String s : picNames) {
						if (!DatabaseUtil.isOtherUse(context, s))
							BitmapUtils.deletePic(s);
					}
					mhandler.post(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(context, "菜品删除成功",
									Toast.LENGTH_SHORT).show();
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
					mhandler.post(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(context, "菜品删除失败",
									Toast.LENGTH_SHORT).show();
						}
					});
				}
			}
		});
	}
}