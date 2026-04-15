package jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class CRUDExample {
	//add
	@Test
	public void add() throws ClassNotFoundException, SQLException {
		//定义创建数据库连接的所需参数
	    String url = "jdbc:mysql://localhost:3306/testDB?"
				+ "characterEncoding=utf8&useSSL=false&serverTimezone=UTC"
				+ "&rewriteBatchedStatements=true";
	    String dbUser = "root";
	    String dbPassword = "root";
	    String driverName = "com.mysql.cj.jdbc.Driver";
	    //1、加载驱动程序
	    Class.forName(driverName);
	    //2、创建连接对象
	    Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
	    //3、创建Statement对象执行SQL语句
	    String sql = "insert into user(id,name,password,sex,age,birthday) values(2,'zhangsan','123','男',21,'1990-12-31')";
	    Statement stmt = conn.createStatement();
	    int lines = stmt.executeUpdate(sql);
	    //4、遍历结果集(此处不需)
		System.out.println("lines=" + lines);
	    //5、关闭 资源 对象 
	    stmt.close();
	    conn.close();
    }
	//update
	@Test
	public void update() throws ClassNotFoundException, SQLException {
	    //定义创建数据库连接的所需参数
		String url = "jdbc:mysql://localhost:3306/testDB?"
				+ "characterEncoding=utf8&useSSL=false&serverTimezone=UTC"
				+ "&rewriteBatchedStatements=true";
	    String dbUser = "root";
	    String dbPassword = "root";
	    String driverName = "com.mysql.cj.jdbc.Driver";
	    //1、加载驱动程序
	    Class.forName(driverName);
	    //2、创建连接对象
	    Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
	    //3、创建Statement对象执行SQL语句
	    String sql = "update userinfo set password='222222' where id=2";
	    Statement stmt = conn.createStatement();
	    int lines = stmt.executeUpdate(sql);
	    //4、遍历结果集(此处不需)
		System.out.println("update lines=" + lines);
	    //5、关闭 资源 对象 
	    stmt.close();
	    conn.close();
    }
	//delete
	@Test
	public void delete() throws ClassNotFoundException, SQLException {
	    //定义创建数据库连接的所需参数
		String url = "jdbc:mysql://localhost:3306/testDB?"
				+ "characterEncoding=utf8&useSSL=false&serverTimezone=UTC"
				+ "&rewriteBatchedStatements=true";
	    String dbUser = "root";
	    String dbPassword = "root";
	    String driverName = "com.mysql.cj.jdbc.Driver";
	    //1、加载驱动程序
	    Class.forName(driverName);
	    //2、创建连接对象
	    Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
	    //3、创建Statement对象执行SQL语句
	    String sql = "delete from userinfo where id=2";
	    Statement stmt = conn.createStatement();
	    int lines = stmt.executeUpdate(sql);
	    //4、遍历结果集(此处不需)
		System.out.println("delete lines=" + lines);
	    //5、关闭 资源 对象 
	    stmt.close();
	    conn.close();
    }
	//get
	@Test
	public void get() throws ClassNotFoundException, SQLException {
	    //定义创建数据库连接的所需参数
		String url = "jdbc:mysql://localhost:3306/testDB?"
				+ "characterEncoding=utf8&useSSL=false&serverTimezone=UTC"
				+ "&rewriteBatchedStatements=true";
	    String dbUser = "root";
	    String dbPassword = "root";
	    String driverName = "com.mysql.cj.jdbc.Driver";
	    //1、加载驱动程序
	    Class.forName(driverName);
	    //2、创建连接对象
	    Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
	    //3、创建Statement对象执行SQL语句
	    String sql = "select id,name, password,sex,age,birthday from userinfo";
	    Statement stmt = conn.createStatement();
	    ResultSet rs = stmt.executeQuery(sql);
	    //4、遍历结果集
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
	    //5、关闭 资源 对象 
	    stmt.close();
	    conn.close();
    }
}
