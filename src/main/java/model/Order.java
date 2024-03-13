package model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Food> items;

    public Order() {
        items = new ArrayList<>();
    }

    public void addItem(Food food) {
        items.add(food);
    }

    public List<Food> getItems() {
        return items;
    }

    public double calculateTotal() {
        double total = 0;
        for (Food item : items) {
            total += item.getPrice();
        }
        return total;
    }
}
