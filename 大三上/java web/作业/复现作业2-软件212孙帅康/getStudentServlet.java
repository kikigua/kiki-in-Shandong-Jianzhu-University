package servletExm1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.DBConnection;

@WebServlet("/getStudents")
public class getStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public getStudentServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        String resultstr="";
		//访问数据库
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try{
			conn = DBConnection.getConnection();
			String sql = "select sno,name, password,sex,age,birthday from student1";
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				int sno=rs.getInt("sno");
				String name=rs.getString("name");
				String password=rs.getString("password");
				String sex=rs.getString("sex");
				int age=rs.getInt("age");
				resultstr=resultstr+"<tr><td>"+sno+"</td><td>"+name+"</td><td>"+password+"</td><td>"+sex+"</td><td>"+age+"</td></tr>";
			}
			out.println("<html>");
	        out.println("<head><title>用户列表</title></head>");
	        out.println("<body><table><th>学号</th><th>用户名</th><th>密码</th><th>性别</th><th>年龄</th>"+resultstr+"</table></body>");
	        out.println("</html>");
		}catch(Exception e){
			out.println("<html>"+
			                 "<head><title>错误</title></head>"+
						     "<body>出错了！["+e.getMessage()+"]</body>"+
		                 "</html>");
		}
		finally{
			DBConnection.close(rs, st, conn);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
