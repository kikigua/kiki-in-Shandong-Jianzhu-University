package com.example.MySQLite;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseUtil {
	// 是否已经离线随拍
	public static boolean isLoadedRandom(Context context, String[] selectionArgs) {
		DatabaseHelper dbHelper = null;
		SQLiteDatabase sdb = null;
		Cursor cr = null;
		try {
			dbHelper = new DatabaseHelper(context);
			sdb = dbHelper.getWritableDatabase();
			String sql = "select * from random where id=?";
			cr = sdb.rawQuery(sql, selectionArgs);
			if (cr.moveToFirst()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (cr != null)
					cr.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (sdb != null)
					sdb.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (dbHelper != null)
					dbHelper.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;

	}

	// 添加离线随拍
	public static boolean loadedRandom(Context Context, Object[] bindArgs) {

		DatabaseHelper dbHelper = null;
		SQLiteDatabase sdb = null;
		try {
			dbHelper = new DatabaseHelper(Context);
			sdb = dbHelper.getWritableDatabase();
			String sql = "insert into random (id,";
			sql += "uid,sculture,uploadTime,introduce,";
			sql += "slike,collection,pinglun,";
			sql += "label ,city ,picPath) values(";
			sql += "?,?,?,?,?,?,?,?,?,?,?)";
			System.out.println("sql " + sql);
			sdb.execSQL(sql, bindArgs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (sdb != null)
					sdb.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (dbHelper != null)
					dbHelper.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	// 获取本地所有随拍
	public static List<String[]> getRandom(Context context, String timeDiver) {
		DatabaseHelper dbHelper = null;
		SQLiteDatabase sdb = null;
		Cursor cr = null;
		List<String[]> list = null;
		// String[] str = null;
		try {
			dbHelper = new DatabaseHelper(context);
			String sql = "select uid,uploadTime,introduce,slike,collection,";
			sql += "pinglun,label,city,picPath,sculture,id from random where "
					+ "uploadTime <" + timeDiver
					+ "  order by uploadTime desc Limit 10";
			sdb = dbHelper.getReadableDatabase();
			System.out.println("slq " + sql);
			cr = sdb.rawQuery(sql, null);
			if (cr.moveToFirst()) {
				list = new ArrayList<String[]>();
				do {
					System.out.println("cursor");
					String[] str = new String[11];
					for (int i = 0; i < 11; i++) {
						str[i] = cr.getString(i);
					}
					list.add(str);
				} while (cr.moveToNext());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (cr != null) {
					cr.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (sdb != null)
					sdb.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (dbHelper != null)
					dbHelper.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	// 根据Id获取本地随拍
	public static String[] getRandomById(Context context, String randomId) {
		DatabaseHelper dbHelper = null;
		SQLiteDatabase sdb = null;
		Cursor cr = null;
		try {
			dbHelper = new DatabaseHelper(context);
			String sql = "select uid,uploadTime,introduce,slike,collection,";
			sql += "pinglun,label,city,picPath,sculture,id from random where "
					+ " id=" + randomId;
			sdb = dbHelper.getReadableDatabase();
			System.out.println("slq " + sql);
			cr = sdb.rawQuery(sql, null);
			if (cr.moveToFirst()) {
				String[] str = new String[11];
				for (int i = 0; i < 11; i++) {
					str[i] = cr.getString(i);
				}
				return str;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (cr != null) {
					cr.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (sdb != null)
					sdb.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (dbHelper != null)
					dbHelper.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	//删除本地随拍
	public static boolean deleteRandom(Context context, String randomId) {
		DatabaseHelper dbHelper = null;
		SQLiteDatabase sdb = null;
		try {
			dbHelper = new DatabaseHelper(context);
			String sql = "delete from random where id=" + randomId;
			sdb = dbHelper.getReadableDatabase();
			System.out.println("slq " + sql);
			sdb.execSQL(sql);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (sdb != null)
					sdb.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (dbHelper != null)
					dbHelper.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 是否已经离线菜谱
	public static boolean isLoadedMenu(Context context, String[] selectionArgs) {
		DatabaseHelper dbHelper = null;
		SQLiteDatabase sdb = null;
		Cursor cr = null;
		try {
			dbHelper = new DatabaseHelper(context);
			sdb = dbHelper.getWritableDatabase();
			String sql = "select * from menu where id=?";
			cr = sdb.rawQuery(sql, selectionArgs);
			if (cr.moveToFirst()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (cr != null)
					cr.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (sdb != null)
					sdb.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (dbHelper != null)
					dbHelper.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return false;

	}

	// 添加离线菜谱
	public static boolean loadedMenu(Context Context, Object[] bindArgs) {

		DatabaseHelper dbHelper = null;
		SQLiteDatabase sdb = null;
		try {
			dbHelper = new DatabaseHelper(Context);
			sdb = dbHelper.getWritableDatabase();
			String sql = "insert into menu(id,cname,uid,primaryPic,difficulty,flavour,ctime,"
					+ "tools,craft,food,codiments,introduction,tips,slike,collection,"
					+ "pinglun,uploadTime,sculture) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			sdb.execSQL(sql, bindArgs);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (sdb != null)
					sdb.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (dbHelper != null)
					dbHelper.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	// 添加制作过程
	public static boolean loadProc(Context Context, Object[] bindArgs) {

		DatabaseHelper dbHelper = null;
		SQLiteDatabase sdb = null;
		try {
			String sql = "insert into pro values(?,?,?,?,?"
					+ ",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			dbHelper = new DatabaseHelper(Context);
			sdb = dbHelper.getWritableDatabase();
			sdb.execSQL(sql, bindArgs);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (sdb != null)
					sdb.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (dbHelper != null)
					dbHelper.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	// 获取本地所有菜品
	public static List<String[]> getMenu(Context context, String timeDiver) {
		DatabaseHelper dbHelper = null;
		SQLiteDatabase sdb = null;
		Cursor cr = null;
		List<String[]> list = null;
		try {
			dbHelper = new DatabaseHelper(context);
			String sql = "select primaryPic,uid,cname,introduction"
					+ ",flavour,craft,tools,difficulty,ctime"
					+ ",food,codiments,tips,uploadTime,"
					+ "slike,collection,pinglun,id,sculture from menu where "
					+ "uploadTime <" + timeDiver
					+ "  order by uploadTime desc Limit 10";
			sdb = dbHelper.getReadableDatabase();
			System.out.println("slq " + sql);
			cr = sdb.rawQuery(sql, null);
			if (cr.moveToFirst()) {
				list = new ArrayList<String[]>();
				do {
					System.out.println("cursor");
					String[] str = new String[18];
					for (int i = 0; i < 18; i++) {
						str[i] = cr.getString(i);
					}
					list.add(str);
				} while (cr.moveToNext());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (cr != null) {
					cr.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (sdb != null)
					sdb.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (dbHelper != null)
					dbHelper.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	// 根据id获取本地菜品
	public static String[] getMenuById(Context context, String menuId) {
		DatabaseHelper dbHelper = null;
		SQLiteDatabase sdb = null;
		Cursor cr = null;
		try {
			dbHelper = new DatabaseHelper(context);
			String sql = "select primaryPic,uid,cname,introduction"
					+ ",flavour,craft,tools,difficulty,ctime"
					+ ",food,codiments,tips,uploadTime,"
					+ "slike,collection,pinglun,sculture from menu where "
					+ " id=" + menuId;
			sdb = dbHelper.getReadableDatabase();
			System.out.println("slq " + sql);
			cr = sdb.rawQuery(sql, null);
			if (cr.moveToFirst()) {
				String[] str = new String[18];
				for (int i = 0; i < 17; i++) {
					str[i] = cr.getString(i);
					System.out.println("str[i]" + str[i]);
				}
				return str;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (cr != null) {
					cr.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (sdb != null)
					sdb.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (dbHelper != null)
					dbHelper.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	// 获取本地菜品制作过程
	public static String getPro(Context context, String menuId) {
		DatabaseHelper dbHelper = null;
		SQLiteDatabase sdb = null;
		Cursor cr = null;
		String msg = null;
		try {
			dbHelper = new DatabaseHelper(context);
			String sql = "select * from pro where id=?";
			sdb = dbHelper.getReadableDatabase();
			System.out.println("slq " + sql);
			cr = sdb.rawQuery(sql, new String[] { menuId });
			if (cr.moveToFirst()) {
				StringBuffer sb = new StringBuffer();
				for (int i = 1; i < 21; i++) {
					if (cr.getString(i) == null)
						break;
					sb.append(cr.getString(i) + "%");
					System.out.println("cr.getString(i) " + cr.getString(i));

				}
				msg = sb.substring(0, sb.length() - 1).toString();
				System.out.println("msg " + msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (cr != null) {
					cr.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (sdb != null)
					sdb.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (dbHelper != null)
					dbHelper.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return msg;
	}

	//删除本地菜品
	public static boolean deleteMenu(Context context, String randomId) {
		DatabaseHelper dbHelper = null;
		SQLiteDatabase sdb = null;
		try {
			dbHelper = new DatabaseHelper(context);
			String sql = "delete from menu where id=" + randomId;
			sdb = dbHelper.getReadableDatabase();
			System.out.println("slq " + sql);
			sdb.execSQL(sql);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (sdb != null)
					sdb.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (dbHelper != null)
					dbHelper.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 删除本地菜品制作过程
	public static boolean deletePro(Context context, String randomId) {
		DatabaseHelper dbHelper = null;
		SQLiteDatabase sdb = null;
		try {
			dbHelper = new DatabaseHelper(context);
			String sql = "delete from pro where id=" + randomId;
			sdb = dbHelper.getReadableDatabase();
			System.out.println("slq " + sql);
			sdb.execSQL(sql);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (sdb != null)
					sdb.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (dbHelper != null)
					dbHelper.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	// 其他地方是否用到该图片
	public static Boolean isOtherUse(Context context, String picName) {
		DatabaseHelper dbHelper = null;
		SQLiteDatabase sdb = null;
		Cursor cr = null;
		try {
			dbHelper = new DatabaseHelper(context);
			sdb = dbHelper.getReadableDatabase();

			String[] sqls = new String[] {
					"select * from menu where primaryPic='" + picName + "'",
					"select * from menu where sculture='" + picName + "'",
					"select * from random where sculture='" + picName + "'",
					"select * from random where picPath like '%" + picName
							+ "%'" };
			for (int i = 0; i < sqls.length; i++) {
				cr = sdb.rawQuery(sqls[i], null);
				if (cr.moveToFirst()) {
					return true;
				}
				cr.close();
			}
			String sqlT = "select * from pro where ? like '%" + picName + "%'";
			for (int i = 1; i <= 20; i++) {
				cr = sdb.rawQuery(sqlT, new String[] { "step" + i });
				if (cr.moveToFirst()) {
					return true;
				}
				cr.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (cr != null) {
					cr.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (sdb != null)
					sdb.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (dbHelper != null)
					dbHelper.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	// 自定义标签插入
	public static Boolean insertLabel(Context context, String label) {
		DatabaseHelper dbHelper = null;
		SQLiteDatabase sdb = null;
		try {
			dbHelper = new DatabaseHelper(context);
			String sql = "insert into labels(label) values(?)";
			sdb = dbHelper.getReadableDatabase();
			System.out.println("slq " + sql);
			sdb.execSQL(sql, new Object[] { label });
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (sdb != null)
					sdb.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (dbHelper != null)
					dbHelper.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	// 安装时插入标签信息
	public static Boolean insertLabels(Context context, String[] str) {
		DatabaseHelper dbHelper = null;
		SQLiteDatabase sdb = null;
		try {
			dbHelper = new DatabaseHelper(context);
			sdb = dbHelper.getWritableDatabase();
			String sql = "insert into labels values(?)";
			for (String s : str) {
				String[] bindArgs = new String[] { s };
				sdb.execSQL(sql, bindArgs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (sdb != null)
					sdb.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (dbHelper != null)
					dbHelper.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	// 查找手机数据库中所有标签，
	public static List<String> getLabels(Context context) {
		DatabaseHelper dbHelper = null;
		SQLiteDatabase sdb = null;
		Cursor cr = null;
		try {
			dbHelper = new DatabaseHelper(context);
			String sql = "select * from labels";
			sdb = dbHelper.getReadableDatabase();
			System.out.println("slq " + sql);
			cr = sdb.rawQuery(sql, null);
			if (cr.moveToFirst()) {
				List<String> list = new ArrayList<String>();
				do {
					list.add(cr.getString(0));
				} while (cr.moveToNext());
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (cr != null) {
					cr.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (sdb != null)
					sdb.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (dbHelper != null)
					dbHelper.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
