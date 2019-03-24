package com.joey.reminder.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.joey.reminder.R;
import com.joey.reminder.adapters.ReminderAdapter;
import com.joey.reminder.databases.Reminder;
import com.joey.reminder.viewmodels.ReminderViewModel;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    private TextView noReminders;

    private ReminderViewModel reminderViewModel;

    private ReminderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        noReminders = findViewById(R.id.no_reminders);

        reminderViewModel = ViewModelProviders.of(this).get(ReminderViewModel.class);

        adapter = new ReminderAdapter(this);

        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddReminderActivity.class));
            }
        });

        init();
    }

    public void init() {
        RecyclerView recyclerView = findViewById(R.id.reminderList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        LiveData<List<Reminder>> reminders = reminderViewModel.getAllReminders();
        if (reminders.getValue() == null) {
            noReminders.setVisibility(View.VISIBLE);
        }

        reminderViewModel.allReminders.observe(this, new Observer<List<Reminder>>() {
            @Override
            public void onChanged(List<Reminder> reminders) {
                adapter.setReminders(reminders);
            }
        });
    }
}
