package com.pantry.repository;

import java.util.*;
import com.pantry.model.UserItem;

public interface InventoryRepository {
    void addItem(UserItem item);
    List<UserItem> findAll();
    UserItem findItemById(UUID id);
    void removeItemById(UUID id);
    void updateItem(UserItem item);
}

