<!DOCTYPE html>
<html>
<head>
	<title>角色管理</title>
	<#include "../common.ftl">
</head>
<body class="childrenBody">

<form class="layui-form" >
	<blockquote class="layui-elem-quote quoteBox">
		<form class="layui-form">
			<div class="layui-inline">
				<div class="layui-input-inline">
					<input type="text" name="roleName"  class="layui-input searchVal" placeholder="角色名" />
				</div>
				<#if permissions?seq_contains("5012")>
				<a class="layui-btn search_btn" data-type="reload"><i class="layui-icon">&#xe615;</i> 搜索</a>
				</#if>
			</div>
		</form>
	</blockquote>

	<table id="roleList" class="layui-table"  lay-filter="roles"></table>

<#--	头工具栏-->
	<script type="text/html" id="toolbarDemo">
		<div class="layui-btn-container">
			<#if permissions?seq_contains("5011")>
			<a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
				<i class="layui-icon">&#xe608;</i>
				添加角色
			</a>
			</#if>

			<a class="layui-btn layui-btn-danger delNews_btn" lay-event="grant">
				<i class="layui-icon">&#xe672;</i>
				授权
			</a>
		</div>
	</script>
	<!--操作-->
	<script id="roleListBar" type="text/html">
		<#if permissions?seq_contains("5013")>
		<a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
		</#if>
		<#if permissions?seq_contains("5014")>
		<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
		</#if>
	</script>
</form>
<script type="text/javascript" src="${ctx}/js/role/role.js"></script>

</body>
</html>