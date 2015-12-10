package domain;

public class Discount {
    private final String barcode;
    private final double discount;

    public Discount(String barcode, double discount) {
        this.barcode = barcode;
        this.discount = discount;
    }

    public String getBarcode() {
        return barcode;
    }

    public double getDiscount() {
        return discount;
    }
}