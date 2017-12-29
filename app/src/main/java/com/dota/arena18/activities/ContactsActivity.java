package com.dota.arena18.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
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
                    OrganisersFragment organisers = new com.dota.arena18.activities.OrganisersFragment();
                    return organisers;
                case 1:
                    CaptainsFragment captains = new com.dota.arena18.activities.CaptainsFragment();
                    return captains;
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
                    return "Organisers";
                case 1:
                    return "Captains";
            }
            return null;
        }
    }
}
