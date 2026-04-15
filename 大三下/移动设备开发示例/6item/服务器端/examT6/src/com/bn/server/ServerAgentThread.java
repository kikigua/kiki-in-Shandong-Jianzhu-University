package com.bn.server;

import java.io.*;
import java.net.*;
import com.bn.databaseutil.*;
import com.bn.util.*;

public class ServerAgentThread extends Thread {
	Socket sc;
	DataInputStream din;
	DataOutputStream dout;
	FileInputStream fis;
	String picPath;
	File file;
	byte[] bb;
	Boolean flag;
	String thumbnailpath;

	public ServerAgentThread(Socket sc) {
		this.sc = sc;
	}

	public void run() {
		try {
			din = new DataInputStream(sc.getInputStream()); // 创建数据输入流
			dout = new DataOutputStream(sc.getOutputStream()); // 创建数据输出流
			String msg = din.readUTF(); // 将数据放入字符串
			System.out.println("msg " + msg);
			// /按编号获取图片 android pc
			if (msg.startsWith(Constant.GET_IMAGE)) {
				String remsg = msg.substring(Constant.GET_IMAGE.length(), msg.length());
				File fileResource = new File("resource"); // 创建文件流
				picPath = fileResource.getAbsolutePath() + "\\IMAGE\\"; // 文件路径
				file = new File(picPath + remsg);
				System.out.println("file " + file.getAbsolutePath());
				fis = new FileInputStream(file);
				byte[] bb = new byte[fis.available()];
				fis.read(bb);
				dout.writeInt(bb.length);
				dout.write(bb);
				fis.close();
			}
			// 按名称获取图片 缩略图 pc
			// 图片名是跟在“GET_THUMBNAIL”后面的，形式为：Constant.GET_THUMBNAIL + picName
			else if (msg.startsWith(Constant.GET_THUMBNAIL)) {
				String picName = msg.substring(Constant.GET_THUMBNAIL.length(), msg.length());
				File fileResource = new File("resource"); // 创建文件流
				picPath = fileResource.getAbsolutePath() + "\\IMAGE\\"; // 文件路径
				thumbnailpath = fileResource.getAbsolutePath() + "\\IMAGE\\thumbnail\\";
				file = new File(thumbnailpath + picName);
				if (!file.exists()) {
					Thumbanils.saveImageAsJpg(picPath + picName, thumbnailpath + picName, 200, 200);
					file = new File(thumbnailpath + picName);
				}
				fis = new FileInputStream(file);
				byte[] bb = new byte[fis.available()];
				fis.read(bb);
				dout.writeInt(bb.length);
				dout.write(bb);
				fis.close();
			}
			//获取推荐新闻
			else if (msg.startsWith(Constant.GET_RECOMMEND_NEWS)) {
				String rem = DBUtil.searchAllNews();
				dout.writeUTF(rem);
			}
			//按编号查询新闻
			else if (msg.startsWith(Constant.SEAECH_NEWS_YEARS)) {
				String id = msg.substring(Constant.SEAECH_NEWS_YEARS.length(), msg.length());
				String remsg = DBUtil.searchNews(id);
				dout.writeUTF(remsg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				din.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				dout.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				sc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}