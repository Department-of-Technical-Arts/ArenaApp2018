package com.dota.arena18.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.dota.arena18.R;
import com.jaouan.compoundlayout.CircleGradientRadioLayout;
import com.jaouan.compoundlayout.CompoundLayout;
import com.jaouan.compoundlayout.GradientRadioLayout;

import java.util.ArrayList;


public class CreditsActivity extends AppCompatActivity {
    /**
     * The CreditsActivity contains a list of all the developers who worked on the app.
     * The names, photos of each are displayed and clicking redirects to their Facebook/Github/LinkedIn profiles
     *
     */


    private RecyclerView mRecycler;
    private CreditsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
        ActionBar actionBar =getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        mRecycler = findViewById(R.id.credits_recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(CreditsActivity.this));

        mAdapter = new CreditsAdapter(CreditsActivity.this, getDevData());

        mRecycler.setAdapter(mAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private ArrayList<DeveloperLayoutDetails> getDevData() {
        ArrayList<DeveloperLayoutDetails> details = new ArrayList<>(3);
        details.add(new DeveloperLayoutDetails("Android Developers", getAndroidDevs()));
        details.add(new DeveloperLayoutDetails("Web Developers", getWebDevs()));
        details.add(new DeveloperLayoutDetails("Designers", getDesigners()));

        return details;
    }

    private ArrayList<DeveloperDetails> getAndroidDevs() {
        ArrayList<DeveloperDetails> details = new ArrayList<>(3);

        details.add(new DeveloperDetails("C Shri Akhil", R.drawable.dev_akhil, new String[] {"https://www.facebook.com/c.akhil.shri","https://github.com/TheGamer007/","https://www.linkedin.com/in/shriakhilc/",null}));
        details.add(new DeveloperDetails("Harshvardhan Takawale", R.drawable.dev_harshvardhan, new String[] { "https://www.facebook.com/harshvardhan.takawale","https://github.com/harshvardhan-takawale","https://www.linkedin.com/in/harshvardhan-takawale-9b5125142/",null}));
        details.add(new DeveloperDetails("Ashwik Reddy", R.drawable.dev_ashwik, new String[] {"https://www.facebook.com/ashwik.aileni","https://github.com/Ashwik",null,null}));

        return details;
    }

    private ArrayList<DeveloperDetails> getWebDevs() {
        ArrayList<DeveloperDetails> details = new ArrayList<>(2);

        details.add(new DeveloperDetails("Rohitt Vashishtha", R.drawable.dev_rohitt, new String[] {"https://www.facebook.com/VagrantRohitt","https://github.com/aero31aero",null,null}));
        details.add(new DeveloperDetails("Abhilash Verma", R.drawable.dev_abhilash, new String[] {"https://www.facebook.com/abhilash.verma.127","https://github.com/rogeredthat",null,null}));

        return details;
    }

    private ArrayList<DeveloperDetails> getDesigners() {
        ArrayList<DeveloperDetails> details = new ArrayList<>(3);

        details.add(new DeveloperDetails("Gagan Aditya", R.drawable.des_gagan, new String[] {"https://www.facebook.com/gagan.aditya.5",null,null,"https://www.behance.net/gagan1805960ea"}));
        details.add(new DeveloperDetails("Anshuman Das", R.drawable.des_anshuman, new String[] {"https://www.facebook.com/anshuman.das.9678",null,null,"https://www.behance.net/f2015230fc49"}));
        details.add(new DeveloperDetails("Nithya Vardhan Reddy", R.drawable.des_nithya, new String[] {"https://www.facebook.com/nithyavardhan",null,null,null}));

        return details;
    }
}