package com.example.moodtrack.domain;

public class Feeling extends Affect {

    public Feeling(String type) {
        super(type); // set name and date
    }

    @Override
    public String toString() {
        return super.getType();
    }
}
