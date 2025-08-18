//

package com.pahana.servlet;

import com.pahana.dao.UserDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static org.mockito.Mockito.*;

public class LoginServletTest {
    private LoginServlet servlet;
    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private HttpSession session;
    @Mock private RequestDispatcher dispatcher;
    @Mock private UserDAO userDAO;
    @Mock private ServletContext servletContext;
    @Mock private ServletConfig servletConfig;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        servlet = new LoginServlet();
        when(servletConfig.getServletContext()).thenReturn(servletContext);
        servlet.init(servletConfig); // Initialize servlet with mocked ServletConfig
        servlet.setUserDAO(userDAO); // Inject mock UserDAO
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        when(request.getSession()).thenReturn(session);
    }

    @Test
    public void testDoPost_ValidCredentials() throws Exception {
        when(request.getParameter("username")).thenReturn("admin");
        when(request.getParameter("password")).thenReturn("pass123");
        when(userDAO.validateUser("admin", "pass123")).thenReturn(true);

        servlet.doPost(request, response);

        verify(session).setAttribute("username", "admin");
        verify(response).sendRedirect("dashboard.jsp");
    }

    @Test
    public void testDoPost_InvalidCredentials() throws Exception {
        when(request.getParameter("username")).thenReturn("fake");
        when(request.getParameter("password")).thenReturn("wrong");
        when(userDAO.validateUser("fake", "wrong")).thenReturn(false);

        servlet.doPost(request, response);

        verify(request).setAttribute("error", "Invalid username or password");
        verify(dispatcher).forward(request, response);
    }
}