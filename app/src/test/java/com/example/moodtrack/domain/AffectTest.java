package com.example.moodtrack.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class AffectTest {

    @Test
    public void testKeyAutoIncrements() {
        Affect a1 = new Feeling("Hungry");
        Affect a2 = new Experience("Hungry");

        assertEquals("1", a1.getKey());
        assertEquals("2", a2.getKey());
    }
}