<%--
  Created by IntelliJ IDEA.
  User: Gala
  Date: 5/11/2020
  Time: 3:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="css/style.css">
</head>

<body style="background-color:#757575">

<div id="main-wrapper">
    <center><h2>Sign Up Form</h2></center>
    <center><img src="img/ava.png" class="avatar"/></center>

    <form class="myform" action="register" method="post">
        <label><b>Username</b></label><br >
        <input name="username" type="text" class="inputvalues" placeholder="Enter username" required/><br >

        <label><b>Password</b></label><br >
        <input name="password" type="password" class="inputvalues" placeholder="Enter password" required/><br >

        <label><b>Confirm Password</b></label><br >
        <input name="cpassword" type="password" class="inputvalues" placeholder="Confirm password" required/><br >

        <label><b>Email</b></label><br >
        <input name="email" type="text" class="inputvalues" placeholder="Enter your email" required/><br >

        <input name="submit_button" type="submit" id="signup_button" value="Sign Up"/><br >
        <a href="index.jsp"> <input type="button" id="back_button" value="<<Back"/></a>

    </form>

</div>

</body>
</html>
