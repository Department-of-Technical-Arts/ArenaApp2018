package com.dota.arena18.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dota.arena18.R;
import com.dota.arena18.api.EventsInterface;
import com.dota.arena18.api.ApiClient;
import com.dota.arena18.api.EventDetails;
import com.ramotion.foldingcell.FoldingCell;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventsActivity extends AppCompatActivity {
    /**
     * The EventsActivity lists all the sporting EventDetails scheduled to happen during the fest.
     * Each event will have its own DetailsActivity to display information.
     *
     */


    ArrayList<EventDetails> list = new ArrayList<>();
    FoldingCellListAdapter adapter;
    ListView theListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        theListView = (ListView) findViewById(R.id.mainListView);


        EventsInterface apiservice  = ApiClient.getClient().create(EventsInterface.class);
        Call<ArrayList<EventDetails>> call = apiservice.getEvents();
        call.enqueue(new Callback<ArrayList<EventDetails>>() {
            @Override
            public void onResponse(Call<ArrayList<EventDetails>> call, Response<ArrayList<EventDetails>> response) {
                list = response.body();
                adapter = new FoldingCellListAdapter(EventsActivity.this,list);
                theListView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<EventDetails>> call, Throwable t) {
                Log.e(EventsActivity.class.getSimpleName(),"not able to access");

            }
        });




        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ((FoldingCell) view).toggle(false);
                adapter.registerToggle(i);
            }
        });



    }
}
