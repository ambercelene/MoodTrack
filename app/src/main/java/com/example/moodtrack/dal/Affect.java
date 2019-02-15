package com.example.moodtrack.dal;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.util.Date;

@Entity
@TypeConverters(DateConverter.class)
public class Affect {

    @PrimaryKey(autoGenerate = true)
//    @PrimaryKey
    @NonNull
    public long id;

    @NonNull
    public String name; // happy, excited, boating

    @NonNull
    public String type; // feeling, emotion, experience

    public Date date;

    public String description;

    public int intensity; // how strongly you are feeling it

    public int duration; // length in mood
}
