<!DOCTYPE>
<html>
<head>
    <title>解析器</title>
    <link rel="stylesheet" type="text/css" href="/js/jquery-easyui-1.5.5.4/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/js/jquery-easyui-1.5.5.4/themes/icon.css">
    <script type="text/javascript" src="/js/jquery-easyui-1.5.5.4/jquery.min.js"></script>
    <script type="text/javascript" src="/js/jquery-easyui-1.5.5.4/jquery.easyui.min.js"></script>
</head>
<body class="easyui-layout">
<div data-options="region:'north'" style="height:50px;padding: 10px;">
    <form id="searchForm" method="post">
        <div>
            <label for="parserName">解析器名称：</label>
            <input class="easyui-textbox" id="parserName" name="parserName" style="width:300px">
            <a id="searchBtn" class="easyui-linkbutton" data-options="iconCls:'icon-search'" href="javascript:void(0);">搜索</a>
        </div>
    </form>
</div>
<div data-options="region:'center'">
    <table id="dataGrid"></table>
    <!-- 编辑的面板 -->
    <div id="addParserWin" class="easyui-window" title="新增解析器" style="width:550px;height:520px"
         data-options="iconCls:'icon-save',modal:true,collapsible:false,maximizable:false,closed:true">
        <div style="padding:10px 60px 20px 60px">
            <form id="addParserForm" method="post">
                <table cellpadding="5">
                    <tr>
                        <td><label for="parserName">名称:</label></td>
                        <td><input class="easyui-textbox" type="text" style="width: 200px;" name="parserName"
                                   data-options="required:true"></input></td>
                    </tr>
                    <tr>
                        <td><label for="parserDesc">说明:</label></td>
                        <td><input class="easyui-textbox" style="height: 80px;width: 330px;" name="parserDesc"
                                   data-options="required:true,multiline:true"></input></td>
                    </tr>
                    <tr>
                        <td><label for="parserContent">内容:</label></td>
                        <td><input class="easyui-textbox" style="height:240px;width: 330px;" name="parserContent"
                                   data-options="required:true,multiline:true"></input></td>
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


<script type="text/javascript">
    $('#dataGrid').datagrid({
        url: '/api/spiderParser/list',
        fitColumns: true,
        fit: true,
        striped: true,
        singleSelect: true,
        pagination: true,
        columns: [[
            {field: 'id', title: 'ID'},
            {field: 'parserName', title: '名称'},
            {field: 'parserDesc', title: '说明'},
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
            text: "新增解析器",
            handler: function () {
                clearForm();
                $("#addParserWin").window('open');
            }
        }, '-', {
            iconCls: 'icon-help',
            handler: function () {
                alert('帮助按钮')
            }
        }]
    });
    $("#searchBtn").click(function () {
        var parserName = $("#parserName").val();
        $('#dataGrid').datagrid('load', {
            parserName: parserName
        });
    });
    function submitForm() {
        $('#addParserForm').form('submit', {
            url: '/api/spiderParser/add',
            onSubmit: function (params) {
                console.info(params);
            },
            success: function (resp) {
                var data = JSON.parse(resp);
                if (data && data.code == 0) {
                    $("#addParserWin").window('close');
                    // 成功，刷新页面数据，关闭windows
                    $('#dataGrid').datagrid('reload');

                } else {
                    console.info(data);
                    alert("失败，" + data.msg);
                }

            }
        });
    }
    function clearForm() {
        $('#addParserForm').form('clear');
    }
</script>
</body>
</html>