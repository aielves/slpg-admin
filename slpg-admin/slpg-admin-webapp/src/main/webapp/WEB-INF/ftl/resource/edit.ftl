<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
<#if model.id??>
    <title>授权管理 -> 资源列表 -> 修改资源</title>
<#else>
    <title>授权管理 -> 资源列表 -> 添加资源</title>
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
                        资源列表
                    <#if model.id??>
                        <small> -> 修改资源</small>
                    <#else>
                        <small> -> 添加资源</small>
                    </#if>
                    </div>
                    <p class="page-header-description">新增定义用户资源权限。</p>
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
                            <div class="widget-title am-fl">请谨慎新增定义您的资源权限。</div>
                            <div class="widget-function am-fr">
                                <a href="javascript:;" class="am-icon-cog"></a>
                            </div>
                        </div>
                        <div class="widget-body am-fr">
                            <form id="form" class="am-form tpl-form-border-form">
                            <@formToken/>
                                <input type="hidden" id="id" name="pojo[id]" value="${model.id!''}"/>
                                <input type="hidden" id="code" name="pojo[code]" value="${model.code!''}"/>
                                <div class="am-form-group">
                                    <label for="username" class="am-u-sm-12 am-form-label am-text-left">资源名称 <span
                                            class="tpl-form-line-small-title">Resource Name</span></label>
                                    <div class="am-u-sm-12">
                                        <input type="text" class="tpl-form-input am-margin-top-xs" id="name"
                                               name="pojo[name]"
                                               placeholder="请输入资源名称" value="${model.name!''}">
                                        <small style="color: #ff5588">请输入资源名称，8个字以内。</small>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label for="username" class="am-u-sm-12 am-form-label am-text-left">资源路径 <span
                                            class="tpl-form-line-small-title">Resource Url</span></label>
                                    <div class="am-u-sm-12">
                                        <input type="text" class="tpl-form-input am-margin-top-xs" id="url"
                                               name="pojo[url]"
                                               placeholder="请输入资源路径" value="${model.url!''}">
                                        <small style="color: #ff5588">请输入资源路径，资源请求访问地址。</small>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label for="username" class="am-u-sm-12 am-form-label am-text-left">资源序号 <span
                                            class="tpl-form-line-small-title">Resource Order</span></label>
                                    <div class="am-u-sm-12">
                                        <input type="text" class="tpl-form-input am-margin-top-xs" id="orderno"
                                               name="pojo[orderno]"
                                               placeholder="请输入资源序号" value="${model.orderno!''}">
                                        <small style="color: #ff5588">请输入资源序号，资源排列序号。</small>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label for="sex" class="am-u-sm-12 am-form-label am-text-left">资源类型 <span
                                            class="tpl-form-line-small-title">Resource Type</span></label>
                                    <div class="am-u-sm-12  am-margin-top-xs">
                                        <select id="type" name="pojo[type]" data-am-selected="{searchBox: 0}"
                                                style="display: none;">
                                            <option value="">-请选择资源类型
                                            </option>
                                            <option value="1" <#if model.state?? && model.state==1>selected</#if>>-大模块栏目
                                            </option>
                                            <option value="2" <#if model.state?? && model.state==2>selected</#if>>-小模块栏目
                                            </option>
                                            <option value="3" <#if model.state?? && model.state==3>selected</#if>>-分模块资源
                                            </option>
                                        </select>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label for="sex" class="am-u-sm-12 am-form-label am-text-left">使用状态 <span
                                            class="tpl-form-line-small-title">Use State</span></label>
                                    <div class="am-u-sm-12  am-margin-top-xs">
                                        <select id="state" name="pojo[state]" data-am-selected="{searchBox: 0}"
                                                style="display: none;">
                                            <option value="1" <#if model.state?? && model.state==1>selected</#if>>-正常
                                            </option>
                                            <option value="2" <#if model.state?? && model.state==2>selected</#if>>-禁用
                                            </option>
                                        </select>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <div class="am-u-sm-12 am-u-sm-push-12">
                                        <button type="button"
                                                onclick="submitByAjax('#form','/resource/save',true)"
                                                class="am-btn am-btn-primary tpl-btn-bg-color-success ">
                                            提交
                                        </button>
                                        <a href="/resource/list" class="am-btn am-btn-link">返回</a>
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