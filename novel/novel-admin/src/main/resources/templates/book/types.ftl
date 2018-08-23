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
<table id="tt" style="width:600px;height:400px"></table>
<div id="mm" class="easyui-menu" style="width:120px;">
    <div onclick="append()" data-options="iconCls:'icon-add'">添加</div>
    <div onclick="removeIt()" data-options="iconCls:'icon-remove'">删除</div>
</div>
<!-- 新增网站window弹框 -->
<div id="addTypesWin" class="easyui-window" title="新增图书分类" style="width:500px;height:300px"
     data-options="iconCls:'icon-save',modal:true,collapsible:false,maximizable:false,closed:true">
    <div style="padding:10px 60px 20px 60px">
        <form id="addTypesForm" method="post">
            <table cellpadding="5">
                <tr>
                    <td><label for="name">分类名称:</label></td>
                    <td><input class="easyui-textbox" type="text" name="name"
                               data-options="required:true"></input></td>
                </tr>
                <tr>
                    <td><label for="seq">排序:</label></td>
                    <td><input class="easyui-numberbox" type="text" id="addTypesFormSeq" value="100" name="seq"
                               data-options="required:true"></input></td>
                </tr>
                <tr>
                    <td><label for="pic">图标链接:</label></td>
                    <td><input class="easyui-textbox" type="text" name="pic"
                               data-options="required:false,validType:'url'"></input></td>
                </tr>
                <tr>
                    <td><label for="clazz">所属分类:</label></td>
                    <td>
                        <select class="easyui-combobox" id="addTypesFormClazz" data-options="editable:false,panelHeight:66" name="clazz" style="width:200px;">
                            <option value="0">男生</option>
                            <option value="1">女生</option>
                        </select>
                    </td>
                </tr>
            </table>
            <input type="hidden" id="addTypesFormParentId" name="parentId" value=""/>
        </form>
        <div style="text-align:center;padding:5px">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">新增分类</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeWin()">取消新增</a>
        </div>
    </div>

</div>
<script type="text/javascript">
    var editingId;
    $('#tt').treegrid({
        url:'/api/bookTypes/tree',
        idField:'id',
        treeField:'name',
        onContextMenu:function(e,row) { // 菜单
            e.preventDefault();
            cancel();
            $(this).treegrid('select', row.id);
            $('#mm').menu('show', {
                left: e.pageX,
                top: e.pageY
            });
        },
        toolbar: [{
            iconCls: 'icon-edit',
            text:'编辑',
            handler: function(){
                if (editingId != undefined){
                    $('#tt').treegrid('select', editingId);
                    return;
                }
                var row = $('#tt').treegrid('getSelected');
                if (row){
                    editingId = row.id
                    $('#tt').treegrid('beginEdit', editingId);
                } else {
                    alert("未选择要编辑的行");
                }
            }
        },'-',{
            iconCls: 'icon-save',
            text:'保存编辑',
            handler: function(){
                if (editingId != undefined){
                    var jsonParam = {'id':editingId};
                    var editors = $('#tt').treegrid('getEditors',editingId);
                    $.each(editors,function (index,editor) {
                        var val = editor.target.val();
                        var name = editor.field;
                        jsonParam[name] = val;
                    });
                    jsonParam.createTime = undefined;
                    $.post('/api/bookTypes/edit',jsonParam,function(data){
                        if (data && data.code == 0) {
                            $('#tt').treegrid('endEdit', editingId);
                        } else {
                            // 编辑失败，弹框提示
                            alert("编辑失败，"+ data.msg);
                            $('#tt').treegrid('cancelEdit', editingId);
                        }
                        editingId = undefined;
                    },'json')
                }
            }
        },'-',{
            iconCls: 'icon-cancel',
            text:'取消编辑',
            handler: function(){cancel()}
        },'->','-',{
            iconCls: 'icon-reload',
            text:'重新加载',
            handler: function(){
                $('#tt').treegrid('reload');
            }
        }],
        parentField : 'parentId',
        fit : true,
        fitColumns : true,
        rownumbers: true,
        columns:[[
            {title:'分类名称',field:'name',width:180,editor:'text'},
            {field:'seq',title:'排序',width:60,align:'right',editor:'numberbox'},
            {field:'pic',title:'图标',width:80,editor:'text',
                formatter:function (value) {
                    if (value) {
                        return '<img src="'+value+'" alt="分类图标" />';
                    }else {
                        return value;
                    }
                }
            },
            {field:'clazz',title:'分类',width:80,editor:{
                    type:'combobox',
                    options:{
                        data:[{"value":0,"text":"男生"},{"value":1,"text":"女生"}],
                        valueField: "value",
                        textField: "text",
                        editable: false,
                        panelHeight:66,
                        required: true
                    }
                },
                formatter: function(value,row,index){
                    if (value == 0){
                        return '男生';
                    } else {
                        return '女生';
                    }
                }
            },
            {field:'createTime',title:'创建时间',width:80}
        ]],
        loadFilter: function(data){
            if (data && data.code == 0){
                data.data.
                return data.data;
            } else {
                return [];
            }
        },
        onLoadSuccess:function(){
            $('#tt').treegrid('collapseAll');
        }
    });
    function cancel(){
        if (editingId != undefined){
            $('#tt').treegrid('cancelEdit', editingId);
            editingId = undefined;
        }
    }
    function append(){
        var node = $('#tt').treegrid('getSelected');
        $('#tt').treegrid('expand', node.id);
        $('#addTypesForm').form('clear');
        $("#addTypesFormParentId").val(node.id);
        $('#addTypesFormClazz').combobox('setValue', node.clazz);
        $("#addTypesFormClazz").combobox('readonly',true);
        $("#addTypesFormSeq").textbox('setValue', "100");
        $("#addTypesWin").window('open');
    }
    function removeIt(){
        var node = $('#tt').treegrid('getSelected');
        if (node){
            var tipMsg ;
            if(node.children) {
                tipMsg = "确认删除分类及其所有子分类吗？";
            } else {
                tipMsg = "确认删除分类吗？";
            }
            $.messager.confirm('删除确认', tipMsg, function(r){
                if (r){
                    $.post("/api/bookTypes/delete",{"id":node.id},function(data){
                        if (data || data.code == 0) {
                            $('#tt').treegrid('remove', node.id);
                        } else {
                            alert("删除失败，" + data.msg);
                        }
                    },"json")
                }
            });
        }
    }
    function closeWin() {
        $("#addTypesWin").window('close');
    }
    function submitForm() {
        $('#addTypesForm').form('submit', {
            url: '/api/bookTypes/add',
            success: function (resp) {
                var data = JSON.parse(resp);
                if (data && data.code == 0) {
                    $("#addTypesWin").window('close');
                    // 成功，添加分类到页面
                    var dd = data.data;
                    console.info(dd);
                    $('#tt').treegrid('append',{
                        parent: dd.parentId,
                        data: [dd]
                    })
                } else {
                    alert("失败，" + data.msg);
                }
            }
        });
    }
</script>
</body>
</html>