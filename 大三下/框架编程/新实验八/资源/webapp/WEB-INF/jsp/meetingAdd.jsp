<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--添加标签库--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--在作用域中保存一个当前路径参数ctx--%>
<c:set value="${pageContext.request.contextPath}" var="ctx"/>


<div class="providerAdd">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>会议新增页面</span>
    </div>
    <form action="#" method="post" id="meetingAddForm">
        <!--div的class 为error是验证错误，ok是验证成功-->
        <div>
            <label for="title">会议主题：</label>
            <input type="text" name="title" id="title"/>
            <span id="sp1">*</span>
        </div>
        <div>
            <label for="startTime">开始时间：</label>
            <input type="datetime-local" name="startTime" id="startTime"/>
            <span id="sp2">*</span>
        </div>
        <div>
            <label for="endTime">结束时间：</label>
            <input type="datetime-local" name="endTime" id="endTime"/>
            <span id="sp3">*</span>
        </div>
        <div>
            <label for="meetingRoom">会议地点：</label>
            <input type="text" name="meetingRoom" id="meetingRoom"/>
            <span id="sp4">*</span>
        </div>
        <div>
            <label for="persons">与会人员：</label>
            <input type="text" name="persons" id="persons"/>
            <span id="sp5"></span>
        </div>
        <div>
            <label for="content">会议内容：</label>
            <textarea name="content" id="content" rows="8" cols="40"></textarea>
            <span id="sp6"></span>
        </div>
        <div>
            <label for="note">描述：</label>
            <textarea name="note" id="note" rows="8" cols="40"></textarea>
            <span id="sp7"></span>
        </div>
        <div class="providerAddBtn">
            <!--<a href="#">保存</a>-->
            <!--<a href="billList.jsp">返回</a>-->
            <input type="button" value="保存" id="btn"/>
            <input type="button" value="返回" onclick="location.href='${ctx}/provider/providerList'"/>
        </div>
    </form>
</div>

<%--新增供应商--%>
<script type="text/javascript">
    $(function () {
        $("#btn").click(function () {
                //验证成功
                var data = $("#meetingAddForm").serialize();
                $.post(
                    ctx + "/meeting/meetingAdd",
                    data,
                    function (result) {
                        //alert(result);
                        if (result > 0) {
                            alert("新增成功！");
                            location.href = "${ctx}/meeting/meetingList";
                        } else {
                            alert("新增失败！");
                        }

                    }, "json"
                );
    });
    });
</script>


