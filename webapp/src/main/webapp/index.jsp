<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/22
  Time: 14:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>小说搜搜-免费且无广告的小说阅读网</title>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-easyui-1.5.4.5/jquery.easyui.min.js"
            type="text/javascript"></script><!-- Bootstrap core CSS -->
    <script src="${pageContext.request.contextPath}/js/jquery-easyui-1.5.4.5/locale/easyui-lang-zh_CN.js"
            type="text/javascript"></script>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/js/jquery-easyui-1.5.4.5/themes/default/easyui.css">

    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/js/jquery-easyui-1.5.4.5/themes/icon.css">
    <style type="text/css">
        .type {
            font: 18px verdana, arial, sans-serif; /* 设置文字大小和字体样式 */
        }

        .type li {
            list-style: none; /* 将默认的列表符号去掉 */
            padding: 3px; /* 将默认的内边距去掉 */
            margin: 3px; /* 将默认的外边距去掉 */
            float: left; /* 往左浮动 */
        }

        .type li a {
            text-decoration: none; /* 去掉下划线 */
        }
    </style>
    <script type="text/javascript">
        var novelList;
        var typeList;
        var site;
        var pageSize = 10;

        function selectType(title, type) {
            var url = "search/byType";
            var json = JSON.stringify({type: type, site: title});
            getNovelList(url, json);
            // $("#novelList").html("");
            $("#novelList").datagrid('loadData', {total: 0, rows: []});
            for (i = 0; i < novelList.length; i++) {
                // var temp = "<tr><td>" + novelList[i].name + "</td><td>" + novelList[i].author + "</td><td>" + novelList[i].url + "</td><td>" + novelList[i].type + "</td><td>" + novelList[i].status + "</td></tr>"
                $("#novelList").datagrid('insertRow', {

                    row: {
                        name: novelList[i].name,
                        author: novelList[i].author,
                        url: "<a href='" + novelList[i].url + "'>" + novelList[i].url + "</a>",
                        type: novelList[i].type,
                        status: novelList[i].status,

                    }
                });
            }
        }

        function getNovelList(url, json) {
            $.ajax({
                type: "post",
                url: url,
                data: json,
                async: false,
                contentType: "application/json;charset=UTF-8",
                dataType: "json",
                success: function (data) {
                    novelList = data;
                }
            });
        }

        $(function () {

            $("#novelList").datagrid({
                onClickRow: function (rowIndex, rowData) {
                    $(this).datagrid('unselectRow', rowIndex);
                },
                pageNumber: 1,
                singleSelect: true,
                border: false,
                rownumbers: true,
                pageList: [10, 20, 30],//选择一页显示多少数据
                pagination: true,//在DataGrid控件底部显示分页工具栏。
            });


            function getTypeList(url, json) {
                $.ajax({
                    type: "post",
                    url: url,
                    data: json,
                    async: false,
                    contentType: "application/json;charset=UTF-8",
                    dataType: "json",
                    success: function (data) {
                        typeList = data;
                    }
                });
            }


            $('#tt').tabs({
                onSelect: function (title) {
                    var json = JSON.stringify({site: title});
                    var temp;
                    site = title;
                    $(".type").html("");
                    getTypeList("search/typeList", json);
                    for (i = 0; i < typeList.length; i++) {
                        temp = "<li><a href=javascript:void(0); onclick='selectType(site,this.text)'>" + typeList[i] + "</a></li>";
                        $(".type").append(temp);
                    }
                }
            });
        })


        function qq(value, name) {
            var pageNum = 1;
            getNovelList(value, name, pageNum, pageSize);
        }
    </script>
</head>
<body>
<div style="text-align:center; vertical-align:middle;margin:30px;">
    <input id="ss" class="easyui-searchbox" searcher="qq" prompt="可输入书名，作者名，类型" menu="#mm"
           style="width:900px"></input>
    <div id="mm">
        <div name="all">全部</div>
        <div name="qidian.com">起点</div>
        <div name="zhulang.com">逐浪</div>
    </div>

</div>
<div id="tt" class="easyui-tabs" style="width:70%;height:230px;margin: 0 auto">
    <div title="起点" style="padding:10px;display:none;">
        <ul class="type"></ul>
    </div>
    <div title="逐浪" style="padding:10px;display:none;">
        <ul class="type"></ul>
    </div>
</div>
<div style="width:70%;height:230px;margin: 0 auto;margin-top:30px;">
    <table width="100%" id="novelList" style="height:auto;" id="novelList">
        <thead>
        <tr>
            <th field="name" width="12%" data-options="resizable:false">名称</th>
            <th field="author" width="12%" data-options="resizable:false">作者</th>
            <th field="url" width="52%" data-options="resizable:false">原始地址</th>
            <th field="type" width="12%" data-options="resizable:false">类型</th>
            <th field="status" width="12%" data-options="resizable:false">更新状态</th>
        </tr>
        </thead>
        <tbody>

        </tbody>
    </table>
</div>
</body>
</html>
