package com.example.moodtrack.domain;

import java.util.LinkedList;
import java.util.List;

import com.example.moodtrack.dal.Affect;

public class MoodJournal {

    /**
     * String key of entry. Format, "yyyy-MM-dd"
     */
    DailyMoodTable journal;

    public MoodJournal() {
        journal = new DailyMoodTable();
    }

    public void addEntry(Affect affect) {
        journal.insert(affect);
    }

    public LinkedList<Affect> getDailyEntries(String key) {
        return journal.getDailyEntries(key).getList();
    }

    public void load(List<Affect> affectsList) {
        for (Affect affect : affectsList) {
            journal.insert(affect);
        }
    }
}



