<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8" errorPage="../error.jsp"  %>
<%@ page import="java.util.List" %>
<%@ page import="bean.UserBean" %>

<%@ include file="/LoginCheck.jsp" %>

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
			<jsp:include page="menu.jsp"/>
		</div>
		<!-- content -->
		<div class="content">
		<jsp:useBean id="userbpo" class="bean.UserBean" scope="page"/>
			<%
				List<UserBean> users=userbpo.getUsers();
			%>
			<table class="mtable2">
				<caption>用户列表</caption>
				<tr><th>用户id</th><th>用户名</th><th>密码</th><th>性别</th><th>年龄</th><th>出生日期</th><th>操作</th></tr>
				<%
					for(int i=0;i<users.size();i++){
						UserBean tmp=users.get(i);
				%>
						<tr>
							<td><%=tmp.getUserid()%></td>
						    <td><%=tmp.getUsername()%></td>
						    <td><%=tmp.getPassword()%></td>
						    <td><%=tmp.getSex()%></td>
						    <td><%=tmp.getAge()%></td>
						    <td><%=tmp.getBirthday()%></td>
						    <td><a href="UpdateUser.jsp?userid=<%=tmp.getUserid()%>">修改</a>
						   	 &nbsp;&nbsp;&nbsp;&nbsp;
						    	<a href="DelUser_do.jsp?userid=<%=tmp.getUserid()%>">删除</a></td>
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