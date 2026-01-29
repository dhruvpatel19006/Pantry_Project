package com.pantry;

import com.pantry.model.UserItem;
import com.pantry.repository.InventoryRepository;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final InventoryRepository inventory = new InventoryRepository();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("🍎 Welcome to Pantry Tracker");

        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> addItem();
                case "2" -> listItems();
                case "3" -> {
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
        System.out.println("3. Exit");
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
            inventory.addItem(item);

            System.out.println("✅ Item added!");

        } catch (DateTimeParseException e) {
            System.out.println("❌ Invalid date format.");
        } catch (NumberFormatException e) {
            System.out.println("❌ Quantity must be a number.");
        }
    }

    private static void listItems() {
        List<UserItem> items = inventory.findAll();

        if (items.isEmpty()) {
            System.out.println("📦 Inventory is empty.");
            return;
        }

        System.out.println("\n📋 Inventory:");
        for (UserItem item : items) {
            System.out.printf(
                    "- %s | Qty: %d | Expires: %s%n",
                    item.getName(),
                    item.getQuantity(),
                    item.getExpirationDate()
            );
        }
    }
}
