package com.dota.arena18.activities;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;

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


    public void handleClicks(View v){
        switch (v.getId()){
            case R.id.btn_about:
                startActivity(new Intent(this, AboutActivity.class));
                break;
            case R.id.btn_events:
                startActivity(new Intent(this, EventsActivity.class));
                break;
            case R.id.btn_contact:
                startActivity(new Intent(this, ContactsActivity.class));
                break;
            case R.id.btn_news:
                startActivity(new Intent(this,NewsActivity.class));
            default: break;
        }
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
