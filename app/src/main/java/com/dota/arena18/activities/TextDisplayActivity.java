package com.dota.arena18.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.dota.arena18.R;

public class TextDisplayActivity extends AppCompatActivity {
    private static final String TAG = TextDisplayActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_display);

        TextView display = findViewById(R.id.tv_display);

        String s = getIntent().getStringExtra("text");
        if (s.equalsIgnoreCase("about")) {
            setTitle("About us");
            display.setText(R.string.about_us);
        } else if (s.equalsIgnoreCase("dir")) {
            setTitle("Directions");
            display.setText(R.string.directions);
        } else {
            Log.i(TAG, "onCreate: Something went wrong");
        }
    }
}
