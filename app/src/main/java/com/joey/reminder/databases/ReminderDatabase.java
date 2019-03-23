package com.joey.reminder.databases;

import android.content.Context;

import com.joey.reminder.utils.DateConverter;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Reminder.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class ReminderDatabase extends RoomDatabase {

    public abstract ReminderDao reminderDao();

    private static volatile ReminderDatabase INSTANCE;
    private static final String DATABASE_NAME = "reminders";

    public static ReminderDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (ReminderDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ReminderDatabase.class,
                            ReminderDatabase.DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
