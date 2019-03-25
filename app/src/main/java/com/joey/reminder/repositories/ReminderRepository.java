package com.joey.reminder.repositories;

import android.app.Application;
import android.os.AsyncTask;

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
        new InsertAsyncTask(reminderDao).execute(reminder);
    }

    @WorkerThread
    public void update(Reminder reminder) {
        new UpdateAsyncTask(reminderDao).execute(reminder);
    }

    @WorkerThread
    public void delete(Reminder reminder) {
        new DeleteAsyncTask(reminderDao).execute(reminder);
    }

    @WorkerThread
    public void deleteAll() {
        new DeleteAllAsyncTask(reminderDao).execute();
    }

    private static class InsertAsyncTask extends AsyncTask<Reminder, Void, Void> {
        private ReminderDao reminderDao;

        InsertAsyncTask(ReminderDao reminderDao) {
            this.reminderDao = reminderDao;
        }

        @Override
        protected Void doInBackground(Reminder... reminders) {
            reminderDao.insert(reminders[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Reminder, Void, Void> {
        private ReminderDao reminderDao;

        UpdateAsyncTask(ReminderDao reminderDao) {
            this.reminderDao = reminderDao;
        }

        @Override
        protected Void doInBackground(Reminder... reminders) {
            reminderDao.update(reminders[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Reminder, Void, Void> {
        private ReminderDao reminderDao;

        DeleteAsyncTask(ReminderDao reminderDao) {
            this.reminderDao = reminderDao;
        }

        @Override
        protected Void doInBackground(Reminder... reminders) {
            reminderDao.delete(reminders[0]);
            return null;
        }
    }

    private static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private ReminderDao reminderDao;

        DeleteAllAsyncTask(ReminderDao reminderDao) {
            this.reminderDao = reminderDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            reminderDao.deleteAll();
            return null;
        }
    }
}
