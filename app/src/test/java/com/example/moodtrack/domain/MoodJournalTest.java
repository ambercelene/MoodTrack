package com.example.moodtrack.domain;

import com.example.moodtrack.dal.Affect;
import com.example.moodtrack.dal.DateHelper;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MoodJournalTest {

    MoodJournal mcal;

    @Before
    public void start() {
        mcal = new MoodJournal();
    }

    @Test
    public void testAddEntrySavesMoodDataCorrectly() {
        Affect xp = new Affect("Experience","Max'n relax'n", DateHelper.getTodayPlusDays(0));

        mcal.addEntry(xp);

        assertTrue(mcal.getDailyEntries(xp.getKey()).contains(xp));
    }

    @Test
    public void testCanAddAndRetrieveEntry() {
        Affect affect1 = new Affect("Experience","Max'n relax'n", DateHelper.getTodayPlusDays(0));

        mcal.addEntry(affect1);

        Affect affect2 = new Affect("Feeling","Chill", DateHelper.getTodayPlusDays(0));
        mcal.addEntry(affect2);

        Affect affect3 = new Affect("Experience","Just exercised", DateHelper.getTodayPlusDays(0));
        mcal.addEntry(affect3);

//        assertNotNull(mcal.getEntry(affect1.getKey()));
//        assertEquals(affect1, mcal.getEntry(affect1.getKey()));
//        assertEquals(affect2, mcal.getEntry(affect2.getKey()));
//        assertEquals(affect3, mcal.getEntry(affect3.getKey()));
    }
}