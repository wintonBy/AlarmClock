package com.winton.alarmclock.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by winton on 2017/2/8.
 */

@Entity
public class Clock {

    @Id
    private Long id;

    private String title;
    private String hour;
    private String minute;
    private boolean repeate;

    private boolean mon;
    private boolean tues;
    private boolean wed;
    private boolean thurs;
    private boolean fri;
    private boolean sat;
    private boolean sun;

    @Generated(hash = 852308375)
    public Clock(Long id, String title, String hour, String minute, boolean repeate,
            boolean mon, boolean tues, boolean wed, boolean thurs, boolean fri,
            boolean sat, boolean sun) {
        this.id = id;
        this.title = title;
        this.hour = hour;
        this.minute = minute;
        this.repeate = repeate;
        this.mon = mon;
        this.tues = tues;
        this.wed = wed;
        this.thurs = thurs;
        this.fri = fri;
        this.sat = sat;
        this.sun = sun;
    }

    @Generated(hash = 1588708936)
    public Clock() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHour() {
        return this.hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinute() {
        return this.minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public boolean getRepeate() {
        return this.repeate;
    }

    public void setRepeate(boolean repeate) {
        this.repeate = repeate;
    }

    public boolean getMon() {
        return this.mon;
    }

    public void setMon(boolean mon) {
        this.mon = mon;
    }

    public boolean getTues() {
        return this.tues;
    }

    public void setTues(boolean tues) {
        this.tues = tues;
    }

    public boolean getWed() {
        return this.wed;
    }

    public void setWed(boolean wed) {
        this.wed = wed;
    }

    public boolean getThurs() {
        return this.thurs;
    }

    public void setThurs(boolean thurs) {
        this.thurs = thurs;
    }

    public boolean getFri() {
        return this.fri;
    }

    public void setFri(boolean fri) {
        this.fri = fri;
    }

    public boolean getSat() {
        return this.sat;
    }

    public void setSat(boolean sat) {
        this.sat = sat;
    }

    public boolean getSun() {
        return this.sun;
    }

    public void setSun(boolean sun) {
        this.sun = sun;
    }
}
