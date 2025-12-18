package com.sports.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Utility class for managing database connections
 */
public class DatabaseConnection {
    
    private static final Logger logger = Logger.getLogger(DatabaseConnection.class.getName());
    
    // JDBC URL, username and password for MySQL connection
    private static final String URL = "jdbc:mysql://localhost:3306/sports_db";
    private static final String USER = "username"; //use your own
    private static final String PASSWORD = "password"; //user your own
    
    // Static block to load the MySQL JDBC driver
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            logger.info("MySQL JDBC Driver loaded successfully");
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Failed to load MySQL JDBC driver", e);
            throw new RuntimeException("Failed to load MySQL JDBC driver", e);
        }
    }
    
    /**
     * Gets a connection to the database
     * 
     * @return A Connection object to the database
     * @throws SQLException If connection cannot be established
     */
    public static Connection getConnection() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            logger.info("Database connection established successfully");
            return conn;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to establish database connection", e);
            throw e;
        }
    }
    
    /**
     * Closes the database connection safely
     * 
     * @param connection The connection to close
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                logger.info("Database connection closed successfully");
            } catch (SQLException e) {
                logger.log(Level.WARNING, "Error closing database connection", e);
            }
        }
    }
} 