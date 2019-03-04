package com.example.moodtrack;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moodtrack.dal.Affect;
import com.example.moodtrack.dal.MoodViewModel;

import java.util.List;


public class CalendarFragment extends Fragment {

    private MoodViewModel moodViewModel;
    private MoodCalendarListAdapter adapter;

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

        // bind data to text element
        subscribeUiMoods();

        // set up list view for date selected mood data
        loadRecyclerView();
    }

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
