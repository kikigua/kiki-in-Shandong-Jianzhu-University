package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import util.DBConnection;

public class UserLogin {
	public static boolean validlogin(String name, String password)throws Exception{
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		boolean successflag=false;
		try {
			// 2、获得连接对象
			conn = DBConnection.getConnection();
			// 定义sql语句
			String sql = "select * from user where name='"+name+"' and password='"+password+"'";
			// 3、创建语句对象
			st = conn.createStatement();
			// 4、遍历结果集（此处不需要）
			rs=st.executeQuery(sql);
			if(rs.next()==true) successflag=true;
		} catch(Exception e){
			throw new Exception("登录失败："+e.getMessage());
		}finally {
			// 5、关闭资源对象
			DBConnection.close(rs, st, conn);
		}
		return successflag;
	}
	

}
