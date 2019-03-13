package com.example.moodtrack;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;

import java.util.HashMap;

import androidx.fragment.app.Fragment;


public class HomeFragment extends Fragment {

    String selectedDate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // example
        if (savedInstanceState != null) {
            selectedDate = savedInstanceState.getString("Date");
//            journal.getDailyEntries(selectedDate).getDailyMoodCounts();
        }

        PieChart chart = getActivity().findViewById(R.id.chart);
        Log.d("PieChart", String.valueOf(chart== null) );
        return inflater.inflate(R.layout.fragment_home, null);
    }

}
