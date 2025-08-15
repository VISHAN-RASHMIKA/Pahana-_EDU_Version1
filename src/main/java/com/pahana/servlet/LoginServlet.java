//package com.pahana.servlet;
//
//import com.pahana.dao.UserDAO;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//@WebServlet("/login")
//public class LoginServlet extends HttpServlet {
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        UserDAO userDAO = new UserDAO();
//
//        if (userDAO.validateUser(username, password)) {
//            HttpSession session = request.getSession();
//            session.setAttribute("username", username);
//            response.sendRedirect("dashboard.jsp");
//        } else {
//            request.setAttribute("error", "Invalid username or password");
//            request.getRequestDispatcher("login.jsp").forward(request, response);
//        }
//    }
//}


package com.pahana.servlet;

import com.pahana.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    // Handles GET requests (e.g., when user visits /login in browser)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to login.jsp page
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    // Handles POST requests (e.g., when user submits login form)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDAO userDAO = new UserDAO();

        if (userDAO.validateUser(username, password)) {
            // Login successful
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            response.sendRedirect("dashboard.jsp");
        } else {
            // Login failed
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
