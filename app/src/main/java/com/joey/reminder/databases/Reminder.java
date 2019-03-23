package com.joey.reminder.databases;

import com.joey.reminder.utils.DateConverter;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity(tableName = "tasks")
public class Reminder {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    @TypeConverters(DateConverter.class)
    private String date;
    private String time;
    private String repeat;
    private String repeatNo;
    private String repeatType;
    private String active;

    Reminder(int id, String title, String date, String time, String repeat, String repeatNo, String repeatType, String active) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.time = time;
        this.repeat = repeat;
        this.repeatNo = repeatNo;
        this.repeatType = repeatType;
        this.active = active;
    }

    int getId() {
        return id;
    }

    String getTitle() {
        return title;
    }

    String getDate() {
        return date;
    }

    String getTime() {
        return time;
    }

    String getRepeat() {
        return repeat;
    }

    String getRepeatNo() {
        return repeatNo;
    }

    String getRepeatType() {
        return repeatType;
    }

    String getActive() {
        return active;
    }
}
