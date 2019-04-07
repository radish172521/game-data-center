$(function () {
    validateRule();
});
$.validator.setDefaults({
    submitHandler: function () {
        update();
    }
});


function update() {
    var params = new FormData($('#signupForm')[0]);
    $.ajax({
        cache: false,
        type: "POST",
        url: "/game/exchangeGoods/saveGoods",
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
            goodsName: {
                required: true
            },
            goodsPrice: {
                required: true,
                number: true
            },
            goodsPictureUrl: {
                required: true
            },
            goodsDisable: {
                required: true
            },
            sort: {
                digits: true
            }
        },
        messages: {
            goodsName: {
                required: icon + "请输入商品名"
            },
            goodsPrice: {
                required: icon + "请输入兑换价格",
                number: icon + "兑换价格必须为数字"
            },
            goodsPictureUrl: {
                required: icon + "请选择图片"
            },
            goodsDisable: {
                required: icon + "状态不能为空"
            },sort:{
                digits: icon + "必须输入整数"
            }
        }
    });
}