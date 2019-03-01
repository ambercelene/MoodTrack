package com.example.moodtrack.domain;

/**
 * A daily entry "data node".
 */
public class Entry {

    protected int year;

    protected int month;

    protected int day;

    protected String date; // hashkey; format "yyyy-MM-dd/HH:mm:ss"

    protected Affect moodData; // the entry

    public Entry(Affect moodData) {
        this.moodData = moodData;
        setDate(moodData);
    }

    private void setDate(Affect moodData) {
        this.date = moodData.getDate();
        String[] dateparts = date.split("/")[0].split("-");
        year = Integer.parseInt(dateparts[0]);
        month = Integer.parseInt(dateparts[1]);
        day = Integer.parseInt(dateparts[1]);
    }

}
