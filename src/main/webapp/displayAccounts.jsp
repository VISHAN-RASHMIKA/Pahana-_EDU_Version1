<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ page import="com.pahana.model.Customer" %>--%>
<%--<%@ page import="java.util.List" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Display Accounts - Pahana Edu Billing System</title>--%>
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
<%--<div class="container">--%>
<%--    <h2>Customer Accounts</h2>--%>
<%--    <% List<Customer> customers = (List<Customer>) request.getAttribute("customers"); %>--%>
<%--    <% if (customers == null || customers.isEmpty()) { %>--%>
<%--    <p>No customers found.</p>--%>
<%--    <% } else { %>--%>
<%--    <table class="table">--%>
<%--        <thead>--%>
<%--        <tr>--%>
<%--            <th>Account Number</th>--%>
<%--            <th>First Name</th>--%>
<%--            <th>Last Name</th>--%>
<%--            <th>Address</th>--%>
<%--            <th>Telephone</th>--%>
<%--            <th>Actions</th>--%>
<%--        </tr>--%>
<%--        </thead>--%>
<%--        <tbody>--%>
<%--        <% for (Customer customer : customers) { %>--%>
<%--        <tr>--%>
<%--            <td><%= customer.getAccountNumber() %></td>--%>
<%--            <td><%= customer.getFirstName() %></td>--%>
<%--            <td><%= customer.getLastName() %></td>--%>
<%--            <td><%= customer.getAddress() %></td>--%>
<%--            <td><%= customer.getTelephone() %></td>--%>
<%--            <td>--%>
<%--                <a href="editCustomer?accountNumber=<%= customer.getAccountNumber() %>" class="btn btn-primary">Edit</a>--%>
<%--                <a href="calculateBill?accountNumber=<%= customer.getAccountNumber() %>" class="btn btn-primary">Calculate Bill</a>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--        <% } %>--%>
<%--        </tbody>--%>
<%--    </table>--%>
<%--    <% } %>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>


<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ page import="com.pahana.model.Customer" %>--%>
<%--<%@ page import="java.util.List" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Display Accounts - Pahana Edu Billing System</title>--%>
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
<%--<div class="container">--%>
<%--    <h2>Customer Accounts</h2>--%>
<%--    <% List<Customer> customers = (List<Customer>) request.getAttribute("customers"); %>--%>
<%--    <% if (customers == null || customers.isEmpty()) { %>--%>
<%--    <p>No customers found.</p>--%>
<%--    <% } else { %>--%>
<%--    <table class="table">--%>
<%--        <thead>--%>
<%--        <tr>--%>
<%--            <th>Account Number</th>--%>
<%--            <th>First Name</th>--%>
<%--            <th>Last Name</th>--%>
<%--            <th>Address</th>--%>
<%--            <th>Telephone</th>--%>
<%--            <th>Actions</th>--%>
<%--        </tr>--%>
<%--        </thead>--%>
<%--        <tbody>--%>
<%--        <% for (Customer customer : customers) { %>--%>
<%--        <tr>--%>
<%--            <td><%= customer.getAccountNumber() %></td>--%>
<%--            <td><%= customer.getFirstName() %></td>--%>
<%--            <td><%= customer.getLastName() %></td>--%>
<%--            <td><%= customer.getAddress() %></td>--%>
<%--            <td><%= customer.getTelephone() %></td>--%>
<%--            <td>--%>
<%--                <a href="editCustomer?accountNumber=<%= customer.getAccountNumber() %>" class="btn btn-primary">Edit</a>--%>
<%--                <a href="calculateBill?accountNumber=<%= customer.getAccountNumber() %>" class="btn btn-primary">Calculate Bill</a>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--        <% } %>--%>
<%--        </tbody>--%>
<%--    </table>--%>
<%--    <% } %>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pahana.model.Customer" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Display Accounts - Pahana Edu Billing System</title>
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
    <h2>Customer Accounts</h2>
    <% List<Customer> customers = (List<Customer>) request.getAttribute("customers"); %>
    <% if (customers == null || customers.isEmpty()) { %>
    <p>No customers found.</p>
    <% } else { %>
    <table class="table">
        <thead>
        <tr>
            <th>Account Number</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Address</th>
            <th>Telephone</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <% for (Customer customer : customers) { %>
        <tr>
            <td><%= customer.getAccountNumber() %></td>
            <td><%= customer.getFirstName() %></td>
            <td><%= customer.getLastName() %></td>
            <td><%= customer.getAddress() %></td>
            <td><%= customer.getTelephone() %></td>
            <td>
                <a href="editCustomer?accountNumber=<%= customer.getAccountNumber() %>" class="btn btn-primary">Edit</a>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
    <% } %>
</div>
</body>
</html>