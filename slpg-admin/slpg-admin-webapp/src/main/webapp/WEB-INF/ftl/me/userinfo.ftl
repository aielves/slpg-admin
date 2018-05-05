<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>狮岭皮革平台管理后台 - 主页</title>
<#include "../script.ftl"/>
</head>

<body data-type="index">
<script src="/static/assets/js/theme.js"></script>
<div class="am-g tpl-g">
    <!-- 头部 -->
<#include "../top.ftl"/>
    <!-- 风格切换 -->
<#include "../skiner.ftl"/>
    <!-- 侧边导航栏 -->
<#include "../left.ftl"/>
    <!-- 内容区域 -->
    <div class="tpl-content-wrapper">
        <div class="container-fluid am-cf">
            <div class="row">
                <div class="am-u-sm-12 am-u-md-12 am-u-lg-9">
                    <div class="page-header-heading"><span class="am-icon-home page-header-heading-icon"></span> 个人中心
                        <small>帐号设置</small>
                    </div>
                    <p class="page-header-description">用户可修改并提交个人帐号数据。</p>
                </div>
                <div class="am-u-lg-3 tpl-index-settings-button">
                    <button type="button" class="page-header-button"><span class="am-icon-paint-brush"></span> 设置
                    </button>
                </div>
            </div>

        </div>

        <div class="row-content am-cf">
            <div class="row">


                <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                    <div class="widget am-cf">
                        <div class="widget-head am-cf">
                            <div class="widget-title am-fl">请谨慎操作您的账号数据。</div>
                            <div class="widget-function am-fr">
                                <a href="javascript:;" class="am-icon-cog"></a>
                            </div>
                        </div>
                        <div class="widget-body am-fr">

                            <form class="am-form tpl-form-border-form">
                                <div class="am-form-group">
                                    <label for="username" class="am-u-sm-12 am-form-label am-text-left">账号 <span class="tpl-form-line-small-title">Account</span></label>
                                    <div class="am-u-sm-12">
                                        <input type="text" class="tpl-form-input am-margin-top-xs" id="username" placeholder="请输入账号" disabled value="${user.username!''}">
                                        <small>账号不能修改。</small>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label for="nickname" class="am-u-sm-12 am-form-label am-text-left">昵称 <span class="tpl-form-line-small-title">Nickname</span></label>
                                    <div class="am-u-sm-12">
                                        <input type="text" class="tpl-form-input am-margin-top-xs" id="nickname" name="nickname" placeholder="请输入昵称" maxlength="8" value="${user.nickname!''}">
                                        <small>请输入昵称，8个字以内。</small>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label for="sex" class="am-u-sm-12 am-form-label am-text-left">性别 <span class="tpl-form-line-small-title">Sex</span></label>
                                    <div class="am-u-sm-12  am-margin-top-xs">
                                        <select id="sex" name="sex" data-am-selected="{searchBox: 1}" style="display: none;">
                                            <option value="1">-男</option>
                                            <option value="2">-女</option>
                                        </select>

                                    </div>
                                </div>

                                <div class="am-form-group">
                                    <label class="am-u-sm-12 am-form-label  am-text-left">SEO关键字 <span class="tpl-form-line-small-title">SEO</span></label>
                                    <div class="am-u-sm-12">
                                        <input type="text" class="am-margin-top-xs" placeholder="输入SEO关键字">
                                    </div>
                                </div>

                                <div class="am-form-group">
                                    <label for="user-weibo" class="am-u-sm-12 am-form-label  am-text-left">个人头像 <span class="tpl-form-line-small-title">Images</span></label>
                                    <div class="am-u-sm-12 am-margin-top-xs">
                                        <div class="am-form-group am-form-file">
                                            <div class="tpl-form-file-img">
                                                <img src="assets/img/a5.png" alt="">
                                            </div>
                                            <button type="button" class="am-btn am-btn-danger am-btn-sm ">
                                                <i class="am-icon-cloud-upload"></i> 选择头像图片</button>
                                            <input id="doc-form-file" type="file" multiple="">
                                        </div>

                                    </div>
                                </div>

                                <div class="am-form-group">
                                    <label for="user-weibo" class="am-u-sm-12 am-form-label  am-text-left">添加分类 <span class="tpl-form-line-small-title">Type</span></label>
                                    <div class="am-u-sm-12">
                                        <input type="text" id="user-weibo" class="am-margin-top-xs" placeholder="请添加分类用点号隔开">
                                        <div>

                                        </div>
                                    </div>
                                </div>

                                <div class="am-form-group">
                                    <label for="user-intro" class="am-u-sm-12 am-form-label  am-text-left">隐藏文章</label>
                                    <div class="am-u-sm-12">
                                        <div class="tpl-switch">
                                            <input type="checkbox" class="ios-switch bigswitch tpl-switch-btn am-margin-top-xs" checked="">
                                            <div class="tpl-switch-btn-view">
                                                <div>
                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                </div>

                                <div class="am-form-group">
                                    <label for="user-intro" class="am-u-sm-12 am-form-label  am-text-left">文章内容</label>
                                    <div class="am-u-sm-12 am-margin-top-xs">
                                        <textarea class="" rows="10" id="user-intro" placeholder="请输入文章内容"></textarea>
                                    </div>
                                </div>

                                <div class="am-form-group">
                                    <div class="am-u-sm-12 am-u-sm-push-12">
                                        <button type="button" class="am-btn am-btn-primary tpl-btn-bg-color-success ">提交</button>
                                    </div>
                                </div>
                            </form>
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