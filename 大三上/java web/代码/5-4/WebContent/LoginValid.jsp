<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="error.jsp"%>
<body>
    <jsp:useBean id="user" class="bean.UserBean" scope="session"/>
	<jsp:setProperty name="user" property="username" param="name"/>
	<jsp:setProperty name="user" property="password"/>
	<%
	    boolean successflag=user.validlogin();
	    if(successflag==true){
	    	response.sendRedirect("LoginSuccess.jsp");
	    }else{
	    	request.setAttribute("error", "用户名或密码不正确，登陆失败！");
	    	request.getRequestDispatcher("index.jsp").forward(request, response);
	    }
	%>
</body>
