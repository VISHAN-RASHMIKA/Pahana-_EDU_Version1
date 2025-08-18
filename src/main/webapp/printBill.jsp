<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ page import="com.pahana.model.Bill" %>--%>
<%--<%@ page import="com.pahana.model.BillItem" %>--%>
<%--<%@ page import="com.pahana.model.Customer" %>--%>
<%--<%@ page import="com.pahana.dao.ItemDAO" %>--%>
<%--<%@ page import="com.pahana.model.Item" %>--%>
<%--<%@ page import="java.util.List" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Print Bill - Pahana Edu Billing System</title>--%>
<%--    <link rel="stylesheet" href="css/style.css">--%>
<%--    <script>--%>
<%--        function printBill() {--%>
<%--            window.print();--%>
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
<%--    <h2>Bill Details</h2>--%>
<%--    <% Customer customer = (Customer) request.getAttribute("customer"); %>--%>
<%--    <% Bill bill = (Bill) request.getAttribute("bill"); %>--%>
<%--    <% List<BillItem> billItems = (List<BillItem>) request.getAttribute("billItems"); %>--%>
<%--    <% ItemDAO itemDAO = (ItemDAO) request.getAttribute("itemDAO"); %>--%>
<%--    <p><strong>Customer:</strong> <%= customer.getFirstName() %> <%= customer.getLastName() %> (Account: <%= customer.getAccountNumber() %>)</p>--%>
<%--    <p><strong>Bill Date:</strong> <%= bill.getBillDate() %></p>--%>
<%--    <table class="table">--%>
<%--        <thead>--%>
<%--        <tr>--%>
<%--            <th>Item</th>--%>
<%--            <th>Unit Price (LKR)</th>--%>
<%--            <th>Quantity</th>--%>
<%--            <th>Subtotal (LKR)</th>--%>
<%--        </tr>--%>
<%--        </thead>--%>
<%--        <tbody>--%>
<%--        <% for (BillItem billItem : billItems) { %>--%>
<%--        <% Item item = itemDAO.getItem(billItem.getItemId()); %>--%>
<%--        <tr>--%>
<%--            <td><%= item.getName() %></td>--%>
<%--            <td><%= item.getUnitPrice() %></td>--%>
<%--            <td><%= billItem.getQuantity() %></td>--%>
<%--            <td><%= item.getUnitPrice() * billItem.getQuantity() %></td>--%>
<%--        </tr>--%>
<%--        <% } %>--%>
<%--        </tbody>--%>
<%--    </table>--%>
<%--    <p><strong>Total Amount:</strong> LKR <%= bill.getTotalAmount() %></p>--%>
<%--    <button onclick="printBill()" class="btn btn-primary">Print Bill</button>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>


<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ page import="com.pahana.model.Bill" %>--%>
<%--<%@ page import="com.pahana.model.BillItem" %>--%>
<%--<%@ page import="com.pahana.model.Customer" %>--%>
<%--<%@ page import="com.pahana.dao.ItemDAO" %>--%>
<%--<%@ page import="com.pahana.model.Item" %>--%>
<%--<%@ page import="java.util.List" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Print Bill - Pahana Edu Billing System</title>--%>
<%--    <link rel="stylesheet" href="css/style.css">--%>
<%--    <script>--%>
<%--        function printBill() {--%>
<%--            window.print();--%>
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
<%--<div class="container">--%>
<%--    <div class="card">--%>
<%--        <h2>Bill Details</h2>--%>
<%--        <% Customer customer = (Customer) request.getAttribute("customer"); %>--%>
<%--        <% Bill bill = (Bill) request.getAttribute("bill"); %>--%>
<%--        <% List<BillItem> billItems = (List<BillItem>) request.getAttribute("billItems"); %>--%>
<%--        <% ItemDAO itemDAO = (ItemDAO) request.getAttribute("itemDAO"); %>--%>
<%--        <p><strong>Customer:</strong> <%= customer.getFirstName() %> <%= customer.getLastName() %> (Account: <%= customer.getAccountNumber() %>)</p>--%>
<%--        <p><strong>Bill Date:</strong> <%= bill.getBillDate() %></p>--%>
<%--        <table class="table">--%>
<%--            <thead>--%>
<%--            <tr>--%>
<%--                <th>Item</th>--%>
<%--                <th>Unit Price (LKR)</th>--%>
<%--                <th>Quantity</th>--%>
<%--                <th>Subtotal (LKR)</th>--%>
<%--            </tr>--%>
<%--            </thead>--%>
<%--            <tbody>--%>
<%--            <% for (BillItem billItem : billItems) { %>--%>
<%--            <% Item item = itemDAO.getItem(billItem.getItemId()); %>--%>
<%--            <tr>--%>
<%--                <td><%= item.getName() %></td>--%>
<%--                <td><%= item.getUnitPrice() %></td>--%>
<%--                <td><%= billItem.getQuantity() %></td>--%>
<%--                <td><%= item.getUnitPrice() * billItem.getQuantity() %></td>--%>
<%--            </tr>--%>
<%--            <% } %>--%>
<%--            </tbody>--%>
<%--        </table>--%>
<%--        <p><strong>Total Amount:</strong> LKR <%= bill.getTotalAmount() %></p>--%>
<%--        <button onclick="printBill()" class="btn btn-primary">Print Bill</button>--%>
<%--    </div>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>



<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pahana.model.Bill" %>
<%@ page import="com.pahana.model.BillItem" %>
<%@ page import="com.pahana.model.Customer" %>
<%@ page import="com.pahana.dao.ItemDAO" %>
<%@ page import="com.pahana.model.Item" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Print Bill - Pahana Edu Billing System</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        @media print {
            .navbar, .btn-print, .no-print {
                display: none;
            }
            .card {
                box-shadow: none;
                border: none;
                padding: 0;
            }
            .container {
                margin: 0;
                padding: 10px;
            }
            .bill-header {
                text-align: center;
                border-bottom: 2px solid #2c3e50;
                padding-bottom: 10px;
                margin-bottom: 20px;
            }
            .bill-footer {
                text-align: right;
                border-top: 2px solid #2c3e50;
                padding-top: 10px;
                margin-top: 20px;
            }
            .table {
                border: 1px solid #2c3e50;
            }
            .table th, .table td {
                border: 1px solid #2c3e50;
            }
        }
    </style>
    <script>
        function printBill() {
            window.print();
        }
    </script>
</head>
<body>
<nav class="navbar no-print">
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
        <div class="bill-header">
            <h2>Pahana Edu Bookshop - Invoice</h2>
            <p>123 Book Street, Colombo City, Sri Lanka</p>
            <p>Tel: +94 11 234 5678 | Email: info@pahanaedu.lk</p>
        </div>
        <% Customer customer = (Customer) request.getAttribute("customer"); %>
        <% Bill bill = (Bill) request.getAttribute("bill"); %>
        <% List<BillItem> billItems = (List<BillItem>) request.getAttribute("billItems"); %>
        <% ItemDAO itemDAO = (ItemDAO) request.getAttribute("itemDAO"); %>
        <p><strong>Customer:</strong> <%= customer.getFirstName() %> <%= customer.getLastName() %> (Account: <%= customer.getAccountNumber() %>)</p>
        <p><strong>Address:</strong> <%= customer.getAddress() %></p>
        <p><strong>Telephone:</strong> <%= customer.getTelephone() %></p>
        <p><strong>Bill Date:</strong> <%= bill.getBillDate() %></p>
        <table class="table">
            <thead>
            <tr>
                <th>Item</th>
                <th>Unit Price (LKR)</th>
                <th>Quantity</th>
                <th>Subtotal (LKR)</th>
            </tr>
            </thead>
            <tbody>
            <% for (BillItem billItem : billItems) { %>
            <% Item item = itemDAO.getItem(billItem.getItemId()); %>
            <tr>
                <td><%= item.getName() %></td>
                <td><%= String.format("%.2f", item.getUnitPrice()) %></td>
                <td><%= billItem.getQuantity() %></td>
                <td><%= String.format("%.2f", item.getUnitPrice() * billItem.getQuantity()) %></td>
            </tr>
            <% } %>
            </tbody>
        </table>
        <div class="bill-footer">
            <p><strong>Total Amount:</strong> LKR <%= String.format("%.2f", bill.getTotalAmount()) %></p>
        </div>
        <button onclick="printBill()" class="btn btn-primary btn-print no-print">Print Bill</button>
    </div>
</div>
</body>
</html>