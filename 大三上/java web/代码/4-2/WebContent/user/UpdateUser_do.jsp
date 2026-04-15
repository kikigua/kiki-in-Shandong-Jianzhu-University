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
	String sex=request.getParameter("sex");
	String age=request.getParameter("age");
	String birthday=request.getParameter("birthday");
	
	User user=new User(Integer.valueOf(userid),username0,"",sex,Integer.valueOf(age),birthday);
	UserBpo.updateUser(user);
	
	response.sendRedirect("MagUser.jsp");
%>
