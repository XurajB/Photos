package com.iandrobot.example.photos.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.iandrobot.example.photos.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //add to container
        if (findViewById(R.id.fragment_container) != null) {

            //to prevent overlap
            if (savedInstanceState != null) {
                return;
            }

            MainFragment mainFragment = new MainFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, mainFragment)
                    .commit();
        }
    }
}
