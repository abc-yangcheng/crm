<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>CRM-后台权限管理</title>
    <#include "common.ftl">
    <style id="layuimini-bg-color">
    </style>
</head>
<body class="layui-layout-body layuimini-all">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header header">
        <div class="layui-logo">
            <a href="">
                <img src="images/logo.png" alt="logo">
                <h1>后台管理系统</h1>
            </a>
        </div>
        <a>
            <div class="layuimini-tool"><i title="展开" class="fa fa-outdent" data-side-fold="1"></i></div>
        </a>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item" lay-unselect>
                <a href="javascript:;" data-refresh="刷新"><i class="fa fa-refresh"></i></a>
            </li>
            <li class="layui-nav-item" lay-unselect>
                <a href="javascript:;" data-clear="清理" class="layuimini-clear"><i class="fa fa-trash-o"></i></a>
            </li>
            <li class="layui-nav-item mobile layui-hide-xs" lay-unselect>
                <a href="javascript:;" data-check-screen="full"><i class="fa fa-arrows-alt"></i></a>
            </li>
            <li class="layui-nav-item layuimini-setting">
                <a href="javascript:;">${(user.userName)!""}</a>
                <dl class="layui-nav-child">
                    <dd>
                        <a href="javascript:;" data-iframe-tab="user/toPasswordPage" data-title="修改密码" data-icon="fa fa-gears">修改密码</a>
                    </dd>
                    <dd>
                        <a href="javascript:;" data-iframe-tab="user/basic" data-title="基本资料" data-icon="fa fa-gears">基本资料<span class="layui-badge-dot"></span></a>
                    </dd>
                    <dd>
                        <hr>
                    </dd>
                    <dd>
                        <a href="javascript:" class="login-out" id="login-out">退出登录</a>
                    </dd>
                </dl>
            </li>
            <li class="layui-nav-item layuimini-select-bgcolor mobile layui-hide-xs" lay-unselect>
                <a href="javascript:;" data-bgcolor="配色方案"><i class="fa fa-ellipsis-v"></i></a>
            </li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll layui-left-menu">
<#--            判断当前登录用户是否拥有权限-->
            <#if permissions??>
                <ul class="layui-nav layui-nav-tree layui-left-nav-tree layui-this" id="currency">

                    <#-- 通过freemarker中的内建指令判断菜单是否显示-->
                        <#if permissions?seq_contains("10")>
                        <li class="layui-nav-item">
                            <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-street-view"></i><span class="layui-left-nav"> 营销管理</span> <span class="layui-nav-more"></span></a>
                            <dl class="layui-nav-child">
                        </#if>
                                <#if permissions?seq_contains("1010")>
                                    <dd>
                                        <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-1" data-tab="sale_chance/index" target="_self"><i class="fa fa-tty"></i><span class="layui-left-nav"> 营销机会管理</span></a>
                                    </dd>
                                </#if>
                                <#if permissions?seq_contains("1020")>
                                    <dd>
                                        <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-2" data-tab="cus_dev_plan/index" target="_self"><i class="fa fa-ellipsis-h"></i><span class="layui-left-nav"> 客户开发计划</span></a>
                                    </dd>
                                </#if>
                            </dl>
                        </li>


                    <#if permissions?seq_contains("20")>
                    <li class="layui-nav-item">
                        <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-flag"></i><span class="layui-left-nav"> 客户管理</span> <span class="layui-nav-more"></span></a>
                        <dl class="layui-nav-child">
                    </#if>
                    <#if permissions?seq_contains("2010")>
                        <dd>
                            <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-3" data-tab="customer/index" target="_self"><i class="fa fa-exchange"></i><span class="layui-left-nav"> 客户信息管理</span></a>
                        </dd>
                    </#if>

                        </dl>
                    </li>


                    <#if permissions?seq_contains("30")>
                    <li class="layui-nav-item">
                        <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-home"></i><span class="layui-left-nav"> 地区分布</span> <span class="layui-nav-more"></span></a>
                        <dl class="layui-nav-child">
                    </#if>
                            <#if permissions?seq_contains("3010")>
                                <dd>
                                    <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-10" data-tab="echarts/echarts" target="_self"><i class="fa fa-globe"></i><span class="layui-left-nav"> 客户所在地</span></a>
                                </dd>
                            </#if>
                        </dl>
                    </li>


<#--                    <#if permissions?seq_contains("50")>-->
<#--                    <li class="layui-nav-item">-->
<#--                        <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-gears"></i><span class="layui-left-nav"> 系统设置</span> <span class="layui-nav-more"></span></a>-->
<#--                        <dl class="layui-nav-child">-->
<#--                    </#if>-->
                    <li class="layui-nav-item">
                    <#if permissions?seq_contains("40")>
                        <dd>
                            <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-11" data-tab="user/index" target="_self"><i class="fa fa-user"></i><span class="layui-left-nav"> 用户管理</span></a>
                        </dd>
                    </#if>
                    </li>
                    <li class="layui-nav-item">
                    <#if permissions?seq_contains("50")>
                        <dd class="">
                            <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-12" data-tab="role/index" target="_self"><i class="fa fa-code-fork"></i><span class="layui-left-nav"> 角色管理</span></a>
                        </dd>
                    </#if>
                    </li>
                    <li class="layui-nav-item">
                    <#if permissions?seq_contains("60")>
                        <dd class="">
                            <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-13" data-tab="module/index" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 菜单管理</span></a>
                        </dd>
                    </#if>
                    </li>

                    <li class="layui-nav-item">
                            <dd class="">
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-13" data-tab="up" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 发送邮件</span></a>
                            </dd>
                    </li>

                    <span class="layui-nav-bar" style="top: 201px; height: 0px; opacity: 0;"></span>
                </ul>
            </#if>
        </div>
    </div>

    <div class="layui-body">
        <div class="layui-tab" lay-filter="layuiminiTab" id="top_tabs_box">
            <ul class="layui-tab-title" id="top_tabs">
                <li class="layui-this" id="layuiminiHomeTabId" lay-id="welcome"><i class="fa fa-home"></i> <span>首页</span></li>
            </ul>

            <ul class="layui-nav closeBox">
                <li class="layui-nav-item">
                    <a href="javascript:;"> <i class="fa fa-dot-circle-o"></i> 页面操作</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" data-page-close="other"><i class="fa fa-window-close"></i> 关闭其他</a></dd>
                        <dd><a href="javascript:;" data-page-close="all"><i class="fa fa-window-close-o"></i> 关闭全部</a></dd>
                    </dl>
                </li>
            </ul>
            <div class="layui-tab-content clildFrame">
                <div id="layuiminiHomeTabIframe" class="layui-tab-item layui-show">
                </div>
            </div>
        </div>
    </div>

</div>

<script type="text/javascript" src="${ctx}/js/main.js"></script>
</body>
</html>
