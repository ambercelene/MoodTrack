package com.example.moodtrack.domain;

public class Feeling extends Affect {

    public Feeling(String name) {
        super(name); // set name and date
    }

    @Override
    public String toString() {
        return getName();
    }
}
