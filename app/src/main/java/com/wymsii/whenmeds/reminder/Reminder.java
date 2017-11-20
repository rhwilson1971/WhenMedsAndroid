package com.wymsii.whenmeds.reminder;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.wymsii.whenmeds.script.Script;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by reuben on 11/9/17.
 */


public class Reminder {

    @PrimaryKey
    private int rid;

    //@ForeignKey
    private int scriptid;

    @ColumnInfo(name="end_date")
    Date endDate;

    @ColumnInfo(name="start_date")
    Date startDate;

    @ColumnInfo(name="reminder_name")
    String reminderName;

    public void setReminderName(String reminderName){ this.reminderName = reminderName; }

    public void setRid(int rid) { this.rid = rid;}
    public void setStartDate(Date startDate) { this.startDate = startDate; }
    public void setEndDate(Date endDate){this.endDate = endDate;}

    public Date getEndDate(){ return this.endDate;}
    public Date getStartDate() { return this.startDate; }
    public int getId() { return this.rid; }
    public String getReminderName() { return this.reminderName; }

}
