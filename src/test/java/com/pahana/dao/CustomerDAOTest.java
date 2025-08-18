////package com.pahana.dao;
////
////import com.pahana.model.Customer;
////import org.junit.Before;
////import org.junit.BeforeClass;
////import org.junit.Test;
////import org.mockito.Mock;
////import org.mockito.MockitoAnnotations;
////
////import java.sql.*;
////
////import static org.junit.Assert.*;
////import static org.mockito.Mockito.*;
////
////public class CustomerDAOTest {
////    private CustomerDAO customerDAO;
////    @Mock private Connection conn;
////    @Mock private PreparedStatement stmt;
////    @Mock private ResultSet rs;
////
//////    @BeforeClass
//////    public static void initDatabase() throws SQLException {
//////        try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:customerdao_test;DB_CLOSE_DELAY=-1", "sa", "")) {
//////            Statement stmt = conn.createStatement();
//////            stmt.execute("CREATE TABLE IF NOT EXISTS customers (account_number VARCHAR(10) PRIMARY KEY, first_name VARCHAR(50), last_name VARCHAR(50), address VARCHAR(255), telephone VARCHAR(15))");
//////            stmt.execute("INSERT INTO customers (account_number, first_name, last_name, address, telephone) VALUES ('CUST001', 'John', 'Doe', '123 Main St', '1234567890')");
//////        }
//////    }
////
////    @BeforeClass
////    public static void initDatabase() throws SQLException {
////        try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:customerdao_test;DB_CLOSE_DELAY=-1", "sa", "")) {
////            Statement stmt = conn.createStatement();
////            // Drop and recreate table to ensure clean state
////            stmt.execute("DROP TABLE IF EXISTS customers");
////            stmt.execute("CREATE TABLE customers (account_number VARCHAR(10) PRIMARY KEY, first_name VARCHAR(50), last_name VARCHAR(50), address VARCHAR(255), telephone VARCHAR(15))");
////            // Optionally insert initial data if needed
////            // stmt.execute("INSERT INTO customers (account_number, first_name, last_name, address, telephone) VALUES ('CUST001', 'John', 'Doe', '123 Main St', '1234567890')");
////        }
////    }
////
////    @Before
////    public void setUp() throws SQLException {
////        MockitoAnnotations.openMocks(this);
////        customerDAO = new CustomerDAO() {
////            @Override
////            protected Connection getConnection() throws SQLException {
////                return conn;
////            }
////        };
////        when(conn.prepareStatement(anyString())).thenReturn(stmt);
////    }
////
////    @Test
////    public void testAddCustomer_Success() throws SQLException {
////        Customer customer = new Customer("CUST002", "John", "Doe", "123 Main St", "1234567890");
////        when(stmt.executeUpdate()).thenReturn(1);
////
////        boolean result = customerDAO.addCustomer(customer);
////
////        assertTrue(result);
////        verify(stmt).setString(1, "CUST002");
////        verify(stmt).setString(2, "John");
////        verify(stmt).setString(3, "Doe");
////        verify(stmt).setString(4, "123 Main St");
////        verify(stmt).setString(5, "1234567890");
////    }
////
////    @Test
////    public void testGenerateAccountNumber_Success() throws SQLException {
////        when(conn.prepareStatement(anyString())).thenReturn(stmt);
////        when(stmt.executeQuery()).thenReturn(rs);
////        when(rs.next()).thenReturn(true).thenReturn(false); // No duplicate
////        when(rs.getString(1)).thenReturn("CUST001");
////
////        String accountNumber = customerDAO.generateAccountNumber();
////
////        assertEquals("CUST002", accountNumber);
////        verify(conn).commit();
////    }
////}
//
//
//package com.pahana.dao;
//
//import com.pahana.model.Customer;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import java.sql.*;
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.*;
//
//public class CustomerDAOTest {
//    private CustomerDAO customerDAO;
//    @Mock private Connection conn;
//    @Mock private PreparedStatement stmt;
//    @Mock private ResultSet rs;
//
//    @BeforeClass
//    public static void initDatabase() throws SQLException {
//        try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:customerdao_test;DB_CLOSE_DELAY=-1", "sa", "")) {
//            Statement stmt = conn.createStatement();
//            stmt.execute("DROP TABLE IF EXISTS customers");
//            stmt.execute("CREATE TABLE customers (account_number VARCHAR(10) PRIMARY KEY, first_name VARCHAR(50), last_name VARCHAR(50), address VARCHAR(255), telephone VARCHAR(15))");
//        }
//    }
//
//    @Before
//    public void setUp() throws SQLException {
//        MockitoAnnotations.openMocks(this);
//        customerDAO = new CustomerDAO() {
//            @Override
//            protected Connection getConnection() throws SQLException {
//                return conn;
//            }
//        };
//        when(conn.prepareStatement(anyString())).thenReturn(stmt);
//    }
//
//    @Test
//    public void testAddCustomer_Success() throws SQLException {
//        Customer customer = new Customer("CUST002", "John", "Doe", "123 Main St", "1234567890");
//        when(stmt.executeUpdate()).thenReturn(1);
//        boolean result = customerDAO.addCustomer(customer);
//        assertTrue(result);
//        verify(stmt).setString(1, "CUST002");
//        verify(stmt).setString(2, "John");
//        verify(stmt).setString(3, "Doe");
//        verify(stmt).setString(4, "123 Main St");
//        verify(stmt).setString(5, "1234567890");
//    }
//
//    @Test
//    public void testGenerateAccountNumber_Success() throws SQLException {
//        when(conn.prepareStatement(anyString())).thenReturn(stmt);
//        when(stmt.executeQuery()).thenReturn(rs);
//        when(rs.next()).thenReturn(true).thenReturn(false); // No duplicate
//        when(rs.getString(1)).thenReturn("CUST001");
//        String accountNumber = customerDAO.generateAccountNumber();
//        assertEquals("CUST002", accountNumber);
//        verify(conn).commit();
//    }
//}

package com.pahana.dao;

import com.pahana.model.Customer;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.sql.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CustomerDAOTest {
    private CustomerDAO customerDAO;
    @Mock private Connection conn;
    @Mock private PreparedStatement stmt;
    @Mock private ResultSet rs;

    @BeforeClass
    public static void initDatabase() throws SQLException {
        try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:customerdao_test;DB_CLOSE_DELAY=-1", "sa", "")) {
            Statement stmt = conn.createStatement();
            stmt.execute("DROP TABLE IF EXISTS customers");
            stmt.execute("CREATE TABLE customers (account_number VARCHAR(10) PRIMARY KEY, first_name VARCHAR(50), last_name VARCHAR(50), address VARCHAR(255), telephone VARCHAR(15))");
        }
    }

    @Before
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        customerDAO = new CustomerDAO() {
            @Override
            protected Connection getConnection() throws SQLException {
                return conn;
            }
        };
        when(conn.prepareStatement(anyString())).thenReturn(stmt);
    }

    @Test
    public void testAddCustomer_Success() throws SQLException {
        Customer customer = new Customer("CUST005", "John", "Doe", "123 Main St", "1234567890");
        when(stmt.executeUpdate()).thenReturn(1);
        boolean result = customerDAO.addCustomer(customer);
        assertTrue(result);
        verify(stmt).setString(1, "CUST005");
        verify(stmt).setString(2, "John");
        verify(stmt).setString(3, "Doe");
        verify(stmt).setString(4, "123 Main St");
        verify(stmt).setString(5, "1234567890");
    }

    @Test
    public void testGenerateAccountNumber_Success() throws SQLException {
        when(conn.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true).thenReturn(false); // No duplicate
        when(rs.getString(1)).thenReturn("CUST004");
        String accountNumber = customerDAO.generateAccountNumber();
        assertEquals("CUST005", accountNumber);
        verify(conn).commit();
    }
}