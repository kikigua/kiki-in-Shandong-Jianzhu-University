<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <!DOCTYPE html>
    <html xmlns="http://www.w3.org/1999/xhtml">
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>在线办公云协同系统</title>

    <style type="text/css">
    * {
    margin: 0;
    padding: 0;
    }

    body {
    font: 12px 宋体;
    background: #4BB8EF url(${ctx}/statics/images/bg.gif) repeat-x;
    }

    img {
    border: 0;
    }

    .login-top {
    width: 100%;
    height: 186px;
    margin: 147px auto 0;
    <%--background: url(${ctx}/statics/images/login_01.gif) no-repeat center 0;--%>
    /*background: rgb(192,211,233);*/
        text-align: center;
        font-size: 30px;
        color: cadetblue;
    }

    .login-area {
    width: 100%;
    height: 140px;
    margin: 0 auto;
    background: url(${ctx}/statics/images/login_02.gif) no-repeat center 0;
    }

    .login-area form {
    width: 290px;
    margin: 0 auto;
    }

    .login-area label {
    clear: left;
    float: left;
    margin-top: 13px;
    width: 60px;
    font: 600 14px 宋体;
    }

    .login-area input {
    width: 122px;
    height: 16px;
    margin-top: 11px;
    border: 1px #767F94 solid;
    font: 12px/ 16px 宋体;
    }

    input.login-sub {
    width: 104px;
    height: 34px;
    border: 0;
    background: url(${ctx}/statics/images/login_sub.gif) no-repeat 0px 1px; *
    margin-top: 5px;
    }

    .login-copyright {
    width: 100%;
    height: 30px;
    margin: 18px auto 0;
    <%--background: url(${ctx}/statics/images/copyright.gif) no-repeat center 0;--%>
    }
    </style>

    <%--导入jQuery--%>
    <script type="text/javascript" src="${ctx}/statics/js/jquery-1.12.4.js"></script>
    <script type="text/javascript">
    </script>
    </head>
    <body>
    <div class="login-top" >
        <h1>在线办公云协同系统</h1>
    </div>
    <div class="login-area">

    <form action="${ctx}/user/userLogin" method="post" id="myForm">
    <label>
    用户名：
    </label>
    <input type="text" name="userName" id="user"/>
    <label>
    密&nbsp;&nbsp;码：
    </label>
    <input type="password" name="userPassword" id="mima" />
    <input type="button" id="btn" class="login-sub"/>
    <input type="hidden" id="msg" value="${requestScope.msg}" />
    </form>
    </div>
    <div class="login-copyright"></div>
    <script type="text/javascript">
    $(function () {
    $("#btn").click(function () {
    var flag1 = false;
    var flag2 = false;

    if ($("#user").val() == null || $("#user").val() == "") {
    alert("用户名不能为空！");
    } else {
    flag1 = true;
    }
    if ($("#mima").val() == null || $("#mima").val() == "") {
    alert("密码不能为空！");
    } else {
    flag2 = true;
    }
    if (flag1 && flag2) {
    var data = $("#myForm").serialize();
    //alert(data);
    $.post(
    "${ctx}/user/userLogin",
    data,
    function (result) {

    if (result.resCode == "200") {
    location.href = "${ctx}/user/login";
    } else {
    alert(result.resMsg);
    }
    }, "json");
    } else {
    alert("请输入用户名和密码！");
    }
    });
    });
    </script>
    </body>
    </html>