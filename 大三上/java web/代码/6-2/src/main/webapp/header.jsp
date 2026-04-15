<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8" %>
<%@ page import="java.util.*" %> 
<%
	String path=request.getContextPath();//部署的应用程序名"/www"
%>
<div align="center">XXXXXXXXX系统</div>
<div align="left">
<jsp:useBean id="user" class="bean.UserBean" scope="session"/>
当前用户：<jsp:getProperty name="user" property="username"/>&nbsp;&nbsp;&nbsp;&nbsp;
当前时间：<%=(new java.util.Date()).toString() %>&nbsp;&nbsp;&nbsp;&nbsp;
当前在线人数：${onlineNumber}
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="<%=path%>/LoginSuccess.jsp">返回首页</a>&nbsp;&nbsp;
	<a href="<%=path%>/exit.jsp">退出</a>
</div>



