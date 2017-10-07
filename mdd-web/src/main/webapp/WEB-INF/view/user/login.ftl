<!DOCTYPE html>
<html>
<head>
    <title>猫大东的榕树下</title>
<#include "./inc/base-head.ftl">
    <link  rel="stylesheet" href="${host}/static/user/css/login.css">
    <link  rel="stylesheet" href="${host}/static/common/css/bootstrapValidator.min.css">
</head>
<body>
<header>
    <nav>
        <a href="${host}/">
            <img class="logo" src="${host}/static/img/mdd-logo.png"> </img>
            <span class="title">猫大东的榕树下</span>
        </a>
        <a class="register" href="${host}/user/register">注册</a>
    </nav>
</header>
<div class="container">
    <div class="big-logo"></div>
    <span class="login-title">登录</span>
    <section class="login">
        <form id="loginForm">
            <div class="form-group">
                <input type="email" class="form-control " name ="email" id="email" placeholder="邮箱"  autocomplete="off">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" id="pwd" name="pwd"  placeholder="密码（6-16位字符，区分大小写）">
            </div>
            <div class="form-group" id="verify">
                <input type="text" class="form-control" id="code" name="code" placeholder="验证码">
                <img id="verifyCode">
            </div>
            <div class="form-group row">
                <div class="col-sm-12">
                    <div class="form-check">
                        <label class="form-check-label">
                            <input class="form-check-input" name="remember" id="remember" type="checkbox">自动登录
                        </label>
                        <a id="forget">忘记密码</a>
                    </div>
                </div>
            </div>
            <button type="submit" class="btn btn-secondary" id="loginBtn" role="button">登录</button>
        </form>

    </section>
</div>

<#include "./inc/base-script.ftl">
<script src="${host}/static/common/js/jquery.validate.js"></script>
<script src="${host}/static/common/js/additional-methods.js"></script>
<script src="${host}/static/user/js/login.js"></script>
</body>
</html>