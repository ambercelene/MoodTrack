package com.example.moodtrack.domain;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public abstract class Affect {

    private static int id;
    private String key;

    private String date;
    protected int year;
    protected int month;
    protected int day;

    private String type;
    private String description;
    private int duration;
    private int intensity;

    public Affect(String type, String description) {
        setType(type);
        setDescription(description);
        setDate();
        key = date + String.valueOf(++id);
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    private void setDate() {
        Calendar rightNow = Calendar.getInstance();
        SimpleDateFormat ymdformat = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");
        date = ymdformat.format(rightNow.getTime());
        setDateParts();
    }

    private void setDateParts() {
        String[] dateparts = date.split("/")[0].split("-");
        year = Integer.parseInt(dateparts[0]);
        month = Integer.parseInt(dateparts[1]);
        day = Integer.parseInt(dateparts[2]);
    }

    public String getDate() {
//        return key.split("/")[0];
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

    public String getKey() {
        return key;
    }
}
