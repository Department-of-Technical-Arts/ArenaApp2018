package com.dota.arena18.activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.dota.arena18.R;

public class ContactsActivity extends AppCompatActivity {
    /**
     * The ContactsActivity contains a list of post-holder contact information.
     * Code can be taken from previous apps, with just the raw data being changed.
     *
     * Items should open the number in dailer when clicked.
     *
     * (optional) Update contact details via API call rather than just raw, hard-coded data.
     *
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private int captain_scroll=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        if(getIntent().hasExtra("event_index")) {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            if (!bundle.isEmpty()) {
                captain_scroll = bundle.getInt("event_index");

            }
        }


        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());


        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }




    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    CaptainsFragment captains = new com.dota.arena18.activities.CaptainsFragment();
                    Bundle args = new Bundle();
                    args.putInt("abc",captain_scroll);
                    captains.setArguments(args);
                    return captains;
                case 1:
                    OrganisersFragment organisers = new com.dota.arena18.activities.OrganisersFragment();
                    return organisers;

                default:
                    return null;

            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Captains";
                case 1:
                    return "Organisers";
            }
            return null;
        }
    }
}
