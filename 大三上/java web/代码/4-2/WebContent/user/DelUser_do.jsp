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
	UserBpo.delUserById(userid);
	response.sendRedirect("MagUser.jsp");
%>
