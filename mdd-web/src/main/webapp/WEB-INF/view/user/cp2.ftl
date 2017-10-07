<!DOCTYPE html>
<html>
<head>
    <title>猫大东的榕树下</title>
<#include "./inc/base-head.ftl">
    <link  rel="stylesheet" href="${host}/static/user/css/cp2.css">
    <link  rel="stylesheet" href="${host}/static/common/css/mstep.css">
    <link  rel="stylesheet" href="${host}/static/common/css/bootstrapValidator.min.css">
</head>
<body>
<header>
    <nav>
        <a href="${host}/">
            <img class="logo" src="${host}/static/img/mdd-logo.png"> </img>
            <span class="title">猫大东的榕树下</span>
        </a>
        <a class="login" href="${host}/user/register">注册</a>
        <a class="login" href="${host}/user/login">登录</a>
    </nav>
</header>
<div class="container">
    <div class="big-logo"></div>
    <span class="cp2-title">忘记密码</span>
    <section class="cp2">
        <ul class="step-container">
            <li class="step-past">1.输入绑定邮箱
            <span class="arrow arrow-past">
                <span class="arrow-pre"></span>
                <span class="arrow-next"></span>
            </span>
            </li>
            <li class="step-current">2.设置新密码
            <span class="arrow arrow-current">
                <span class="arrow-pre"></span>
                <span class="arrow-next"></span>
            </span>
            </li>
            <li class="step-next">3.设置成功</li>
        </ul>

        <form id="cp2From">
            <div class="form-group">
                <input type="password" class="form-control " name = "pwd" id = "pwd" placeholder="新密码">
            </div>
            <button type="submit" class="btn btn-secondary" id="cp1Btn" role="button">确认</button>
        </form>

    </section>
</div>
<script>
    var token = ${tokenJson}
    var key = '${key}'
    localStorage.setItem("token",JSON.stringify(token));
</script>
<#include "./inc/base-script.ftl">
<script src="${host}/static/common/js/jquery.validate.js"></script>
<script src="${host}/static/common/js/additional-methods.js"></script>
<script src="${host}/static/user/js/cp2.js"></script>
</body>
</html>