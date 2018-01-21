package com.dota.arena18.activities;

import android.os.Build;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.dota.arena18.R;
import com.jaouan.compoundlayout.CircleGradientRadioLayout;
import com.jaouan.compoundlayout.CompoundLayout;
import com.jaouan.compoundlayout.GradientRadioLayout;


public class CreditsActivity extends AppCompatActivity {
    /**
     * The CreditsActivity contains a list of all the developers who worked on the app.
     * The names, photos of each are displayed and clicking redirects to their Facebook/Github/LinkedIn profiles
     *
     */

    private TextView subtitleTextView;
    private TextView subtitleTextView1;
    private TextView description;
    private View descriptionLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
        ActionBar actionBar =getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        subtitleTextView = findViewById(R.id.subtitle);
        subtitleTextView1 = findViewById(R.id.subtitle1);
        description = findViewById(R.id.long_description);
        descriptionLayout = findViewById(R.id.description_layout);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            bindCompoundListener((CompoundLayout) findViewById(R.id.profile_1), R.string.akhil, R.string.akhil, (CircleGradientRadioLayout) findViewById(R.id.profile_1), getColor(R.color.circle1), getColor(R.color.circle2), subtitleTextView);
            bindCompoundListener((CompoundLayout) findViewById(R.id.profile_2), R.string.harshvardhan, R.string.harshvardhan, (CircleGradientRadioLayout) findViewById(R.id.profile_2), getColor(R.color.circle3), getColor(R.color.circle4),subtitleTextView);
            bindCompoundListener((CompoundLayout) findViewById(R.id.profile_3), R.string.ashwik, R.string.ashwik, (CircleGradientRadioLayout) findViewById(R.id.profile_3),getColor(R.color.circle5), getColor(R.color.circle6),subtitleTextView);
            bindCompoundListener((CompoundLayout) findViewById(R.id.profile_4), R.string.rohitt, R.string.rohitt, (CircleGradientRadioLayout) findViewById(R.id.profile_4),getColor(R.color.circle1), getColor(R.color.circle2),subtitleTextView1);
            bindCompoundListener((CompoundLayout) findViewById(R.id.profile_5), R.string.abhilash, R.string.abhilash, (CircleGradientRadioLayout) findViewById(R.id.profile_5),getColor(R.color.circle3), getColor(R.color.circle4),subtitleTextView1);

        } else {
            bindCompoundListener((CompoundLayout) findViewById(R.id.profile_1), R.string.akhil, R.string.akhil, (CircleGradientRadioLayout) findViewById(R.id.profile_1), 0, 0,subtitleTextView);
            bindCompoundListener((CompoundLayout) findViewById(R.id.profile_2), R.string.harshvardhan, R.string.harshvardhan, (CircleGradientRadioLayout) findViewById(R.id.profile_2), 0,0,subtitleTextView);
            bindCompoundListener((CompoundLayout) findViewById(R.id.profile_3), R.string.ashwik, R.string.ashwik, (CircleGradientRadioLayout) findViewById(R.id.profile_3), 0,0,subtitleTextView);
            bindCompoundListener((CompoundLayout) findViewById(R.id.profile_4), R.string.rohitt, R.string.rohitt, (CircleGradientRadioLayout) findViewById(R.id.profile_4),0, 0,subtitleTextView1);
            bindCompoundListener((CompoundLayout) findViewById(R.id.profile_5), R.string.abhilash, R.string.abhilash, (CircleGradientRadioLayout) findViewById(R.id.profile_5),0, 0,subtitleTextView1);
        }
    }

    /**
     * Bind compound listener.
     *
     * @param compoundLayout Compound layout.
     * @param subtitle       Subtitle to set.
     */
    private void bindCompoundListener(final CompoundLayout compoundLayout, @StringRes final int subtitle, @StringRes final int desc
            , final CircleGradientRadioLayout circleGradientRadioLayout
            , final int color1
            , final int color2
            , final TextView textView) {
        compoundLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Animation fadeOutAnimation = AnimationUtils.loadAnimation(CreditsActivity.this, android.R.anim.fade_out);
                fadeOutAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        textView.setText(getString(subtitle));
                        description.setText(desc);
                        descriptionLayout.startAnimation(AnimationUtils.loadAnimation(CreditsActivity.this
                                , android.R.anim.fade_in));
                        circleGradientRadioLayout.setColorA(color1);
                        circleGradientRadioLayout.setColorB(color2);
                        circleGradientRadioLayout.setAngle(45);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
                descriptionLayout.startAnimation(fadeOutAnimation);
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}