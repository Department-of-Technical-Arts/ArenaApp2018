package com.dota.arena18.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dota.arena18.R;

public class AboutActivity extends AppCompatActivity {
    /**
     * The AboutActivity contains details such as directions to the campus, and a map fragment.
     * Both of these can be taken from past apps, since no major changes are needed.
     *
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }
}
