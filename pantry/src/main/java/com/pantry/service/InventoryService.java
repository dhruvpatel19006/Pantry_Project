package com.pantry.service;



import com.pantry.model.UserItem;
import com.pantry.repository.InventoryRepository;
import com.pantry.util.ExpirationUtils;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class InventoryService {
    private final InventoryRepository repository;

    public InventoryService(InventoryRepository repository){
        this.repository = repository;
    }

    public void addItem(UserItem item){
        repository.addItem(item);
    }

    public List<UserItem> getAllItems(){
        return repository.findAll();
    }

    public List<UserItem> getExpiredItems(){
        List<UserItem> allItems = getAllItems();
        List<UserItem> expiredItems = new ArrayList<>();
        for (UserItem item : allItems) {
            if (ExpirationUtils.isExpired(item)) {
                expiredItems.add(item);
            }
        }
        return expiredItems;
    }

    public List<UserItem> getItemsExpiringWithinDays(int days){
        List<UserItem> allItems = getAllItems();
        List<UserItem> expiringItems = new ArrayList<>();
        for (UserItem item : allItems) {
            if (ExpirationUtils.expiresWithinDays(item, days)) {
                expiringItems.add(item);
            }
        }
        return expiringItems;
    }

    public List<UserItem> getItemsSortedByExpiration(){
        List<UserItem> allItems = getAllItems();
        allItems.sort((item1, item2) -> item1.getExpirationDate().compareTo(item2.getExpirationDate()));
        return allItems;
    }

    public void removeItemById(UUID id) {
        repository.removeItemById(id);
    }
}
