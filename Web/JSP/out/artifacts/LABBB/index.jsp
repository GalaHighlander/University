<%--
  Created by IntelliJ IDEA.
  User: Gala
  Date: 5/10/2020
  Time: 2:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
      <link rel="stylesheet" href="css/style.css">

      <title>Login Page</title>
  </head>
  <body>
  <div id="main-wrapper">
      <center><h2>Login Form</h2></center>
      <center><img src="img/ava.png" class="avatar"/></center>
  <form class="myform" action="LoginServlet" method="POST">
      <label><b>Username</b></label><br >
      <input class="inputvalues" name="username" type="text"placeholder="Type your username" required><br>
      <label><b>Password</b></label><br >
      <input class="inputvalues" name="password" type="password"placeholder="Type your password" required><br>
      <input id="login_button" type="submit" value="Login">
      <a href="register.jsp"><input type="button" id="register_button" value="Register"/></a>
  </form>
  </div>

  </body>
</html>
