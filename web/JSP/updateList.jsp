<%--
  Created by IntelliJ IDEA.
  User: kinglong
  Date: 2021/9/29
  Time: 9:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="<%=request.getContextPath()%>/update" method="post">
    教师编号：
    <input type="text" name="id" value="<%=request.getParameter("id")%>" >
    <br>

    教师姓名：
    <input type="text" name="realname" value="<%=request.getParameter("realname")%>" >
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
