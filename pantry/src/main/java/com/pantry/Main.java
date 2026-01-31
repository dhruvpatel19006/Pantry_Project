package com.pantry;

import com.pantry.config.DatabaseConfig;
import com.pantry.model.UserItem;
import com.pantry.repository.InventoryRepository;
import com.pantry.repository.SQLiteInventoryRepository;
import com.pantry.service.InventoryService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final SQLiteInventoryRepository repo = new SQLiteInventoryRepository();
    private static final InventoryService inventoryService = new InventoryService(repo);
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        DatabaseConfig.initializeDatabase();
        System.out.println("🍎 Welcome to Pantry Tracker");

        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> addItem();
                case "2" -> listItems();
                case "3" -> listExpiringSoon();
                case "4" -> removeItem();
                case "5" -> {
                    running = false;
                    System.out.println("👋 Goodbye!");
                }
                default -> System.out.println("❌ Invalid option.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Add item");
        System.out.println("2. View inventory");
        System.out.println("3. View items expiring soon");
        System.out.println("4. Remove Item");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
    }

    private static void addItem() {
        try {
            System.out.print("Item name: ");
            String name = scanner.nextLine();

            System.out.print("Expiration date (YYYY-MM-DD): ");
            LocalDate expirationDate = LocalDate.parse(scanner.nextLine());

            System.out.print("Quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine());

            UserItem item = new UserItem(name, expirationDate, quantity);
            inventoryService.addItem(item);

            System.out.println("✅ Item added!");

        } catch (DateTimeParseException e) {
            System.out.println("❌ Invalid date format.");
        } catch (NumberFormatException e) {
            System.out.println("❌ Quantity must be a number.");
        }
    }

    private static void listItems() {
        List<UserItem> items = inventoryService.getAllItems();

        if (items.isEmpty()) {
            System.out.println("📦 Inventory is empty.");
            return;
        }

        System.out.println("\n📋 Inventory:");
        for(int i = 0; i < items.size(); i++) {
            UserItem item = items.get(i);
            System.out.printf("%d. %s | Qty: %d | Expires: %s%n",
                    i + 1, item.getName(), item.getQuantity(), item.getExpirationDate());
        }


    }

    private static void listExpiringSoon(){
        System.out.println("Enter number of days: ");
        int days = Integer.parseInt(scanner.nextLine());
        List<UserItem> items = inventoryService.getItemsExpiringWithinDays(days);

        if (items.isEmpty()) {
            System.out.println("📦 No items expiring soon.");
            return;
        }

        System.out.println("\n📋 Items expiring soon:");
        for (UserItem item : items) {
            System.out.printf(
                    "- %s | Qty: %d | Expires: %s%n",
                    item.getName(),
                    item.getQuantity(),
                    item.getExpirationDate()
            );
        }
    }

    private static void removeItem() {
        try {
            listItems();
            System.out.print("Enter item number to remove: ");
            int itemNumber = Integer.parseInt(scanner.nextLine());

            if (itemNumber < 1 || itemNumber > inventoryService.getAllItems().size()) {
                System.out.println("❌ Invalid item number.");
                return;
            }

            UserItem itemToRemove = inventoryService.getAllItems().get(itemNumber - 1);
            inventoryService.removeItemById(itemToRemove.getId());
            System.out.println("✅ Item removed.");

        } catch (IllegalArgumentException e) {
            System.out.println("❌ Invalid item number.");
        }
    }

}
