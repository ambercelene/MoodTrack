package com.example.moodtrack.dal;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;

public class DatabaseInitializer {

    public static void populateAsync(final AppDatabase db) {

        DatabaseInitializer.PopulateDbAsync task = new DatabaseInitializer.PopulateDbAsync(db);
        task.execute();
    }

    public static void populateSync(@NonNull final AppDatabase db) {
        populateWithTestData(db);
    }

    private static Affect addAffect(final AppDatabase db, final String type, final String name, final Date date) {
        Affect moodData = new Affect();
        moodData.type = type;
        moodData.name = name;
        moodData.date = date;
        db.affectModel().insert(moodData);
        return moodData;
    }

    private static void populateWithTestData(AppDatabase db) {
        db.affectModel().deleteAll();

        try {
            Date today = getTodayPlusDays(0);
            Date yesterday = getTodayPlusDays(-1);
            Date twoDaysAgo = getTodayPlusDays(-2);
            Date lastWeek = getTodayPlusDays(-7);
            Date twoWeeksAgo = getTodayPlusDays(-14);

            Affect affect1 = addAffect(db,"emotion","angry", today);
            Affect affect2 = addAffect(db,"feeling","distracted", yesterday);
            Affect affect3 = addAffect(db,"experience","homework", twoDaysAgo);
            Affect affect4 = addAffect(db,"feeling","confused", lastWeek);
            Affect affect5 = addAffect(db,"emotion","excited", twoWeeksAgo);

            Log.d("DB", "Added mood data.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Date getTodayPlusDays(int daysAgo) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, daysAgo);
        return calendar.getTime();
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final AppDatabase mDb;

        PopulateDbAsync(AppDatabase db) {
            mDb = db;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            populateWithTestData(mDb);
            return null;
        }

    }
}
