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
                                    <label for="sex" class="am-u-sm-12 am-form-label am-text-left">资源类型 <span
                                            class="tpl-form-line-small-title">Resource Type</span></label>
                                    <div class="am-u-sm-12  am-margin-top-xs">
                                        <select id="type" name="pojo[type]" data-am-selected="{searchBox: 0}"
                                                style="display: none;" onchange="type_change(this);">
                                            <option value="">
                                            </option>
                                            <option value="1" <#if model.type?? && model.type==1>selected</#if>>-大模块栏目
                                            </option>
                                            <option value="2" <#if model.type?? && model.type==2>selected</#if>>-小模块栏目
                                            </option>
                                            <option value="3" <#if model.type?? && model.type==3>selected</#if>>-节点块资源
                                            </option>
                                        </select>
                                    </div>
                                </div>
                                <div class="am-form-group" id="am-form-group-level1" style="display: none;">
                                    <label for="sex" class="am-u-sm-12 am-form-label am-text-left">所属大模块 <span
                                            class="tpl-form-line-small-title">Resource Level 1</span></label>
                                    <div class="am-u-sm-12  am-margin-top-xs">
                                        <select id="am-form-group-level1-select" name="pid1"
                                                data-am-selected="{searchBox: 0}"
                                                style="display: none;" onchange="level1_change(this)">
                                        </select>
                                    </div>
                                </div>
                                <div class="am-form-group" id="am-form-group-level2" style="display: none;">
                                    <label for="sex" class="am-u-sm-12 am-form-label am-text-left">所属小模块 <span
                                            class="tpl-form-line-small-title">Resource Level 2</span></label>
                                    <div class="am-u-sm-12  am-margin-top-xs">
                                        <select id="am-form-group-level2-select" name="pid2"
                                                data-am-selected="{searchBox: 0}"
                                                style="display: none;">
                                        </select>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label for="username" class="am-u-sm-12 am-form-label am-text-left">资源名称 <span
                                            class="tpl-form-line-small-title">Resource Name</span></label>
                                    <div class="am-u-sm-12">
                                        <input type="text" class="tpl-form-input am-margin-top-xs" id="name"
                                               name="pojo[name]"
                                               placeholder="请输入资源名称" value="${model.name!''}">
                                        <small style="color: #ff5588">请输入资源名称，10个字以内。</small>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label for="username" class="am-u-sm-12 am-form-label am-text-left">资源简介 <span
                                            class="tpl-form-line-small-title">Resource Resume</span></label>
                                    <div class="am-u-sm-12">
                                        <input type="text" class="tpl-form-input am-margin-top-xs" id="url"
                                               name="pojo[resume]"
                                               placeholder="请输入资源简介" value="${model.resume!''}">
                                        <small style="color: #ff5588">请输入资源简介,50个字以内(可不填写)。</small>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label for="username" class="am-u-sm-12 am-form-label am-text-left">资源路径 <span
                                            class="tpl-form-line-small-title">Resource Url</span></label>
                                    <div class="am-u-sm-12">
                                        <input type="text" class="tpl-form-input am-margin-top-xs" id="url"
                                               name="pojo[url]"
                                               placeholder="请输入资源路径" value="${model.url!''}">
                                        <small style="color: #ff5588">请输入资源访问地址,资源类型为大小栏目可不填写。</small>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label for="sex" class="am-u-sm-12 am-form-label am-text-left">优先级别 <span
                                            class="tpl-form-line-small-title">Resource Order</span></label>
                                    <div class="am-u-sm-12  am-margin-top-xs">
                                        <select id="orderno" name="pojo[orderno]" data-am-selected="{searchBox: 0}"
                                                style="display: none;">
                                            <option value="">
                                            </option>
                                            <option value="1" <#if model.orderno?? && model.orderno ==1>selected</#if>>1
                                            </option>
                                            <option value="2" <#if model.orderno?? && model.orderno==2>selected</#if>>2
                                            </option>
                                            <option value="3" <#if model.orderno?? && model.orderno==3>selected</#if>>3
                                            </option>
                                            <option value="4" <#if model.orderno?? && model.orderno==4>selected</#if>>4
                                            </option>
                                            <option value="5" <#if model.orderno?? && model.orderno==5>selected</#if>>5
                                            </option>
                                            <option value="6" <#if model.orderno?? && model.orderno==6>selected</#if>>6
                                            </option>
                                            <option value="7" <#if model.orderno?? && model.orderno==7>selected</#if>>7
                                            </option>
                                            <option value="8" <#if model.orderno?? && model.orderno==8>selected</#if>>8
                                            </option>
                                            <option value="9" <#if model.orderno?? && model.orderno==9>selected</#if>>9
                                            </option>
                                            <option value="10" <#if model.orderno?? && model.orderno==10>selected</#if>>
                                                10
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
<#include "../script2.ftl"/>
<script>
    function type_change(obj) {
        var level1 = $("#am-form-group-level1-select");
        var level2 = $("#am-form-group-level2-select");
        level1.html("<option value=''></option>");
        level2.html("<option value=''></option>");
        var type = $(obj).val();
        if (type == "1") { // 所属为大模块屏蔽2,3级资源
            $("#am-form-group-level1").hide();
            $("#am-form-group-level2").hide();
        } else {
            $("#am-form-group-level1").show(); // 非1级资源则显示1级资源列表
            $.post("/resource/findByPid", "pojo[pid]=" + 0, function (json) {
                if (json.code == "000000") {
                    $.each(json.data, function (i, v) {
                        level1.append("<option value='" + v.id + "'>" + v.name + "</option>")
                    });
                }
            });
            if (type == "2") { // 如是2级资源则屏蔽3级资源
                $("#am-form-group-level2").hide();
            } else { // 如是3级资源则显示3级资源
                $("#am-form-group-level2").show();
            }
        }
    }

    function level1_change(obj) {
        var level2 = $("#am-form-group-level2-select");
        level2.html("<option value=''></option>");
        var type_val = $("#type").val();
        var level1_val = $(obj).val();
        if (type_val == "3" && level1_val != "") {
            $("#am-form-group-level2").show(); // 显示2级资源
            $.post("/resource/findByPid", "pojo[pid]=" + level1_val, function (json) {
                if (json.code == "000000") {
                    $.each(json.data, function (i, v) {
                        level2.append("<option value='" + v.id + "'>" + v.name + "</option>")
                    });
                }
            });
        }
    }
</script>
</body>
</html>