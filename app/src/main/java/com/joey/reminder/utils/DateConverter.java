package com.joey.reminder.utils;

import android.text.format.DateFormat;

import java.util.Date;

import androidx.room.TypeConverter;

public class DateConverter {

    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    public static String dateFormat(Date date) {
        return (String) DateFormat.format("MMM dd, yyyy", date);
    }
}
