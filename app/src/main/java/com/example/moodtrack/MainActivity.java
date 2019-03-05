package com.example.moodtrack;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.example.moodtrack.dal.MoodViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

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
