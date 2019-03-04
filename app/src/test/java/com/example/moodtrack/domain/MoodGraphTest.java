package com.example.moodtrack.domain;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MoodGraphTest {

    MoodGraph graph;

    @Before
    public void start() {
        graph = new MoodGraph();
    }

    @After
    public void finish() {
        graph = null;
    }

    @Test
    public void testCanCreateNewGraph() {
        assertNotNull(graph);
    }

    @Test
    public void testNewGraphIsInitiallyEmpty() {
        assertEquals(0, graph.getSize());
    }

    @Test
    public void testNewGraphHasInitialCapacityOf127() {
        assertEquals(127, graph.getCapacity());
    }
}