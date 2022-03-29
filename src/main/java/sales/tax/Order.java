package sales.tax;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private final List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
    }

    public double getOrderSalesTax() {
        double orderSalesTax = 0.0;

        for (Item item : items) {
            orderSalesTax += item.getTaxAmount();
        }

        return orderSalesTax;
    }

    public double getOrderTotal() {
        double orderTotal = 0.0;

        for (Item item : items) {
            orderTotal += item.getTotalPriceWithTax();
        }

        return orderTotal;
    }

    public String generateReceipt() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\n");
        stringBuilder.append("Receipt Details:");
        stringBuilder.append("\n");

        DecimalFormat df = new DecimalFormat("$0.00");

        for (Item item : items) {
            stringBuilder.append(item.getQuantity());
            stringBuilder.append(" ");
            stringBuilder.append(item.getName());
            stringBuilder.append(": ");
            stringBuilder.append(df.format(item.getTotalPriceWithTax()));
            stringBuilder.append("\n");
        }

        stringBuilder.append("Sales Taxes: ");
        stringBuilder.append(df.format(getOrderSalesTax()));
        stringBuilder.append("\n");
        stringBuilder.append("Total: ");
        stringBuilder.append(df.format(getOrderTotal()));

        return stringBuilder.toString();
    }

}
