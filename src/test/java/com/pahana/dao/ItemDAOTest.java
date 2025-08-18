package com.pahana.dao;

import com.pahana.model.Item;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ItemDAOTest {
    private ItemDAO itemDAO;
    @Mock private Connection conn;
    @Mock private PreparedStatement stmt;
    @Mock private ResultSet rs;

    @BeforeClass
    public static void initDatabase() throws SQLException {
        try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:itemdao_test;DB_CLOSE_DELAY=-1", "sa", "")) {
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS items (item_id VARCHAR(10) PRIMARY KEY, name VARCHAR(100), unit_price DOUBLE, quantity INT)");
            stmt.execute("INSERT INTO items (item_id, name, unit_price, quantity) VALUES ('ITEM001', 'Book', 10.0, 50)");
        }
    }

    @Before
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        itemDAO = new ItemDAO() {
            @Override
            protected Connection getConnection() throws SQLException {
                return conn;
            }
        };
        when(conn.prepareStatement(anyString())).thenReturn(stmt);
    }

    @Test
    public void testAddItem_Success() throws SQLException {
        Item item = new Item("ITEM002", "Book", 10.0, 50);
        when(stmt.executeUpdate()).thenReturn(1);

        boolean result = itemDAO.addItem(item);

        assertTrue(result);
        verify(stmt).setString(1, "ITEM002");
        verify(stmt).setString(2, "Book");
        verify(stmt).setDouble(3, 10.0);
        verify(stmt).setInt(4, 50);
    }

    @Test
    public void testReduceItemQuantity_InsufficientStock() throws SQLException {
        when(conn.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true);
        when(rs.getInt("quantity")).thenReturn(5);

        boolean result = itemDAO.reduceItemQuantity("ITEM001", 10);

        assertFalse(result);
        verify(conn).rollback();
    }
}