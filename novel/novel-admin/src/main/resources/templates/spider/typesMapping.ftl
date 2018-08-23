<!DOCTYPE>
<html>
<head>
    <title>图书分类管理</title>
    <link rel="stylesheet" type="text/css" href="/js/jquery-easyui-1.5.5.4/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/js/jquery-easyui-1.5.5.4/themes/icon.css">
    <script type="text/javascript" src="/js/jquery-easyui-1.5.5.4/jquery.min.js"></script>
    <script type="text/javascript" src="/js/jquery-easyui-1.5.5.4/jquery.easyui.min.js"></script>
</head>
<body>
<table id="mappingTG"></table>
<div id="mm01" class="easyui-menu" style="width:120px;">
    <div onclick="add()" data-options="iconCls:'icon-add'">添加</div>
</div>
<div id="mm02" class="easyui-menu" style="width:120px;">
    <div onclick="add()" data-options="iconCls:'icon-add'">添加</div>
    <div onclick="edit()" data-options="iconCls:'icon-edit'">编辑</div>
    <div onclick="remove()" data-options="iconCls:'icon-remove'">删除</div>
</div>

<!-- 新增网站window弹框 -->
<div id="addTypesWin" class="easyui-window" title="新增分类映射" style="width:500px;height:260px"
     data-options="iconCls:'icon-save',modal:true,collapsible:false,maximizable:false,closed:true">
    <div style="padding:10px 60px 20px 60px">
        <form id="addTypesForm" method="post">
            <table cellpadding="5">
                <tr>
                    <td><label for="originalTypes">原始分类:</label></td>
                    <td><input class="easyui-textbox" type="text" name="originalTypes"
                               data-options="required:true"/></td>
                </tr>
                <tr>
                    <td><label for="bookTypesId">对应分类:</label></td>
                    <td>
                        <input id="addTypesFormCombotree" name="bookTypesId" />
                    </td>
                </tr>
                <tr>
                    <td><label for="seq">排序:</label></td>
                    <td><input class="easyui-numberspinner" type="text" id="addTypesFormSeq" name="seq"
                               data-options="required:true"/></td>
                </tr>
            </table>
            <input type="hidden" id="addTypesFormParentId" name="parentId" value=""/>
            <input type="hidden" id="addTypesFormSpiderWebId" name="spiderWebId" value=""/>
            <input type="hidden" id="addTypesFormId" name="id" value=""/>
        </form>
        <div style="text-align:center;padding:5px">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#addTypesWin').window('close');">取消</a>
        </div>
    </div>
</div>
<script type="text/javascript">
    $('#mappingTG').treegrid({
        url:'/api/spiderTypesMapping/webList',
        idField:'id',
        treeField:'originalTypes',
        fitColumns:true,
        fit : true,
        columns:[[
            {title:'原始分类',field:'originalTypes',width:180},
            {title:'对应分类',field:'bookTypesName',width:180},
            {title:'排序',field:'seq',width:180}
        ]],
        onContextMenu:function(e,row) { // 菜单
            e.preventDefault();
            $(this).treegrid('select', row.id);
            if (row.id > 0) {
                $('#mm02').menu('show', {
                    left: e.pageX,
                    top: e.pageY
                });
            } else {
                $('#mm01').menu('show', {
                    left: e.pageX,
                    top: e.pageY
                });
            }
        },
        loadFilter: function(data,parentId){
            if (data && data.code == 0){
                var tg = $(this);
                var opts = tg.treegrid('options');
                opts.onBeforeExpand = function(row){
                    var url = '/api/spiderTypesMapping/tree?webId=' + (-row.id);
                    opts.url = url;
                    return true;
                };
                return data.data;
            } else {
                return [];
            }
        },
        toolbar: [{
            iconCls: 'icon-reload',
            text: "刷新",
            handler: function () {
                $('#mappingTG').datagrid('reload');
            }
        }, '-' ]
    });

    $("#addTypesFormCombotree").combotree({
        url: '/api/bookTypes/tree',
        required: true,
        editable:false,
        valueField: 'id',
        textField:'name',
        loadFilter:function (data, parent) {
            if (data && data.code == 0) {
                return data.data;
            } else {
                return [];
            }
        },
        formatter:function(node){
            return node.name;
        }
    });
    // 新增弹框
    function add() {
        var node = $('#mappingTG').treegrid('getSelected');
        $('#addTypesForm').form('clear');
        if (node.id > 0) {
            $("#addTypesFormParentId").val(node.id);
        }
        $("#addTypesFormSpiderWebId").val(node.spiderWebId);
        $("#addTypesFormSeq").numberspinner('setValue',100);
        if(node.bookTypesId) {
            $('#addTypesFormCombotree').combotree('setValue', node.bookTypesId);
        } else {
            $('#addTypesFormCombotree').combotree('setValue', 1);
        }
        var t = $('#addTypesFormCombotree').combotree('tree');	// get the tree object
        t.tree('collapseAll');
        $("#addTypesWin").window('open');
    }
    function submitForm() {
        $('#addTypesForm').form('submit', {
            url: '/api/spiderTypesMapping/save',
            success: function (resp) {
                var data = JSON.parse(resp);
                $("#addTypesWin").window('close');
                if (data && data.code == 0) {
                    var dd = data.data;
                    var parentId = dd.parentId;
                    if (!parentId) {
                        parentId = -dd.spiderWebId;
                    }
                    console.info(dd);
                    $('#mappingTG').treegrid('append',{
                        parent: dd.parentId,
                        data: [dd]
                    });
                } else {
                    alert("失败，" + data.msg);
                }
            }
        });
    }
    function remove() {
        var node = $('#mappingTG').treegrid('getSelected');
        if(node) {
            $.post("/api/spiderTypesMapping/delete",{"id":node.id},function(data){
                if (data && data.code == 0) {
                    alert("删除成功");
                } else {
                    alert(data.msg);
                }
            },"json");
        } else {
            alert("请选中要删除的节点行");
        }
    }
</script>
</body>
</html>