package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class create {

    private static final String URL = "jdbc:sqlite:D:/project/PD/src/main/billing.db";

    public static void main(String[] args) {
        try {
            // Establish connection to the database
            Connection conn = DriverManager.getConnection(URL);

            // Create statement object
            Statement stmt = conn.createStatement();

            // Create table
            String createTableSQL = "CREATE TABLE IF NOT EXISTS foods (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL," +
                    "price REAL NOT NULL)";

            // Execute SQL query to create table
            stmt.execute(createTableSQL);

            // Close resources
            stmt.close();
            conn.close();

            System.out.println("Table 'foods' created successfully.");
        } catch (SQLException e) {
            System.err.println("Error creating table: " + e.getMessage());
        }
    }
}
