package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TableDeleter {

    public static void main(String[] args) {
        try (Connection conn = Main.getConnection()) {
            deleteTable(conn, "foods");
            System.out.println("Table 'foods' deleted successfully.");
        } catch (SQLException e) {
            System.err.println("Error deleting table: " + e.getMessage());
        }
    }

    private static void deleteTable(Connection conn, String tableName) throws SQLException {
        String query = "DROP TABLE IF EXISTS " + tableName;
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.executeUpdate();
        }
    }
}
