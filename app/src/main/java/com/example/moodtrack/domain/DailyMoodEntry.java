package com.example.moodtrack.domain;

/**
 * A daily entry "data node".
 */
public class DailyMoodEntry {

    protected int year;

    protected int month;

    protected int day;

    protected String key; // hashkey; format "yyyy-MM-dd"

    protected Affect moodData; // the entry

    protected DailyMoodEntry next;

    public DailyMoodEntry(Affect moodData) {
        this.moodData = moodData;
        setKey(moodData);
    }

    private void setKey(Affect moodData) {
        String date = moodData.getDate();
        String[] dateparts = date.split("/")[0].split("-");
        year = Integer.parseInt(dateparts[0]);
        month = Integer.parseInt(dateparts[1]);
        day = Integer.parseInt(dateparts[2]);
        this.key = date;
    }
}
