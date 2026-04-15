<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%--添加标签库--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--在作用域中保存一个当前路径参数ctx--%>
<c:set value="${pageContext.request.contextPath}" var="ctx"/>

<%--去除 GET http://localhost:8080/favicon.ico 404 (Not Found) 错误--%>
<%--<link rel="shortcut icon" href="#"/>--%>

<%--js中的项目路径名ctx--%>
<script type="text/javascript">
    var ctx = "${pageContext.request.contextPath}";
</script>

<%--导入css--%>
<link type="text/css" rel="stylesheet" href="${ctx}/statics/css/style.css"/>
<link type="text/css" rel="stylesheet" href="${ctx}/statics/css/public.css"/>
<link type="text/css" rel="stylesheet" href="${ctx}/statics/css/common.css"/>

<%--导入jQuery--%>
<script type="text/javascript" src="${ctx}/statics/js/jquery-1.12.4.js"></script>

<%--导入time.js--%>
<script type="text/javascript" src="${ctx}/statics/js/time.js"></script>

<%--导入js.js--%>
<script type="text/javascript" src="${ctx}/statics/js/js.js"></script>
<script type="text/javascript" src="${ctx}/statics/js/md5.js"></script>



