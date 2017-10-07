/**
 * Created by 猫大东 on 2017/5/22.
 */
var validator = $("#cp2From").validate({
    debug: true,
    onkeyup: false,
    onfocusout: false,
    onclick: false,
    focusInvalid: false,
    submitHandler: function () {
        findPwd();
    },
    rules: {
        pwd: {
            required: true,
            minlength: 6,
            maxlength: 16
        }
    },
    messages: {
        pwd: {
            required: ">喵<需要一段魔法咒语来开启树洞",
            minlength: ">喵<喜欢长度不少于6的密码",
            maxlength: ">喵<喜欢长度不多于16的密码"
        },
    },
    errorElement: "span",
    errorPlacement: function (error, element) {

        element.parent().removeClass("has-success");
        element.removeClass("form-control-success");
        element.parent().addClass("has-danger");
        element.addClass("form-control-danger");
        error.css("background", element.css("background-color"));
        if (element.prop("type") === "checkbox") {
            error.insertAfter(element.parent("label"));
        } else {
            error.insertAfter(element.parent());
            error.click(function () {
                error.remove();
                element.focus();
            });
            element.click(function () {
                error.remove();
            });
        }
    },
    success: function (label, element) {
        label.remove();
        $(element).parent().removeClass("has-danger");
        $(element).removeClass("form-control-danger");
        $(element).parent().addClass("has-success");
        $(element).addClass("form-control-success");
    }
});

function findPwd() {
    var publicKey = RSAUtils.getKeyPair(token.pk.encoded, '', token.pk.modulus);
    var data = {};
    data.pwd =RSAUtils.encryptedString(publicKey, $("#pwd").val());;
    data.key = key;
    Post({
        url: "user/alterPwd",
        data: data,
        success: function (res) {
            debugger
            localStorage.setItem("token",JSON.stringify(res));
            token = res.data;
            /*$(".step-current").addClass("step-past");
            $(".step-current").removeClass("step-current");
            $(".step-next").addClass("step-current");
            $(".step-next").removeClass("step-next");*/
            $("#cp2From").remove();
            $(".cp2").append("<div class='success-msg'>设置成功，页面将自动跳转</div>")
            setTimeout(function(){
                window.location.href = host;
            },1000)
        }
    })
}