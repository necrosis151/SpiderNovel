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
        var result;
        function selectType(title,type) {
            var url="search/byType";
            alert(title+type);
            var json=JSON.stringify({type:type,site:title});
            sendAjax(url,json);
        }

        function sendAjax(url, json) {

            $.ajax({
                type: "post",
                url: url,
                data: json,
                async:false,
                contentType: "application/json;charset=UTF-8",
                dataType: "json",
                success: function (data) {
                    result=data;
                }
            });
        }

        $(function () {
            $('#tt').tabs({
                onSelect: function (title) {
                    var json = JSON.stringify({site: title});
                    var temp;
                    $(".type").html("");
                    sendAjax("search/typeList", json);
                    for (i = 0; i < result.length; i++) {
                        temp = "<li><a href=javascript:void(0); onclick='selectType(title,this.text)'>" + result[i] + "</a></li>";
                        $(".type").append(temp);
                    }
                }
            });
        })
        var pageSize = 10;

        function getNovelList(value, name, pageNum, pageSize) {
            var json = JSON.stringify({content: value, site: name, pageNum: pageNum, pageSize: pageSize});
            $.ajax({
                type: "post",
                url: "search/novelList",
                data: json,
                contentType: "application/json;charset=UTF-8",
                dataType: "json",
                success: function (result) {
                    alert(result.length);
                    // $('#list').empty();   //清空resText里面的所有内容
                    // var html = '';
                    // $.each(data, function(commentIndex, comment){
                    //     html += '<div class="comment"><h6>' + comment['username']
                    //         + ':</h6><p class="para"' + comment['content']
                    //         + '</p></div>';
                    // });
                    // $('#resText').html(html);
                }
            });
        }

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
</body>
</html>
