package com.dota.arena18.activities;

import android.app.ListActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.dota.arena18.R;
import com.twitter.sdk.android.tweetui.SearchTimeline;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.yarolegovich.lovelydialog.LovelyInfoDialog;

public class TweetsActivity extends ListActivity {

    // The TweetsActivity loads and displays a list of tweets with a particular hastag

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweets);

        final SearchTimeline searchTimeline = new SearchTimeline.Builder()
                .query("#Arena2018")
                .maxItemsPerRequest(50)
                .build();
        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(this)
                .setTimeline(searchTimeline)
                .build();
        setListAdapter(adapter);
    }
}
