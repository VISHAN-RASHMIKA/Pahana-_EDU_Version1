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
//
//@WebServlet("/editItem")
//public class EditItemServlet extends HttpServlet {
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String itemId = request.getParameter("itemId");
//        if (itemId == null || itemId.trim().isEmpty()) {
//            request.setAttribute("error", "Item ID is missing");
//            request.getRequestDispatcher("displayItems.jsp").forward(request, response);
//            return;
//        }
//        ItemDAO itemDAO = new ItemDAO();
//        Item item = itemDAO.getItem(itemId.trim());
//        if (item != null) {
//            request.setAttribute("item", item);
//            request.getRequestDispatcher("editItem.jsp").forward(request, response);
//        } else {
//            request.setAttribute("error", "Item not found for ID: " + itemId);
//            request.getRequestDispatcher("displayItems.jsp").forward(request, response);
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
//            request.getRequestDispatcher("editItem.jsp").forward(request, response);
//            return;
//        }
//
//        if (name.isEmpty()) {
//            request.setAttribute("error", "Name is required");
//            request.getRequestDispatcher("editItem.jsp").forward(request, response);
//            return;
//        }
//
//        Item item = new Item(itemId, name, unitPrice, quantity);
//        ItemDAO itemDAO = new ItemDAO();
//        if (itemDAO.updateItem(item)) {
//            request.setAttribute("message", "Item updated successfully");
//            request.getRequestDispatcher("displayItems.jsp").forward(request, response);
//        } else {
//            request.setAttribute("error", "Failed to update item");
//            request.getRequestDispatcher("editItem.jsp").forward(request, response);
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

@WebServlet("/editItem")
public class EditItemServlet extends HttpServlet {
    private ItemDAO itemDAO;

    public EditItemServlet() {
        this.itemDAO = new ItemDAO();
    }

    // For testing
    protected void setItemDAO(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String itemId = request.getParameter("itemId");
        if (itemId == null || itemId.trim().isEmpty()) {
            request.setAttribute("error", "Item ID is missing");
            request.getRequestDispatcher("displayItems.jsp").forward(request, response);
            return;
        }

        Item item = itemDAO.getItem(itemId.trim());
        if (item != null) {
            request.setAttribute("item", item);
            request.getRequestDispatcher("editItem.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Item not found for ID: " + itemId);
            request.getRequestDispatcher("displayItems.jsp").forward(request, response);
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
            request.getRequestDispatcher("editItem.jsp").forward(request, response);
            return;
        }

        if (name.isEmpty()) {
            request.setAttribute("error", "Name is required");
            request.getRequestDispatcher("editItem.jsp").forward(request, response);
            return;
        }

        Item item = new Item(itemId, name, unitPrice, quantity);
        if (itemDAO.updateItem(item)) {
            request.setAttribute("message", "Item updated successfully");
            request.getRequestDispatcher("displayItems.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Failed to update item");
            request.getRequestDispatcher("editItem.jsp").forward(request, response);
        }
    }
}