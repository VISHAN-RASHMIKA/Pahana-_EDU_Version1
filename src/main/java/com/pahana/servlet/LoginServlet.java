//
//
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
//    protected UserDAO getUserDAO() { // Add this for testing
//        return new UserDAO();
//    }
//    // Handles GET requests (e.g., when user visits /login in browser)
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        // Forward to login.jsp page
//        request.getRequestDispatcher("login.jsp").forward(request, response);
//    }
//
//    // Handles POST requests (e.g., when user submits login form)
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//
//        UserDAO userDAO = new UserDAO();
//
//        if (userDAO.validateUser(username, password)) {
//            // Login successful
//            HttpSession session = request.getSession();
//            session.setAttribute("username", username);
//            response.sendRedirect("dashboard.jsp");
//        } else {
//            // Login failed
//            request.setAttribute("error", "Invalid username or password");
//            request.getRequestDispatcher("login.jsp").forward(request, response);
//        }
//    }

//}
//package com.pahana.servlet;
//
//import com.pahana.dao.UserDAO;
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
//    private UserDAO userDAO;
//
//    public LoginServlet() {
//        this.userDAO = new UserDAO();
//    }
//
//    // For testing
//    protected UserDAO getUserDAO() {
//        return userDAO;
//    }
//
//    // For testing, allow setting a mock DAO
//    void setUserDAO(UserDAO userDAO) {
//        this.userDAO = userDAO;
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//
//        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
//            request.setAttribute("error", "Username and password are required");
//            request.getRequestDispatcher("login.jsp").forward(request, response);
//            return;
//        }
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
    private UserDAO userDAO;

    public LoginServlet() {
        this.userDAO = new UserDAO();
    }

    // For testing, allow injection of mock UserDAO
    protected void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    // For testing, allow access to UserDAO
    protected UserDAO getUserDAO() {
        return userDAO;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            request.setAttribute("error", "Username and password are required");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        if (userDAO.validateUser(username, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            response.sendRedirect("dashboard.jsp");
        } else {
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
