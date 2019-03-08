package com.example.moodtrack.domain;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * DailyMoodList is a wrapper around Java's LinkedList that holds a day's worth of entries.
 */
public class DailyMoodList {

    // preprocess the mood type array data
    public String[] moods = {"Neutral", "Happy", "Excited", "Tender", "Sad", "Angry", "Annoyed"};

    private LinkedList<Affect> list;

    public DailyMoodList() {
        list = new LinkedList<>();
    }

    public void add(Affect entry) {
        if (entry == null) return;
        list.add(entry);
    }

    public Affect get(String date) {
        for (Affect current : list) {
            if (date.equals(current.getKey())) {
                return current;
            }
        }
        return null;
    }

    protected LinkedList<Affect> getList() {
        return list;
    }

    public int getSize() {
        return list.size();
    }

    public boolean contains(Affect entry) {
        return list.contains(entry);
    }

    /**
     *
     * Runtime: O(6)
     *
     * @return
     */
    public String getOverallDailyMood() {
        String overallMood = "";
        int max = 0;
        HashMap<String, Integer> counts = getDailyMoodCounts();
        for (String mood : moods) {
            if (counts.get(mood) > max) {
                overallMood = mood;
            }
        }
        return overallMood;
    }

    /**
     * Returns a map of number of entries of each mood type
     *
     * Runtime: O(n)
     *
     * @return
     */
    public HashMap<String, Integer> getDailyMoodCounts() {
        HashMap<String, Integer> counts = initCounts();
        for (Affect current : list) {
            String entryType = current.getType();
            counts.put(entryType, counts.get(entryType) + 1);
        }
        return counts;
    }

    /**
     * Runtime: O(6)
     *
     * @return
     */
    private HashMap<String, Integer> initCounts() {
        HashMap<String, Integer> counts = new HashMap<>();
        for (String mood : moods) {
            counts.put(mood, 0);
        }
        return counts;
    }
}
