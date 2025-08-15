package com.pahana.dao;

import com.pahana.model.User;

import java.sql.*;
import java.util.Properties;
import java.io.InputStream;

public class UserDAO {
//    private Connection getConnection() throws SQLException {
//        Properties props = new Properties();
//        try (InputStream input = getClass().getClassLoader().getResourceAsStream("db.properties")) {
//            props.load(input);
//        } catch (Exception e) {
//            throw new SQLException("Failed to load database properties", e);
//        }
//        return DriverManager.getConnection(
//                props.getProperty("db.url"),
//                props.getProperty("db.username"),
//                props.getProperty("db.password")
//        );
//    }
private Connection getConnection() throws SQLException {
    Properties props = new Properties();
    try (InputStream input = getClass().getClassLoader().getResourceAsStream("db.properties")) {
        props.load(input);
    } catch (Exception e) {
        throw new SQLException("Failed to load database properties", e);
    }

    try {
        // Add this line to explicitly load the driver
        Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
        throw new SQLException("MySQL Driver not found", e);
    }

    return DriverManager.getConnection(
            props.getProperty("db.url"),
            props.getProperty("db.username"),
            props.getProperty("db.password")
    );
}

    public boolean validateUser(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



}