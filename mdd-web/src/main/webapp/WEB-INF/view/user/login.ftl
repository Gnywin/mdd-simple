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
        <a class="register" href="${host}/user/register">注册</a>
    </nav>
</header>
<div class="container">
    <div class="big-logo"></div>
    <span class="register-title">登入</span>
    <section class="register">
        <form id="regForm">
            <div class="form-group">
                <input type="email" class="form-control " name ="email" id="email" placeholder="邮箱"  autocomplete="off">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" id="pwd" name="pwd"  placeholder="密码（6-16位字符，区分大小写）">
            </div>
            <div class="form-group row">
                <div class="col-sm-12">
                    <div class="form-check">
                        <label class="form-check-label">
                            <input class="form-check-input" type="checkbox">自动登入
                        </label>
                    </div>
                </div>
            </div>
            <button type="submit" class="btn btn-secondary" id="loginBtn" role="button">注册</button>
        </form>

    </section>
</div>

<#include "./inc/base-script.ftl">
<script src="${host}/static/common/js/jquery.validate.js"></script>
<script src="${host}/static/common/js/additional-methods.js"></script>
<script src="${host}/static/user/js/register.js"></script>
</body>
</html>