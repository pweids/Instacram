<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
        <a href="index.jsp"><img src="logo.jpg" alt="Instacram"></a>
    </div>
    <form id="login" action="register" method="post">
    <h1>Register</h1>
    <div id="inputs">
        <input id="username" type="text" name="username" placeholder="Username" autofocus required>   
        <input id="password" type="password" name="password" placeholder="Password" required>
        <input id="email" type="text" name="email" placeholder="Email" required>
                
    </div>
    <div id="actions">
        <input type="submit" id="submit" value="Register">
    </div>
</form>
</div>
    <div class="footer">
        <p id="footer-text">© 2012 Instacram, Inc.</p>
    </div>

</body>
</html>
