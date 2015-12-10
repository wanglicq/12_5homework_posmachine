package domain;

public final class Item {
    private final String barcode;
    private double price;

    public Item(final String barcode, final double price) {
        this.barcode = barcode;
        this.price = price;
    }

    public String getBarcode() {
        return barcode;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
