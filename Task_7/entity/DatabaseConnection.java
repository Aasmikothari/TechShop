package com.techshop.entity;

import com.techshop.exception.DatabaseException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/tecshop";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() {
        int maxRetries = 3;
        int attempt = 0;

        while (attempt < maxRetries) {
            try {
                attempt++;
                System.out.println("Attempting to connect to the database... (Try " + attempt + ")");
                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Database connected successfully!");
                return connection;
            } catch (SQLException e) {
                System.err.println("Database connection failed: " + e.getMessage());
                if (attempt == maxRetries) {
                    throw new DatabaseException("Failed to connect to the database after " + maxRetries + " attempts.", e);
                }
            }

            // Wait before retrying
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        throw new DatabaseException("Unexpected error while connecting to the database.");
    }
}