layui.use(['form','jquery','jquery_cookie'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);


    form.on('submit(saveBtn)', function (data) {
        // console.log(data)
        data = data.field;
        //加载
        index = layer.load(1);
        $.ajax({
            type:"post",
            url:ctx+"/user/updatePassword",
            data:{
                oldPassword:data.old_password,
                newPassword:data.new_password,
                confirmPassword:data.again_password
            },
            dataType:"json",
            success:function (data) {
                layer.close(index);
                //修改密码成功后，清除cookie并且跳转到重新登录
                if(data.code == 200){
                    layer.msg("修改成功，系统即将退出。",{
                        icon: 1,
                        time: 2000, //2秒关闭（如果不配置，默认是3秒）
                        shade : [0.6 , '#000' , true],
                    },function () {
                        //清除cookie
                        $.removeCookie("userId",{domain:"localhost",path:"/"})
                        $.removeCookie("userName",{domain:"localhost",path:"/"})
                        $.removeCookie("trueName",{domain:"localhost",path:"/"})
                        setTimeout(function () {
                            //跳转到父窗口也就是登录界面，否则将在后台系统里面
                            window.parent.location.href = ctx + "/";
                        },);
                    })
                }else {
                    layer.msg(data.msg);
                }
            }
        })
    });
});