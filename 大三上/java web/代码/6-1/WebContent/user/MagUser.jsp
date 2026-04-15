<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8" errorPage="/error.jsp"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/LoginCheck.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>用户管理</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css" />
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
			<form action="${pageContext.request.contextPath}/GetUsersServlet" method="post">
				用户名：<input type="text" name="username" value="${param.username}"/>&nbsp;&nbsp;
				性别：<input type="text" name="sex" value="${param.sex}"/>
				<input type="submit" value="查询"/>
			</form>
		
			<table class="mtable2">
				<caption>用户列表</caption>
				<tr><th>序号</th><th>用户id</th><th>用户名</th><th>密码</th><th>性别</th><th>年龄</th><th>出生日期</th><th>操作</th></tr>
				<c:forEach var="tmp" items="${users}" varStatus="s">
					<tr>
						<td>${s.index+1}</td>
						<td>${tmp.userid}</td>
					    <td>${tmp.username}</td>
					    <td>${tmp.password}</td>
					    <td>${tmp.sex}</td>
					    <td>${tmp.age}</td>
					    <td>${tmp.birthday}</td>
					    <td><a href="${pageContext.request.contextPath}/user/UpdateUser.jsp?userid=${tmp.userid}">修改</a>
					   	 &nbsp;&nbsp;&nbsp;&nbsp;
					    	<a href="${pageContext.request.contextPath}/DeleteUserServlet?userid=${tmp.userid}"
					    	onclick="if(!confirm('确定要删除吗？')) return false;">删除</a></td>
					</tr>
				</c:forEach>
			</table>
			${error}
		</div>
		<!-- footer -->
		<div class="footer">
			<jsp:include page="/footer.jsp" flush="true"/>
		</div>
	  </div>
		
	</body>
</html>