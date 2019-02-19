package com.example.moodtrack.dal;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.content.Context;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.Date;


@Database(entities = {Affect.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract AffectDao affectModel();

    public static AppDatabase getInMemoryDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class)
                            // Don't do this on a real app!
                            .allowMainThreadQueries()
                            .build();
       }
        return INSTANCE;
    }

    // singleton pattern; only creates one instance
    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    // Create database
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class,
                            "mood_database"
                    ).addCallback(sOnOpenDbCallback)
                     .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sOnOpenDbCallback =
            new RoomDatabase.Callback() {

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final AffectDao mDao;

        PopulateDbAsync(AppDatabase db) {
            mDao = db.affectModel();
        }

        @Override
        protected Void doInBackground(final Void... params) {

            if (mDao.getCount() == 0) {
                mDao.deleteAll();

                mDao.insert(new Affect("emotion", "angry", DbHelper.getTodayPlusDays(0)));
                mDao.insert(new Affect("feeling", "distracted", DbHelper.getTodayPlusDays(-1)));
                mDao.insert(new Affect("experience", "homework", DbHelper.getTodayPlusDays(-2)));
                mDao.insert(new Affect("feeling", "confused", DbHelper.getTodayPlusDays(-7)));
                mDao.insert(new Affect("emotion", "excited", DbHelper.getTodayPlusDays(-14)));

                Log.d("DB", "Initialized mood data.");
            }
            return null;
        }
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}