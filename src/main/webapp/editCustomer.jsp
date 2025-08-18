<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ page import="com.pahana.model.Customer" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Edit Customer - Pahana Edu Billing System</title>--%>
<%--    <link rel="stylesheet" href="css/style.css">--%>
<%--</head>--%>
<%--<body>--%>
<%--<nav class="navbar">--%>
<%--    <div class="container">--%>
<%--        <span>Pahana Edu Billing System</span>--%>
<%--        <div>--%>
<%--            <a href="dashboard.jsp" class="btn btn-primary">Back to Dashboard</a>--%>
<%--            <a href="logout" class="btn btn-danger">Logout</a>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</nav>--%>
<%--<div class="card">--%>
<%--    <h2>Edit Customer</h2>--%>
<%--    <% if (request.getAttribute("error") != null) { %>--%>
<%--    <div class="alert alert-error"><%= request.getAttribute("error") %></div>--%>
<%--    <% } %>--%>
<%--    <% Customer customer = (Customer) request.getAttribute("customer"); %>--%>
<%--    <form action="editCustomer" method="post">--%>
<%--        <input type="hidden" name="accountNumber" value="<%= customer.getAccountNumber() %>">--%>
<%--        <label>First Name:</label>--%>
<%--        <input type="text" name="firstName" value="<%= customer.getFirstName() %>" required>--%>
<%--        <label>Last Name:</label>--%>
<%--        <input type="text" name="lastName" value="<%= customer.getLastName() %>" required>--%>
<%--        <label>Address:</label>--%>
<%--        <input type="text" name="address" value="<%= customer.getAddress() %>" required>--%>
<%--        <label>Telephone:</label>--%>
<%--        <input type="text" name="telephone" value="<%= customer.getTelephone() %>" required>--%>
<%--        <button type="submit" class="btn btn-primary">Update Customer</button>--%>
<%--    </form>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>



<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pahana.model.Customer" %>
<html>
<head>
    <title>Edit Customer - Pahana Edu Billing System</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<nav class="navbar">
    <div class="container">
        <span>Pahana Edu Billing System</span>
        <div>
            <a href="dashboard.jsp" class="btn btn-primary">Back to Dashboard</a>
            <a href="logout" class="btn btn-danger">Logout</a>
        </div>
    </div>
</nav>
<div class="container">
    <div class="card">
        <h2>Edit Customer</h2>
        <% if (request.getAttribute("error") != null) { %>
        <div class="alert alert-error"><%= request.getAttribute("error") %></div>
        <% } %>
        <% Customer customer = (Customer) request.getAttribute("customer"); %>
        <form action="editCustomer" method="post">
            <input type="hidden" name="accountNumber" value="<%= customer.getAccountNumber() %>">
            <label for="firstName">First Name:</label>
            <input type="text" id="firstName" name="firstName" value="<%= customer.getFirstName() %>" required>
            <label for="lastName">Last Name:</label>
            <input type="text" id="lastName" name="lastName" value="<%= customer.getLastName() %>" required>
            <label for="address">Address:</label>
            <input type="text" id="address" name="address" value="<%= customer.getAddress() %>" required>
            <label for="telephone">Telephone:</label>
            <input type="text" id="telephone" name="telephone" value="<%= customer.getTelephone() %>" required>
            <button type="submit" class="btn btn-primary">Update Customer</button>
        </form>
    </div>
</div>
</body>
</html>