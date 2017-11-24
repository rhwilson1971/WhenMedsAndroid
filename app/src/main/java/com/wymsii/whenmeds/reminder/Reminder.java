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

@Entity(tableName = "reminders",
        foreignKeys = {
                @ForeignKey(entity = Script.class,
                        parentColumns = "id",
                        childColumns = "scriptId",
                        onDelete = ForeignKey.CASCADE)},
        indices = {@Index(value = "scriptId")
        })
public class Reminder {

    @PrimaryKey(autoGenerate = true)
    private int id;
    Date endDate;
    Date startDate;
    String reminderName;
    private int scriptId;

    public void setReminderName(String reminderName){ this.reminderName = reminderName; }
    public void setId(int rid) { this.id = rid;}
    public void setStartDate(Date startDate) { this.startDate = startDate; }
    public void setEndDate(Date endDate){this.endDate = endDate;}
    public void setScriptId(int scriptId){ this.scriptId = scriptId;}

    public String getReminderName() { return this.reminderName; }
    public Date getEndDate(){ return this.endDate;}
    public Date getStartDate() { return this.startDate; }
    public int getId() { return this.id; }
    public int getScriptId() { return this.scriptId; }
}
