package com.mood.tracker;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mood.tracker.dal.MoodViewModel;

public class EntriesFragment extends Fragment {

    private MoodViewModel mMoodViewModel;
    private TextView mMoodsListView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup view,
                             @Nullable Bundle savedInstanceState) {

        // do not reference db in an activity, use a view model
        mMoodViewModel = ViewModelProviders.of(this).get(MoodViewModel.class);

        return inflater.inflate(R.layout.fragment_entries, null);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        // get the view mood list text element
        mMoodsListView = (TextView) getActivity().findViewById(R.id.mood_list);

        populateDb();

        // bind data to text element
        subscribeUiMoods();
    }


    private void populateDb() {
        mMoodViewModel.createDb();
    }

    private void subscribeUiMoods() {
        mMoodViewModel.getAffectsResult().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String result) {
                mMoodsListView.setText(result);
            }
        });
    }
}


