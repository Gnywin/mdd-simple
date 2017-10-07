/**
 * Created by 猫大东 on 2017/5/22.
 */
var email_flag = false;
var validator = $("#cp1From").validate({
    debug: true,
    onkeyup: false,
    onfocusout: false,
    onclick: false,
    focusInvalid: false,
    submitHandler: function () {
        findPwd();
    },
    rules: {
        email: {
            required: true,
            email: true,
            loginNameUnExist: true,
            maxlength: 124
        },
        code: {
            required: true,
            verify: true,
            maxlength: 4
        }
    },
    messages: {
        email: {
            required: "请告诉喵您绑定的邮箱",
            email: "不要告诉喵错误的邮箱地址，喵",
            maxlength: "这个邮箱有些诡异哦，无法找回，喵",
            loginNameUnExist: "该邮箱尚未注册，还不快去注册！"
        },
        code: {
            required: "请输入验证码",
            maxlength: "验证码错误"
        }
    },
    errorElement: "span",
    errorPlacement: function (error, element) {
        //邮箱验证
        if (element.attr('id') == "email") {
            email_flag = false;
        }
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
        //邮箱验证
        if ($(element).attr('id') == "email") {
            email_flag = true;
        }
        label.remove();
        $(element).parent().removeClass("has-danger");
        $(element).removeClass("form-control-danger");
        $(element).parent().addClass("has-success");
        $(element).addClass("form-control-success");
    }
});

$(function () {
    var $verifyCode =  $("#verifyCode");
    var $email = $("#email");
    var $code = $("#code");
    var $email_bak = "";
    var $code_bak = "";

    $verifyCode.attr('src',host+'verify?tid='+token.accessToken +'&time'+ new Date().getMilliseconds());
    $verifyCode.click(function (){
        $verifyCode.attr('src',host+'verify?tid='+token.accessToken+'&time'+ new Date().getMilliseconds());
    });

    $email.mouseout(function () {
        if ($email_bak != $email.val()) {
            $email_bak = $email.val();
            validator.element($email);
        }
    });
    $code.mouseout(function () {
        if ($code_bak != $code.val()) {
            $code_bak = $code.val();
            validator.element($code);
        }
    });
});
function findPwd() {
    var data = {};
    data.email = $("#email").val();
    data.type = 1;
    Get({
        url: "email/send",
        data: data,
        success: function (res) {
            $("#cp1From").remove();
            $(".cp1").append("<div class='success-msg'>邮件发送成功，请前往邮箱确认</div>")
        }
    })
}