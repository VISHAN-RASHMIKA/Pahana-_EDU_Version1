<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login - Pahana Edu Billing System</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<h2>Login</h2>
<% if (request.getAttribute("error") != null) { %>
<p style="color: red;"><%= request.getAttribute("error") %></p>
<% } %>
<form action="login" method="post">
    <label>Username: </label><input type="text" name="username" required><br>
    <label>Password: </label><input type="password" name="password" required><br>
    <input type="submit" value="Login">
</form>
</body>
</html>