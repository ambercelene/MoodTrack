package com.example.moodtrack.domain;

public class Experience extends Affect {

    public Experience(String description) {
        super("Experience", description);
    }

    @Override
    public String toString() {
        return getDescription();
    }
}
