<!DOCTYPE html>
<html>
<head>
    <title>猫大东的榕树下</title>
<#include "./inc/base-head.ftl">
    <link  rel="stylesheet" href="${host}/static/user/css/logout.css">
    <link  rel="stylesheet" href="${host}/static/common/css/bootstrapValidator.min.css">
</head>
<body>
<header>
    <nav>
        <a href="${host}/">
            <img class="logo" src="${host}/static/img/mdd-logo.png"> </img>
            <span class="title">猫大东的榕树下</span>
        </a>
    </nav>
</header>
<div class="container">
    <div class="big-logo"></div>
    <span class="logout-title">登出</span>
    <section class="logout">
        <div class="success-msg">登出成功，即将跳转页面</div>
    </section>
</div>
<script>
    var token = ${tokenJson}
    localStorage.setItem("token",JSON.stringify(token));
    setTimeout(function(){
        window.location.href = host;
    },2000)
</script>
<#include "./inc/base-script.ftl">
<script src="${host}/static/common/js/jquery.validate.js"></script>
<script src="${host}/static/common/js/additional-methods.js"></script>
</body>
</html>