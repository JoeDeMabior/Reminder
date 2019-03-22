package com.joey.reminder.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Task.class}, version = 1)
@TypeConverters(DateConverter.class)
public abstract class TaskDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();

    private static final String DATABASE_NAME = "reminders";

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static volatile TaskDatabase taskDatabase;

    public static TaskDatabase getInstance(Context context) {
        if (taskDatabase == null) {
            synchronized (LOCK) {
                if (taskDatabase == null) {
                    taskDatabase = Room.databaseBuilder(context.getApplicationContext(),
                            TaskDatabase.class,
                            TaskDatabase.DATABASE_NAME)
                            .build();
                }
            }
        }
        return taskDatabase;
    }
}
