package jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import org.junit.Test;
import util.DBConnection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class preparedStatementDemo {
	   //插入
		@Test
		public void insertStudent() throws SQLException {
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			try{
				conn = DBConnection.getConnection();
				String sql = "insert into student(sno,sex) values(?,?)";
			    ps = conn.prepareStatement(sql);
			  //设置参数
			    ps.setInt(1, 3);
				ps.setString(2, "男");
				//执行SQL
			    ps.executeUpdate();
			}catch(SQLException e){
				e.printStackTrace();
			}
			finally{
				DBConnection.close(rs, ps, conn);
			}
	    }
		
		//update
		public static void updateStudent() throws SQLException {
			Connection conn = null;
			Statement st = null;
			ResultSet rs = null;
			try{
				conn = DBConnection.getConnection();
				String sql = "update student set sex='女' where sno=2";
			    st = conn.createStatement();
			    st.executeUpdate(sql);
			}finally{
				DBConnection.close(rs, st, conn);
			}
	    }
		//delete
		@Test
		public void deleteStudent() throws SQLException {
			Connection conn = null;
			Statement st = null;
			ResultSet rs = null;
			try{
				conn = DBConnection.getConnection();
				String sql = "delete from student where sno=2";
			    st = conn.createStatement();
			    st.executeUpdate(sql);
			}finally{
				DBConnection.close(rs, st, conn);
			}
	    }
		//查询
		@Test
		public void selectStudent() throws SQLException {
			Connection conn = null;
			Statement st = null;
			ResultSet rs = null;
			try{
				conn = DBConnection.getConnection();
				String sql = "select sno,sex from student";
				st = conn.createStatement();
				rs = st.executeQuery(sql);
				while(rs.next()){
					int sno=rs.getInt("sno");
					String sex=rs.getString("sex");
					System.out.println("sno=" + sno + ";sex=" + sex );
				}
			}finally{
				DBConnection.close(rs, st, conn);
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
				String sql = "{call getSexBySno(?)}";
				//2.获取 CallableStatement 对象
				cs=conn.prepareCall(sql);
			    //3. 设置参数（可选）
				cs.setInt(1, 2);
				//4. 执行存储过程
				cs.execute();
				//5.获得输出参数值（可选）
				String studentSex = cs.getString(2);
				System.out.println(studentSex + "-" );
			}catch(Exception e) {
				e.printStackTrace();
			}finally{
				DBConnection.close(rs, cs, conn);
			}
		}
		//insert 事务
		@Test
		public void insretwithtrans() throws SQLException {
			Connection conn = null;
			PreparedStatement ps1 = null;
			PreparedStatement ps2 = null;
			ResultSet rs = null;
			try{
				conn = DBConnection.getConnection();
				conn.setAutoCommit(false);
						
				String sql1 = "insert into student(sno,sex) values(31,'男')";
				String sql2="insert into student(sno,sex) values(32,'女')";
						
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
}
