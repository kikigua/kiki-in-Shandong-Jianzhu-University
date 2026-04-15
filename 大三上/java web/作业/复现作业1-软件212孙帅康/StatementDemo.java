package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import pojo.Student;
import util.DBConnection;

public class StatementDemo {
	    //插入用户
		public void insertStudent(Student student) throws Exception{
			Connection conn = null;
			Statement st = null;
			ResultSet rs = null;
			try{
				conn = DBConnection.getConnection();
				String sql = "insert into student(sno,sex) "
						+ "values("+student.getSno()+",'"+student.getSex()+"')";
				st = conn.createStatement();
			    st.executeUpdate(sql);
			}catch(Exception e){
				//System.out.println("增加用户失败:"+e.getMessage());
				
				throw new Exception("增加用户失败:"+e.getMessage());
			}finally{
				DBConnection.close(rs, st, conn);
			}
	    }
		@Test
		public void test() throws Exception{
			Student student=new Student(2,"男");
			this.insertStudent(student);
		}
		
		
		//更新用户
		public static void updateStudent() throws SQLException {
			Connection conn = null;
			Statement st = null;
			ResultSet rs = null;
			try{
				conn = DBConnection.getConnection();
				String sql = "update student set sex=‘女’ where sno=2";
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
		public void deleteStudent() throws SQLException {
			Connection conn = null;
			Statement st = null;
			ResultSet rs = null;
			try{
				conn = DBConnection.getConnection();
				String sql = "delete from student where sno=2";
			    st = conn.createStatement();
			    st.executeUpdate(sql);
			}catch(Exception e){
				System.out.println("删除用户失败："+e.getMessage());
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
					System.out.println("sno=" + sno + ";sex=" + sex);
				}
			}finally{
				DBConnection.close(rs, st, conn);
			}
	    }
}
