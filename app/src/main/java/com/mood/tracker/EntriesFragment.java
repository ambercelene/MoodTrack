package com.mood.tracker;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mood.tracker.dal.MoodViewModel;

public class EntriesFragment extends Fragment {

    public static final String EXTRA_REPLY = "com.mood.tracker.moodlistsql.REPLY";

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

        final Button saveButton = getActivity().findViewById(R.id.button_save);

//        saveButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                Intent replyIntent = new Intent();
//                if (TextUtils.isEmpty(saveButton.getText())) {
//                    getActivity().setResult(getActivity().RESULT_CANCELED, replyIntent);
//                } else {
//                    String word = saveButton.getText().toString();
//                    replyIntent.putExtra(EXTRA_REPLY, word);
//                    getActivity().setResult(getActivity().RESULT_OK, replyIntent);
//                }
//                getActivity().finish();
//            }
//        });
    }

}


