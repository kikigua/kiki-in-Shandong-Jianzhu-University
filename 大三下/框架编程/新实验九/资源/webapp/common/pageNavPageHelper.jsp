<%--
  Created by IntelliJ IDEA.
  User: wang
  Date: 2021/1/5/005
  Time: 11:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--添加标签库--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--在作用域中保存一个当前路径参数ctx--%>
<c:set value="${pageContext.request.contextPath}" var="ctx"/>

<style type="text/css">
    #pageNav {
        border: 1px black solid;
    }
</style>

<%--导入css样式--%>
<link type="text/css" rel="stylesheet" href="${ctx}/statics/css/common.css"/>

<%--<script type="text/javascript" src="${ctx}/statics/js/wang/jquery-1.12.4.js"></script>--%>
<div class="page-bar">
    <%--测试变量是否传递过来--%>
    <%--<h1 style="color: red">Test1<br/>
        ${param.pageIndex}
    </h1>--%>
    <ul class="page-num-ul clearfix">
        <li style="margin-left: 25px">共${param.total}条记录&nbsp;&nbsp; 第${param.pageNum}页/共${param.pages}页
        </li>&nbsp;&nbsp;
        <c:choose>
            <c:when test="${param.pageNum==1||param.pageNum==0}">
                <a id="pageNav">首页</a>
                <a id="pageNav">上一页</a>
            </c:when>
            <c:otherwise>
                <a href="javascript:page_nav(1);" id="pageNav">首页</a>
                <a id="pageNav" href="javascript:page_nav(${param.pageNum-1});">上一页</a>
            </c:otherwise>

        </c:choose>

        <c:choose>
            <c:when test="${param.pageNum==param.pages}">
                <a id="pageNav">下一页</a>
                <a id="pageNav">尾页</a>
            </c:when>
            <c:otherwise>
                <a id="pageNav" href="javascript:page_nav(${param.pageNum+1});">下一页</a>
                <a id="pageNav" href="javascript:page_nav(${param.pages});">尾页</a>
            </c:otherwise>

        </c:choose>

    </ul>
    <c:if test="${param.pageNum!=0}">
    <span class="page-go-form"><label>跳转至</label>
	     <input type="text" name="inputPage" id="inputPage" class="page-key" value="${param.pageNum}"
                style="text-align: center;height: 20px"/>页
	     <button type="button" class="page-btn"
                 onclick='page_nav(document.getElementById("inputPage").value)'>GO</button>
		</span>
    </c:if>
    <script type="text/javascript">
        function page_nav(pageNum) {
            // alert("pageNum---" + pageNum);
            var pageIndexRegist = /^[1-9]d*$/;
            var form = document.getElementById("searchForm");
            if (!pageIndexRegist.test(pageNum)) {
                pageNum = 1;
            }
            if (pageNum >${param.pages}) {
                pageNum =${param.pages};
            }
            //alert("pageSize="+pageIndex);
            //alert(form.pageIndex.getAttribute("name"));
            form.pageNum.value = pageNum;
            form.submit();
        }

    </script>


</div>
