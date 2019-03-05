package com.example.moodtrack.dal;

import android.app.Application;

import java.util.List;

import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

public class MoodViewModel extends AndroidViewModel {

    /**
     * Mood database
     */
    private AppDatabase mDb;

    /**
     * Formatted string of list of stored affects
     */
    private LiveData<String> mAffectsResult;

    /**
     * ArrayList of stored affects
     */
    private LiveData<List<Affect>> affectsList;

    public MoodViewModel(Application application) {
        super(application);
//        mDb = AppDatabase.getInMemoryDatabase(getApplication());
        mDb = AppDatabase.getDatabase(getApplication());
    }

    public LiveData<List<Affect>> getAffectsList() {
        return mDb.affectModel().getMoodDataList();
    }

    public LiveData<String> getAffectsResult() {
        return mAffectsResult;
    }

    public void insert(Affect moodData) {
        mDb.affectModel().insert(moodData);
    }

    public void initDb() {
        // Receive changes
        subscribeToDbChanges();
    }

    private void subscribeToDbChanges() {
        affectsList = mDb.affectModel().getMoodDataList();

        // Instead of exposing the list of Loans, we can apply a transformation and expose Strings.
        mAffectsResult = Transformations.map(affectsList, new Function<List<Affect>, String>() {
            @Override
            public String apply(List<Affect> affects) {
                StringBuilder sb = new StringBuilder();
                for (Affect moodData : affects) {
                    sb.append(moodData);
                }
                return sb.toString();
            }
        });
    }

}
