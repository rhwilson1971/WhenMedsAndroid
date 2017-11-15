package com.wymsii.whenmeds.reminder;

import com.wymsii.whenmeds.script.Script;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by reuben on 11/9/17.
 */

public class Reminder {

    List<Date> reminderTimes = new ArrayList<>();
    Date endDate;
    Script script;

    public void setScript(Script script){this.script = script;}
    public void setReminderTime(Date reminderTime){reminderTimes.add(reminderTime);}
    public void setEndDate(Date endDate){this.endDate = endDate;}

    public Date getEndDate(){ return this.endDate;}
    public List<Date> getReminders() {return this.reminderTimes;}
    public Script getScript() { return this.script; }

}
