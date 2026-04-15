<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8" %>
<%@ page import="java.util.*" %> 
<% String username=request.getParameter("username");
%>
<div align="center">XXXXXXXXX系统</div>
<br/>
当前用户：<%=username %>&nbsp;&nbsp;
当前时间：<%=(new java.util.Date()).toLocaleString() %>

