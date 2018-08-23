<!DOCTYPE>
<html>
<head>
    <title>单页面爬虫管理</title>
    <link rel="stylesheet" type="text/css" href="/js/jquery-easyui-1.5.5.4/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/js/jquery-easyui-1.5.5.4/themes/icon.css">
    <script type="text/javascript" src="/js/jquery-easyui-1.5.5.4/jquery.min.js"></script>
    <script type="text/javascript" src="/js/jquery-easyui-1.5.5.4/jquery.easyui.min.js"></script>
</head>
<body class="easyui-layout">
<div data-options="region:'north'" style="height:50px;padding: 10px;">
    <form id="searchForm" method="post">
        <div>
            <label for="s_pageName">爬虫名称：</label>
            <input class="easyui-textbox" id="s_pageName" style="width:150px">
            &nbsp;&nbsp;&nbsp;&nbsp;
            <label for="s_runStatus">运行状态：</label>
            <select class="easyui-combobox" id="s_runStatus" data-options="editable: false,panelHeight:132" style="width:200px;">
                <option value="">全部</option>
                <option value="0">未运行</option>
                <option value="1">运行中</option>
                <option value="2">已完成</option>
            </select>
            <a id="searchBtn" class="easyui-linkbutton" data-options="iconCls:'icon-search'" href="javascript:void(0);">搜索</a>
        </div>
    </form>
</div>
<div data-options="region:'center'">
    <table id="dataGrid"></table>
    <!-- 新增单页面爬虫window弹框 -->
    <div id="addPageSpiderWin" class="easyui-window" title="新增单页面爬虫" style="width:500px;height:420px"
         data-options="iconCls:'icon-save',modal:true,collapsible:false,maximizable:false,closed:true">
        <div style="padding:10px 60px 20px 60px">
            <form id="addPageSpiderForm" method="post">
                <table cellpadding="5">
                    <tr>
                        <td><label for="pageName">爬虫名称:</label></td>
                        <td><input class="easyui-textbox" type="text" id="pageName" name="pageName"
                                   data-options="required:true"/></td>
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
                        <td><label for="defaultCharset">字符集:</label></td>
                        <td>
                            <input class="easyui-textbox" required="required" type="text" id="defaultCharset" name="defaultCharset"
                                   value="UTF-8"/>
                        </td>
                    </tr>
                    <tr>
                        <td><label for="startUrl">开始页URL:</label></td>
                        <td>
                            <input class="easyui-textbox" data-options="required:true,validType:'url'" type="text" id="startUrl" name="startUrl" style="width:240px;"/>
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
        url: '/api/spiderPage/list',
        fitColumns: true,
        fit: true,
        striped: true,
        singleSelect:true,
        pagination: true,
        columns: [[
            {field: 'id', title: 'ID'},
            {field: 'pageName', title: '爬虫名称'},
            {field: 'startUrl', title: '爬取地址'},
            {field: 'runStatus', title: '运行状态',
                formatter: function(value,row,index){
                    if(value == 0){
                        return '未运行';
                    } else if (value == 1) {
                        return '运行中';
                    } else if (value == 2) {
                        return '已完成';
                    }
                    return value;
                }
            },
            {field: 'spiderWebName', title: '所属站点'},
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
            text: "新增爬虫",
            handler: function () {
                clearForm();
                $("#addPageSpiderWin").window('open');
            }
        }, '-']
    });
    function submitForm() {
        $('#addPageSpiderForm').form('submit', {
            url: '/api/spiderPage/add',
            onSubmit: function (params) {
                console.info(params);
            },
            success: function (resp) {
                var data = JSON.parse(resp);
                if (data && data.code == 0) {
                    $("#addPageSpiderWin").window('close');
                    // 成功，刷新页面数据，关闭windows
                    $('#dataGrid').datagrid('reload');
                } else {
                    alert("失败，" + data.msg);
                }

            }
        });
    }
    
    function clearForm() {
        $("#addPageSpiderForm").form('clear');
        $("#spiderWebId").combobox('setValue', 1);
        $("#defaultCharset").textbox('setValue', 'UTF-8');
    }
    $("#searchBtn").click(function () {
        var pageName = $("#s_pageName").textbox('getValue');
        var runStatus = $("#s_runStatus").combobox('getValue');
        $('#dataGrid').datagrid('load', {
            pageName:pageName,
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