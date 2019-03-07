package com.example.moodtrack.domain;

/**
 * A daily entry "data node".
 */
public class DailyMoodEntry {

    protected int year;

    protected int month;

    protected int day;

    protected String date; // hashkey; format "yyyy-MM-dd"

    protected Affect moodData; // the entry

    protected DailyMoodEntry next;

    public DailyMoodEntry(Affect moodData) {
        this.moodData = moodData;
        setDate(moodData);
    }

    private void setDate(Affect moodData) {
        this.date = moodData.getDate().split("/")[0];
        String[] dateparts = date.split("-");
        year = Integer.parseInt(dateparts[0]);
        month = Integer.parseInt(dateparts[1]);
        day = Integer.parseInt(dateparts[2]);
    }

}
