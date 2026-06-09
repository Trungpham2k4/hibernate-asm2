package fa.training.util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Validator {
    public static boolean isNotBlank(String str) {
        return str != null && !str.trim().isEmpty();
    }

    public static boolean isPositive(int number) {
        return number > 0;
    }

    public static boolean isValidSeatStatus(String status) {
        return status.isEmpty() ||
                "AVAILABLE".equalsIgnoreCase(status) ||
               "NOT_AVAILABLE".equalsIgnoreCase(status) ||
               "BOOKED".equalsIgnoreCase(status);
    }

    public static boolean isValidSeatType(String type) {
        return type.isEmpty() ||
                "VIP".equalsIgnoreCase(type) ||
               "NORMAL".equalsIgnoreCase(type);
    }

    public static boolean isValidDate(String date) {
        if(date == null) return true;
        try{
            LocalDate.parse(date, Constant.DATE_FORMATTER);
            return true;
        }catch (DateTimeParseException e){
            return false;
        }
    }
}
