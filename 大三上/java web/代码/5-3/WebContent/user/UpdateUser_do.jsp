<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8" errorPage="../error.jsp"  %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>

<%@ include file="/LoginCheck.jsp" %>

<jsp:useBean id="updateuser" class="bean.UserBean" scope="request"/>
<jsp:setProperty name="updateuser" property="*"/>
<% 
	Map<String,String> errors=new HashMap<String,String>();
	if(updateuser.getUsername().equals("")) errors.put("username", "用户名不能为空！");
	
	if(errors.size()==0){
		try {
			updateuser.updateUser();
		} catch (Exception e) {
			errors.put("othererr", "更新数据失败："+e.getMessage());
		}
	}
	
	if(errors.size()!=0){
		request.setAttribute("errors", errors);
		request.getRequestDispatcher("/user/UpdateUser.jsp").forward(request, response);
	}
%>
<jsp:forward page="/user/MagUser.jsp"/>