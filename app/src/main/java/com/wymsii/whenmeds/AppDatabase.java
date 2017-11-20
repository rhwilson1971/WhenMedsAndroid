package com.wymsii.whenmeds;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.wymsii.whenmeds.reminder.Reminder;
import com.wymsii.whenmeds.reminder.ReminderDao;

/**
 * Created by reuben on 11/19/17.
 */

@Database(entities = {Reminder.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{

    public abstract ReminderDao reminderDao();
}
