package com.example.moodtrack.domain;

import java.util.HashMap;

public class MoodCalendar {

    /**
     * String date of entry. Format, "yyyy-MM-dd"
     */
    HashMap<String, Day> calendar;

    public MoodCalendar() {
        calendar = new HashMap<>();
    }

    public void addEntry(Affect moodData) {
        //
    }
}

