package com.pantry;
import com.pantry.model.User;
import com.pantry.model.UserItem;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        UserItem item = new UserItem("Milk", LocalDate.of(2026, 12, 31), 2);

        System.out.println(item.getName());
        System.out.println(item.getExpirationDate());
        System.out.println(item.getDateAdded());
        System.out.println(item.getQuantity());
    }
}
