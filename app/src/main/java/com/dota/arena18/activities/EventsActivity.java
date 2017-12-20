package com.dota.arena18.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dota.arena18.R;
import com.ramotion.foldingcell.FoldingCell;

import java.util.ArrayList;

public class EventsActivity extends AppCompatActivity {
    /**
     * The EventsActivity lists all the sporting events scheduled to happen during the fest.
     * Each event will have its own DetailsActivity to display information.
     *
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        ListView theListView = (ListView) findViewById(R.id.mainListView);
        final ArrayList<EventItem> eventItems = EventItem.gettestinglist();
        final FoldingCellListAdapter adapter = new FoldingCellListAdapter(this, eventItems);

        theListView.setAdapter(adapter);

        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ((FoldingCell) view).toggle(false);
                adapter.registerToggle(i);
            }
        });



    }
}
