package com.dota.arena18.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dota.arena18.R;

public class DetailsActivity extends AppCompatActivity {
    /**
     * The DetailsActivity provides a template to display event information
     * Each event will have a textual description field, and contact information
     * We may/may not have an image on top
     *
     * This information will be retrieved from the server and also cached in the database.
     *
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
    }
}
