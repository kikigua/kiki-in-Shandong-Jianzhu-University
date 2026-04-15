package servlet;

import java.io.IOException;
import java.util.HashMap;
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
		request.setCharacterEncoding("utf-8");
		UserBean user=this.getUserFromRequest(request);
		Map<String,String> errors=this.valid(user);
		if(errors.size()==0){
			try {
				user.updateUser();
			} catch (Exception e) {
				errors.put("othererr", "更新数据失败："+e.getMessage());
			}
		}
		if(errors.size()!=0){
			request.setAttribute("errors", errors);
			request.setAttribute("updateuser", user);
			request.getRequestDispatcher("/user/UpdateUser.jsp").forward(request, response);
		}else{
			response.sendRedirect(request.getContextPath()+"/user/MagUser.jsp");
		}
	}

	private Map<String,String> valid(UserBean user){
		Map<String,String> errors=new HashMap<String,String>();
		if(user.getUsername().equals("")) errors.put("username", "用户名不能为空！");
		return errors;
	}
	private UserBean getUserFromRequest(HttpServletRequest request){
		String userid=request.getParameter("userid");
		String username=request.getParameter("username");
		String sex=request.getParameter("sex");
		String age=request.getParameter("age");
		String birthday=request.getParameter("birthday");
		
		UserBean user=new UserBean();
		user.setUserid(userid);
		user.setUsername(username);
		user.setSex(sex);
		user.setAge(age);
		user.setBirthday(birthday);
		
		return user;
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
