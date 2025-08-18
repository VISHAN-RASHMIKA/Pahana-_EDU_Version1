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
import java.util.Arrays;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DisplayAccountsServletTest {
    private DisplayAccountsServlet servlet;
    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private RequestDispatcher dispatcher;
    @Mock private CustomerDAO customerDAO;
    @Mock private ServletContext servletContext;
    @Mock private ServletConfig servletConfig;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        servlet = new DisplayAccountsServlet();
        when(servletConfig.getServletContext()).thenReturn(servletContext);
        servlet.init(servletConfig); // Initialize servlet with mocked ServletConfig
        servlet.setCustomerDAO(customerDAO); // Inject mock CustomerDAO
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    }

    @Test
    public void testDoGet_Success() throws Exception {
        when(customerDAO.getAllCustomers()).thenReturn(Arrays.asList(new Customer("CUST005", "John", "Doe", "123 Main St", "1234567890")));
        when(request.getRequestDispatcher("displayAccounts.jsp")).thenReturn(dispatcher);

        servlet.doGet(request, response);

        verify(request).setAttribute(eq("customers"), anyList());
        verify(dispatcher).forward(request, response);
    }
}