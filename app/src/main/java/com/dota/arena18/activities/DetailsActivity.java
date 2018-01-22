package com.dota.arena18.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.dota.arena18.R;
import com.dota.arena18.api.ApiClient;
import com.dota.arena18.api.EventDetails;
import com.dota.arena18.api.EventsInterface;
import com.dota.arena18.database.Model;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends AppCompatActivity {
    /**
     * The DetailsActivity provides a template to display event information
     * Each event will have a textual description field, and contact information
     * We may/may not have an image on top
     *
     * This information will be retrieved from the server and also cached in the database.
     *
     */
    EventDetails event;
    Model model ;
    private Realm realm;
    TextView rules;
    String api_id, stringid;
    int id;
    ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar =getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        img = findViewById(R.id.app_bar_image);

        Intent intent = getIntent();
        String name = getIntent().getStringExtra("event_name");

        setTitle(name);
        api_id = intent.getStringExtra("api_id");
        stringid = intent.getStringExtra("eventid");
        id = Integer.parseInt(stringid);
        rules = (TextView)findViewById(R.id.rules_details);
        realm = Realm.getDefaultInstance();

        img.setImageResource(getIntent().getIntExtra("img_res", R.drawable.download));


        EventsInterface apiservice  = ApiClient.getClient().create(EventsInterface.class);
        Call<EventDetails> call = apiservice.getevent(api_id);

        call.enqueue(new Callback<EventDetails>() {
            @Override
            public void onResponse(Call<EventDetails> call, Response<EventDetails> response) {

               event = response.body();
               rules.setText(event.getRules());
               updatedatatorealm();
            }

            @Override
            public void onFailure(Call<EventDetails> call, Throwable t) {

                Log.e(DetailsActivity.class.getSimpleName(),"DetailsActivity: not connected to internet");
                getDatafromrealm();
            }
        });





    }
    public void updatedatatorealm(){
        Model eventmodel = realm.where(Model.class).equalTo("id",id).findFirst();
        realm.beginTransaction();
        eventmodel.setDb_rules(event.getRules());
        realm.commitTransaction();

    }
    public void getDatafromrealm()
    {
        Model eventmodel = realm.where(Model.class).equalTo("id",id).findFirst();
        if(eventmodel.getDb_rules()!="")
          {
           rules.setText(eventmodel.getDb_rules());
          }

    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
