package com.example.moodtrack.domain;


import com.example.moodtrack.dal.Affect;

public class DailyMoodTable {

    private final int[] PRIME_TABLE_CAPACITY = {
            127, 257, 503, 1013, 2027, 3001, 4003, 5011, 7507, 10007, 20011
    };

    private int capacityIndex;

    private DailyMoodList[] table;

    private int tableSize;

    public DailyMoodTable() {
        tableSize = capacityIndex = 0;
        table = new DailyMoodList[PRIME_TABLE_CAPACITY[capacityIndex]];
//        for (int i = 0; i < table.length; i++) {
//            table[i] = new DailyMoodList();
//        }
    }

    public double getLoadFactor() {
        if (table.length == 0) return 0;
        return tableSize / table.length;
    }

    private int hash(String value) {
        // naive approach
        return Math.abs(value.hashCode()) % table.length;
    }

    /**
     * Combines date/time and the table length to create a unique key. (avoids collisions under 1s)
     *
     * @param value
     * @param mod
     * @return
     */
    private int hash(String value, int mod) {
        int hashCode = (value + String.valueOf(mod)).hashCode();
        return Math.abs(hashCode) % table.length;
    }

    public int getKey(String date) {
        return hash(date);
    }

    /**
     * Runtime: O()
     *
     * @param affect
     */
    public void insert(Affect affect) {
        if (1.5 < getLoadFactor()) {
            // resize storage if starts becoming inefficient
            resize();
        }
        int key = hash(affect.getKey());
        if (table[key] == null) {
            // lazy load a new list
            table[key] = new DailyMoodList();
        }
        table[key].add(affect);
        tableSize++;
    }

    /**
     * Returns list of given key's daily mood data.
     *
     * @param date Get mood data for given key
     * @return     If data exist, returns list for given key
     */
    public DailyMoodList getDailyEntries(String date) {
        if (date == null) throw new IllegalArgumentException("Date can not be null.");
        return table[hash(date)];
    }

    public boolean contains(Affect data) {
        return table[hash(data.getKey())].contains(data);
    }

    public boolean isEmpty() {
        return tableSize == 0;
    }

    public int getSize() {
        return tableSize;
    }

    public int getCapacity() {
        return table.length;
    }

    public void resize() {
        DailyMoodList[] tempGraph = table;
        table = new DailyMoodList[PRIME_TABLE_CAPACITY[++capacityIndex]];
        tableSize = 0;
        for (int j = 0; j < tempGraph.length; j++) {
            if (tempGraph[j] != null) {
                for (Affect md : tempGraph[j].getList()) {
                    insert(md);
                }
            }
        }
    }
}
