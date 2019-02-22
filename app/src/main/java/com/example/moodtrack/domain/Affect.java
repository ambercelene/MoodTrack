package com.example.moodtrack.domain;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public abstract class Affect {

    private String date;
    private String name;
    private String experience;
    private int duration;
    private int intensity;

    public Affect(String name) {
        this.name = name;
        setDate();
    }

    private void setDate() {
        Calendar rightNow = Calendar.getInstance();
        SimpleDateFormat ymdformat = new SimpleDateFormat("yyyy / MM / dd ");
        date = ymdformat.format(rightNow.getTime());
    }

    public String getDate() {
        return date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setExperience(String comment) {
        experience = comment;
    }

    public String getExperience() {
        return experience;
    }

    public void setDuration(int count) {
        duration = count;
    }

    public int getDuration() {
        return duration;
    }

    public void setIntensity(int level) {
        intensity = level;
    }

    public int getIntensity() {
        return intensity;
    }
}
