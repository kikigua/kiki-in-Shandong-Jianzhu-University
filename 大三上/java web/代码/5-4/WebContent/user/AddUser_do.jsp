<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8" errorPage="../error.jsp"  %>
<%@ page import="java.util.Map" %>

<%@ include file="/LoginCheck.jsp" %>

<jsp:useBean id="newuser" class="bean.UserBean" scope="request"/>
<jsp:setProperty name="newuser" property="*"/>
<% 
    Map<String, String> errors=newuser.checkUser();
    if(errors.size()==0){
    	newuser.addUser();
    }else{
    	request.setAttribute("errors", errors);
    	request.getRequestDispatcher("/user/AddUser.jsp").forward(request, response);
    }
%>
<jsp:forward page="/user/MagUser.jsp"/>
