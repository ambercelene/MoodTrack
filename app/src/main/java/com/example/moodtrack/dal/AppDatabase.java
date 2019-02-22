package com.example.moodtrack.dal;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;


@Database(entities = {Affect.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private final static String DB_NAME = "mood_affects.db";
    private static AppDatabase INSTANCE;

    public abstract AffectDao affectModel();

    public static AppDatabase getInMemoryDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class)
                            // Don't allow queries on main thread in production!
                            .allowMainThreadQueries()
                            .addCallback(sOnOpenDbCallback)
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
                            DB_NAME
                    ).allowMainThreadQueries() // Don't do this in production!
                     .addCallback(sOnOpenDbCallback)
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
//            mDao.deleteAll();

            // if no affects stored in model, prime with initial data
            if (mDao.getCount() == 0) {
                mDao.insert(new Affect("emotion", "angry", DateHelper.getTodayPlusDays(0)));
                mDao.insert(new Affect("feeling", "distracted", DateHelper.getTodayPlusDays(-1)));
                mDao.insert(new Affect("experience", "homework", DateHelper.getTodayPlusDays(-2)));
                mDao.insert(new Affect("feeling", "confused", DateHelper.getTodayPlusDays(-7)));
                mDao.insert(new Affect("emotion", "excited", DateHelper.getTodayPlusDays(-14)));

                Log.d("DB", "Initialized mood data.");
            }
            return null;
        }
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}