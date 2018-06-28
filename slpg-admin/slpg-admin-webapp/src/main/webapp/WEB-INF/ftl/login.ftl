<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>登录</title>
<#include "script.ftl"/>

</head>

<body data-type="login">
<script src="<@OSSDomain />/assets/js/theme.js"></script>
<div class="am-g tpl-g">
<#include "modal.ftl">
    <!-- 风格切换 -->
    <div class="tpl-skiner">
        <div class="tpl-skiner-toggle am-icon-cog">
        </div>
        <div class="tpl-skiner-content">
            <div class="tpl-skiner-content-title">
                选择主题
            </div>
            <div class="tpl-skiner-content-bar">
                <span class="skiner-color skiner-white" data-color="theme-white"></span>
                <span class="skiner-color skiner-black" data-color="theme-black"></span>
            </div>
        </div>
    </div>
    <div class="tpl-login">
        <div class="tpl-login-content">
            <#--<div class="tpl-login-logo">
            </div>-->

            <form id="form" class="am-form tpl-form-line-form">
                <div class="am-form-group">
                    <input type="text" class="tpl-form-input" name="username" maxlength="18"
                           placeholder="请输入账号" value="13823912345">
                </div>
                <div class="am-form-group">
                    <input type="password" class="tpl-form-input" name="password" maxlength="18"
                           placeholder="请输入密码" value="123456">
                </div>
                <div class="am-form-group tpl-login-remember-me">
                    <input id="remember-me" type="checkbox">
                    <label for="remember-me">
                        <span>记住密码</span>
                    </label>
                    <label style="margin-left: 90px;">
                        <a href="/user/signupInit">注册账号</a> | <a href="#">忘记密码?</a>
                    </label>
                </div>
                <div class="am-form-group tpl-login-remember-me">

                </div>
                <div class="am-form-group">
                    <button type="button" onclick="submitByAjax('#form', '/user/login', false)"
                            class="am-btn am-btn-primary  am-btn-block tpl-btn-bg-color-success  tpl-login-btn">提交
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="<@OSSDomain />/assets/js/amazeui.min.js"></script>
<script src="<@OSSDomain />/assets/js/app.js"></script>
</body>
</html>