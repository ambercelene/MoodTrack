package com.example.moodtrack.domain;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        DailyMoodListTest.class,
        DailyMoodTableTest.class,
        MoodJournalTest.class
})
public class TestSuite {}
