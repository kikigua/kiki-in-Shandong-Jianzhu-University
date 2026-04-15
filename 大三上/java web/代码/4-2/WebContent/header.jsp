<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8" %>
<%@ page import="java.util.*" %> 
<%
	String username=(String)(session.getAttribute("username")); 
	int count=0;	
	HashSet sessions=(HashSet)application.getAttribute("sessions");
	if(sessions!=null){
		count=sessions.size();
	}
	String contextpath=request.getContextPath();
%>
<div align="center">XXXXXXXXX系统</div>
<div align="left">当前用户：<%=username%>&nbsp;&nbsp;&nbsp;&nbsp;在线人数：<%=count%>&nbsp;&nbsp;&nbsp;&nbsp;当前时间：<%=(new java.util.Date()).toString() %>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="<%=contextpath%>/LoginSuccess.jsp">返回首页</a>&nbsp;&nbsp;<a href="<%=contextpath%>/exit.jsp">退出</a>
</div>



