package com.bn.databaseutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.bn.util.Constant;
import com.bn.util.TranslateUtil;

public class DBUtil {
	public static Connection getConnection() {
		Connection con = null;						//声明连接
		try {
			Class.forName("org.gjt.mm.mysql.Driver");	//声明驱动
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"
					+ "foodbase?useUnicode=true&characterEncoding=UTF-8",
					"root", "");						//获得连接
		} catch (Exception e) {e.printStackTrace();}
		return con;										//返回连接	
	}
	public static Boolean isManager(String info) {
		Connection con=null;							
		Statement st = null;							//定义接口对象
		ResultSet rs = null;							//定义结果集
		String[] mess = info.split("<#>");
		try {
			con = getConnection();						//获得连接对象
			st = con.createStatement();					//获取接口
			String sql = "select * from manager where cname='" + mess[0]
					+ "' and pwd='" + mess[1] + "';";
			rs = st.executeQuery(sql);					//执行SQL语句
			while (rs.next()) 
			{return true;}							//返回结果			
		} catch (Exception e) {e.printStackTrace();} 
		finally {
			try {rs.close();} catch (Exception e) {e.printStackTrace();}//关闭结果集
			try {st.close();} catch (SQLException e) {e.printStackTrace();}//关闭接口
			try {con.close();} catch (SQLException e) {e.printStackTrace();}//关闭连接
		}
		return false;														//返回结果			
	}

	// 按编号查找菜谱 pc
	public static String searchMenu(String menuId) {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "select  primaryPic,uid,cname,introduction"
					+ ",flavour,craft,tools,difficulty,ctime"
					+ ",food,codiments,tips,uploadTime,"
					+ "slike,collection,pinglun,sculpture"
					+ " from menu where id=" + menuId;

			System.out.println("sql " + sql);
			rs = st.executeQuery(sql);
			StringBuffer message = new StringBuffer();
			while (rs.next()) {
				for (int i = 1; i <= 17; i++) {
					message.append(rs.getString(i) + "<#>");
				}
				if (message.length() > 0) {
					return message.substring(0, message.length() - 3)
							.toString();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {rs.close();} catch (Exception e) {e.printStackTrace();}
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return Constant.NO_MESSAGE;
	}

	// 按编号查找菜谱 pc
	public static String searchMenuYears(String menuId) {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "select  primaryPic,uid,cname,introduction"
					+ ",flavour,craft,tools,difficulty,ctime"
					+ ",food,codiments,tips,uploadTime,"
					+ "slike,collection,pinglun,sculpture"
					+ " from menu where state=1 and id=" + menuId;

			System.out.println("sql " + sql);
			rs = st.executeQuery(sql);
			StringBuffer message = new StringBuffer();
			while (rs.next()) {
				for (int i = 1; i <= 17; i++) {
					message.append(rs.getString(i) + "<#>");
				}
				if (message.length() > 0) {
					return message.substring(0, message.length() - 3)
							.toString();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {rs.close();} catch (Exception e) {e.printStackTrace();}
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return Constant.NO_MESSAGE;
	}

	// 按条件查找菜谱 pc // 按条件查询菜谱 菜系,口味,时间,难度,状态pc
	public static String getMsgSelect(String info) {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		String[] sss = info.split("<#>");
		try {

			st = con.createStatement();
			StringBuffer sql = new StringBuffer(
					"select id,cname,uid,style,flavour,craft,ctime,tools,"
							+ "difficulty,state from menu where ");
			String[] condition = new String[] { "style", "flavour", "ctime",
					"difficulty", "state" };
			for (int i = 0; i < sss.length - 1; i++) {
				if (!sss[i].equals(Constant.NO_MESSAGE)) {
					sql.append(condition[i] + "='" + sss[i] + "'  and ");
				}
			}
			if (!sss[sss.length - 1].equals(Constant.NO_MESSAGE)) {
				sql.append(condition[condition.length - 1] + "="
						+ sss[sss.length - 1] + "      ");
			}
			System.out.println("sql "
					+ sql.substring(0, sql.length() - 6));
			rs = st.executeQuery(sql.substring(0, sql.length() - 6));
			StringBuffer message = new StringBuffer();
			while (rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(2);
				String uid = rs.getString(3);
				String style = rs.getString(4);
				String flavour = rs.getString(5);
				String craft = rs.getString(6);
				String ctime = rs.getString(7);
				String tools = rs.getString(8);
				String difficulty = rs.getString(9);
				Boolean state = rs.getBoolean(10);
				message.append(id + "<#>" + name + "<#>" + uid + "<#>" + style
						+ "<#>" + flavour + "<#>" + craft + "<#>" + ctime
						+ "<#>" + tools + "<#>" + difficulty + "<#>" + state
						+ "<%>");
			}
			if (message.length() > 0) {
				return message.substring(0, message.length() - 3).toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {rs.close();} catch (Exception e) {e.printStackTrace();}
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return Constant.NO_MESSAGE;
	}

	// 按条件查找用户信息 android pc
	public static String getUserSelect(String sex, String dateformate,
			String date) {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			// DATE_FORMAT(entertime,'%Y-%m-%d')='2014-09-18';
			st = con.createStatement();
			String sql = "select uid,pwd,sex,entertime,menu,random,menuc,randomc,"
					+ "attention from uid ";
			if (!sex.equals(Constant.NO_MESSAGE)) {
				sql += " where sex='" + sex + "'";
				if (!dateformate.equals(Constant.NO_MESSAGE)) {
					sql += " and DATE_FORMAT(entertime,'" + dateformate
							+ "')='" + date + "'";
				}
			} else if (!dateformate.equals(Constant.NO_MESSAGE)) {
				sql += " where DATE_FORMAT(entertime,'" + dateformate + "')='"
						+ date + "'";
			}
			System.out.println("sql " + sql);
			rs = st.executeQuery(sql);
			StringBuffer message = new StringBuffer();
			while (rs.next()) {
				for (int i = 1; i <= 9; i++) {
					message.append(rs.getString(i) + "<#>");
				}
				message.replace(message.length() - 3, message.length(), "<%>");
			}
			if (message.length() > 0) {
				return message.substring(0, message.length() - 3).toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {rs.close();} catch (Exception e) {e.printStackTrace();}
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return Constant.NO_MESSAGE;
	}

	// 获取用户部分信息
	public static String getUserPartInfo() {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "select uid,pwd,sex,entertime,sign from uid";
			rs = st.executeQuery(sql);
			rs = st.executeQuery(sql);
			StringBuffer message = new StringBuffer();
			while (rs.next()) {
				for (int i = 1; i <= 5; i++) {
					message.append(rs.getString(i) + "<#>");
				}
				message.replace(message.length() - 3, message.length(), "<%>");
			}
			if (message.length() > 0) {
				return message.substring(0, message.length() - 3).toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {rs.close();} catch (Exception e) {e.printStackTrace();}
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return Constant.NO_MESSAGE;
	}

	// /编号获取过程
	public static String getProcess(String info) {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		try {
			st = con.createStatement();
			String sql = "select * from pro where id='" + info + "'";
			rs = st.executeQuery(sql);
			if (rs.next()) {
				int i = 2;
				while (rs.getString(i) != null) {
					sb.append(rs.getString(i++) + "%");
				}
			}
			return sb.substring(0, sb.length() - 1).toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {rs.close();} catch (Exception e) {e.printStackTrace();}
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return Constant.NO_MESSAGE;
	}

	// /按编号删除菜谱
	// /线程同步锁，资源对象
	public static Object DELETE_MENU_BLOCK = new Object();

	public static int deleteMenu(String info) {
		synchronized (DELETE_MENU_BLOCK) {
			Connection con = getConnection();
			Statement st = null;
			String[] ss = null;
			try {
				st = con.createStatement();
				ss = info.split("<#>");
				int v = deleteProcess(ss[0]);
				if (v > 0) {
					String sql = "delete from menu where id=" + ss[0];
					st.executeUpdate(sql);
					updateUidMenu(ss[1], "-1");
				}
				return v;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {st.close();} catch (SQLException e) {e.printStackTrace();}
				try {con.close();} catch (SQLException e) {e.printStackTrace();}
			}
			return -1;
		}
	}

	public static Object INSERT_MENU = new Object();//添加菜谱 gck
	public static Boolean insertMenu(String info) {
		synchronized (INSERT_MENU) {
			Connection con = getConnection();
			Statement st = null;
			try {
				String[] ss = info.split("<%>");
				String uid = ss[1].split("<#>")[1];
				String sculpture = getHeadImage(uid);
				String sql = "insert into menu"
						+ "(id,primaryPic,uid,cname,style,flavour,craft,ctime,tools,"
						+ "difficulty,tips,introduction,food,codiments,sculpture) values("
						+ ss[0] + "," + TranslateUtil.getInsertSentence(ss[1])
						+ ",'" + sculpture + "')";
				System.out.println("sql " + sql);
				st = con.createStatement();

				if (st.executeUpdate(sql) > 0) {
					updateUidMenu(uid,"+1");
					return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {st.close();} catch (SQLException e) {e.printStackTrace();}
				try {con.close();} catch (SQLException e) {e.printStackTrace();}
			}
			return false;
		}
	}

	// /插入过程
	public static String INSERY_PRO="INSERY_PRO";
	public static int insertProcess(String info) {
		synchronized (INSERY_PRO) {
		Connection con = getConnection();
		Statement st = null;
		String sql = "insert into pro(step1,step2,step3,step4,step5,step6,"
				+ "step7,step8,step9,step10,step11,step12,step13,"
				+ "step14,step15,step16,step17,step18,step19,step20) "
				+ "values(" + TranslateUtil.getInsertProcess(info) + ")";
		try {
			st = con.createStatement();
			System.out.println("sql "+sql);
			int v = st.executeUpdate(sql);
			if (v > 0) {
				return getProMenuId();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return 0;
		}
	}

	// /删除过程
	public static int deleteProcess(String id) {
		Connection con = getConnection();
		Statement st = null;
		int v = 0;
		String sql = "delete from pro where id=" + id;
		try {
			st = con.createStatement();
			v = st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return v;
	}

	// /获取过程最大Id
	public static int getProMenuId() {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "select max(id) from pro ";
			rs = st.executeQuery(sql);
			if (rs.next()) {
				int remg = rs.getInt(1);
				return remg;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {rs.close();} catch (Exception e) {e.printStackTrace();}
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return 1;
	}

	// 按编号查询随拍 android
	public static String getRandomY(String randomId) {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		try {
			st = con.createStatement();
			String sql = "select uid,uploadTime,introduce,slike"
					+ ",collection,pinglun,label,city,picPath,sculpture from random where state=1 and id="
					+ randomId + " order by uploadTime  desc limit 10";
			System.out.println("sql " + sql);
			rs = st.executeQuery(sql);
			while (rs.next()) {

				for (int i = 1; i <= 10; i++) {
					sb.append(rs.getString(i) + "<#>");
				}
				sb.replace(sb.length() - 3, sb.length(), "<%>");
			}
			if (sb.length() > 0)
				return sb.substring(0, sb.length() - 3).toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {rs.close();} catch (Exception e) {e.printStackTrace();}
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return Constant.NO_MESSAGE;
	}

	// 按编号查询随拍 pc
	public static String getRandom(String randomId) {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		try {
			st = con.createStatement();
			String sql = "select uid,uploadTime,introduce,slike"
					+ ",collection,pinglun,label,city,picPath,sculpture from random where id="
					+ randomId + " order by uploadTime  desc limit 10";
			System.out.println("sql " + sql);
			rs = st.executeQuery(sql);
			while (rs.next()) {

				for (int i = 1; i <= 10; i++) {
					sb.append(rs.getString(i) + "<#>");
				}
				sb.replace(sb.length() - 3, sb.length(), "<%>");
			}
			if (sb.length() > 0)
				return sb.substring(0, sb.length() - 3).toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {rs.close();} catch (Exception e) {e.printStackTrace();}
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return Constant.NO_MESSAGE;
	}

	// /获取最大随拍编号
	public static int getMaxRandomId() {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "select max(id) from random ";
			rs = st.executeQuery(sql);
			if (rs.next()) {
				int remg = rs.getInt(1);
				return remg;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {rs.close();} catch (Exception e) {e.printStackTrace();}
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return 1;
	}

	// /插入随拍
	public static Boolean insertRandom(String info) {
		Connection con = getConnection();
		int v = 0;
		Statement st = null;
		try {
			st = con.createStatement();
			String sculpture = getHeadImage(info.split("<#>")[0]);
			String sql = "insert into random (uid,city,label,introduce,picPath,sculpture) values(";
			sql += TranslateUtil.getInsertSentence(info) + ",'" + sculpture
					+ "')";
			System.out.println("sql "+sql);
			v = st.executeUpdate(sql);
			if (v > 0) {
				updateUidRandom(info.split("<#>")[0], "+1");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return true;
	}

	// /删除随拍
	public static Object DELETE_RANDOM = new Object();

	public static int deleteRandom(String info) {
		synchronized (DELETE_RANDOM) {
			Connection con = getConnection();
			int v = 0;
			Statement st = null;
			String[] ss = null;
			try {
				st = con.createStatement();
				ss = info.split("<%>");
				String sql = "delete from random where id=" + ss[0];
				v = st.executeUpdate(sql);
				if (v > 0) {
					updateUidRandom(ss[1], "-1");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {st.close();} catch (SQLException e) {e.printStackTrace();}
				try {con.close();} catch (SQLException e) {e.printStackTrace();}
			}
			return v;
		}
	}

	// /插入用户
	public static Object INSERT_USER = new Object();

	public static int insertUser(String info) {
		synchronized (INSERT_USER) {
			Connection con = getConnection();
			int v = 0;
			Statement st = null;
			try {
				st = con.createStatement();
				String[] ss = info.split("<#>");
				String sql = "insert into uid(uid,pwd,sculpture,sign) values("
						+ "'" + ss[0] + "','" + ss[1] + "','" + ss[2] + "','"
						+ ss[3] + "')";
				v = st.executeUpdate(sql);
				sql = "insert into attention(uid,content) values('" + ss[0]
						+ "','#')";
				st.executeUpdate(sql);
				sql = "insert into menuc(uid,content) values('" + ss[0]
						+ "','#')";
				st.executeUpdate(sql);
				sql = "insert into randomc(uid,content) values('" + ss[0]
						+ "','#')";
				st.executeUpdate(sql);
				sql = "insert into dailay(uid,content) values('" + ss[0]
						+ "','#')";
				st.executeUpdate(sql);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {st.close();} catch (SQLException e) {e.printStackTrace();}
				try {con.close();} catch (SQLException e) {e.printStackTrace();}
			}
			return v;
		}

	}

	// /获取个人的各项信息
	public static String getUidMessage(String info) {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		try {
			st = con.createStatement();
			String sql = "select uid,pwd,sculpture,attention,fans,menu,random,menuc,randomc from uid where uid='"
					+ info + "'";
			rs = st.executeQuery(sql);
			if (rs.next()) {
				for (int i = 1; i <= 9; i++) {
					sb.append(rs.getString(i) + "<#>");
				}
			}
			if (sb.length() > 0)
				return sb.substring(0, sb.length() - 3).toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {rs.close();} catch (Exception e) {e.printStackTrace();}
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return Constant.NO_MESSAGE;
	}

	// 增加关注 android gck
	public static Object ADD_ATTENTION = new Object();

	public static Boolean addAttention(String info) {
		synchronized (ADD_ATTENTION) {
			Connection con = getConnection();
			Statement st = null;
			ResultSet rs = null;
			try {
				st = con.createStatement();
				String uid = info.split("<%>")[0];
				String target = info.split("<%>")[1];
				String sql = "select content from attention where uid='" + uid
						+ "'";
				rs = st.executeQuery(sql);
				if (rs.next()) {
					String str = rs.getString(1);
					if (str.contains("#" + target + "#")) {
						return true;
					} else {
						str += target + "#";
						sql = "update attention set content='" + str
								+ "',count=count+1  where uid='" + uid + "'";
						st.executeUpdate(sql);
						updateUIdFans(target, "+1");
						updateAtten(uid, "+1");
						return true;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {rs.close();} catch (Exception e) {e.printStackTrace();}
				try {st.close();} catch (SQLException e) {e.printStackTrace();}
				try {con.close();} catch (SQLException e) {e.printStackTrace();}
			}
			return false; // 关注成功
		}
	}

	// 收藏菜品
	public static Boolean collectionMenu(String user, String menuId) {
		Connection con = getConnection();
		Statement st = null;

		try {
			st = con.createStatement();
			String sql = "update uid set menuContent=concat(menuContent,'"
					+ menuId + "<#>'),menuc=menuc+1 where uid='" + user + "'";
			if (st.executeUpdate(sql) > 0) {
				updateMenuC(menuId, "+1");
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return false;
	}

	// 随拍表 收藏项update android
	public static Boolean updateRandom(String randomId, String update) {
		Connection con = getConnection();
		Statement st = null;
		try {
			st = con.createStatement();
			String sql = "update random set collection=collection" + update
					+ " where id=" + randomId;
			if (st.executeUpdate(sql) > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return false;
	}

	// 获取关注信息
	public static String getAttention(String info) {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "select content from attention where uid='" + info
					+ "'";
			rs = st.executeQuery(sql);
			if (rs.next()) {
				String s = rs.getString(1);
				if (s.length() > 1) {
					return s.substring(1, s.length() - 1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {rs.close();} catch (Exception e) {e.printStackTrace();}
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return Constant.NO_MESSAGE;
	}

	// /取消关注
	public static Boolean deleteAttention(String uid, String target) {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "select content from attention where uid='" + uid
					+ "'";
			rs = st.executeQuery(sql);
			if (rs.next()) {
				String str = rs.getString(1);
				if (!str.contains("#" + target + "#")) {
					return true;
				} else {
					str = str.replace("#" + target + "#", "#");
					sql = "update attention set content='" + str
							+ "',count=count-1 where uid='" + uid + "'";
					st.executeUpdate(sql);
					updateUIdFans(target, "-1");
					updateAtten(uid, "-1");
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {rs.close();} catch (Exception e) {e.printStackTrace();}
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return false;
	}

	// /获取fans
	public static String getFans(String info) {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement pst = null;
		StringBuffer sb = new StringBuffer();
		ArrayList<String> al = null;
		try {
			st = con.createStatement();
			al = new ArrayList<String>();
			String[] sss = info.split("<%>");
			String sql = "select uid from attention where content like '%#"
					+ sss[1] + "#%'";
			rs = st.executeQuery(sql);
			while (rs.next()) {
				al.add(rs.getString(1));
			}
			pst = con
					.prepareStatement("select "
							+ TranslateUtil.getColumn(sss[0])
							+ " from uid where uid=?");
			while (al.size() > 0) {
				pst.setString(1, al.remove(al.size() - 1));
				rs = pst.executeQuery();
				while (rs.next()) {
					int size = sss[0].split("<#>").length;
					for (int i = 1; i <= size; i++)
						sb.append(rs.getString(i) + "<#>");
					sb.append("<%>");
				}
			}
			if (sb.length() > 1) {
				return sb.substring(0, sb.length() - 3).toString();

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {rs.close();} catch (Exception e) {e.printStackTrace();}
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
			try {pst.close();} catch (SQLException e) {e.printStackTrace();}
			
		}
		return Constant.NO_MESSAGE;
	}

	// /获取菜谱收藏 android
	public static String getMenuC(String lastId, String uid) {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement pst = null;
		StringBuffer rms = null;

		try {
			st = con.createStatement();
			String sql = "select menuContent from uid where uid='" + uid + "'";
			System.out.println("sql " + sql);
			rs = st.executeQuery(sql);
			if (rs.next()) {
				String[] ids = rs.getString(1).split("<#>");
				List<String> list = Arrays.asList(ids);
				if (list.size() <= 0)
					return Constant.NO_MESSAGE;
				int indexOf = 0;
				if (!lastId.equals(Constant.NO_MESSAGE)
						&& list.contains(lastId)) {
					indexOf = list.indexOf(lastId);
				}
				String sqll = "select id,cname,uid,primaryPic,food,"
						+ "slike,collection,pinglun,uploadTime "
						+ "from menu where id=?";
				pst = con.prepareStatement(sqll);
				rms = new StringBuffer();
				int k = 0;
				for (; ++indexOf < list.size(); indexOf++) {
					String s = (String) list.get(indexOf);
					if (s.length() > 0) {
						pst.setInt(1, Integer.valueOf(s));
						rs = pst.executeQuery();
						while (rs.next()) {
							for (int i = 1; i <= 9; i++) {
								rms.append(rs.getString(i) + "<#>");
							}
							rms.replace(rms.length() - 3, rms.length(), "<%>");
						}
					}
					if (++k == 5) {
						break; // 每次加载5项
					}
				}
				if (rms.length() > 3) {
					return rms.subSequence(0, rms.length() - 3).toString();
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			try {rs.close();} catch (Exception e) {e.printStackTrace();}
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
			try {if (pst != null)pst.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return Constant.NO_MESSAGE;
	}

	// 获取菜品收藏 pc
	public static String getMenuCPC(String uid) {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement pst = null;
		StringBuffer rms = null;

		try {
			st = con.createStatement();
			String sql = "select menuContent from uid where uid='" + uid + "'";
			System.out.println("sql " + sql);
			rs = st.executeQuery(sql);
			if (rs.next()) {
				String[] ids = rs.getString(1).split("<#>");
				if (ids.length <= 0)
					return Constant.NO_MESSAGE;
				String sqll = "select id,cname from menu where id=?";
				pst = con.prepareStatement(sqll);
				rms = new StringBuffer();
				for (String s : ids) {
					if (s.isEmpty())
						continue;
					pst.setInt(1, Integer.valueOf(s));
					rs = pst.executeQuery();
					while (rs.next()) {
						for (int i = 1; i <= 2; i++) {
							rms.append(rs.getString(i) + "<#>");
						}
						rms.replace(rms.length() - 3, rms.length(), "<%>");
					}
				}
				if (rms.length() > 3) {
					return rms.subSequence(0, rms.length() - 3).toString();
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			try {if (pst != null)pst.close();} catch (SQLException e) {e.printStackTrace();}
			try {rs.close();} catch (Exception e) {e.printStackTrace();}
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return Constant.NO_MESSAGE;
	}

	// /获取随拍收藏 pc
	public static String getRandomC(String lastId, String uid) {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement pst = null;
		try {
			st = con.createStatement();
			String sql = "select randomContent from uid where uid='" + uid
					+ "'";
			rs = st.executeQuery(sql);
			if (rs.next()) {
				String[] ids = rs.getString(1).split("<#>");
				List<String> list = Arrays.asList(ids);
				int indexOf = 0;
				if (!lastId.equals(Constant.NO_MESSAGE)
						&& list.contains(lastId)) {
					indexOf = list.indexOf(lastId);
				}
				String sqll = "select id,picPath,uid,introduce,slike"
						+ ",collection,pinglun,uploadTime,sculpture from random"
						+ " where id=?";
				pst = con.prepareStatement(sqll);
				StringBuffer rms = new StringBuffer();
				int k = 0;
				for (; ++indexOf < ids.length; indexOf++) {
					String s = ids[indexOf];
					if (s.length() > 0) {
						pst.setInt(1, Integer.valueOf(s));
						rs = pst.executeQuery();
						while (rs.next()) {
							for (int i = 1; i <= 9; i++) {
								rms.append(rs.getString(i) + "<#>");
							}
							rms.replace(rms.length() - 3, rms.length(), "<%>");
						}
					}
					if (++k == 5) {
						break; // 每次加载5项
					}
				}
				if (rms.length() > 3) {
					return rms.subSequence(0, rms.length() - 3).toString();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {pst.close();} catch (SQLException e) {e.printStackTrace();}
			try {rs.close();} catch (Exception e) {e.printStackTrace();}
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return Constant.NO_MESSAGE;
	}

	// 获取用户所有随拍收藏pc
	public static String getRandomCPC(String uid) {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement pst = null;
		try {
			st = con.createStatement();
			String sql = "select randomContent from uid where uid='" + uid
					+ "'";
			rs = st.executeQuery(sql);
			if (rs.next()) {
				String[] ids = rs.getString(1).split("<#>");
				String sqll = "select id,label from random" + " where id=?";
				pst = con.prepareStatement(sqll);
				StringBuffer rms = new StringBuffer();
				if (ids.length <= 0) {
					return Constant.NO_MESSAGE;
				}
				for (String s : ids) {
					if (s.isEmpty())
						continue;
					pst.setInt(1, Integer.valueOf(s));
					rs = pst.executeQuery();
					while (rs.next()) {
						String id = rs.getString(1);
						String label = rs.getString(2);
						rms.append(id + "<#>" + label + "<%>");
					}
				}
				if (rms.length() > 3) {
					return rms.subSequence(0, rms.length() - 3).toString();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {pst.close();} catch (SQLException e) {e.printStackTrace();}
			try {rs.close();} catch (Exception e) {e.printStackTrace();}
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return Constant.NO_MESSAGE;
	}

	// /取消菜谱收藏
	public static Boolean deleteMenuC(String uid, String menuId) {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			String sql = "select menuContent from uid where uid='" + uid + "'";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			String content = null;
			if (rs.next()) {
				content = rs.getString(1);
				content = content.replace("<#>" + menuId + "<#>", "<#>");
				sql = "update uid set menuc=menuc-1,menuContent='" + content
						+ "' where uid='" + uid + "'";
				st.executeUpdate(sql);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {rs.close();} catch (Exception e) {e.printStackTrace();}
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return false;
	}

	// /取消随拍收藏
	public static Boolean deleteRandomC(String uid, String ranId) {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "select randomContent from uid where uid='" + uid
					+ "'";
			rs = st.executeQuery(sql);
			String content = null;
			if (rs.next()) {
				content = rs.getString(1);
				content = content.replace("<#>" + ranId + "<#>", "<#>");
				sql = "update uid set randomc=randomc-1,randomContent='"
						+ content + "' where uid='" + uid + "'";
				st.executeUpdate(sql);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {rs.close();} catch (Exception e) {e.printStackTrace();}
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return false;
	}

	// 用户表 菜谱update
	public static int updateUidMenu(String uid, String update) {
		Connection con = getConnection();
		Statement st = null;
		try {
			st = con.createStatement();
			String sql = "update uid set menu=menu" + update + " where uid='"
					+ uid + "'";
			int v = st.executeUpdate(sql);
			return v;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return 0;
	}

	// 用户表 随拍项update
	public static int updateUidRandom(String uid, String update) {
		Connection con = getConnection();
		Statement st = null;
		try {
			st = con.createStatement();
			String sql = "update uid set random=random" + update
					+ " where uid='" + uid + "'";
			int v = st.executeUpdate(sql);
			return v;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return 0;
	}

	// 用户表收藏 update
	public static int updateAtten(String uid, String update) {
		Connection con = getConnection();
		Statement st = null;
		try {
			st = con.createStatement();
			String sql = "update uid set attention=attention" + update
					+ " where uid='" + uid + "'";
			int v = st.executeUpdate(sql);
			return v;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return 0;
	}

	// 用户表 粉丝update
	public static int updateUIdFans(String uid, String update) {
		Connection con = getConnection();
		Statement st = null;
		try {
			st = con.createStatement();
			String sql = "update uid set fans=fans" + update + " where uid='"
					+ uid + "'";
			int v = st.executeUpdate(sql);
			return v;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return 0;
	}

	// 用户表 菜谱收藏update
	public static int updateUidMenuC(String uid, String update) {
		Connection con = getConnection();
		Statement st = null;
		try {
			st = con.createStatement();
			String sql = "update uid set menuc=menuc" + update + " where uid='"
					+ uid + "'";
			int v = st.executeUpdate(sql);
			return v;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return 0;
	}

	// 获取android 广告栏信息
	public static String getRecommend() {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "select id,picPath,tips,upTime from recommend";
			rs = st.executeQuery(sql);
			StringBuffer message = new StringBuffer();
			while (rs.next()) {
				for (int i = 1; i <= 4; i++) {
					message.append(rs.getString(i) + "<#>");
				}
				message.replace(message.length() - 3, message.length(), "<%>");
			}
			if (message.length() > 0) {
				return message.substring(0, message.length() - 3).toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {rs.close();} catch (Exception e) {e.printStackTrace();}
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return Constant.NO_MESSAGE;
	}

	// /模糊搜索随拍 android
	public static String getRandomLike(String timeDiver, int type, String arg) {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "select id,picPath,uid,introduce,slike"
					+ ",collection,pinglun,uploadTime,sculpture from random where state=1 and uploadTime<'"
					+ timeDiver + "'" + Constant.getRandomSql(type, arg);
			System.out.println("sql " + sql);
			rs = st.executeQuery(sql);
			StringBuffer message = new StringBuffer();
			while (rs.next()) {
				for (int i = 1; i <= 9; i++) {
					message.append(rs.getString(i) + "<#>");
				}
				message.replace(message.length() - 3, message.length(), "<%>");
			}
			if (message.length() > 0) {
				return message.substring(0, message.length() - 3).toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {rs.close();} catch (Exception e) {e.printStackTrace();}
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return Constant.NO_MESSAGE;
	}

	// android 获取头像名字(按用户名)
	public static String getHeadImage(String info) {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "select sculpture from uid where uid='" + info + "';";
			rs = st.executeQuery(sql);
			StringBuffer message = new StringBuffer();
			while (rs.next()) {
				message.append(rs.getString(1));
			}
			if (message.length() > 0) {
				return message.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {rs.close();} catch (Exception e) {e.printStackTrace();}
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return null;
	}

	// /android 是否是用户
	public static Boolean isUser(String info) {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		;
		String[] str;
		try {
			str = info.split("<#>");
			st = con.createStatement();
			String sql = "select * from uid where uid='" + str[0]
					+ "' and pwd='" + str[1] + "'";
			rs = st.executeQuery(sql);
			while (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {rs.close();} catch (Exception e) {e.printStackTrace();}
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return false;
	}

	// 是否喜欢过随拍
	public static Boolean iSLikeRandom(String info) {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		;
		String[] str;
		try {
			str = info.split("<#>");
			st = con.createStatement();
			String sql = "select * from random where id=" + str[1]
					+ " and likeUser like '%" + str[0] + "%'";
			rs = st.executeQuery(sql);
			while (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {rs.close();} catch (Exception e) {e.printStackTrace();}
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return false;
	}

	// 随拍表 喜欢项加1 喜欢的用户用户名加
	public static Boolean likeRandom(String info) {
		Connection con = getConnection();
		Statement st = null;
		String[] str = info.split("<#>");
		try {
			st = con.createStatement();
			String sql = "update random set slike=slike+1,likeUser=concat(likeUser,"
					+ "'" + str[0] + "<#>') where id=" + str[1];
			if (st.executeUpdate(sql) > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return false;
	}

	// 是否收藏了随拍 android gck
	public static Boolean isCollection(String info) {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		;
		String[] str;
		try {
			str = info.split("<#>");
			st = con.createStatement();
			String sql = "select * from uid where uid='" + str[0]
					+ "' and randomContent like '%<#>" + str[1] + "<#>%'";
			rs = st.executeQuery(sql);
			while (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {rs.close();} catch (Exception e) {e.printStackTrace();}
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return false;
	}

	// 用户表 随拍收藏加1 随拍收藏添加收藏随拍的id
	public static Boolean collectionRandom(String info) {
		Connection con = getConnection();
		Statement st = null;
		String[] str = info.split("<#>");
		try {
			st = con.createStatement();
			String sql = "update uid set randomc=randomc+1,randomContent=concat(randomContent,"
					+ "'" + str[1] + "<#>') where uid='" + str[0] + "'";
			if (st.executeUpdate(sql) > 0) {
				updateRandom(str[1], "+1");
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return false;
	}

	// 喜欢菜品
	public static Boolean likeMenu(String user, String menuId) {
		Connection con = getConnection();
		Statement st = null;
		try {
			st = con.createStatement();
			String sql = "update menu set slike=slike+1,likeuser=concat(likeuser,'"
					+ user + "<#>') where id=" + menuId;
			if (st.executeUpdate(sql) > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return false;
	}

	// 是否喜欢过菜品
	public static Boolean isLikeMenu(String user, String menuId) {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "select * from menu where id=" + menuId
					+ " and likeuser like '%<#>" + user + "<#>%'";
			rs = st.executeQuery(sql);
			if (rs.next()) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {rs.close();} catch (Exception e) {e.printStackTrace();}
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return false;
	}

	// 是否收藏了菜品
	public static Boolean isCollectionMenu(String user, String menuId) {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "select * from uid where uid='" + user
					+ "' and menuContent like '%<#>" + menuId + "<#>%'";
			rs = st.executeQuery(sql);
			if (rs.next()) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {rs.close();} catch (Exception e) {e.printStackTrace();}
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return false;
	}

	// 菜谱表 收藏加1
	public static Boolean updateMenuC(String menuId, String update) {
		Connection con = getConnection();
		Statement st = null;
		try {
			st = con.createStatement();
			String sql = "update menu set collection=collection" + update
					+ " where id=" + menuId;
			if (st.executeUpdate(sql) > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return false;
	}

	// /模糊搜索菜品 android
	public static String getMenuLike(String timeDiver, String type, String args) {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			// String sql
			// ="select "+TranslateUtil.getColumn(columns)+" from menu where "+selectoionArgs;
			String sql = "select id,cname,uid,primaryPic,food,"
					+ "slike,collection,pinglun,uploadTime,sculpture "
					+ "from menu where state=1 and uploadTime<'" + timeDiver
					+ "'" + Constant.getSql(type, args);
			;
			rs = st.executeQuery(sql);
			System.out.println("sql " + sql);
			StringBuffer message = new StringBuffer();
			while (rs.next()) {
				for (int i = 1; i <= 10; i++) {
					message.append(rs.getString(i) + "<#>");
				}
				message.replace(message.length() - 3, message.length(), "<%>");
			}
			if (message.length() > 0) {
				return message.substring(0, message.length() - 3).toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {rs.close();} catch (Exception e) {e.printStackTrace();}
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return Constant.NO_MESSAGE;
	}

	// 添加菜谱评论
	public static Boolean commentMenu(int menuId, String uidId, String content,
			String picName) {
		Connection con = getConnection();
		Statement st = null;
		try {

			st = con.createStatement();
			String sculpture = getHeadImage(uidId);
			String sql = "insert into commentmenu(menu_id,uid,picPath,word,sculpture) values('"
					+ menuId+ "','"	+ uidId	+ "','"	+ picName+ "','"+ content + "','" + sculpture + "')";
			System.out.println("sql " + sql);
			int i = st.executeUpdate(sql);
			if (i > 0)
			{
				updateMenuPinglun(menuId,"+1");
				return true;

			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return false;
	}

	// 添加随拍评论
	public static Boolean commentRandom(int randomId, String uid,
			String content, String picName) {
		Connection con = getConnection();
		Statement st = null;
		try {
			st = con.createStatement();
			String sculpture = getHeadImage(uid);
			String sql = "insert into commentrandom(random_id,uid,picPath,word,sculpture) values('"
					+ randomId
					+ "','"
					+ uid
					+ "','"
					+ picName
					+ "','"
					+ content + "','" + sculpture + "')";
			int i = st.executeUpdate(sql);
			if (i > 0)
			{
				updateRadomPinglun(randomId,"+1");
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return false;
	}

	// 根据菜品id获取菜品评论
	public static String getCommentM(int menuId, String timeDiver) {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "select id,uid,times,picPath,word,sculpture from commentmenu where  times<'"
					+ timeDiver
					+ "' and menu_id="
					+ menuId
					+ " and state=1 order by times  desc limit 10";
			System.out.println("sql " + sql);
			rs = st.executeQuery(sql);
			StringBuffer message = new StringBuffer();
			while (rs.next()) {
				message.append(rs.getString(1) + "<#>");
				message.append(rs.getString(2) + "<#>");
				message.append(rs.getString(3) + "<#>");
				message.append(rs.getString(4) + "<#>");
				message.append(rs.getString(5) + "<#>");
				message.append(rs.getString(6) + "<%>");
			}
			if (message.length() > 0) {
				return message.substring(0, message.length() - 3);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {rs.close();} catch (Exception e) {e.printStackTrace();}
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return Constant.NO_MESSAGE;
	}

	// 根据随拍id 获取随拍评论pc
	public static String getCommentR(int randomId, String timeDiver) {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "select id,uid,times,picPath,word,sculpture from commentrandom where  times<'"
					+ timeDiver
					+ "' and random_id="
					+ randomId
					+ " and state=1 order by times  desc limit 10";
			rs = st.executeQuery(sql);
			StringBuffer message = new StringBuffer();
			while (rs.next()) {
				message.append(rs.getString(1) + "<#>");
				message.append(rs.getString(2) + "<#>");
				message.append(rs.getString(3) + "<#>");
				message.append(rs.getString(4) + "<#>");
				message.append(rs.getString(5) + "<#>");
				message.append(rs.getString(6) + "<%>");
			}
			if (message.length() > 0) {
				return message.substring(0, message.length() - 3);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {rs.close();} catch (Exception e) {e.printStackTrace();}
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return Constant.NO_MESSAGE;
	}

	// 获取用户所有的菜谱pc
	public static String getMenuByUid(String uid) {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "select id,cname from menu where uid='" + uid + "'";
			rs = st.executeQuery(sql);
			StringBuffer message = new StringBuffer();
			while (rs.next()) {
				message.append(rs.getString(1) + "<#>");
				message.append(rs.getString(2) + "<%>");
			}
			if (message.length() > 0) {
				return message.substring(0, message.length() - 3);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {rs.close();} catch (Exception e) {e.printStackTrace();}
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return Constant.NO_MESSAGE;
	}

	// 获取用户所有的随拍
	public static String getRandomByUid(String uid) {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "select id,introduce,label from random where uid='"
					+ uid + "'";
			rs = st.executeQuery(sql);
			StringBuffer message = new StringBuffer();
			while (rs.next()) {
				message.append(rs.getString(1) + "<#>");
				message.append(rs.getString(2) + "<#>");
				message.append(rs.getString(3) + "<%>");

			}
			if (message.length() > 0) {
				return message.substring(0, message.length() - 3);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {rs.close();} catch (Exception e) {e.printStackTrace();}
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return Constant.NO_MESSAGE;
	}

	// pc 根据城市,标签,状态
	public static String getRandomByCondition(String city, String label,
			String state) {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			StringBuffer sql = new StringBuffer(
					"select id,label,city,uid,introduce,state from random ");
			if (!city.equals(Constant.NO_MESSAGE)) {
				sql.append(" where city='" + city + "'");
				if (!label.equals(Constant.NO_MESSAGE)) {
					sql.append(" and label='" + label + "'");
				}
				if (!state.equals(Constant.NO_MESSAGE)) {
					sql.append(" and state=" + state);
				}
			} else if (!label.equals(Constant.NO_MESSAGE)) {
				sql.append(" where label='" + label + "'");
				if (!state.equals(Constant.NO_MESSAGE)) {
					sql.append(" and state=" + state);
				}
			} else if (!state.equals(Constant.NO_MESSAGE)) {
				sql.append("where state=" + state);
			}
			System.out.println(" sql " + sql);
			rs = st.executeQuery(sql.toString());
			StringBuffer message = new StringBuffer();
			while (rs.next()) {
				message.append(rs.getString(1) + "<#>");
				message.append(rs.getString(2) + "<#>");
				message.append(rs.getString(3) + "<#>");
				message.append(rs.getString(4) + "<#>");
				message.append(rs.getString(5) + "<#>");
				message.append(rs.getBoolean(6) + "<%>");
			}
			if (message.length() > 0) {
				return message.substring(0, message.length() - 3);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {rs.close();} catch (Exception e) {e.printStackTrace();}
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return Constant.NO_MESSAGE;
	}

	// 获取精品菜谱 pc
	public static String getExcellentMenuPC() {	

		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement pst = null;
		ResultSet prs = null;
		try {
			st = con.createStatement();
			String sql = "select menu_id,r_time from menu_recommend order by r_time";
			System.out.println("sql " + sql);
			rs = st.executeQuery(sql);
			pst = con
					.prepareStatement("select cname from menu where state=1 and id=?");
			StringBuffer sb = new StringBuffer();
			while (rs.next()) {
				int id = rs.getInt(1);
				String uploadTime=rs.getString(2);
				pst.setInt(1, id);
				prs = pst.executeQuery();
				if (prs.next()) {
					String cname = prs.getString(1);
					sb.append(id + "<#>" + cname + "<#>" + uploadTime + "<%>");
				}
			}
			if (sb.length() > 0) {
				return sb.substring(0, sb.length() - 3);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {prs.close();} catch (SQLException e) {e.printStackTrace();}
			try {rs.close();} catch (Exception e) {e.printStackTrace();}
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return Constant.NO_MESSAGE;
	}

	// 获取精品菜谱 android
	public static String getExcellentMenu() {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement pst = null;
		ResultSet prs = null;
		try {
			st = con.createStatement();
			String sql = "select menu_id from menu_recommend order by r_time";
			System.out.println("sql " + sql);
			rs = st.executeQuery(sql);
			pst = con
					.prepareStatement("select cname,primaryPic,slike,collection,pinglun from menu where state=1 and id=?");
			StringBuffer sb = new StringBuffer();
			while (rs.next()) {
				int id = rs.getInt(1);
				pst.setInt(1, id);
				prs = pst.executeQuery();
				if (prs.next()) {

					String cname = prs.getString(1);
					String primaryPic = prs.getString(2);
					int slike = prs.getInt(3);
					int collection = prs.getInt(4);
					int pinglun = prs.getInt(5);
					sb.append(id + "<#>" + cname + "<#>" + primaryPic + "<#>"
							+ slike + "<#>" + collection + "<#>" + pinglun
							+ "<%>");
				}
			}
			if (sb.length() > 0) {
				return sb.substring(0, sb.length() - 3);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {prs.close();} catch (SQLException e) {e.printStackTrace();}
			try {rs.close();} catch (Exception e) {e.printStackTrace();}
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		
		}
		return Constant.NO_MESSAGE;
	}

	// 获取精品随拍 Pc
	public static String getExcellentRandomPC() {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "select random_id,r_time from random_recommend order by r_time";
			System.out.println("sql " + sql);
			rs = st.executeQuery(sql);
			StringBuffer sb = new StringBuffer();
			while (rs.next()) {
				String randomId = rs.getString(1);
				String rTime = rs.getString(2);
				sb.append(randomId + "<#>" + rTime + "<%>");
			}
			if (sb.length() > 0) {
				return sb.substring(0, sb.length() - 3);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {rs.close();} catch (Exception e) {e.printStackTrace();}
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return Constant.NO_MESSAGE;
	}

	// 获取精品随拍 android
	public static String getExcellentRandom() {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement pst = null;
		ResultSet prs = null;
		try {
			st = con.createStatement();
			String sql = "select random_id from random_recommend order by r_time";
			System.out.println("sql " + sql);
			rs = st.executeQuery(sql);
			pst = con
					.prepareStatement("select introduce,picPath,slike,collection,pinglun from random where state=1 and id=?");
			StringBuffer sb = new StringBuffer();
			while (rs.next()) {
				int id = rs.getInt(1);
				pst.setInt(1, id);
				prs = pst.executeQuery();
				if (prs.next()) {
					String introduce = prs.getString(1);
					String picPath = prs.getString(2);
					int slike = prs.getInt(3);
					int collection = prs.getInt(4);
					int pinglun = prs.getInt(5);
					sb.append(id + "<#>" + introduce + "<#>" + picPath + "<#>"
							+ slike + "<#>" + collection + "<#>" + pinglun
							+ "<%>");
				}
			}
			if (sb.length() > 0) {
				return sb.substring(0, sb.length() - 3);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {prs.close();} catch (SQLException e) {	e.printStackTrace();}
			try {rs.close();} catch (Exception e) {e.printStackTrace();}
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return Constant.NO_MESSAGE;
	}

	// 添加主推荐
	public static Boolean upLoadPrimaryRecommend(String picName, String info) {
		Connection con = getConnection();
		Statement st = null;
		try {
			st = con.createStatement();
			String sql = "insert into recommend(picPath,tips) values('"
					+ picName + "','" + info + "')";
			System.out.println("sql " + sql);
			int v = st.executeUpdate(sql);
			if (v > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return false;
	}

	public static Boolean deletePrimaryRecommend(String id) {
		Connection con = getConnection();
		Statement st = null;
		try {
			st = con.createStatement();
			String sql = "delete from recommend where id=" + id;
			System.out.println("sql " + sql);
			int v = st.executeUpdate(sql);
			if (v > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return false;
	}

	// / 添加精品菜谱
	public static Boolean addExcellentMenu(String menuId) {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "select cname from menu where id=" + menuId;
			System.out.println("sql " + sql);
			rs = st.executeQuery(sql);
			if (rs.next()) {
				sql = "insert into menu_recommend(menu_id) values("
						+ menuId + ")";
				System.out.println("sql " + sql);

				int v = st.executeUpdate(sql);
				if (v > 0)
					return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {rs.close();} catch (Exception e) {e.printStackTrace();}
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return false;
	}

	// 删除精品菜谱
	public static Boolean deleteExcellentMenu(String menuId) {
		Connection con = getConnection();
		Statement st = null;
		try {
			st = con.createStatement();
			String sql = "delete from menu_recommend where menu_id=" + menuId;
			System.out.println("sql " + sql);
			int v = st.executeUpdate(sql);
			if (v > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return false;
	}

	// / 添加精品随拍
	public static Boolean addExcellentRandom(String randomId) {
		Connection con = getConnection();
		Statement st = null;
		try {
			st = con.createStatement();
			String sql = "insert into random_recommend(random_id) values("
					+ randomId + ")";
			System.out.println("sql " + sql);
			int v = st.executeUpdate(sql);
			if (v > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return false;
	}

	// 删除精品随拍
	public static Boolean deleteExcellentRandom(String randomId) {
		Connection con = getConnection();
		Statement st = null;
		try {
			st = con.createStatement();
			String sql = "delete from random_recommend where random_id="
					+ randomId;
			System.out.println("sql " + sql);
			int v = st.executeUpdate(sql);
			if (v > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return false;
	}

	// 注册
	public static Boolean register(String name, String passward,
			String sculpture, String sex) {
		Connection con = getConnection();
		Statement st = null;
		try {
			st = con.createStatement();
			String sql = "insert into uid(uid,pwd,sculpture,sex) values('"
					+ name + "','" + passward + "','" + sculpture + "','" + sex
					+ "')";
			System.out.println("sql " + sql);
			int v = st.executeUpdate(sql);
			if (v > 0) {
				sql = "insert into attention(uid) values('" + name + "')";
				System.out.println("sql");
				st.executeUpdate(sql);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return false;
	}

	// 禁止随拍通过
	public static Boolean forbidRandom(String randomId) {
		Connection con = null;
		Statement st = null;
		try {
			con = getConnection();
			st = con.createStatement();
			String sql = "update random set state=0 where id=" + randomId;
			System.out.println("sql " + sql);
			int v = st.executeUpdate(sql);
			if (v > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return false;
	}

	// 允许随拍通过
	public static Boolean permitRandom(String randomId) {
		Connection con = null;
		Statement st = null;
		try {
			con = getConnection();
			st = con.createStatement();
			String sql = "update random set state=1 where id=" + randomId;
			System.out.println("sql " + sql);
			int v = st.executeUpdate(sql);
			if (v > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return false;
	}

	// 禁止菜品通过
	public static Boolean forbidMenu(String menuId) {
		Connection con = null;
		Statement st = null;
		try {
			con = getConnection();
			st = con.createStatement();
			String sql = "update menu set state=0 where id=" + menuId;
			System.out.println("sql " + sql);
			int v = st.executeUpdate(sql);
			if (v > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return false;
	}

	// 允许随拍通过
	public static Boolean permitMenu(String menuId) {
		Connection con = null;
		Statement st = null;
		try {
			con = getConnection();
			st = con.createStatement();
			String sql = "update menu set state=1 where id=" + menuId;
			System.out.println("sql " + sql);
			int v = st.executeUpdate(sql);
			if (v > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return false;
	}

	// 获取可以推荐的内容
	public static String getRandomLeft(String city, String label) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			st = con.createStatement();
			String sql = "select id,label,city,uid,introduce  from random where state=1 and id not in(select random_id from random_recommend) ";
			if (!city.equals(Constant.NO_MESSAGE)) {
				if (!label.equals(Constant.NO_MESSAGE)) {
					sql += ",label='" + label + "'";
				}
			} else if (!label.equals(Constant.NO_MESSAGE)) {
				sql += " and label='" + label + "'";
			}
			System.out.println("sql " + sql);
			rs = st.executeQuery(sql.toString());
			StringBuffer message = new StringBuffer();
			while (rs.next()) {
				message.append(rs.getString(1) + "<#>");
				message.append(rs.getString(2) + "<#>");
				message.append(rs.getString(3) + "<#>");
				message.append(rs.getString(4) + "<#>");
				message.append(rs.getString(5) + "<%>");
			}
			if (message.length() > 0) {
				return message.substring(0, message.length() - 3);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {rs.close();} catch (Exception e) {e.printStackTrace();}
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return Constant.NO_MESSAGE;
	}

	// 获取可以推荐的菜谱
	public static String getMenuLeft(String style, String flavour,
			String ctime, String difficulty) {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "select id,cname,uid,style,flavour,craft,ctime,tools,"
					+ "difficulty from menu where state=1 and id not in(select menu_id from menu_recommend )";
			StringBuffer sb = new StringBuffer();
			if (!style.equals(Constant.NO_MESSAGE))
				sb.append(" and style='" + style + "' ");
			if (!flavour.equals(Constant.NO_MESSAGE))
				sb.append(" and flavour='" + flavour + "' ");
			if (!ctime.equals(Constant.NO_MESSAGE))
				sb.append(" and ctime='" + ctime + "' ");
			if (!difficulty.endsWith(Constant.NO_MESSAGE))
				sb.append(" and difficulty='" + difficulty + "' ");
			if (sb.length() > 0)
				sql += " and " + sb.substring(4, sb.length());
			System.out.println("sql " + sql);
			rs = st.executeQuery(sql);
			StringBuffer message = new StringBuffer();
			while (rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(2);
				String uid = rs.getString(3);
				String mstyle = rs.getString(4);
				String mflavour = rs.getString(5);
				String craft = rs.getString(6);
				String mctime = rs.getString(7);
				String tools = rs.getString(8);
				String mdifficulty = rs.getString(9);
				message.append(id + "<#>" + name + "<#>" + uid + "<#>" + mstyle
						+ "<#>" + mflavour + "<#>" + craft + "<#>" + mctime
						+ "<#>" + tools + "<#>" + mdifficulty + "<%>");
			}
			if (message.length() > 0) {
				return message.substring(0, message.length() - 3).toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {rs.close();} catch (Exception e) {e.printStackTrace();}
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return Constant.NO_MESSAGE;
	}

	// 按条件查找菜品评论 pc
	public static String getMenuPinglunSelect(String date, String dataFormate,
			String state) {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			// DATE_FORMAT(entertime,'%Y-%m-%d')='2014-09-18';
			st = con.createStatement();
			String sql = "select id,"
					+ "uid,word,times,state,picPath from commentmenu";
			StringBuffer sb = new StringBuffer();
			if (!dataFormate.equals(Constant.NO_MESSAGE)) // 日期，格式
			{
				sb.append(" where DATE_FORMAT(times,'" + dataFormate + "')='"
						+ date + "'");
			}
			if (!state.equals(Constant.NO_MESSAGE)) {
				if (sb.length() > 0) {
					sb.append(" and ");
				} else {
					sb.append(" where ");
				}
				sb.append(" state=" + state);
			}
			sql += sb.toString();
			System.out.println("sql " + sql);
			rs = st.executeQuery(sql);
			StringBuffer message = new StringBuffer();
			while (rs.next()) {
				String menu_id = rs.getString(1);
				String uid = rs.getString(2);
				String word = rs.getString(3);
				String times = rs.getString(4);
				Boolean mstate = rs.getBoolean(5);
				String picName = rs.getString(6);
				message.append(menu_id + "<#>" + uid + "<#>" + word + "<#>"
						+ times + "<#>" + mstate + "<#>" + picName + "<%>");
			}
			if (message.length() > 0) {
				return message.substring(0, message.length() - 3).toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {rs.close();} catch (Exception e) {e.printStackTrace();}
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return Constant.NO_MESSAGE;
	}

	// 禁止菜品评论 pc
	public static Boolean forbidMenuPinglun(String pinglunId) {
		Connection con = getConnection();
		Statement st = null;
		try {
			st = con.createStatement();
			String sql = "update commentmenu set state=0 where id=" + pinglunId;
			System.out.println("sql " + sql);
			if (st.executeUpdate(sql) > 0) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return false;
	}

	// 通过菜品评论 pc
	public static Boolean permitMenuPinglun(String pinglunId) {
		Connection con = getConnection();
		Statement st = null;
		try {
			st = con.createStatement();
			String sql = "update commentmenu set state=1 where id=" + pinglunId;
			System.out.println("sql " + sql);
			if (st.executeUpdate(sql) > 0) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return false;
	}

	// 按条件查找随拍评论 pc
	public static String getRandomPinglunSelect(String date,
			String dataFormate, String state) {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			// DATE_FORMAT(entertime,'%Y-%m-%d')='2014-09-18';
			st = con.createStatement();
			String sql = "select id,"
					+ "uid,word,times,state,picPath from commentrandom";
			StringBuffer sb = new StringBuffer();
			if (!dataFormate.equals(Constant.NO_MESSAGE)) // 日期，格式
			{
				sb.append(" where DATE_FORMAT(times,'" + dataFormate + "')='"
						+ date + "'");
			}
			if (!state.equals(Constant.NO_MESSAGE)) {
				if (sb.length() > 0) {
					sb.append(" and ");
				} else {
					sb.append(" where ");
				}
				sb.append(" state=" + state);
			}
			sql += sb.toString();
			System.out.println("sql " + sql);
			rs = st.executeQuery(sql);
			StringBuffer message = new StringBuffer();
			while (rs.next()) {
				String menu_id = rs.getString(1);
				String uid = rs.getString(2);
				String word = rs.getString(3);
				String times = rs.getString(4);
				Boolean mstate = rs.getBoolean(5);
				String picName = rs.getString(6);
				message.append(menu_id + "<#>" + uid + "<#>" + word + "<#>"
						+ times + "<#>" + mstate + "<#>" + picName + "<%>");
			}
			if (message.length() > 0) {
				return message.substring(0, message.length() - 3).toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {rs.close();} catch (Exception e) {e.printStackTrace();}
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return Constant.NO_MESSAGE;
	}

	// 禁止菜品评论 pc
	public static Boolean forbidRandomPinglun(String pinglunId) {
		Connection con = getConnection();
		Statement st = null;
		try {
			st = con.createStatement();
			String sql = "update commentrandom set state=0 where id="
					+ pinglunId;
			System.out.println("sql " + sql);
			if (st.executeUpdate(sql) > 0) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return false;
	}

	// 是否是精品菜谱
	public static Boolean isExcellentMenu(String MenuId) {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "select * from menu_recommend where menu_id=" + MenuId;
			System.out.println("sql " + sql);
			rs = st.executeQuery(sql);
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {rs.close();} catch (Exception e) {e.printStackTrace();}
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return false;
	}

	// 是否是精品随拍
	public static Boolean isExcellentRandom(String randomId) {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "select * from random_recommend where random_id="
					+ randomId;
			System.out.println("sql " + sql);
			rs = st.executeQuery(sql);
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {rs.close();} catch (Exception e) {e.printStackTrace();}
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return false;
	}

	// 通过菜品评论 pc
	public static Boolean permitRandomPinglun(String pinglunId) {
		Connection con = getConnection();
		Statement st = null;
		try {
			st = con.createStatement();
			String sql = "update commentrandom set state=1 where id="
					+ pinglunId;
			System.out.println("sql " + sql);
			if (st.executeUpdate(sql) > 0) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return false;
	}

	// 获取所有菜系
	public static String getStyle() {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "select * from style ";
			System.out.println("sql " + sql);
			rs = st.executeQuery(sql);
			StringBuffer sb = new StringBuffer();
			while (rs.next()) {
				sb.append(rs.getString(1) + "<#>");
			}
			if (sb.length() > 0) {
				return sb.substring(0, sb.length() - 3);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return null;
	}

	// 获取所有口味
	public static String getFlavour() {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "select * from flavour ";
			System.out.println("sql " + sql);
			rs = st.executeQuery(sql);
			StringBuffer sb = new StringBuffer();
			while (rs.next()) {
				sb.append(rs.getString(1) + "<#>");
			}
			if (sb.length() > 0) {
				return sb.substring(0, sb.length() - 3);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return null;
	}

	// 获取所有工艺
	public static String getCraft() {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "select * from craft ";
			System.out.println("sql " + sql);
			rs = st.executeQuery(sql);
			StringBuffer sb = new StringBuffer();
			while (rs.next()) {
				sb.append(rs.getString(1) + "<#>");
			}
			if (sb.length() > 0) {
				return sb.substring(0, sb.length() - 3);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return null;
	}

	// 获取所有制作时间
	public static String getTime() {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "select * from ctime ";
			System.out.println("sql " + sql);
			rs = st.executeQuery(sql);
			StringBuffer sb = new StringBuffer();
			while (rs.next()) {
				sb.append(rs.getString(1) + "<#>");
			}
			if (sb.length() > 0) {
				return sb.substring(0, sb.length() - 3);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return null;
	}

	// 获取所有厨具
	public static String getTool() {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "select * from tool ";
			System.out.println("sql " + sql);
			rs = st.executeQuery(sql);
			StringBuffer sb = new StringBuffer();
			while (rs.next()) {
				sb.append(rs.getString(1) + "<#>");
			}
			if (sb.length() > 0) {
				return sb.substring(0, sb.length() - 3);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return null;
	}

	// 获取所有难度
	public static String getDifficulty() {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "select * from difficulty ";
			System.out.println("sql " + sql);
			rs = st.executeQuery(sql);
			StringBuffer sb = new StringBuffer();
			while (rs.next()) {
				sb.append(rs.getString(1) + "<#>");
			}
			if (sb.length() > 0) {
				return sb.substring(0, sb.length() - 3);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return null;
	}
	// 获取所有标签
		public static String getLabel() {
			Connection con = getConnection();
			Statement st = null;
			ResultSet rs = null;
			try {
				st = con.createStatement();
				String sql = "select * from label ";
				System.out.println("sql " + sql);
				rs = st.executeQuery(sql);
				StringBuffer sb = new StringBuffer();
				while (rs.next()) {
					sb.append(rs.getString(1) + "<#>");
				}
				if (sb.length() > 0) {
					return sb.substring(0, sb.length() - 3);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {st.close();} catch (SQLException e) {e.printStackTrace();}
				try {con.close();} catch (SQLException e) {e.printStackTrace();}
			}
			return null;
		}
	//菜品评论数目update
	public static void updateMenuPinglun(int menuId,String update)
	{
		Connection con = getConnection();
		Statement st = null;
		try {
			st = con.createStatement();
			String sql = "update menu set pinglun=pinglun" + update + " where id="
					+ menuId;
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}	
	}

	// 菜平评论数目update
	public static void updateRadomPinglun(int randomId, String update) {
		Connection con = getConnection();
		Statement st = null;
		try {
			st = con.createStatement();
			String sql = "update random set pinglun=pinglun" + update
					+ " where id=" + randomId;
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
	}
}
