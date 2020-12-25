layui.use(['table', 'treetable'], function () {
    var $ = layui.jquery,
        table = layui.table,
        treeTable = layui.treetable;
    // 渲染表格
      treeTable.render({
        treeColIndex: 1,
        treeSpid: -1,
        treeIdName: 'id',
        treePidName: 'parentId',
        elem: '#munu-table',
        url: ctx+'/module/list',
        toolbar: "#toolbarDemo",
        treeDefaultClose:true,
        page: true,
        cols: [[
            {type: 'numbers'},
            {field: 'moduleName', minWidth: 100, title: '菜单名称'},
            {field: 'optValue', width: 80,title: '权限码'},
            {field: 'url', title: '菜单url'},
            {field: 'createDate', title: '创建时间'},
            {field: 'updateDate', title: '更新时间'},
            {
                field: 'grade', width: 80, align: 'center', templet: function (data) {
                    if (data.grade == 0) {
                        return '<span class="layui-badge layui-bg-blue">目录</span>';
                    }
                    if(data.grade == 1){
                        return '<span class="layui-badge-rim">菜单</span>';
                    }
                    if (data.grade == 2) {
                        return '<span class="layui-badge layui-bg-gray">按钮</span>';
                    }
                },
                title: '类型'
            }
        ]],
        done: function () {
            layer.closeAll('loading');
        }
    });

    // 头工具栏事件
    table.on('toolbar(munu-table)',function (obj) {
        switch (obj.event) {
            //展开所有
            case "expand":
                treeTable.expandAll('#munu-table');
                break;
            //展开所有
            case "fold":
                treeTable.foldAll('#munu-table');
                break;
        }
    });



});