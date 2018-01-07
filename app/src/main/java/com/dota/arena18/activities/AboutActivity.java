package com.dota.arena18.activities;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.dota.arena18.R;

import static android.support.v4.content.PermissionChecker.PERMISSION_DENIED;
import static android.support.v4.content.PermissionChecker.PERMISSION_GRANTED;

public class AboutActivity extends AppCompatActivity {
    /**
     * The AboutActivity contains details such as directions to the campus, and a map fragment.
     * Both of these can be taken from past apps, since no major changes are needed.
     *
     */

    private static final int REQUEST_LOCATION = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        CardView cardView_abt ,cardView_dir ,cardView_map;
        cardView_abt = findViewById(R.id.cardview_abtus);
        cardView_dir = findViewById(R.id.cardview_dir);
        cardView_map = findViewById(R.id.cardview_map);
        cardView_abt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAbout();
            }
        });
        cardView_dir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDir();
            }
        });
        cardView_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMap();
            }
        });

    }

    public void openMap(){
        int permCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
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
