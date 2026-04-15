package com.bn.NetUtil;

import java.io.*;
import java.net.*;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.bn.util.*;

public class NetIO {
	public static Socket ss = null;
	public static DataInputStream din;
	public static DataOutputStream dos;
	public static String message = "";
	public static int count;
	public static byte[] data;
	public static Boolean falg;
	static Lock mLock=new ReentrantLock();

	// 通信建立
	public static void connect() throws Exception {
		mLock.lock();
	//	ss=new Socket("192.168.191.1",8888);
		ss = new Socket("127.0.0.1", 8888);
		din = new DataInputStream(ss.getInputStream());
		dos = new DataOutputStream(ss.getOutputStream());
		
	}

	// 通信关闭
	public static void disConnect() {
		if (dos != null)
			try {
				
				dos.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		if (din != null)
			try {
				din.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		if (ss != null)
			try {
				ss.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		mLock.unlock();
	}

	// 判断是否是管理员
	public static Boolean isManager(String info) {

		try {
			connect();
			dos.writeUTF(Constant.IS_MANAGER + info);
			falg = din.readBoolean();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return falg;
	}

	// 按编号查找菜谱
	public static String searchMenu(String menuId) {
		try {
			connect();
			dos.writeUTF(Constant.SEAECH_MENU + menuId);
			message = din.readUTF();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return message;
	}

	// //条件查找菜谱 gck 根据菜系、口味、时间、难度、专题
	public static String[][] getMsgSelect(String info) {
		try {
			connect();
			dos.writeUTF(Constant.GET_MSG_SELECT + info);
			message = din.readUTF();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		System.out.println("message " + message);
		return TranslateUtil.getContent(message, "<%>", "<#>");
	}

	// //条件查找用户信息
	public static String[][] getUser(String sex, String dateformate, String date) {
		try {
			connect();
			dos.writeUTF(Constant.GET_USER_SELECT + sex + "<#>" + dateformate
					+ "<#>" + date);
			message = din.readUTF();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return TranslateUtil.getContent(message, "<%>", "<#>");
	}

	// ///根据菜谱编号得到制作过程
	public static String getProcess(String id) {
		try {
			connect();
			dos.writeUTF(Constant.GET_PROCESS + id);
			message = din.readUTF();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return message;
	}

	// //按照图片名称获取图片
	public static byte[] getImagebyte(String info) {
		try {
			connect();
			dos.writeUTF(Constant.GET_IMAGE + info);
			data = NetIOUtil.readBytes(din);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return data;
	}

	// //按编号删除菜品
	public static int deleteMenu(String info) {
		try {
			connect();
			dos.writeUTF(Constant.DELETR_MENU + info);
			count = din.readInt();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return count;
	}

	// //添加菜品
	public static Boolean insertMenu(String info) {
		try {
			connect();
			dos.writeUTF(Constant.INSERT_MENU + info);	
			falg = din.readBoolean();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			disConnect();
		}
		return falg;
	}

	// 插入图片
	public static String insertPic(byte[] bb) {
		try {
			connect();
			dos.writeUTF(Constant.INSERT_PIC);
			dos.writeInt(bb.length);
			dos.write(bb);
			dos.flush();
			System.out.println("bb "+bb.length);
			message=din.readUTF();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			disConnect();
		}
		return message;
	}

	// 插入制作过程
	public static int insertProcess(String info) {
		int id = -1;
		try {
			connect();
			dos.writeUTF(Constant.INSERT_PRO + info);
			id = din.readInt();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return id;
	}

	// //按编号查询随拍
	public static String getRandomById(String rnadomId) {
		try {
			connect();
			dos.writeUTF(Constant.GET_RANDOM + rnadomId);
			message = din.readUTF();
			System.out.println("message "+message);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return message;
	}

	// 根据城市，标签,状态,查询随拍
	public static String[][] getRandomByCondition(String city, String label,
			String state) {
		try {
			connect();
			dos.writeUTF(Constant.GET_RANDOMBY_CODITION + city + "<#>" + label
					+ "<#>" + state);
			message = din.readUTF();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return TranslateUtil.getContent(message, "<%>", "<#>");
	}

	// 获取个人的各项信息
	public static String getUidMessage(String info) {
		try {
			connect();
			dos.writeUTF(Constant.GET_UID_MESSAGE + info);
			message = din.readUTF();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return message;
	}

	// //获取关注信息
	public static String getAttention(String info) {
		try {
			connect();
			dos.writeUTF(Constant.GET_ATTENTION + info);
			message = din.readUTF();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return message;
	}

	// //取消关注
	public static Boolean cancelAttention(String uid,String target) {
		try {
			connect();
			dos.writeUTF(Constant.DELETE_ATTENTION + uid+"<%>"+target);
			falg = din.readBoolean();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return falg;
	}

	// //获取粉丝
	public static String getFans(String info) {
		try {
			connect();
			dos.writeUTF(Constant.GET_FANS + info);
			message = din.readUTF();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return message;
	}

	// //获取菜谱收藏
	public static String getMenuC(String uid) {
		try {
			connect();
			dos.writeUTF(Constant.GET_MENU_COMMENT + uid);
			message = din.readUTF();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return message;
	}

	// //获取随拍收藏
	public static String getRandomC(String uid) {
		try {
			connect();
			dos.writeUTF(Constant.GET_RANDOM_RECOMMENT+uid);
			message = din.readUTF();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return message;
	}

	// //取消菜谱收藏
	public static Boolean deleteMenuC(String uid,String menuId) {
		Boolean flag = false;
		try {
			connect();
			dos.writeUTF(Constant.DELETE_MENUC +uid+"<%>"+ menuId);
			flag = din.readBoolean();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return flag;
	}

	//取消随拍收藏
	public static Boolean deleteRandomC(String uid,String randomId) {
		Boolean flag = false;
		try {
			connect();
			dos.writeUTF(Constant.DELETE_RANDOMC + uid+"<%>"+randomId);
			flag = din.readBoolean();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return flag;
	}

	public static String getMenuByUid(String uid) {
		try {
			connect();
			dos.writeUTF(Constant.GET_MENUBY_UID + uid);
			message = din.readUTF();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return message;
	//	return TranslateUtil.getContent(message, "<%>", "<#>");
	}

	public static String getRandomByUid(String uid) {
		try {
			connect();
			dos.writeUTF(Constant.GET_RANDOMBY_UID + uid);
			message = din.readUTF();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		System.out.println("message "+message);

		return message;
	}

	// 获取主推荐内容
	public static List<String[]> getPrimaryRecomment() {
		try {
			connect();
			dos.writeUTF(Constant.GET_RECOMMEND);
			message = din.readUTF();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		System.out.println("message " + message);
		return TranslateUtil.getListData(message, "<%>", "<#>");
	}

	// 获取推荐的精品菜谱
	public static String[][] getExcellentMenuPC() {
		try {
			connect();
			dos.writeUTF(Constant.GET_RECOMMEND_MENU_PC);
			message = din.readUTF();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		System.out.println("message " + message);
		return TranslateUtil.getContent(message, "<%>", "<#>");
	}

	public static String[][] getExcellentRandomPC() {
		try {
			connect();
			dos.writeUTF(Constant.GET_RECOMMEND_RANDOM_PC);
			message = din.readUTF();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		System.out.println("message " + message);
		return TranslateUtil.getContent(message, "<%>", "<#>");
	}

	// 添加主推荐
	public static Boolean UploadPrimaryRecommend(String path, String info) {
		try {
			connect();
			dos.writeUTF(Constant.UPLOAD_PRIMARY_RECOMMEND + path + "<#>"
					+ info);
			falg = din.readBoolean();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return falg;
	}

	// 删除主推荐
	public static Boolean deletePrimaryRecommend(String id) {
		try {
			connect();
			dos.writeUTF(Constant.DELETE_PRIMARY_RECOMMEND + id);
			falg = din.readBoolean();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return falg;
	}

	// 添加精品菜谱
	public static Boolean addExcelletnMenu(String MenuId) {
		try {
			connect();
			dos.writeUTF(Constant.ADD_RECOMMEND_MENU + MenuId);
			falg = din.readBoolean();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return falg;
	}

	// 删除精品菜谱
	public static Boolean deleteExcelletnMenu(String MenuId) {
		try {
			connect();
			dos.writeUTF(Constant.DELETE_RECOMMEND_MENU + MenuId);
			falg = din.readBoolean();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return falg;
	}

	// 添加精品随拍
	public static Boolean addExcelletnRandom(String randomId) {
		try {
			connect();
			dos.writeUTF(Constant.ADD_RECOMMEND_RANDOM + randomId);
			falg = din.readBoolean();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return falg;
	}

	// 删除精品随拍
	public static Boolean deleteExcelletnRandom(String randomId) {
		try {
			connect();
			dos.writeUTF(Constant.DELETE_RECOMMEND_RANDOM + randomId);
			falg = din.readBoolean();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return falg;
	}

	// 禁止随拍通过
	public static Boolean forbitRandom(String randomId) {
		try {
			connect();
			dos.writeUTF(Constant.FORBID_RANDOM + randomId);
			falg = din.readBoolean();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return falg;
	}

	// 允许随拍通过
	public static Boolean permitRandom(String randomId) {
		try {
			connect();
			dos.writeUTF(Constant.PERMINT_RANDOM + randomId);
			falg = din.readBoolean();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return falg;
	}

	// 禁止菜品通过
	public static Boolean forbitMenu(String menuId) {
		try {
			connect();
			dos.writeUTF(Constant.FORBID_MENU + menuId);
			falg = din.readBoolean();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return falg;
	}

	// 允许菜品通过
	public static Boolean permitMenu(String menuId) {
		try {
			connect();
			dos.writeUTF(Constant.PERMIT_MENU + menuId);
			falg = din.readBoolean();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return falg;
	}

	// 根据城市，标签,状态,查询可以推荐的随拍
	public static String[][] getRandomLeft(String city, String label) {
		try {
			connect();
			dos.writeUTF(Constant.GET_RANDOM_LEFT + city + "<#>" + label);
			message = din.readUTF();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		System.out.println("message " + message);
		return TranslateUtil.getContent(message, "<%>", "<#>");
	}

	// 条件可以推荐的菜谱 gck 根据菜系、口味、时间、难度
	public static String[][] getMenuLeft(String style, String flavour,
			String time, String difficulty) {
		try {
			connect();
			dos.writeUTF(Constant.GET_MENU_LEFT + style + "<#>" + flavour
					+ "<#>" + time + "<#>" + difficulty);
			message = din.readUTF();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		System.out.println("message " + message);
		return TranslateUtil.getContent(message, "<%>", "<#>");
	}

	// 条件获取菜品评论
	public static String[][] getMenuPinglun(String dateFormate, String date,
			String state) {
		try {
			connect();
			dos.writeUTF(Constant.GET_MENU_PINLUN + date + "<#>" + dateFormate
					+ "<#>" + state);
			message = din.readUTF();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		System.out.println("message " + message);
		return TranslateUtil.getContent(message, "<%>", "<#>");
	}

	// 禁止菜品评论
	public static Boolean forbidMenuPinglun(String pinglunId) {
		try {
			connect();
			dos.writeUTF(Constant.FORBIT_MENU_PINLUN + pinglunId);
			falg = din.readBoolean();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		System.out.println("message " + message);
		return falg;

	}

	// 通过菜品评论
	public static Boolean permitMenuPinglun(String pinglunId) {
		try {
			connect();
			dos.writeUTF(Constant.PERMIT_MENU_PINGLUN + pinglunId);
			falg = din.readBoolean();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		System.out.println("message " + message);
		return falg;

	}

	// 条件获取随拍评论pc
	public static String[][] getRandomPinglun(String dateFormate, String date,
			String state) {
		try {
			connect();
			dos.writeUTF(Constant.GET_RANDOM_PINLUN + date + "<#>"
					+ dateFormate + "<#>" + state);
			message = din.readUTF();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		System.out.println("message " + message);
		return TranslateUtil.getContent(message, "<%>", "<#>");
	}

	// 禁止菜品评论
	public static Boolean forbidRandomPinglun(String pinglunId) {
		try {
			connect();
			dos.writeUTF(Constant.FORBIT_RANDOM_PINLUN + pinglunId);
			falg = din.readBoolean();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		System.out.println("message " + message);
		return falg;

	}

	// 通过菜品评论
	public static Boolean permitRandomPinglun(String pinglunId) {
		try {
			connect();
			dos.writeUTF(Constant.PERMIT_RANDOM_PINGLUN + pinglunId);
			falg = din.readBoolean();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		System.out.println("message " + message);
		return falg;

	}

	public static void iniInfo() {
		try {
			connect();
			dos.writeUTF(Constant.INIT_INFO);
			String str=din.readUTF();
			System.out.println("str "+str);
			Constant.style =str.split("<#>");//= din.readUTF().split("<#>");
			Constant.flavour = din.readUTF().split("<#>");
			Constant.craft = din.readUTF().split("<#>");
			Constant.ctime = din.readUTF().split("<#>");
			Constant.tool = din.readUTF().split("<#>");
			Constant.difficulty = din.readUTF().split("<#>");
			Constant.label=din.readUTF().split("<#>");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}

	// 是否是精品菜谱
	public static Boolean isExcellentMenu(String menuId) {
		try {
			connect();
			dos.writeUTF(Constant.IS_EXCELLENT_MENU + menuId);
			falg = din.readBoolean();
			return falg;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return false;
	}

	// 是否是精品随拍
	public static Boolean isExcellentRandom(String randomId) {
		try {
			connect();
			dos.writeUTF(Constant.IS_EXCELLENT_RANDOM + randomId);
			falg = din.readBoolean();
			return falg;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return false;
	}
}
