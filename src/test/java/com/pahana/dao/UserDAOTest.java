package com.pahana.dao;

import com.pahana.model.User;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserDAOTest {
    private UserDAO userDAO;
    @Mock private Connection conn;
    @Mock private PreparedStatement stmt;
    @Mock private ResultSet rs;

    @BeforeClass
    public static void initDatabase() throws SQLException {
        try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:userdao_test;DB_CLOSE_DELAY=-1", "sa", "")) {
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS users (username VARCHAR(50) PRIMARY KEY, password VARCHAR(50))");
            stmt.execute("INSERT INTO users (username, password) VALUES ('admin', 'pass123')");
        }
    }

    @Before
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        userDAO = new UserDAO() {
            @Override
            protected Connection getConnection() throws SQLException {
                return conn; // Mocked connection
            }
        };
        when(conn.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);
    }

    @Test
    public void testValidateUser_Success() throws SQLException {
        when(rs.next()).thenReturn(true);

        boolean result = userDAO.validateUser("admin", "pass123");

        assertTrue(result);
        verify(stmt).setString(1, "admin");
        verify(stmt).setString(2, "pass123");
    }

    @Test
    public void testValidateUser_Failure() throws SQLException {
        when(rs.next()).thenReturn(false);

        boolean result = userDAO.validateUser("fake", "wrong");

        assertFalse(result);
        verify(stmt).setString(1, "fake");
        verify(stmt).setString(2, "wrong");
    }
}