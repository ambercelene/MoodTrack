package com.example.moodtrack.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DailyMoodListTest {

    DailyMoodList list;

    @Before
    public void start() {
        list = new DailyMoodList();
    }

    @Test
    public void testCanAddAffectToNewList() {
        Affect moodData = new Feeling("Surprise");
        list.add(moodData);

        assertEquals(1, list.getSize());
    }

    @Test
    public void get() {
        Affect moodData = new Feeling("Bummed");
        list.add(moodData);

        try {
            Thread.sleep(1000);
        } catch (Exception e) {}

        Affect moodData2 = new Feeling("Sad");
        list.add(moodData2);

        assertEquals(moodData, list.get(moodData.getKey()));
        assertEquals("Bummed", list.get(moodData.getKey()).getDescription());

        assertNotEquals(moodData.getDate(), moodData2.getKey());

        assertEquals(moodData2, list.get(moodData2.getKey()));
        assertEquals("Sad", list.get(moodData2.getKey()).getDescription());
    }
}