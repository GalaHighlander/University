<%--
  Created by IntelliJ IDEA.
  User: Gala
  Date: 5/10/2020
  Time: 3:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="LoginServlet" method="POST">
    Enter Username:<input name="username" type="text"><br>
    Enter Password:<input name="password" type="password"><br>
    <input type="submit" value="Login">
</form>
</body>
</html>
