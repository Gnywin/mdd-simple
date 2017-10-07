<!DOCTYPE html>
<html>
<head>
    <title>哎哟！吓了喵一跳</title>
    <#include "./inc/base-head.ftl">
</head>
<body >
<div>
${mddExp.msg}
</div>
<div>出现了一些小小的意外，请勿刷新页面。系统将在自动修复页面</div>
<#include "./inc/base-script.ftl">
<script>
   var code = ${mddExp.code}
    if(code >=300 && code <= 329){
        localStorage.removeItem("token");
    }
</script>
<script src="${host}/static/error/js/error.js"></script>
</body>
</html>