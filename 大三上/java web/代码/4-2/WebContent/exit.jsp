<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8" errorPage="error.jsp" %>
<%
	session.invalidate();//触发sessionDestroyed事件
    response.sendRedirect("index.jsp");
%>
