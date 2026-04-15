package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pojo.User;
import util.DBConnection;
import util.Date_String;

public class UserBpo {
	/**验证用户登录信息*/
	public static boolean validlogin(String name, String password)throws SQLException{
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
			// 4、遍历结果集
			rs=st.executeQuery(sql);
			if(rs.next()==true) successflag=true;
		} finally {
			// 5、关闭资源对象
			DBConnection.close(rs, st, conn);
		}
		return successflag;
	}
	/**查询用户信息*/
	public static List<User> getUsers()throws SQLException{
		List<User> users=new ArrayList<User>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			// 2、获得连接对象
			conn = DBConnection.getConnection();
			// 定义sql语句
			String sql = "select * from user";
			// 3、创建语句对象
			st = conn.createStatement();
			// 4、遍历结果集
			rs=st.executeQuery(sql);
			while(rs.next()==true){
				int userid=rs.getInt("id");
				String username=rs.getString("name");
				String password=rs.getString("password");
				String sex=rs.getString("sex");
				int age=rs.getInt("age");
				String birthday=Date_String.getStringDate1(rs.getDate("birthday"));
				User tmp=new User(userid,username,password,sex,age,birthday);
				users.add(tmp);
			}
		} finally {
			// 5、关闭资源对象 
			DBConnection.close(rs, st, conn);
		}
		return users;
	}
	/**查询单个用户信息*/
	public static User getUserById(String userid)throws SQLException{
		User user=null;
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			// 2、获得连接对象
			conn = DBConnection.getConnection();
			// 定义sql语句
			String sql = "select * from user where id="+userid;
			// 3、创建语句对象
			st = conn.createStatement();
			// 4、遍历结果集
			rs=st.executeQuery(sql);
			if(rs.next()==true){
				String username=rs.getString("name");
				String password=rs.getString("password");
				String sex=rs.getString("sex");
				int age=rs.getInt("age");
				String birthday=Date_String.getStringDate1(rs.getDate("birthday"));
				user=new User(Integer.valueOf(userid),username,password,sex,age,birthday);
			}
		} finally {
			// 5、关闭资源对象 
			DBConnection.close(rs, st, conn);
		}
		return user;
	}
	/**修改用户个人信息*/
	public static void updateUser(User user)throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "update user set name=?,sex=?,age=?,birthday=? where id="+user.getUserid();
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getSex());
			ps.setInt(3, user.getAge());
			ps.setTimestamp(4, Date_String.toTimestamp(user.getBirthday()));
			
			ps.executeUpdate();
		} finally {
			// 5、关闭资源对象 
			DBConnection.close(rs, ps, conn);
		}
	}
	/**删除用户个人信息*/
	public static void delUserById(String userid)throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "delete from user where id="+userid;
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
		} finally {
			// 5、关闭资源对象 
			DBConnection.close(rs, ps, conn);
		}
	}
	/**增加用户信息*/
	public static void addUser(User user)throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "insert into user(id,name,password,sex,age,birthday) values(?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, user.getUserid());
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getSex());
			ps.setInt(5, user.getAge());
			ps.setTimestamp(6, Date_String.toTimestamp(user.getBirthday()));
			ps.executeUpdate();
		} finally {
			// 5、关闭资源对象 
			DBConnection.close(rs, ps, conn);
		}
	}
	
	public static void addUsers()throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> users=new ArrayList<User>();
		User user1=new User(33333, "用户1");
		User user2=new User(33334, "用户2");
		User user3=new User(33335, "用户3");
		users.add(user1);
		users.add(user2);
		users.add(user3);
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			for(User user:users) {
				String sql = "insert into user(id,name) values(?,?)";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, user.getUserid());
				ps.setString(2, user.getUsername());
				ps.executeUpdate();
			}
			conn.commit();
		} finally {
			// 5、关闭资源对象 
			conn.setAutoCommit(true);
			DBConnection.close(rs, ps, conn);
		}
	}
	public static void main(String[] args) throws Exception{
		UserBpo.addUsers();
	}
}
