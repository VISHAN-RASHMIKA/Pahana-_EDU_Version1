package com.pahana.servlet;

import com.pahana.dao.CustomerDAO;
import com.pahana.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addCustomer")
public class AddCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNumber = request.getParameter("accountNumber");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String telephone = request.getParameter("telephone");
        int unitsConsumed = Integer.parseInt(request.getParameter("unitsConsumed"));

        if (accountNumber.isEmpty() || name.isEmpty() || address.isEmpty() || telephone.isEmpty()) {
            request.setAttribute("error", "All fields are required");
            request.getRequestDispatcher("addCustomer.jsp").forward(request, response);
            return;
        }

        Customer customer = new Customer(accountNumber, name, address, telephone, unitsConsumed);
        CustomerDAO customerDAO = new CustomerDAO();

        if (customerDAO.addCustomer(customer)) {
            request.setAttribute("message", "Customer added successfully");
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Failed to add customer");
            request.getRequestDispatcher("addCustomer.jsp").forward(request, response);
        }
    }
}