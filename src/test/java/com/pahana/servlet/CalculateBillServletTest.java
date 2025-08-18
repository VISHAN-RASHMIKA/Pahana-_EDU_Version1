package com.pahana.servlet;

import com.pahana.dao.BillDAO;
import com.pahana.dao.CustomerDAO;
import com.pahana.dao.ItemDAO;
import com.pahana.model.Bill;
import com.pahana.model.BillItem;
import com.pahana.model.Customer;
import com.pahana.model.Item;
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
import java.util.Date;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CalculateBillServletTest {
    private CalculateBillServlet servlet;
    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private RequestDispatcher dispatcher;
    @Mock private BillDAO billDAO;
    @Mock private CustomerDAO customerDAO;
    @Mock private ItemDAO itemDAO;
    @Mock private ServletContext servletContext;
    @Mock private ServletConfig servletConfig;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        servlet = new CalculateBillServlet();
        when(servletConfig.getServletContext()).thenReturn(servletContext);
        servlet.init(servletConfig); // Initialize servlet with mocked ServletConfig
        // Use reflection or modify servlet to allow dependency injection
        // For simplicity, we'll assume setters exist or modify the servlet
        servlet.setBillDAO(billDAO);
        servlet.setCustomerDAO(customerDAO);
        servlet.setItemDAO(itemDAO);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    }

    @Test
    public void testDoGet_Success() throws Exception {
        when(customerDAO.getAllCustomers()).thenReturn(Arrays.asList(new Customer("CUST005", "John", "Doe", "123 Main St", "1234567890")));
        when(itemDAO.getAllItems()).thenReturn(Arrays.asList(new Item("ITEM001", "Book", 10.0, 50)));
        when(request.getRequestDispatcher("calculateBill.jsp")).thenReturn(dispatcher);

        servlet.doGet(request, response);

        verify(request).setAttribute(eq("customers"), anyList());
        verify(request).setAttribute(eq("items"), anyList());
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testDoPost_Success() throws Exception {
        when(request.getParameter("accountNumber")).thenReturn("CUST005");
        when(request.getParameterValues("itemIds")).thenReturn(new String[]{"ITEM001"});
        when(request.getParameterValues("quantities")).thenReturn(new String[]{"5"});
        when(customerDAO.getCustomer("CUST005")).thenReturn(new Customer("CUST005", "John", "Doe", "123 Main St", "1234567890"));
        when(itemDAO.getItem("ITEM001")).thenReturn(new Item("ITEM001", "Book", 10.0, 50));
        when(billDAO.addBillWithInventoryUpdate(any(Bill.class), anyList(), eq(itemDAO))).thenReturn(true);
        when(request.getRequestDispatcher("printBill.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(request).setAttribute(eq("bill"), any(Bill.class));
        verify(request).setAttribute(eq("customer"), any(Customer.class));
        verify(request).setAttribute(eq("billItems"), anyList());
        verify(request).setAttribute(eq("itemDAO"), eq(itemDAO));
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testDoPost_MissingAccountNumber() throws Exception {
        when(request.getParameter("accountNumber")).thenReturn("");
        when(customerDAO.getAllCustomers()).thenReturn(Arrays.asList(new Customer("CUST005", "John", "Doe", "123 Main St", "1234567890")));
        when(itemDAO.getAllItems()).thenReturn(Arrays.asList(new Item("ITEM001", "Book", 10.0, 50)));
        when(request.getRequestDispatcher("calculateBill.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(request).setAttribute("error", "Please select a customer");
        verify(request).setAttribute(eq("customers"), anyList());
        verify(request).setAttribute(eq("items"), anyList());
        verify(dispatcher).forward(request, response);
        verify(billDAO, never()).addBillWithInventoryUpdate(any(Bill.class), anyList(), any());
    }

    @Test
    public void testDoPost_InvalidItems() throws Exception {
        when(request.getParameter("accountNumber")).thenReturn("CUST005");
        when(request.getParameterValues("itemIds")).thenReturn(new String[]{"ITEM001"});
        when(request.getParameterValues("quantities")).thenReturn(new String[]{"0"});
        when(customerDAO.getCustomer("CUST005")).thenReturn(new Customer("CUST005", "John", "Doe", "123 Main St", "1234567890"));
        when(itemDAO.getItem("ITEM001")).thenReturn(new Item("ITEM001", "Book", 10.0, 50));
        when(customerDAO.getAllCustomers()).thenReturn(Arrays.asList(new Customer("CUST005", "John", "Doe", "123 Main St", "1234567890")));
        when(itemDAO.getAllItems()).thenReturn(Arrays.asList(new Item("ITEM001", "Book", 10.0, 50)));
        when(request.getRequestDispatcher("calculateBill.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(request).setAttribute("error", "Invalid quantity for item: ITEM001");
        verify(request).setAttribute(eq("customers"), anyList());
        verify(request).setAttribute(eq("items"), anyList());
        verify(dispatcher).forward(request, response);
        verify(billDAO, never()).addBillWithInventoryUpdate(any(Bill.class), anyList(), any());
    }

    @Test
    public void testDoPost_InsufficientStock() throws Exception {
        when(request.getParameter("accountNumber")).thenReturn("CUST005");
        when(request.getParameterValues("itemIds")).thenReturn(new String[]{"ITEM001"});
        when(request.getParameterValues("quantities")).thenReturn(new String[]{"100"});
        when(customerDAO.getCustomer("CUST005")).thenReturn(new Customer("CUST005", "John", "Doe", "123 Main St", "1234567890"));
        when(itemDAO.getItem("ITEM001")).thenReturn(new Item("ITEM001", "Book", 10.0, 50));
        when(customerDAO.getAllCustomers()).thenReturn(Arrays.asList(new Customer("CUST005", "John", "Doe", "123 Main St", "1234567890")));
        when(itemDAO.getAllItems()).thenReturn(Arrays.asList(new Item("ITEM001", "Book", 10.0, 50)));
        when(request.getRequestDispatcher("calculateBill.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(request).setAttribute("error", "Insufficient stock for item: Book (Available: 50)");
        verify(request).setAttribute(eq("customers"), anyList());
        verify(request).setAttribute(eq("items"), anyList());
        verify(dispatcher).forward(request, response);
        verify(billDAO, never()).addBillWithInventoryUpdate(any(Bill.class), anyList(), any());
    }
}