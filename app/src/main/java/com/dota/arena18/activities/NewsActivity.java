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
        Intent i = new Intent(this, MedalsTallyActivity.class);

        switch (v.getId()) {
            case R.id.btn_arena:
                i.putExtra("flag", 0);
                break;
            case R.id.btn_interbits:
                i.putExtra("flag", 1);
                break;
        }

        startActivity(i);
    }

    public void openTweets(View v) {
        startActivity(new Intent(this, TweetsActivity.class));
    }
}
