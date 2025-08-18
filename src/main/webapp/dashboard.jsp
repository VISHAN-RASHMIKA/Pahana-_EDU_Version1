<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Dashboard - Pahana Edu Billing System</title>--%>
<%--    <link rel="stylesheet" href="css/style.css">--%>
<%--</head>--%>
<%--<body>--%>
<%--<nav class="navbar">--%>
<%--    <div class="container">--%>
<%--        <span>Pahana Edu Billing System</span>--%>
<%--        <div>--%>
<%--            <a href="logout" class="btn btn-danger">Logout</a>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</nav>--%>
<%--<div class="container">--%>
<%--    <h2>Welcome, <%= session.getAttribute("username") %></h2>--%>
<%--    <% if (request.getAttribute("message") != null) { %>--%>
<%--    <div class="alert alert-success"><%= request.getAttribute("message") %></div>--%>
<%--    <% } %>--%>
<%--    <% if (request.getAttribute("error") != null) { %>--%>
<%--    <div class="alert alert-error"><%= request.getAttribute("error") %></div>--%>
<%--    <% } %>--%>
<%--    <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(200px, 1fr)); gap: 10px;">--%>
<%--        <a href="addCustomer.jsp" class="btn btn-primary">Add New Customer</a>--%>
<%--        <a href="displayAccounts" class="btn btn-primary">Display Accounts</a>--%>
<%--        <a href="addItem.jsp" class="btn btn-primary">Add New Item</a>--%>
<%--        <a href="displayItems" class="btn btn-primary">Manage Items</a>--%>
<%--        <a href="calculateBill?accountNumber=ACC001" class="btn btn-primary">Calculate Bill (Sample: ACC001)</a>--%>
<%--        <a href="help.jsp" class="btn btn-primary">Help</a>--%>
<%--    </div>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>


<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Dashboard - Pahana Edu Billing System</title>--%>
<%--    <link rel="stylesheet" href="css/style.css">--%>
<%--</head>--%>
<%--<body>--%>
<%--<nav class="navbar">--%>
<%--    <div class="container">--%>
<%--        <span>Pahana Edu Billing System</span>--%>
<%--        <div>--%>
<%--            <a href="logout" class="btn btn-danger">Logout</a>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</nav>--%>
<%--<div class="container">--%>
<%--    <h2>Welcome, <%= session.getAttribute("username") %></h2>--%>
<%--    <% if (request.getAttribute("message") != null) { %>--%>
<%--    <div class="alert alert-success"><%= request.getAttribute("message") %></div>--%>
<%--    <% } %>--%>
<%--    <% if (request.getAttribute("error") != null) { %>--%>
<%--    <div class="alert alert-error"><%= request.getAttribute("error") %></div>--%>
<%--    <% } %>--%>
<%--    <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(200px, 1fr)); gap: 10px;">--%>
<%--        <a href="addCustomer" class="btn btn-primary">Add New Customer</a>--%>
<%--        <a href="displayAccounts" class="btn btn-primary">Display Accounts</a>--%>
<%--        <a href="addItem" class="btn btn-primary">Add New Item</a>--%>
<%--        <a href="displayItems" class="btn btn-primary">Manage Items</a>--%>
<%--        <a href="calculateBill" class="btn btn-primary">Calculate Bill</a>--%>
<%--        <a href="help.jsp" class="btn btn-primary">Help</a>--%>
<%--    </div>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>



<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard - Pahana Edu Billing System</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<nav class="navbar">
    <div class="container">
        <span class="logo">📘 Pahana Edu Billing System</span>
        <div>
            <a href="logout" class="btn btn-danger">Logout</a>
        </div>
    </div>
</nav>

<div class="container dashboard">
    <h2>Welcome, <%= session.getAttribute("username") %></h2>

    <% if (request.getAttribute("message") != null) { %>
    <div class="alert alert-success"><%= request.getAttribute("message") %></div>
    <% } %>
    <% if (request.getAttribute("error") != null) { %>
    <div class="alert alert-error"><%= request.getAttribute("error") %></div>
    <% } %>

    <!-- Dashboard Grid -->
    <div class="dashboard-grid">
        <a href="addCustomer" class="card">➕ Add New Customer</a>
        <a href="displayAccounts" class="card">📂 Display Accounts</a>
        <a href="addItem" class="card">📦 Add New Item</a>
        <a href="displayItems" class="card">🛠 Manage Items</a>
        <a href="calculateBill" class="card">🧾 Calculate Bill</a>
        <a href="help.jsp" class="card">❓ Help</a>
    </div>
</div>
</body>
</html>
