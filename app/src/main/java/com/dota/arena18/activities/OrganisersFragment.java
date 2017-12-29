package com.dota.arena18.activities;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.dota.arena18.R;

import java.util.ArrayList;

/**
 * Created by lenovo on 12/29/2017.
 */

public class OrganisersFragment extends android.support.v4.app.Fragment implements AdapterView.OnItemClickListener {

    OrganisersAdapter OrganisersAdapter;
    RecyclerView recyclerView;
    ArrayList<Contacts> data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_organisers, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        data = new ArrayList<>();
        recyclerView = (RecyclerView) view.findViewById(R.id.contacts_recycler);

        OrganisersAdapter = new OrganisersAdapter(getActivity(),this,data);
        recyclerView.setAdapter(OrganisersAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        OrganisersAdapter.setArrayList(data);
        feedData();
    }

    private void feedData() {
        
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView,final View view,final int i, long l) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.contact_card_toolbar);
        toolbar.inflateMenu(R.menu.options_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.call:
                        Uri number = Uri.parse("tel:" + data.get(i).getMobile());
                        view.getContext().startActivity(new Intent(Intent.ACTION_DIAL, number));
                        break;
                    case R.id.mail:
                        Intent emailIntent = new Intent(Intent.ACTION_SEND);
                        emailIntent.setType("text/html").putExtra(Intent.EXTRA_EMAIL, new String[]{data.get(i).getMail()});
                        emailIntent.setType("message/rfc822");
                        view.getContext().startActivity(Intent.createChooser(emailIntent, "Choose an Email client :"));
                        break;
                }
                return false;
            }
        });

    }
}
