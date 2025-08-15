<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Customer - Pahana Edu Billing System</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<h2>Add New Customer</h2>
<% if (request.getAttribute("error") != null) { %>
<p style="color: red;"><%= request.getAttribute("error") %></p>
<% } %>
<form action="addCustomer" method="post">
    <label>Account Number: </label><input type="text" name="accountNumber" required><br>
    <label>Name: </label><input type="text" name="name" required><br>
    <label>Address: </label><input type="text" name="address" required><br>
    <label>Telephone: </label><input type="text" name="telephone" required><br>
    <label>Units Consumed: </label><input type="number" name="unitsConsumed" value="0" required><br>
    <input type="submit" value="Add Customer">
</form>
<a href="dashboard.jsp">Back to Dashboard</a>
</body>
</html>