package com.dota.arena18.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dota.arena18.R;
import com.dota.arena18.api.EventsInterface;
import com.dota.arena18.api.ApiClient;
import com.dota.arena18.api.EventDetails;
import com.dota.arena18.database.Model;
import com.ramotion.foldingcell.FoldingCell;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
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
    ArrayList<Model> realmlist = new ArrayList<>();
    FoldingCellListAdapter adapter;
    ListView theListView;
    private Realm myrealm;
    public Model model = new Model();
    int id=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        theListView = (ListView) findViewById(R.id.mainListView);
        myrealm = Realm.getDefaultInstance();
        adapter = new FoldingCellListAdapter(EventsActivity.this,realmlist);
        theListView.setAdapter(adapter);

        if(myrealm!=null)
        {
            getdatafromrealm(myrealm);
        }


        EventsInterface apiservice  = ApiClient.getClient().create(EventsInterface.class);
        Call<ArrayList<EventDetails>> call = apiservice.getEvents();
        call.enqueue(new Callback<ArrayList<EventDetails>>() {
            @Override
            public void onResponse(Call<ArrayList<EventDetails>> call, Response<ArrayList<EventDetails>> response) {
                list = response.body();
                if(realmlist.size()==0)
                {
                    for(int i=0;i<list.size();i++)
                    {
                       model.setDb_eventname(list.get(i).getEventname());
                       model.setDb_rules(list.get(i).getRules());
                       model.setDb_prizemoney(list.get(i).getPrize());
                      adddatatorealm(model);
                    }

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<EventDetails>> call, Throwable t) {
                Log.e(EventsActivity.class.getSimpleName(),"not connected to internet");

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

     public void getdatafromrealm(Realm realm1) {

        RealmResults<Model> results = realm1.where(Model.class).findAll();
         for (int i = 0; i < results.size(); i++) {
             realm1.beginTransaction();
             results.get(i).setId(id);
             realm1.commitTransaction();
             realmlist.add(results.get(i));
             id++;
         }
           adapter.notifyDataSetChanged();

     }

    private void adddatatorealm(Model mymodel)
       {
        myrealm.beginTransaction();
        Model event = myrealm.createObject(Model.class);
        event.setId(id);
        event.setDb_eventname(mymodel.getDb_eventname());
        event.setDb_rules(mymodel.getDb_rules());
        event.setDb_prizemoney(mymodel.getDb_prizemoney());
        myrealm.commitTransaction();
        realmlist.add(event);
        id++;
        adapter.notifyDataSetChanged();
       }
}
