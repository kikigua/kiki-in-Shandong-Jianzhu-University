<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8" errorPage="/error.jsp"  %>
<%@ page import="java.util.List" %>
<%@ page import="jdbc.UserBpo" %>
<%@ page import="pojo.User" %>
<% 
request.setCharacterEncoding("utf-8");
response.setCharacterEncoding("utf-8");

String username=(String)(session.getAttribute("username")); 
if(username==null || username.equals("")){
	response.sendRedirect(request.getContextPath()+"/index.jsp");
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>增加用户信息</title>
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
			<form action="<%=request.getContextPath() %>/user/AddUser_do.jsp" method="post">
				<table class="mtable3" align=center>
					<caption>增加用户基本信息</caption>
					<tr><td>用户id：</td><td><input type="text" name="userid"/></td></tr>
					<tr><td>用户名:</td><td><input type="text" name="username"/></td></tr>
					<tr><td>密码:</td><td><input type="password" name="password"/></td></tr>
					<tr><td>确认密码:</td><td><input type="password" name="confirmpassword"/></td></tr>
					<tr><td>性别</td><td><input type="text" name="sex"/></td></tr>
					<tr><td>年龄</td><td><input type="text" name="age"/></td></tr>
					<tr><td>出生日期</td><td><input type="text" name="birthday"/></td></tr>
					<tr><td><input type="reset" value="重置"/><input type="submit" value="提交"/></td></tr>
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