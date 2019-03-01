package com.example.moodtrack.domain;

import java.util.HashMap;

public class MoodCalendar {

    /**
     * The calendar, simply mapping a unique date to it's day.
     * Create a date like: year + "-" + MONTH + "-" + DAY
     */
    HashMap<String, Day> calendar;

    public MoodCalendar() {
        calendar = new HashMap<>();
    }
}

