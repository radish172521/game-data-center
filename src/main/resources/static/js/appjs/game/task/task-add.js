//var menuTree;
$(function () {
    validateRule();
});
$.validator.setDefaults({
    submitHandler: function () {
        save();
    }
});

function save() {
    var params = new FormData($('#signupForm')[0]);
    $.ajax({
        cache: false,
        type: "POST",
        url: "/game/task/saveTask",
        data: params, // 你的formid
        async: false,
        processData: false,
        contentType: false,
        error: function (request) {
            alert("Connection error");
        },
        success: function (data) {
            if (data.code == 0) {
                parent.layer.msg("操作成功");
                parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);

            } else {
                parent.layer.msg(data.msg);
            }
        }
    });
}

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules: {
            gameTaskName: {
                required: true
            },
            gameTaskType: {
                required: true
            },
            gameTaskRewardType: {
                required: true
            },
            rewardCount: {
                required: true
            },
            rewardMultiple: {
                required: true
            },
            disabled: {
                required: true
            },
            rewardDrawTimes: {
                required: true
            }
        },
        messages: {
            gameTaskName: {
                required: icon + "请输入游戏任务名"
            },
            gameTaskType: {
                required: icon + "请选择游戏类型"
            },
            gameTaskRewardType: {
                required: icon + "请选择游戏奖励类型"
            },
            rewardCount: {
                required: icon + "请输入游戏奖励数量"
            },
            rewardMultiple: {
                required: icon + "请输入游戏奖励倍数"
            },
            disabled: {
                required: icon + "请选择任务状态"
            },
            rewardDrawTimes: {
                required: icon + "请选择是否奖励抽奖次数"
            }

        }
    });
}