<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>修改密码</title>
    <#include "common.ftl">
    <style>
        .layui-form-item .layui-input-company {width: auto;padding-right: 10px;line-height: 38px;}
    </style>
</head>
<body class="childrenBody">
<div class="layuimini-container">
    <div class="layuimini-main">

        <div class="layui-form layuimini-form">
            <div class="layui-form-item">
                <label class="layui-form-label required">QQ邮箱</label>
                <div class="layui-input-block">
                    <input type="text" name="email" lay-verify="required" lay-reqtext="邮箱不能为空" placeholder="请输入QQ邮箱"  value="" class="layui-input">
<#--                    <tip>填写自己账号的旧的密码。</tip>-->
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">邮件主题</label>
                <div class="layui-input-block">
                    <input type="text" name="title"  placeholder="请输入邮件主题"  value="" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">邮件内容</label>
                <div class="layui-input-block">
                    <textarea placeholder="请输入邮件内容" name="content" class="layui-textarea"></textarea>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <input type="file" name="filepath">
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="upfile" id="upfile">确认发送</button>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${ctx}/js/index.js" charset="utf-8"></script>

</body>
</html>