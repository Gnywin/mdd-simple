/**
 * Created by 猫大东 on 2017/5/8.
 */
$(document).ready(function () {
    setTimeout(function(){
        $(".big-logo").slideToggle(700);
    },800);
});
$("#i-say").click(function() {
    if (token.type != 0) {
        actionConfirm({
            msg:"尚未注册哦，还不快去！",
            confirm:function(){
                window.location.href = host + "user/register";
            }
        })
    }else {
        setTimeout(function(){
            $(".say").fadeOut(600);
        },10)
        $(".say-ba").fadeIn(700);
    }
});