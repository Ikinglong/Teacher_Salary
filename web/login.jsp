<%--
  Created by IntelliJ IDEA.
  User: kinglong
  Date: 2021/9/29
  Time: 11:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login page</title>
</head>
<body>
<h1>欢迎管理员登陆</h1>
<hr>
    <form action="<%=request.getContextPath()%>/login" method="post">
        username<input type="text" name="username"><br>
        password<input type="password" name="password"><br>
        <input type="checkbox" name="rememberMe" value="ok">记住我<br>
        <input type="submit" value="login">
    </form>

</body>
</html>
