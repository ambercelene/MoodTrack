package com.example.moodtrack.domain;

public class Experience extends Affect {

    public Experience(String comment) {
        super("experience");
        setDescription(comment);
    }

    @Override
    public String toString() {
        return getDescription();
    }
}
