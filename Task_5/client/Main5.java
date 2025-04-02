package com.techshop.client;

import com.techshop.entity.DatabaseConnection;
import com.techshop.exception.DatabaseException;

import java.sql.Connection;

public class Main5 {
    public static void main(String[] args) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            if (conn != null) {
                System.out.println("Connection established successfully!");
            }
        } catch (DatabaseException e) {
            System.err.println(e.getMessage());
        }
    }
}