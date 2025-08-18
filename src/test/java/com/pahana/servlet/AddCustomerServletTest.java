////package com.pahana.servlet;
////
////import com.pahana.dao.CustomerDAO;
////import com.pahana.model.Customer;
////import org.junit.Before;
////import org.junit.Test;
////import org.mockito.Mock;
////import org.mockito.MockitoAnnotations;
////
////import javax.servlet.RequestDispatcher;
////import javax.servlet.ServletContext;
////import javax.servlet.ServletException;
////import javax.servlet.http.HttpServletRequest;
////import javax.servlet.http.HttpServletResponse;
////
////import java.io.IOException;
////
////import static org.junit.Assert.*;
////import static org.mockito.Mockito.*;
////
////public class AddCustomerServletTest {
////    private AddCustomerServlet servlet;
////    @Mock private HttpServletRequest request;
////    @Mock private HttpServletResponse response;
////    @Mock private RequestDispatcher dispatcher;
////    @Mock private CustomerDAO customerDAO;
////    @Mock private ServletContext servletContext;
////
////    @Before
////    public void setUp() throws Exception {
////        MockitoAnnotations.openMocks(this);
////        servlet = new AddCustomerServlet() {
////            @Override
////            protected CustomerDAO getCustomerDAO() {
////                return customerDAO;
////            }
////
////            @Override
////            public ServletContext getServletContext() {
////                return servletContext;
////            }
////        };
////        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
////    }
////    //
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
////        if (customerDAO.addCustomer(customer)) { // Use the field, not a new instance
////            request.setAttribute("message", "Customer added successfully");
////            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
////        } else {
////            request.setAttribute("error", "Failed to add customer");
////            request.setAttribute("accountNumber", accountNumber);
////            request.getRequestDispatcher("addCustomer.jsp").forward(request, response);
////        }
////    }//
////
//////    @Test
//////    public void testDoPost_Success() throws Exception {
//////        when(request.getParameter("accountNumber")).thenReturn("CUST002");
//////        when(request.getParameter("firstName")).thenReturn("John");
//////        when(request.getParameter("lastName")).thenReturn("Doe");
//////        when(request.getParameter("address")).thenReturn("123 Main St");
//////        when(request.getParameter("telephone")).thenReturn("1234567890");
//////        when(customerDAO.addCustomer(any(Customer.class))).thenReturn(true);
//////
//////        servlet.doPost(request, response);
//////
//////        verify(request).setAttribute("message", "Customer added successfully");
//////        verify(dispatcher).forward(request, response);
//////    }
////
////    @Test
////    public void testDoPost_Success() throws Exception {
////        // Use a unique account number to avoid conflicts
////        when(request.getParameter("accountNumber")).thenReturn("CUST003");
////        when(request.getParameter("firstName")).thenReturn("John");
////        when(request.getParameter("lastName")).thenReturn("Doe");
////        when(request.getParameter("address")).thenReturn("123 Main St");
////        when(request.getParameter("telephone")).thenReturn("1234567890");
////        when(customerDAO.addCustomer(any(Customer.class))).thenReturn(true);
////        when(request.getRequestDispatcher("dashboard.jsp")).thenReturn(dispatcher);
////
////        servlet.doPost(request, response);
////
////        verify(request).setAttribute("message", "Customer added successfully");
////        verify(dispatcher).forward(request, response);
////    }
////
////    @Test
////    public void testDoPost_MissingFields() throws Exception {
////        when(request.getParameter("accountNumber")).thenReturn("CUST002");
////        when(request.getParameter("firstName")).thenReturn("");
////        when(request.getParameter("lastName")).thenReturn("Doe");
////        when(request.getParameter("address")).thenReturn("123 Main St");
////        when(request.getParameter("telephone")).thenReturn("1234567890");
////
////        servlet.doPost(request, response);
////
////        verify(request).setAttribute("error", "All fields are required");
////        verify(dispatcher).forward(request, response);
////        verify(customerDAO, never()).addCustomer(any(Customer.class));
////    }
////}
//
//
//package com.pahana.servlet;
//
//import com.pahana.dao.CustomerDAO;
//import com.pahana.model.Customer;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletContext;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.*;
//
//public class AddCustomerServletTest {
//    private AddCustomerServlet servlet;
//    @Mock private HttpServletRequest request;
//    @Mock private HttpServletResponse response;
//    @Mock private RequestDispatcher dispatcher;
//    @Mock private CustomerDAO customerDAO;
//    @Mock private ServletContext servletContext;
//
//    @Before
//    public void setUp() throws Exception {
//        MockitoAnnotations.openMocks(this);
//        servlet = new AddCustomerServlet();
//        servlet.setCustomerDAO(customerDAO); // Inject mock
//        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
//        when(servlet.getServletContext()).thenReturn(servletContext);
//    }
//
//    @Test
//    public void testDoPost_Success() throws Exception {
//        when(request.getParameter("accountNumber")).thenReturn("CUST004"); // Use unique account number
//        when(request.getParameter("firstName")).thenReturn("John");
//        when(request.getParameter("lastName")).thenReturn("Doe");
//        when(request.getParameter("address")).thenReturn("123 Main St");
//        when(request.getParameter("telephone")).thenReturn("1234567890");
//        when(customerDAO.addCustomer(any(Customer.class))).thenReturn(true);
//        when(request.getRequestDispatcher("dashboard.jsp")).thenReturn(dispatcher);
//
//        servlet.doPost(request, response);
//
//        verify(request).setAttribute("message", "Customer added successfully");
//        verify(dispatcher).forward(request, response);
//    }
//
//    @Test
//    public void testDoPost_MissingFields() throws Exception {
//        when(request.getParameter("accountNumber")).thenReturn("CUST004");
//        when(request.getParameter("firstName")).thenReturn("");
//        when(request.getParameter("lastName")).thenReturn("Doe");
//        when(request.getParameter("address")).thenReturn("123 Main St");
//        when(request.getParameter("telephone")).thenReturn("1234567890");
//
//        servlet.doPost(request, response);
//
//        verify(request).setAttribute("error", "All fields are required");
//        verify(dispatcher).forward(request, response);
//        verify(customerDAO, never()).addCustomer(any(Customer.class));
//    }
//}

package com.pahana.servlet;

import com.pahana.dao.CustomerDAO;
import com.pahana.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AddCustomerServletTest {
    private AddCustomerServlet servlet;
    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private RequestDispatcher dispatcher;
    @Mock private CustomerDAO customerDAO;
    @Mock private ServletContext servletContext;
    @Mock private ServletConfig servletConfig;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        servlet = new AddCustomerServlet();
        when(servletConfig.getServletContext()).thenReturn(servletContext);
        servlet.init(servletConfig); // Initialize servlet with mocked ServletConfig
        servlet.setCustomerDAO(customerDAO); // Inject mock CustomerDAO
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    }

    @Test
    public void testDoPost_Success() throws Exception {
        when(request.getParameter("accountNumber")).thenReturn("CUST005"); // Use unique account number
        when(request.getParameter("firstName")).thenReturn("John");
        when(request.getParameter("lastName")).thenReturn("Doe");
        when(request.getParameter("address")).thenReturn("123 Main St");
        when(request.getParameter("telephone")).thenReturn("1234567890");
        when(customerDAO.addCustomer(any(Customer.class))).thenReturn(true);
        when(request.getRequestDispatcher("dashboard.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(request).setAttribute("message", "Customer added successfully");
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testDoPost_MissingFields() throws Exception {
        when(request.getParameter("accountNumber")).thenReturn("CUST005");
        when(request.getParameter("firstName")).thenReturn("");
        when(request.getParameter("lastName")).thenReturn("Doe");
        when(request.getParameter("address")).thenReturn("123 Main St");
        when(request.getParameter("telephone")).thenReturn("1234567890");

        servlet.doPost(request, response);

        verify(request).setAttribute("error", "All fields are required");
        verify(dispatcher).forward(request, response);
        verify(customerDAO, never()).addCustomer(any(Customer.class));
    }
}