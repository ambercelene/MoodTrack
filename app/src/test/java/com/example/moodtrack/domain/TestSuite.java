package com.example.moodtrack.domain;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        MoodEntryTest.class,
        DailyMoodTableTest.class,
        MoodCalendarTest.class,
        DailyMoodListTest.class
})
public class TestSuite {}
