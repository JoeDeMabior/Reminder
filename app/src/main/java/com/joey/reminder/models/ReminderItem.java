package com.joey.reminder.models;

public class ReminderItem {
    public String title;
    public String dateTime;
    public String repeat;
    public String repeatNo;
    public String repeatType;
    public String active;

    public ReminderItem(String title, String dateTime, String repeat, String repeatNo, String repeatType, String active) {
        this.title = title;
        this.dateTime = dateTime;
        this.repeat = repeat;
        this.repeatNo = repeatNo;
        this.repeatType = repeatType;
        this.active = active;
    }
}
