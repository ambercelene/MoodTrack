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

    public long getId() {
        return id;
    }

    public String getKey() {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        return formater.format(date);
    }

    public String getDate() {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss", Locale.US);
        return formater.format(date);
    }

    public String toString() {
        return getDate();
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public int getIntensity() {
        return intensity;
    }

    public long getDuration() {
        return duration;
    }

    private void setDateParts() {
        String[] dateparts = getKey().split("-");
//        year = Integer.parseInt(dateparts[0]);
//        month = Integer.parseInt(dateparts[1]);
//        day = Integer.parseInt(dateparts[2]);
    }
}
