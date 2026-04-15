<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

	<c:url var="urlStr" value="/index.jsp">
		<c:param name="id" value="111"/>
	</c:url>
	<a href="${urlStr}">测试1</a>
	
	<!-- 同一容器下的不同工程 -->
	<c:url var="urlStr" value="/index.jsp" context="/7-2">
		<c:param name="id" value="111"/>
	</c:url>
	<a href="${urlStr}">测试2</a>
	
	<!-- 引入不同工程下的内容 -->
	<%-- <div>
		<c:import url="http://www.baidu.com" charEncoding="utf-8"/>
	</div> --%>
</body>
</html>