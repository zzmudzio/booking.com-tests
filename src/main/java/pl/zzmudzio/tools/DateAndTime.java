package pl.zzmudzio.tools;

import java.time.LocalDate;

public class DateAndTime {
    public static String getCurrentDayOfMonth() {
        LocalDate localDate = LocalDate.now();
        return String.valueOf(localDate.getDayOfMonth());
    }
}
