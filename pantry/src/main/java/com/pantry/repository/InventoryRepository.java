package com.pantry.repository;

import java.util.*;
import com.pantry.model.UserItem;

public class InventoryRepository {
    private List<UserItem> items = new ArrayList<>();

    public void addItem(UserItem item) {
        items.add(item);
    }

    public List<UserItem> getItems() {
        return items;
    }

    public void removeItem(UserItem item) {
        items.remove(item);
    }

    public UserItem findItemById(UUID id) {
        for (UserItem item : items) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    public void removeItemById(UUID id) {
        items.removeIf(item -> item.getId().equals(id));
    }

    public List<UserItem> findAll() {
        return new ArrayList<>(items);
    }
}
