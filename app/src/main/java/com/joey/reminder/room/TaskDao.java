package com.joey.reminder.room;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

public interface TaskDao {
    @Insert
    void insert(Task...tasks);

    @Update
    void update(Task task);

    @Delete
    void delete(Task task);

    @Query("SELECT * FROM items")
    void getAll();

    @Query("DELETE FROM items")
    void deleteAll();
}
