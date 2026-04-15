<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/headerAndLeft.jsp" %>
<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>会议管理页面</span>
    </div>
    <div class="search">
        <form action="${ctx}/meeting/meetingList" method="post" id="searchForm" name="searchForm">
            <span>会议主题：</span>
            <input type="text" name="title" value="${param.title}" placeholder="请输入会议的主题"/>

            <input type="hidden" name="pageIndex" value="1"/>
            <input type="hidden" name="pageSize" value="6"/>
            <input type="button" value="查询" id="btn1"/>
                <a href="javaScript:void(0)" class="billAdd">添加会议</a>
        </form>

    </div>
    <!--账单表格 样式和供应商公用-->
    <table class="providerTable" cellpadding="0" cellspacing="0">
        <tr class="firstTr">
            <th width="10%">会议主题</th>
            <th width="20%">会议室</th>
            <th width="25%">开始时间</th>
            <th width="25%">操作</th>
        </tr>
        <c:if test="${empty pageInfo.list}">
            <tr>
                <td colspan="5" style="font-size: 16px">查询不到相关信息，请检查检索条件重新检索！</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
        </c:if>
        <c:forEach items="${pageInfo.list}" var="meeting" varStatus="counts">
            <tr <c:if test="${counts.count%2 eq 0}"> class="admin-list-td-h2" </c:if>>
                <td>${meeting.title}</td>
                <td>${meeting.meetingRoom}</td>
                <td>
                        <fmt:formatDate value="${meeting.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                <td>
                            <a href="${ctx}/meeting/view/?id=${meeting.id}"><img src="${ctx}/statics/images/read.png" alt="查看"
                                                                           title="查看"/></a>
                            <a href="javaScript:void(0)" id="${meeting.id}" class="billUpdate"><img
                                    src="${ctx}/statics/images/xiugai.png" alt="修改" title="修改"/></a>

                            <a href="javaScript:void(0)" class="removeMeeting" id="${meeting.id}"><img
                                    src="${ctx}/statics/images/schu.png" alt="删除" title="删除"/></a>

                </td>
            </tr>
        </c:forEach>

    </table>

    <%--分页条--%>
    <div class="pageNav" style="margin-top: 50px">
        <c:import url="/common/pageNav.jsp">
            <c:param name="pageIndex" value="${pageInfo.pageNum}"></c:param>
            <c:param name="pageSize" value="${pageInfo.pageSize}"></c:param>
            <c:param name="totalPageNum" value="${pageInfo.pages}"></c:param>
            <c:param name="totalCount" value="${pageInfo.total}"></c:param>
        </c:import>
    </div>
    <br/>

    <div class="billUpdateView"></div>
    <div class="billAddView"></div>

    <div class="addView"></div>

</div>
</section>

<%--billAdd--%>
<script type="text/javascript">
    $(function () {
        $(".billAdd").click(function () {
            $(".addVIew").load(
                "${ctx}/meeting/toMeetingAdd"
            );
        });
    });
</script>

<%--billUpdate--%>
<script type="text/javascript">
    $(function () {
        $(".billUpdate").click(function () {
            $(".addVIew").load(
                "${ctx}/meeting/toMeetingUpdate",
                {"id": $(this).attr("id")}
            );
        });
    });
</script>

<%--条件分页查询按钮提交--%>
<script type="text/javascript">
    $(function () {
        $("#btn1").click(function () {
            $("#searchForm").submit();
        });
    });
</script>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeMeeting">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除该会议吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>

<footer class="footer">
    &nbsp;qst
</footer>


</body>
</html>