//package com.pahana.dao;
//
//import com.pahana.model.Item;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Properties;
//import java.io.InputStream;
//
//public class ItemDAO {
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
//    public String generateItemId() throws SQLException {
//        String prefix = "ITEM";
//        String sql = "SELECT MAX(item_id) FROM items WHERE item_id LIKE 'ITEM%'";
//        try (Connection conn = getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql);
//             ResultSet rs = stmt.executeQuery()) {
//            if (rs.next() && rs.getString(1) != null) {
//                String lastItemId = rs.getString(1);
//                int number = Integer.parseInt(lastItemId.replace(prefix, "")) + 1;
//                return prefix + String.format("%03d", number);
//            }
//            return prefix + "001";
//        }
//    }
//
//    public boolean addItem(Item item) {
//        String sql = "INSERT INTO items (item_id, name, unit_price, quantity) VALUES (?, ?, ?, ?)";
//        try (Connection conn = getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setString(1, item.getItemId());
//            stmt.setString(2, item.getName());
//            stmt.setDouble(3, item.getUnitPrice());
//            stmt.setInt(4, item.getQuantity());
//            return stmt.executeUpdate() > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    public Item getItem(String itemId) {
//        String sql = "SELECT * FROM items WHERE item_id = ?";
//        try (Connection conn = getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setString(1, itemId.trim());
//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) {
//                    return new Item(
//                            rs.getString("item_id"),
//                            rs.getString("name"),
//                            rs.getDouble("unit_price"),
//                            rs.getInt("quantity")
//                    );
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public boolean updateItem(Item item) {
//        String sql = "UPDATE items SET name = ?, unit_price = ?, quantity = ? WHERE item_id = ?";
//        try (Connection conn = getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setString(1, item.getName());
//            stmt.setDouble(2, item.getUnitPrice());
//            stmt.setInt(3, item.getQuantity());
//            stmt.setString(4, item.getItemId());
//            return stmt.executeUpdate() > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    public boolean reduceItemQuantity(String itemId, int quantity) {
//        String sql = "UPDATE items SET quantity = quantity - ? WHERE item_id = ? AND quantity >= ?";
//        try (Connection conn = getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setInt(1, quantity);
//            stmt.setString(2, itemId);
//            stmt.setInt(3, quantity);
//            return stmt.executeUpdate() > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    public boolean deleteItem(String itemId) {
//        String sql = "DELETE FROM items WHERE item_id = ?";
//        try (Connection conn = getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setString(1, itemId.trim());
//            return stmt.executeUpdate() > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    public List<Item> getAllItems() {
//        List<Item> items = new ArrayList<>();
//        String sql = "SELECT * FROM items";
//        try (Connection conn = getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql);
//             ResultSet rs = stmt.executeQuery()) {
//            while (rs.next()) {
//                items.add(new Item(
//                        rs.getString("item_id"),
//                        rs.getString("name"),
//                        rs.getDouble("unit_price"),
//                        rs.getInt("quantity")
//                ));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return items;
//    }
//}

//
//package com.pahana.dao;
//
//import com.pahana.model.Item;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Properties;
//import java.io.InputStream;
//
//public class ItemDAO {
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
//    public String generateItemId() throws SQLException {
//        String prefix = "ITEM";
//        String sql = "SELECT MAX(item_id) FROM items WHERE item_id LIKE 'ITEM%'";
//        try (Connection conn = getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql);
//             ResultSet rs = stmt.executeQuery()) {
//            if (rs.next()) {
//                String lastItemId = rs.getString(1);
//                if (lastItemId != null && lastItemId.startsWith(prefix)) {
//                    int number = Integer.parseInt(lastItemId.replace(prefix, "")) + 1;
//                    return prefix + String.format("%03d", number);
//                }
//            }
//            return prefix + "001";
//        }
//    }
//
//    public boolean addItem(Item item) {
//        String sql = "INSERT INTO items (item_id, name, unit_price, quantity) VALUES (?, ?, ?, ?)";
//        try (Connection conn = getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setString(1, item.getItemId());
//            stmt.setString(2, item.getName());
//            stmt.setDouble(3, item.getUnitPrice());
//            stmt.setInt(4, item.getQuantity());
//            return stmt.executeUpdate() > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    public Item getItem(String itemId) {
//        String sql = "SELECT * FROM items WHERE item_id = ?";
//        try (Connection conn = getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setString(1, itemId.trim());
//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) {
//                    return new Item(
//                            rs.getString("item_id"),
//                            rs.getString("name"),
//                            rs.getDouble("unit_price"),
//                            rs.getInt("quantity")
//                    );
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public boolean updateItem(Item item) {
//        String sql = "UPDATE items SET name = ?, unit_price = ?, quantity = ? WHERE item_id = ?";
//        try (Connection conn = getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setString(1, item.getName());
//            stmt.setDouble(2, item.getUnitPrice());
//            stmt.setInt(3, item.getQuantity());
//            stmt.setString(4, item.getItemId());
//            return stmt.executeUpdate() > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    public boolean reduceItemQuantity(String itemId, int quantity) {
//        String sql = "UPDATE items SET quantity = quantity - ? WHERE item_id = ? AND quantity >= ?";
//        try (Connection conn = getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setInt(1, quantity);
//            stmt.setString(2, itemId);
//            stmt.setInt(3, quantity);
//            return stmt.executeUpdate() > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    public boolean deleteItem(String itemId) {
//        String sql = "DELETE FROM items WHERE item_id = ?";
//        try (Connection conn = getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setString(1, itemId.trim());
//            return stmt.executeUpdate() > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    public List<Item> getAllItems() {
//        List<Item> items = new ArrayList<>();
//        String sql = "SELECT * FROM items";
//        try (Connection conn = getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql);
//             ResultSet rs = stmt.executeQuery()) {
//            while (rs.next()) {
//                items.add(new Item(
//                        rs.getString("item_id"),
//                        rs.getString("name"),
//                        rs.getDouble("unit_price"),
//                        rs.getInt("quantity")
//                ));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return items;
//    }
//}


//
//package com.pahana.dao;
//
//import com.pahana.model.Item;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Properties;
//import java.io.InputStream;
//
//public class ItemDAO {
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
//    public String generateItemId() throws SQLException {
//        String prefix = "ITEM";
//        String sql = "SELECT MAX(item_id) FROM items WHERE item_id LIKE 'ITEM%'";
//        try (Connection conn = getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql);
//             ResultSet rs = stmt.executeQuery()) {
//            if (rs.next()) {
//                String lastItemId = rs.getString(1);
//                if (lastItemId != null && lastItemId.startsWith(prefix)) {
//                    int number = Integer.parseInt(lastItemId.replace(prefix, "")) + 1;
//                    return prefix + String.format("%03d", number);
//                }
//            }
//            return prefix + "001";
//        }
//    }
//
//    public boolean addItem(Item item) {
//        String sql = "INSERT INTO items (item_id, name, unit_price, quantity) VALUES (?, ?, ?, ?)";
//        try (Connection conn = getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setString(1, item.getItemId());
//            stmt.setString(2, item.getName());
//            stmt.setDouble(3, item.getUnitPrice());
//            stmt.setInt(4, item.getQuantity());
//            return stmt.executeUpdate() > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    public Item getItem(String itemId) {
//        String sql = "SELECT * FROM items WHERE item_id = ?";
//        try (Connection conn = getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setString(1, itemId.trim());
//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) {
//                    return new Item(
//                            rs.getString("item_id"),
//                            rs.getString("name"),
//                            rs.getDouble("unit_price"),
//                            rs.getInt("quantity")
//                    );
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public boolean updateItem(Item item) {
//        String sql = "UPDATE items SET name = ?, unit_price = ?, quantity = ? WHERE item_id = ?";
//        try (Connection conn = getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setString(1, item.getName());
//            stmt.setDouble(2, item.getUnitPrice());
//            stmt.setInt(3, item.getQuantity());
//            stmt.setString(4, item.getItemId());
//            return stmt.executeUpdate() > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    public boolean reduceItemQuantity(String itemId, int quantity) throws SQLException {
//        if (itemId == null || itemId.trim().isEmpty()) {
//            throw new SQLException("Item ID cannot be null or empty");
//        }
//        Connection conn = null;
//        PreparedStatement stmt = null;
//        try {
//            conn = getConnection();
//            conn.setAutoCommit(false);
//
//            // Lock the row for update
//            String selectSql = "SELECT quantity FROM items WHERE item_id = ? FOR UPDATE";
//            stmt = conn.prepareStatement(selectSql);
//            stmt.setString(1, itemId);
//            ResultSet rs = stmt.executeQuery();
//            if (!rs.next()) {
//                conn.rollback();
//                return false;
//            }
//            int currentQuantity = rs.getInt("quantity");
//            if (currentQuantity < quantity) {
//                conn.rollback();
//                return false;
//            }
//            stmt.close();
//
//            // Update quantity
//            String updateSql = "UPDATE items SET quantity = quantity - ? WHERE item_id = ? AND quantity >= ?";
//            stmt = conn.prepareStatement(updateSql);
//            stmt.setInt(1, quantity);
//            stmt.setString(2, itemId);
//            stmt.setInt(3, quantity);
//            int rowsAffected = stmt.executeUpdate();
//
//            if (rowsAffected > 0) {
//                conn.commit();
//                return true;
//            } else {
//                conn.rollback();
//                return false;
//            }
//        } catch (SQLException e) {
//            if (conn != null) {
//                try {
//                    conn.rollback();
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                }
//            }
//            throw e;
//        } finally {
//            if (stmt != null) {
//                try {
//                    stmt.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (conn != null) {
//                try {
//                    conn.setAutoCommit(true);
//                    conn.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    public boolean deleteItem(String itemId) {
//        String sql = "DELETE FROM items WHERE item_id = ?";
//        try (Connection conn = getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setString(1, itemId.trim());
//            return stmt.executeUpdate() > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    public List<Item> getAllItems() {
//        List<Item> items = new ArrayList<>();
//        String sql = "SELECT * FROM items";
//        try (Connection conn = getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql);
//             ResultSet rs = stmt.executeQuery()) {
//            while (rs.next()) {
//                items.add(new Item(
//                        rs.getString("item_id"),
//                        rs.getString("name"),
//                        rs.getDouble("unit_price"),
//                        rs.getInt("quantity")
//                ));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return items;
//    }
//}



package com.pahana.dao;

import com.pahana.model.Item;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.io.InputStream;

public class ItemDAO {
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

    public synchronized String generateItemId() throws SQLException {
        String prefix = "ITEM";
        String sql = "SELECT MAX(item_id) FROM items WHERE item_id LIKE ? FOR UPDATE";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            stmt.setString(1, prefix + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                String newItemId = prefix + "001";
                if (rs.next()) {
                    String lastItemId = rs.getString(1);
                    if (lastItemId != null && lastItemId.startsWith(prefix)) {
                        int number = Integer.parseInt(lastItemId.replace(prefix, "")) + 1;
                        newItemId = prefix + String.format("%03d", number);
                    }
                }
                // Validate uniqueness
                String checkSql = "SELECT COUNT(*) FROM items WHERE item_id = ?";
                try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                    checkStmt.setString(1, newItemId);
                    ResultSet checkRs = checkStmt.executeQuery();
                    if (checkRs.next() && checkRs.getInt(1) > 0) {
                        throw new SQLException("Generated item ID already exists");
                    }
                }
                conn.commit();
                return newItemId;
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        }
    }

    public boolean addItem(Item item) {
        String sql = "INSERT INTO items (item_id, name, unit_price, quantity) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            if (item.getQuantity() < 0) {
                throw new SQLException("Quantity cannot be negative");
            }
            stmt.setString(1, item.getItemId());
            stmt.setString(2, item.getName());
            stmt.setDouble(3, item.getUnitPrice());
            stmt.setInt(4, item.getQuantity());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Item getItem(String itemId) {
        String sql = "SELECT * FROM items WHERE item_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, itemId.trim());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Item(
                            rs.getString("item_id"),
                            rs.getString("name"),
                            rs.getDouble("unit_price"),
                            rs.getInt("quantity")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateItem(Item item) {
        String sql = "UPDATE items SET name = ?, unit_price = ?, quantity = ? WHERE item_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            if (item.getQuantity() < 0) {
                throw new SQLException("Quantity cannot be negative");
            }
            stmt.setString(1, item.getName());
            stmt.setDouble(2, item.getUnitPrice());
            stmt.setInt(3, item.getQuantity());
            stmt.setString(4, item.getItemId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean reduceItemQuantity(String itemId, int quantity) throws SQLException {
        if (itemId == null || itemId.trim().isEmpty()) {
            throw new SQLException("Item ID cannot be null or empty");
        }
        if (quantity <= 0) {
            throw new SQLException("Quantity must be positive");
        }
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            String selectSql = "SELECT quantity FROM items WHERE item_id = ? FOR UPDATE";
            stmt = conn.prepareStatement(selectSql);
            stmt.setString(1, itemId);
            ResultSet rs = stmt.executeQuery();
            if (!rs.next()) {
                conn.rollback();
                return false;
            }
            int currentQuantity = rs.getInt("quantity");
            if (currentQuantity < quantity) {
                conn.rollback();
                return false;
            }
            stmt.close();
            String updateSql = "UPDATE items SET quantity = quantity - ? WHERE item_id = ? AND quantity >= ?";
            stmt = conn.prepareStatement(updateSql);
            stmt.setInt(1, quantity);
            stmt.setString(2, itemId);
            stmt.setInt(3, quantity);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                conn.commit();
                return true;
            } else {
                conn.rollback();
                return false;
            }
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            throw e;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean deleteItem(String itemId) {
        String sql = "DELETE FROM items WHERE item_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, itemId.trim());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM items";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                items.add(new Item(
                        rs.getString("item_id"),
                        rs.getString("name"),
                        rs.getDouble("unit_price"),
                        rs.getInt("quantity")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }
}