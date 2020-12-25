$(function () {
    //加载数据对象
    loadModuleInfo(); 
});

//定义树形结构数据
var zTreeObj;

/**
 * 加载树形结构资源
 */
function loadModuleInfo() {
    $.ajax({
        type:"post",
        //获取隐藏域角色id，查询当前角色对应的已经授权的资源
        url:ctx+"/module/queryAllModules?roleId="+$("input[name='roleId']").val(),
        dataType:"json",
        success:function (data) {

            //console.log(data)
            // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
            var setting = {
                //使用复选框
                check: {
                    enable: true
                },
                //使用简单的json结构
                data: {
                    simpleData: {
                        enable: true
                    }
                },
                //当check被选中或者被取消的时候触发的函数
                callback: {
                    onCheck: zTreeOnCheck
                }
            };
            zTreeObj = $.fn.zTree.init($("#test1"), setting, data);
        }
    })
}

//点击资源选项后，使用ajax通过授权接口 异步将该权限分配到角色中
function zTreeOnCheck(event, treeId, treeNode) {
    //获取所有被勾选的节点集合
    var nodes= zTreeObj.getCheckedNodes(true);
    //获取所有的节点，获取资源的集合 mids=1&mids=2&mids=3...
    var mids="mids=";
    for(var i=0;i<nodes.length;i++){
        if(i<nodes.length-1){
            mids=mids+nodes[i].id+"&mids=";
        }else{
            mids=mids+nodes[i].id;
        }
    }

    $.ajax({
        type:"post",
        url:ctx+"/role/addGrant",
        data:mids+"&roleId="+$("input[name='roleId']").val(),
        dataType:"json",
        success:function (data) {
            console.log(data);
        }
    })

}