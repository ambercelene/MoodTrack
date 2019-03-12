package com.example.moodtrack.domain;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public abstract class Entry {

    private static int id;
    private String key;

    private String date;
    protected int year;
    protected int month;
    protected int day;

    Affect affect;

    public Entry(Affect moodData) {
        if (moodData.getType() == null) {
            throw new IllegalArgumentException("Affect type cannot be empty");
        }
        affect = moodData;
        setDate();
        key = date + String.valueOf(++id);
    }

    public void setType(String type) {
        affect.setType(type);
    }

    public String getType() {
        return affect.getType();
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

    public void setDescription(String input) {
        affect.setDescription(input);
    }

    public String getDescription() {
        return affect.getDescription();
    }

    public void setDuration(int count) {
        affect.setDuration(count);
    }

    public int getDuration() {
        return affect.getDuration();
    }

    public void setIntensity(int level) {
        affect.setIntensity(level);
    }

    public int getIntensity() {
        return affect.getIntensity();
    }

    public String getKey() {
        return key;
    }
}
