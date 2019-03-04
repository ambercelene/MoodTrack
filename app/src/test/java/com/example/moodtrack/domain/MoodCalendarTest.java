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

        assertEquals(xp, mcal.getEntry(xp.getDate()).moodData);
    }

    @Test
    public void testGetEntry() {
        Experience affect1 = new Experience("Max'n relax'n");
        Feeling affect2 = new Feeling("Chill");
        Experience affect3 = new Experience("Just exercised.");

        mcal.addEntry(affect1);
        mcal.addEntry(affect2);
        mcal.addEntry(affect3);

        assertEquals(affect3, mcal.getEntry(affect1.getDate()).moodData);
    }
}