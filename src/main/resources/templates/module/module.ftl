<!DOCTYPE html>
<html>
<head>
    <title>资源管理</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<#--操作头-->
    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <a class="layui-btn layui-btn-normal layui-btn-radius" lay-event="expand">
                <i class="layui-icon">&#xe608;</i>
                全部展开
            </a>
            <a class="layui-btn layui-btn-warm layui-btn-radius" lay-event="fold">
                <i class="layui-icon">&#xe608;</i>
                全部折叠
            </a>
        </div>
    </script>

    <table id="munu-table" class="layui-table" lay-filter="munu-table"></table>


    <script type="text/javascript" src="${ctx}/js/module/module.js"></script>
</body>
</html>