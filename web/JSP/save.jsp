<%--
  Created by IntelliJ IDEA.
  User: kinglong
  Date: 2021/9/24
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sava the teachers information</title>
</head>
<body>
    <form action="<%=request.getContextPath()%>/save" method="post">
    教师姓名：
        <input type="text" name="realname">
        <br>
    性别：
        <input type="radio" name="sex" value="m">男
        <input type="radio" name="sex" value="f">女
        <br>
    职务：
        <input type="text" name="post">
        <br>

        <input type="submit" name="Save!">
     </form>
</body>
</html>
