package com.example.util;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import android.content.Context;
import com.example.MySQLite.DatabaseUtil;

public class NetInfoUtil {

	// 响应
	public static Socket ss = null;
	public static DataInputStream din = null;
	public static DataOutputStream dos = null;
	public static String message = "";
	public static byte[] data;
	public static boolean flag;
	static Lock mLock = new ReentrantLock();

	// 缓冲
	public static Socket cachess = null;
	public static DataInputStream cachedin = null;
	public static DataOutputStream cachedos = null;
	static Lock cacheLock = new ReentrantLock();
	// 后台下载
	public static Socket onLoadss = null;
	public static DataInputStream onLoaddin = null;
	public static DataOutputStream onLoaddos = null;
	static Lock onLoadLock = new ReentrantLock();

	// 通信建立(界面响应)
	public static void connect() throws Exception { // 通信建立
		mLock.lock();
		ss = new Socket();// 创建一个ServerSocket对象
		SocketAddress socketAddress = new InetSocketAddress(
				MusicApplication.socketIp, 8887); // 绑定到指定IP和端口
		ss.connect(socketAddress, 5000);// 设置连接超时时间
		din = new DataInputStream(ss.getInputStream());// 创建新数据输入流
		dos = new DataOutputStream(ss.getOutputStream());// 创建新数据输出流
	}

	// 通信关闭(界面响应)
	public static void disConnect() {
		if (dos != null) { // 判断输出流是否为空
			try {
				dos.flush(); // 清缓冲
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (din != null) { // 判断输入流是否为空
			try {
				din.close(); // 关闭输入流
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (ss != null) { // ServerSocket对象是否为空
			try {
				ss.close();
			} catch (Exception e) {
				e.printStackTrace(); // 关闭ServerSocket连接
			}
		}
		mLock.unlock();
	}

	// 通信建立(缓冲)
	public static void cacheConnect() throws Exception {
		cacheLock.lock();
		cachess = new Socket();// 创建一个ServerSocket对象
		SocketAddress socketAddress = new InetSocketAddress(
				MusicApplication.socketIp, 8888); // 绑定到指定IP和端口
		cachess.connect(socketAddress, 5000);// 设置连接超时时间
		cachedin = new DataInputStream(cachess.getInputStream());// 创建新数据输入流
		cachedos = new DataOutputStream(cachess.getOutputStream());// 创建新数据输出流
	}

	// 通信关闭(缓冲)
	public static void cacheDisConnect() {
		if (cachedos != null) {
			try {
				cachedos.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (cachedin != null) {
			try {
				cachedin.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (cachess != null) {
			try {
				cachess.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		cacheLock.unlock();
	}

	// 通信建立(下载)
	public static void onLoadConnect() throws Exception {
		onLoadLock.lock();
		onLoadss = new Socket();// 创建一个ServerSocket对象
		SocketAddress socketAddress = new InetSocketAddress(
				MusicApplication.socketIp, 8889); // 绑定到指定IP和端口
		onLoadss.connect(socketAddress, 5000);// 设置连接超时时间
		onLoaddin = new DataInputStream(onLoadss.getInputStream());// 创建新数据输入流
		onLoaddos = new DataOutputStream(onLoadss.getOutputStream());// 创建新数据输出流
	}

	// 通信关闭(下载)
	public static void onLoadDisConnect() {
		if (onLoaddos != null) {
			try {
				onLoaddos.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (onLoaddin != null) {
			try {
				onLoaddin.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (onLoadss != null) {
			try {
				onLoadss.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		onLoadLock.unlock();
	}

	// 是否是用户(界面响应)
	public static Boolean isUser(String sname, String password) {
		try {
			connect();
			dos.writeUTF(Constant.IS_USER + sname + "<#>" + password);
			flag = din.readBoolean();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return flag;

	}
//	//安装时添加数据库信息
//	public static void iniInfo(Context context) {
//		try {
//			System.out.println("initDATA");
//			onLoadConnect();
//			onLoaddos.writeUTF(Constant.INIT_INFO);
//			String[] data=onLoaddin.readUTF().split("<#>");
//			DatabaseUtil.insertData(context, "style", data);
//			data=onLoaddin.readUTF().split("<#>");
//			DatabaseUtil.insertData(context, "flavour", data);
//			data=onLoaddin.readUTF().split("<#>");
//			DatabaseUtil.insertData(context, "craft", data);
//			data=onLoaddin.readUTF().split("<#>");
//			DatabaseUtil.insertData(context, "ctime", data);
//			data=onLoaddin.readUTF().split("<#>");
//			DatabaseUtil.insertData(context, "tool", data);
//			data=onLoaddin.readUTF().split("<#>");
//			DatabaseUtil.insertData(context, "difficulty", data);
//			data=onLoaddin.readUTF().split("<#>");
//			DatabaseUtil.insertData(context, "labels", data);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			onLoadDisConnect();
//		}
//	}
	// 获取用户所有信息(缓冲)
	public static List<String> getUser(String sname) {
		List<String> list = new ArrayList<String>();
		try {
			cacheConnect();
			cachedos.writeUTF(Constant.GET_UID_MESSAGE + sname);
			String message = cachedin.readUTF();
			System.out.println("message" + message);
			String[] content = message.split("<#>");
			for (String s : content) {
				list.add(s);
			}
			if (list.size() > 0)
				return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cacheDisConnect();
		}
		return null;
	}

	public static List<String[]> getExcellentMenu() {// 获取推荐的精品菜谱
		try {
			cacheConnect();
			cachedos.writeUTF(Constant.GET_RECOMMEND_MENU);
			message = cachedin.readUTF();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cacheDisConnect();
		}
		return StrListChange.StrToList(message);
	}

	public static String uploadPic(byte[] bb) {// 上传图片(后台)
		String picName = null;
		try {
			onLoadConnect();
			onLoaddos.writeUTF(Constant.INSERT_PIC);
			onLoaddos.writeInt(bb.length);
			onLoaddos.write(bb);
			onLoaddos.flush();
			picName = onLoaddin.readUTF();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			onLoadDisConnect();
		}
		return picName;
	}

	public static byte[] getCachePicture(String picName) {// 获取图片（按名称图片名）(缓冲)
		byte[] data = null;
		try {
			cacheConnect();
			cachedos.writeUTF(Constant.GET_IMAGE + picName);
			data = IOUtil.readBytes(cachedin);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cacheDisConnect();
		}
		return data;
	}

	// 获取缩略图 (缓冲)
	public static byte[] getCacheThumbnail(String picName) {
		byte[] data = null;
		try {
			cacheConnect();
			cachedos.writeUTF(Constant.GET_THUMBNAIL + picName);
			data = IOUtil.readBytes(cachedin);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cacheDisConnect();
		}
		return data;
	}

	//
	// // 获取图片（按名称图片名）(下载)
	public static byte[] getOnLoadPicture(String picName) {
		byte[] data = null;
		try {
			onLoadConnect();
			onLoaddos.writeUTF(Constant.GET_IMAGE + picName);
			data = IOUtil.readBytes(onLoaddin);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			onLoadDisConnect();
		}
		return data;
	}

	// // 获取推荐信息(缓冲)
	public static List<String[]> getRecommend() {
		String message = null;
		try {
			cacheConnect();
			cachedos.writeUTF(Constant.GET_RECOMMEND);
			message = cachedin.readUTF();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cacheDisConnect();
		}
		return StrListChange.StrToList(message);
	}

	// 模糊查询随拍(缓冲)
	public static List<String[]> getRanLike(String timeDiver, int type,
			String arg) {
		List<String[]> list = null;
		String message = null;
		try {
			connect();
			dos.writeUTF(Constant.GET_RANDOM_LIKE + timeDiver + "<#>" + type
					+ "<#>" + arg);
			message = din.readUTF();
			list = StrListChange.StrToList(message);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			disConnect();
		}
		return list;
	}

	// /按编号查询随拍(缓冲)
	public static String getRandomDetailC(String randomId) {
		String message = null;
		try {
			cacheConnect(); // (获取除id外所有内容） 上传者、上传时间、简介、喜爱人数、收藏人数、评论人数、标签、城市、
			// likeUser、图片名
			cachedos.writeUTF(Constant.GET_RANDOM_YEARS + randomId);
			message = cachedin.readUTF();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cacheDisConnect();
		}
		return message;
	}

	// 按编号查询随拍(下载)
	public static String getRandomDetailL(String randomId) {
		String message = null;
		try {
			onLoadConnect(); // (获取除id外所有内容） 上传者、上传时间、简介、喜爱人数、收藏人数、评论人数、标签、城市、
			// likeUser、图片名
			onLoaddos.writeUTF(Constant.GET_RANDOM_YEARS + randomId);
			message = onLoaddin.readUTF();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			onLoadDisConnect();
		}
		return message;
	}

	//
	// // /获取头像名字(根据用户名)
	// public static String getHeadImageName(String userName) {
	// try {
	// connect();
	// dos.writeUTF(Constant.ANDROID_GET_HEAD_IMAGE + userName);
	// message = din.readUTF();
	// } catch (Exception e) {
	// e.printStackTrace();
	// } finally {
	// disConnect();
	// }
	// return message;
	// }
	//
	// // /喜欢随拍 用户名 随拍id(界面响应)
	public static Boolean likeRandom(String userId, String randomId) {
		try {
			connect();
			dos.writeUTF(Constant.LIKE_RANDOM + userId + "<#>" + randomId);
			flag = din.readBoolean();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return flag;
	}

	//
	// // 收藏随拍(界面响应)
	public static Boolean collectionRandom(String user, String randomId) {
		try {
			connect();
			dos.writeUTF(Constant.COLL_RANDOM + user + "<#>" + randomId);
			flag = din.readBoolean();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return flag;
	}

	//
	// // 添加关注信息(界面响应)
	public static Boolean addAttention(String user, String target) {
		try {
			connect();
			dos.writeUTF(Constant.ADD_ATTENTION + user + "<%>" + target);
			flag = din.readBoolean();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		// System.out.println("state "+state);
		return flag;
	}

	// /取消关注信息()
	public static Boolean cancelAttention(String user, String target) {
		try {
			connect();
			dos.writeUTF(Constant.DELETE_ATTENTION + user + "<%>" + target);
			flag = din.readBoolean();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return flag;
	}

	public static String getMenuDetC(String menuId) {// 按编号获取菜品详细信息(缓冲)
		String message = null;
		try {
			cacheConnect();
			cachedos.writeUTF(Constant.SEAECH_MENU_YEARS + menuId);
			message = cachedin.readUTF();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			cacheDisConnect();
		}
		return message;
	}
	//上传菜品(后台)
	public static Boolean uploadMenu(LinkedList<String> list) {
		Boolean flag = false;
		try {
			onLoadConnect();
			StringBuffer sb = new StringBuffer();
			sb.append(list.get(0).toString().trim() + "<%>");
			for (int i = 1; i < list.size(); i++) {
				sb.append(list.get(i) + "<#>");
			}
			System.out.println("sb netInfo "+sb.substring(0, sb.length() - 3));
			onLoaddos.writeUTF(Constant.INSERT_MENU
					+ sb.substring(0, sb.length() - 3).trim());
			flag = onLoaddin.readBoolean();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			onLoadDisConnect();
		}
		return flag;
	}

	public static Boolean likeMenu(String user, String menuId) {// 喜欢菜谱(界面响应)
		try {
			connect();
			dos.writeUTF(Constant.LIKE_MENU + user + "<%>" + menuId);
			flag = din.readBoolean();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			disConnect();
		}
		return flag;
	}

	// 按编号获取菜品详细信息(下载)
	public static String getMenuDetL(String menuId) {
		String message = null;
		try {
			onLoadConnect();
			onLoaddos.writeUTF(Constant.SEAECH_MENU_YEARS + menuId);
			message = onLoaddin.readUTF();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			onLoadDisConnect();
		}
		return message;
	}

	// 获取制作过程(缓冲)
	public static String getMenuProC(String menuId) {
		String message = null;
		try {
			cacheConnect();
			cachedos.writeUTF(Constant.GET_PROCESS + menuId);
			message = cachedin.readUTF();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			cacheDisConnect();
		}
		return message;
	}//获取制作过程(缓冲)

	public static String getMenuProL(String menuId) {
		String message = null;
		try {
			onLoadConnect();
			onLoaddos.writeUTF(Constant.GET_PROCESS + menuId);
			message = onLoaddin.readUTF();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			onLoadDisConnect();
		}
		return message;
	}

	// 收藏菜品(界面响应)
	public static Boolean collectionMenu(String user, String menuId) {
		try {
			connect();
			dos.writeUTF(Constant.COLLECTION_MENU + user + "<%>" + menuId);
			flag = din.readBoolean();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			disConnect();
		}
		return flag;
	}

	 // 模糊搜索菜品(缓冲)
	public static List<String[]> getMenuLike(String timeDiver, int type,
			String args) {
		String message = null;
		try {
			cacheConnect();
			cachedos.writeUTF(Constant.GET_MENU_LIKE + timeDiver + "<#>" + type
					+ "<#>" + args);
			message = cachedin.readUTF();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cacheDisConnect();
		}
		List<String[]> list = StrListChange.StrToList(message);
		// System.out.println("message " + message);
		return list;
	}


	// 上传制作过程(后台)
	public static int uploadPro(List<String> picpath, List<String> introduces) {
		int id = 0;
		try {
			onLoadConnect();
			StringBuilder str = new StringBuilder();
			for (int i = 0; i < picpath.size(); i++) {
				str.append(picpath.get(i) + "|" + introduces.get(i) + "<#>");
			}
			onLoaddos.writeUTF(Constant.INSERT_PRO
					+ str.substring(0, str.length() - 3));
			id = onLoaddin.readInt();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			onLoadDisConnect();
		}
		return id;
	}
	
	// 获取收藏菜谱(缓冲)
	public static String getMenuC(String lastId, String uid) {
		String message = null;
		try {
			cacheConnect();
			cachedos.writeUTF(Constant.GET_MENUC + lastId + "<#>" + uid);
			message = cachedin.readUTF();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			cacheDisConnect();
		}
		return message;
	}

	//
	// // 获取收藏随拍
	public static String getRandomC(String lastId, String uid) {
		String message = null;
		try {
			cacheConnect();
			cachedos.writeUTF(Constant.GET_RANDOMC + lastId + "<#>" + uid);
			message = cachedin.readUTF();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			cacheDisConnect();
		}
		return message;
	}

	//
	// // 菜谱添加评论(后台)
	public static Boolean commentMenu(String menuId, String uid,
			String content, String picName) {
		Boolean flag = false;
		try {
			onLoadConnect();
			onLoaddos.writeUTF(Constant.MENU_COMENT + menuId + "<#>" + uid
					+ "<#>" + content + "<#>" + picName);
			flag = onLoaddin.readBoolean();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			onLoadDisConnect();
		}
		// System.out.println("falg " + flag);
		return flag;
	}

	//
	// // 评论随拍(后台)
	public static Boolean commentRandom(String randomId, String uid,
			String content, String picName) {
		Boolean flag = false;
		try {
			onLoadConnect();
			onLoaddos.writeUTF(Constant.RANDOM_COMMENT + randomId + "<#>" + uid
					+ "<#>" + content + "<#>" + picName);
			flag = onLoaddin.readBoolean();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			onLoadDisConnect();
		}
		return flag;
	}

	// 获取菜谱评论(缓冲)
	public static List<String[]> getCommentM(String menuId, String timeDiver) {
		String message = null;
		try {
			cacheConnect();
			cachedos.writeUTF(Constant.GET_COMMENT_M + menuId + "<#>"
					+ timeDiver);
			message = cachedin.readUTF();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			cacheDisConnect();
		}
		return StrListChange.StrToList(message);
	}

	//
	// // 获取随拍评论(缓冲)
	public static List<String[]> getCommentR(String randomId, String timeDiver) {
		String message = null;
		try {
			cacheConnect();
			cachedos.writeUTF(Constant.GET_COMMENT_R + randomId + "<#>"
					+ timeDiver);
			message = cachedin.readUTF();
			System.out.println("message " + message);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			cacheDisConnect();
		}
		return StrListChange.StrToList(message);
	}

	//
	// // 取消菜谱收藏(界面响应)
	public static Boolean cancelCollectionM(String uid, String menuId) {
		try {
			connect();
			dos.writeUTF(Constant.DELETE_MENUC + uid + "<%>" + menuId);
			flag = din.readBoolean();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return flag;
	}

	// 插入随拍(后台)
	public static Boolean insertRandom(String[] contents) {
		Boolean flag = null;
		try {
			onLoadConnect();
			StringBuilder str = new StringBuilder();
			for (String s : contents) {
				str.append(s + "<#>");				
			}
			System.out.println("NetInfo Constant.INSERT_RANDOM " +Constant.INSERT_RANDOM + str);
			onLoaddos.writeUTF(Constant.INSERT_RANDOM + str);
			flag = onLoaddin.readBoolean();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			onLoadDisConnect();
		}
		return flag;
	}

	// // 取消随拍收藏(界面响应)
	public static Boolean cancelCollectionR(String uid, String ranId) {
		try {
			connect();
			dos.writeUTF(Constant.DELETE_RANDOMC + uid + "<%>" + ranId);
			flag = din.readBoolean();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return flag;
	}

	// 获取推荐的精品随拍
	public static List<String[]> getExcellentRandom() {
		try {
			cacheConnect();
			cachedos.writeUTF(Constant.GET_RECOMMEND_RANDOM);
			message = cachedin.readUTF();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cacheDisConnect();
		}
		return StrListChange.StrToList(message);
	}

	// 注册
	public static Boolean register(String name, String passward,
			String sculture, String sex) {
		try {
			cacheConnect();
			cachedos.writeUTF(Constant.REGIST + name + "<#>" + passward + "<#>"
					+ sculture + "<#>" + sex);
			flag = cachedin.readBoolean();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cacheDisConnect();
		}
		return flag;
	}
}