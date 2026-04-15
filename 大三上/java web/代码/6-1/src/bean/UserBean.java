package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import bpo.UserBpo;
import util.DBConnection;
import util.Date_String;

public class UserBean {
	private String userid;
	private String username;
	private String password;
	private String confirmpassword;
	private String sex;
	private String age;
	private String birthday;
	public String getUserid() {
		if(userid==null) userid="";
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		if(username==null) username="";
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		if(password==null) password="";
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		if(sex==null) sex="";
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		if(age==null) age="";
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getBirthday() {
		if(birthday==null) birthday="";
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	/**验证用户登录信息*/
	public boolean validlogin()throws SQLException{
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		boolean successflag=false;
		try {
			conn = DBConnection.getConnection();
			String sql = "select * from user where name='"+this.username
					+"' and password='"+this.password+"'";
			st = conn.createStatement();
			rs=st.executeQuery(sql);
			if(rs.next()==true) successflag=true;
		} finally {
			DBConnection.close(rs, st, conn);
		}
		return successflag;
	}
	/**修改用户个人信息*/
	public void updateUser()throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "update user set name=?,sex=?,age=?,birthday=? "
					   + "where id="+this.userid;
			ps = conn.prepareStatement(sql);
			ps.setString(1, this.username);
			ps.setString(2, this.sex);
			ps.setInt(3, Integer.valueOf(this.age));
			ps.setTimestamp(4, Date_String.toTimestamp(this.birthday));
			
			ps.executeUpdate();
		} finally {
			DBConnection.close(rs, ps, conn);
		}
	}
	/**删除用户个人信息*/
	public void delUserById()throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "delete from user where id="+this.userid;
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
		} finally {
			DBConnection.close(rs, ps, conn);
		}
	}
	/**增加用户信息*/
	public void addUser()throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "insert into user(id,name,password,sex,age,birthday)"
					   + " values(?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.valueOf(this.userid));
			ps.setString(2, this.getUsername());
			ps.setString(3, this.getPassword());
			ps.setString(4, this.getSex());
			if(this.age==null){
				ps.setNull(5, Types.INTEGER);
			}else{
				ps.setInt(5, Integer.valueOf(this.getAge()));
			}
			ps.setTimestamp(6, Date_String.toTimestamp(this.getBirthday()));
			ps.executeUpdate();
		} catch(Exception e){
			throw e;
		}finally {
			DBConnection.close(rs, ps, conn);
		}
	}
	/**校验输入的数据是否符合要求 add update 使用*/
	public Map<String, String> checkUser()throws Exception{
		Map<String, String> errors = new HashMap<String, String>();
		if(userid==null||userid.equals("")) {
			errors.put("userid", "用户编号不能为空！");
		}else{
			UserBpo userbpo=new UserBpo();
			UserBean user=userbpo.getUserById(userid);
			if(user!=null) errors.put("userid", "用户编号已存在！");
		}
		
		if(username==null||username.equals("")) errors.put("username", "用户名不能为空！");
		if(password==null||password.equals("")) errors.put("password", "密码不能为空！");
		if(confirmpassword==null||confirmpassword.equals("")) 
			errors.put("confirmpassword", "确认密码不能为空！");
		if(password!=null && confirmpassword!=null && !password.equals(confirmpassword)) 
			errors.put("confirmpassword", "确认密码与密码不一致！");
		
		return errors;
	}
}
