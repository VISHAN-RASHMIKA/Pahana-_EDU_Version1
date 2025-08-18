

<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ page import="com.pahana.model.Customer" %>--%>
<%--<%@ page import="com.pahana.model.Item" %>--%>
<%--<%@ page import="java.util.List" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Calculate Bill - Pahana Edu Billing System</title>--%>
<%--    <link rel="stylesheet" href="css/style.css">--%>
<%--    <script>--%>
<%--        function addItemRow() {--%>
<%--            const container = document.getElementById("itemRows");--%>
<%--            const row = document.createElement("div");--%>
<%--            row.className = "item-row";--%>
<%--            row.style.display = "flex";--%>
<%--            row.style.gap = "10px";--%>
<%--            row.style.marginBottom = "10px";--%>
<%--            row.innerHTML = `--%>
<%--                <select name="itemIds" required>--%>
<%--                    <% List<Item> items = (List<Item>) request.getAttribute("items"); %>--%>
<%--                    <% for (Item item : items) { %>--%>
<%--                        <option value="<%= item.getItemId() %>"><%= item.getName() %> (LKR <%= item.getUnitPrice() %>, Stock: <%= item.getQuantity() %>)</option>--%>
<%--                    <% } %>--%>
<%--                </select>--%>
<%--                <input type="number" name="quantities" min="1" required placeholder="Quantity">--%>
<%--                <button type="button" onclick="removeItemRow(this)" class="btn btn-danger">Remove</button>--%>
<%--            `;--%>
<%--            container.appendChild(row);--%>
<%--        }--%>

<%--        function removeItemRow(button) {--%>
<%--            button.parentElement.remove();--%>
<%--        }--%>
<%--    </script>--%>
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
<%--    <h2>Calculate Bill</h2>--%>
<%--    <% if (request.getAttribute("error") != null) { %>--%>
<%--    <div class="alert alert-error"><%= request.getAttribute("error") %></div>--%>
<%--    <% } %>--%>
<%--    <form action="calculateBill" method="post">--%>
<%--        <label>Select Customer:</label>--%>
<%--        <select name="accountNumber" required>--%>
<%--            <option value="">Select a customer</option>--%>
<%--            <% List<Customer> customers = (List<Customer>) request.getAttribute("customers"); %>--%>
<%--            <% for (Customer customer : customers) { %>--%>
<%--            <option value="<%= customer.getAccountNumber() %>"><%= customer.getFirstName() %> <%= customer.getLastName() %> (<%= customer.getAccountNumber() %>)</option>--%>
<%--            <% } %>--%>
<%--        </select>--%>
<%--        <div id="itemRows">--%>
<%--            <div class="item-row" style="display: flex; gap: 10px; margin-bottom: 10px;">--%>
<%--                <select name="itemIds" required>--%>
<%--                    <% for (Item item : items) { %>--%>
<%--                    <option value="<%= item.getItemId() %>"><%= item.getName() %> (LKR <%= item.getUnitPrice() %>, Stock: <%= item.getQuantity() %>)</option>--%>
<%--                    <% } %>--%>
<%--                </select>--%>
<%--                <input type="number" name="quantities" min="1" required placeholder="Quantity">--%>
<%--                <button type="button" onclick="removeItemRow(this)" class="btn btn-danger">Remove</button>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <button type="button" onclick="addItemRow()" class="btn btn-primary" style="margin-bottom: 10px;">Add Another Item</button>--%>
<%--        <button type="submit" class="btn btn-primary">Generate Bill</button>--%>
<%--    </form>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>




<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pahana.model.Customer" %>
<%@ page import="com.pahana.model.Item" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Calculate Bill - Pahana Edu Billing System</title>
    <link rel="stylesheet" href="css/style.css">
    <script>
        function addItemRow() {
            const container = document.getElementById("itemRows");
            const row = document.createElement("div");
            row.className = "item-row";
            row.innerHTML = `
                <select name="itemIds" required>
                    <% List<Item> items = (List<Item>) request.getAttribute("items"); %>
                    <% for (Item item : items) { %>
                        <option value="<%= item.getItemId() %>"><%= item.getName() %> (LKR <%= item.getUnitPrice() %>, Stock: <%= item.getQuantity() %>)</option>
                    <% } %>
                </select>
                <input type="number" name="quantities" min="1" required placeholder="Quantity">
                <button type="button" onclick="removeItemRow(this)" class="btn btn-danger">Remove</button>
            `;
            container.appendChild(row);
        }

        function removeItemRow(button) {
            button.parentElement.remove();
        }
    </script>
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
        <h2>Calculate Bill</h2>
        <% if (request.getAttribute("error") != null) { %>
        <div class="alert alert-error"><%= request.getAttribute("error") %></div>
        <% } %>
        <form action="calculateBill" method="post">
            <label for="accountNumber">Select Customer:</label>
            <select id="accountNumber" name="accountNumber" required>
                <option value="">Select a customer</option>
                <% List<Customer> customers = (List<Customer>) request.getAttribute("customers"); %>
                <% for (Customer customer : customers) { %>
                <option value="<%= customer.getAccountNumber() %>"><%= customer.getFirstName() %> <%= customer.getLastName() %> (<%= customer.getAccountNumber() %>)</option>
                <% } %>
            </select>
            <div id="itemRows">
                <div class="item-row">
                    <select name="itemIds" required>
                        <% for (Item item : items) { %>
                        <option value="<%= item.getItemId() %>"><%= item.getName() %> (LKR <%= item.getUnitPrice() %>, Stock: <%= item.getQuantity() %>)</option>
                        <% } %>
                    </select>
                    <input type="number" name="quantities" min="1" required placeholder="Quantity">
                    <button type="button" onclick="removeItemRow(this)" class="btn btn-danger">Remove</button>
                </div>
            </div>
            <button type="button" onclick="addItemRow()" class="btn btn-primary">Add Another Item</button>
            <button type="submit" class="btn btn-primary">Generate Bill</button>
        </form>
    </div>
</div>
</body>
</html>