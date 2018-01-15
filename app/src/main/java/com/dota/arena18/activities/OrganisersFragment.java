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

        Contacts temp1 = new Contacts("Abhinav Eittireddy","President",
                "9542929354", R.drawable.default_pic,"blank");

        Contacts temp2 = new Contacts("Aditya Iyer","General Secretary",
                "9705932532", R.drawable.default_pic,"blank");

        Contacts temp3 = new Contacts("Kritanshu Singh","Sports Secretary(B)",
                "7587785794", R.drawable.default_pic,"blank");

        Contacts temp4 = new Contacts("Alla Pranathi","Department of Arts and Deco",
                "7675801005", R.drawable.default_pic,"blank");

        Contacts temp5 = new Contacts("Poluri Siddhartha Reddy","Department of Firewallz",
                "9640909609", R.drawable.default_pic,"blank");

        Contacts temp6 = new Contacts("Deepansh Garg","Department of Lights and Sounds",
                "8504923423", R.drawable.default_pic,"blank");

        Contacts temp7 = new Contacts("Abhijeet Singh Shekhawat","Department of Photography",
                "9133234429", R.drawable.default_pic,"blank");

        Contacts temp8 = new Contacts("Kapil Shah","Department of Professional Events",
                "9724320802", R.drawable.default_pic,"blank");

        Contacts temp9 = new Contacts("Dirisala Ajay M.","Department of Publicity and Public Relations",
                "9133192283", R.drawable.default_pic,"blank");

        Contacts temp10 = new Contacts("Shobhit Jain","Department of Recreational Activities",
                "8824272425", R.drawable.default_pic,"blank");

        Contacts temp11 = new Contacts("Gummalla Sri Bhargav","Department of Sponsorship and Marketing",
                "7661837418", R.drawable.default_pic,"blank");

        Contacts temp12 = new Contacts("Seerapu Gagan Aditya Reddy","Department of Technical Arts",
                "9493531127", R.drawable.default_pic,"blank");

        Contacts temp13 = new Contacts("Sayan Ray","Department of Visual Effects",
                "9133312930", R.drawable.default_pic,"blank");

        data.add(temp1);
        data.add(temp2);
        data.add(temp3);
        data.add(temp4);
        data.add(temp5);
        data.add(temp6);
        data.add(temp7);
        data.add(temp8);
        data.add(temp9);
        data.add(temp10);
        data.add(temp11);
        data.add(temp12);
        data.add(temp13);


        
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
