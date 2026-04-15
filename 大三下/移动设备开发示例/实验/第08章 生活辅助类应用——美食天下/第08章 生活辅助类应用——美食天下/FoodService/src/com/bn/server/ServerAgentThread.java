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
			System.out.println("msg "+msg);
			if (msg.startsWith(Constant.IS_MANAGER)) { // 是否是管理员 pc
				flag = DBUtil.isManager(msg.substring( // 判断是否是管理员
						Constant.IS_MANAGER.length(), msg.length()));
				dout.writeBoolean(flag); // 将结果输出到输出流
			}			
			else if (msg.startsWith(Constant.INSERT_MENU)) {	//插入菜谱
				Boolean flag = DBUtil.insertMenu(msg.substring(	//数据库操作结果
						Constant.INSERT_MENU.length(), msg.length()));
				dout.writeBoolean(flag);						//将结果写入输出流
			} 
			else if (msg.startsWith(Constant.SEAECH_MENU)) { // 编号查询菜品
				String menId = msg.substring // 菜品编号
						(Constant.SEAECH_MENU.length(), msg.length());
				String remsg = DBUtil.searchMenu(menId);
				dout.writeUTF(remsg); // 将结果输出到输出流
			}// 菜编号查找菜谱 android years
			else if (msg.startsWith(Constant.SEAECH_MENU_YEARS)) {
				String menId = msg.substring(
						Constant.SEAECH_MENU_YEARS.length(), msg.length());
				String remsg = DBUtil.searchMenuYears(menId);
				dout.writeUTF(remsg);
			}
			// 按条件查询菜谱 菜系,口味,时间,难度,状态 pc
			else if (msg.startsWith(Constant.GET_MSG_SELECT)) {
				String remsg = DBUtil.getMsgSelect(msg.substring(
						Constant.GET_MSG_SELECT.length(), msg.length()));
				dout.writeUTF(remsg);

			}
			// 按条件查询用户 pc
			else if (msg.startsWith(Constant.GET_USER_SELECT)) {
				String meg = msg.substring(Constant.GET_USER_SELECT.length(),
						msg.length());
				String sex = meg.split("<#>")[0];
				String dateformate = meg.split("<#>")[1];
				;
				String date = meg.split("<#>")[2];
				;
				String remsg = DBUtil.getUserSelect(sex, dateformate, date);
				dout.writeUTF(remsg);
			}
			// /按编号获取过程 android pc
			else if (msg.startsWith(Constant.GET_PROCESS)) {
				String remsg = DBUtil.getProcess(msg.substring(
						Constant.GET_PROCESS.length(), msg.length()));
				dout.writeUTF(remsg);
			}// /按编号获取图片 android pc
			else if (msg.startsWith(Constant.GET_IMAGE)) {
				String remsg = msg.substring(Constant.GET_IMAGE.length(),
						msg.length());
				File fileResource = new File("resource"); // 创建文件流
				picPath = fileResource.getAbsolutePath() + "\\IMAGE\\"; // 文件路径
				file = new File(picPath + remsg);
				System.out.println("file "+file.getAbsolutePath());

				fis = new FileInputStream(file);
				byte[] bb = new byte[fis.available()];
				fis.read(bb);
				dout.writeInt(bb.length);
				dout.write(bb);
				fis.close();
			}// 按名称获取图片 缩略图 pc
			else if (msg.startsWith(Constant.GET_THUMBNAIL)) {
				String picName = msg.substring(Constant.GET_THUMBNAIL.length(),
						msg.length());
				File fileResource = new File("resource"); // 创建文件流
				picPath = fileResource.getAbsolutePath() + "\\IMAGE\\"; // 文件路径
				thumbnailpath = fileResource.getAbsolutePath()
						+ "\\IMAGE\\thumbnail\\";
				file = new File(thumbnailpath + picName);
				if (!file.exists()) {
					Thumbanils.saveImageAsJpg(picPath + picName, thumbnailpath
							+ picName, 200, 200);
					file = new File(thumbnailpath + picName);
				}
				fis = new FileInputStream(file);
				byte[] bb = new byte[fis.available()];
				fis.read(bb);
				dout.writeInt(bb.length);
				dout.write(bb);
				fis.close();
			}

			// /按编号删除菜谱 pc
			else if (msg.startsWith(Constant.DELETR_MENU)) {
				int v = DBUtil.deleteMenu(msg.substring(
						Constant.DELETR_MENU.length(), msg.length()));
				dout.writeInt(v);
			}
			// /插入图片 android gck
			else if (msg.startsWith(Constant.INSERT_PIC)) {
				bb = ImageUtil.streamTobyte(din);
				File fileResource = new File("resource"); // 创建文件流
				picPath = fileResource.getAbsolutePath() + "\\IMAGE\\"; // 文件路径
				File parentFile = new File(picPath);
				if (!parentFile.exists()) {
					parentFile.mkdirs();
				}
				String picName = System.currentTimeMillis() + ".jpeg";
				ImageUtil.saveByte(bb, picPath + picName);
				dout.writeUTF(picName);
			}
			// pc 按编号获取随拍
			else if (msg.startsWith(Constant.GET_RANDOM)) {
				String menuId = msg.substring(Constant.GET_RANDOM.length(),
						msg.length());
				String remsg = DBUtil.getRandom(menuId);
				dout.writeUTF(remsg);
			}
			// android 根据编号获取随拍
			else if (msg.startsWith(Constant.GET_RANDOM_YEARS)) {
				String menuId = msg.substring(
						Constant.GET_RANDOM_YEARS.length(), msg.length());
				String remsg = DBUtil.getRandomY(menuId);
				dout.writeUTF(remsg);
			}
			// 插入随拍 android
			else if (msg.startsWith(Constant.INSERT_RANDOM)) {

				String mes = msg.substring(Constant.INSERT_RANDOM.length(),
						msg.length());
				Boolean b = DBUtil.insertRandom(mes);
				dout.writeBoolean(b);
			}
			// 获取个人的各项信息
			else if (msg.startsWith(Constant.GET_UID_MESSAGE)) {
				String mes = msg.substring(Constant.GET_UID_MESSAGE.length(),
						msg.length());
				String rmes = DBUtil.getUidMessage(mes);
				dout.writeUTF(rmes);
			}
			// 增加关注 android
			else if (msg.startsWith(Constant.ADD_ATTENTION)) {
				String mes = msg.substring(Constant.ADD_ATTENTION.length(),
						msg.length());
				flag = DBUtil.addAttention(mes);
				dout.writeBoolean(flag);
			}
			// 获取关注信息 pc
			else if (msg.startsWith(Constant.GET_ATTENTION)) {
				String mes = msg.substring(Constant.GET_ATTENTION.length(),
						msg.length());
				String remsg = DBUtil.getAttention(mes);
				dout.writeUTF(remsg);
			}
			// 取消关注
			else if (msg.startsWith(Constant.DELETE_ATTENTION)) {
				String mes = msg.substring(Constant.DELETE_ATTENTION.length(),
						msg.length());
				String uid = mes.split("<%>")[0];
				String target = mes.split("<%>")[1];
				flag = DBUtil.deleteAttention(uid, target);
				dout.writeBoolean(flag);
			}
			// 获取粉丝 pc
			else if (msg.startsWith(Constant.GET_FANS)) {
				String mes = msg.substring(Constant.GET_FANS.length(),
						msg.length());
				String remsg = DBUtil.getFans(mes);
				dout.writeUTF(remsg);
			}
			// 获取菜谱收藏 android
			else if (msg.startsWith(Constant.GET_MENUC)) {
				String mes = msg.substring(Constant.GET_MENUC.length(),
						msg.length());
				String lastId = mes.split("<#>")[0];
				String uid = mes.split("<#>")[1];
				String remsg = DBUtil.getMenuC(lastId, uid);
				dout.writeUTF(remsg);
			}// 获取菜谱收藏 pc
			else if (msg.startsWith(Constant.GET_MENU_COMMENT)) {
				String uid = msg.substring(Constant.GET_MENU_COMMENT.length(),
						msg.length());
				String remsg = DBUtil.getMenuCPC(uid);
				dout.writeUTF(remsg);
			}
			// 获取随拍收藏 android
			else if (msg.startsWith(Constant.GET_RANDOMC)) {
				String mes = msg.substring(Constant.GET_RANDOMC.length(),
						msg.length());
				String lastId = mes.split("<#>")[0];
				String uid = mes.split("<#>")[1];
				String remsg = DBUtil.getRandomC(lastId, uid);
				dout.writeUTF(remsg);
			}// 获取所有随拍收藏 PC
			else if (msg.startsWith(Constant.GET_RANDOM_RECOMMENT)) {
				String uid = msg.substring(
						Constant.GET_RANDOM_RECOMMENT.length(), msg.length());
				String remsg = DBUtil.getRandomCPC(uid);
				dout.writeUTF(remsg);
			}
			// 取消菜谱收藏 android pc
			else if (msg.startsWith(Constant.DELETE_MENUC)) {
				String mes = msg.substring(Constant.DELETE_MENUC.length(),
						msg.length());
				String uid = mes.split("<%>")[0];
				String menuId = mes.split("<%>")[1];
				flag = DBUtil.deleteMenuC(uid, menuId);
				dout.writeBoolean(flag);
			}
			// 取消随拍收藏 android pc
			else if (msg.startsWith(Constant.DELETE_RANDOMC)) {
				String mes = msg.substring(Constant.DELETE_RANDOMC.length(),
						msg.length());
				String uid = mes.split("<%>")[0];
				String ranId = mes.split("<%>")[1];
				flag = DBUtil.deleteRandomC(uid, ranId);
				dout.writeBoolean(flag);
			}
			// 获取主推荐信息 android
			else if (msg.startsWith(Constant.GET_RECOMMEND)) {
				String rmsg = DBUtil.getRecommend();
				dout.writeUTF(rmsg);
			}
			// 是否是用户(根据用户名、密码)
			else if (msg.startsWith(Constant.IS_USER)) {
				flag = DBUtil.isUser(msg.substring(11, msg.length()));
				dout.writeBoolean(flag);
			}// /喜欢随拍
			else if (msg.startsWith(Constant.LIKE_RANDOM)) {
				if (!DBUtil.iSLikeRandom(msg.substring(15, msg.length()))) {
					DBUtil.likeRandom(msg.substring(15, msg.length()));
				}
				dout.writeBoolean(true);
			}
			// /收藏随拍
			else if (msg.startsWith(Constant.COLL_RANDOM)) {
				if (!DBUtil.isCollection(msg.substring(15, msg.length()))) {
					DBUtil.collectionRandom(msg.substring(15, msg.length()));
				}
				dout.writeBoolean(true);
			}
			// 模糊搜索随拍 android
			else if (msg.startsWith(Constant.GET_RANDOM_LIKE)) {
				String info = msg.substring(Constant.GET_RANDOM_LIKE.length(),
						msg.length());
				String[] str = info.split("<#>");
				String timediver = str[0];
				int type = Integer.valueOf(str[1]);
				String arg = str[2];
				String rmsg = DBUtil.getRandomLike(timediver, type, arg);
				dout.writeUTF(rmsg);
			}
			// 喜欢菜品
			else if (msg.startsWith(Constant.LIKE_MENU)) {
				String info = msg.substring(Constant.LIKE_MENU.length(),
						msg.length());
				String[] parameters = info.split("<%>");
				if (!DBUtil.isLikeMenu(parameters[0], parameters[1])) {
					DBUtil.likeMenu(parameters[0], parameters[1]);
				}
				dout.writeBoolean(true);
			}
			// 收藏菜品
			else if (msg.startsWith(Constant.COLLECTION_MENU)) {
				String info = msg.substring(Constant.COLLECTION_MENU.length(),
						msg.length());
				String[] parameters = info.split("<%>");
				if (!DBUtil.isCollectionMenu(parameters[0], parameters[1])) {
					DBUtil.collectionMenu(parameters[0], parameters[1]);
				}
				dout.writeBoolean(true);

			}
			// 模糊搜索菜品
			else if (msg.startsWith(Constant.GET_MENU_LIKE)) {
				String sub = msg.substring(Constant.GET_MENU_LIKE.length(),
						msg.length());
				String[] parameter = sub.split("<#>");
				String timeDiver = parameter[0];
				String type = parameter[1];
				String arg = parameter[2];
				String rmsg = DBUtil.getMenuLike(timeDiver, type, arg);
				dout.writeUTF(rmsg);
			}
			// 插入如菜谱制作过程
			else if (msg.startsWith(Constant.INSERT_PRO)) {
				String info = msg.substring(Constant.INSERT_PRO.length(),
						msg.length());
				int id = DBUtil.insertProcess(info);
				dout.writeInt(id);
			}// /菜品评论
			else if (msg.startsWith(Constant.MENU_COMENT)) {
				String str = msg.substring(Constant.MENU_COMENT.length(),
						msg.length());
				String[] sg = str.split("<#>");
				Boolean b = DBUtil.commentMenu(Integer.parseInt(sg[0]), sg[1],
						sg[2], sg[3]);
				dout.writeBoolean(b);
			}// 随拍评论
			else if (msg.startsWith(Constant.RANDOM_COMMENT)) {
				String str = msg.substring(Constant.RANDOM_COMMENT.length(),
						msg.length());
				String[] sg = str.split("<#>");
				Boolean b = DBUtil.commentRandom(Integer.parseInt(sg[0]),
						sg[1], sg[2], sg[3]);
				dout.writeBoolean(b);
			}// 获取菜品评论
			else if (msg.startsWith(Constant.GET_COMMENT_M)) {
				String str = msg.substring(Constant.GET_COMMENT_M.length(),
						msg.length());
				String[] info = str.split("<#>");
				String rmsg = DBUtil.getCommentM(Integer.parseInt(info[0]),
						info[1]);
				dout.writeUTF(rmsg);
			}// 获取随拍评论
			else if (msg.startsWith(Constant.GET_COMMENT_R)) {
				String str = msg.substring(Constant.GET_COMMENT_R.length(),
						msg.length());
				String[] info = str.split("<#>");
				String rmsg = DBUtil.getCommentR(Integer.parseInt(info[0]),
						info[1]);
				dout.writeUTF(rmsg);
			} else if (msg.startsWith(Constant.GET_MENUBY_UID)) {
				String uid = msg.substring(Constant.GET_MENUBY_UID.length(),
						msg.length());
				String remsg = DBUtil.getMenuByUid(uid);
				dout.writeUTF(remsg);
			} else if (msg.startsWith(Constant.GET_RANDOMBY_UID)) {
				String uid = msg.substring(Constant.GET_RANDOMBY_UID.length(),
						msg.length());
				String remsg = DBUtil.getRandomByUid(uid);
				dout.writeUTF(remsg);
			}// pc 根据城市,标签,状态获取随拍
			else if (msg.startsWith(Constant.GET_RANDOMBY_CODITION)) {
				String[] info = msg.substring(
						Constant.GET_RANDOMBY_CODITION.length(), msg.length())
						.split("<#>");
				String city = info[0];
				String label = info[1];
				String state = info[2];
				String remsg = DBUtil.getRandomByCondition(city, label, state);
				dout.writeUTF(remsg);
			}
			// 获取精品菜谱 android
			else if (msg.startsWith(Constant.GET_RECOMMEND_MENU)) {
				String rem = DBUtil.getExcellentMenu();
				dout.writeUTF(rem);
			}// 获取精品菜谱 android pc
			else if (msg.startsWith(Constant.GET_RECOMMEND_MENU_PC)) {
				String rem = DBUtil.getExcellentMenuPC();
				dout.writeUTF(rem);
			}
			// 获取精品随拍 android
			else if (msg.startsWith(Constant.GET_RECOMMEND_RANDOM)) {
				String rem = DBUtil.getExcellentRandom();
				dout.writeUTF(rem);
			}// 获取精品随拍 pc
			else if (msg.startsWith(Constant.GET_RECOMMEND_RANDOM_PC)) {
				String rem = DBUtil.getExcellentRandomPC();
				dout.writeUTF(rem);
			}// 添加主推荐
			else if (msg.startsWith(Constant.UPLOAD_PRIMARY_RECOMMEND)) {
				String info = msg.substring(
						Constant.UPLOAD_PRIMARY_RECOMMEND.length(),
						msg.length());
				String picName = info.split("<#>")[0];
				String content = info.split("<#>")[1];
				flag = DBUtil.upLoadPrimaryRecommend(picName, content);
				dout.writeBoolean(flag);
				;
			}// 删除主推荐
			else if (msg.startsWith(Constant.DELETE_PRIMARY_RECOMMEND)) {
				String primryId = msg.substring(
						Constant.DELETE_PRIMARY_RECOMMEND.length(),
						msg.length());
				flag = DBUtil.deletePrimaryRecommend(primryId);
				dout.writeBoolean(flag);
			}// 添加精品菜谱
			else if (msg.startsWith(Constant.ADD_RECOMMEND_MENU)) {
				String menuId = msg.substring(
						Constant.ADD_RECOMMEND_MENU.length(), msg.length());
				flag = DBUtil.addExcellentMenu(menuId);
				dout.writeBoolean(flag);
			}
			// 删除精品菜谱
			else if (msg.startsWith(Constant.DELETE_RECOMMEND_MENU)) {
				String menuId = msg.substring(
						Constant.DELETE_RECOMMEND_MENU.length(), msg.length());
				flag = DBUtil.deleteExcellentMenu(menuId);
				dout.writeBoolean(flag);
			}// 添加精品随拍
			else if (msg.startsWith(Constant.ADD_RECOMMEND_RANDOM)) {
				String randomId = msg.substring(
						Constant.ADD_RECOMMEND_RANDOM.length(), msg.length());
				flag = DBUtil.addExcellentRandom(randomId);
				dout.writeBoolean(flag);
			}
			// 删除精品随拍
			else if (msg.startsWith(Constant.DELETE_RECOMMEND_RANDOM)) {
				String randomId = msg
						.substring(Constant.DELETE_RECOMMEND_RANDOM.length(),
								msg.length());
				flag = DBUtil.deleteExcellentRandom(randomId);
				dout.writeBoolean(flag);
			}// 注册
			else if (msg.startsWith(Constant.REGIST)) {
				String info = msg.substring(Constant.REGIST.length(),
						msg.length());
				String name = info.split("<#>")[0];
				String passward = info.split("<#>")[1];
				String sculture = info.split("<#>")[2];
				String sex = info.split("<#>")[3];
				flag = DBUtil.register(name, passward, sculture, sex);
				dout.writeBoolean(flag);

			}// 禁止随拍通过
			else if (msg.startsWith(Constant.FORBID_RANDOM)) {
				String randomId = msg.substring(
						Constant.FORBID_RANDOM.length(), msg.length());
				flag = DBUtil.forbidRandom(randomId);
				dout.writeBoolean(flag);
			}// 允许随拍通过
			else if (msg.startsWith(Constant.PERMINT_RANDOM)) {
				String randomId = msg.substring(
						Constant.PERMINT_RANDOM.length(), msg.length());
				flag = DBUtil.permitRandom(randomId);
				dout.writeBoolean(flag);
			}
			// 禁止菜品通过
			else if (msg.startsWith(Constant.FORBID_MENU)) {
				String randomId = msg.substring(Constant.FORBID_MENU.length(),
						msg.length());
				flag = DBUtil.forbidMenu(randomId);
				dout.writeBoolean(flag);
			}// 允许菜品通过
			else if (msg.startsWith(Constant.PERMIT_MENU)) {
				String menuId = msg.substring(Constant.PERMIT_MENU.length(),
						msg.length());
				flag = DBUtil.permitMenu(menuId);
				dout.writeBoolean(flag);
			}// 查询可以推荐的随拍
			else if (msg.startsWith(Constant.GET_RANDOM_LEFT)) {
				String leftmsg = msg.substring(
						Constant.GET_RANDOM_LEFT.length(), msg.length());
				String city = leftmsg.split("<#>")[0];
				String label = leftmsg.split("<#>")[1];
				String rmsg = DBUtil.getRandomLeft(city, label);
				dout.writeUTF(rmsg);

			}// 查询可以推荐的菜谱
			else if (msg.startsWith(Constant.GET_MENU_LEFT)) {
				String leftmsg = msg.substring(Constant.GET_MENU_LEFT.length(),
						msg.length());
				String[] str = leftmsg.split("<#>");
				String style = str[0];
				String flavour = str[1];
				String ctime = str[2];
				String difficulty = str[3];
				String rmsg = DBUtil.getMenuLeft(style, flavour, ctime,
						difficulty);
				dout.writeUTF(rmsg);
			} // 条件获取菜品评论 pc
			else if (msg.startsWith(Constant.GET_MENU_PINLUN)) {
				String[] conditions = msg.substring(
						Constant.GET_MENU_PINLUN.length(), msg.length()).split(
						"<#>");
				String date = conditions[0];
				String dateFormate = conditions[1];
				String state = conditions[2];
				String rmsg = DBUtil.getMenuPinglunSelect(date, dateFormate,
						state);
				dout.writeUTF(rmsg);
			}// 禁止菜品评论 pc
			else if (msg.startsWith(Constant.FORBIT_MENU_PINLUN)) {
				String Id = msg.substring(Constant.FORBIT_MENU_PINLUN.length(),
						msg.length());
				flag = DBUtil.forbidMenuPinglun(Id);
				dout.writeBoolean(flag);
			}// /通过菜品评论 pc
			else if (msg.startsWith(Constant.PERMIT_MENU_PINGLUN)) {
				String pinglunId = msg.substring(
						Constant.PERMIT_MENU_PINGLUN.length(), msg.length());
				flag = DBUtil.permitMenuPinglun(pinglunId);
				dout.writeBoolean(flag);
			}
			// 条件获取随拍评论 pc
			else if (msg.startsWith(Constant.GET_RANDOM_PINLUN)) {
				String[] conditions = msg.substring(
						Constant.GET_RANDOM_PINLUN.length(), msg.length())
						.split("<#>");
				String date = conditions[0];
				String dateFormate = conditions[1];
				String state = conditions[2];
				String rmsg = DBUtil.getRandomPinglunSelect(date, dateFormate,
						state);
				dout.writeUTF(rmsg);
			}// 禁止随拍评论 pc
			else if (msg.startsWith(Constant.FORBIT_RANDOM_PINLUN)) {
				String Id = msg.substring(
						Constant.FORBIT_RANDOM_PINLUN.length(), msg.length());
				flag = DBUtil.forbidRandomPinglun(Id);
				dout.writeBoolean(flag);
			}// /通过随拍评论 pc
			else if (msg.startsWith(Constant.PERMIT_RANDOM_PINGLUN)) {
				String pinglunId = msg.substring(
						Constant.PERMIT_RANDOM_PINGLUN.length(), msg.length());
				flag = DBUtil.permitRandomPinglun(pinglunId);
				dout.writeBoolean(flag);
			}
			// 初始化信息
			else if (msg.startsWith(Constant.INIT_INFO)) {
				String style = DBUtil.getStyle();
				dout.writeUTF(style);
				String flavour = DBUtil.getFlavour();
				dout.writeUTF(flavour);
				String craft = DBUtil.getCraft();
				dout.writeUTF(craft);
				String time = DBUtil.getTime();
				dout.writeUTF(time);
				String tool = DBUtil.getTool();
				dout.writeUTF(tool);
				String difficulty = DBUtil.getDifficulty();
				dout.writeUTF(difficulty);
				String label = DBUtil.getLabel();
				dout.writeUTF(label);
			}
			// 是否是精品菜谱
			else if (msg.startsWith(Constant.IS_EXCELLENT_MENU)) {
				String MenuId = msg.substring(
						Constant.IS_EXCELLENT_MENU.length(), msg.length());
				flag = DBUtil.isExcellentMenu(MenuId);
				dout.writeBoolean(flag);
			}
			// 是否是精品随拍
			else if (msg.startsWith(Constant.IS_EXCELLENT_RANDOM)) {
				String randomId = msg.substring(
						Constant.IS_EXCELLENT_RANDOM.length(), msg.length());
				flag = DBUtil.isExcellentRandom(randomId);
				dout.writeBoolean(flag);
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
