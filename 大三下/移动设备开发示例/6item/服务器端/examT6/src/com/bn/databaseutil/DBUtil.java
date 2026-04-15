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

public class DBUtil {
	public static Connection getConnection() {
		Connection con = null; // 声明连接
		try {
			Class.forName("org.gjt.mm.mysql.Driver"); // 声明驱动
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/" + "android_exam1?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC", "root", "123456"); // 获得连接
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con; // 返回连接
	}
	//按编号查询新闻
	public static String searchNews(String id) {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "select * from t6 where id=" + id;
			System.out.println("sql " + sql);
			rs = st.executeQuery(sql);
			StringBuffer message = new StringBuffer();
			while (rs.next()) {
				for (int i = 1; i <= 5; i++) {
					message.append(rs.getString(i) + "<#>");
				}
				if (message.length() > 0) {
					return message.substring(0, message.length() - 3).toString();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return Constant.NO_MESSAGE;
	}
	// 获取全部新闻
	public static String searchAllNews() {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement pst = null;
		ResultSet prs = null;
		try {
			st = con.createStatement();
			// String sql = "select menu_id from menu_recommend order by r_time";
			String sql = "select id from t6 order by id";
			System.out.println("sql " + sql);
			rs = st.executeQuery(sql);
			pst = con.prepareStatement("select * from t6 where id=?");
			StringBuffer sb = new StringBuffer();
			// 循环读取每一条记录，并将每条记录按指定格式拼接在一起。
			while (rs.next()) {
				int id = rs.getInt(1);
				pst.setInt(1, id);
				prs = pst.executeQuery();
				if (prs.next()) {
					int idd = prs.getInt(1);
					String title = prs.getString(2);
					String source = prs.getString(3);
					String uploadTime = prs.getString(4);
					String pic = prs.getString(5);
					sb.append(idd + "<#>" + title + "<#>" + source + "<#>" + uploadTime + "<#>" + pic + "<%>");
					System.out.println(idd + "<#>" + title + "<#>" + source + "<#>" + uploadTime + "<#>" + pic + "<%>");
				}
			}
			if (sb.length() > 0) {
				return sb.substring(0, sb.length() - 3);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				prs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return Constant.NO_MESSAGE;
	}
	
	public static void main(String[] args) {
		DBUtil.searchAllNews();
	}
}