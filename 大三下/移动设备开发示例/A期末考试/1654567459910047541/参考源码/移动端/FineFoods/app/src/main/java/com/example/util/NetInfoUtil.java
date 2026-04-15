package com.example.util;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import android.content.Context;
import com.king.photo.util.StrListChange;

public class NetInfoUtil {

	public static String message = "";

	// 缓冲：针对8888端口的输入输出数据流
	// 创建处理界面加载任务的输入输出数据流
	public static Socket cachess = null;
	public static DataInputStream cachedin = null;
	public static DataOutputStream cachedos = null;
	static Lock cacheLock = new ReentrantLock();

	// 通信建立(缓冲)
	public static void cacheConnect() throws Exception {
		cacheLock.lock();
		cachess = new Socket();// 创建一个ServerSocket对象
		SocketAddress socketAddress = new InetSocketAddress(
				MusicApplication.socketIp, 8888); // 绑定到指定IP和端口
		cachess.connect(socketAddress, 5000);// 设置连接超时时间
		// 创建新数据输入流
		cachedin = new DataInputStream(cachess.getInputStream());
		// 创建新数据输出流
		cachedos = new DataOutputStream(cachess.getOutputStream());
	}

	// 通信关闭(缓冲)
	public static void cacheDisConnect() {
		if (cachedos != null) {
			try {	cachedos.flush();	}
			catch (Exception e) {	e.printStackTrace();	}
		}
		if (cachedin != null) {
			try {	cachedin.close();	}
			catch (Exception e) {	e.printStackTrace();	}
		}
		if (cachess != null) {
			try {	cachess.close();    }
			catch (Exception e) {   e.printStackTrace();	}
		}
		cacheLock.unlock();
	}

	public static List<String[]> getAllMenu() {// 获取全部菜品
		try {
			cacheConnect();   //连接服务器
			//向服务器发送参数值：Constant.GET_ALL_MENU，请求“全部菜品”
			//服务器端ServerAgentThread通过识别参数值Constant.GET_ALL_MENU，确定调用服务器端的DBUtil.getAllMenu()
			//DBUtil.getAllMenu()执行SQL语句“select id from menu order by id”来返回全部菜品
			cachedos.writeUTF(Constant.GET_ALL_MENU);
			message = cachedin.readUTF();  //从服务器返回从数据库中获取的“全部菜品”
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cacheDisConnect();
		}
		return StrListChange.StrToList(message);   //将“全部菜品”由字符串转化为list并返回。
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

	// 获取缩略图 (缓冲)，所谓缩略图意思是固定大小的较小的图片。
	public static byte[] getCacheThumbnail(String picName) {
		byte[] data = null;
		try {
			cacheConnect();
			///图片名跟在“GET_THUMBNAIL”后面！
			cachedos.writeUTF(Constant.GET_THUMBNAIL + picName);
			data = IOUtil.readBytes(cachedin);  //读取图片数据
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cacheDisConnect();
		}
		return data;
	}


}