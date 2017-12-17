package com.dota.arena18;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jaredrummler.android.widget.AnimatedSvgView;

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = SplashActivity.class.getSimpleName();

    AnimatedSvgView svgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        svgView = findViewById(R.id.animated_svg_view);
        svgView.start();
    }
}
