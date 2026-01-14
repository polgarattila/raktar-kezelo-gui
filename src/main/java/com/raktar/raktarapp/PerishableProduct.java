package com.raktar.raktarapp;


public class PerishableProduct extends AbstractProduct {

    private String expirationDate;

    public PerishableProduct(String name, String sku, int quantity, double price, String expirationDate) {
        super(name, sku, quantity, price);
        this.expirationDate = expirationDate;
    }

    @Override
    public String getProductDetails() {
        return name + ", " + sku + ", " + quantity + ", " + price + ", " + expirationDate;
    }
}
