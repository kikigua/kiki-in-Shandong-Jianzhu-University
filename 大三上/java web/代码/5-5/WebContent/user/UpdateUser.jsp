<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8" errorPage="../error.jsp"  %>
<%@ page import="java.util.Map"%>
<%@ page import="bean.UserBean"%>
<%@ page import="bpo.UserBpo"%>
<%@ include file="/LoginCheck.jsp" %>
<% 
	UserBean updateuser=(UserBean)request.getAttribute("updateuser");
	if(updateuser==null){
		String userid=(String)request.getParameter("userid");
		updateuser=(new UserBpo()).getUserById(userid);
	}

	Map<String,String> errors=(Map<String,String>)request.getAttribute("errors"); 
	String usernameerr="", othererr="";
	if(errors!=null){
		usernameerr=errors.get("username");
		if(usernameerr==null)usernameerr="";
		othererr=errors.get("othererr");
		if(othererr==null)othererr="";
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>修改用户信息</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common.css" />
	</head>
	<body>
	  <div id="wrapper">
	 	<!-- header -->
	   <div class="header">
			<jsp:include page="/header.jsp" flush="true"/>
	    </div>
		<!-- menu -->
		<div class="menu">
			<jsp:include page="/user/menu.jsp"/>
		</div>
		<!-- content -->
		<div class="content">
			<form action="<%=request.getContextPath()%>/UpdateUserServlet" method="post">
				<table class="mtable3" align=center>
					<caption>修改用户基本信息</caption>
					<tr><td>用户id：</td>
						<td><input type="hidden" name="userid" 
									value="<%=updateuser.getUserid()%>"/><%=updateuser.getUserid()%></td></tr>
					<tr><td>用户名:</td>
						<td><input type="text" name="username" 
									value="<%=updateuser.getUsername()%>"/><%=usernameerr %></td></tr>
					<tr><td>性别</td>
						<td><input type="text" name="sex" value="<%=updateuser.getSex()%>"/></td></tr>
					<tr><td>年龄</td>
						<td><input type="text" name="age" value="<%=updateuser.getAge()%>"/></td></tr>
					<tr><td>出生日期</td>
						<td><input type="text" name="birthday" value="<%=updateuser.getBirthday()%>"/></td></tr>
					<tr><td><input type="submit" value="保存"/></td></tr>
				</table>
				<%=othererr %>
			</form>
		</div>
		<!-- footer -->
		<div class="footer">
			<jsp:include page="/footer.jsp" flush="true"/>
		</div>
	  </div>
		
	</body>
</html>