package com.pantry.repository;

import com.pantry.model.UserItem;
import com.pantry.config.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.*;


public class SQLiteInventoryRepository implements InventoryRepository{

    @Override
    public void addItem(UserItem item) {
        
        String sql = "INSERT INTO user_items(id, name, expiration_date, quantity) VALUES(?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, item.getId().toString());
            pstmt.setString(2, item.getName());
            pstmt.setString(3, item.getExpirationDate().toString());
            pstmt.setInt(4, item.getQuantity());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error adding item to database", e);
        }
    }

    @Override
    public List<UserItem> findAll() {
        List<UserItem> items = new ArrayList<>();
        String sql = "SELECT * FROM user_items";
        try(Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                // Map ResultSet to UserItem object
                UserItem item = new UserItem(
                    UUID.fromString(rs.getString("id")),
                    rs.getString("name"),
                    java.time.LocalDate.parse(rs.getString("expiration_date")),
                    rs.getInt("quantity")
                );
                items.add(item);
            }
            return items;

        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving items from database", e);
        }
    }

    @Override
    public UserItem findItemById(UUID id) {
        // Implementation for finding item by ID in SQLite database
        return null;
    }

    @Override
    public void removeItemById(UUID id) {
        // Implementation for removing item by ID in SQLite database
    }

    @Override
    public void updateItem(UserItem item) {
        // Implementation for updating item in SQLite database
    }
}
