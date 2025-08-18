<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ page import="com.pahana.model.Item" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Edit Item - Pahana Edu Billing System</title>--%>
<%--    <link rel="stylesheet" href="css/style.css">--%>
<%--</head>--%>
<%--<body>--%>
<%--<nav class="navbar">--%>
<%--    <div class="container">--%>
<%--        <span>Pahana Edu Billing System</span>--%>
<%--        <div>--%>
<%--            <a href="displayItems" class="btn btn-primary">Back to Items</a>--%>
<%--            <a href="logout" class="btn btn-danger">Logout</a>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</nav>--%>
<%--<div class="card">--%>
<%--    <h2>Edit Item</h2>--%>
<%--    <% if (request.getAttribute("error") != null) { %>--%>
<%--    <div class="alert alert-error"><%= request.getAttribute("error") %></div>--%>
<%--    <% } %>--%>
<%--    <% Item item = (Item) request.getAttribute("item"); %>--%>
<%--    <form action="editItem" method="post">--%>
<%--        <input type="hidden" name="itemId" value="<%= item.getItemId() %>">--%>
<%--        <label>Name:</label>--%>
<%--        <input type="text" name="name" value="<%= item.getName() %>" required>--%>
<%--        <label>Unit Price (LKR):</label>--%>
<%--        <input type="number" step="0.01" name="unitPrice" value="<%= item.getUnitPrice() %>" required>--%>
<%--        <label>Quantity:</label>--%>
<%--        <input type="number" name="quantity" value="<%= item.getQuantity() %>" min="0" required>--%>
<%--        <button type="submit" class="btn btn-primary">Update Item</button>--%>
<%--    </form>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pahana.model.Item" %>
<html>
<head>
    <title>Edit Item - Pahana Edu Billing System</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<nav class="navbar">
    <div class="container">
        <span>Pahana Edu Billing System</span>
        <div>
            <a href="displayItems" class="btn btn-primary">Back to Items</a>
            <a href="logout" class="btn btn-danger">Logout</a>
        </div>
    </div>
</nav>
<div class="container">
    <div class="card">
        <h2>Edit Item</h2>
        <% if (request.getAttribute("error") != null) { %>
        <div class="alert alert-error"><%= request.getAttribute("error") %></div>
        <% } %>
        <% Item item = (Item) request.getAttribute("item"); %>
        <form action="editItem" method="post">
            <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" value="<%= item.getName() %>" required>
            <label for="unitPrice">Unit Price (LKR):</label>
            <input type="number" id="unitPrice" name="unitPrice" step="0.01" value="<%= item.getUnitPrice() %>" required>
            <label for="quantity">Quantity:</label>
            <input type="number" id="quantity" name="quantity" value="<%= item.getQuantity() %>" min="0" required>
            <button type="submit" class="btn btn-primary">Update Item</button>
        </form>
    </div>
</div>
</body>
</html>