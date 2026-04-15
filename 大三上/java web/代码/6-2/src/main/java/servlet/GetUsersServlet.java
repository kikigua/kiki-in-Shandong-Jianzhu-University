package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UserBean;
import bpo.UserBpo;

/**
 * Servlet implementation class GetUsersServlet
 */
@WebServlet("/GetUsersServlet")
public class GetUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUsersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String username=request.getParameter("username");
		String sex=request.getParameter("sex");
		String error="";
		List<UserBean> users=null;
		try{
			UserBpo userbpo=new UserBpo();
			users=userbpo.getUsers(username, sex);
		}catch(Exception e){
			error="查询用户出错："+e.getMessage();
		}
		
		request.setAttribute("error", error);
		request.setAttribute("users", users);
		request.getRequestDispatcher("/user/MagUser.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
