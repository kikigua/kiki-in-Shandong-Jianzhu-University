<% 
request.setCharacterEncoding("utf-8");
response.setCharacterEncoding("utf-8");
String path=request.getContextPath();//部署的应用程序名"/www"
%>
<jsp:useBean id="user" class="bean.UserBean" scope="session"/>
<%
String username=user.getUsername(); 
if(username.equals("")){
	response.sendRedirect(path+"/index.jsp");
}
%>