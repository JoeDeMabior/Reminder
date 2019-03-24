package com.joey.reminder.viewmodels;

import android.app.Application;

import com.joey.reminder.databases.Reminder;
import com.joey.reminder.repositories.ReminderRepository;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ReminderViewModel extends AndroidViewModel {
    private ReminderRepository reminderRepository;
    public LiveData<List<Reminder>> allReminders;

    public ReminderViewModel(Application application) {
        super(application);
        reminderRepository = new ReminderRepository(application);
        allReminders = reminderRepository.getAllReminders();
    }

    public LiveData<List<Reminder>> getAllReminders() {
        return allReminders;
    }

    public void insert(Reminder reminder) {
        reminderRepository.insert(reminder);
    }

    public void update(Reminder reminder) {
        reminderRepository.update(reminder);
    }

    public void delete(Reminder reminder) {
        reminderRepository.delete(reminder);
    }

    public void deleteAll() {
        reminderRepository.deleteAll();
    }
}
