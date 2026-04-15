<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8" errorPage="/error.jsp"  %>
<%@ page import="java.util.List" %>
<%@ page import="jdbc.UserBpo" %>
<%@ page import="pojo.User" %>
<% 
request.setCharacterEncoding("utf-8");
response.setCharacterEncoding("utf-8");

String username=(String)(session.getAttribute("username")); 
if(username==null || username.equals("")){
	response.sendRedirect("../index.jsp");
}

String userid=request.getParameter("userid");
if(userid==null || userid.equals("")){
	response.sendRedirect("MagUser.jsp");
}

User user=UserBpo.getUserById(userid);
if(user==null) response.sendRedirect("MagUser.jsp");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>修改用户信息</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/common.css" />
	</head>
	<body>
	  <div id="wrapper">
	 	<!-- header -->
	   <div class="header">
			<jsp:include page="/header.jsp" flush="true"/>
	    </div>
		<!-- menu -->
		<div class="menu">
			<jsp:include page="/user/menu.jsp"/>
		</div>
		<!-- content -->
		<div class="content">
			<form action="UpdateUser_do.jsp?userid=<%=userid%>" method="post">
				<table class="mtable3" align=center>
					<caption>修改用户基本信息</caption>
					<tr><td>用户id：</td>
						<td><%=userid%></td></tr>
					<tr><td>用户名:</td>
						<td><input type="text" name="username" value="<%=user.getUsername()%>"/></td></tr>
					<tr><td>性别</td>
						<td><input type="text" name="sex" value="<%=user.getSex()%>"/></td></tr>
					<tr><td>年龄</td>
						<td><input type="text" name="age" value="<%=user.getAge()%>"/></td></tr>
					<tr><td>出生日期</td>
						<td><input type="text" name="birthday" value="<%=user.getBirthday()%>"/></td></tr>
					<tr><td><input type="submit" value="保存"/></td></tr>
				</table>
			</form>
		</div>
		<!-- footer -->
		<div class="footer">
			<jsp:include page="/footer.jsp" flush="true"/>
		</div>
	  </div>
		
	</body>
</html>