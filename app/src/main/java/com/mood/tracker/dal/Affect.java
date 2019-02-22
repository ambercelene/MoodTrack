package com.mood.tracker.dal;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import androidx.annotation.NonNull;

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
