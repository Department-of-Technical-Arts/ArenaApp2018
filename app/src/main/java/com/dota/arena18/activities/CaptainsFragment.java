package com.dota.arena18.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.dota.arena18.R;

import java.util.ArrayList;

/**
 * Created by lenovo on 12/29/2017.
 */

public class CaptainsFragment extends Fragment implements AdapterView.OnItemClickListener{
    OrganisersAdapter OrganisersAdapter;
    RecyclerView recyclerView;
    ArrayList<Contacts> data1;
    Toolbar toolbar;
    ImageView call_image_captain;
    private RecyclerView.SmoothScroller smoothScroller;
    int captain_no=0;
    private static final float MILLIS_PER_INCH = 100f;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle args = getArguments();
        if(args!=null) {
            captain_no = args.getInt("abc");
        }
        View rootView = inflater.inflate(R.layout.fragment_captains, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        data1 = new ArrayList<>();
        call_image_captain = view.findViewById(R.id.call_image_card);
        recyclerView = (RecyclerView) view.findViewById(R.id.contacts_recycler);
        OrganisersAdapter = new OrganisersAdapter(getActivity(),this,data1);
        recyclerView.setAdapter(OrganisersAdapter);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(getContext()){
            @Override
            protected int getVerticalSnapPreference() {
                return LinearSmoothScroller.SNAP_TO_START;
            }

            @Override
            protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                return MILLIS_PER_INCH / displayMetrics.densityDpi;
            }
        };
        smoothScroller = linearSmoothScroller;

        OrganisersAdapter.setArrayList(data1);

        feedData();

        switch (captain_no){
            case 0 :
                smoothScroller.setTargetPosition(0);
                layoutManager.startSmoothScroll(smoothScroller);
                break;

            case 1 :
                smoothScroller.setTargetPosition(2);
                layoutManager.startSmoothScroll(smoothScroller);
                break;
            case 2 :
                smoothScroller.setTargetPosition(3);
                layoutManager.startSmoothScroll(smoothScroller);
                break;
            case 3 :
                smoothScroller.setTargetPosition(2);
                layoutManager.startSmoothScroll(smoothScroller);
                break;
            case 4 :
                smoothScroller.setTargetPosition(4);
                layoutManager.startSmoothScroll(smoothScroller);
                break;
            case 5 :
                smoothScroller.setTargetPosition(5);
                layoutManager.startSmoothScroll(smoothScroller);
                break;
            case 6 :
                smoothScroller.setTargetPosition(6);
                layoutManager.startSmoothScroll(smoothScroller);
                break;
            case 7 :
                smoothScroller.setTargetPosition(7);
                layoutManager.startSmoothScroll(smoothScroller);
                break;
            case 8 :
                smoothScroller.setTargetPosition(9);
                layoutManager.startSmoothScroll(smoothScroller);
                break;
            case 9 :
                smoothScroller.setTargetPosition(12);
                layoutManager.startSmoothScroll(smoothScroller);
                break;
            case 10 :
                smoothScroller.setTargetPosition(13);
                layoutManager.startSmoothScroll(smoothScroller);
                break;
            case 11 :
                smoothScroller.setTargetPosition(14);
                layoutManager.startSmoothScroll(smoothScroller);
                break;
            case 12 :
                smoothScroller.setTargetPosition(15);
                layoutManager.startSmoothScroll(smoothScroller);
                break;
            case 13 :
                smoothScroller.setTargetPosition(16);
                layoutManager.startSmoothScroll(smoothScroller);
                break;
            case 14 :
                smoothScroller.setTargetPosition(17);
                layoutManager.startSmoothScroll(smoothScroller);
                break;
            case 15 :
                smoothScroller.setTargetPosition(27);
                layoutManager.startSmoothScroll(smoothScroller);
                break;
            case 16 :
                smoothScroller.setTargetPosition(24);
                layoutManager.startSmoothScroll(smoothScroller);
                break;
            case 17 :
                smoothScroller.setTargetPosition(18);
                layoutManager.startSmoothScroll(smoothScroller);
                break;
            case 18 :
                smoothScroller.setTargetPosition(19);
                layoutManager.startSmoothScroll(smoothScroller);
                break;
            case 19 :
                smoothScroller.setTargetPosition(20);
                layoutManager.startSmoothScroll(smoothScroller);
                break;
            case 20 :
                smoothScroller.setTargetPosition(22);
                layoutManager.startSmoothScroll(smoothScroller);
                break;
            case 21 :
                smoothScroller.setTargetPosition(21);
                layoutManager.startSmoothScroll(smoothScroller);
                break;
            case 22 :
                smoothScroller.setTargetPosition(23);
                layoutManager.startSmoothScroll(smoothScroller);
                break;
            case 23 :
                smoothScroller.setTargetPosition(24);
                layoutManager.startSmoothScroll(smoothScroller);
                break;
            case 24 :
                smoothScroller.setTargetPosition(25);
                layoutManager.startSmoothScroll(smoothScroller);
                break;
            case 25 :
                smoothScroller.setTargetPosition(26);
                layoutManager.startSmoothScroll(smoothScroller);
                break;

            default:
                smoothScroller.setTargetPosition(0);
                layoutManager.startSmoothScroll(smoothScroller);
                break;
        }
    }

    private void feedData() {
        Contacts temp1 = new Contacts("Pranav Asthana","Badminton(B)",
                "8142909120", R.drawable.default_pic,"blank");

        Contacts temp2 = new Contacts("Mrinalini Shukla","Badminton(G)",
                "9756200943", R.drawable.default_pic,"blank");

        Contacts temp3 = new Contacts("Rishi Teja","Chess, Team A",
                "9030509445", R.drawable.default_pic,"blank");

        Contacts temp4 = new Contacts("Surya Alluri","Chess, Team B",
                "9440192257", R.drawable.default_pic,"blank");

        Contacts temp5 = new Contacts("Akhilesh","Chess, Team C",
                "9133234481", R.drawable.default_pic,"blank");

        Contacts temp6 = new Contacts("Arpit","Hockey",
                "9133235023", R.drawable.default_pic,"blank");

        Contacts temp7 = new Contacts("Guna Kaushik","Kabaddi",
                "9492322223", R.drawable.default_pic,"blank");

        Contacts temp8 = new Contacts("Rishi","Volleyball(B)",
                "7989906805", R.drawable.default_pic,"blank");

        Contacts temp9 = new Contacts("Prathyusha","Volleyball(G)",
                "7680830838", R.drawable.default_pic,"blank");

        Contacts temp10 = new Contacts("Sumeeth","Carrom(B)",
                "9010921979", R.drawable.default_pic,"blank");

        Contacts temp11 = new Contacts("Aishwarya","Carrom(G)",
                "8885818066", R.drawable.default_pic,"blank");

        Contacts temp12 = new Contacts("Siddharth Garg","Squash, Team A",
                "9100589625", R.drawable.default_pic,"blank");

        Contacts temp13 = new Contacts("Madhan Thiyagarajan","Squash, Team B",
                "9611938719", R.drawable.default_pic,"blank");

        Contacts temp14 = new Contacts("Ajay Singh","Athletics(B)",
                "7404556711", R.drawable.default_pic,"blank");

        Contacts temp15 = new Contacts("Kritika Kasliwal","Athletics(G)",
                "7997083711", R.drawable.default_pic,"blank");

        Contacts temp16 = new Contacts("R. Aadith","Throwball, Powerlifting",
                "8074629851", R.drawable.default_pic,"blank");

        Contacts temp17 = new Contacts("Pranav Mohan","Football(B)",
                "8142899987", R.drawable.default_pic,"blank");

        Contacts temp18 = new Contacts("Shreya Deep","Football(G)",
                "9343540996", R.drawable.default_pic,"blank");

        Contacts temp19 = new Contacts("Vishnu Ramesh ","Basketball(B)",
                "8897055088", R.drawable.default_pic,"blank");

        Contacts temp20 = new Contacts("Uttara Ravi","Basketball(G)",
                "8639855896", R.drawable.default_pic,"blank");

        Contacts temp21 = new Contacts("Sudeep Athreya","Snooker",
                "9133235134", R.drawable.default_pic,"blank");

        Contacts temp22 = new Contacts("Apurav Gupta","8- BALL POOL",
                "9133234271", R.drawable.default_pic,"blank");

        Contacts temp23 = new Contacts("Rishabh Singh","Tennis",
                "8076502532", R.drawable.default_pic,"blank");

        Contacts temp24 = new Contacts("Rajat Mishra","Duathlon",
                "9133234262", R.drawable.default_pic,"blank");

        Contacts temp25 = new Contacts("Deepak Anjna","Bodybuilding",
                "9711863989", R.drawable.default_pic,"blank");

        Contacts temp26 = new Contacts("Purna Srivatsa","Table Tennis(B)",
                "8008397842", R.drawable.default_pic,"blank");

        Contacts temp27 = new Contacts("Aishwarya rebelly","Table Tennis(G)",
                "9591325445", R.drawable.default_pic,"blank");

        Contacts temp28 = new Contacts("Ishant Singh Bhadauriya","Cricket"
                ,"9705932586", R.drawable.default_pic, "blank");


        data1.add(temp14);
        data1.add(temp15);

        data1.add(temp1);
        data1.add(temp2);

        data1.add(temp19);
        data1.add(temp20);

        data1.add(temp25);

        data1.add(temp10);
        data1.add(temp11);

        data1.add(temp3);
        data1.add(temp4);
        data1.add(temp5);

        data1.add(temp28);

        data1.add(temp24);

        data1.add(temp17);
        data1.add(temp18);

        data1.add(temp6);

        data1.add(temp7);

        data1.add(temp21);

        data1.add(temp12);
        data1.add(temp13);

        data1.add(temp26);
        data1.add(temp27);

        data1.add(temp23);

        data1.add(temp16);

        data1.add(temp8);
        data1.add(temp9);

        data1.add(temp22);


    }








    @Override
    public void onItemClick(AdapterView<?> adapterView, final View view, final int i, long l) {

        
        /*Toolbar toolbar = (Toolbar) view.findViewById(R.id.contact_card_toolbar);
          toolbar.inflateMenu(R.menu.options_menu);
          toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.call:
                        Uri number = Uri.parse("tel:" + data1.get(i).getMobile());
                        view.getContext().startActivity(new Intent(Intent.ACTION_DIAL, number));
                        break;
                    *//*case R.id.mail:
                        Intent emailIntent = new Intent(Intent.ACTION_SEND);
                        emailIntent.setType("text/html").putExtra(Intent.EXTRA_EMAIL, new String[]{data1.get(i).getMail()});
                        emailIntent.setType("message/rfc822");
                        view.getContext().startActivity(Intent.createChooser(emailIntent, "Choose an Email client :"));
                        break;*//*
                }
                return false;
            }
        });*/


    }
}
