package com.example.moodtrack.domain;

public class MoodCalendar {

    /**
     * String key of entry. Format, "yyyy-MM-dd"
     */
    DailyMoodTable map;

    public MoodCalendar() {
        map = new DailyMoodTable();
    }

    public void addEntry(Affect affect) {
        map.insert(affect);
    }

    public Affect getEntry(String date) {
        return map.getDailyEntries(date).get(date);
    }

}



