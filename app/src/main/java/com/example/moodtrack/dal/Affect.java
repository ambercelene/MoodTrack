package com.example.moodtrack.dal;

import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity
@TypeConverters(DateHelper.class)
public class Affect {

    @PrimaryKey(autoGenerate = true)
//    @PrimaryKey
    @NonNull
    public long id;

    @NonNull
    public String type; // feeling, emotion, experience

    @NonNull
    public String description;

    @NonNull
    public Date date;

    public String name; // happy, excited, boating

    public int intensity; // how strongly you are feeling it

    public long duration; // length in mood

    //TODO: finish initializing Affect constructor
    public Affect(String type, String description, Date date) {
        this.type = type;
        this.description = description;
        this.date = date;
    }

    public String toString() {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss", Locale.US);
//        return String.format("Affect %d:\n  %s, %s\n  %s\n", id, type, description, formater.format(date));
        return formater.format(date);
    }

}
