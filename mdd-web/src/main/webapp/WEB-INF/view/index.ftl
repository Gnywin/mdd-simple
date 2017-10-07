<!DOCTYPE html>
<html>
<head>
    <title>猫大东的榕树下</title>
    <#include "./inc/base-head.ftl">
    <link  rel="stylesheet" href="${host}/static/index/css/index.css">
</head>
<body>
<header>
    <nav>
        <a href="./">
            <img class="logo" src="${host}/static/img/mdd-logo.png"/>
            <span class="title">猫大东的榕树下</span>
        </a>
        <a class="login" href="${host}/user/login">登录</a>
        <a class="register" href="${host}/user/register">注册</a>
        <a class="logout" href="${host}/user/logout">登出</a>
    </nav>
</header>
<div class="container">
        <div class="big-logo"></div>
        <section class="danmaku">
            <p>弹幕走起来</p>
            <p>弹幕走起来</p>
        </section>
        <section class="essay">
            <div class="essay_search">
                <div class="input-group">
                    <input type="text" class="form-control" id="word" name="word" placeholder="Search for..."
                           aria-label="Search for...">
                    <span class="input-group-btn">
                        <button class="btn btn-secondary" type="button">Go!</button>
                    </span>
                </div>
            </div>
            <div class="essay_list">
                <div class="list-group">
                    <a class="list-group-item list-group-item-action flex-column align-items-start">
                        <div class="d-flex w-100 justify-content-between">
                            <h5 class="mb-1">List group item heading</h5>
                            <small>3 days ago</small>
                        </div>
                        <p class="mb-1">Donec id elit non mi porta gravida at eget metus. Maecenas sed diam eget risus varius blandit.</p>
                        <div class="input-group">
                            <input type="text" class="form-control" id="word" name="word" placeholder="喵一声,回复一下"
                                   aria-label="喵一声,回复一下">
                            <span class="input-group-btn">
                                 <button class="btn btn-secondary" type="button">喵</button>
                            </span>
                       </div>
                    </a>
                    <a class="list-group-item list-group-item-action flex-column align-items-start">
                        <div class="d-flex w-100 justify-content-between">
                            <h5 class="mb-1">List group item heading</h5>
                            <small class="text-muted">3 days ago</small>
                        </div>
                        <p class="mb-1">Donec id elit non mi porta gravida at eget metus. Maecenas sed diam eget risus varius blandit.</p>
                        <div class="input-group">
                            <input type="text" class="form-control" id="word" name="word" placeholder="喵一声,回复一下"
                                   aria-label="喵一声,回复一下">
                            <span class="input-group-btn">
                                 <button class="btn btn-secondary" type="button">喵</button>
                            </span>
                        </div>
                    </a>
                    <a  class="list-group-item list-group-item-action flex-column align-items-start">
                        <div class="d-flex w-100 justify-content-between">
                            <h5 class="mb-1">List group item heading</h5>
                            <small class="text-muted">3 days ago</small>
                        </div>
                        <p class="mb-1">Donec id elit non mi porta gravida at eget metus. Maecenas sed diam eget risus varius blandit.</p>
                    </a>
                </div>
            </div>
        </section>

</div>
<footer>
    <div class="say">

 <#--       <div class="round-mid">
        </div>-->
        <button class="round" id="i-say">吐槽</button>


    </div>
    <div class="say-bar">
        <div class="say-text">
            <textarea></textarea>
        </div>
        <div class="say-tools">

        </div>
        <div class="say-action">
            <button type="button" class="btn btn-light">发射</button>
        </div>
    </div>
</footer>

<#include "./inc/base-script.ftl">
<script src="${host}/static/index/js/index.js"></script>
</body>
</html>