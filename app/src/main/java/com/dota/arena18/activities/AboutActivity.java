package com.dota.arena18.activities;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dota.arena18.R;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.content.PermissionChecker.PERMISSION_DENIED;
import static android.support.v4.content.PermissionChecker.PERMISSION_GRANTED;

public class AboutActivity extends AppCompatActivity {
    /**
     * The AboutActivity contains details such as directions to the campus, and a map fragment.
     * Both of these can be taken from past apps, since no major changes are needed.
     *
     */

    public static final int REQUEST_LOCATION = 101;
    RecyclerView recyclerView;
    List<Imagedata> list = new ArrayList<>();
    public static int permCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        recyclerView = findViewById(R.id.about_recyclerview);
        list.add(new Imagedata(R.drawable.abt,"About us"));
        list.add(new Imagedata(R.drawable.about_directions,"Directions"));
        list.add(new Imagedata(R.drawable.about_map,"Map"));
        AboutActivityAdapter adapter = new AboutActivityAdapter(this,list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(
               new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                   @Override
                   public void onItemClick(View view, int position) {
                       switch (position){
                           case 0: openAbout(); break;
                           case 1: openDir(); break;
                           case 2: openMap(); break;
                       }
                   }

                   @Override
                   public void onLongItemClick(View view, int position) {

                   }
               })
        );

    }

    public void openMap(){
        permCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (permCheck == PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            startActivity(new Intent(this, MapsActivity.class));
        }
    }

    public void openAbout(){
        Intent i = new Intent(this, TextDisplayActivity.class);
        i.putExtra("text", "about");
        startActivity(i);
    }

    public void openDir(){
        Intent i = new Intent(this, TextDisplayActivity.class);
        i.putExtra("text", "dir");
        startActivity(i);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PERMISSION_GRANTED) {
                    startActivity(new Intent(this, MapsActivity.class));
                } else {
                    // Permissions not granted; display reasoning, or deactivate feature
                }
                break;
            default: super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }
}
