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
		<title>用户管理</title>
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
			<jsp:include page="/user/menu.jsp" flush="true"/>
		</div>
		<!-- content -->
		<div class="content">
			<% 
			  List<User> users=UserBpo.getUsers();
			%>
			<table class="mtable2">
				<caption>用户列表</caption>
				<tr><th>用户id</th><th>用户名</th><th>性别</th><th>年龄</th><th>出生日期</th><th>操作</th></tr>
				<%
					for(int i=0;i<users.size();i++){
						User user=users.get(i);%>
						<tr>
							<td><%=user.getUserid()%></td>
						    <td><%=user.getUsername()%></td>
						    <td><%=user.getSex()%></td>
						    <td><%=user.getAge()%></td>
						    <td><%=user.getBirthday()%></td>
						    <td><a href="<%=request.getContextPath() %>/user/UpdateUser.jsp?userid=<%=user.getUserid()%>">修改</a>
						   	 &nbsp;&nbsp;&nbsp;&nbsp;
						    	<a href="<%=request.getContextPath() %>/user/DelUser_do.jsp?userid=<%=user.getUserid()%>" onclick="return confirm('确定要删除吗？');">删除</a></td>
						</tr>
					<%}
				%>
			</table>
		</div>
		<!-- footer -->
		<div class="footer">
			<jsp:include page="/footer.jsp" flush="true"/>
		</div>
	  </div>
		
	</body>
</html>