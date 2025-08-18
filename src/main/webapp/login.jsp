

<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Login - Pahana Edu Billing System</title>--%>
<%--    <link rel="stylesheet" href="css/style.css">--%>
<%--</head>--%>
<%--<body>--%>
<%--<nav class="navbar">--%>
<%--    <div class="container">--%>
<%--        <span>Pahana Edu Billing System</span>--%>
<%--    </div>--%>
<%--</nav>--%>
<%--<div class="card">--%>
<%--    <h2>Login</h2>--%>
<%--    <% if (request.getAttribute("error") != null) { %>--%>
<%--    <div class="alert alert-error"><%= request.getAttribute("error") %></div>--%>
<%--    <% } %>--%>
<%--    <form action="login" method="post">--%>
<%--        <label>Username:</label>--%>
<%--        <input type="text" name="username" required>--%>
<%--        <label>Password:</label>--%>
<%--        <input type="password" name="password" required>--%>
<%--        <button type="submit" class="btn btn-primary">Login</button>--%>
<%--    </form>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login - Pahana Edu Billing System</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<nav class="navbar">
    <div class="container">
        <span>Pahana Edu Billing System</span>
    </div>
</nav>
<div class="container">
    <div class="card">
        <h2>Login</h2>
        <% if (request.getAttribute("error") != null) { %>
        <div class="alert alert-error"><%= request.getAttribute("error") %></div>
        <% } %>
        <form action="login" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
            <button type="submit" class="btn btn-primary">Login</button>
        </form>
    </div>
</div>
</body>
</html>