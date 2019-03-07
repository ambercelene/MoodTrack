package com.example.moodtrack;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;

import androidx.fragment.app.Fragment;


public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

//        HashMap<String, int> stats = new HashMap<>();
//        stats.put("Happy", 0);
//        int happyCount = 0;
//
//        if (mood.type == "Happy") {
//            stats.put("Happy", stats.get("Happy") + 1);
//            happyCount++;
//
//        }

        return inflater.inflate(R.layout.fragment_home, null);
    }

}
