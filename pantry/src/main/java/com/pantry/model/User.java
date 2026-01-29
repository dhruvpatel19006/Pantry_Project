package com.pantry.model;

import java.time.LocalDateTime;

public class User {
    private String username;
    private String email;
    // private String passwordHashString;
    private static int idCounter = 1;
    private final int id;
    private LocalDateTime createdAt;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
        this.id = idCounter++;
        this.createdAt = LocalDateTime.now();
    }

    public User(int id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.createdAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
