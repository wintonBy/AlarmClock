package com.winton.alarmclock.bean;

import android.app.AlarmManager;

/**
 * Created by winton on 2016/12/8.
 */

public class Clock {
    private AlarmManager.AlarmClockInfo clockInfo;

    public AlarmManager.AlarmClockInfo getClockInfo() {
        return clockInfo;
    }

    public void setClockInfo(AlarmManager.AlarmClockInfo clockInfo) {
        this.clockInfo = clockInfo;
    }
}
