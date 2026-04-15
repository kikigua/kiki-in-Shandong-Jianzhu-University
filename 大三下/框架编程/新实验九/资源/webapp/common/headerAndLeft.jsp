<%--
  Created by IntelliJ IDEA.
  User: wang
  Date: 2021/3/17/017
  Time: 10:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/headInclude.jsp" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>在线办公云协同系统</title>
    <%
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/loginBak.jsp");
        }
    %>
</head>
<body>
<!--头部-->
<header class="publicHeader">

    <h1>在线办公云协同系统</h1>

    <div class="publicHeaderR">
        <p><span>您好！</span><span style="color: white"> ${user.userName}</span> , 欢迎你！</p>
        <a href="${ctx}/user/logout">退出</a>
    </div>
</header>
<!--时间-->
<section class="publicTime">
    <span id="time"></span>
<%--    <a href="https://www.google.com/intl/zh-CN/chrome/" target="_blank">温馨提示：为了能正常浏览，请使用高版本浏览器!(推荐Chrome)</a>--%>
</section>
<!--主体内容-->
<section class="publicMian ">
    <div class="left">
        <h2 class="leftH2"><span class="span1"></span>功能列表 <span></span></h2>
        <nav>
            <ul class="list" id="list">
                <li><a href="${ctx}/meeting/meetingList">会议管理</a></li>
                <li><a href="${ctx}/bill/billList">财务报销</a></li>
                <li><a href="${ctx}/user/view?id=${user.id}">个人信息</a></li>
                <li><a href="${ctx}/user/logout">退出系统</a></li>
            </ul>
            <script type="text/javascript">
                /*导航栏高亮*/
                /*截取当前点击的路径的最后一个/后的内容*/
                var urlstr = location.href.substring(location.href.lastIndexOf("/") + 1);
                $("#list a").each(function () {
                    /*遍历a链接，截取链接最后一个/后的内容*/
                    var str = $(this).attr("href").substring($(this).attr("href").lastIndexOf("/") + 1);
                    if (urlstr == str) {
                        $(this).parent().attr("id", "active");
                    } else {
                        $(this).parent().remove("id");
                    }
                });
            </script>
        </nav>

    </div>


