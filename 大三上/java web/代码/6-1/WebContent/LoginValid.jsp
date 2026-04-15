<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="/error.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<body>
    <jsp:useBean id="user" class="bean.UserBean"/>
	<jsp:setProperty name="user" property="username" param="name"/>
	<jsp:setProperty name="user" property="password"/>
	<c:set var="successflag" value="${user.validlogin()}"/>
	<c:choose>
		<c:when test="${successflag}">
			<c:set var="user" value="${user}" scope="session"/>
			<c:redirect url="LoginSuccess.jsp"/>
		</c:when>
		<c:otherwise>
			<jsp:forward page="index.jsp">
				<jsp:param value="error" name="用户名或密码不正确，登陆失败！"/>
			</jsp:forward>
		</c:otherwise>
	</c:choose>
</body>
