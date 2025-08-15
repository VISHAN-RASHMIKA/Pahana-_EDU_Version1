<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard - Pahana Edu Billing System</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<h2>Welcome, <%= session.getAttribute("username") %></h2>
<% if (request.getAttribute("message") != null) { %>
<p style="color: green;"><%= request.getAttribute("message") %></p>
<% } %>
<% if (request.getAttribute("error") != null) { %>
<p style="color: red;"><%= request.getAttribute("error") %></p>
<% } %>
<a href="addCustomer.jsp">Add New Customer</a><br>
<a href="editCustomer?accountNumber=ACC001">Edit Customer (Sample: ACC001)</a><br>
<a href="logout">Logout</a>
</body>
</html>