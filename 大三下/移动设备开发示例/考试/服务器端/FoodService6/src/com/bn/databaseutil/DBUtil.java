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
		Connection con = null;						//��������
		try {
			Class.forName("org.gjt.mm.mysql.Driver");	//��������
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"
					+ "foodbase?useUnicode=true&characterEncoding=UTF-8",
					"root", "root");						//�������
		} catch (Exception e) {e.printStackTrace();}
		return con;									//��������	
	}
	
	
	// ��ȡ���в��� android
	public static String getAllMenu() {
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement pst = null;
		ResultSet prs = null;
		try {
			st = con.createStatement();
			String sql = "select id from reader order by id";
			System.out.println("sql " + sql);
			rs = st.executeQuery(sql);
			pst = con.prepareStatement("select title,author,datetime,img from reader where id=?");
			StringBuffer sb = new StringBuffer();
			//ѭ����ȡÿһ����¼������ÿ����¼��ָ����ʽƴ����һ��
			while (rs.next()) {
				int id = rs.getInt(1);  
				pst.setInt(1, id);
				prs = pst.executeQuery();
				if (prs.next()) {
					String title= prs.getString(1);
					String author = prs.getString(2);
					String datetime = prs.getString(3);
					String img = prs.getString(4);
					sb.append(id + "<#>" + title + "<#>" + author + "<#>" + datetime + "<#>" + img + "<#>" + "<%>");
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
	




//package com.bn.databaseutil;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import com.bn.util.Constant;
//
//
//public class DBUtil {
//
//	public static Connection getConnection() {
//		Connection con = null;						//��������
//		try {
//			Class.forName("org.gjt.mm.mysql.Driver");	//��������
//			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"
//					+ "foodbase?useUnicode=true&characterEncoding=UTF-8",
//					"root", "root");						//�������
//		} catch (Exception e) {e.printStackTrace();}
//		return con;									//��������	
//	}
//	
//	
//	// ��ȡ���в��� android
//	public static String getAllMenu() {
//		Connection con = getConnection();
//		Statement st = null;
//		ResultSet rs = null;
//		PreparedStatement pst = null;
//		ResultSet prs = null;
//		try {
//			st = con.createStatement();	
//			String sql = "select id from menu2 order by id";
//			System.out.println("sql " + sql);
//			rs = st.executeQuery(sql);
//			pst = con.prepareStatement("select title,img1,img2,img3,timetile,num from menu2 where id=?");
//			StringBuffer sb = new StringBuffer();
//			//ѭ����ȡÿһ����¼������ÿ����¼��ָ����ʽƴ����һ��
//			while (rs.next()) {
//				int id = rs.getInt(1);  
//				pst.setInt(1, id);
//				prs = pst.executeQuery();
//				if (prs.next()) {
//					String title = prs.getString(1);
//					String img1 = prs.getString(2);
//					String img2 = prs.getString(3);
//					String img3 = prs.getString(4);
//					String timetile = prs.getString(5);
//					String num = prs.getString(6);
//					sb.append(id + "<#>" + title + "<#>" + img1 + "<#>"+img2 + "<#>"+img3 + "<#>"+ timetile + "<#>"+num+ "<#>"+"<%>");
//				}
//			}
//			if (sb.length() > 0) {
//				System.out.println(sb);
//				System.out.println(sb.substring(0, sb.length()));
//				return sb.substring(0, sb.length());				
//			}
//		} 
//		catch (Exception e) {
//			e.printStackTrace();
//		} 
//		finally {
//			try {prs.close();} catch (SQLException e) {e.printStackTrace();}
//			try {rs.close();} catch (Exception e) {e.printStackTrace();}
//			try {st.close();} catch (SQLException e) {e.printStackTrace();}
//			try {con.close();} catch (SQLException e) {e.printStackTrace();}
//		
//		}
//		return Constant.NO_MESSAGE;
//	}	
//}
//	