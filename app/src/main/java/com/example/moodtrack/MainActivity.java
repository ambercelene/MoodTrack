package com.example.moodtrack;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.example.moodtrack.dal.MoodViewModel;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    // do not reference db in an activity, use a view model
    private MoodViewModel mMoodViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMoodViewModel = ViewModelProviders.of(this).get(MoodViewModel .class);
        mMoodViewModel.initDb();

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        loadFragment(new HomeFragment());
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
}
