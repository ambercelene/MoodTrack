package com.example.moodtrack.domain;


import java.util.HashMap;
import java.util.LinkedList;

import com.example.moodtrack.dal.Affect;

/**
 * DailyMoodList is a wrapper around Java's LinkedList that holds a day's worth of entries.
 */
public class DailyMoodList {

    // preprocess the mood type array data
    public String[] moodTypes = {"Neutral", "Happy", "Excited", "Tender", "Sad", "Angry", "Annoyed"};

    private int moodTypeSize;

    private HashMap<String, Integer> typeCounts;

    private LinkedList<Affect> list;

    public DailyMoodList() {
        list = new LinkedList<>();
        moodTypeSize = moodTypes.length;
        typeCounts = initCounts();
    }

    /**
     * Runtime: O(1)
     *
     * @param entry
     */
    public void add(Affect entry) {
        if (entry == null) return;
        list.add(entry);
        addCount(entry.getDescription());
    }

    /**
     * Runtime: O(n), where n is number entries for a given date
     *
     * @param date
     * @return
     */
    public Affect get(String date) {
        for (Affect current : list) {
            if (date.equals(current.getDate())) {
                return current;
            }
        }
        return null;
    }

    // Runtime: O(1)
    protected LinkedList<Affect> getList() {
        return list;
    }

    // Runtime: O(1)
    public int getSize() {
        return list.size();
    }

    public boolean contains(Affect entry) {
        return list.contains(entry);
    }

    /**
     *
     * Runtime: O(n), n is the number of different moods
     *
     * @return
     */
    public String getOverallDailyMood() {
        String overallMood = "";
        Integer max = 0;
        for (String mood : moodTypes) {
            if (typeCounts.get(mood).compareTo(max) > 0) {
                max = typeCounts.get(mood);
                overallMood = mood;
            }
        }
        return overallMood;
    }

    /**
     * Returns a map of number of entries of each mood type
     *
     * Runtime: O(1)
     *
     * @return
     */
    public HashMap<String, Integer> getDailyMoodCounts() {
        return typeCounts;
    }

    /**
     * Runtime: O(1)
     *
     * @param entryType
     */
    private void addCount(String entryType) {
        try {
            if (!typeCounts.containsKey(entryType)) {
                typeCounts.put(entryType, 1);
            } else {
                Integer i;
                if ((i = typeCounts.get(entryType)) != null) {
                    // had to deal with unboxing issue above
                    typeCounts.put(entryType, i + 1);
                }
            }
        } catch (NullPointerException npe) {}
    }

    /**
     * Runtime: O(n), where n is the number of different moods
     *
     * @return
     */
    protected HashMap<String, Integer> initCounts() {
        typeCounts = new HashMap<>();
        for (String mood : moodTypes) {
            typeCounts.put(mood, 0);
        }
        return typeCounts;
    }
}
