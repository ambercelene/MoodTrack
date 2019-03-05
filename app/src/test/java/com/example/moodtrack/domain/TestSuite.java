package com.example.moodtrack.domain;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        EntryTest.class,
        HashedMoodMapTest.class,
        MoodCalendarTest.class})
public class TestSuite {}
