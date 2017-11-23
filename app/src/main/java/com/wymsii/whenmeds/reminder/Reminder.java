package com.wymsii.whenmeds.reminder;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.wymsii.whenmeds.script.Script;
import java.util.Date;

/**
 * Created by reuben on 11/9/17.
 */

@Entity(
        foreignKeys = {
                @ForeignKey(entity = Script.class,
                        parentColumns = "scriptId",
                        childColumns = "script_id",
                        onDelete = ForeignKey.CASCADE)},
        indices = {@Index(value = "script_id")
        })
public class Reminder {

    @PrimaryKey(autoGenerate = true)
    private int reminderId;
    @ColumnInfo(name="end_date")
    Date endDate;
    @ColumnInfo(name="start_date")
    Date startDate;
    @ColumnInfo(name="reminder_name")
    String reminderName;

    @ColumnInfo(name = "script_id")
    private int scriptId;

    public void setReminderName(String reminderName){ this.reminderName = reminderName; }
    public void setReminderId(int rid) { this.reminderId = rid;}
    public void setStartDate(Date startDate) { this.startDate = startDate; }
    public void setEndDate(Date endDate){this.endDate = endDate;}
    public void setScriptId(int scriptId){ this.scriptId = scriptId;}

    public String getReminderName() { return this.reminderName; }
    public Date getEndDate(){ return this.endDate;}
    public Date getStartDate() { return this.startDate; }
    public int getReminderId() { return this.reminderId; }
    public int getScriptId() { return this.scriptId; }
}
