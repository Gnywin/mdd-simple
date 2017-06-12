<!DOCTYPE html>
<html>
<head>
    <title>猫大东的榕树下</title>
<#include "./inc/base-head.ftl">
    <link  rel="stylesheet" href="${host}/static/user/css/register.css">
    <link  rel="stylesheet" href="${host}/static/common/css/bootstrapValidator.min.css">
</head>
<body>
<header>
    <nav>
        <a href="${host}/">
            <img class="logo" src="${host}/static/img/mdd-logo.png"> </img>
            <span class="title">猫大东的榕树下</span>
        </a>
        <a class="login" href="${host}/user/login">登入</a>
    </nav>
</header>
<div class="container">
    <div class="big-logo"></div>
    <span class="register-title">注册</span>
    <section class="register">
        <form id="regForm">
            <div class="form-group">
                <input type="email" class="form-control " name ="email" id="email" placeholder="邮箱"  autocomplete="off">
            </div>
            <div class="form-inline input-group form-group">
                <input type="text" class="form-control" id="emailCode"  name="emailCode" placeholder="邮箱验证码"
                       autocomplete="off" readonly>
                <a  tabindex="0"  data-trigger="focus" class="btn input-group-addon btn-secondary" id="codeBtn" role="button"
                    data-content="喵需要一个正确的邮箱才能告诉你这段小咒语哦~">获取邮箱验证码</a>
            </div>
            <div class="form-group">
                <input type="password" class="form-control" id="pwd" name="pwd"  placeholder="密码（6-16位字符，区分大小写）">
            </div>
            <button type="submit" class="btn btn-secondary" id="registerBtn" role="button">注册</button>
        </form>

    </section>
</div>

<#include "./inc/base-script.ftl">
<script src="${host}/static/common/js/jquery.validate.js"></script>
<script src="${host}/static/common/js/additional-methods.js"></script>
<script src="${host}/static/user/js/register.js"></script>
</body>
</html>