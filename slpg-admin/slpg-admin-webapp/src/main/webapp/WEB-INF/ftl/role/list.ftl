<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>授权管理 -> 角色列表</title>
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
            <div class="row">
                <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                    <div class="widget am-cf">
                        <div class="widget-head am-cf">
                            <div class="widget-title  am-cf">角色列表</div>

                        </div>
                        <div class="widget-body  am-fr">

                            <div class="am-u-sm-12 am-u-md-6 am-u-lg-6">
                                <div class="am-form-group">
                                    <div class="am-btn-toolbar">
                                        <div class="am-btn-group am-btn-group-xs">
                                            <button title="添加" type="button"
                                                    class="am-btn am-btn-default am-btn-success"
                                                    onclick="location.href='/role/find'"><span
                                                    class="am-icon-plus"></span> 添加
                                            </button>
                                            <button title="修改" type="button" onclick="eidtCheckedData('/role/find');"
                                                    class="am-btn am-btn-default am-btn-secondary"><span
                                                    class="am-icon-save"></span> 修改
                                            </button>
                                            <button title="审核" type="button" onclick="reviewData();"
                                                    class="am-btn am-btn-default am-btn-warning"><span
                                                    class="am-icon-archive"></span> 审核
                                            </button>
                                            <button title="删除" type="button"
                                                    onclick="deleteCheckedData('/role/delete', 'pagingForm');"
                                                    class="am-btn am-btn-default am-btn-danger"><span
                                                    class="am-icon-trash-o"></span> 删除
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <form id="pagingForm" action="/role/list" method="post">
                                <input type="hidden" name="access_token" value="123"/>
                                <div class="cf">
                                    <div class="fl pl10">
                                        <div class="am-form-group tpl-table-list-select">
                                            <select data-am-selected="{btnSize: 'sm'}">
                                                <option value="option1">所有类别</option>
                                                <option value="option2">IT业界</option>
                                                <option value="option3">数码产品</option>
                                                <option value="option3">笔记本电脑</option>
                                                <option value="option3">平板电脑</option>
                                                <option value="option3">只能手机</option>
                                                <option value="option3">超极本</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="fl pl10">
                                        <div class="am-form-group tpl-table-list-select">
                                            <select data-am-selected="{btnSize: 'sm'}">
                                                <option value="option1">所有类别</option>
                                                <option value="option2">IT业界</option>
                                                <option value="option3">数码产品</option>
                                                <option value="option3">笔记本电脑</option>
                                                <option value="option3">平板电脑</option>
                                                <option value="option3">只能手机</option>
                                                <option value="option3">超极本</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="fl pl10">
                                        <div class="am-form-group tpl-table-list-select">
                                            <select data-am-selected="{btnSize: 'sm'}">
                                                <option value="option1">所有类别</option>
                                                <option value="option2">IT业界</option>
                                                <option value="option3">数码产品</option>
                                                <option value="option3">笔记本电脑</option>
                                                <option value="option3">平板电脑</option>
                                                <option value="option3">只能手机</option>
                                                <option value="option3">超极本</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="fl pl10">
                                        <div class="am-form-group tpl-table-list-select">
                                            <select data-am-selected="{btnSize: 'sm'}">
                                                <option value="option1">所有类别</option>
                                                <option value="option2">IT业界</option>
                                                <option value="option3">数码产品</option>
                                                <option value="option3">笔记本电脑</option>
                                                <option value="option3">平板电脑</option>
                                                <option value="option3">只能手机</option>
                                                <option value="option3">超极本</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="fl pl10">
                                        <div class="am-form-group tpl-table-list-select">
                                            <select data-am-selected="{btnSize: 'sm'}">
                                                <option value="option1">所有类别</option>
                                                <option value="option2">IT业界</option>
                                                <option value="option3">数码产品</option>
                                                <option value="option3">笔记本电脑</option>
                                                <option value="option3">平板电脑</option>
                                                <option value="option3">只能手机</option>
                                                <option value="option3">超极本</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="fl pl10">
                                        <div class="am-form-group tpl-table-list-select">
                                            <select data-am-selected="{btnSize: 'sm'}">
                                                <option value="option1">所有类别</option>
                                                <option value="option2">IT业界</option>
                                                <option value="option3">数码产品</option>
                                                <option value="option3">笔记本电脑</option>
                                                <option value="option3">平板电脑</option>
                                                <option value="option3">只能手机</option>
                                                <option value="option3">超极本</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="fl pl10">
                                        <div class="am-form-group tpl-table-list-select">
                                            <select data-am-selected="{btnSize: 'sm'}">
                                                <option value="option1">所有类别</option>
                                                <option value="option2">IT业界</option>
                                                <option value="option3">数码产品</option>
                                                <option value="option3">笔记本电脑</option>
                                                <option value="option3">平板电脑</option>
                                                <option value="option3">只能手机</option>
                                                <option value="option3">超极本</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="search-group cf">
                                        <div class="am-input-group am-input-group-sm tpl-form-border-form cl-p">
                                            <input type="text" class="am-form-field" placeholder="请输入搜索条件">
                                            <input type="text" class="am-form-field" placeholder="请输入搜索条件">
                                            <input type="text" class="am-form-field" placeholder="请输入搜索条件">
                                            <input type="text" class="am-form-field" placeholder="请输入搜索条件">
                                            <input type="text" class="am-form-field" placeholder="请输入搜索条件">
                                            <input type="text" class="am-form-field" placeholder="请输入搜索条件">
                                            <input type="text" class="am-form-field" placeholder="请输入搜索条件">
                                        </div>
                                    </div>
                                    <div class="search-group cf">
                                        <div class="am-input-group am-input-group-sm tpl-form-border-form cl-p">
                                            <input type="text" class="am-form-field" placeholder="请输入搜索条件">
                                            <input type="text" class="am-form-field" placeholder="请输入搜索条件">
                                            <input type="text" class="am-form-field" placeholder="请输入搜索条件">
                                            <input type="text" class="am-form-field" placeholder="请输入搜索条件">
                                            <input type="text" class="am-form-field" placeholder="请输入搜索条件">
                                            <input type="text" class="am-form-field" placeholder="请输入搜索条件">
                                            <input type="text" class="am-form-field" placeholder="请输入搜索条件">
                                            <div class="am-input-group-btn pl10">
                                                <button title="搜索查询"
                                                        class="am-btn am-btn-default am-btn-success tpl-table-list-field am-icon-search btn-search pl10"
                                                        type="button"
                                                        onclick="pagingForm('pagingForm', 1, ${limit.pageSize!'50'});"></button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="am-u-sm-12">
                                    <table width="100%"
                                           class="am-table am-table-compact am-table-striped tpl-table-black "
                                           id="example-r">
                                        <thead>
                                        <tr>
                                            <th>
                                                <label title="全选/取消" class="am-checkbox" onclick="checkedData(this)">
                                                    <input type="checkbox" data-am-ucheck>
                                                </label>
                                            </th>
                                            <th>ID</th>
                                            <th>编号</th>
                                            <th>名称</th>
                                            <th>状态</th>
                                            <th>创建</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <#if models?? && (models?size>0)>
                                            <#list models as model>
                                            <tr class="gradeX">
                                                <td>
                                                    <label title="勾选/取消" class="am-checkbox am-secondary">
                                                        <input type="checkbox" value="${model.id!'0'}" data-am-ucheck>
                                                    </label>
                                                </td>
                                                <td>${model.id!''}</td>
                                                <td>${model.code!''}</td>
                                                <td>${model.name!''}</td>
                                                <td>
                                                    <#if model.state == 1>
                                                        正常
                                                    <#else>
                                                        禁用
                                                    </#if>
                                                </td>
                                                <td><@gtm8 time="${model.ctime}"/></td>
                                                <td>
                                                    <div class="tpl-table-black-operation">
                                                        <a href="/role/find?pojo[id]=${model.id!'0'}" title="授权">
                                                            <i class="am-icon-key"></i> 授权
                                                        </a>
                                                        <a href="/role/find?pojo[id]=${model.id!'0'}" title="修改">
                                                            <i class="am-icon-pencil"></i> 修改
                                                        </a>
                                                        <a href="javascript:void(0);" title="删除"
                                                           class="tpl-table-black-operation-del"
                                                           onclick="deleteCheckedDataById('/role/delete', 'pagingForm', ${model.id!'0'});">
                                                            <i class="am-icon-trash"></i> 删除
                                                        </a>
                                                    </div>
                                                </td>
                                            </tr>
                                            </#list>
                                        <#else>
                                        <tr class="gradeX">
                                            <td colspan="7" align="center" class="center">暂无数据</td>
                                        </tr>
                                        </#if>
                                        <!-- more data -->
                                        </tbody>
                                    </table>
                                </div>
                            <@pagination fn="pagingForm" pageNo="${limit.pageNo!'0'}" pageSize="${limit.pageSize!'0'}" pageNumber="${limit.pageNumber!'0'}" />
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