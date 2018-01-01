package com.dota.arena18.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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

    public void openMap(View v){
        startActivity(new Intent(this, MapsActivity.class));
    }

    public void openAbout(View v){
        Intent i = new Intent(this, TextDisplayActivity.class);
        i.putExtra("text", "about");
        startActivity(i);
    }

    public void openDir(View v){
        Intent i = new Intent(this, TextDisplayActivity.class);
        i.putExtra("text", "dir");
        startActivity(i);
    }
}
