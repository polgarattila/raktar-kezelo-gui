package com.raktar.raktarapp;

public abstract class AbstractProduct {
    protected String name;
    protected String sku;
    protected int quantity;
    protected double price;

    // Konstruktor az alap adatokhoz
    public AbstractProduct(String name, String sku, int quantity, double price) {
        this.name = name;
        this.sku = sku;
        this.quantity = quantity;
        this.price = price;
    }

    // Absztrakt metódus: Minden gyereknek kötelező lesz megírnia a saját verzióját!
    public abstract String getProductDetails();

    // Sima metódus: Ezt minden gyerek ugyanúgy örökli
    public void sell(int amount) {
        if (amount <= quantity) {
            quantity -= amount;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity >= 0) {
            this.quantity = quantity;
        } else {
            System.out.println("Hiba: A mennyiség nem lehet negatív!");
        }
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
