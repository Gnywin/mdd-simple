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