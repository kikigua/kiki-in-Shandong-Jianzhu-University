package servletExm2;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DBConnection;

@WebServlet("/RegisterStudentServlet")
public class RegisterStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public RegisterStudentServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接收请求参数
		//处理中文乱码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//得到request请求参数
		String usersno=request.getParameter("usersno");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		
        PrintWriter out=response.getWriter();
		//访问数据库
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try{
			conn = DBConnection.getConnection();
			String sql = "insert into student1(sno,name,password) values("+usersno+",'"+username+"','"+password+"')";
			st = conn.createStatement();
			st.executeUpdate(sql);
			
			response.sendRedirect("servletExm2/RegisterSuccess.html");//定位到html（相对路径）
			
		}catch(Exception e){
			out.println("<html>"+
			                 "<head><title>错误</title></head>"+
						     "<body>出错了！["+e.getMessage()+"]<a href='servletExm2/RegisterStudent.html'>重新注册</a></body>"+
		                 "</html>");
		}
		finally{
			DBConnection.close(rs, st, conn);
			out.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
