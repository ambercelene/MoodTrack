package com.example.moodtrack.domain;

public class Emotion extends Affect {

    public Emotion(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return getName();
    }
}
