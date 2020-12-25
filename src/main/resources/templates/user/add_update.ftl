<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">

</head>
<body class="childrenBody">
<form class="layui-form" style="width:90%;">
<#--隐藏域-->
    <input name="id" type="hidden" value="${(user.id)!}"/>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label required">用户名</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName"
                   lay-verify="required"  lay-reqtext="用户名不能为空" name="userName" id="userName"  value="${(user.userName)!}" placeholder="请输入用户名 （必填）">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label required">真实姓名</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName"
                   lay-verify="required" lay-reqtext="真实姓名不能为空" name="trueName" id="trueName" value="${(user.trueName)!}" placeholder="请输入真实姓名 （必填）">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label required">邮箱</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userEmail"
                   lay-verify="email"  name="email" value="${(user.email)!}"
                   id="email"
                   placeholder="请输入邮箱 （必填）">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label required">手机号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userEmail"
                   lay-verify="phone"  name="phone" value="${(user.phone)!}" id="phone" placeholder="请输入手机号 （必填）">
        </div>
    </div>

    <div class="magb15 layui-col-md4 layui-col-xs12">
        <label class="layui-form-label required">角色</label>
        <div class="layui-input-block">
<#--            角色下拉框xm-select是layui的插件-->
            <select name="roleIds"  xm-select="selectId">
            </select>
        </div>
    </div>

    <br/>
    <div>&nbsp;</div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit=""
                    lay-filter="addOrUpdateUser">确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>

</form>
<script type="text/javascript" src="${ctx}/js/user/add.update.js"></script>
</body>
</html>