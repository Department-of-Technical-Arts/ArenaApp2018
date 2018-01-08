package com.dota.arena18.activities;

import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.dota.arena18.R;
import com.jaouan.compoundlayout.CompoundLayout;


public class CreditsActivity extends AppCompatActivity {
    /**
     * The CreditsActivity contains a list of all the developers who worked on the app.
     * The names, photos of each are displayed and clicking redirects to their Facebook/Github/LinkedIn profiles
     *
     */

    private TextView subtitleTextView;
    private TextView description;
    private View descriptionLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        subtitleTextView = (TextView) findViewById(R.id.subtitle);
        description = (TextView) findViewById(R.id.long_description);
        descriptionLayout = findViewById(R.id.description_layout);

        bindCompoundListener((CompoundLayout) findViewById(R.id.profile_1), R.string.harshvardhan,R.string.desc1);
        bindCompoundListener((CompoundLayout) findViewById(R.id.profile_2), R.string.doris_day,R.string.doris_day);
        bindCompoundListener((CompoundLayout) findViewById(R.id.profile_3), R.string.grace_kelly, R.string.grace_kelly);
    }

    /**
     * Bind compound listener.
     *
     * @param compoundLayout Compound layout.
     * @param subtitle       Subtitle to set.
     */
    private void bindCompoundListener(final CompoundLayout compoundLayout, @StringRes final int subtitle,@StringRes final int desc) {
        compoundLayout.setOnCheckedChangeListener(new CompoundLayout.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundLayout compoundLayout, boolean checked) {
                if (checked) {
                    final Animation fadeOutAnimation = AnimationUtils.loadAnimation(CreditsActivity.this, android.R.anim.fade_out);
                    fadeOutAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            subtitleTextView.setText(getString(subtitle));
                            description.setText(desc);
                            descriptionLayout.startAnimation(AnimationUtils.loadAnimation(CreditsActivity.this, android.R.anim.fade_in));
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                    descriptionLayout.startAnimation(fadeOutAnimation);
                }
            }
        });
    }

}
