package com.joey.reminder.viewmodel;

import com.joey.reminder.room.Task;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TaskViewModel extends ViewModel {
    private Task myTask;
    private MutableLiveData<Task> task;

    public TaskViewModel() {
        task = new MutableLiveData<>();
    }

    public MutableLiveData<Task> getTask() {
        return task;
    }

    public void setTask(MutableLiveData<Task> task) {
        task.postValue(myTask);
    }
}
