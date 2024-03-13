package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class menu {

    public static void main(String[] args) {
        try (Connection conn = Main.getConnection()) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Select an option:");
            System.out.println("1. Order food");
            System.out.println("2. Add new food item");

            int choice = scanner.nextInt();
            if (choice == 1) {
                orderFood(conn);
            } else if (choice == 2) {
                addFoodItem(conn);
            } else {
                System.out.println("Invalid choice.");
            }

            scanner.close();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void orderFood(Connection conn) throws SQLException {
        // Display food menu
        displayMenu();

        // Take user input for food selection
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter food item (Momo/Pizza/Burger): ");
        String foodChoice = scanner.nextLine().trim();

        // Fetch the price of the selected food item from the database
        double price = getFoodPrice(conn, foodChoice);
        if (price != -1) {
            System.out.println("Price of " + foodChoice + ": Rs" + price);
        } else {
            System.out.println("Invalid food item selected.");
            return;
        }

        // Take user input for quantity
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();

        // Calculate total
        double total = price * quantity;
        System.out.println("Total: Rs" + total);
    }

    private static void addFoodItem(Connection conn) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter food name: ");
        String foodName = scanner.nextLine().trim();
        System.out.print("Enter food price: ");
        double price = scanner.nextDouble();

        insertFood(conn, foodName, price);
        System.out.println("Food item added successfully.");
    }

    private static void displayMenu() {
        System.out.println("----- Food Menu -----");
        System.out.println("1. Momo - Rs100");
        System.out.println("2. Pizza - Rs200");
        System.out.println("3. Burger - Rs150");
        System.out.println("----------------------");
    }

    private static double getFoodPrice(Connection conn, String foodName) throws SQLException {
        String query = "SELECT price FROM foods WHERE name = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, foodName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("price");
            } else {
                return -1; // Indicate invalid food item
            }
        }
    }

    private static void insertFood(Connection conn, String name, double price) throws SQLException {
        String query = "INSERT INTO foods (name, price) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setDouble(2, price);
            pstmt.executeUpdate();
        }
    }
}
