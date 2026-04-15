<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>JSP内建对象</title>
	</head>
	<body>
		<% out.println("<table style='border:1px solid'>");
		   out.println("<tr><td>列1</td><td>列2</td><td>列3</td></tr>");
		   out.println("</table>");
		   
		   out.println(true);
		   out.println(123);
		%>
	</body>
</html>