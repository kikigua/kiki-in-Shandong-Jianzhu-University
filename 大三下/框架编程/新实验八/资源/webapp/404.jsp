<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="/common/headerAndLeft.jsp" %>


<div class="right">
    <%--    <img class="wColck" src="${ctx}/statics/images/clock.jpg" alt=""/>--%>
    <div class="wFont">

        <h1 style="color:red">您访问的页面不存在!</h1>
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
</section>
<footer class="footer">
    &nbsp;qst
</footer>

</body>
</html>
