package com.example.samsungproj;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.samsungproj.CalcFragment;
import com.example.samsungproj.HomeFragment;
import com.example.samsungproj.ListFragment;
import com.example.samsungproj.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    private HomeFragment homeFragment;
    private ListFragment listFragment;
    private CalcFragment calculatorFragment;

    private Fragment activeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        homeFragment = new HomeFragment();
        listFragment = new ListFragment();
        calculatorFragment = new CalcFragment();

        activeFragment = homeFragment;

        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_layout, homeFragment)
                .add(R.id.frame_layout, listFragment)
                .add(R.id.frame_layout, calculatorFragment)
                .hide(listFragment)
                .hide(calculatorFragment)
                .commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            if (item.getItemId() == R.id.home) {
                selectedFragment = homeFragment;
            } else if (item.getItemId() == R.id.list) {
                selectedFragment = listFragment;
            } else if (item.getItemId() == R.id.calc) {
                selectedFragment = calculatorFragment;
            }

            getSupportFragmentManager().beginTransaction()
                    .hide(activeFragment)
                    .show(selectedFragment)
                    .commit();

            activeFragment = selectedFragment;

            return true;
        });
    }
}