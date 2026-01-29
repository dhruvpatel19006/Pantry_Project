package com.pantry.model;
import java.util.UUID;
import java.time.LocalDate;

public class UserItem {
    private final UUID id;
    private String name;
    private LocalDate expirationDate;
    private LocalDate dateAdded;
    private int quantity;

    public UserItem(String name, LocalDate expirationDate, int quantity) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.expirationDate = expirationDate;
        this.dateAdded = LocalDate.now();
        this.quantity = quantity;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
