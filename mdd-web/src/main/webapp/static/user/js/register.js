/**
 * Created by 猫大东 on 2017/5/22.
 */
var email_flag = false;
var validator = $("#regForm").validate({
    debug: true,
    onkeyup: false,
    onfocusout: false,
    onclick: false,
    focusInvalid: false,
    submitHandler: function () {
        register();
    },
    rules: {
        pwd: {
            required: true,
            minlength: 6,
            maxlength: 16
        },
        email: {
            required: true,
            email: true,
            loginNameExist: true,
            maxlength: 124
        },
        emailCode: {
            required: true,
            digits: true,
            rangelength: [6, 6]
        }
    },
    messages: {
        pwd: {
            required: ">喵<需要一段魔法咒语来开启树洞",
            minlength: ">喵<喜欢长度不少于6的密码",
            maxlength: ">喵<喜欢长度不多于16的密码"
        },
        emailCode: {
            required: ">喵<需要一个验证码",
            digits: "这可不像本喵给的验证码",
            rangelength: "这可不像本喵给的验证码"
        },
        email: {
            required: "请告诉喵您的邮箱，让喵记得您",
            email: "不要告诉喵错误的邮箱地址，喵",
            maxlength: "这个邮箱有些诡异哦，换个试试吧，喵",
            loginNameExist: "喵，该邮箱已注册，您可以通过邮箱找回"
        },
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
    var $codeBtn = $("#codeBtn");
    var $emailCode = $("#emailCode");
    var $email = $("#email");
    var $email_bak = "";
    var $code_flag = true;
    $codeBtn.popover({
        placement: "right",
        trigger: 'focus',
    });
    $emailCode.blur(function () {
        validator.element($emailCode);
    });
    $("#pwd").blur(function () {
        validator.element($("#pwd"));
    });
    $email.blur(function () {
        validator.element($email);
        if (email_flag && $code_flag) {
            $codeBtn.removeClass("btn-secondary");
            $codeBtn.addClass("btn-info");
            $emailCode.removeAttr("readonly");
            $codeBtn[0].dataset.content = "咒语已经发送至小主的邮箱了哦~";
        } else if (!email_flag) {
            $codeBtn.removeClass("btn-info");
            $codeBtn.addClass("btn-secondary");
            $emailCode.attr("readonly", true);
            $emailCode.val("");
            $codeBtn[0].dataset.content = "喵需要一个正确的邮箱才能告诉你这一小段魔咒哦~";
        }
        $("#emailCode-error").css("background", $("#emailCode").css("background-color"));//变换背景色
    });
    $email.mouseout(function () {
        if ($email_bak != $email.val()) {
            $email_bak = $email.val();
            validator.element($email);
            if (email_flag && $code_flag) {
                $codeBtn.removeClass("btn-secondary");
                $codeBtn.addClass("btn-info");
                $emailCode.removeAttr("readonly");
                $codeBtn[0].dataset.content = "咒语已经发送至小主的邮箱了哦~";
            } else if (!email_flag) {
                $codeBtn.removeClass("btn-info");
                $codeBtn.addClass("btn-secondary");
                $emailCode.attr("readonly", true);
                $emailCode.val("");
                $codeBtn[0].dataset.content = "喵需要一个正确的邮箱才能告诉你这段小咒语哦~";
            }
        }
        $("#emailCode-error").css("background", $("#emailCode").css("background-color"));
    });
    $codeBtn.click(function () {
        if (email_flag && $code_flag) {
            Get({
                url: "email/send",
                data: {email: $email.val()},
                success: function (res) {
                    $codeBtn.removeClass("btn-info");
                    $codeBtn.addClass("btn-secondary");
                    $code_flag = false;
                    var time = 60;
                    $codeBtn.text(time + "秒后可以重试，喵");
                    var inv = setInterval(function () {
                        time--;
                        $codeBtn.text(time + "秒后可以重试，喵");
                        if (time == 0) {
                            $code_flag = true;
                            $codeBtn.text("获取邮箱验证码");
                            clearInterval(inv);
                        }
                    }, 1000);
                },
                error: function () {
                    showError({
                        msg: "口令已发送，让喵喘口气~"
                    });
                    $codeBtn.removeClass("btn-info");
                    $codeBtn.addClass("btn-secondary");
                    $code_flag = false;
                    var time = 60;
                    $codeBtn.text(time + "秒后可以重试，喵");
                    var inv = setInterval(function () {
                        time--;
                        $codeBtn.text(time + "秒后可以重试，喵");
                        if (time == 0) {
                            $code_flag = true;
                            $codeBtn.text("获取邮箱验证码");
                            clearInterval(inv);
                        }
                    }, 1000);
                }
            });

        }
    });

});
function register() {
    var publicKey = RSAUtils.getKeyPair(token.pk.encoded, '', token.pk.modulus);
    var data = {};
    data.pwd = RSAUtils.encryptedString(publicKey, $("#pwd").val());
    data.loginName = RSAUtils.encryptedString(publicKey, $("#email").val());
    data.code = RSAUtils.encryptedString(publicKey, $("#emailCode").val());
    Post({
        url: "user/register",
        data: data,
        success: function (res) {
            localStorage.setItem("token",JSON.stringify(res));
            token = res.data;
            showYea({
                title: "注册成功",
                msg: "喵呜，恭喜小主、贺喜小主",
                confirm: function () {
                    window.location.href = host;
                }
            })
        }
    })
}