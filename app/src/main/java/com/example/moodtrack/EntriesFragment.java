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
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.moodtrack.dal.Affect;
import com.example.moodtrack.dal.DateHelper;
import com.example.moodtrack.dal.MoodViewModel;

public class EntriesFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    public static final String EXTRA_REPLY = "com.example.moodtrack.entry.REPLY";

    private String feeling;
    private String description;
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

        // configure dropdown menu (spinner)
        final Spinner moodsList = loadSpinner((Spinner) getActivity().findViewById(R.id.entry_spinner));
        moodsList.setOnItemSelectedListener(this);

//        final EditText textbox = getActivity().findViewById(R.id.entry_description);
//        textbox.setOnClickListener(textEnteredCallback);

        final RadioGroup radio = getActivity().findViewById(R.id.entry_intensity);
        radio.setOnCheckedChangeListener(intensityCallback);

        final Button button = getActivity().findViewById(R.id.save_entry_btn);
        button.setOnClickListener(saveEntryCallback);


    }

    /* ------------------------------------------------------------------------
    /   Feeling Spinner Input
    /  --------------------------------------------------------------------- */

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

        feeling = parent.getItemAtPosition(pos).toString();

        Toast.makeText(parent.getContext(),
                "OnItemSelectedListener : " + feeling,
                Toast.LENGTH_SHORT).show();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    /* ------------------------------------------------------------------------
    /   Description EditText Input
    /  --------------------------------------------------------------------- */

    private View.OnClickListener textEnteredCallback = new View.OnClickListener() {
        public void onClick(View v) {
//            String str = eText.getText().toString();
//            Toast msg = Toast.makeText(getBaseContext(),str,Toast.LENGTH_LONG);
//            msg.show();
        }
    };

    /* ------------------------------------------------------------------------
    /   Intensity Radio Input
    /  --------------------------------------------------------------------- */

    private RadioGroup.OnCheckedChangeListener intensityCallback =
            new RadioGroup.OnCheckedChangeListener() {
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    // checkedId is the RadioButton selected

                    switch(checkedId) {
                        case R.id.intensity_level_1:
                            intensity = 1;
                            break;
                        case R.id.intensity_level_2:
                            intensity = 2;
                            break;
                        case R.id.intensity_level_3:
                            intensity = 3;
                            break;
                        case R.id.intensity_level_4:
                            intensity = 4;
                            break;
                        case R.id.intensity_level_5:
                            intensity = 5;
                            break;
                    }
                    Log.d("Intensity", "Level " + intensity);
                }
            };

    /* ------------------------------------------------------------------------
    /   Save Entry Button
    /  --------------------------------------------------------------------- */

    private View.OnClickListener saveEntryCallback = new View.OnClickListener() {
        public void onClick(View v) {
            Toast.makeText(getContext(), "Saving Entry", Toast.LENGTH_SHORT).show();

            if (feeling != null && intensity != 0) {

                EditText textbox = getActivity().findViewById(R.id.entry_description);
                description = textbox.getText().toString();

                Affect moodData = new Affect(feeling, description, DateHelper.getTodayPlusDays(0));

                //TODO: Save entry to db
                if (mMoodViewModel == null) {
                    Log.d("DB error", "No view model");
                }
                mMoodViewModel.insert(moodData);
                Log.d("Saving Entry", moodData.toString());
            }
        }
    };

}


