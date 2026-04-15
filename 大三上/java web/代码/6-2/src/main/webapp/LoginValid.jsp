<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8" errorPage="error.jsp" %>
<%@page import="bean.UserBean"%>
<%
	String name=request.getParameter("name");
    String password=request.getParameter("password");
    UserBean user=new UserBean();
    user.setUsername(name);
    user.setPassword(password);
    boolean successflag=user.validlogin();
    if(successflag==true){
    	session.setAttribute("user", user);
    	response.sendRedirect("LoginSuccess.jsp");
    }else{
    	//throw new Exception("用户名或密码不正确，登陆失败！");
    	request.setAttribute("error", "用户名或密码不正确，登陆失败！");
    	request.getRequestDispatcher("index.jsp").forward(request,response);
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>校验登陆是否成功</title>
	</head>
	<body>
		
	</body>
</html>