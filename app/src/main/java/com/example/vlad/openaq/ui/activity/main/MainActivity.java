package com.example.vlad.openaq.ui.activity.main;

import android.os.Bundle;

import com.example.vlad.openaq.R;
import com.example.vlad.openaq.ui.activity.BaseActivity;
import com.example.vlad.openaq.ui.screen.city.CityFragment;

public class MainActivity extends BaseActivity {

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
