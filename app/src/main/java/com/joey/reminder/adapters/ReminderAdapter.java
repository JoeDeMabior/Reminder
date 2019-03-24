package com.joey.reminder.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.joey.reminder.R;
import com.joey.reminder.models.ReminderItem;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ReminderViewHolder> {
    private LayoutInflater inflater;
    private ArrayList<ReminderItem> items = new ArrayList<>();

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
        ReminderItem item = items.get(position);
        holder.setReminderTitle(item.title);
        holder.setReminderDateTime(item.dateTime);
        holder.setReminderRepeatInfo(item.repeat, item.repeatNo, item.repeatType);
        holder.setActiveImage(item.active);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItemCount(ArrayList<ReminderItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void removeItemSelected(int selected) {
        if (items.isEmpty()) {
            return;
        }
        items.remove(selected);
        notifyItemRemoved(selected);
    }

    class ReminderViewHolder extends RecyclerView.ViewHolder {
        private TextView titleText, dateTimeText, repeatInfoText;
        private ImageView thumbnailImage, activeImage;
        private TextDrawable textDrawable;
        private ColorGenerator colorGenerator = ColorGenerator.DEFAULT;

        ReminderViewHolder(@NonNull View itemView) {
            super(itemView);

            thumbnailImage = itemView.findViewById(R.id.thumbnail_image);
            titleText = itemView.findViewById(R.id.title);
            dateTimeText = itemView.findViewById(R.id.date_time);
            repeatInfoText = itemView.findViewById(R.id.repeat_info);
            activeImage = itemView.findViewById(R.id.active_image);
        }

        void setReminderTitle(String title) {
            titleText.setText(title);
            String letter = "A";

            if (title != null && !title.isEmpty()) {
                letter = title.substring(0, 1);
            }

            int color = colorGenerator.getRandomColor();

            // Create a circular icon consisting of a random background color and first letter of the title
            textDrawable = TextDrawable.builder().buildRound(letter, color);
            thumbnailImage.setImageDrawable(textDrawable);
        }

        void setReminderDateTime(String dateTime) {
            dateTimeText.setText(dateTime);
        }

        void setReminderRepeatInfo(String repeat, String repeatNo, String repeatType) {
            StringBuilder builder = new StringBuilder();
            if (repeat.equals("true")) {
                repeatInfoText.setText(builder.append("Every").append(" ").append(repeatNo).append(" ").append(repeatType)
                        .append("(s)"));
            } else if (repeat.equals("false")) {
                repeatInfoText.setText(builder.append("Repeat off"));
            }
        }

        void setActiveImage(String active) {
            if (active.equals("true")) {
                activeImage.setImageResource(R.drawable.ic_notifications_active);
            } else if (active.equals("false")) {
                activeImage.setImageResource(R.drawable.ic_notifications_off);
            }
        }
    }
}
