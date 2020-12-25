layui.use(['element', 'layer', 'layuimini','jquery','jquery_cookie'], function () {
    var $ = layui.jquery,
        layer = layui.layer,
        $ = layui.jquery_cookie($);


    // 菜单初始化
    $('#layuiminiHomeTabIframe').html('<iframe width="100%" height="100%" frameborder="0"  src="welcome"></iframe>')
    layuimini.initTab();


    // 用户退出功能
    $("#login-out").click(function () {

        // window.parent.location.href = ctx + "/";
        layer.confirm('是否登出当前用户?', {icon: 3, title:'提示'}, function(index){
            window.parent.location.href = ctx + "/";
            $.removeCookie("userId",{domain:"localhost",path:"/"})
            $.removeCookie("userName",{domain:"localhost",path:"/"})
            $.removeCookie("trueName",{domain:"localhost",path:"/"})

            layer.close(index);
        });
        // layer.alert("111");
    })
});