<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ page import="com.pahana.model.Item" %>--%>
<%--<%@ page import="java.util.List" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Manage Items - Pahana Edu Billing System</title>--%>
<%--    <link rel="stylesheet" href="css/style.css">--%>
<%--    <script>--%>
<%--        function confirmDelete() {--%>
<%--            return confirm("Are you sure you want to delete this item?");--%>
<%--        }--%>
<%--    </script>--%>
<%--</head>--%>
<%--<body>--%>
<%--<nav class="navbar">--%>
<%--    <div class="container">--%>
<%--        <span>Pahana Edu Billing System</span>--%>
<%--        <div>--%>
<%--            <a href="addItem" class="btn btn-primary">Add New Item</a>--%>
<%--            <a href="dashboard.jsp" class="btn btn-primary">Back to Dashboard</a>--%>
<%--            <a href="logout" class="btn btn-danger">Logout</a>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</nav>--%>
<%--<div class="container">--%>
<%--    <h2>Manage Items</h2>--%>
<%--    <% if (request.getAttribute("message") != null) { %>--%>
<%--    <div class="alert alert-success"><%= request.getAttribute("message") %></div>--%>
<%--    <% } %>--%>
<%--    <% if (request.getAttribute("error") != null) { %>--%>
<%--    <div class="alert alert-error"><%= request.getAttribute("error") %></div>--%>
<%--    <% } %>--%>
<%--    <% List<Item> items = (List<Item>) request.getAttribute("items"); %>--%>
<%--    <% if (items == null || items.isEmpty()) { %>--%>
<%--    <p>No items found.</p>--%>
<%--    <% } else { %>--%>
<%--    <table class="table">--%>
<%--        <thead>--%>
<%--        <tr>--%>
<%--            <th>Item ID</th>--%>
<%--            <th>Name</th>--%>
<%--            <th>Unit Price (LKR)</th>--%>
<%--            <th>Quantity</th>--%>
<%--            <th>Actions</th>--%>
<%--        </tr>--%>
<%--        </thead>--%>
<%--        <tbody>--%>
<%--        <% for (Item item : items) { %>--%>
<%--        <tr>--%>
<%--            <td><%= item.getItemId() %></td>--%>
<%--            <td><%= item.getName() %></td>--%>
<%--            <td><%= item.getUnitPrice() %></td>--%>
<%--            <td><%= item.getQuantity() %></td>--%>
<%--            <td>--%>
<%--                <a href="editItem?itemId=<%= item.getItemId() %>" class="btn btn-primary">Edit</a>--%>
<%--                <a href="deleteItem?itemId=<%= item.getItemId() %>" class="btn btn-danger" onclick="return confirmDelete()">Delete</a>--%>
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
<%@ page import="com.pahana.model.Item" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Manage Items - Pahana Edu Billing System</title>
    <link rel="stylesheet" href="css/style.css">
    <script>
        function confirmDelete() {
            return confirm("Are you sure you want to delete this item?");
        }
    </script>
</head>
<body>
<nav class="navbar">
    <div class="container">
        <span>Pahana Edu Billing System</span>
        <div>
            <a href="addItem" class="btn btn-primary">Add New Item</a>
            <a href="dashboard.jsp" class="btn btn-primary">Back to Dashboard</a>
            <a href="logout" class="btn btn-danger">Logout</a>
        </div>
    </div>
</nav>
<div class="container">
    <h2>Manage Items</h2>
    <% if (request.getAttribute("message") != null) { %>
    <div class="alert alert-success"><%= request.getAttribute("message") %></div>
    <% } %>
    <% if (request.getAttribute("error") != null) { %>
    <div class="alert alert-error"><%= request.getAttribute("error") %></div>
    <% } %>
    <% List<Item> items = (List<Item>) request.getAttribute("items"); %>
    <% if (items == null || items.isEmpty()) { %>
    <p>No items found.</p>
    <% } else { %>
    <table class="table">
        <thead>
        <tr>
            <th>Item ID</th>
            <th>Name</th>
            <th>Unit Price (LKR)</th>
            <th>Quantity</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <% for (Item item : items) { %>
        <tr>
            <td><%= item.getItemId() %></td>
            <td><%= item.getName() %></td>
            <td><%= item.getUnitPrice() %></td>
            <td><%= item.getQuantity() %></td>
            <td>
                <a href="editItem?itemId=<%= item.getItemId() %>" class="btn btn-primary">Edit</a>
                <a href="deleteItem?itemId=<%= item.getItemId() %>" class="btn btn-danger" onclick="return confirmDelete()">Delete</a>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
    <% } %>
</div>
</body>
</html>