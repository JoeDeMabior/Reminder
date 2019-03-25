package com.joey.reminder.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import es.dmoral.toasty.Toasty;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.joey.reminder.R;
import com.joey.reminder.databases.Reminder;
import com.joey.reminder.viewmodels.ReminderViewModel;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

public class AddReminderActivity extends AppCompatActivity implements
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private EditText titleText;
    private TextView dateText, timeText, repeatText, repeatNoText, repeatTypeText;
    private FloatingActionButton notificationOff, notificationOn;
    private Calendar calendar;
    private int year, month, hour, minute, day;
    private long repeatTime;
    private String title, time, date, repeat, repeatNo, repeatType, active;

    // Values for orientation change
    private static final String KEY_TITLE = "title_key";
    private static final String KEY_TIME = "time_key";
    private static final String KEY_DATE = "date_key";
    private static final String KEY_REPEAT = "repeat_key";
    private static final String KEY_REPEAT_NO = "repeat_no_key";
    private static final String KEY_REPEAT_TYPE = "repeat_type_key";
    private static final String KEY_ACTIVE = "active_key";

    // Constant values in milliseconds
    private static final long milliMinute = 60000L;
    private static final long milliHour = 3600000L;
    private static final long milliDay = 86400000L;
    private static final long milliWeek = 604800000L;
    private static final long milliMonth = 2592000000L;

    StringBuilder stringBuilder = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.add_reminder);

        titleText = findViewById(R.id.heading);
        dateText = findViewById(R.id.set_date);
        timeText = findViewById(R.id.set_time);
        repeatText = findViewById(R.id.set_repeat);
        repeatNoText = findViewById(R.id.set_repeat_no);
        repeatTypeText = findViewById(R.id.set_repeat_type);
        notificationOff = findViewById(R.id.notification_off);
        notificationOn = findViewById(R.id.notification_on);

        active = "true";
        repeat = "true";
        repeatNo = Integer.toString(1);
        repeatType = "Hour";

        calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DATE);

        date = day + "/" + month + "/" + year;
        time = hour + ":" + minute;

        setupTitleText();

        dateText.setText(date);
        timeText.setText(time);
        repeatNoText.setText(repeatNo);
        repeatTypeText.setText(repeatType);
        repeatText.setText(stringBuilder.append("Every").append(" ").append(repeatNo).append(" ").append(repeatType).append("(s)"));

        // Save state on device rotation
        if (savedInstanceState != null) {
            String savedTitle = savedInstanceState.getString(KEY_TITLE);
            titleText.setText(savedTitle);
            title = savedTitle;

            String savedTime = savedInstanceState.getString(KEY_TIME);
            timeText.setText(savedTime);
            time = savedTime;

            String savedDate = savedInstanceState.getString(KEY_DATE);
            dateText.setText(savedDate);
            date = savedDate;

            String saveRepeat = savedInstanceState.getString(KEY_REPEAT);
            repeatText.setText(saveRepeat);
            repeat = saveRepeat;

            String savedRepeatNo = savedInstanceState.getString(KEY_REPEAT_NO);
            repeatNoText.setText(savedRepeatNo);
            repeatNo = savedRepeatNo;

            String savedRepeatType = savedInstanceState.getString(KEY_REPEAT_TYPE);
            repeatTypeText.setText(savedRepeatType);
            repeatType = savedRepeatType;

            active = savedInstanceState.getString(KEY_ACTIVE);
        }

        setupNotificationState();
    }

    // Save state on device rotation


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putCharSequence(KEY_TITLE, titleText.getText());
        outState.putCharSequence(KEY_TIME, timeText.getText());
        outState.putCharSequence(KEY_DATE, dateText.getText());
        outState.putCharSequence(KEY_REPEAT, repeatText.getText());
        outState.putCharSequence(KEY_REPEAT_NO, repeatNoText.getText());
        outState.putCharSequence(KEY_REPEAT_TYPE, repeatTypeText.getText());
        outState.putCharSequence(KEY_ACTIVE, active);
    }

    private void setupTitleText() {
        titleText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                title = charSequence.toString().trim();
                titleText.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void setupNotificationState() {
        if (active.equals("false")) {
            notificationOff.setVisibility(View.VISIBLE);
            notificationOn.setVisibility(View.GONE);
        } else if (active.equals("true")) {
            notificationOff.setVisibility(View.GONE);
            notificationOn.setVisibility(View.VISIBLE);
        }
    }

    public void setDate(View view) {
        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
                this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show(getFragmentManager(), "DatePickerDialog");
    }

    public void setTime(View view) {
        TimePickerDialog timePickerDialog = TimePickerDialog.newInstance(
                this,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
        );
        timePickerDialog.show(getFragmentManager(), "TimePickerDialog");
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        monthOfYear++;
        day = dayOfMonth;
        month = monthOfYear;
        this.year = year;
        date = dayOfMonth + "/" + monthOfYear + "/" + year;
        dateText.setText(date);
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
        hour = hourOfDay;
        this.minute = minute;

        if (minute < 10) {
            time = hourOfDay + ":" + "0" + minute;
        } else {
            time = hourOfDay + ":" + minute;
        }

        timeText.setText(time);
    }

    public void onSwitchRepeat(View view) {
        boolean on = ((Switch) view).isChecked();
        if (on) {
            repeat = "true";
            repeatText.setText(stringBuilder.append("Every").append(" ").append(repeatNo).append(" ").append(repeatType).append("(s)"));
        } else {
            repeat = "false";
            repeatText.setText(R.string.off);
        }
    }

    // On clicking repeat interval button
    public void setRepeatNo(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Enter number");

        // Create an EditText to input repeat number
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        alert.setView(input);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (input.getText().toString().length() == 0) {
                    repeatNo = Integer.toString(1);
                    repeatNoText.setText(repeatNo);
                    repeatText.setText(stringBuilder.append("Every").append(" ").append(repeatNo).append(" ").append(repeatType)
                            .append("(s)"));
                } else {
                    repeatNo = input.getText().toString().trim();
                    repeatNoText.setText(repeatNo);
                    repeatText.setText(stringBuilder.append("Every").append(" ").append(repeatNo).append(" ").append(repeatType)
                            .append("(s)"));
                }
            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alert.show();
    }

    // On clicking repeat type button
    public void selectRepeatType(View view) {
        final String[] items = new String[5];
        items[0] = "Minute";
        items[1] = "Hour";
        items[2] = "Day";
        items[3] = "Week";
        items[4] = "Month";

        // Create a list dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Type");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                repeatType = items[i];
                repeatTypeText.setText(repeatType);
                repeatText.setText(stringBuilder.append("Every").append(" ").append(repeatNo).append(" ").append(repeatType)
                        .append("(s)"));
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void setNotificationOff(View view) {
        active = "true";
        setupNotificationState();
    }

    public void setNotificationOn(View view) {
        active = "false";
        setupNotificationState();
    }

    public void saveReminder() {
        Reminder reminder = new Reminder(title, date, time, repeat, repeatNo, repeatType, active);
        ReminderViewModel reminderViewModel = ViewModelProviders.of(this).get(ReminderViewModel.class);
        reminderViewModel.insert(reminder);

        Toasty.success(this, "Saved", Toast.LENGTH_SHORT).show();

        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_alter_reminder, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                titleText.setText(title);
                if (titleText.getText().toString().length() == 0) {
                    titleText.setError("Title required.");
                } else {
                    saveReminder();
                }
                return true;
            case R.id.discard:
                Toasty.info(this, "Discarded.", Toast.LENGTH_SHORT).show();
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
