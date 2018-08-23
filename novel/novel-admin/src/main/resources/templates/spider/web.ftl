<!DOCTYPE>
<html>
<head>
    <title>站点</title>
    <link rel="stylesheet" type="text/css" href="/js/jquery-easyui-1.5.5.4/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/js/jquery-easyui-1.5.5.4/themes/icon.css">
    <script type="text/javascript" src="/js/jquery-easyui-1.5.5.4/jquery.min.js"></script>
    <script type="text/javascript" src="/js/jquery-easyui-1.5.5.4/jquery.easyui.min.js"></script>
</head>
<body class="easyui-layout">
<div data-options="region:'north'" style="height:50px;padding: 10px;">
    <form id="searchForm" method="post">
        <div>
            <label for="webName">站点名称：</label>
            <input class="easyui-textbox" id="webName" name="webName" style="width:300px">
            <a id="searchBtn" class="easyui-linkbutton" data-options="iconCls:'icon-search'" href="javascript:void(0);">搜索</a>
        </div>
    </form>
</div>
<div data-options="region:'center'">
    <table id="dataGrid"></table>
    <!-- 新增网站window弹框 -->
    <div id="addWebWin" class="easyui-window" title="新增站点" style="width:400px;height:220px"
         data-options="iconCls:'icon-save',modal:true,collapsible:false,maximizable:false,closed:true">
        <div style="padding:10px 60px 20px 60px">
            <form id="addWebForm" method="post">
                <table cellpadding="5">
                    <tr>
                        <td><label for="webName">名称:</label></td>
                        <td><input class="easyui-textbox" type="text" name="webName"
                                   data-options="required:true"></input></td>
                    </tr>
                    <tr>
                        <td><label for="indexPage">首页:</label></td>
                        <td><input class="easyui-textbox" type="text" name="indexPage"
                                   data-options="required:true,validType:'url'"></input></td>
                    </tr>
                </table>
            </form>
            <div style="text-align:center;padding:5px">
                <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">新增</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
            </div>
        </div>

    </div>
</div>

<!-- 编辑的面板 -->
<script type="text/javascript">
    $('#dataGrid').datagrid({
        url: '/api/spiderWeb/list',
        fitColumns: true,
        fit: true,
        striped: true,
        singleSelect:true,
        pagination: true,
        columns: [[
            {field: 'id', title: 'ID'},
            {field: 'webName', title: '名称'},
            {field: 'indexPage', title: '首页'},
            {field: 'createTime', title: '创建时间'}
        ]],
        loadFilter: function (data) {
            var obj = {};
            if (data && data.code == 0) {
                obj.total = data.data.total;
                obj.rows = data.data.list;
            }
            return obj;
        },
        toolbar: [{
            iconCls: 'icon-add',
            text: "新增网站",
            handler: function () {
                clearForm();
                $("#addWebWin").window('open');
            }
        }, '-', {
            iconCls: 'icon-help',
            handler: function () {
                alert('帮助按钮')
            }
        }]
    });
    $("#searchBtn").click(function () {
        var webName = $("#webName").val();
        $('#dataGrid').datagrid('load', {
            webName: webName
        });
    });

    function submitForm() {
        $('#addWebForm').form('submit', {
            url: '/api/spiderWeb/add',
            onSubmit: function (params) {
                console.info(params);
            },
            success: function (resp) {
                var data = JSON.parse(resp);
                if (data && data.code == 0) {
                    $("#addWebWin").window('close');
                    // 成功，刷新页面数据，关闭windows
                    $('#dataGrid').datagrid('reload');

                } else {
                    alert("失败，" + data.msg);
                }

            }
        });
    }
    function clearForm() {
        $('#addWebForm').form('clear');
    }
</script>
</body>
</html>