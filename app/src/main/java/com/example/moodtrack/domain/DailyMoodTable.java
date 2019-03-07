package com.example.moodtrack.domain;


public class DailyMoodTable {

    private final int[] PRIME_GRAPH_CAPACITY = {
            127, 257, 503, 1013, 2027, 3001, 4003, 5011, 7507, 10007, 20011
    };

    private int capacityIndex;

    private DailyMoodList[] graph;

    private int graphSize;

    public DailyMoodTable() {
        graphSize = capacityIndex = 0;
        graph = new DailyMoodList[PRIME_GRAPH_CAPACITY[capacityIndex]];
//        for (int i = 0; i < graph.length; i++) {
//            graph[i] = new DailyMoodList();
//        }
    }

    public double getLoadFactor() {
        if (graph.length == 0) return 0;
        return graphSize / graph.length;
    }

    private int hash(String date) {
        // naive approach
        return Math.abs(date.hashCode()) % graph.length;
    }

    public int getKey(String date) {
        return hash(date);
    }

    public void insert(Affect entry) {
//        if (1.5 < getLoadFactor()) {
//            resize();
//        }
        int key = hash(entry.getDate());
        if (graph[key] == null) {
            graph[key] = new DailyMoodList();
        }
        graph[key].add(entry);
        graphSize++;
    }

    /**
     * Returns list of given date's daily mood data.
     *
     * @param date Get mood data for given date
     * @return     If data exist, returns list for given date
     */
    public DailyMoodList getDailyEntries(String date) {
        if (date == null) throw new IllegalArgumentException("Date can not be null.");
        return graph[hash(date)];
    }

    public boolean contains(String key) {
        return graph[hash(key)] != null;
    }

    public boolean isEmpty() {
        return graphSize == 0;
    }

    public int getSize() {
        return graphSize;
    }

    public int getCapacity() {
        return graph.length;
    }

    public void resize() {
        DailyMoodList[] tempGraph = graph;
        graph = new DailyMoodList[PRIME_GRAPH_CAPACITY[++capacityIndex]];
//        graphSize = 0;
//        for (int j = 0; j < tempGraph.length; j++) {
//            for (DailyMoodEntry affect : tempGraph[j]) {
//                insert(affect);
//            }
//        }
    }
}
