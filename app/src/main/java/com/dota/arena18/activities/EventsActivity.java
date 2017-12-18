package com.dota.arena18.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dota.arena18.R;

public class EventsActivity extends AppCompatActivity {
    /**
     * The EventsActivity lists all the sporting events scheduled to happen during the fest.
     * Each event will have its own DetailsActivity to display information.
     *
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
    }
}
