package com.example.moodtrack.domain;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

import static org.junit.Assert.*;

public class DailyMoodListTest {

    public String[] moods = {"Neutral", "Happy", "Excited", "Tender", "Sad", "Angry", "Annoyed"};

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
    public void testCanAddAndGetAffect() {
        Affect moodData = new Feeling("Bummed");
        list.add(moodData);

//        try {
//            Thread.sleep(1000);
//        } catch (Exception e) {}

        Affect moodData2 = new Feeling("Sad");
        list.add(moodData2);

        assertEquals(moodData, list.get(moodData.getKey()));
        assertEquals("Bummed", list.get(moodData.getKey()).getDescription());

        assertNotEquals(moodData.getKey(), moodData2.getKey());

        assertEquals(moodData2, list.get(moodData2.getKey()));
        assertEquals("Sad", list.get(moodData2.getKey()).getDescription());
    }

    @Test
    public void testGetSize() {
        int i = 0;
        for (int j = 0; j < 20; j++) {
            Affect moodData = new Feeling(moods[getRandom(7)]);
            list.add(moodData);
        }
        assertEquals(20, list.getSize());
    }

    @Test
    public void testInitCounts() {
        loadMoodData();
        HashMap<String, Integer> counts = list.initCounts();

        assertEquals(7, counts.size());
        for (int i = 0; i < moods.length; i++) {
            assertEquals(0, counts.get(moods[i]).intValue());
        }
    }

    @Test
    public void testCanSetCount() {
        loadMoodData();
        HashMap<String, Integer> counts = list.initCounts();

        for (int i = 0; i < moods.length; i++) {
            assertEquals(0, counts.get(moods[i]).intValue());
        }
    }

//    @Ignore
    @Test
    public void testDailyMoodCounts() {
        loadMoodData();
        HashMap<String, Integer> counts = list.getDailyMoodCounts();

        int actual = list.getDailyMoodCounts().get("Neutral");
        assertEquals(7, actual);

        assertEquals(7, counts.get(moods[0]).intValue());
        assertEquals(4, counts.get(moods[1]).intValue());
        assertEquals(0, counts.get(moods[2]).intValue());
        assertEquals(0, counts.get(moods[3]).intValue());
        assertEquals(0, counts.get(moods[4]).intValue());
        assertEquals(0, counts.get(moods[5]).intValue());
        assertEquals(6, counts.get(moods[6]).intValue());
    }

    @Test
    public void testOverallMood() {
        loadMoodData();
        assertEquals("Neutral", list.getOverallDailyMood());
//        assertNotNull("Annoyed", list.getOverallDailyMood());

//        list.add(new Feeling("Annoyed"));
//        assertEquals("Neutral", list.getOverallDailyMood());
//        assertNotNull("Annoyed", list.getOverallDailyMood());

    }

    private void loadMoodData() {
        list.add(new Feeling("Happy"));
        list.add(new Feeling("Happy"));
        list.add(new Feeling("Happy"));
        list.add(new Feeling("Happy"));

        list.add(new Feeling("Neutral"));
        list.add(new Feeling("Neutral"));
        list.add(new Feeling("Neutral"));
        list.add(new Feeling("Neutral"));
        list.add(new Feeling("Neutral"));
        list.add(new Feeling("Neutral"));
        list.add(new Feeling("Neutral"));

        list.add(new Feeling("Annoyed"));
        list.add(new Feeling("Annoyed"));
        list.add(new Feeling("Annoyed"));
        list.add(new Feeling("Annoyed"));
        list.add(new Feeling("Annoyed"));
        list.add(new Feeling("Annoyed"));

        list.add(new Feeling("Anger"));
        list.add(new Feeling("Anger"));
        list.add(new Feeling("Anger"));
        list.add(new Feeling("Anger"));
    }

    private int getRandom(int upperBound) {
        Random rand = new Random();
        return rand.nextInt(upperBound);
    }
}