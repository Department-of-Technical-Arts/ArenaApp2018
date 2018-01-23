package com.dota.arena18.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.dota.arena18.R;
import com.dota.arena18.api.ApiClient;
import com.dota.arena18.api.ScoresFeed;
import com.dota.arena18.api.ScoresInterface;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ashwik on 23-01-2018.
 */

public class ScoresFeedActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private List<ScoresFeed> list;
    private ScoredFeedAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoresfeed);

        swipeRefreshLayout = findViewById(R.id.feed_refresh);
        recyclerView = findViewById(R.id.feed_recyclerview);
        adapter = new ScoredFeedAdapter(list,this);

        getdatafromApi();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                
                swipeRefreshLayout.setRefreshing(false);
            }
        });


    }

     private  void getdatafromApi()
     {
         ScoresInterface apiservice = ApiClient.getClient().create(ScoresInterface.class);
         Call<ArrayList<ScoresFeed>> call = apiservice.getScoresfeed();
         call.enqueue(new Callback<ArrayList<ScoresFeed>>() {
             @Override
             public void onResponse(Call<ArrayList<ScoresFeed>> call, Response<ArrayList<ScoresFeed>> response) {
                 list = response.body();
                 adapter.notifyDataSetChanged();
             }

             @Override
             public void onFailure(Call<ArrayList<ScoresFeed>> call, Throwable t) {
                 Log.e("ScoresFeedActivity","Not connected to internet");
                 new StyleableToast.Builder(ScoresFeedActivity.this)
                         .text("No Network Connection...")
                         .textColor(Color.RED)
                         .show();
             }
         });

     }
}
