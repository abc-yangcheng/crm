<!DOCTYPE html>
<html>
<head>
	<title>用户管理</title>
	<#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" >

<#--	条件搜索框-->
	<blockquote class="layui-elem-quote quoteBox">
		<form class="layui-form">
			<div class="layui-inline">
				<div class="layui-input-inline">
					<input type="text" name="userName"
						   class="layui-input
					searchVal" placeholder="用户名" />
				</div>
				<div class="layui-input-inline">
					<input type="text" name="email" class="layui-input
					searchVal" placeholder="邮箱" />
				</div>
				<div class="layui-input-inline">
					<input type="text" name="phone" class="layui-input
					searchVal" placeholder="手机号" />
				</div>
				<#if permissions?seq_contains("4012")>
				<a class="layui-btn search_btn" data-type="reload"><i
							class="layui-icon">&#xe615;</i> 搜索</a>
				</#if>
			</div>
		</form>
	</blockquote>


	<table id="userList" class="layui-table"  lay-filter="users"></table>

<#--	表头操作-->
	<script type="text/html" id="toolbarDemo">
		<div class="layui-btn-container">
			<#if permissions?seq_contains("4011")>
			<a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
				<i class="layui-icon">&#xe608;</i>
				添加用户
			</a>
			</#if>
			<#if permissions?seq_contains("4014")>
			<a class="layui-btn layui-btn-warm delNews_btn" lay-event="del">
				<i class="layui-icon"></i>
				删除用户
			</a>
			</#if>
		</div>
	</script>

	<!--行操作-->
	<script id="userListBar" type="text/html">
		<#if permissions?seq_contains("4013")>
		<a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
		</#if>
		<#if permissions?seq_contains("4014")>
		<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
		</#if>
	</script>

</form>
<script type="text/javascript" src="${ctx}/js/user/user.js"></script>

</body>
</html>