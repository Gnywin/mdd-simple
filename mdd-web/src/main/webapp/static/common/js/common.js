/**
 * Created by 猫大东 on 2017/10/6.
 */
$(document).ready(function(){
    $(".register").attr('href',host+'user/register?tid='+token.accessToken);
    $(".login").attr('href',host+'user/login?tid='+token.accessToken);
    $(".logout").attr('href',host+'user/logout?tid='+token.accessToken);
});
