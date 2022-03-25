package sales.tax;

public class Item {

    public static final double SALES_TAX = 0.10;
    public static final double IMPORT_TAX = 0.05;

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

    public double getTotalPriceWithTax() {
        return price * quantity + getTaxAmount();
    }

    public double getTaxAmount() {
        double tax = 0;
        double cost = price * quantity;

        // Compute basic sales tax
        if (!isTaxExempt()) {
            tax += (Math.ceil(cost * SALES_TAX * 20)) / 20;
        }

        // Compute import tax
        if (isImported()) {
            tax += (Math.ceil(cost * IMPORT_TAX * 20)) / 20;
        }

        return tax;
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
