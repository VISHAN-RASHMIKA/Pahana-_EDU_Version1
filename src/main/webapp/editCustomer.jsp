<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pahana.model.Customer" %>
<html>
<head>
    <title>Edit Customer - Pahana Edu Billing System</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<h2>Edit Customer</h2>
<% if (request.getAttribute("error") != null) { %>
<p style="color: red;"><%= request.getAttribute("error") %></p>
<% } %>
<% Customer customer = (Customer) request.getAttribute("customer"); %>
<form action="editCustomer" method="post">
    <input type="hidden" name="accountNumber" value="<%= customer.getAccountNumber() %>">
    <label>Name: </label><input type="text" name="name" value="<%= customer.getName() %>" required><br>
    <label>Address: </label><input type="text" name="address" value="<%= customer.getAddress() %>" required><br>
    <label>Telephone: </label><input type="text" name="telephone" value="<%= customer.getTelephone() %>" required><br>
    <label>Units Consumed: </label><input type="number" name="unitsConsumed" value="<%= customer.getUnitsConsumed() %>" required><br>
    <input type="submit" value="Update Customer">
</form>
<a href="dashboard.jsp">Back to Dashboard</a>
</body>
</html>