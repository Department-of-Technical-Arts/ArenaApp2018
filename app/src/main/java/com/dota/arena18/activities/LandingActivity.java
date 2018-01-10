package com.dota.arena18.activities;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;

import com.dota.arena18.R;
import com.gjiazhe.panoramaimageview.GyroscopeObserver;
import com.gjiazhe.panoramaimageview.PanoramaImageView;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;

public class LandingActivity extends AppCompatActivity {

    /**
     * This Activity is the central point of the application, and most navigation will pass through this activity
     * The SplashActivity redirects to this after the animation finishes.
     * The following activities are listed on the screen
     * - Events
     * - News
     * - About
     * - Sponsors
     * - Contact
     * - App Credits
     */

    // TODO: All items should be in a Recycler, instead of as individual buttons.
    // TODO: Copy over current OnClick functions to Recycler when implemented

    private GyroscopeObserver gyroscopeObserver;
    CardView events,aboutus,news, sponsors,credits,contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        TwitterAuth twitterAuth = new TwitterAuth(this);
        twitterAuth.loadInBackground();


        gyroscopeObserver = new GyroscopeObserver();

        gyroscopeObserver.setMaxRotateRadian(Math.PI/2);

        PanoramaImageView panoramaImageView = (PanoramaImageView) findViewById(R.id.panorama_image_view);
        panoramaImageView.setGyroscopeObserver(gyroscopeObserver);


       events = (CardView)findViewById(R.id.card_events);
        aboutus= (CardView)findViewById(R.id.card_about);
        news= (CardView)findViewById(R.id.card_news);
        sponsors = (CardView)findViewById(R.id.card_sposners);
        contacts = (CardView)findViewById(R.id.card_contacts);
        credits = (CardView)findViewById(R.id.card_credits);


        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), EventsActivity.class));
            }
        });

        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AboutActivity.class));
            }
        });

        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), NewsActivity.class));
            }
        });

        sponsors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        credits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CreditsActivity.class));
            }
        });

        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ContactsActivity.class));
            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        gyroscopeObserver.unregister();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gyroscopeObserver.register(this);
    }

    public class TwitterAuth extends AsyncTaskLoader{

        private Context mcontext;

        public TwitterAuth(Context context) {
            super(context);
            mcontext = context;
        }

        @Override
        public Object loadInBackground() {

            TwitterConfig config = new TwitterConfig.Builder(mcontext)
                    .logger(new DefaultLogger(Log.DEBUG))
                    .twitterAuthConfig(new TwitterAuthConfig(getString(R.string.consumer_key),getString(R.string.consumer_secret)))
                    .debug(true)
                    .build();
            Twitter.initialize(config);

            return null;
        }
    }
}
