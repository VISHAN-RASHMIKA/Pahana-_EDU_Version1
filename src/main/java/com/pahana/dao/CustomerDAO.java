//package com.pahana.dao;
//
//import com.pahana.model.Customer;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Properties;
//import java.io.InputStream;
//
//public class CustomerDAO {
//    private Connection getConnection() throws SQLException {
//        Properties props = new Properties();
//        try (InputStream input = getClass().getClassLoader().getResourceAsStream("db.properties")) {
//            if (input == null) {
//                throw new SQLException("Database properties file not found");
//            }
//            props.load(input);
//        } catch (Exception e) {
//            throw new SQLException("Failed to load database properties", e);
//        }
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            throw new SQLException("MySQL JDBC Driver not found", e);
//        }
//        return DriverManager.getConnection(
//                props.getProperty("db.url"),
//                props.getProperty("db.username"),
//                props.getProperty("db.password")
//        );
//    }
//
//    public String generateAccountNumber() throws SQLException {
//        String prefix = "CUST";
//        String sql = "SELECT MAX(account_number) FROM customers WHERE account_number LIKE 'CUST%'";
//        try (Connection conn = getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql);
//             ResultSet rs = stmt.executeQuery()) {
//            if (rs.next() && rs.getString(1) != null) {
//                String lastAccount = rs.getString(1);
//                int number = Integer.parseInt(lastAccount.replace(prefix, "")) + 1;
//                return prefix + String.format("%03d", number);
//            }
//            return prefix + "001";
//        }
//    }
//
//    public boolean addCustomer(Customer customer) {
//        String sql = "INSERT INTO customers (account_number, first_name, last_name, address, telephone) VALUES (?, ?, ?, ?, ?)";
//        try (Connection conn = getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setString(1, customer.getAccountNumber());
//            stmt.setString(2, customer.getFirstName());
//            stmt.setString(3, customer.getLastName());
//            stmt.setString(4, customer.getAddress());
//            stmt.setString(5, customer.getTelephone());
//            return stmt.executeUpdate() > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    public Customer getCustomer(String accountNumber) {
//        String sql = "SELECT * FROM customers WHERE account_number = ?";
//        try (Connection conn = getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setString(1, accountNumber.trim());
//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) {
//                    return new Customer(
//                            rs.getString("account_number"),
//                            rs.getString("first_name"),
//                            rs.getString("last_name"),
//                            rs.getString("address"),
//                            rs.getString("telephone")
//                    );
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public boolean updateCustomer(Customer customer) {
//        String sql = "UPDATE customers SET first_name = ?, last_name = ?, address = ?, telephone = ? WHERE account_number = ?";
//        try (Connection conn = getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setString(1, customer.getFirstName());
//            stmt.setString(2, customer.getLastName());
//            stmt.setString(3, customer.getAddress());
//            stmt.setString(4, customer.getTelephone());
//            stmt.setString(5, customer.getAccountNumber());
//            return stmt.executeUpdate() > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    public List<Customer> getAllCustomers() {
//        List<Customer> customers = new ArrayList<>();
//        String sql = "SELECT * FROM customers";
//        try (Connection conn = getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql);
//             ResultSet rs = stmt.executeQuery()) {
//            while (rs.next()) {
//                customers.add(new Customer(
//                        rs.getString("account_number"),
//                        rs.getString("first_name"),
//                        rs.getString("last_name"),
//                        rs.getString("address"),
//                        rs.getString("telephone")
//                ));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return customers;
//    }
//}

//
//
//package com.pahana.dao;
//
//import com.pahana.model.Customer;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Properties;
//import java.io.InputStream;
//
//public class CustomerDAO {
//    Connection getConnection() throws SQLException {
//        Properties props = new Properties();
//        try (InputStream input = getClass().getClassLoader().getResourceAsStream("db.properties")) {
//            if (input == null) {
//                throw new SQLException("Database properties file not found");
//            }
//            props.load(input);
//        } catch (Exception e) {
//            throw new SQLException("Failed to load database properties", e);
//        }
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            throw new SQLException("MySQL JDBC Driver not found", e);
//        }
//        return DriverManager.getConnection(
//                props.getProperty("db.url"),
//                props.getProperty("db.username"),
//                props.getProperty("db.password")
//        );
//    }
//
//    public String generateAccountNumber() throws SQLException {
//        String prefix = "CUST";
//        String sql = "SELECT MAX(account_number) FROM customers WHERE account_number LIKE 'CUST%'";
//        try (Connection conn = getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql);
//             ResultSet rs = stmt.executeQuery()) {
//            if (rs.next()) {
//                String lastAccount = rs.getString(1);
//                if (lastAccount != null && lastAccount.startsWith(prefix)) {
//                    int number = Integer.parseInt(lastAccount.replace(prefix, "")) + 1;
//                    return prefix + String.format("%03d", number);
//                }
//            }
//            return prefix + "001";
//        }
//    }
//
//    public boolean addCustomer(Customer customer) {
//        String sql = "INSERT INTO customers (account_number, first_name, last_name, address, telephone) VALUES (?, ?, ?, ?, ?)";
//        try (Connection conn = getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setString(1, customer.getAccountNumber());
//            stmt.setString(2, customer.getFirstName());
//            stmt.setString(3, customer.getLastName());
//            stmt.setString(4, customer.getAddress());
//            stmt.setString(5, customer.getTelephone());
//            return stmt.executeUpdate() > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    public Customer getCustomer(String accountNumber) {
//        String sql = "SELECT * FROM customers WHERE account_number = ?";
//        try (Connection conn = getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setString(1, accountNumber.trim());
//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) {
//                    return new Customer(
//                            rs.getString("account_number"),
//                            rs.getString("first_name"),
//                            rs.getString("last_name"),
//                            rs.getString("address"),
//                            rs.getString("telephone")
//                    );
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public boolean updateCustomer(Customer customer) {
//        String sql = "UPDATE customers SET first_name = ?, last_name = ?, address = ?, telephone = ? WHERE account_number = ?";
//        try (Connection conn = getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setString(1, customer.getFirstName());
//            stmt.setString(2, customer.getLastName());
//            stmt.setString(3, customer.getAddress());
//            stmt.setString(4, customer.getTelephone());
//            stmt.setString(5, customer.getAccountNumber());
//            return stmt.executeUpdate() > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    public List<Customer> getAllCustomers() {
//        List<Customer> customers = new ArrayList<>();
//        String sql = "SELECT * FROM customers";
//        try (Connection conn = getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql);
//             ResultSet rs = stmt.executeQuery()) {
//            while (rs.next()) {
//                customers.add(new Customer(
//                        rs.getString("account_number"),
//                        rs.getString("first_name"),
//                        rs.getString("last_name"),
//                        rs.getString("address"),
//                        rs.getString("telephone")
//                ));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return customers;
//    }
//}


package com.pahana.dao;

import com.pahana.model.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.io.InputStream;

public class CustomerDAO {
//    private Connection getConnection() throws SQLException {
//        Properties props = new Properties();
//        try (InputStream input = getClass().getClassLoader().getResourceAsStream("db.properties")) {
//            if (input == null) {
//                throw new SQLException("Database properties file not found");
//            }
//            props.load(input);
//        } catch (Exception e) {
//            throw new SQLException("Failed to load database properties", e);
//        }
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            throw new SQLException("MySQL JDBC Driver not found", e);
//        }
//        return DriverManager.getConnection(
//                props.getProperty("db.url"),
//                props.getProperty("db.username"),
//                props.getProperty("db.password")
//        );
//    }

    // In BillDAO.java, CustomerDAO.java, ItemDAO.java, UserDAO.java
    protected Connection getConnection() throws SQLException {
        Properties props = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                throw new SQLException("Database properties file not found");
            }
            props.load(input);
        } catch (Exception e) {
            throw new SQLException("Failed to load database properties", e);
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found", e);
        }
        return DriverManager.getConnection(
                props.getProperty("db.url"),
                props.getProperty("db.username"),
                props.getProperty("db.password")
        );
    }

    public synchronized String generateAccountNumber() throws SQLException {
        String prefix = "CUST";
        String sql = "SELECT MAX(account_number) FROM customers WHERE account_number LIKE ? FOR UPDATE";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            stmt.setString(1, prefix + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                String newAccountNumber = prefix + "001";
                if (rs.next()) {
                    String lastAccount = rs.getString(1);
                    if (lastAccount != null && lastAccount.startsWith(prefix)) {
                        int number = Integer.parseInt(lastAccount.replace(prefix, "")) + 1;
                        newAccountNumber = prefix + String.format("%03d", number);
                    }
                }
                // Validate uniqueness
                String checkSql = "SELECT COUNT(*) FROM customers WHERE account_number = ?";
                try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                    checkStmt.setString(1, newAccountNumber);
                    ResultSet checkRs = checkStmt.executeQuery();
                    if (checkRs.next() && checkRs.getInt(1) > 0) {
                        throw new SQLException("Generated account number already exists");
                    }
                }
                conn.commit();
                return newAccountNumber;
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        }
    }

    public boolean addCustomer(Customer customer) {
        String sql = "INSERT INTO customers (account_number, first_name, last_name, address, telephone) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, customer.getAccountNumber());
            stmt.setString(2, customer.getFirstName());
            stmt.setString(3, customer.getLastName());
            stmt.setString(4, customer.getAddress());
            stmt.setString(5, customer.getTelephone());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Customer getCustomer(String accountNumber) {
        String sql = "SELECT * FROM customers WHERE account_number = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, accountNumber.trim());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Customer(
                            rs.getString("account_number"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("address"),
                            rs.getString("telephone")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateCustomer(Customer customer) {
        String sql = "UPDATE customers SET first_name = ?, last_name = ?, address = ?, telephone = ? WHERE account_number = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, customer.getFirstName());
            stmt.setString(2, customer.getLastName());
            stmt.setString(3, customer.getAddress());
            stmt.setString(4, customer.getTelephone());
            stmt.setString(5, customer.getAccountNumber());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                customers.add(new Customer(
                        rs.getString("account_number"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("address"),
                        rs.getString("telephone")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
}