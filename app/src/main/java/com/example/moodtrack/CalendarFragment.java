package com.example.moodtrack;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.moodtrack.dal.Affect;
import com.example.moodtrack.dal.MoodViewModel;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class CalendarFragment extends Fragment {

    private MoodViewModel moodViewModel;
    private MoodCalendarListAdapter adapter;

    private String selectedDate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup view,
                             @Nullable Bundle savedInstanceState) {

        // do not reference db in an activity, use a view model
        moodViewModel = ViewModelProviders.of(this).get(MoodViewModel.class);

        return inflater.inflate(R.layout.fragment_calendar, null);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

//        startDb();

        CalendarView cal = getActivity().findViewById(R.id.calendarView);
        cal.setOnDateChangeListener(dateSelectedCallback);

        // bind data to text element
        subscribeUiMoods();

        // set up list view for date selected mood data
        loadRecyclerView();
    }

    private CalendarView.OnDateChangeListener dateSelectedCallback = new CalendarView.OnDateChangeListener() {
        @Override
        public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
            selectedDate = String.format("%d-%02d-%02d", year, month, dayOfMonth);
            Log.d("DateSelected", selectedDate);
            Toast.makeText(getContext(), selectedDate, Toast.LENGTH_SHORT).show();
        }
    };

    // set up list view for date selected mood data using an adapter
    private void loadRecyclerView() {
        RecyclerView recyclerView = getActivity().findViewById(R.id.recyclerview);
        adapter = new MoodCalendarListAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void startDb() {
        moodViewModel.initDb();
    }

    private void subscribeUiMoods() {
        moodViewModel.getAffectsList().observe(this, new Observer<List<Affect>>() {
            @Override
            public void onChanged(@Nullable final List<Affect> affects) {
                // Update the cached copy of the affects in the adapter.
                adapter.setMoodData(affects);
            }
        });
    }
}
