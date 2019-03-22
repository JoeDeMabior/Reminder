package com.joey.reminder.repository;

import android.app.Application;

import com.joey.reminder.room.Task;
import com.joey.reminder.room.TaskDao;
import com.joey.reminder.room.TaskDatabase;

import java.util.List;

import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;

public class TaskRepository {
    private TaskDao taskDao;
    private LiveData<List<Task>> allTasks;

    public TaskRepository(Application application) {
        TaskDatabase taskDatabase = TaskDatabase.getInstance(application);
        taskDao = taskDatabase.taskDao();
        allTasks = taskDao.getAll();
    }

    @WorkerThread
    public LiveData<List<Task>> getAllTasks() {
        return  allTasks;
    }

    @WorkerThread
    public void insert(Task task) {
        taskDao.insert(task);
    }

    @WorkerThread
    public void update(Task task) {
        taskDao.update(task);
    }

    @WorkerThread
    public void delete(Task task) {
        taskDao.delete(task);
    }

    @WorkerThread
    public void deleteAll() {
        taskDao.deleteAll();
    }
}
