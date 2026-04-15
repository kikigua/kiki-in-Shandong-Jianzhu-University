<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Response test</title>
</head>
<body>
	<%-- <%! int i=0; %>
	<%
	//一秒钟刷新一次，每次使得i加1
	response.setHeader("refresh","1"); 
	%>
	<h1><%=i++ %></h1> --%>
	
	<%-- <%//2秒钟后跳转到useOut.jsp去
	  response.setHeader("refresh","2;URL=../index.jsp");
	%> --%>
	
	<% //request.getParameter("name");
	response.sendRedirect("../index.jsp"); %>
</body>
</html>