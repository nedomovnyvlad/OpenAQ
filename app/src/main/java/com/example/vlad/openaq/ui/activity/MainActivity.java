package com.example.vlad.openaq.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.vlad.openaq.R;
import com.example.vlad.openaq.ui.screen.city.CityFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_frame, CityFragment.newInstance())
                    .commit();
        }
    }
}
