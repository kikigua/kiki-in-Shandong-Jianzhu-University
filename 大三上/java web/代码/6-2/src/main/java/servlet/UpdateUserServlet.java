package servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UserBean;

/**
 * Servlet implementation class UpdateUserServlet
 */
@WebServlet("/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String userid=request.getParameter("userid");
		String username0=request.getParameter("username");
		String age=request.getParameter("age");
		
		UserBean updateuser=new UserBean();
		updateuser.setUserid(userid);
		updateuser.setUsername(username0);
		updateuser.setAge(age);
		
		Map<String,String> errors=updateuser.checkUpdateUser();
		if(errors.size()==0) {
			try {
				updateuser.updateUser();
				response.sendRedirect("user/MagUser.jsp");
			} catch (Exception e) {
				errors.put("othererr", e.getMessage());
			}
		}
		if(errors.size()!=0) {
			request.setAttribute("errors", errors);
			request.setAttribute("updateuser", updateuser);
			request.getRequestDispatcher("user/UpdateUser.jsp").forward(request,response);		
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

} 
