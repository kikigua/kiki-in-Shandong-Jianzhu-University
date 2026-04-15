<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<%
	  String id=session.getId();
	  boolean isNewSession=session.isNew();
	  session.setAttribute("name","zhangsan");
	  String name=(String)session.getAttribute("name");
	  //默认的服务器设置的session超时数，超过此时间session失效
	  int originalMaxInactiveInterval=session.getMaxInactiveInterval();
	  //单位是秒即设置session超时的时间是100秒
	session.setMaxInactiveInterval(100); 
	  int newMaxInactiveInterval=session.getMaxInactiveInterval();
	%>
	<h1>
	  id=<%=id %><br>
	  isNewSession=<%=isNewSession %><br>
	  name=<%=name%><br>
	  originalMaxInactiveInterval=<%=originalMaxInactiveInterval %><br>
	  newMaxInactiveInterval=<%=newMaxInactiveInterval %><br>
	</h1>
</body>
</html>