<% 
request.setCharacterEncoding("utf-8");
response.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="user" class="bean.UserBean" scope="session"/>
<%
String username=user.getUsername(); 
if(username.equals("")){
	response.sendRedirect(request.getContextPath()+"/index.jsp");
}
%>