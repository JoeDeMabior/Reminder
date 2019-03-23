package com.joey.reminder.repositories;

import android.app.Application;

import com.joey.reminder.databases.Reminder;
import com.joey.reminder.databases.ReminderDao;
import com.joey.reminder.databases.ReminderDatabase;

import java.util.List;

import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;

public class ReminderRepository {
    private ReminderDao reminderDao;
    private LiveData<List<Reminder>> allReminders;

    public ReminderRepository(Application application) {
        ReminderDatabase reminderDatabase = ReminderDatabase.getInstance(application);
        reminderDao = reminderDatabase.reminderDao();
        allReminders = reminderDao.getAll();
    }

    @WorkerThread
    public LiveData<List<Reminder>> getAllReminders() {
        return allReminders;
    }

    @WorkerThread
    public void insert(Reminder reminder) {
        reminderDao.insert(reminder);
    }

    @WorkerThread
    public void update(Reminder reminder) {
        reminderDao.update(reminder);
    }

    @WorkerThread
    public void delete(Reminder reminder) {
        reminderDao.delete(reminder);
    }

    @WorkerThread
    public void deleteAll() {
        reminderDao.deleteAll();
    }
}
