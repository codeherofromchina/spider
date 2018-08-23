<!DOCTYPE>
<html>
<head>
    <title>首页</title>

    <!-- 引入css文件，不限制顺序 -->
    <link rel="stylesheet" type="text/css" href="/js/jquery-easyui-1.5.5.4/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/js/jquery-easyui-1.5.5.4/themes/icon.css">

    <!-- 引入js文件，限制顺序,先加jquery再加jquery.easyui -->
    <script type="text/javascript" src="/js/jquery-easyui-1.5.5.4/jquery.min.js"></script>
    <script type="text/javascript" src="/js/jquery-easyui-1.5.5.4/jquery.easyui.min.js"></script>
</head>
<body class="easyui-layout">
<div data-options="region:'west',split:true,title:'导航'" style="width:220px;">
    <ul class="easyui-tree" id="nav">
        <li>
            <span>爬虫管理</span>
            <ul>
                <li id="12" data-options="attributes:{'url':'/spider/web'}">
                    <span>爬虫网站</span>
                </li>
                <li id="13" data-options="attributes:{'url':'/spider/parser'}">
                    <span>网页解析器</span>
                </li>
                <li id="14" data-options="attributes:{'url':'/spider/typesMapping'}">
                    <span>分类映射管理</span>
                </li>
                <li id="1" data-options="attributes:{'url':'/spider/listSpider'}">
                    <span>列表爬虫</span>
                </li>
                <li id="2" data-options="attributes:{'url':'/spider/pageSpider'}">
                    <span>单页爬虫</span>
                </li>
            </ul>
        </li>
        <li>
            <span>内容管理</span>
            <ul>
                <li id="3" data-options="attributes:{'url':'http://www.baidu.com'}">
                    <span>图书管理</span>
                </li>
                <li id="4" data-options="attributes:{'url':'/book/types'}">
                    <span>分类管理</span>
                </li>
                <li id="5" data-options="attributes:{'url':'http://www.baidu.com'}">
                    <span>标签管理</span>
                </li>
            </ul>
        </li>
        <li>
            <span>访问管理</span>
            <ul>
                <li id="6" data-options="attributes:{'url':'http://www.baidu.com'}">
                    <span>管理员管理</span>
                </li>
                <li id="7" data-options="attributes:{'url':'http://www.baidu.com'}">
                    <span>角色管理</span>
                </li>
                <li id="8" data-options="attributes:{'url':'http://www.baidu.com'}">
                    <span>策略管理</span>
                </li>
            </ul>
        </li>
        <li>
            <span>API管理</span>
            <ul>
                <li id="9" data-options="attributes:{'url':'http://www.baidu.com'}">
                    <span>用户管理</span>
                </li>
                <li id="10" data-options="attributes:{'url':'http://www.baidu.com'}">
                    <span>API调用管理</span>
                </li>
                <li id="11" data-options="attributes:{'url':'http://www.baidu.com'}">
                    <span>API接口管理</span>
                </li>
            </ul>
        </li>
    </ul>
</div>
<div data-options="region:'center'">
    <div class="easyui-tabs" id="content-tabs">
        <div title="welcome" style="padding:10px"><h2>welcome page</h2></div>
    </div>
</div>
<script type="text/javascript">
$("#nav").tree({
    onClick: function(node){
        var url = node.attributes.url;
        var title = node.text;
        var id = node.id;
        var exists = $("#content-tabs").tabs('exists',title);
        if (exists) {
            $("#content-tabs").tabs('select',title);
        } else {
            $("#content-tabs").tabs('add', {
                id: id,
                title: title,
                //href:url,
                content: '<iframe src="' + url + '" frameborder=0 height=100% width=100% scrolling=no></iframe>',
                closable: true
            });
        }
    }
});
</script>
</body>
</html>