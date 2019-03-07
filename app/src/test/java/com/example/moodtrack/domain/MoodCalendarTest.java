package com.example.moodtrack.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MoodCalendarTest {

    MoodCalendar mcal;

    @Before
    public void start() {
        mcal = new MoodCalendar();
    }

    @Test
    public void testAddEntrySavesMoodDataCorrectly() {
        Experience xp = new Experience("Max'n relax'n");
        mcal.addEntry(xp);

        assertEquals(xp, mcal.getEntry(xp.getDate()));
    }

    @Test
    public void testCanAddAndRetrieveEntry() {
        Experience affect1 = new Experience("Max'n relax'n");
        mcal.addEntry(affect1);

        Feeling affect2 = new Feeling("Chill");
        mcal.addEntry(affect2);

        Experience affect3 = new Experience("Just exercised.");
        mcal.addEntry(affect3);

        assertNotNull(mcal.getEntry(affect1.getDate()));
        assertEquals(affect1, mcal.getEntry(affect1.getDate()));
//        assertEquals(affect2, mcal.getEntry(affect2.getDate()));
//        assertEquals(affect3, mcal.getEntry(affect3.getDate()));
    }
}