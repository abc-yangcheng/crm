layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    //关闭弹出框
    $("#closeBtn").click(function () {
        //当你在iframe页面关闭自身时
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index); //再执行关闭

    })

    //更新与添加复用
    form.on('submit(addOrUpdateCusDevPlan)',function (data) {
        var index = top.layer.msg("数据提交中,请稍后...",{icon:16,time:false,shade:0.8});
        var url = ctx+"/cus_dev_plan/save";
        var time = data.field.planDate
        console.log(time.match(/^(\d{4})(-)(\d{2})(-)(\d{2})$/))

        if($("input[name='id']").val()){
            url=ctx+"/cus_dev_plan/update";
        }
        $.post(url,data.field,function (res) {
            if(res.code == 200){
                top.layer.msg("操作成功");
                top.layer.close(index);
                layer.closeAll("iframe");
                // 刷新父页面
                parent.location.reload();
            }else{
                layer.msg(res.msg);
            }
        });
        return false;
    });

});