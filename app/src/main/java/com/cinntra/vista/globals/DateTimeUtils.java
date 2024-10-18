package com.cinntra.vista.globals;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateTimeUtils {

    // Static method to get today's date in the format dd-MM-yyyy
    public static String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        return dateFormat.format(calendar.getTime());
    }

    // Static method to get the current time in the format HH:mm:ss
    public static String getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        return timeFormat.format(calendar.getTime()).toUpperCase();
    }

    // Static method to get the current time in the format HH:mm:ss
    public static String getCurrentTime1() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:MM", Locale.getDefault());
        return timeFormat.format(calendar.getTime()).toUpperCase();
    }
}

