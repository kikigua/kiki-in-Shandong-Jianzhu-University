<%@ page contentType="text/html;charset=utf-8" %>
<!--destination.jsp-->
<%
	String useName=request.getParameter("userName");
	String outStr= "欢迎你！";
	outStr+=useName;
	out.println(outStr);	
%>
