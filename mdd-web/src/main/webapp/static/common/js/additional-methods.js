/**
 * Created by  猫大东 on 2017/6/4.
 */
jQuery.validator.addMethod("loginNameExist", function (value, element) {
    var result = false;
    Http({
        url: host + "user/loginNameExist",
        data: {loginName: value},
        async: false,
        success: function (res) {
            if (res == 0) {
                result = true;
            }
        }
    });
    return result;

}, "该用户已经存在");

jQuery.validator.addMethod("loginNameUnExist", function (value, element) {
    var result = false;
    Http({
        url: host + "user/loginNameExist",
        data: {loginName: value},
        async: false,
        success: function (res) {
            if (res == 1) {
                result = true;
            }
        }
    });
    return result;

}, "该用户不存在");
/***
 * 校验验证码
 */
jQuery.validator.addMethod("verify", function (value, element) {
    var result = false;
    Http({
        url: host + "verifyCode",
        data: {verify: value},
        async: false,
        success: function (res) {
            if (res == 1) {
                result = true;
            }else if(res == -1){
                jQuery.validator.messages.verify = "验证码失效";
            }else {
                jQuery.validator.messages.verify = "验证码错误";
            }
        }
    });
    return result;
}, "验证码错误");