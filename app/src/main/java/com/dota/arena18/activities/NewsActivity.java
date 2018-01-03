package com.dota.arena18.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.dota.arena18.R;
import com.yarolegovich.lovelydialog.LovelyInfoDialog;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
    }

    public void openTally(View v) {
        startActivity(new Intent(this, MedalsTallyActivity.class));
    }

    public void openTweets(View v) {
        startActivity(new Intent(this, TweetsActivity.class));
    }
}
