package com.mood.tracker.dal;

import android.app.Application;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

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

        // Populate it with initial data
        DatabaseInitializer.populateAsync(mDb);

        // Receive changes
        subscribeToDbChanges();
    }

    private void subscribeToDbChanges() {
        LiveData<List<Affect>> affectsList = mDb.affectModel().findAllAffects();

        // Instead of exposing the list of Loans, we can apply a transformation and expose Strings.
        mAffectsResult = Transformations.map(affectsList, new Function<List<Affect>, String>() {
            @Override
            public String apply(List<Affect> affects) {
                StringBuilder sb = new StringBuilder();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm",
                        Locale.US);

                for (Affect moodData : affects) {
                    sb.append(String.format("Affect %d:\n  %s, %s\n  %s\n",
                            moodData.id,
                            moodData.type,
                            moodData.name,
                            simpleDateFormat.format(moodData.date)));
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
