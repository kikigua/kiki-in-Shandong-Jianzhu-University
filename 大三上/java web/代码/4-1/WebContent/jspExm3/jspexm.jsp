<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%--以下分别声明了一个实例变量和一个方法 --%>
	<%! int i=5; %>
	<%! 
	  int add(int a,int b){
	    return a+b;
	  }
	%>
	<%--使用表达式输出声明的实例变量--%>
	i=<%=i %><br>
	<%--使用脚本段调用声明的方法并用表达式输出结果 --%>
	<% 
	int x=4,y=5,z;
	z=add(x,y);
	%>
	z=<%=z %>
</body>
</html>