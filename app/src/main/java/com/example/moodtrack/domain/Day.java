package com.example.moodtrack.domain;

enum Month {
    JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE,
    JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER
}

enum Weekday {
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
}

/**
 * The day "data node".
 */
class Day {

    protected int year;

    protected Month month;

    protected Weekday weekday;

    protected String date; // hashkey

    protected Affect moodData; // the entry
}
