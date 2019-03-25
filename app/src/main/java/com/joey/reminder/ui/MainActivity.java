package com.joey.reminder.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.joey.reminder.R;
import com.joey.reminder.adapters.ReminderAdapter;
import com.joey.reminder.databases.Reminder;
import com.joey.reminder.viewmodels.ReminderViewModel;

import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {
    private TextView noReminders;

    private ReminderViewModel reminderViewModel;

    private ReminderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.app_name);

        noReminders = findViewById(R.id.no_reminders);

        reminderViewModel = ViewModelProviders.of(this).get(ReminderViewModel.class);

        adapter = new ReminderAdapter(this);

        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddReminderActivity.class);
                startActivity(intent);
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

        reminderViewModel.getAllReminders().observe(this, new Observer<List<Reminder>>() {
            @Override
            public void onChanged(List<Reminder> reminders) {
                adapter.setReminders(reminders);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.deleteAll) {
            LiveData<List<Reminder>> reminders = reminderViewModel.getAllReminders();
            if (reminders == null) {
                Snackbar.make(findViewById(android.R.id.content), "There's no reminder to delete.", Snackbar.LENGTH_SHORT).show();
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Delete all reminders?");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    reminderViewModel.deleteAll();
                    Toasty.success(MainActivity.this, "All reminders deleted.", Toast.LENGTH_SHORT).show();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
