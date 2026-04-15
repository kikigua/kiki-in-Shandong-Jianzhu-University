<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="../error.jsp"%>
<%@ page import="bean.UserBean" %>

<% 
    String userid=request.getParameter("userid");
	UserBean user=new UserBean();
	user.setUserid(userid);
	user.delUserById();
	response.sendRedirect("MagUser.jsp");
%>