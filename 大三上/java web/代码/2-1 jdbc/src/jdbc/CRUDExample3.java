package jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import org.junit.Test;

import util.DBConnection;
/**
 * prepareStatement  transaction
 * */
public class CRUDExample3{
	
	//add
	@Test
	public void add() throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			conn = DBConnection.getConnection();
			String sql = "insert into user(id,name,password,sex,age,birthday) values(?,?,?,?,?,?)";
		    ps = conn.prepareStatement(sql);
		    //设置参数
		    ps.setInt(1, 2);
			ps.setString(2, "zhangsan");
			ps.setString(3, "123");
			ps.setString(4, "男");
			ps.setInt(5, 21);
			Calendar c = Calendar.getInstance();
			c.set(1990, 12, 31);
			ps.setDate(6, new java.sql.Date(c.getTime().getTime()));
			//执行SQL
		    ps.executeUpdate();
		}finally{
			DBConnection.close(rs, ps, conn);
		}
    }
	
	//add with transaction
	@Test
	public void addwithtrans() throws SQLException {
		Connection conn = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		try{
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			
			String sql1 = "insert into user(id,name,password,sex,age,birthday) values(2,'zhangsan','123','man',21,'1990-12-31')";
		    String sql2="insert into student(stuid,name,sex,age,birthday) values(2,'zhangsan','man',21,'1990-12-31')";
			
		    ps1 = conn.prepareStatement(sql1);
			ps1.executeUpdate();
			
			ps2 = conn.prepareStatement(sql2);
			ps2.executeUpdate();
			
			conn.commit();
		}catch(SQLException e){
			e.printStackTrace();
			//操作出现异常，撤销事务  
			conn.rollback();
		}finally{
			conn.setAutoCommit(true);
			DBConnection.close(null, ps1, null);
			DBConnection.close(rs, ps2, conn);
		}
    }
	//update
	public static void update() throws SQLException {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try{
			conn = DBConnection.getConnection();
			String sql = "update user set password='222222' where id=2";
		    st = conn.createStatement();
		    st.executeUpdate(sql);
		}finally{
			DBConnection.close(rs, st, conn);
		}
    }
	//delete
	@Test
	public void delete() throws SQLException {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try{
			conn = DBConnection.getConnection();
			String sql = "delete from user where id=2";
		    st = conn.createStatement();
		    st.executeUpdate(sql);
		}finally{
			DBConnection.close(rs, st, conn);
		}
    }
	//get
	@Test
	public void get() throws SQLException {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try{
			conn = DBConnection.getConnection();
			String sql = "select id,name, password,sex,age,birthday from user";
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				int id=rs.getInt("id");
				String name=rs.getString("name");
				String password=rs.getString("password");
				String sex=rs.getString("sex");
				int age=rs.getInt("age");
				Date birthday = rs.getDate("birthday");
				System.out.println("id=" + id + ";name=" + name + ";password="
						+ password + ";sex=" + sex + ";age=" + age + ";birthday="
						+ birthday);
			}
		}finally{
			DBConnection.close(rs, st, conn);
		}
    }
	
}
