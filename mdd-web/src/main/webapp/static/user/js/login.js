/**
 * Created by 猫大东 on 2017/5/22.
 */
var email_flag = false;
var validator = $("#loginForm").validate({
    debug: true,
    onkeyup: false,
    onfocusout: false,
    onclick: false,
    focusInvalid: false,
    submitHandler: function () {
        login();
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
            maxlength: 124
        },
    },
    messages: {
        pwd: {
            required: ">喵<需要一段魔法咒语来开启树洞",
            minlength: ">喵<喜欢长度不少于6的密码",
            maxlength: ">喵<喜欢长度不多于16的密码"
        },
        email: {
            required: "需要邮箱才可以验证小主的身份哦",
            email: "不要告诉喵错误的邮箱地址，喵",
            maxlength: "这个邮箱有些诡异哦，无法验证",
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
    $("#forget").attr('href',host+'user/cp1?tid='+token.accessToken);
    var $email = $("#email");
    var $email_bak = "";
    $("#pwd").blur(function () {
        validator.element($("#pwd"));
    });
    $email.blur(function () {
        validator.element($email);
    });
    $email.mouseout(function () {
        if ($email_bak != $email.val()) {
            $email_bak = $email.val();
            validator.element($email);
        }
        $("#emailCode-error").css("background", $("#emailCode").css("background-color"));
    });
});
function login() {
    var publicKey = RSAUtils.getKeyPair(token.pk.encoded, '', token.pk.modulus);
    var data = {};
    data.pwd = RSAUtils.encryptedString(publicKey, $("#pwd").val());
    data.loginName = RSAUtils.encryptedString(publicKey, $("#email").val());
    data.remember = $("#remember")[0].checked ? 1 : 0;
    Post({
        url: "user/login",
        data: data,
        success: function (res) {
            localStorage.setItem("token",JSON.stringify(res));
            token = res.data;
            window.location.href = host;
        },
        error:function (res) {
            if(res.code == 601){
                $("#verify").show();
                $("#verifyCode").attr('src',host+'verify?tid='+token.accessToken +'&time'+ new Date().getMilliseconds());
                $("#verifyCode").click(function (){
                    $("#verifyCode").attr('src',host+'verify?tid='+token.accessToken+'&time'+ new Date().getMilliseconds());
                });
            }else {
                showError({
                    msg:res.msg
                })
            }
        },
    })
}
