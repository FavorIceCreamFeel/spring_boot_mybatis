<html>
<head>
    <title>显示</title>
</head>
<body>
<span>用户名:${hashMap.userName!"请登录"}</span>
<a href="/logout">退出</a>
<a href="/Home/list">123</a>
    <table>
        <tr>
            <th>姓名</th>
            <th>Email</th>
            <th>密码</th>
            <th>练习方式</th>
            <th>状态</th>
        </tr>
        <#list studentPageInfo.list as student>
        <tr>
            <td>${student.userName}</td>
            <td>${student.email}</td>
            <td>${student.pwd}</td>
            <td>${student.phoneNum}</td>
            <td>${student.status}</td>
        </tr>
        </#list>
    </table>
<form action="/file/upload" method="post" enctype="multipart/form-data">
    头像：<input type="file" name="filename"><br/>
    <input id="b1" type="submit" value="提交">
</form>

</body>
</html>