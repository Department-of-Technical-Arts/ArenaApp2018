package com.dota.arena18.activities;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.dota.arena18.R;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.tweetui.SearchTimeline;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;

public class NewsActivity extends ListActivity {
    /**
     * The NewsActivity contains a link to the scores tally table, and a list of newsletters
     * Required details will all be retrieved from the servers via API calls.
     *
     * Maintaining a database with the current information will be useful in case of bad network
     * coverage, since otherwise they will only see placeholder information.
     *
     * Opening this activity should trigger an API call that checks for new newsletters. This only
     * retrieves the number, and not actual text.
     *
     * Updates to tally are made when it is opened. Newsletter text is fetched when it is opened.
     *
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);



        final SearchTimeline searchTimeline = new SearchTimeline.Builder()
                .query("#Football")
                .maxItemsPerRequest(50)
                .build();
        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(this)
                .setTimeline(searchTimeline)
                .build();
        setListAdapter(adapter);
    }
}
