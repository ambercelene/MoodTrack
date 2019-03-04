package com.example.moodtrack.domain;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

class MoodGraphTest {

    MoodGraph graph;

    @Before
    void setUp() {
        graph = new MoodGraph();
    }

    @After
    void tearDown() {
        graph = null;
    }

    @Test
    void testCanCreateNewGraph() {
        assertNotNull(graph);
    }

    @Test
    void testNewGraphIsInitiallyEmpty() {
        assertEquals(0, graph.getSize());
    }

    @Test
    void testNewGraphHasInitialCapacityOf127() {
        assertEquals(127, graph.getCapacity());
    }
}