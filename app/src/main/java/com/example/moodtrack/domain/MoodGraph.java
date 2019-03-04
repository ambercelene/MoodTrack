package com.example.moodtrack.domain;


public class MoodGraph {

    private final int[] PRIME_GRAPH_SIZE = {
            127, 257, 503, 1013, 2027, 3001, 4003, 5011, 7507, 10007, 20011
    };

    private int primeGraphSizeIndex;

    // Adjacency list
    private int[] graph;

    private int graphSize;

    public MoodGraph() {
        graphSize = primeGraphSizeIndex = 0;
        graph = new int[PRIME_GRAPH_SIZE[primeGraphSizeIndex]];
    }

    public int getSize() {
        return graphSize;
    }

    public int getCapacity() {
        return graph.length;
    }
}
