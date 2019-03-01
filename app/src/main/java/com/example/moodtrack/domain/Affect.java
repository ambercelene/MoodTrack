package com.example.moodtrack.domain;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public abstract class Affect {

    private String date;
    private String type;
    private String description;
    private int duration;
    private int intensity;

    public Affect(String type) {
        setType(type);
        setDate();
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    private void setDate() {
        Calendar rightNow = Calendar.getInstance();
        SimpleDateFormat ymdformat = new SimpleDateFormat("yyyy-MM-dd");
        date = ymdformat.format(rightNow.getTime());
    }

    public String getDate() {
        return date;
    }

    public void setDescription(String comment) {
        description = comment;
    }

    public String getDescription() {
        return description;
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
