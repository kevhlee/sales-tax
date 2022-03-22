package sales.tax;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private final List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
    }

    public double getTotalCost() {
        double totalCost = 0.0;

        for (Item item : items) {

        }

        return totalCost;
    }

}

