package jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import pojo.User;
import util.DBConnection;
/**
 *Statement
 * */
public class CRUDExample1{
	//add
	public void add(User user) throws Exception{
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try{
			conn = DBConnection.getConnection();
			String sql = "insert into user(id,name,password) "
					+ "values("+user.getId()+",'"+user.getName()+"','"+user.getPassword()+"')";
			st = conn.createStatement();
		    st.executeUpdate(sql);
		}catch(Exception e){
			//System.out.println("增加用户失败："+e.getMessage());
			
			throw new Exception("增加用户失败："+e.getMessage());
		}finally{
			DBConnection.close(rs, st, conn);
		}
    }
	@Test
	public void test() throws Exception{
		User user=new User(22,"test","1");
		this.add(user);
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
		}catch(Exception e){
			System.out.println("更新用户失败："+e.getMessage());
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
		}catch(Exception e){
			System.out.println("删除用户失败："+e.getMessage());
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
	@Test
	public void getByName() throws SQLException {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try{
			conn = DBConnection.getConnection();
			String sql = "select id,name, password,sex,age,birthday from user where name like ?";
			st = conn.prepareStatement(sql);
			st.setString(1,"%");
			rs = st.executeQuery();
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
