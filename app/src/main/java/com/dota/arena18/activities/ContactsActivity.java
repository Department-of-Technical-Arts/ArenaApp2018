package com.dota.arena18.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dota.arena18.R;

public class ContactsActivity extends AppCompatActivity {
    /**
     * The ContactsActivity contains a list of post-holder contact information.
     * Code can be taken from previous apps, with just the raw data being changed.
     *
     * Items should open the number in dailer when clicked.
     *
     * (optional) Update contact details via API call rather than just raw, hard-coded data.
     *
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
    }
}
