//
//
//
//package com.pahana.servlet;
//
//import com.pahana.dao.ItemDAO;
//import com.pahana.model.Item;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.sql.SQLException;
//
//@WebServlet("/addItem")
//public class AddItemServlet extends HttpServlet {
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        try {
//            ItemDAO itemDAO = new ItemDAO();
//            String itemId = itemDAO.generateItemId();
//            if (itemId == null) {
//                throw new SQLException("Failed to generate item ID");
//            }
//            request.setAttribute("itemId", itemId);
//            request.getRequestDispatcher("addItem.jsp").forward(request, response);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            request.setAttribute("error", "Failed to generate item ID: " + e.getMessage());
//            request.getRequestDispatcher("addItem.jsp").forward(request, response);
//        }
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String itemId = request.getParameter("itemId");
//        String name = request.getParameter("name");
//        double unitPrice;
//        int quantity;
//        try {
//            unitPrice = Double.parseDouble(request.getParameter("unitPrice"));
//            quantity = Integer.parseInt(request.getParameter("quantity"));
//            if (quantity < 0) {
//                throw new NumberFormatException("Quantity cannot be negative");
//            }
//        } catch (NumberFormatException e) {
//            request.setAttribute("error", "Invalid unit price or quantity");
//            request.setAttribute("itemId", itemId);
//            request.getRequestDispatcher("addItem.jsp").forward(request, response);
//            return;
//        }
//
//        if (itemId == null || name.isEmpty()) {
//            request.setAttribute("error", "Item ID and name are required");
//            request.setAttribute("itemId", itemId);
//            request.getRequestDispatcher("addItem.jsp").forward(request, response);
//            return;
//        }
//
//        Item item = new Item(itemId, name, unitPrice, quantity);
//        ItemDAO itemDAO = new ItemDAO();
//        if (itemDAO.addItem(item)) {
//            request.setAttribute("message", "Item added successfully");
//            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
//        } else {
//            request.setAttribute("error", "Failed to add item");
//            request.setAttribute("itemId", itemId);
//            request.getRequestDispatcher("addItem.jsp").forward(request, response);
//        }
//    }
//}



package com.pahana.servlet;

import com.pahana.dao.ItemDAO;
import com.pahana.model.Item;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/addItem")
public class AddItemServlet extends HttpServlet {
    private ItemDAO itemDAO;

    public AddItemServlet() {
        this.itemDAO = new ItemDAO();
    }

    // For testing
    protected void setItemDAO(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String itemId = itemDAO.generateItemId();
            if (itemId == null) {
                throw new SQLException("Failed to generate item ID");
            }
            request.setAttribute("itemId", itemId);
            request.getRequestDispatcher("addItem.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Failed to generate item ID: " + e.getMessage());
            request.getRequestDispatcher("addItem.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String itemId = request.getParameter("itemId");
        String name = request.getParameter("name");
        double unitPrice;
        int quantity;
        try {
            unitPrice = Double.parseDouble(request.getParameter("unitPrice"));
            quantity = Integer.parseInt(request.getParameter("quantity"));
            if (quantity < 0) {
                throw new NumberFormatException("Quantity cannot be negative");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid unit price or quantity");
            request.setAttribute("itemId", itemId);
            request.getRequestDispatcher("addItem.jsp").forward(request, response);
            return;
        }

        if (itemId == null || name.isEmpty()) {
            request.setAttribute("error", "Item ID and name are required");
            request.setAttribute("itemId", itemId);
            request.getRequestDispatcher("addItem.jsp").forward(request, response);
            return;
        }

        Item item = new Item(itemId, name, unitPrice, quantity);
        if (itemDAO.addItem(item)) {
            request.setAttribute("message", "Item added successfully");
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Failed to add item");
            request.setAttribute("itemId", itemId);
            request.getRequestDispatcher("addItem.jsp").forward(request, response);
        }
    }
}