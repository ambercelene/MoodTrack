package com.example.moodtrack.domain;


import com.example.moodtrack.dal.DateHelper;
import com.example.moodtrack.dal.Affect;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class DailyMoodTableTest {

    DailyMoodTable table;

    @Before
    public void start() {
        table = new DailyMoodTable();
    }

    @After
    public void finish() {
        table = null;
    }

    @Test
    public void testCanCreateNewGraph() {
        assertNotNull(table);
    }

    @Test
    public void testNewGraphIsInitiallyEmpty() {
        assertEquals(0, table.getSize());
    }

    @Test
    public void testNewGraphHasInitialCapacityOf127() {
        assertEquals(127, table.getCapacity());
    }

    // not sure how to test this
    @Ignore
    @Test
    public void testHashFunction() {
        Affect moodData = new Affect("Feeling","Annoyed", DateHelper.getTodayPlusDays(0));

//        assertEquals(69, table.getKey(moodData.getKey()));

    }

    @Test
    public void testInsert() {
        Affect moodData = new Affect("Feeling","Annoyed", DateHelper.getTodayPlusDays(0));
        table.insert(moodData);
        assertTrue(table.contains(moodData));
    }

    @Test
    public void testLoadFactor() {
        for(int i = 0; i < 63; i++) { // Approx half size
            Affect moodData = new Affect("Feeling","Annoyed", DateHelper.getTodayPlusDays(0));
            table.insert(moodData);
        }
        assertEquals(63 / 127, table.getLoadFactor(), 1e-8);
    }

    @Test
    public void testLoadFactorReturnsZeroOnEmpty() {
        assertEquals(0, table.getLoadFactor(), 1e-8);
    }

    @Test
    public void testResizeCorrectlyResizes() {
        table.resize();
        assertEquals(257, table.getCapacity());
    }

    private int getRandom(int upperBound) {
        Random rand = new Random();
        return rand.nextInt(upperBound);
    }
}