package com.pahana.dao;

import com.pahana.model.Customer;

import java.sql.*;
import java.util.Properties;
import java.io.InputStream;

public class CustomerDAO {
    private Connection getConnection() throws SQLException {
        Properties props = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("db.properties")) {
            props.load(input);
        } catch (Exception e) {
            throw new SQLException("Failed to load database properties", e);
        }
        return DriverManager.getConnection(
                props.getProperty("db.url"),
                props.getProperty("db.username"),
                props.getProperty("db.password")
        );
    }

    public boolean addCustomer(Customer customer) {
        String sql = "INSERT INTO customers (account_number, name, address, telephone, units_consumed) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, customer.getAccountNumber());
            stmt.setString(2, customer.getName());
            stmt.setString(3, customer.getAddress());
            stmt.setString(4, customer.getTelephone());
            stmt.setInt(5, customer.getUnitsConsumed());
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
            stmt.setString(1, accountNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Customer(
                        rs.getString("account_number"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("telephone"),
                        rs.getInt("units_consumed")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateCustomer(Customer customer) {
        String sql = "UPDATE customers SET name = ?, address = ?, telephone = ?, units_consumed = ? WHERE account_number = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getAddress());
            stmt.setString(3, customer.getTelephone());
            stmt.setInt(4, customer.getUnitsConsumed());
            stmt.setString(5, customer.getAccountNumber());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}