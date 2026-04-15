<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<header>
    <%@include file="/common/headerAndLeft.jsp"%>
</header>

<!-- 右边内容 -->
    <div class="main" style="font-size: 30px">


        <h1 style="color:red">操作异常!</h1>
        <a href="/user/login">点击返回，或等待5秒自动返回！<span id="sp0">5s</span></a>

        <script type="text/javascript">
            var interval = window.setInterval(goBack,1000);
            var index=5;
            var span=document.getElementById("sp0");
            function goBack() {
                index--;
                span.innerText=index+"s";
                if(index==0){
                    location.href='/user/login';
                    window.clearInterval(interval);
                }
            }
        </script>
    </div>
</div>

