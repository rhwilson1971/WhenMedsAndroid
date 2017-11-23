package com.wymsii.whenmeds.script;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.wymsii.whenmeds.reminder.Reminder;

import java.util.List;

/**
 * Created by reuben on 11/22/17.
 */

@Dao
public interface ScriptDao {
    @Query("SELECT * from Script")
    List<Reminder> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Script> scripts);

    @Delete
    void delete(Script script);

}
