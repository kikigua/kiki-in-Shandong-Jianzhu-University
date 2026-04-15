<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8" errorPage="/error.jsp"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="/LoginCheck.jsp" %>
<c:if test="${empty updateuser}">
	<c:set var="userid" value="${param.userid}"/>
	<jsp:useBean id="userbpo" class="bpo.UserBpo"/>
	<c:set var="updateuser" value="${userbpo.getUserById(userid)}"/>
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>修改用户信息</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
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
			<form action="${pageContext.request.contextPath}/UpdateUserServlet" method="post">
				<table class="mtable3" align=center>
					<caption>修改用户基本信息</caption>
					<tr><td>用户id：</td>
						<td><input type="hidden" name="userid" 
									value="${updateuser.userid}"/>${updateuser.userid}</td></tr>
					<tr><td>用户名:</td>
						<td><input type="text" name="username" 
									value="${updateuser.username}"/>${errors.username}</td></tr>
					<tr><td>性别</td>
						<td><input type="text" name="sex" value="${updateuser.sex}"/></td></tr>
					<tr><td>年龄</td>
						<td><input type="text" name="age" value="${updateuser.age}"/></td></tr>
					<tr><td>出生日期</td>
						<td><input type="text" name="birthday" value="${updateuser.birthday}"/></td></tr>
					<tr><td><input type="submit" value="保存"/></td></tr>
				</table>
				${errors.othererr}
			</form>
		</div>
		<!-- footer -->
		<div class="footer">
			<jsp:include page="/footer.jsp" flush="true"/>
		</div>
	  </div>
		
	</body>
</html>