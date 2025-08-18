//////package com.pahana.servlet;
//////
//////import com.pahana.dao.CustomerDAO;
//////import com.pahana.model.Customer;
//////
//////import javax.servlet.ServletException;
//////import javax.servlet.annotation.WebServlet;
//////import javax.servlet.http.HttpServlet;
//////import javax.servlet.http.HttpServletRequest;
//////import javax.servlet.http.HttpServletResponse;
//////import java.io.IOException;
//////import java.sql.SQLException;
//////
//////@WebServlet("/addCustomer")
//////public class AddCustomerServlet extends HttpServlet {
//////    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//////        try {
//////            CustomerDAO customerDAO = new CustomerDAO();
//////            String accountNumber = customerDAO.generateAccountNumber();
//////            request.setAttribute("accountNumber", accountNumber);
//////            request.getRequestDispatcher("addCustomer.jsp").forward(request, response);
//////        } catch (SQLException e) {
//////            e.printStackTrace();
//////            request.setAttribute("error", "Failed to generate account number");
//////            request.getRequestDispatcher("addCustomer.jsp").forward(request, response);
//////        }
//////    }
//////
//////    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//////        String accountNumber = request.getParameter("accountNumber");
//////        String firstName = request.getParameter("firstName");
//////        String lastName = request.getParameter("lastName");
//////        String address = request.getParameter("address");
//////        String telephone = request.getParameter("telephone");
//////
//////        if (firstName.isEmpty() || lastName.isEmpty() || address.isEmpty() || telephone.isEmpty()) {
//////            request.setAttribute("error", "All fields are required");
//////            request.setAttribute("accountNumber", accountNumber);
//////            request.getRequestDispatcher("addCustomer.jsp").forward(request, response);
//////            return;
//////        }
//////
//////        Customer customer = new Customer(accountNumber, firstName, lastName, address, telephone);
//////        CustomerDAO customerDAO = new CustomerDAO();
//////        if (customerDAO.addCustomer(customer)) {
//////            request.setAttribute("message", "Customer added successfully");
//////            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
//////        } else {
//////            request.setAttribute("error", "Failed to add customer");
//////            request.setAttribute("accountNumber", accountNumber);
//////            request.getRequestDispatcher("addCustomer.jsp").forward(request, response);
//////        }
//////    }
//////}
////
////
////package com.pahana.servlet;
////
////import com.pahana.dao.CustomerDAO;
////import com.pahana.model.Customer;
////
////import javax.servlet.ServletException;
////import javax.servlet.annotation.WebServlet;
////import javax.servlet.http.HttpServlet;
////import javax.servlet.http.HttpServletRequest;
////import javax.servlet.http.HttpServletResponse;
////import java.io.IOException;
////import java.sql.SQLException;
////
////@WebServlet("/addCustomer")
////public class AddCustomerServlet extends HttpServlet {
////    protected CustomerDAO getCustomerDAO() { // Add this for testing
////        return new CustomerDAO();
////    }
////    private CustomerDAO customerDAO; // Add this field
////
////    public AddCustomerServlet() {
////        this.customerDAO = new CustomerDAO(); // Initialize in constructor
////    }
////
////    // For testing, allow setting a mock DAO
////    void setCustomerDAO(CustomerDAO customerDAO) {
////        this.customerDAO = customerDAO;
////    }
////    @Override
////    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////        try {
////            String accountNumber = customerDAO.generateAccountNumber();
////            if (accountNumber == null) {
////                throw new SQLException("Failed to generate account number");
////            }
////            request.setAttribute("accountNumber", accountNumber);
////            request.getRequestDispatcher("addCustomer.jsp").forward(request, response);
////        } catch (SQLException e) {
////            e.printStackTrace();
////            request.setAttribute("error", "Failed to generate account number: " + e.getMessage());
////            request.getRequestDispatcher("addCustomer.jsp").forward(request, response);
////        }
////    }
////
//////    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//////        try {
//////            CustomerDAO customerDAO = new CustomerDAO();
//////            String accountNumber = customerDAO.generateAccountNumber();
//////            if (accountNumber == null) {
//////                throw new SQLException("Failed to generate account number");
//////            }
//////            request.setAttribute("accountNumber", accountNumber);
//////            request.getRequestDispatcher("addCustomer.jsp").forward(request, response);
//////        } catch (SQLException e) {
//////            e.printStackTrace();
//////            request.setAttribute("error", "Failed to generate account number: " + e.getMessage());
//////            request.getRequestDispatcher("addCustomer.jsp").forward(request, response);
//////        }
//////    }
////
////    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////        String accountNumber = request.getParameter("accountNumber");
////        String firstName = request.getParameter("firstName");
////        String lastName = request.getParameter("lastName");
////        String address = request.getParameter("address");
////        String telephone = request.getParameter("telephone");
////
////        if (accountNumber == null || firstName.isEmpty() || lastName.isEmpty() || address.isEmpty() || telephone.isEmpty()) {
////            request.setAttribute("error", "All fields are required");
////            request.setAttribute("accountNumber", accountNumber);
////            request.getRequestDispatcher("addCustomer.jsp").forward(request, response);
////            return;
////        }
////
////        Customer customer = new Customer(accountNumber, firstName, lastName, address, telephone);
////        CustomerDAO customerDAO = new CustomerDAO();
////        if (customerDAO.addCustomer(customer)) {
////            request.setAttribute("message", "Customer added successfully");
////            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
////        } else {
////            request.setAttribute("error", "Failed to add customer");
////            request.setAttribute("accountNumber", accountNumber);
////            request.getRequestDispatcher("addCustomer.jsp").forward(request, response);
////        }
////    }
////}
//
//package com.pahana.servlet;
//
//import com.pahana.dao.CustomerDAO;
//import com.pahana.model.Customer;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.sql.SQLException;
//
//@WebServlet("/addCustomer")
//public class AddCustomerServlet extends HttpServlet {
//    private CustomerDAO customerDAO;
//
//    public AddCustomerServlet() {
//        this.customerDAO = new CustomerDAO();
//    }
//
//    // For testing, allow setting a mock DAO
//    void setCustomerDAO(CustomerDAO customerDAO) {
//        this.customerDAO = customerDAO;
//    }
//
//    // For testing
//    protected CustomerDAO getCustomerDAO() {
//        return customerDAO;
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        try {
//            String accountNumber = customerDAO.generateAccountNumber();
//            if (accountNumber == null) {
//                throw new SQLException("Failed to generate account number");
//            }
//            request.setAttribute("accountNumber", accountNumber);
//            request.getRequestDispatcher("addCustomer.jsp").forward(request, response);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            request.setAttribute("error", "Failed to generate account number: " + e.getMessage());
//            request.getRequestDispatcher("addCustomer.jsp").forward(request, response);
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String accountNumber = request.getParameter("accountNumber");
//        String firstName = request.getParameter("firstName");
//        String lastName = request.getParameter("lastName");
//        String address = request.getParameter("address");
//        String telephone = request.getParameter("telephone");
//
//        if (accountNumber == null || firstName.isEmpty() || lastName.isEmpty() || address.isEmpty() || telephone.isEmpty()) {
//            request.setAttribute("error", "All fields are required");
//            request.setAttribute("accountNumber", accountNumber);
//            request.getRequestDispatcher("addCustomer.jsp").forward(request, response);
//            return;
//        }
//
//        Customer customer = new Customer(accountNumber, firstName, lastName, address, telephone);
//        if (customerDAO.addCustomer(customer)) { // Use the field, not a new instance
//            request.setAttribute("message", "Customer added successfully");
//            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
//        } else {
//            request.setAttribute("error", "Failed to add customer");
//            request.setAttribute("accountNumber", accountNumber);
//            request.getRequestDispatcher("addCustomer.jsp").forward(request, response);
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
import java.sql.SQLException;

@WebServlet("/addCustomer")
public class AddCustomerServlet extends HttpServlet {
    private CustomerDAO customerDAO;

    public AddCustomerServlet() {
        this.customerDAO = new CustomerDAO();
    }

    // For testing, allow setting a mock DAO
    protected void setCustomerDAO(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    // For testing
    protected CustomerDAO getCustomerDAO() {
        return customerDAO;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String accountNumber = customerDAO.generateAccountNumber();
            if (accountNumber == null) {
                throw new SQLException("Failed to generate account number");
            }
            request.setAttribute("accountNumber", accountNumber);
            request.getRequestDispatcher("addCustomer.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Failed to generate account number: " + e.getMessage());
            request.getRequestDispatcher("addCustomer.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNumber = request.getParameter("accountNumber");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String address = request.getParameter("address");
        String telephone = request.getParameter("telephone");

        if (accountNumber == null || firstName.isEmpty() || lastName.isEmpty() || address.isEmpty() || telephone.isEmpty()) {
            request.setAttribute("error", "All fields are required");
            request.setAttribute("accountNumber", accountNumber);
            request.getRequestDispatcher("addCustomer.jsp").forward(request, response);
            return;
        }

        Customer customer = new Customer(accountNumber, firstName, lastName, address, telephone);
        if (customerDAO.addCustomer(customer)) {
            request.setAttribute("message", "Customer added successfully");
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Failed to add customer");
            request.setAttribute("accountNumber", accountNumber);
            request.getRequestDispatcher("addCustomer.jsp").forward(request, response);
        }
    }
}