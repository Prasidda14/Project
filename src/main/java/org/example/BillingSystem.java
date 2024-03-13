package org.example;
import model.Food;
import model.Order;

import java.sql.SQLException;
import java.util.List;

public class BillingSystem {
    public static void main(String[] args) {
        try {

            List<Food> foods = Main.getAllFoods();


            Order order = new Order();
            if (foods.size() >= 3) {
                order.addItem(foods.get(0));
                order.addItem(foods.get(1));
                order.addItem(foods.get(2));
            } else {
                System.out.println("Insufficient food items in the database.");
                return; // Exit the program if there are not enough food items
            }

            // Calculate total and generate bill
            double total = order.calculateTotal();
            generateBill(order, total);
        } catch (SQLException e) {
            System.err.println("Error retrieving food items: " + e.getMessage());
        }
    }

    private static void generateBill(Order order, double total) {
        System.out.println("----- Your Bill -----");
        for (Food item : order.getItems()) {
            System.out.println(item.getName() + ": Rs" + item.getPrice());
        }
        System.out.println("Total: Rs" + total);
        System.out.println("---------------------");
    }
}
