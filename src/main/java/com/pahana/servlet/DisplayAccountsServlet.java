//package com.pahana.servlet;
//
//import com.pahana.dao.CustomerDAO;
//import com.pahana.model.Customer;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
//@WebServlet("/displayAccounts")
//public class DisplayAccountsServlet extends HttpServlet {
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        CustomerDAO customerDAO = new CustomerDAO();
//        List<Customer> customers = customerDAO.getAllCustomers();
//        request.setAttribute("customers", customers);
//        request.getRequestDispatcher("displayAccounts.jsp").forward(request, response);
//    }
//}


package com.pahana.servlet;

import com.pahana.dao.CustomerDAO;
import com.pahana.model.Customer;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/displayAccounts")
public class DisplayAccountsServlet extends HttpServlet {
    private CustomerDAO customerDAO;

    public DisplayAccountsServlet() {
        this.customerDAO = new CustomerDAO();
    }

    // For testing
    protected void setCustomerDAO(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Customer> customers = customerDAO.getAllCustomers();
        request.setAttribute("customers", customers);
        request.getRequestDispatcher("displayAccounts.jsp").forward(request, response);
    }
}