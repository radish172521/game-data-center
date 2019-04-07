//var menuTree;
$(function () {
    validateRule();
});
$.validator.setDefaults({
    submitHandler: function () {
        save();
    }
});
$("#gameRuleDiv").hide();
$('input[type=radio][name=enabledTimesRule]').change(function(){
    var val = $(this).val();
    if(val == 1){
        $("#gameRuleDiv").show();
        $("#gameTaskTypeDiv").hide();
    }else if(val == 0){
        $("#gameTaskTypeDiv").show();
        $("#gameRuleDiv").hide();
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
            taskConstantKey: {
                required: true
            },
            gameTaskName: {
                required: true
            },
            rewardDrawTimes: {
                digits: true
            },
            rewardIntegralMin: {
                number: true
            },
            rewardIntegralMax: {
                number: true
            },
            enabledTimesRule: {
                required: true
            },
            disabled: {
                required: true
            }
        },
        messages: {
            gameTaskName: {
                required: icon + "请输入游戏任务名"
            },
            taskConstantKey: {
                required: icon + "请输入游戏任务约定Key"
            },
            rewardDrawTimes: {
                digits: icon + "奖励抽奖次数必须为整数"
            },
            rewardIntegralMin: {
                number: icon + "请输入至少奖励多少积分"
            },
            rewardIntegralMax: {
                number: icon + "请输入至多奖励多少积分"
            },
            enabledTimesRule: {
                required: icon + "请选择开启任务次数规则"
            },
            disabled: {
                required: icon + "请选择启用状态"
            }

        }
    });


}