<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8" errorPage="/error.jsp"  %>
<%@ page import="java.util.List" %>
<%@ page import="jdbc.UserBpo" %>
<%@ page import="pojo.User" %>
<%@ page import="util.Date_String" %>
<% 
	request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");
	
	String username=(String)(session.getAttribute("username")); 
	if(username==null || username.equals("")){
		response.sendRedirect("../index.jsp");
	}
	
	String userid=request.getParameter("userid");
	String username0=request.getParameter("username");
	String password=request.getParameter("password");
	String confirmpassword=request.getParameter("confirmpassword");
	String sex=request.getParameter("sex");
	String age=request.getParameter("age");
	String birthday=request.getParameter("birthday");
	
	if(username0.equals("")) throw new Exception("用户名不允许为空，请重新输入！");
	if(!password.equals(confirmpassword)) throw new Exception("输入的密码和确认密码不一致，请重新输入！");
	
	User user=new User(Integer.valueOf(userid),username0,password,sex,Integer.valueOf(age),birthday);
	UserBpo.addUser(user);
	
	response.sendRedirect("MagUser.jsp");
%>
