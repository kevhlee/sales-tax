package sales.tax;

public class Item {

    private final String name;
    private final double price;
    private final int quantity;
    private boolean taxExempt;
    private boolean imported;

    public Item(String name, double price, int quantity) {
        this(name, price, quantity, false, false);
    }

    public Item(String name, double price, int quantity, boolean taxExempt, boolean imported) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.taxExempt = taxExempt;
        this.imported = imported;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isTaxExempt() {
        return taxExempt;
    }

    public boolean isImported() {
        return imported;
    }

    public void setImported(boolean imported) {
        this.imported = imported;
    }

    public void setTaxExempt(boolean taxExempt) {
        this.taxExempt = taxExempt;
    }

    @Override
    public String toString() {
        return name;
    }

}
