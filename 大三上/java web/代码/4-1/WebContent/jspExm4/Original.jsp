<%@ page contentType="text/html;charset=utf-8" %>
<html>
	<head><title>test</title></head>
	<body>
		<jsp:forward page="Destination.jsp">
			<jsp:param name="userName" value="zhangsan"/>
		</jsp:forward>
	</body>
</html>
