<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8" errorPage="/error.jsp"  %>
<%@ page import="java.util.List" %>
<%@ page import="util.Date_String" %>
<%@ include file="/LoginCheck.jsp" %>
<jsp:useBean id="userbpo" class="bean.UserBean"/>
<% 
	String userid=request.getParameter("userid");
	userbpo.delUserById(userid);
%>
<jsp:forward page="/user/MagUser.jsp"/>
