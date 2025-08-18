
package com.pahana.dao;

import com.pahana.model.Bill;
import com.pahana.model.BillItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.io.InputStream;

public class BillDAO {
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
    public boolean addBillWithInventoryUpdate(Bill bill, List<BillItem> billItems, ItemDAO itemDAO) {
        Connection conn = null;
        PreparedStatement billStmt = null;
        PreparedStatement itemStmt = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);

            // Insert bill
            String billSql = "INSERT INTO bills (account_number, total_amount, bill_date) VALUES (?, ?, ?)";
            billStmt = conn.prepareStatement(billSql, Statement.RETURN_GENERATED_KEYS);
            billStmt.setString(1, bill.getAccountNumber());
            billStmt.setDouble(2, bill.getTotalAmount());
            billStmt.setTimestamp(3, new Timestamp(bill.getBillDate().getTime()));
            int affectedRows = billStmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating bill failed, no rows affected.");
            }

            // Get generated bill_id
            int billId;
            try (ResultSet generatedKeys = billStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    billId = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating bill failed, no ID obtained.");
                }
            }

            // Insert bill items
            String itemSql = "INSERT INTO bill_items (bill_id, item_id, quantity) VALUES (?, ?, ?)";
            itemStmt = conn.prepareStatement(itemSql);
            for (BillItem billItem : billItems) {
                if (billItem.getItemId() == null || billItem.getItemId().trim().isEmpty()) {
                    throw new SQLException("Item ID cannot be null for bill item");
                }
                itemStmt.setInt(1, billId);
                itemStmt.setString(2, billItem.getItemId());
                itemStmt.setInt(3, billItem.getQuantity());
                itemStmt.addBatch();
            }
            itemStmt.executeBatch();

            // Commit bill and bill items before updating inventory
            conn.commit();

            // Update inventory in a separate transaction
            for (BillItem billItem : billItems) {
                if (!itemDAO.reduceItemQuantity(billItem.getItemId(), billItem.getQuantity())) {
                    throw new SQLException("Failed to update inventory for item: " + billItem.getItemId());
                }
            }

            return true;
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (itemStmt != null) itemStmt.close();
                if (billStmt != null) billStmt.close();
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Bill getBill(int billId) {
        String sql = "SELECT * FROM bills WHERE bill_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, billId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Bill(
                            rs.getInt("bill_id"),
                            rs.getString("account_number"),
                            rs.getDouble("total_amount"),
                            rs.getTimestamp("bill_date")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<BillItem> getBillItems(int billId) {
        List<BillItem> billItems = new ArrayList<>();
        String sql = "SELECT * FROM bill_items WHERE bill_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, billId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    billItems.add(new BillItem(
                            rs.getInt("bill_item_id"),
                            rs.getInt("bill_id"),
                            rs.getString("item_id"),
                            rs.getInt("quantity")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return billItems;
    }
}