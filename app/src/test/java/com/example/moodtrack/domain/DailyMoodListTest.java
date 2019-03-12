package com.example.moodtrack.domain;

import org.junit.Before;
import org.junit.Test;

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
    public void testDailyMoodCounts() {
        loadMoodData();
        HashMap<String, Integer> counts = list.getDailyMoodCounts();

        assertEquals(6, (int) counts.get("Neutral"));
    }

    @Test
    public void testOverallMood() {
        loadMoodData();

        assertEquals("Neutral", list.getOverallDailyMood());
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