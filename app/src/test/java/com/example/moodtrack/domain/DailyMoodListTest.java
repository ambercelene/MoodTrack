package com.example.moodtrack.domain;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

import com.example.moodtrack.dal.Affect;
import com.example.moodtrack.dal.DateHelper;

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
        Affect moodData = new Affect("Feeling","Happy", DateHelper.getTodayPlusDays(0));
        list.add(moodData);

        assertEquals(1, list.getSize());
    }

    @Test
    public void testCanAddAndGetAffect() {
        Affect moodData = new Affect("Feeling","Bummed", DateHelper.getTodayPlusDays(-1));
        list.add(moodData);

//        try {
//            Thread.sleep(1000);
//        } catch (Exception e) {}

        Affect moodData2 = new Affect("Feeling","Sad", DateHelper.getTodayPlusDays(0));
        list.add(moodData2);

        assertEquals(moodData, list.get(moodData.getDate()));
        assertEquals("Bummed", list.get(moodData.getDate()).getDescription());

        assertNotEquals(moodData.getDate(), moodData2.getDate());

        assertEquals(moodData2, list.get(moodData2.getDate()));
        assertEquals("Sad", list.get(moodData2.getDate()).getDescription());
    }

    @Test
    public void testGetSize() {
        int i = 0;
        for (int j = 0; j < 20; j++) {
            Affect moodData = new Affect("Feeling", moods[getRandom(7)], DateHelper.getTodayPlusDays(0));
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
        assertNotNull("Annoyed", list.getOverallDailyMood());

        list.add(new Affect("Feeling","Annoyed", DateHelper.getTodayPlusDays(0)));
        list.add(new Affect("Feeling","Annoyed", DateHelper.getTodayPlusDays(0)));
        assertEquals("Annoyed", list.getOverallDailyMood());
        assertNotNull("Neutral", list.getOverallDailyMood());

    }

    private void loadMoodData() {
        list.add(new Affect("Feeling","Happy", DateHelper.getTodayPlusDays(0)));
        list.add(new Affect("Feeling","Happy", DateHelper.getTodayPlusDays(0)));
        list.add(new Affect("Feeling","Happy", DateHelper.getTodayPlusDays(1)));
        list.add(new Affect("Feeling","Happy", DateHelper.getTodayPlusDays(2)));

        list.add(new Affect("Feeling","Neutral", DateHelper.getTodayPlusDays(0)));
        list.add(new Affect("Feeling","Neutral", DateHelper.getTodayPlusDays(0)));
        list.add(new Affect("Feeling","Neutral", DateHelper.getTodayPlusDays(1)));
        list.add(new Affect("Feeling","Neutral", DateHelper.getTodayPlusDays(1)));
        list.add(new Affect("Feeling","Neutral", DateHelper.getTodayPlusDays(2)));
        list.add(new Affect("Feeling","Neutral", DateHelper.getTodayPlusDays(2)));
        list.add(new Affect("Feeling","Neutral", DateHelper.getTodayPlusDays(3)));

        list.add(new Affect("Feeling","Annoyed", DateHelper.getTodayPlusDays(3)));
        list.add(new Affect("Feeling","Annoyed", DateHelper.getTodayPlusDays(3)));
        list.add(new Affect("Feeling","Annoyed", DateHelper.getTodayPlusDays(3)));
        list.add(new Affect("Feeling","Annoyed", DateHelper.getTodayPlusDays(3)));
        list.add(new Affect("Feeling","Annoyed", DateHelper.getTodayPlusDays(3)));
        list.add(new Affect("Feeling","Annoyed", DateHelper.getTodayPlusDays(3)));

        list.add(new Affect("Feeling","Anger", DateHelper.getTodayPlusDays(0)));
        list.add(new Affect("Feeling","Anger", DateHelper.getTodayPlusDays(2)));
        list.add(new Affect("Feeling","Anger", DateHelper.getTodayPlusDays(1)));
        list.add(new Affect("Feeling","Anger", DateHelper.getTodayPlusDays(3)));
    }

    private int getRandom(int upperBound) {
        Random rand = new Random();
        return rand.nextInt(upperBound);
    }
}