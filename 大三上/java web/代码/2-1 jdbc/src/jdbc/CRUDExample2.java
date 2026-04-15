package jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Calendar;

import org.junit.Test;

import util.DBConnection;
/**
 * PreparedStatement
 * */
public class CRUDExample2 {
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
		}catch(SQLException e){
			e.printStackTrace();
		}
		finally{
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
			
			String sql1 = "insert into user(id,name,password,sex,age,birthday) values(31,'李晨','123','男',21,'1990-12-31')";
		    String sql2="insert into student(stuid,name,sex,age,birthday) values(31,'李晨','男',21,'1990-12-31')";
			
		    ps1 = conn.prepareStatement(sql1);
			ps1.executeUpdate();
			
			ps2 = conn.prepareStatement(sql2);
			ps2.executeUpdate();
		    
			conn.commit();
		}catch(SQLException e){
			//操作出现异常，撤销事务  
			e.printStackTrace();
			conn.rollback();
		}finally{
			conn.setAutoCommit(true);
			DBConnection.close(null, ps1, null);
			DBConnection.close(rs, ps2, conn);
		}
    }
	/**调用存储过程*/
	@Test
	public void callProctest() throws Exception{
		Connection conn = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		try{
			conn = DBConnection.getConnection();
			//1.定义调用存储过程的 SQL 语句
			String sql = "{call getUserNameAndAgeById(?,?,?)}";
			//2.获取 CallableStatement 对象
			cs=conn.prepareCall(sql);
		    //3. 设置参数（可选）
			cs.setInt(1, 1);
			cs.registerOutParameter(2, Types.VARCHAR);
			cs.registerOutParameter(3, Types.INTEGER);
			//4. 执行存储过程
			cs.execute();
			//5.获得输出参数值（可选）
			String userName = cs.getString(2);
			int userAge = cs.getInt("userAge");
			System.out.println(userName + "-" + userAge);
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			DBConnection.close(rs, cs, conn);
		}
	}
}
