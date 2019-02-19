package com.example.moodtrack.dal;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class MoodViewModel extends AndroidViewModel {

    private LiveData<String> mAffectsResult;

    private AppDatabase mDb;

    public MoodViewModel(Application application) {
        super(application);
    }

    public LiveData<String> getAffectsResult() {
        return mAffectsResult;
    }

    public void createDb() {
        mDb = AppDatabase.getInMemoryDatabase(getApplication());
//        mDb = AppDatabase.getDatabase(getApplication());

        if(mDb.affectModel().getCount() == 0) {
            // Populate it with initial data
            DbHelper.populateAsync(mDb);
        }

        // Receive changes
        subscribeToDbChanges();
    }

    private void subscribeToDbChanges() {
        LiveData<List<Affect>> affectsList = mDb.affectModel().getMoodDataList();

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

    private Date getYesterdayDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }
}
