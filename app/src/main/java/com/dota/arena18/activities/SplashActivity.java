package com.dota.arena18.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dota.arena18.R;
import com.jaredrummler.android.widget.AnimatedSvgView;

public class SplashActivity extends AppCompatActivity implements AnimatedSvgView.OnStateChangeListener{
    /**
     * The SplashActivity is used to display an opening animation. It is the entry point for the
     * application (i.e. LAUNCHER_ACTIVITY).
     * Once the animation finishes, the activity closes itself and opens the LandingActivity.
     */

    private static final String TAG = SplashActivity.class.getSimpleName();
    AnimatedSvgView svgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        svgView = findViewById(R.id.animated_svg_view);
        svgView.setOnStateChangeListener(this);
        svgView.start();
    }

    @Override
    public void onStateChange(int state) {
        if(state == AnimatedSvgView.STATE_FINISHED){
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(SplashActivity.this, LandingActivity.class);
                    startActivity(i);
                    finish();
                }
            },500);
        }
    }
}
