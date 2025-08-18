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
//
//@WebServlet("/editCustomer")
//public class EditCustomerServlet extends HttpServlet {
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String accountNumber = request.getParameter("accountNumber");
//        if (accountNumber == null || accountNumber.trim().isEmpty()) {
//            request.setAttribute("error", "Account number is missing");
//            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
//            return;
//        }
//        CustomerDAO customerDAO = new CustomerDAO();
//        Customer customer = customerDAO.getCustomer(accountNumber.trim());
//        if (customer != null) {
//            request.setAttribute("customer", customer);
//            request.getRequestDispatcher("editCustomer.jsp").forward(request, response);
//        } else {
//            request.setAttribute("error", "Customer not found for account number: " + accountNumber);
//            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
//        }
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String accountNumber = request.getParameter("accountNumber");
//        String firstName = request.getParameter("firstName");
//        String lastName = request.getParameter("lastName");
//        String address = request.getParameter("address");
//        String telephone = request.getParameter("telephone");
//
//        if (firstName.isEmpty() || lastName.isEmpty() || address.isEmpty() || telephone.isEmpty()) {
//            request.setAttribute("error", "All fields are required");
//            request.getRequestDispatcher("editCustomer.jsp").forward(request, response);
//            return;
//        }
//
//        Customer customer = new Customer(accountNumber, firstName, lastName, address, telephone);
//        CustomerDAO customerDAO = new CustomerDAO();
//        if (customerDAO.updateCustomer(customer)) {
//            request.setAttribute("message", "Customer updated successfully");
//            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
//        } else {
//            request.setAttribute("error", "Failed to update customer");
//            request.getRequestDispatcher("editCustomer.jsp").forward(request, response);
//        }
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

@WebServlet("/editCustomer")
public class EditCustomerServlet extends HttpServlet {
    private CustomerDAO customerDAO;

    public EditCustomerServlet() {
        this.customerDAO = new CustomerDAO();
    }

    // For testing
    protected void setCustomerDAO(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNumber = request.getParameter("accountNumber");
        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            request.setAttribute("error", "Account number is missing");
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
            return;
        }

        Customer customer = customerDAO.getCustomer(accountNumber.trim());
        if (customer != null) {
            request.setAttribute("customer", customer);
            request.getRequestDispatcher("editCustomer.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Customer not found for account number: " + accountNumber);
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNumber = request.getParameter("accountNumber");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String address = request.getParameter("address");
        String telephone = request.getParameter("telephone");

        if (firstName.isEmpty() || lastName.isEmpty() || address.isEmpty() || telephone.isEmpty()) {
            request.setAttribute("error", "All fields are required");
            request.getRequestDispatcher("editCustomer.jsp").forward(request, response);
            return;
        }

        Customer customer = new Customer(accountNumber, firstName, lastName, address, telephone);
        if (customerDAO.updateCustomer(customer)) {
            request.setAttribute("message", "Customer updated successfully");
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Failed to update customer");
            request.getRequestDispatcher("editCustomer.jsp").forward(request, response);
        }
    }
}
