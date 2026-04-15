<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8" errorPage="../error.jsp"  %>
<%@ page import="java.util.List" %>

<%@ include file="/LoginCheck.jsp" %>

<jsp:useBean id="userbean" class="bean.UserBean"/>
<jsp:setProperty name="userbean" property="*"/>

<% 
	userbean.delUserById();
%>
<jsp:forward page="/user/MagUser.jsp"/>