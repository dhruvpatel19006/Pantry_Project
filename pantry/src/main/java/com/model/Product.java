package com.model;

public class Product {
    private int id;
    private static int idCounter = 0;
    private int quantity;
    private String name;
    private String expirationDate;

    public Product(String name, int quantity, String expirationDate) {
        this.id = idCounter++;
        this.name = name;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
}
