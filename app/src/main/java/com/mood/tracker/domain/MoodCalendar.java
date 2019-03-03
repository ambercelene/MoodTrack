package com.example.moodtrack.domain;

import java.util.HashMap;

public class MoodCalendar {

    /**
     * String date of entry. Format, "yyyy-MM-dd"
     */
    HashMap<String, Entry> map;

    public MoodCalendar() {
        map = new HashMap<>();
    }

    public void addEntry(Affect affect) {
        Entry entry = new Entry(affect);
        map.put(entry.date, entry);
    }

    public Entry getEntry(String date) {
        return map.get(date);
    }
}



