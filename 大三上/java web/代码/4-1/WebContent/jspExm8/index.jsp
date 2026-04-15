<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String username=(String)session.getAttribute("name");
	if(username==null){
		response.sendRedirect("login.html");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>登录成功</title>
	</head>
	<body>
		欢迎您！<%=session.getAttribute("name") %>，登录成功！
		<form action="logout_do.jsp" method="post">
			<input type="submit" value="注销"/>
		</form>
	</body>
</html>