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
                                            <button type="button" class="am-btn am-btn-default am-btn-success" onclick="location.href='/role/find'"><span class="am-icon-plus"></span> 添加</button>
                                            <button type="button" class="am-btn am-btn-default am-btn-secondary"><span class="am-icon-save"></span> 修改</button>
                                            <button type="button" class="am-btn am-btn-default am-btn-warning"><span class="am-icon-archive"></span> 审核</button>
                                            <button type="button" class="am-btn am-btn-default am-btn-danger"><span class="am-icon-trash-o"></span> 删除</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <form id="form" method="post">
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
                                                <button class="am-btn am-btn-default am-btn-success tpl-table-list-field am-icon-search btn-search pl10" type="button"></button>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </form>

                            <div class="am-u-sm-12">
                                <table width="100%" class="am-table am-table-compact am-table-striped tpl-table-black " id="example-r">
                                    <thead>
                                    <tr>
                                        <th>文章标题</th>
                                        <th>作者</th>
                                        <th>时间</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr class="gradeX">
                                        <td>Amaze UI 模式窗口</td>
                                        <td>张鹏飞</td>
                                        <td>2016-09-26</td>
                                        <td>
                                            <div class="tpl-table-black-operation">
                                                <a href="javascript:;">
                                                    <i class="am-icon-pencil"></i> 编辑
                                                </a>
                                                <a href="javascript:;" class="tpl-table-black-operation-del">
                                                    <i class="am-icon-trash"></i> 删除
                                                </a>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr class="even gradeC">
                                        <td>有适配微信小程序的计划吗</td>
                                        <td>天纵之人</td>
                                        <td>2016-09-26</td>
                                        <td>
                                            <div class="tpl-table-black-operation">
                                                <a href="javascript:;">
                                                    <i class="am-icon-pencil"></i> 编辑
                                                </a>
                                                <a href="javascript:;" class="tpl-table-black-operation-del">
                                                    <i class="am-icon-trash"></i> 删除
                                                </a>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr class="gradeX">
                                        <td>请问有没有amazeui 分享插件</td>
                                        <td>王宽师</td>
                                        <td>2016-09-26</td>
                                        <td>
                                            <div class="tpl-table-black-operation">
                                                <a href="javascript:;">
                                                    <i class="am-icon-pencil"></i> 编辑
                                                </a>
                                                <a href="javascript:;" class="tpl-table-black-operation-del">
                                                    <i class="am-icon-trash"></i> 删除
                                                </a>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr class="even gradeC">
                                        <td>关于input输入框的问题</td>
                                        <td>着迷</td>
                                        <td>2016-09-26</td>
                                        <td>
                                            <div class="tpl-table-black-operation">
                                                <a href="javascript:;">
                                                    <i class="am-icon-pencil"></i> 编辑
                                                </a>
                                                <a href="javascript:;" class="tpl-table-black-operation-del">
                                                    <i class="am-icon-trash"></i> 删除
                                                </a>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr class="even gradeC">
                                        <td>有没有发现官网上的下载包不好用</td>
                                        <td>醉里挑灯看键</td>
                                        <td>2016-09-26</td>
                                        <td>
                                            <div class="tpl-table-black-operation">
                                                <a href="javascript:;">
                                                    <i class="am-icon-pencil"></i> 编辑
                                                </a>
                                                <a href="javascript:;" class="tpl-table-black-operation-del">
                                                    <i class="am-icon-trash"></i> 删除
                                                </a>
                                            </div>
                                        </td>
                                    </tr>

                                    <tr class="even gradeC">
                                        <td>我建议WEB版本文件引入问题</td>
                                        <td>罢了</td>
                                        <td>2016-09-26</td>
                                        <td>
                                            <div class="tpl-table-black-operation">
                                                <a href="javascript:;">
                                                    <i class="am-icon-pencil"></i> 编辑
                                                </a>
                                                <a href="javascript:;" class="tpl-table-black-operation-del">
                                                    <i class="am-icon-trash"></i> 删除
                                                </a>
                                            </div>
                                        </td>
                                    </tr>
                                    <!-- more data -->
                                    </tbody>
                                </table>
                            </div>
                            <@page pageFun="paging" pageNo="10" pageNumber="20" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<#include "../script2.ftl"/>
<script>
    function paging(pageNo) {
        alert(pageNo);
    }
</script>
</body>
</html>