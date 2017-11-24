package com.wymsii.whenmeds.reminder;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by reuben on 11/19/17.
 */


@Dao
public interface ReminderDao {
    @Query("SELECT * from reminders")
    List<Reminder> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Reminder> reminders);

    @Delete
    void delete(Reminder reminder);
}
