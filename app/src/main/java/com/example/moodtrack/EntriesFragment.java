package com.example.moodtrack;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.example.moodtrack.dal.MoodViewModel;

public class EntriesFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    public static final String EXTRA_REPLY = "com.example.moodtrack.entry.REPLY";

    private int intensity;

    private MoodViewModel mMoodViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup view,
                             @Nullable Bundle savedInstanceState) {

        // do not reference db in an activity, use a view model
        mMoodViewModel = ViewModelProviders.of(this).get(MoodViewModel.class);

        return inflater.inflate(R.layout.fragment_entries, null);

    }

    // view is created, now configure event handlers
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        Spinner moodsList = loadSpinner((Spinner) getActivity().findViewById(R.id.entry_spinner));

        final Button textBox = getActivity().findViewById(R.id.entry_textbox);
    }

    private Spinner loadSpinner(Spinner spinner) {

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.moods_array,
                android.R.layout.simple_spinner_item
        );

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        return spinner;
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

}


