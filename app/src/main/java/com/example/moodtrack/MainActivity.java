package com.example.moodtrack;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.moodtrack.dal.MoodViewModel;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    // do not reference db in an activity, use a view model
    private MoodViewModel mMoodViewModel;

    private String mood_affect;
    private int intensity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMoodViewModel = ViewModelProviders.of(this).get(MoodViewModel .class);
//        mMoodViewModel.initDb();

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        loadFragment(new HomeFragment());

        final Button button = findViewById(R.id.save_entry_btn);
//        button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                Toast msg = Toast.makeText(getApplicationContext(), "Button pushed", Toast.LENGTH_SHORT);
//                msg.show();
//                Intent replyIntent = new Intent();
//                if (TextUtils.isEmpty(mEditWordView.getText())) {
//                    setResult(RESULT_CANCELED, replyIntent);
//                } else {
//                    String word = mEditWordView.getText().toString();
//                    replyIntent.putExtra(EXTRA_REPLY, word);
//                    setResult(RESULT_OK, replyIntent);
//                }
//                finish();
//            }
//        });
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
       return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Fragment fragment = null;
        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                fragment = new HomeFragment();
                break;
            case R.id.navigation_entries:
                fragment = new EntriesFragment();
                break;
            case R.id.navigation_calendar:
                fragment = new CalendarFragment();
                break;

        }
        return loadFragment(fragment);
    }

    public void onIntensityRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.intensity_level_1:
                if (checked)
                    // actually do something
                    intensity = 1;
                    Log.d("Intensity", "Level 1");
                    break;
            case R.id.intensity_level_2:
                if (checked)
                    // actually do something
                    intensity = 2;
                    Log.d("Intensity", "Level 2");
            case R.id.intensity_level_3:
                if (checked)
                    // actually do something
                    intensity = 3;
                Log.d("Intensity", "Level 3");
            case R.id.intensity_level_4:
                if (checked)
                    // actually do something
                    intensity = 4;
                Log.d("Intensity", "Level 4");
            case R.id.intensity_level_5:
                if (checked)
                    // actually do something
                    intensity = 5;
                Log.d("Intensity", "Level 5");
                break;
        }

    }

}
