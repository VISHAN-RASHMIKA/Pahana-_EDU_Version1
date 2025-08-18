package com.pahana.dao;

import com.pahana.model.Bill;
import com.pahana.model.BillItem;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BillDAOTest {
    private BillDAO billDAO;
    @Mock private Connection conn;
    @Mock private PreparedStatement billStmt;
    @Mock private PreparedStatement itemStmt;
    @Mock private ResultSet generatedKeys;
    @Mock private ResultSet rs;
    @Mock private ItemDAO itemDAO;

//    @BeforeClass
//    public static void initDatabase() throws SQLException {
//        try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:billdao_test;DB_CLOSE_DELAY=-1", "sa", "")) {
//            Statement stmt = conn.createStatement();
//            stmt.execute("CREATE TABLE IF NOT EXISTS users (username VARCHAR(50) PRIMARY KEY, password VARCHAR(50))");
//            stmt.execute("CREATE TABLE IF NOT EXISTS customers (account_number VARCHAR(10) PRIMARY KEY, first_name VARCHAR(50), last_name VARCHAR(50), address VARCHAR(255), telephone VARCHAR(15))");
//            stmt.execute("CREATE TABLE IF NOT EXISTS items (item_id VARCHAR(10) PRIMARY KEY, name VARCHAR(100), unit_price DOUBLE, quantity INT)");
//            stmt.execute("CREATE TABLE IF NOT EXISTS bills (bill_id INT AUTO_INCREMENT PRIMARY KEY, account_number VARCHAR(10), total_amount DOUBLE, bill_date TIMESTAMP)");
//            stmt.execute("CREATE TABLE IF NOT EXISTS bill_items (bill_item_id INT AUTO_INCREMENT PRIMARY KEY, bill_id INT, item_id VARCHAR(10), quantity INT)");
//            // Insert test data
//            stmt.execute("INSERT INTO users (username, password) VALUES ('admin', 'pass123')");
//            stmt.execute("INSERT INTO customers (account_number, first_name, last_name, address, telephone) VALUES ('CUST001', 'John', 'Doe', '123 Main St', '1234567890')");
//            stmt.execute("INSERT INTO items (item_id, name, unit_price, quantity) VALUES ('ITEM001', 'Book', 10.0, 50)");
//        }
//    }


    @BeforeClass
    public static void initDatabase() throws SQLException {
        try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:billdao_test;DB_CLOSE_DELAY=-1", "sa", "")) {
            Statement stmt = conn.createStatement();
            // Drop and recreate tables to ensure clean state
            stmt.execute("DROP TABLE IF EXISTS bill_items");
            stmt.execute("DROP TABLE IF EXISTS bills");
            stmt.execute("DROP TABLE IF EXISTS items");
            stmt.execute("DROP TABLE IF EXISTS customers");
            stmt.execute("DROP TABLE IF EXISTS users");
            stmt.execute("CREATE TABLE users (username VARCHAR(50) PRIMARY KEY, password VARCHAR(50))");
            stmt.execute("CREATE TABLE customers (account_number VARCHAR(10) PRIMARY KEY, first_name VARCHAR(50), last_name VARCHAR(50), address VARCHAR(255), telephone VARCHAR(15))");
            stmt.execute("CREATE TABLE items (item_id VARCHAR(10) PRIMARY KEY, name VARCHAR(100), unit_price DOUBLE, quantity INT)");
            stmt.execute("CREATE TABLE bills (bill_id INT AUTO_INCREMENT PRIMARY KEY, account_number VARCHAR(10), total_amount DOUBLE, bill_date TIMESTAMP)");
            stmt.execute("CREATE TABLE bill_items (bill_item_id INT AUTO_INCREMENT PRIMARY KEY, bill_id INT, item_id VARCHAR(10), quantity INT)");
            // Insert test data
            stmt.execute("INSERT INTO users (username, password) VALUES ('admin', 'pass123')");
            stmt.execute("INSERT INTO customers (account_number, first_name, last_name, address, telephone) VALUES ('CUST001', 'John', 'Doe', '123 Main St', '1234567890')");
            stmt.execute("INSERT INTO items (item_id, name, unit_price, quantity) VALUES ('ITEM001', 'Book', 10.0, 50)");
        }
    }

    @Before
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        billDAO = new BillDAO() {
            @Override
            protected Connection getConnection() throws SQLException {
                return conn; // Mocked connection
            }
        };
        when(conn.prepareStatement(anyString(), anyInt())).thenReturn(billStmt);
        when(conn.prepareStatement(anyString())).thenReturn(itemStmt);
        when(billStmt.getGeneratedKeys()).thenReturn(generatedKeys);
    }

    @Test
    public void testAddBillWithInventoryUpdate_Success() throws SQLException {
        Bill bill = new Bill(0, "CUST001", 100.0, new Date());
        BillItem billItem = new BillItem(0, 0, "ITEM001", 5);
        List<BillItem> billItems = Arrays.asList(billItem);

        when(billStmt.executeUpdate()).thenReturn(1);
        when(generatedKeys.next()).thenReturn(true);
        when(generatedKeys.getInt(1)).thenReturn(1);
        when(itemStmt.executeBatch()).thenReturn(new int[]{1});
        when(itemDAO.reduceItemQuantity("ITEM001", 5)).thenReturn(true);

        boolean result = billDAO.addBillWithInventoryUpdate(bill, billItems, itemDAO);

        assertTrue(result);
        verify(conn).commit();
        verify(itemDAO).reduceItemQuantity("ITEM001", 5);
    }

    @Test
    public void testAddBillWithInventoryUpdate_NullItemId() throws SQLException {
        Bill bill = new Bill(0, "CUST001", 100.0, new Date());
        BillItem billItem = new BillItem(0, 0, null, 5);
        List<BillItem> billItems = Arrays.asList(billItem);

        boolean result = billDAO.addBillWithInventoryUpdate(bill, billItems, itemDAO);

        assertFalse(result);
        verify(conn, never()).commit();
        verify(conn).rollback();
        verify(itemDAO, never()).reduceItemQuantity(anyString(), anyInt());
    }

    @Test
    public void testGetBill_Success() throws SQLException {
        when(conn.prepareStatement(anyString())).thenReturn(billStmt);
        when(billStmt.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true);
        when(rs.getInt("bill_id")).thenReturn(1);
        when(rs.getString("account_number")).thenReturn("CUST001");
        when(rs.getDouble("total_amount")).thenReturn(100.0);
        when(rs.getTimestamp("bill_date")).thenReturn(new Timestamp(System.currentTimeMillis()));

        Bill bill = billDAO.getBill(1);

        assertNotNull(bill);
        assertEquals("CUST001", bill.getAccountNumber());
        assertEquals(100.0, bill.getTotalAmount(), 0.01);
    }

    @Test
    public void testGetBillItems_Success() throws SQLException {
        when(conn.prepareStatement(anyString())).thenReturn(billStmt);
        when(billStmt.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getInt("bill_item_id")).thenReturn(1);
        when(rs.getInt("bill_id")).thenReturn(1);
        when(rs.getString("item_id")).thenReturn("ITEM001");
        when(rs.getInt("quantity")).thenReturn(5);

        List<BillItem> billItems = billDAO.getBillItems(1);

        assertEquals(1, billItems.size());
        assertEquals("ITEM001", billItems.get(0).getItemId());
        assertEquals(5, billItems.get(0).getQuantity());
    }
}