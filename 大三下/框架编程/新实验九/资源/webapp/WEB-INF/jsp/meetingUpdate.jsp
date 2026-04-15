<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--添加标签库--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--在作用域中保存一个当前路径参数ctx--%>
<c:set value="${pageContext.request.contextPath}" var="ctx"/>
<div class="providerAdd">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>会议修改页面</span>
    </div>
    <form action="#" method="post" id="meetingUpdateForm">
        <input type="hidden" name="id" value="${meeting.id}"/>
        <div>
            <label for="title">会议主题：</label>
            <input type="text" name="title" id="title" value="${meeting.title}"/>
            <span id="sp1">*</span>
        </div>
        <div>
            <label for="startTime">开始时间：</label>
            <input type="datetime-local" name="startTime" id="startTime" value='<fmt:formatDate value="${meeting.startTime}" pattern="yyyy-MM-dd'T'HH:mm:ss"/>'/>
            <span id="sp2">请务必手动输入时间</span>

        </div>
        <div>
            <label for="endTime">结束时间：</label>
            <input type="datetime-local" name="endTime" id="endTime" value='<fmt:formatDate value="${meeting.endTime}" pattern="yyyy-MM-dd'T'HH:mm:ss"/>'/>
            <span id="sp3">请务必手动输入时间</span>

        </div>
        <div>
            <label for="meetingRoom">会议地址：</label>
            <input type="text" name="meetingRoom" id="meetingRoom" value="${meeting.meetingRoom}"/>
            <span id="sp4"></span>
        </div>
        <div>
            <label for="persons">与会人员：</label>
            <input type="text" name="persons" id="persons" value="${meeting.persons}"/>
            <span id="sp5"></span>

        </div>
        <div>
            <label for="content">会议内容：</label>
            <input type="text" name="content" id="content" value="${meeting.content}"/>
            <span id="sp6"></span>

        </div>
        <div>
            <label for="note">描述：</label>
            <textarea name="note" rows="8" cols="40" id="note">${meeting.note}</textarea>
            <span id="sp7"></span>

        </div>
        <div class="providerAddBtn">
            <!--<a href="#">保存</a>-->
            <!--<a href="billList.jsp">返回</a>-->
            <input type="button" value="保存" id="btn"/>
            <input type="button" value="返回" onclick="location.href='${ctx}/meeting/meetingList'"/>
        </div>
    </form>
</div>
<%--修改供应商--%>
<script type="text/javascript">
    $(function () {
        $("#btn").click(function () {

                var data = $("#meetingUpdateForm").serialize();
                // alert(data);
                $.post(
                    ctx + "/meeting/meetingUpdate",
                    data,
                    function (result) {
                        // alert(result);
                        if (result>0) {
                            alert("修改成功！");
                            location.href = "${ctx}/meeting/meetingList";
                        } else {
                            alert("修改失败！");
                        }
                    }, "json"
                );
        });
    });
</script>
