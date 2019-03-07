package com.example.moodtrack.domain;


import java.util.HashMap;
import java.util.LinkedList;

public class DailyMoodList {

    String[] moods = {"Neutral", "Happy", "Excited", "Tender", "Sad", "Angry", "Annoyed"};

    LinkedList<DailyMoodEntry> list;

    public DailyMoodList() {
        list = new LinkedList<>();
    }

    public void add(Affect affect) {
        if (affect == null) return;
        DailyMoodEntry entry = new DailyMoodEntry(affect);
        list.add(entry);
    }

    public Affect get(String date) {
        for (DailyMoodEntry current : list) {
            if (date.equals(current.key)) {
                return current.moodData;
            }
        }
        return null;
    }

    public int getSize() {
        return list.size();
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
        for (DailyMoodEntry current : list) {
            String entryType = current.moodData.getType();
            counts.put(entryType, counts.get(entryType) + 1);
        }
        return counts;
    }

    /**
     *
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
