package com.example.moodtrack.domain;


import com.example.moodtrack.dal.DateHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class HashedMoodMapTest {

    HashedMoodMap graph;

    @Before
    public void start() {
        graph = new HashedMoodMap();
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

    @Test
    public void testHashFunction() {
        Entry moodData = new Entry(new Feeling("Annoyed"));
//        assertEquals(45, graph.getKey(moodData.date));

    }

    @Test
    public void testInsert() {
        Entry moodData = new Entry(new Feeling("Annoyed"));
        graph.insert(moodData);
        assertTrue(graph.contains(moodData.date));
    }

    @Test
    public void testResizeCorrectlyResizes() {
        graph.resize();
        assertEquals(257, graph.getCapacity());
    }
}