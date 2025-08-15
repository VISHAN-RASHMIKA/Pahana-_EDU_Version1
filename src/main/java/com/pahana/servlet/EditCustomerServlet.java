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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNumber = request.getParameter("accountNumber");
        CustomerDAO customerDAO = new CustomerDAO();
        Customer customer = customerDAO.getCustomer(accountNumber);
        if (customer != null) {
            request.setAttribute("customer", customer);
            request.getRequestDispatcher("editCustomer.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Customer not found");
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNumber = request.getParameter("accountNumber");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String telephone = request.getParameter("telephone");
        int unitsConsumed = Integer.parseInt(request.getParameter("unitsConsumed"));

        if (name.isEmpty() || address.isEmpty() || telephone.isEmpty()) {
            request.setAttribute("error", "All fields are required");
            request.getRequestDispatcher("editCustomer.jsp").forward(request, response);
            return;
        }

        Customer customer = new Customer(accountNumber, name, address, telephone, unitsConsumed);
        CustomerDAO customerDAO = new CustomerDAO();

        if (customerDAO.updateCustomer(customer)) {
            request.setAttribute("message", "Customer updated successfully");
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Failed to update customer");
            request.getRequestDispatcher("editCustomer.jsp").forward(request, response);
        }
    }
}