<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>账号设置</title>
<#include "../script.ftl"/>
    <script>
        jQuery(document).ready(function () {
            jQuery("#headimg").takungaeImgup({
                upPath: "/user/file/upload",
                delPath: "/user/file/delete",
                inputName: "headimg",
                attrData: {"nickname": "test"},
                imageNum: 1,
                maxSizeKb: 500, // 单位/KB
                fileType: ["jpg", "jpeg", "png"],
            });
        });
    </script>
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
                            <form id="form" class="am-form tpl-form-border-form">
                            <@formToken/>
                                <div class="am-form-group">
                                    <label for="mobile" class="am-u-sm-12 am-form-label am-text-left">手机 <span
                                            class="tpl-form-line-small-title">Mobile</span></label>
                                    <div class="am-u-sm-12">
                                        <input type="text" class="tpl-form-input am-margin-top-xs" id="mobile"
                                               placeholder="请输入手机" disabled value="${user.mobile!''}">
                                        <small style="color: #ff5588">手机不能修改。</small>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label for="nickname" class="am-u-sm-12 am-form-label am-text-left">昵称 <span
                                            class="tpl-form-line-small-title">Nickname</span></label>
                                    <div class="am-u-sm-12">
                                        <input type="text" class="tpl-form-input am-margin-top-xs" id="nickname"
                                               name="pojo[nickname]" placeholder="请输入昵称" maxlength="8"
                                               value="${user.nickname!''}">
                                        <small style="color: #ff5588">请输入昵称，8个字以内。</small>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label for="headimg" class="am-u-sm-12 am-form-label am-text-left">头像 <span
                                            class="tpl-form-line-small-title">Head Image</span></label>
                                    <div class="am-u-sm-12">
                                    <#if user.headimg??>
                                        <section class=" img-section">
                                            <p class="up-p"><span class="up-span"></span></p>
                                            <div class="z_photo upimg-div clear" style="margin-right: 10px;">
                                                <section class="up-section fl">
                                                    <span class="up-span"></span>
                                                    <img class="close-upimg" style="cursor: pointer" title="删除图片"
                                                         onclick="deleteFile('${user.headimg!''}', 1, '/user/file/delete');"
                                                         src="<@OSSDomain />/plugin/imgUp/img/a7.png">
                                                    <img class="up-img" src="${user.headimg!''}">
                                                    <p class="img-name-p">${user.headimg!''}</p>
                                                    <input id="taglocation" name="taglocation" value="" type="hidden">
                                                    <input id="tags" name="tags" value="" type="hidden">
                                                    <input style="display:none;" name="pojo[headimg]"
                                                           value="${user.headimg!''}" sign="${user.headimg!''}"
                                                           type="text">
                                                </section>
                                                <section class="z_file fl" style="display: none;">
                                                    <img src="<@OSSDomain />/plugin/imgUp/img/a11.png" class="add-img">
                                                    <input id="headimg" class="file" value=""
                                                           accept="image/jpg,image/jpeg,image/png" type="file">
                                                </section>
                                            </div>
                                        </section>
                                    <#else>
                                        <section class=" img-section">
                                            <p class="up-p"><span class="up-span"></span></p>
                                            <div class="z_photo upimg-div clear" style="margin-right: 10px;">
                                                <section class="z_file fl">
                                                    <img src="<@OSSDomain />/plugin/imgUp/img/a11.png"
                                                         class="add-img">
                                                    <input type="file" id="headimg" class="file" value=""
                                                           accept="image/jpg,image/jpeg,image/png"/>
                                                </section>
                                            </div>
                                        </section>
                                    </#if>
                                        <small style="color: #ff5588">备注: 建议上传一张规格宽高相同的图片(100*100)</small>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <div class="am-u-sm-12 am-u-sm-push-12">
                                        <button type="button"
                                                onclick="submitByAjax('#form','/user/editMy',true)"
                                                class="am-btn am-btn-primary tpl-btn-bg-color-success ">
                                            提交
                                        </button>
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