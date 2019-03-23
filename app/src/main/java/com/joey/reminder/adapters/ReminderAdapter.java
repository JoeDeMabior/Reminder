package com.joey.reminder.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.joey.reminder.R;
import com.joey.reminder.databases.Reminder;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ReminderViewHolder> {
    private LayoutInflater inflater;
    private List<Reminder> reminders = new ArrayList<>();

    public ReminderAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ReminderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.items, parent, false);
        return new ReminderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReminderViewHolder holder, int position) {
        Reminder reminder = reminders.get(position);
    }

    @Override
    public int getItemCount() {
        return reminders.size();
    }

    public void showAllReminders(List<Reminder> reminders) {
        this.reminders = reminders;
        notifyDataSetChanged();
    }

    class ReminderViewHolder extends RecyclerView.ViewHolder {
        private TextView titleText, dateTimeText, repeatInfoText;
        private ImageView thumbnailImage, activeImage;

        ReminderViewHolder(@NonNull View itemView) {
            super(itemView);

            thumbnailImage = itemView.findViewById(R.id.thumbnail_image);
            titleText = itemView.findViewById(R.id.title);
            dateTimeText = itemView.findViewById(R.id.date_time);
            repeatInfoText = itemView.findViewById(R.id.repeat_info);
            activeImage = itemView.findViewById(R.id.active_image);
        }
    }
}
