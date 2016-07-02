<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>My JSP 'index.jsp' starting page</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <script type="text/javascript" src="/js/jquery-3.0.0.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#loginBtn").click(function () {
                var username = $("#username1").val();
                var userpwd = $("#userpwd1").val();
                var objData = {"username": username, "userpwd": userpwd};
                /* var data = [];
                 data.push(objData); */
                // alert(objData);

                // ajax提交
                // contentType: "application/json" --> $.post设置不了，不能用，报415错误
                /* $.post("user/login", JSON.stringify(objData), function(data) {
                 alert(data);
                 }); */
                $.ajax({
                    type: 'POST',
                    /* url: "user/login_requestbody", */
                    /* url: "user/login_httpEntity", */
                    url: "user/login_httpEntity",
                    dataType: 'json',
                    contentType: "application/json",
                    data: JSON.stringify(objData),
                    /* data: objData, */ // 报400错误
                    success: function (data) {
                        alert('数据加载成功');
                    },
                    error: function (xhr, type) {
                        alert('数据加载失败');
                    }
                });
            });
            // 根据用户名获取查看用户密码
            $("#getUserInfoByUname").click(function () {
                var username = $("#username1").val();
                $.get("userResp/getUserByUname/" + username, function (data) {
                    alert(data.userpwd);
                });
            });

            // 获取所有的用户信息
            $("#getUsers").click(function () {
                $.get("userResp/getUsers", function (data) {
                    if (data.success) {
                        alert(data.data[0].username);
                    }
                });
            });

            // 获取所有的用户信息的json字符串
            $("#getUsersJsonStr").click(function () {
                $.get("userResp/getUsersJsonStr", function (data) {
                    // 处理json格式字符串
                    alert(data); // 字符串
                    alert(data.success); // undenfied
                    data = eval("(" + data + ")"); // eval -->  将json格式string转成json对象
                    alert(data.data[0].username);
                });
            });

            // 获取所有的用户信息
            $("#getUsersByResponseEntity").click(function () {
                $.get("userResp/getUsersByResponseEntity", function (data) {
                    if (data.success) {
                        alert(data.data[0].username);
                    }
                });
            });
        });


    </script>
</head>

<body>
用户名：<input id="username1">
密码：<input id="userpwd1" type="password">
<input type="button" id="loginBtn" value="登录">
<br><br>
<form action="user/login_data" method="post">
    用户名：<input name="username">
    密码：<input name="userpwd" type="password">
    <input type="submit" value="登录">
</form>
<br><br>
<input type="button" id="getUserInfoByUname" value="获取用户信息">
<input type="button" id="getUsers" value="获取所有用户信息">
<input type="button" id="getUsersJsonStr" value="获取所有用户信息的json字符串">
<input type="button" id="getUsersByResponseEntity" value="ResponseEntity获取所有用户信息">
<br><br>
<h3>上传文件-01</h3>
<form action="/file/fileupload" method="post" enctype="multipart/form-data">
    请选择要上传的文件:<input type="file" name="file"/>
    <input type="submit" value="上传">
</form>
<br><br>
<form action="/file/multi_diff_fileupload" method="post" enctype="multipart/form-data">
    选择要上传的文件1-1:<input type="file" name="file1"/><br><br>
    选择要上传的文件1-2:<input type="file" name="file2"/><br><br>
    选择要上传的文件1-3:<input type="file" name="file3"/><br><br>
    <input type="submit" value="上传">
</form>
<br><br>
<h3>上传文件-02</h3>
<form action="/file/multi_same_fileupload" method="post" enctype="multipart/form-data">
    选择要上传的文件2-1:<input type="file" name="files"/><br><br>
    选择要上传的文件2-2:<input type="file" name="files"/><br><br>
    选择要上传的文件2-3:<input type="file" name="files"/><br><br>
    <input type="submit" value="上传">
</form>
</body>
</html>
