//
//
//package com.pahana.servlet;
//
//import com.pahana.dao.BillDAO;
//import com.pahana.dao.CustomerDAO;
//import com.pahana.dao.ItemDAO;
//import com.pahana.model.Bill;
//import com.pahana.model.BillItem;
//import com.pahana.model.Customer;
//import com.pahana.model.Item;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//@WebServlet("/calculateBill")
//public class CalculateBillServlet extends HttpServlet {
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        CustomerDAO customerDAO = new CustomerDAO();
//        ItemDAO itemDAO = new ItemDAO();
//        List<Customer> customers = customerDAO.getAllCustomers();
//        List<Item> items = itemDAO.getAllItems();
//        request.setAttribute("customers", customers);
//        request.setAttribute("items", items);
//        request.getRequestDispatcher("calculateBill.jsp").forward(request, response);
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String accountNumber = request.getParameter("accountNumber");
//        String[] itemIds = request.getParameterValues("itemIds");
//        String[] quantities = request.getParameterValues("quantities");
//
//        // Validate inputs
//        if (accountNumber == null || accountNumber.trim().isEmpty()) {
//            request.setAttribute("error", "Please select a customer");
//            setupFormAttributes(request);
//            request.getRequestDispatcher("calculateBill.jsp").forward(request, response);
//            return;
//        }
//
//        if (itemIds == null || quantities == null || itemIds.length == 0 || quantities.length == 0 || itemIds.length != quantities.length) {
//            request.setAttribute("error", "Please select at least one item and specify quantities");
//            setupFormAttributes(request);
//            request.getRequestDispatcher("calculateBill.jsp").forward(request, response);
//            return;
//        }
//
//        CustomerDAO customerDAO = new CustomerDAO();
//        ItemDAO itemDAO = new ItemDAO();
//        BillDAO billDAO = new BillDAO();
//        Customer customer = customerDAO.getCustomer(accountNumber.trim());
//
//        if (customer == null) {
//            request.setAttribute("error", "Customer not found for account number: " + accountNumber);
//            setupFormAttributes(request);
//            request.getRequestDispatcher("calculateBill.jsp").forward(request, response);
//            return;
//        }
//
//        List<BillItem> billItems = new ArrayList<>();
//        double totalAmount = 0.0;
//
//        // Validate and collect bill items
//        for (int i = 0; i < itemIds.length; i++) {
//            String itemId = itemIds[i];
//            int quantity;
//            try {
//                quantity = Integer.parseInt(quantities[i]);
//                if (quantity <= 0) {
//                    throw new NumberFormatException("Quantity must be positive");
//                }
//            } catch (NumberFormatException e) {
//                request.setAttribute("error", "Invalid quantity for item: " + itemId);
//                setupFormAttributes(request);
//                request.getRequestDispatcher("calculateBill.jsp").forward(request, response);
//                return;
//            }
//
//            Item item = itemDAO.getItem(itemId);
//            if (item == null) {
//                request.setAttribute("error", "Item not found: " + itemId);
//                setupFormAttributes(request);
//                request.getRequestDispatcher("calculateBill.jsp").forward(request, response);
//                return;
//            }
//
//            if (item.getQuantity() < quantity) {
//                request.setAttribute("error", "Insufficient stock for item: " + item.getName() + " (Available: " + item.getQuantity() + ")");
//                setupFormAttributes(request);
//                request.getRequestDispatcher("calculateBill.jsp").forward(request, response);
//                return;
//            }
//
//            billItems.add(new BillItem(0, 0, itemId, quantity));
//            totalAmount += item.getUnitPrice() * quantity;
//        }
//
//        // Create bill and update inventory
//        Bill bill = new Bill(0, accountNumber, totalAmount, new java.util.Date());
//        if (billDAO.addBillWithInventoryUpdate(bill, billItems, itemDAO)) {
//            request.setAttribute("bill", bill);
//            request.setAttribute("customer", customer);
//            request.setAttribute("billItems", billItems);
//            request.setAttribute("itemDAO", itemDAO);
//            request.getRequestDispatcher("printBill.jsp").forward(request, response);
//        } else {
//            request.setAttribute("error", "Failed to generate bill or update inventory");
//            setupFormAttributes(request);
//            request.getRequestDispatcher("calculateBill.jsp").forward(request, response);
//        }
//    }
//
//    private void setupFormAttributes(HttpServletRequest request) {
//        CustomerDAO customerDAO = new CustomerDAO();
//        ItemDAO itemDAO = new ItemDAO();
//        request.setAttribute("customers", customerDAO.getAllCustomers());
//        request.setAttribute("items", itemDAO.getAllItems());
//    }
//}



package com.pahana.servlet;

import com.pahana.dao.BillDAO;
import com.pahana.dao.CustomerDAO;
import com.pahana.dao.ItemDAO;
import com.pahana.model.Bill;
import com.pahana.model.BillItem;
import com.pahana.model.Customer;
import com.pahana.model.Item;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/calculateBill")
public class CalculateBillServlet extends HttpServlet {
    private CustomerDAO customerDAO;
    private ItemDAO itemDAO;
    private BillDAO billDAO;

    public CalculateBillServlet() {
        this.customerDAO = new CustomerDAO();
        this.itemDAO = new ItemDAO();
        this.billDAO = new BillDAO();
    }

    // For testing
    protected void setCustomerDAO(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    protected void setItemDAO(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

    protected void setBillDAO(BillDAO billDAO) {
        this.billDAO = billDAO;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Customer> customers = customerDAO.getAllCustomers();
        List<Item> items = itemDAO.getAllItems();
        request.setAttribute("customers", customers);
        request.setAttribute("items", items);
        request.getRequestDispatcher("calculateBill.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNumber = request.getParameter("accountNumber");
        String[] itemIds = request.getParameterValues("itemIds");
        String[] quantities = request.getParameterValues("quantities");

        // Validate inputs
        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            request.setAttribute("error", "Please select a customer");
            setupFormAttributes(request);
            request.getRequestDispatcher("calculateBill.jsp").forward(request, response);
            return;
        }

        if (itemIds == null || quantities == null || itemIds.length == 0 || quantities.length == 0 || itemIds.length != quantities.length) {
            request.setAttribute("error", "Please select at least one item and specify quantities");
            setupFormAttributes(request);
            request.getRequestDispatcher("calculateBill.jsp").forward(request, response);
            return;
        }

        Customer customer = customerDAO.getCustomer(accountNumber.trim());
        if (customer == null) {
            request.setAttribute("error", "Customer not found for account number: " + accountNumber);
            setupFormAttributes(request);
            request.getRequestDispatcher("calculateBill.jsp").forward(request, response);
            return;
        }

        List<BillItem> billItems = new ArrayList<>();
        double totalAmount = 0.0;
        // Validate and collect bill items
        for (int i = 0; i < itemIds.length; i++) {
            String itemId = itemIds[i];
            int quantity;
            try {
                quantity = Integer.parseInt(quantities[i]);
                if (quantity <= 0) {
                    throw new NumberFormatException("Quantity must be positive");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Invalid quantity for item: " + itemId);
                setupFormAttributes(request);
                request.getRequestDispatcher("calculateBill.jsp").forward(request, response);
                return;
            }

            Item item = itemDAO.getItem(itemId);
            if (item == null) {
                request.setAttribute("error", "Item not found: " + itemId);
                setupFormAttributes(request);
                request.getRequestDispatcher("calculateBill.jsp").forward(request, response);
                return;
            }

            if (item.getQuantity() < quantity) {
                request.setAttribute("error", "Insufficient stock for item: " + item.getName() + " (Available: " + item.getQuantity() + ")");
                setupFormAttributes(request);
                request.getRequestDispatcher("calculateBill.jsp").forward(request, response);
                return;
            }

            billItems.add(new BillItem(0, 0, itemId, quantity));
            totalAmount += item.getUnitPrice() * quantity;
        }

        // Create bill and update inventory
        Bill bill = new Bill(0, accountNumber, totalAmount, new java.util.Date());
        if (billDAO.addBillWithInventoryUpdate(bill, billItems, itemDAO)) {
            request.setAttribute("bill", bill);
            request.setAttribute("customer", customer);
            request.setAttribute("billItems", billItems);
            request.setAttribute("itemDAO", itemDAO);
            request.getRequestDispatcher("printBill.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Failed to generate bill or update inventory");
            setupFormAttributes(request);
            request.getRequestDispatcher("calculateBill.jsp").forward(request, response);
        }
    }

    private void setupFormAttributes(HttpServletRequest request) {
        request.setAttribute("customers", customerDAO.getAllCustomers());
        request.setAttribute("items", itemDAO.getAllItems());
    }
}