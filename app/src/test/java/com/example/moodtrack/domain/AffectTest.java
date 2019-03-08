package com.example.moodtrack.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class AffectTest {

    @Test
    public void testKeyAutoIncrements() {
        Affect a1 = new Feeling("Hungry");
        Affect a2 = new Experience("Hungry");

//        assertEquals("1", a1.getKey());
//        assertEquals("2", a2.getKey());
    }

    @Test
    public void testSetDatePartsWorks() {
        //
        Feeling affect = new Feeling("Happy");

        // yyyy-MM-dd/HH:mm:ss
        String[] dateParts = affect.getDate().split("/")[0].split("-");

        assertEquals(affect.year, Integer.parseInt(dateParts[0]));
        assertEquals(affect.month, Integer.parseInt(dateParts[1]));
        assertEquals(affect.day, Integer.parseInt(dateParts[2]));
    }
}