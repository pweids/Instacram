<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.instacram.dao.*"%>
<!DOCTYPE html>

<html>
<head>
    <title>Instacram</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>

<body>

    <div class="header"></div>
<div class="wrapper">
    <div id="logo">
        <img src="logo.jpg" alt="Instacram">
    </div>
    <form id="login" action="login" method="post">
    <h1>Log In</h1>
    <div id="inputs">
        <input id="username" type="text" name="username" placeholder="Username" autofocus required>   
        <input id="password" type="password" name="password" placeholder="Password" required>
    </div>
    <div id="actions">
        <input type="submit" id="submit" value="Log in">
        Not signed up? <a href="register.jsp">Enroll here.</a>
    </div>
</form>
    <p id="courseNum">Currently serving 2 courses.</p>
    <div id = "clear"></div>
    </div>
    <div class="footer">
        <p id="footer-text">© 2012 Instacram, Inc.</p>
    </div>

</body>
</html>
