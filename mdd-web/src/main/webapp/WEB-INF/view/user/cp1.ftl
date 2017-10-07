<!DOCTYPE html>
<html>
<head>
    <title>猫大东的榕树下</title>
<#include "./inc/base-head.ftl">
    <link  rel="stylesheet" href="${host}/static/user/css/cp1.css">
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
    <span class="cp1-title">忘记密码</span>
    <section class="cp1">
        <ul class="step-container">
            <li class="step-current">1.输入绑定邮箱
            <span class="arrow arrow-current">
                <span class="arrow-pre"></span>
                <span class="arrow-next"></span>
            </span>
            </li>
            <li class="step-next">2.设置新密码
            <span class="arrow arrow-next">
                <span class="arrow-pre"></span>
                <span class="arrow-next"></span>
            </span>
            </li>
            <li class="step-next">3.设置成功</li>
        </ul>

        <form id="cp1From">
            <div class="form-group">
                <input type="email" class="form-control " name ="email" id="email" placeholder="邮箱"  autocomplete="off">
            </div>
            <div class="form-group">
                <img id="verifyCode" style="margin-bottom: 5px">
                <input type="text" style="display: inline-block;width: 150px" class="form-control" id="code" name="code" placeholder="验证码">
            </div>
            <button type="submit" class="btn btn-secondary" id="cp1Btn" role="button">发送验证码</button>
        </form>

    </section>
</div>

<#include "./inc/base-script.ftl">
<script src="${host}/static/common/js/jquery.validate.js"></script>
<script src="${host}/static/common/js/additional-methods.js"></script>
<script src="${host}/static/user/js/cp1.js"></script>
</body>
</html>