/**
 * Created by yaling.he on 2015/11/17.
 */


$(function () {
    $('.removeMeeting').click(function () {
        $('.zhezhao').css('display', 'block');
        $('#removeMeeting').fadeIn();
        //获取当前行的provider主键id
        var id = $(this).attr("id");
        $(".removerChid").attr("id", id);
    });
});

$(function () {
    $('#yes').click(function () {
        $('.zhezhao').css('display', 'none');
        $('#removeMeeting').fadeOut();
        var id = $(".removerChid").attr("id");
        // alert("id" + id);
        $.post(
            ctx + "/meeting/deleteMeetingById",
            {"id": id},
            function (result) {
                //alert("result" + result);
                if (result == 1) {
                    alert("删除成功！");
                    location.href = ctx + "/meeting/meetingList";
                } else {
                    alert("删除失败！");
                    $('.remove').css("display", "none");
                }
            }, "json"
        );
    });
});


$(function () {
    $('#no').click(function () {
        $('.zhezhao').css('display', 'none');
        $('#removeMeeting').fadeOut();
    });
});


$(function () {
    $('.removeBill').click(function () {
        $('.zhezhao').css('display', 'block');
        $('#removeBi').fadeIn();
        //获取当前行的bill主键id
        var id = $(this).attr("id");
        $(".removerChid").attr("id", id);
    });
});

$(function () {
    $('#yesBill').click(function () {
        $('.zhezhao').css('display', 'none');
        $('#removeBi').fadeOut();
        var id = $(".removerChid").attr("id");
        $.post(
            ctx + "/bill/deleteBillById",
            {"id": id},
            function (result) {
                if (result) {
                    alert("删除成功！")
                    location.href = ctx + "/bill/billList";
                } else {
                    alert("删除失败！");
                }

            }
        );
    });
});

$(function () {
    $('#no').click(function () {
        $('.zhezhao').css('display', 'none');
        $('#removeBi').fadeOut();
    });
});

$(function () {
    $('.removeUser').click(function () {
        //样式的处理
        $('.zhezhao').css('display', 'block');
        $('#removeUse').fadeIn();
        //获取当前行的user主键id
        var id = $(this).attr("id");
        $(".removerChid").attr("id", id);

    });
});

$(function () {
    $('#yesUser').click(function () {
        $('.zhezhao').css('display', 'none');
        $('#removeUse').fadeOut();
        var id = $(".removerChid").attr("id");
        $.post(
            ctx + "/user/deleteUserById",
            {"id": id},
            function (result) {
                // alert(result);
                if (result) {
                    alert("删除成功！")
                    location.href = ctx + "/user/userList";
                } else {
                    alert("删除失败！");
                }

            }
        );
    });
});

$(function () {
    $('#no').click(function () {
        $('.zhezhao').css('display', 'none');
        $('#removeUse').fadeOut();
    });
});
