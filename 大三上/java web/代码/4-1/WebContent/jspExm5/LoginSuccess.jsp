<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8" errorPage="error.jsp" %>
<% 
/*
String username=(String)session.getAttribute("username");
if(username==null||username.equals("")){
	response.sendRedirect("index.jsp");
}
*/
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>登陆成功</title>
		<link rel="stylesheet" type="text/css" href="css/style.css" />
	</head>
	<body>
		<% request.setCharacterEncoding("UTF-8") ;//解决jsp:include参数乱码问题 %>
		<div class="header">
			<jsp:include page="headerWithUser.jsp" flush="true">
				<jsp:param value="李四" name="username"/>
			</jsp:include>
		</div>
		<ul>
			<li><a href="#">查询用户信息</a></li>
			<li><a href="#">增加用户信息</a></li>
			<li><a href="#">修改用户信息</a></li>
			<li><a href="#">删除用户信息</a></li>
		</ul>
	</body>
</html>