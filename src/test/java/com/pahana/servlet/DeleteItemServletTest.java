package com.pahana.servlet;

import com.pahana.dao.ItemDAO;
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

public class DeleteItemServletTest {
    private DeleteItemServlet servlet;
    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private RequestDispatcher dispatcher;
    @Mock private ItemDAO itemDAO;
    @Mock private ServletContext servletContext;
    @Mock private ServletConfig servletConfig;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        servlet = new DeleteItemServlet();
        when(servletConfig.getServletContext()).thenReturn(servletContext);
        servlet.init(servletConfig); // Initialize servlet with mocked ServletConfig
        servlet.setItemDAO(itemDAO); // Inject mock ItemDAO
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    }

    @Test
    public void testDoGet_Success() throws Exception {
        when(request.getParameter("itemId")).thenReturn("ITEM001");
        when(itemDAO.deleteItem("ITEM001")).thenReturn(true);

        servlet.doGet(request, response);

        verify(request).setAttribute("message", "Item deleted successfully");
        verify(response).sendRedirect("displayItems");
    }

    @Test
    public void testDoGet_Failure() throws Exception {
        when(request.getParameter("itemId")).thenReturn("ITEM001");
        when(itemDAO.deleteItem("ITEM001")).thenReturn(false);
        when(request.getRequestDispatcher("displayItems.jsp")).thenReturn(dispatcher);

        servlet.doGet(request, response);

        verify(request).setAttribute("error", "Failed to delete item: ITEM001");
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testDoGet_MissingItemId() throws Exception {
        when(request.getParameter("itemId")).thenReturn("");
        when(request.getRequestDispatcher("displayItems.jsp")).thenReturn(dispatcher);

        servlet.doGet(request, response);

        verify(request).setAttribute("error", "Item ID is missing");
        verify(dispatcher).forward(request, response);
        verify(itemDAO, never()).deleteItem(anyString());
    }
}