package com.CleaningService.JavaProject.utils;

import java.sql.Connection;

public class DatabaseConnectionTest {
    public static void main(String[] args) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            System.out.println("Database connection successful!");
            
            if (conn != null && conn.isValid(1)) {
                System.out.println("Connection is valid");
                System.out.println("Connected to: " + conn.getMetaData().getURL());
                conn.close();
            }
        } catch (Exception e) {
            System.err.println("Connection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}