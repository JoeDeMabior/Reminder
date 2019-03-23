package com.joey.reminder.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "items")
public class Task {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String date;
    private String time;
    private String repeat;
    private String repeat_no;
    private String repeat_type;
    private String active;

    Task(int id, String title, String date, String time, String repeat, String repeat_no, String repeat_type, String active) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.time = time;
        this.repeat = repeat;
        this.repeat_no = repeat_no;
        this.repeat_type = repeat_type;
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

    String getRepeat_no() {
        return repeat_no;
    }

    String getRepeat_type() {
        return repeat_type;
    }

    String getActive() {
        return active;
    }
}
