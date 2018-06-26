<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>系统错误 - 500</title>
<#include "../script.ftl"/>
</head>

<body data-type="widgets">
<script src="<@OSSDomain />/assets/js/theme.js"></script>
<#include "../modal.ftl"/>
<div class="am-g tpl-g">
    <!-- 头部 -->
<#include "../top.ftl"/>
    <!-- 风格切换 -->
<#include "../skiner.ftl"/>
    <!-- 侧边导航栏 -->
<#include "../left.ftl"/>
    <!-- 内容区域 -->
    <div class="tpl-content-wrapper">
        <div class="row-content am-cf">
            <div class="widget am-cf">
                <div class="widget-body">
                    <div class="tpl-page-state">
                        <div class="tpl-page-state-title am-text-center tpl-error-title">500</div>
                        <div class="tpl-error-title-info">Internal Server Error</div>
                        <div class="tpl-page-state-content tpl-error-content">
                            <p>对不起，服务器发生一些未知异常错误，请您稍后再尝试访问。</p>
                            <p>&nbsp;</p>
                            <button type="button" class="am-btn am-btn-secondary am-radius tpl-error-btn" onclick="location.href='/user/index'">Back Home
                            </button>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<#include "../script2.ftl"/>
</body>
</html>