layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;


    /**
     * 营销机会列表展示
     */
    var  tableIns = table.render({
        id : "saleChanceListTable",
        //容器元素的ID属性值
        elem: '#saleChanceList',
        //访问数据的URL（后台数据接口）
        url : ctx+'/sale_chance/list',
        //单元格最小的宽度
        cellMinWidth : 95,
        //开启分页
        page : true,
        //容器的高度 full差值
        height : "full-125",
        //每页页数的可选项
        limits : [10,15,20,25],
        //默认每页显示的数量
        limit : 10,
        //绑定头部工具栏
        toolbar: "#toolbarDemo",
        //

        cols : [[
            //要求field和返回的数据中对应的属性字段名字一样
            //title 设置列的标题
            //align 居中
            {type: "checkbox", fixed:"center"},
            {field: "id", title:'编号',fixed:"true",sort:true},
            {field: 'customerName', title: '客户名称',  align:'center'},
            {field: 'successPossibility', title: '成功几率', align:'center'},
            {field: 'linkMan', title: '联系人',  align:'center'},
            {field: 'linkPhone', title: '联系电话', align:'center'},
            {field: 'createMan', title: '创建人', align:'center'},
            {field: 'createDate', title: '创建时间', align:'center'},
            {field: 'assignName', title: '指派人', align:'center'},
            {field: 'assignTime', title: '分配时间', align:'center'},
            {field: 'state', title: '分配状态', align:'center',templet:function(d){
                  // console.log(d);
                    return formatterState(d.state);
                }},
            {field: 'devResult', title: '开发状态', align:'center',templet:function (d) {
                    return formatterDevResult(d.devResult);
                }},
            {title: '操作', templet:'#saleChanceListBar',fixed:"right",align:"center", minWidth:150}
        ]]
    });

    //分配状态判断
    function formatterState(state){
        if(state==0){
            return "<div style='color:yellow '>未分配</div>";
        }else if(state==1){
            return "<div style='color: green'>已分配</div>";
        }else{
            return "<div style='color: red'>未知</div>";
        }
    }

    //开发状态判断
    function formatterDevResult(value){
        /**
         * 0-未开发
         * 1-开发中
         * 2-开发成功
         * 3-开发失败
         */
        if(value==0){
            return "<div style='color: yellow'>未开发</div>";
        }else if(value==1){
            return "<div style='color: #00FF00;'>开发中</div>";
        }else if(value==2){
            return "<div style='color: #00B83F'>开发成功</div>";
        }else if(value==3){
            return "<div style='color: red'>开发失败</div>";
        }else {
            return "<div style='color: #af0000'>未知</div>"
        }
    }


    /**
     * 条件搜索
     */
    // 多条件搜索，表格重载
    $(".search_btn").on("click",function () {
        table.reload("saleChanceListTable",{
            page:{
                curr:1  //重新从第一页开始
            },
            //设置需要传递给后端的参数，异步数据接口
            where:{
                customerName:$("input[name='customerName']").val(),// 客户名
                createMan:$("input[name='createMan']").val(),// 创建人
                state:$("#state").val()    //分配状态
            }
        })
    });


    // 头工具栏事件，toolbar(表格的lay-filter),lay-event的监听
    table.on('toolbar(saleChances)',function (obj) {

        // console.log(obj);
        switch (obj.event) {
            case "add":
                openAddOrUpdateSaleChanceDialog();
                break;
            case "del":
                //console.log(table.checkStatus(obj.config.id).data);
                //批量删除操作table.checkStatus(obj.config.id)获取选中行的id
                delSaleChance(table.checkStatus(obj.config.id).data);
                break;
        }
    });

    /**
     * 批量删除
     *   datas:选择的待删除记录数组
     */

    function delSaleChance(datas){

        if(datas.length==0){
            layer.msg("请选择待删除记录!",{icon:5});
            return;
        }

        //询问用户是否删除
        layer.confirm("确定删除选中的记录吗？",{title:'营销机会管理',icon:3},function (index) {
            layer.close(index);
            // 数组参数的拼接 ids=10&ids=20&ids=30
            var ids="ids=";
            for(var i=0;i<datas.length;i++){
                if(i<datas.length-1){
                    ids=ids+datas[i].id+"&ids=";
                }else{
                    //循环到最后一个id
                    ids=ids+datas[i].id;
                }
            }

            $.ajax({
                type:"post",
                url:ctx+"/sale_chance/delete",
                data:ids,//传递参数数组
                dataType:"json",
                success:function (data) {
                    if(data.code==200){
                        layer.msg("删除成功",{icon:6});
                        //刷新表格
                        tableIns.reload();
                    }else{
                        layer.msg(data.msg,{icon:5});
                    }
                }
            })
        })
    }


    /**
     * 行工具栏监听,判断编辑或者删除
     */
    table.on('tool(saleChances)',function (obj) {
          var layEvent =obj.event;
          if(layEvent === "edit"){
              openAddOrUpdateSaleChanceDialog(obj.data.id);
          }else if(layEvent === "del"){
              //删除一条操作
              layer.confirm("确认删除当前记录?",{icon: 3, title: "机会数据管理"},function (index) {
                  $.post(ctx+"/sale_chance/delete",{ids:obj.data.id},function (data) {
                      if(data.code==200){
                          layer.msg("删除成功",{icon:6});
                          //刷新表格
                          tableIns.reload();
                      }else{
                          layer.msg(data.msg,{icon:5});
                      }
                  })
              })
          }

    });



    /**
     * 打开添加或更新对话框
     */
    function openAddOrUpdateSaleChanceDialog(sid) {
        var title="营销机会管理-机会添加";
        var url=ctx+"/sale_chance/addOrUpdateSaleChancePage";
        if(sid){
            title="营销机会管理-机会更新";
            url=url+"?id="+sid;
        }
        //会在父窗口中打开
        layui.layer.open({
            title:title,
            type:2,  //iframe层
            area:["700px","500px"],
            maxmin:true, //最大化最小化
            content:url
        })
    }

});
