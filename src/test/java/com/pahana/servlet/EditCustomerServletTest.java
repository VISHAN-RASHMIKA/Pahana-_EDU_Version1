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

public class EditCustomerServletTest {
    private EditCustomerServlet servlet;
    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private RequestDispatcher dispatcher;
    @Mock private CustomerDAO customerDAO;
    @Mock private ServletContext servletContext;
    @Mock private ServletConfig servletConfig;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        servlet = new EditCustomerServlet();
        when(servletConfig.getServletContext()).thenReturn(servletContext);
        servlet.init(servletConfig); // Initialize servlet with mocked ServletConfig
        servlet.setCustomerDAO(customerDAO); // Inject mock CustomerDAO
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    }

    @Test
    public void testDoGet_Success() throws Exception {
        when(request.getParameter("accountNumber")).thenReturn("CUST005");
        when(customerDAO.getCustomer("CUST005")).thenReturn(new Customer("CUST005", "John", "Doe", "123 Main St", "1234567890"));
        when(request.getRequestDispatcher("editCustomer.jsp")).thenReturn(dispatcher);

        servlet.doGet(request, response);

        verify(request).setAttribute(eq("customer"), any(Customer.class));
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testDoGet_MissingAccountNumber() throws Exception {
        when(request.getParameter("accountNumber")).thenReturn("");
        when(request.getRequestDispatcher("dashboard.jsp")).thenReturn(dispatcher);

        servlet.doGet(request, response);

        verify(request).setAttribute("error", "Account number is missing");
        verify(dispatcher).forward(request, response);
        verify(customerDAO, never()).getCustomer(anyString());
    }

    @Test
    public void testDoGet_CustomerNotFound() throws Exception {
        when(request.getParameter("accountNumber")).thenReturn("CUST005");
        when(customerDAO.getCustomer("CUST005")).thenReturn(null);
        when(request.getRequestDispatcher("dashboard.jsp")).thenReturn(dispatcher);

        servlet.doGet(request, response);

        verify(request).setAttribute("error", "Customer not found for account number: CUST005");
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testDoPost_Success() throws Exception {
        when(request.getParameter("accountNumber")).thenReturn("CUST005");
        when(request.getParameter("firstName")).thenReturn("Jane");
        when(request.getParameter("lastName")).thenReturn("Doe");
        when(request.getParameter("address")).thenReturn("456 Elm St");
        when(request.getParameter("telephone")).thenReturn("0987654321");
        when(customerDAO.updateCustomer(any(Customer.class))).thenReturn(true);
        when(request.getRequestDispatcher("dashboard.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(request).setAttribute("message", "Customer updated successfully");
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testDoPost_MissingFields() throws Exception {
        when(request.getParameter("accountNumber")).thenReturn("CUST005");
        when(request.getParameter("firstName")).thenReturn("");
        when(request.getParameter("lastName")).thenReturn("Doe");
        when(request.getParameter("address")).thenReturn("456 Elm St");
        when(request.getParameter("telephone")).thenReturn("0987654321");
        when(request.getRequestDispatcher("editCustomer.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(request).setAttribute("error", "All fields are required");
        verify(dispatcher).forward(request, response);
        verify(customerDAO, never()).updateCustomer(any(Customer.class));
    }
}