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

    //查询所有销售，弹出层的销售人员的下拉列表
    $.post(ctx+"/user/queryAllSales",function (res) {
        //console.log(res)
        for(var i=0;i<res.length;i++){
            //绑定下拉框显示，循环遍历加入下拉列表
            //如果下拉列表有值，通过隐藏域判断，加入选中
            if($("input[name='man']").val() == res[i].id){
                $("#assignMan").append("<option value=\""+res[i].id+"\"  selected='selected' >"+res[i].assignName+"</option>");
            }else{
                //没有分配指派人，只要加载列表
                $("#assignMan").append("<option value=\""+res[i].id+"\"   >"+res[i].assignName+"</option>");
            }

        }
        // 重新渲染下拉框内容，否则下拉列表没有内容
        layui.form.render("select");
    });


    /**
     * 更新或者添加操作
     */
    form.on('submit(addOrUpdateSaleChance)',function (data) {
        var index= top.layer.msg("数据提交中,请稍后...",{icon:16,time:false,shade:0.8});
        //默认执行添加
        var url = ctx+"/sale_chance/save";
        //console.log(data.field)
        //如果id不为空则为更新，id为隐藏域中的
        if($("input[name='id']").val()){
            url=ctx+"/sale_chance/update";
        }
        $.post(url,data.field,function (res) {
            if(res.code==200){
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