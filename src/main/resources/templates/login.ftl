<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录</title>
</head>
<body>
<h2>登录</h2>
<h3>表单登录</h3>
<form action="/login/login" method="post">
    <table>
        <tr>
            <td>用户名:</td>
            <td><input type="text" name="username"></td>
        </tr>
        <tr>
            <td>密码:</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td>记住账号：<input type="checkbox" name="remebe"></td>
            <td colspan="2"><button type="submit">登录</button></td>
        </tr>
    </table>
</form>
<form action="/login/login" method="post">
    <table>
        <tr>
            <td>用户名:</td>
            <td><input type="text" name="studentname"></td>
        </tr>
        <tr>
            <td>密码:</td>
            <td><input type="password" name="pwd"></td>
        </tr>
        <tr>
            <td colspan="2"><button type="submit">注册</button></td>
        </tr>
    </table>
</form>
</body>
</html>