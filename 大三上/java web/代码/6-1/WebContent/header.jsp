<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8" %>
<%@ page import="java.util.*" %> 
<div align="center">XXXXXXXXX系统</div>
<div align="left">
<jsp:useBean id="user" class="bean.UserBean" scope="session"/>
当前用户：${user.username}&nbsp;&nbsp;&nbsp;&nbsp;
当前时间：<%=(new java.util.Date()).toString() %>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="${pageContext.request.contextPath}/LoginSuccess.jsp">返回首页</a>&nbsp;&nbsp;
	<a href="${pageContext.request.contextPath}/exit.jsp">退出</a>
</div>



