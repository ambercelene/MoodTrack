package com.example.moodtrack.domain;


import org.junit.Test;

import static org.junit.Assert.*;

public class EntryTest {
    @Test
    public void testEntryDateIsSetCorrectlyFromAffectDate() {
        //
        Feeling affect = new Feeling("Happy");
        Entry entry1 = new Entry(affect);

        // yyyy-MM-dd/HH:mm:ss
        String[] dateParts = affect.getDate().split("/")[0].split("-");

        assertEquals(entry1.year, Integer.parseInt(dateParts[0]));
        assertEquals(entry1.month, Integer.parseInt(dateParts[1]));
        assertEquals(entry1.day, Integer.parseInt(dateParts[2]));
    }
}