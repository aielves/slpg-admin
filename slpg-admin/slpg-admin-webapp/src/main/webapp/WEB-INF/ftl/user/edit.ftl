<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
<#if model.id??>
    <title>授权管理 -> 用户列表 -> 修改用户</title>
<#else>
    <title>授权管理 -> 用户列表 -> 添加用户</title>
</#if>
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
        <div class="container-fluid am-cf">
            <div class="row">
                <div class="am-u-sm-12 am-u-md-12 am-u-lg-9">
                    <div class="page-header-heading"><span class="am-icon-home page-header-heading-icon"></span> 授权管理 ->
                        用户列表
                    <#if model.id??>
                        <small> -> 修改用户</small>
                    <#else>
                        <small> -> 添加用户</small>
                    </#if>
                    </div>
                    <p class="page-header-description">新增用户账号数据。</p>
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
                            <div class="widget-title am-fl">请谨慎新增用户账号数据。</div>
                            <div class="widget-function am-fr">
                                <a href="javascript:;" class="am-icon-cog"></a>
                            </div>
                        </div>
                        <div class="widget-body am-fr">
                            <form id="form" class="am-form tpl-form-border-form">
                            <@formToken/>
                                <input type="hidden" name="pojo[id]" value="${model.id!''}"/>
                                <div class="am-form-group">
                                    <label for="username" class="am-u-sm-12 am-form-label am-text-left">手机号码 <span
                                            class="tpl-form-line-small-title">Mobile</span></label>
                                    <div class="am-u-sm-12">
                                        <input type="text" class="tpl-form-input am-margin-top-xs"
                                               name="pojo[mobile]"
                                               placeholder="请输入手机号码" value="${model.mobile!''}"
                                               <#if model.id??>readonly="readonly"</#if> maxlength="20">
                                        <small style="color: #ff5588">请输入手机号码，作为登陆账号。</small>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label for="username" class="am-u-sm-12 am-form-label am-text-left">登录密码 <span
                                            class="tpl-form-line-small-title">Password</span></label>
                                    <div class="am-u-sm-12">
                                        <input type="password" class="tpl-form-input am-margin-top-xs"
                                               name="pojo[password]"
                                               placeholder="请输入登录密码" value="" maxlength="20">
                                        <small style="color: #ff5588">请输入登录密码，6-18位。</small>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label for="username" class="am-u-sm-12 am-form-label am-text-left">确认密码 <span
                                            class="tpl-form-line-small-title">Confirm Password</span></label>
                                    <div class="am-u-sm-12">
                                        <input type="password" class="tpl-form-input am-margin-top-xs"
                                               placeholder="请输入确认密码" value="" name="confirmPassword" maxlength="20">
                                        <small style="color: #ff5588">请再次输入您的登录密码，6-18位。</small>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label for="username" class="am-u-sm-12 am-form-label am-text-left">用户昵称 <span
                                            class="tpl-form-line-small-title">Nickname</span></label>
                                    <div class="am-u-sm-12">
                                        <input type="text" class="tpl-form-input am-margin-top-xs"
                                               placeholder="请输入用户昵称" value="${model.nickname!''}" name="pojo[nickname]" maxlength="10">
                                        <small style="color: #ff5588">请输入用户昵称，10个字以内。</small>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label for="sex" class="am-u-sm-12 am-form-label am-text-left">账号类型 <span
                                            class="tpl-form-line-small-title">Account Type</span></label>
                                    <div class="am-u-sm-12  am-margin-top-xs">
                                        <select name="pojo[utype]" data-am-selected="{searchBox: 0}"
                                                style="display: none;">
                                            <option value=""></option>
                                            <option value="2" <#if model.utype?? && model.utype==2>selected</#if>>管理用户
                                            </option>
                                            <option value="3" <#if model.utype?? && model.utype==3>selected</#if>>普通用户
                                            </option>
                                        </select>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label for="sex" class="am-u-sm-12 am-form-label am-text-left">使用状态 <span
                                            class="tpl-form-line-small-title">Use State</span></label>
                                    <div class="am-u-sm-12  am-margin-top-xs">
                                        <select name="pojo[state]" data-am-selected="{searchBox: 0}"
                                                style="display: none;">
                                            <option value="1" <#if model.state?? && model.state==1>selected</#if>>正常
                                            </option>
                                            <option value="2" <#if model.state?? && model.state==2>selected</#if>>禁用
                                            </option>
                                        </select>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <div class="am-u-sm-12 am-u-sm-push-12">
                                        <button type="button"
                                                onclick="submitByAjax('#form','/user/save',true)"
                                                class="am-btn am-btn-primary tpl-btn-bg-color-success ">
                                            提交
                                        </button>
                                        <a href="/user/list" class="am-btn am-btn-link">返回</a>
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
<#include "../script2.ftl"/>
</body>
</html>