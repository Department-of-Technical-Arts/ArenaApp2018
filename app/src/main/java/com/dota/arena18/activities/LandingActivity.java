package com.dota.arena18.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dota.arena18.R;

public class LandingActivity extends AppCompatActivity {

    /**
     * This Activity is the central point of the application, and most navigation will pass through this activity
     * The SplashActivity redirects to this after the animation finishes.
     * The following activities are listed on the screen
     * - Events
     * - News
     * - About
     * - Contact
     * - App Credits
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
    }
}
