package com.example.moodtrack.domain;

public class DailyMoodList {

    DailyMoodEntry front;

    int size;

    public DailyMoodList() {
        size = 0;
    }

    public void add(Affect affect) {
        if (affect == null) return;
        DailyMoodEntry entry = new DailyMoodEntry(affect);
        if (front != null) {
            entry.next = front;
        }
        front = entry;
        size++;

    }

    public Affect get(String date) {
        for (DailyMoodEntry current = front; current != null; current = current.next) {
            if (date.equals(current.date)) {
                return current.moodData;
            }
        }
        return null;
    }

    public int getSize() {
        return size;
    }
}
