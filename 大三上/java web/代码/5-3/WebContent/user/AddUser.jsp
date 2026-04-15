<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8" errorPage="../error.jsp"  %>
<%@ page import="java.util.Map" %>

<%@ include file="/LoginCheck.jsp" %>
<% Map<String,String> errors=(Map<String,String>)request.getAttribute("errors"); 
	String useriderr="",usernameerr="", passworderr="", confirmerr="";
	if(errors!=null){
		useriderr=errors.get("userid");
		if(useriderr==null)useriderr="";
		usernameerr=errors.get("username");
		if(usernameerr==null)usernameerr="";
		passworderr=errors.get("password");
		if(passworderr==null)passworderr="";
		confirmerr=errors.get("confirmpassword");
		if(confirmerr==null)confirmerr="";
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>增加用户信息</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/common.css" />
	</head>
	<body>
	  <div id="wrapper">
	 	<!-- header -->
	   <div class="header">
			<jsp:include page="/header.jsp" flush="true"/>
	    </div>
		<!-- menu -->
		<div class="menu">
			<jsp:include page="menu.jsp"/>
		</div>
		<!-- content -->
		<div class="content">
		    <jsp:useBean id="newuser" class="bean.UserBean" scope="request"/>
			<form action="AddUser_do.jsp" method="post">
				<table class="mtable3" align=center>
					<caption>增加用户基本信息</caption>
					<tr><td>用户id：</td>
						<td><input type="text" name="userid" value="<%=newuser.getUserid() %>"/><%=useriderr%></td></tr>
					<tr><td>用户名:</td>
						<td><input type="text" name="username" value="<%=newuser.getUsername() %>"/><%=usernameerr%></td></tr>
					<tr>
						<td>密码:</td><td><input type="password" name="password"/><%=passworderr%></td></tr>
					<tr>
						<td>确认密码:</td><td><input type="password" name="confirmpassword"/><%=confirmerr%></td></tr>
					<tr><td>性别</td><td><input type="text" name="sex" value="<%=newuser.getSex()%>"/></td></tr>
					<tr><td>年龄</td><td><input type="text" name="age" value="<%=newuser.getAge()%>"/></td></tr>
					<tr><td>出生日期</td><td><input type="text" name="birthday" value="<%=newuser.getBirthday()%>"/></td></tr>
					<tr><td><input type="reset" value="重置"/><input type="submit" value="提交"/></td></tr>
				</table>
			</form>
		</div>
		<!-- footer -->
		<div class="footer">
			<jsp:include page="/footer.jsp" flush="true"/>
		</div>
	  </div>
		
	</body>
</html>