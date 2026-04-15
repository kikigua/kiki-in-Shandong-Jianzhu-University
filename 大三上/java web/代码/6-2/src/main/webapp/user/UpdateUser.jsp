<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bpo.UserBpo" %>
<%@ page import="bean.UserBean" %>
<%@ page import="java.util.Map" %>
<%
	
	String userid=request.getParameter("userid");

	UserBean updateuser=(UserBean) request.getAttribute("updateuser");
	if(updateuser==null){
		UserBpo userbpo=new UserBpo();
		updateuser=userbpo.getUserById(userid);
	}	
	
	Map<String,String> errors=(Map<String,String>) request.getAttribute("errors");
	String othererr="",usernameerr="";
	if(errors!=null){
		if(errors.get("othererr")!=null) othererr=errors.get("othererr");
		if(errors.get("username")!=null) usernameerr=errors.get("username");
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改用户信息</title>
</head>
<body>
	<form action="<%=request.getContextPath()%>/UpdateUserServlet?userid=<%=updateuser.getUserid() %>" method="post">
		<table>
			<tr><td>用户id</td><td><%=updateuser.getUserid() %></td></tr>
			<tr><td>用户名</td><td><input type="text" name="username" value="<%=updateuser.getUsername() %>"/><%=usernameerr %></td></tr>
			<tr><td>年龄</td><td><input type="text" name="age" value="<%=updateuser.getAge() %>"/></td></tr>
			<tr><td colspan=2><input type="submit" value="保存"/></td></tr>
		</table>
		<%=othererr %>
	</form>
</body>
</html>