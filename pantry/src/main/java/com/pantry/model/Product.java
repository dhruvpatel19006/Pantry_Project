package com.pantry.model;
import java.util.UUID;

public class Product {
    private final UUID id;

    private int quantity;
    private String name;
    private String expirationDate;

    public Product(String name, int quantity, String expirationDate) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
    }

    public UUID getId() {
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
