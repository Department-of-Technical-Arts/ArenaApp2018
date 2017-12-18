package com.dota.arena18.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dota.arena18.R;

public class CreditsActivity extends AppCompatActivity {
    /**
     * The CreditsActivity contains a list of all the developers who worked on the app.
     * The names, photos of each are displayed and clicking redirects to their Facebook/Github/LinkedIn profiles
     *
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
    }
}
