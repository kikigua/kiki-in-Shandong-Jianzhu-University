<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/headerAndLeft.jsp" %>
<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>会议管理页面 >> 信息查看</span>
    </div>
    <div class="providerView">
        <p><strong>会议主题：</strong><span>${meeting.title}</span></p>
        <p><strong>开始时间：</strong><span>
            <fmt:formatDate value="${meeting.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </span></p>
        <p><strong>结束时间：</strong><span>
                <fmt:formatDate value="${meeting.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>

        </span></p>
        <p><strong>会议室：</strong><span>${meeting.meetingRoom}</span></p>
        <p><strong>参会人员：</strong><span>${meeting.persons}</span></p>
        <p><strong>会议内容：</strong><span>${meeting.content}</span></p>
        <p><strong>备注：</strong><span>${empty meeting.note?'无':meeting.note}</span></p>

        <a href="javaScript:void(0);history.go(-1)">返回</a>
    </div>
</div>
</section>
<footer class="footer">
    &nbsp;qst
</footer>


</body>
</html>