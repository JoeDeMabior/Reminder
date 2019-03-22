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

    public Task(int id, String title, String date, String time, String repeat, String repeat_no, String repeat_type, String active) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.time = time;
        this.repeat = repeat;
        this.repeat_no = repeat_no;
        this.repeat_type = repeat_type;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getRepeat() {
        return repeat;
    }

    public String getRepeat_no() {
        return repeat_no;
    }

    public String getRepeat_type() {
        return repeat_type;
    }

    public String getActive() {
        return active;
    }
}
