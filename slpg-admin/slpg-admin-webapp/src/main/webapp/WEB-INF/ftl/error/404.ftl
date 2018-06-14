<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>系统错误 - 404</title>
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
                        <div class="tpl-page-state-title am-text-center tpl-error-title">404</div>
                        <div class="tpl-error-title-info">Page Not Found</div>
                        <div class="tpl-page-state-content tpl-error-content">
                            <p>对不起,没有找到您所需要的页面,可能是URL不确定,或者页面已被移除。</p>
                            <p>&nbsp;</p>
                            <button type="button" class="am-btn am-btn-secondary am-radius tpl-error-btn">Back Home
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