package com.example.moodtrack.domain;

public class Experience extends Affect {

    public Experience(String comment) {
        super("Experience");
        setDescription(comment);
    }

    @Override
    public String toString() {
        return getDescription();
    }
}
