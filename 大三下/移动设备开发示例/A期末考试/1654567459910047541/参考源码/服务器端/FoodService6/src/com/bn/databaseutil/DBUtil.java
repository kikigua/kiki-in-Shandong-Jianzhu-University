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
		Connection con = null;						//声明连接
		try {
			Class.forName("org.gjt.mm.mysql.Driver");	//声明驱动
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"
					+ "foodbase?useUnicode=true&characterEncoding=UTF-8",
					"root", "");						//获得连接
		} catch (Exception e) {e.printStackTrace();}
		return con;									//返回连接	
	}
	
	
	// 获取所有菜谱 android
	public static String getAllMenu() {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement pst = null;
		ResultSet prs = null;
		try {
			st = con.createStatement();	
			String sql = "select id from menu order by id";
			System.out.println("sql " + sql);
			rs = st.executeQuery(sql);
			pst = con.prepareStatement("select cname,primarypic,food from menu where id=?");
			StringBuffer sb = new StringBuffer();
			//循环读取每一条记录，并将每条记录按指定格式拼接在一起。
			while (rs.next()) {
				int id = rs.getInt(1);  
				pst.setInt(1, id);
				prs = pst.executeQuery();
				if (prs.next()) {
					String cname = prs.getString(1);
					String primaryPic = prs.getString(2);
					String food = prs.getString(3);
					sb.append(id + "<#>" + cname + "<#>" + primaryPic + "<#>"+ food + "<#>"+"<%>");
				}
			}
			if (sb.length() > 0) {
				System.out.println(sb);
				System.out.println(sb.substring(0, sb.length()));
				return sb.substring(0, sb.length());				
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			try {prs.close();} catch (SQLException e) {e.printStackTrace();}
			try {rs.close();} catch (Exception e) {e.printStackTrace();}
			try {st.close();} catch (SQLException e) {e.printStackTrace();}
			try {con.close();} catch (SQLException e) {e.printStackTrace();}
		
		}
		return Constant.NO_MESSAGE;
	}	
}
	