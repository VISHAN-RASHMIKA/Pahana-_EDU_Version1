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
//import java.util.List;
//
//@WebServlet("/displayItems")
//public class DisplayItemsServlet extends HttpServlet {
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        ItemDAO itemDAO = new ItemDAO();
//        List<Item> items = itemDAO.getAllItems();
//        request.setAttribute("items", items);
//        request.getRequestDispatcher("displayItems.jsp").forward(request, response);
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
import java.util.List;

@WebServlet("/displayItems")
public class DisplayItemsServlet extends HttpServlet {
    private ItemDAO itemDAO;

    public DisplayItemsServlet() {
        this.itemDAO = new ItemDAO();
    }

    // For testing
    protected void setItemDAO(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Item> items = itemDAO.getAllItems();
        request.setAttribute("items", items);
        request.getRequestDispatcher("displayItems.jsp").forward(request, response);
    }
}