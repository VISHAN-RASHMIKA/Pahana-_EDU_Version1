//package com.pahana.servlet;
//
//import com.pahana.dao.ItemDAO;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebServlet("/deleteItem")
//public class DeleteItemServlet extends HttpServlet {
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String itemId = request.getParameter("itemId");
//        if (itemId == null || itemId.trim().isEmpty()) {
//            request.setAttribute("error", "Item ID is missing");
//            request.getRequestDispatcher("displayItems.jsp").forward(request, response);
//            return;
//        }
//        ItemDAO itemDAO = new ItemDAO();
//        if (itemDAO.deleteItem(itemId)) {
//            request.setAttribute("message", "Item deleted successfully");
//        } else {
//            request.setAttribute("error", "Failed to delete item: " + itemId);
//        }
//        response.sendRedirect("displayItems");
//    }
//}


package com.pahana.servlet;

import com.pahana.dao.ItemDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteItem")
public class DeleteItemServlet extends HttpServlet {
    private ItemDAO itemDAO;

    public DeleteItemServlet() {
        this.itemDAO = new ItemDAO();
    }

    // For testing
    protected void setItemDAO(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String itemId = request.getParameter("itemId");
//        if (itemId == null || itemId.trim().isEmpty()) {
//            request.setAttribute("error", "Item ID is missing");
//            request.getRequestDispatcher("displayItems.jsp").forward(request, response);
//            return;
//        }
//
//        if (itemDAO.deleteItem(itemId)) {
//            request.setAttribute("message", "Item deleted successfully");
//        } else {
//            request.setAttribute("error", "Failed to delete item: " + itemId);
//        }
//        response.sendRedirect("displayItems");
//    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String itemId = request.getParameter("itemId");
        if (itemId == null || itemId.trim().isEmpty()) {
            request.setAttribute("error", "Item ID is missing");
            request.getRequestDispatcher("displayItems.jsp").forward(request, response);
            return;
        }

        if (itemDAO.deleteItem(itemId)) {
            request.setAttribute("message", "Item deleted successfully");
            response.sendRedirect("displayItems");
        } else {
            request.setAttribute("error", "Failed to delete item: " + itemId);
            request.getRequestDispatcher("displayItems.jsp").forward(request, response);
        }
    }

}