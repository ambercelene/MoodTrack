package com.example.moodtrack.dal;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;

public class DbHelper {

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

    public static void populateAsync(final AppDatabase db) {

        DbHelper.PopulateDbAsync task = new DbHelper.PopulateDbAsync(db);
        task.execute();
    }

    public static void populateSync(@NonNull final AppDatabase db) {
        populateWithTestData(db);
    }

    private static void populateWithTestData(AppDatabase db) {
        db.affectModel().deleteAll();

        try {
            Date today = getTodayPlusDays(0);
            Date yesterday = getTodayPlusDays(-1);
            Date twoDaysAgo = getTodayPlusDays(-2);
            Date lastWeek = getTodayPlusDays(-7);
            Date twoWeeksAgo = getTodayPlusDays(-14);

            db.affectModel().insert(new Affect("emotion","angry", today));
            db.affectModel().insert(new Affect("feeling","distracted", yesterday));
            db.affectModel().insert(new Affect("experience","homework", twoDaysAgo));
            db.affectModel().insert(new Affect("feeling","confused", lastWeek));
            db.affectModel().insert(new Affect("emotion","excited", twoWeeksAgo));

            Log.d("DB", "Initialized mood data.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Date getTodayPlusDays(int daysAgo) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, daysAgo);
        return calendar.getTime();
    }
}
