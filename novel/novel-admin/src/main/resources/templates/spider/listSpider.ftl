<!DOCTYPE>
<html>
<head>
    <title>列表爬虫管理</title>
    <link rel="stylesheet" type="text/css" href="/js/jquery-easyui-1.5.5.4/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/js/jquery-easyui-1.5.5.4/themes/icon.css">
    <script type="text/javascript" src="/js/jquery-easyui-1.5.5.4/jquery.min.js"></script>
    <script type="text/javascript" src="/js/jquery-easyui-1.5.5.4/jquery.easyui.min.js"></script>
</head>
<body class="easyui-layout">
<div data-options="region:'north'" style="height:50px;padding: 10px;">
    <form id="searchForm" method="post">
        <div>
            <label for="s_listName">爬虫名称：</label>
            <input class="easyui-textbox" id="s_listName" style="width:150px">
            &nbsp;&nbsp;&nbsp;&nbsp;
            <label for="s_runStatus">运行状态：</label>
            <select class="easyui-combobox" id="s_runStatus" data-options="editable: false,panelHeight:99" style="width:200px;">
                <option value="">全部</option>
                <option value="true">运行中</option>
                <option value="false">未运行</option>
            </select>
            <a id="searchBtn" class="easyui-linkbutton" data-options="iconCls:'icon-search'" href="javascript:void(0);">搜索</a>
        </div>
    </form>
</div>
<div data-options="region:'center'">
    <table id="dataGrid"></table>
    <!-- 新增列表爬虫window弹框 -->
    <div id="addListSpiderWin" class="easyui-window" title="新增列表爬虫" style="width:500px;height:420px"
         data-options="iconCls:'icon-save',modal:true,collapsible:false,maximizable:false,closed:true">
        <div style="padding:10px 60px 20px 60px">
            <form id="addListSpiderForm" method="post">
                <table cellpadding="5">
                    <tr>
                        <td><label for="listName">爬虫名称:</label></td>
                        <td><input class="easyui-textbox" type="text" id="listName" name="listName"
                                   data-options="required:true"/></td>
                    </tr>
                    <tr>
                        <td><label for="indexPage">线程数（<${maxThreadNum}）:</label></td>
                        <td><input class="easyui-numberspinner" required="required" data-options="min:1,max:${maxThreadNum},editable:false" type="text" id="threadNum" name="threadNum"
                                   value="1"/></td>
                    </tr>
                    <tr>
                        <td><label for="spiderWebId">所属站点:</label></td>
                        <td>
                            <input id="spiderWebId" class="easyui-combobox" name="spiderWebId"
                                   data-options="valueField:'id',textField:'webName',url:'/api/spiderWeb/listAll',loadFilter:myLoadFilter">
                        </td>
                    </tr>
                    <tr>
                        <td><label for="spiderParserId">解析器:</label></td>
                        <td>
                            <input id="spiderParserId" class="easyui-combobox" name="spiderParserId" required="required"
                                   data-options="valueField:'id',textField:'parserName',url:'/api/spiderParser/listAll',loadFilter:myLoadFilter">
                        </td>
                    </tr>
                    <tr>
                        <td><label for="startPage">开始页码:</label></td>
                        <td>
                            <input class="easyui-numberspinner" required="required" data-options="min:1" type="text" id="startPage" name="startPage"
                                   value="1"/>
                        </td>
                    </tr>
                    <tr>
                        <td><label for="currentPage">当前页码:</label></td>
                        <td>
                            <input class="easyui-numberspinner" required="required" data-options="min:1" type="text" id="currentPage" name="currentPage"
                                   value="1"/>
                        </td>
                    </tr>
                    <tr>
                        <td><label for="totalPage">总页码:</label></td>
                        <td>
                            <input class="easyui-numberspinner" required="required" data-options="min:1" type="text" id="totalPage" name="totalPage"
                                   value="1"/>
                        </td>
                    </tr>
                    <tr>
                        <td><label for="defaultCharset">字符集:</label></td>
                        <td>
                            <input class="easyui-textbox" required="required" type="text" id="defaultCharset" name="defaultCharset"
                                   value="UTF-8"/>
                        </td>
                    </tr>
                    <tr>
                        <td><label for="listUrlPattern">列表页正则:</label></td>
                        <td>
                            <input class="easyui-textbox" data-options="required:true,validType:'url'" type="text" id="listUrlPattern" name="listUrlPattern" style="width:240px;"/>
                        </td>
                    </tr>
                    <tr>
                        <td><label for="catalogUrlPattern">目录页正则:</label></td>
                        <td>
                            <input class="easyui-textbox" type="text" data-options="validType:'url'" id="catalogUrlPattern" name="catalogUrlPattern" style="width:240px;"/>
                        </td>
                    </tr>
                    <tr>
                        <td><label for="contentUrlPattern">内容页正则:</label></td>
                        <td>
                            <input class="easyui-textbox" type="text" data-options="validType:'url'" id="contentUrlPattern" name="contentUrlPattern" style="width:240px;"/>
                        </td>
                    </tr>
                    <tr>
                        <td><label for="runStatus">运行状态:</label></td>
                        <td>
                            <select id="runStatus" class="easyui-combobox" name="runStatus" style="width:150px;">
                                <option value="false">暂不运行</option>
                                <option value="true">立即运行</option>
                            </select>
                        </td>
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
        url: '/api/spiderList/list',
        fitColumns: true,
        fit: true,
        striped: true,
        singleSelect:true,
        pagination: true,
        columns: [[
            {field: 'id', title: 'ID'},
            {field: 'listName', title: '爬虫名称'},
            {field: 'threadNum', title: '线程数'},
            {field: 'runStatus', title: '运行状态'},
            {field: 'spiderWebName', title: '所属站点'},
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
            text: "新增爬虫",
            handler: function () {
                clearForm();
                $("#addListSpiderWin").window('open');
            }
        }, '-']
    });
    function submitForm() {
        $('#addListSpiderForm').form('submit', {
            url: '/api/spiderList/add',
            onSubmit: function (params) {
                console.info(params);
            },
            success: function (resp) {
                var data = JSON.parse(resp);
                if (data && data.code == 0) {
                    $("#addListSpiderWin").window('close');
                    // 成功，刷新页面数据，关闭windows
                    $('#dataGrid').datagrid('reload');
                } else {
                    alert("失败，" + data.msg);
                }

            }
        });
    }
    
    function clearForm() {
        $("#addListSpiderForm").form('clear');
        $("#threadNum").numberspinner('setValue', 1);
        $("#spiderWebId").combobox('setValue', 1);
        $("#startPage").numberspinner('setValue', 1);
        $("#currentPage").numberspinner('setValue', 1);
        $("#totalPage").numberspinner('setValue', 1);
        $("#defaultCharset").textbox('setValue', 'UTF-8');
        $("#runStatus").combobox('setValue', 'false');
    }
    $("#searchBtn").click(function () {
        var listName = $("#s_listName").textbox('getValue');
        var runStatus = $("#s_runStatus").combobox('getValue');
        $('#dataGrid').datagrid('load', {
            listName:listName,
            runStatus:runStatus
        });
    });
    function myLoadFilter(data, parent) {
        if (data && data.code == 0) {
            return data.data;
        } else {
            return [];
        }
    }
</script>
</body>
</html>