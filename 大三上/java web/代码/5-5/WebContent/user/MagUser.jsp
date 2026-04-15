<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8" errorPage="../error.jsp"  %>
<%@ page import="java.util.List" %>
<%@ page import="bean.UserBean" %>

<%@ include file="/LoginCheck.jsp" %>
<%
	String error=(String) request.getAttribute("error");
	if(error==null) error="";
	
	String username0=(String) request.getParameter("username");
	if(username0==null) username0="";
	
	String sex=(String) request.getParameter("sex");
	if(sex==null) sex="";
	
	List<UserBean> users=(List<UserBean>) request.getAttribute("users");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>用户管理</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css"/>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common.css" />
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
			<form action="<%=request.getContextPath()%>/GetUsersServlet" method="post">
				用户名：<input type="text" name="username" value="<%=username0%>"/>&nbsp;&nbsp;
				性别：<input type="text" name="sex" value="<%=sex%>"/>
				<input type="submit" value="查询"/>
			</form>
		
			<table class="mtable2">
				<caption>用户列表</caption>
				<tr><th>用户id</th><th>用户名</th><th>密码</th><th>性别</th><th>年龄</th><th>出生日期</th><th>操作</th></tr>
				<%
				if(users!=null){
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
						    <td><a href="<%=request.getContextPath()%>/user/UpdateUser.jsp?userid=<%=tmp.getUserid()%>">修改</a>
						   	 &nbsp;&nbsp;&nbsp;&nbsp;
						    	<a href="<%=request.getContextPath()%>/DeleteUserServlet?userid=<%=tmp.getUserid()%>"
						    	onclick="if(!confirm('确定要删除吗？')) return false;">删除</a></td>
						</tr>
					<%}
				}
				%>
			</table>
			<%=error %>
		</div>
		<!-- footer -->
		<div class="footer">
			<jsp:include page="/footer.jsp" flush="true"/>
		</div>
	  </div>
		
	</body>
</html>