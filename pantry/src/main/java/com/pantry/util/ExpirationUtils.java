package com.pantry.util;

import java.time.LocalDate;
import com.pantry.model.UserItem;

public class ExpirationUtils {

    public static boolean isExpired(UserItem item) {
        return item.getExpirationDate().isBefore(LocalDate.now());
    }

    public static boolean expiresToday(UserItem item) {
        return item.getExpirationDate().isEqual(LocalDate.now());
    }

    public static long daysUntilExpiration(UserItem item) {
        LocalDate expirationDate = item.getExpirationDate();
        return java.time.temporal.ChronoUnit.DAYS.between(LocalDate.now(), expirationDate);
    }
    

    public static boolean expiresWithinDays(UserItem item, int days) {
        if(days < 0){
            return false;
        }
        LocalDate expirationDate = item.getExpirationDate();
        if (isExpired(item)) {
            return false;
        }
        return java.time.temporal.ChronoUnit.DAYS.between(LocalDate.now(), expirationDate) <= days;
    }

}
