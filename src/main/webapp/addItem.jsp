


<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Add Item - Pahana Edu Billing System</title>--%>
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
<%--    <h2>Add New Item</h2>--%>
<%--    <% if (request.getAttribute("error") != null) { %>--%>
<%--    <div class="alert alert-error"><%= request.getAttribute("error") %></div>--%>
<%--    <% } %>--%>
<%--    <form action="addItem" method="post">--%>
<%--        <label>Item ID:</label>--%>
<%--        <input type="text" name="itemId" value="<%= request.getAttribute("itemId") != null ? request.getAttribute("itemId") : "" %>" readonly>--%>
<%--        <label>Name:</label>--%>
<%--        <input type="text" name="name" required>--%>
<%--        <label>Unit Price (LKR):</label>--%>
<%--        <input type="number" step="0.01" name="unitPrice" required>--%>
<%--        <label>Quantity:</label>--%>
<%--        <input type="number" name="quantity" min="0" required>--%>
<%--        <button type="submit" class="btn btn-primary">Add Item</button>--%>
<%--    </form>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>



<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Item - Pahana Edu Billing System</title>
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
        <h2>Add New Item</h2>
        <% if (request.getAttribute("error") != null) { %>
        <div class="alert alert-error"><%= request.getAttribute("error") %></div>
        <% } %>
        <form action="addItem" method="post">
            <label for="itemId">Item ID:</label>
            <input type="text" id="itemId" name="itemId" value="<%= request.getAttribute("itemId") != null ? request.getAttribute("itemId") : "" %>" readonly>
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required>
            <label for="unitPrice">Unit Price (LKR):</label>
            <input type="number" id="unitPrice" name="unitPrice" step="0.01" min="0" required>
            <label for="quantity">Quantity:</label>
            <input type="number" id="quantity" name="quantity" min="0" required>
            <button type="submit" class="btn btn-primary">Add Item</button>
        </form>
    </div>
</div>
</body>
</html>