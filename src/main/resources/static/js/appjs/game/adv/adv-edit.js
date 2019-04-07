$(function() {
    validateRule();
});
$.validator.setDefaults({
    submitHandler : function() {
        update();
    }
});


function update() {
    var params =  new FormData($('#signupForm')[0]);
    $.ajax({
        cache : false,
        type : "POST",
        url : "/game/adv/saveAdv",
        data : params, // 你的formid
        async : false,
        processData:false,
        contentType:false,
        error : function(request) {
            alert("Connection error");
        },
        success : function(data) {
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
        rules : {
            advName : {
                required : true
            },
            picUrl : {
                required : true
            },
            disabled :{
                required : true
            }
        },
        messages : {
            advName : {
                required : icon + "请输入广告名"
            },
            picUrl :{
                required : icon + "请上传图片"
            },
            disabled :{
                required : icon + "请选择状态"
            }
        }
    });
}