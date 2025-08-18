package com.pahana.servlet;

import com.pahana.dao.ItemDAO;
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
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class EditItemServletTest {
    private EditItemServlet servlet;
    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private RequestDispatcher dispatcher;
    @Mock private ItemDAO itemDAO;
    @Mock private ServletContext servletContext;
    @Mock private ServletConfig servletConfig;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        servlet = new EditItemServlet();
        when(servletConfig.getServletContext()).thenReturn(servletContext);
        servlet.init(servletConfig); // Initialize servlet with mocked ServletConfig
        servlet.setItemDAO(itemDAO); // Inject mock ItemDAO
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    }

    @Test
    public void testDoGet_Success() throws Exception {
        when(request.getParameter("itemId")).thenReturn("ITEM001");
        when(itemDAO.getItem("ITEM001")).thenReturn(new Item("ITEM001", "Book", 10.0, 50));
        when(request.getRequestDispatcher("editItem.jsp")).thenReturn(dispatcher);

        servlet.doGet(request, response);

        verify(request).setAttribute(eq("item"), any(Item.class));
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testDoGet_MissingItemId() throws Exception {
        when(request.getParameter("itemId")).thenReturn("");
        when(request.getRequestDispatcher("displayItems.jsp")).thenReturn(dispatcher);

        servlet.doGet(request, response);

        verify(request).setAttribute("error", "Item ID is missing");
        verify(dispatcher).forward(request, response);
        verify(itemDAO, never()).getItem(anyString());
    }

    @Test
    public void testDoGet_ItemNotFound() throws Exception {
        when(request.getParameter("itemId")).thenReturn("ITEM001");
        when(itemDAO.getItem("ITEM001")).thenReturn(null);
        when(request.getRequestDispatcher("displayItems.jsp")).thenReturn(dispatcher);

        servlet.doGet(request, response);

        verify(request).setAttribute("error", "Item not found for ID: ITEM001");
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testDoPost_Success() throws Exception {
        when(request.getParameter("itemId")).thenReturn("ITEM001");
        when(request.getParameter("name")).thenReturn("Updated Book");
        when(request.getParameter("unitPrice")).thenReturn("12.0");
        when(request.getParameter("quantity")).thenReturn("40");
        when(itemDAO.updateItem(any(Item.class))).thenReturn(true);
        when(request.getRequestDispatcher("displayItems.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(request).setAttribute("message", "Item updated successfully");
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testDoPost_MissingFields() throws Exception {
        when(request.getParameter("itemId")).thenReturn("ITEM001");
        when(request.getParameter("name")).thenReturn("");
        when(request.getParameter("unitPrice")).thenReturn("12.0");
        when(request.getParameter("quantity")).thenReturn("40");
        when(request.getRequestDispatcher("editItem.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(request).setAttribute("error", "Name is required");
        verify(dispatcher).forward(request, response);
        verify(itemDAO, never()).updateItem(any(Item.class));
    }

    @Test
    public void testDoPost_InvalidQuantity() throws Exception {
        when(request.getParameter("itemId")).thenReturn("ITEM001");
        when(request.getParameter("name")).thenReturn("Book");
        when(request.getParameter("unitPrice")).thenReturn("12.0");
        when(request.getParameter("quantity")).thenReturn("-1");
        when(request.getRequestDispatcher("editItem.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(request).setAttribute("error", "Invalid unit price or quantity");
        verify(dispatcher).forward(request, response);
        verify(itemDAO, never()).updateItem(any(Item.class));
    }
}