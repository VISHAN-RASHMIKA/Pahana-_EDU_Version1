


<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Add Customer - Pahana Edu Billing System</title>--%>
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
<%--    <h2>Add New Customer</h2>--%>
<%--    <% if (request.getAttribute("error") != null) { %>--%>
<%--    <div class="alert alert-error"><%= request.getAttribute("error") %></div>--%>
<%--    <% } %>--%>
<%--    <form action="addCustomer" method="post">--%>
<%--        <label>Account Number:</label>--%>
<%--        <input type="text" name="accountNumber" value="<%= request.getAttribute("accountNumber") != null ? request.getAttribute("accountNumber") : "" %>" readonly>--%>
<%--        <label>First Name:</label>--%>
<%--        <input type="text" name="firstName" required>--%>
<%--        <label>Last Name:</label>--%>
<%--        <input type="text" name="lastName" required>--%>
<%--        <label>Address:</label>--%>
<%--        <input type="text" name="address" required>--%>
<%--        <label>Telephone:</label>--%>
<%--        <input type="text" name="telephone" required>--%>
<%--        <button type="submit" class="btn btn-primary">Add Customer</button>--%>
<%--    </form>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>



<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Customer - Pahana Edu Billing System</title>
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
        <h2>Add New Customer</h2>
        <% if (request.getAttribute("error") != null) { %>
        <div class="alert alert-error"><%= request.getAttribute("error") %></div>
        <% } %>
        <form action="addCustomer" method="post">
            <label for="accountNumber">Account Number:</label>
            <input type="text" id="accountNumber" name="accountNumber" value="<%= request.getAttribute("accountNumber") != null ? request.getAttribute("accountNumber") : "" %>" readonly>
            <label for="firstName">First Name:</label>
            <input type="text" id="firstName" name="firstName" required>
            <label for="lastName">Last Name:</label>
            <input type="text" id="lastName" name="lastName" required>
            <label for="address">Address:</label>
            <input type="text" id="address" name="address" required>
            <label for="telephone">Telephone:</label>
            <input type="text" id="telephone" name="telephone" required>
            <button type="submit" class="btn btn-primary">Add Customer</button>
        </form>
    </div>
</div>
</body>
</html>