package com.raktar.raktarapp;

public class DurableProduct extends AbstractProduct implements Discountable {
    private int warrantyMonths;

    public DurableProduct(String name, String sku, int quantity, double price, int warrantyMonths) {
        super(name, sku, quantity, price);
        this.warrantyMonths = warrantyMonths;
    }

    @Override
    public String getProductDetails() {
        return name + ", " + sku + ", " + quantity + ", " + price + ", " + warrantyMonths;
    }

    @Override
    public void applyDiscount(double percentage) {
        // Itt számoljuk ki az új árat: ár = ár * (1 - százalék/100)
        this.price -= this.price * (percentage / 100);
    }

    public int getWarrantyMonths() {
        return warrantyMonths;
    }
}
