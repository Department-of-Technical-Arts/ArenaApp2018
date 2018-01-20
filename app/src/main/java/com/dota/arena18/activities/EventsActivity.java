package com.dota.arena18.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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

import static android.support.v4.content.PermissionChecker.PERMISSION_GRANTED;
import static com.dota.arena18.activities.AboutActivity.REQUEST_LOCATION;

public class EventsActivity extends AppCompatActivity implements FoldingCellListAdapter.ListItemClickListener {
    /**
     * The EventsActivity lists all the sporting EventDetails scheduled to happen during the fest.
     * Each event will have its own DetailsActivity to display information.
     *
     */

    private static final float MILLIS_PER_INCH = 100f; // Larger value ==> slower scroll

    ArrayList<EventDetails> list = new ArrayList<>();
    ArrayList<Model> realmlist = new ArrayList<>();
    FoldingCellListAdapter adapter;
    RecyclerView theRecyclerView;
    private Realm myrealm;
    public Model model = new Model();
    int id;
    private SwipeRefreshLayout swipeRefreshLayout;
    private String TAG = EventsActivity.class.getSimpleName();
    private RecyclerView.SmoothScroller smoothScroller;
    private RecyclerView.LayoutManager layoutManager;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        ActionBar actionBar =getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        theRecyclerView = (RecyclerView) findViewById(R.id.mainRecyclerView);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swiperefresh);
        myrealm = Realm.getDefaultInstance();
        adapter = new FoldingCellListAdapter(EventsActivity.this,realmlist,this);
        theRecyclerView.setAdapter(adapter);

        layoutManager = new LinearLayoutManager(this);
        theRecyclerView.setLayoutManager(layoutManager);
        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(this){
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

        swipeRefreshLayout.setRefreshing(true);

         if(myrealm.isEmpty())
        {
            Log.e(TAG,"realm is empty");
            callapi();
        }
        else
        {
            Log.e(TAG,"realm is not empty");
            callLightapi();
        }

        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        if(myrealm.isEmpty())
                        {
                            Log.e(TAG,"realm is empty");
                            callapi();
                        }
                        else
                        {
                            Log.e(TAG,"realm is not empty");
                            callLightapi();
                        }
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }
        );

     }

     public void callapi() {

        if(isNetworkAvailable())
        {
            connectingdailog();
        }
         id =0;
         EventsInterface apiservice  = ApiClient.getClient().create(EventsInterface.class);
         Call<ArrayList<EventDetails>> call = apiservice.getEventsfromapi();
         call.enqueue(new Callback<ArrayList<EventDetails>>() {
             @Override
             public void onResponse(Call<ArrayList<EventDetails>> call, Response<ArrayList<EventDetails>> response) {

                 list = response.body();
                 for(int i=0;i<list.size();i++)
                 {
                     model.setApi_id(list.get(i).getApi_id());
                     model.setDb_eventname(list.get(i).getEventname());
                     model.setDb_rules(list.get(i).getRules());
                     model.setDb_prizemoney(list.get(i).getPrize());
                     model.setDb_venue(list.get(i).getVenue());
                     adddatatorealm(model);
                 }

                 adapter.notifyDataSetChanged();
                 swipeRefreshLayout.setRefreshing(false);

             }

             @Override
             public void onFailure(Call<ArrayList<EventDetails>> call, Throwable t) {
                 Toast.makeText(EventsActivity.this, "Network not available. Loading offline data...", Toast.LENGTH_SHORT).show();
                 Log.e(EventsActivity.class.getSimpleName(),"not connected to internet");
                 getdatafromrealm(myrealm);
             }
         });

     }

     public void callLightapi()
     {
         id=0;

         EventsInterface apiservice  = ApiClient.getClient().create(EventsInterface.class);
         Call<ArrayList<EventDetails>> call = apiservice.getEvents();
         call.enqueue(new Callback<ArrayList<EventDetails>>() {
             @Override
             public void onResponse(Call<ArrayList<EventDetails>> call, Response<ArrayList<EventDetails>> response) {
                 list = response.body();

                   for(int i=0;i<list.size();i++)
                     {
                         model.setApi_id(list.get(i).getApi_id());
                         model.setDb_eventname(list.get(i).getEventname());
                         model.setDb_prizemoney(list.get(i).getPrize());
                         model.setDb_venue(list.get(i).getVenue());
                         Model model1 = myrealm.where(Model.class).equalTo("id",i).findFirst();
                         model.setDb_rules(model1.getDb_rules());
                         adddatatorealm(model);
                     }

                 adapter.notifyDataSetChanged();
                 swipeRefreshLayout.setRefreshing(false);
             }

             @Override
             public void onFailure(Call<ArrayList<EventDetails>> call, Throwable t) {
                 Toast.makeText(EventsActivity.this, "Network not available. Loading offline data...", Toast.LENGTH_SHORT).show();
                 Log.e(EventsActivity.class.getSimpleName(),"not connected to internet");
                 getdatafromrealm(myrealm);
             }
         });

     }


     public void getdatafromrealm(Realm realm1) {

        RealmResults<Model> results = realm1.where(Model.class).findAll();
        Log.e(TAG,"results size:"+String.valueOf(results.size()));

         for (int i = 0; i < results.size(); i++) {
             realm1.beginTransaction();
             results.get(i).setId(id);
             realm1.commitTransaction();
             if((realmlist.size()-1)<id)
             {realmlist.add(id,results.get(i));}
             else
             {
                 realmlist.set(id,results.get(i));
             }
             id++;
         }

         Log.e(EventsActivity.class.getSimpleName(),"Get datafromrealm total events: " + String.valueOf(results.size()));

         adapter.notifyDataSetChanged();
         if(results.size()==0)
         {
             AlertDialog.Builder alertdailog = new AlertDialog.Builder(this);
             alertdailog.setMessage("Internet connectivity is needed to load and update data. " +
                     "Offline data will be available after connecting once.")
                     .setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialogInterface, int i) {
                     dialogInterface.cancel();
                 }
             }).create().show();
         }
         swipeRefreshLayout.setRefreshing(false);

     }

    private void adddatatorealm(Model mymodel)
       {
        myrealm.beginTransaction();
        Model checkmodel = myrealm.where(Model.class).equalTo("id",id).findFirst();
        if(checkmodel == null ) {
            Log.e(TAG,"checkmodel=null"+String.valueOf(id));
        Model event = myrealm.createObject(Model.class);
        event.setId(id);
        event.setDb_eventname(mymodel.getDb_eventname());
        event.setDb_rules(mymodel.getDb_rules());
        event.setDb_prizemoney(mymodel.getDb_prizemoney());
        event.setApi_id(mymodel.getApi_id());
        event.setDb_venue(mymodel.getDb_venue());
        realmlist.add(event);}

        else{
            Log.e(TAG,"checkmodel!=null"+String.valueOf(id));
            checkmodel.setDb_eventname(mymodel.getDb_eventname());
            if(mymodel.getDb_rules()!="")
            {
                checkmodel.setDb_rules(mymodel.getDb_rules());
            }
            checkmodel.setDb_prizemoney(mymodel.getDb_prizemoney());
            checkmodel.setApi_id(mymodel.getApi_id());
            checkmodel.setDb_venue(mymodel.getDb_venue());
            if((realmlist.size()-1)<id)
            {realmlist.add(id,checkmodel);}
            else
            {
                realmlist.set(id,checkmodel);
            }
        }

        myrealm.commitTransaction();
        id++;
        adapter.notifyDataSetChanged();
       }

    @Override
    public void OnListItemClick(int clickedItemIndex,View view) {
        ((FoldingCell) view).toggle(false);
        smoothScroller.setTargetPosition(clickedItemIndex);
        layoutManager.startSmoothScroll(smoothScroller);
        adapter.registerToggle(clickedItemIndex);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null ;
    }

    public void connectingdailog()
    {
        final AlertDialog.Builder alertdailog1 = new AlertDialog.Builder(this);
        alertdailog1.setMessage("The initial data load will take time , but the future ones will be faster.")
                .setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        final AlertDialog alert = alertdailog1.create();
        alert.show();
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (alert.isShowing()) {
                    alert.dismiss();
                }
            }
        };

        alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                handler.removeCallbacks(runnable);
            }
        });

        handler.postDelayed(runnable, 3000);
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
