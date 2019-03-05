package com.example.moodtrack.domain;


public class HashedMoodMap {

    private final int[] PRIME_GRAPH_SIZE = {
            127, 257, 503, 1013, 2027, 3001, 4003, 5011, 7507, 10007, 20011
    };

    private int primeGraphSizeIndex;

    private Entry[] graph;

    private int graphSize;

    public HashedMoodMap() {
        graphSize = primeGraphSizeIndex = 0;
        graph = new Entry[PRIME_GRAPH_SIZE[primeGraphSizeIndex]];
    }

    private double getLoadFactor() {
        return graphSize / graph.length;
    }

    private int hash(String date) {
        // naive approach
        return Math.abs(date.hashCode()) % PRIME_GRAPH_SIZE[primeGraphSizeIndex];
    }

    public int getKey(String date) {
        return hash(date);
    }

    //TODO: needs unit tested
    public void insert(Entry entry) {
        if (1.5 < getLoadFactor()) {
            resize();
        }
        int entryIndex = hash(entry.date);
        if(graph[entryIndex] == null) {
            graph[entryIndex] = entry;
        } else {
            entry.next = graph[entryIndex];
            graph[entryIndex] = entry;
        }
        graphSize++;
    }

    //TODO: needs unit tested
    public Entry remove(String date) {
        Entry current = graph[hash(date)];
        Entry previous = null;
        while(current.date != date) {
            if(current == null) {
                return null;
            }
            previous = current;
            current = current.next;
        }
        previous.next = current.next;
        current.next = null;
        return current;
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
        Entry[] tempGraph = graph;
        graph = new Entry[PRIME_GRAPH_SIZE[++primeGraphSizeIndex]];
//        graphSize = 0;
//        for (int j = 0; j < tempGraph.length; j++) {
//            for (Entry affect : tempGraph[j]) {
//                insert(affect);
//            }
//        }
    }
}
