package com.dota.arena18.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.dota.arena18.R;
import com.dota.arena18.api.ApiClient;
import com.dota.arena18.api.ScoresFeed;
import com.dota.arena18.api.ScoresFeedResponse;
import com.dota.arena18.api.ScoresInterface;
import com.dota.arena18.api.TestApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ashwik on 23-01-2018.
 */

public class ScoresFeedActivity extends AppCompatActivity{

    private static final String SORT_KEY = "createdAt";
    private static final String ORDER = "desc";

    private RecyclerView recyclerView;
    private List<ScoresFeed> list = new ArrayList<>();
    private ScoresFeedAdapter adapter;
    private TextView emptyView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private  String TAG = ScoresFeedActivity.class.getSimpleName();
    private RecyclerView.OnScrollListener scrollListener;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    private boolean isLoading = false;
    LinearLayoutManager layoutManager;
    private int page=1;
    private int totalpages = -1;
    int num=1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoresfeed);

        ActionBar actionBar =getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
        }

        swipeRefreshLayout = findViewById(R.id.feed_refresh);
        recyclerView = findViewById(R.id.feed_recyclerview);
        adapter = new ScoresFeedAdapter(list,this);
        layoutManager = new LinearLayoutManager(this);
        emptyView = findViewById(R.id.feed_emptyview);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isLoading = true;
                page = 1;
                int cur_size = list.size();
                if( cur_size != 0)
                {
                    adapter.clearData();

                }
                getdatafromApi();
            }
        });

        scrollListener = new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if(dy > 0) //check for scroll down
                {

                    visibleItemCount = layoutManager.getChildCount();
                    totalItemCount = layoutManager.getItemCount();
                    pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();

                    if ( (page != totalpages) && !isLoading)
                    {
                        if ( (visibleItemCount + pastVisiblesItems) >= totalItemCount)
                        {
                            isLoading = true;
                            Log.v("...", "Last Item Wow !");
                            page++;
                            getdatafromApi();
                            Log.e(TAG,"current page:"+String.valueOf(page));

                        }

                    }
                }
            }
        };

       recyclerView.addOnScrollListener(scrollListener);

        getdatafromApi();

    }

     private  void getdatafromApi()
     {
         swipeRefreshLayout.setRefreshing(true);

         ScoresInterface apiservice = ApiClient.getClient().create(ScoresInterface.class);
         Call<ScoresFeedResponse> call = apiservice.getScoresfeed(page, SORT_KEY, ORDER);

         call.enqueue(new Callback<ScoresFeedResponse>() {
             @Override
             public void onResponse(@NonNull Call<ScoresFeedResponse> call, @NonNull Response<ScoresFeedResponse> response) {
                 ScoresFeedResponse feedResponse = response.body();
                 int resultCount = feedResponse.getTotalresults();
                 if (resultCount == 0) {
                     // no posts to show
                     recyclerView.setVisibility(View.GONE);
                     emptyView.setVisibility(View.VISIBLE);
                     emptyView.setText("No posts available. Please try later");
                 } else {
                     recyclerView.setVisibility(View.VISIBLE);
                     emptyView.setVisibility(View.GONE);

                     List<ScoresFeed> result = feedResponse.getDocs();

                     if (result != null) {
                         page = response.body().getPage();
                         totalpages = response.body().getTotalPages();

                         list.addAll(result);

                         adapter.notifyDataSetChanged();
                     }
                 }
                 swipeRefreshLayout.setRefreshing(false);
                 isLoading = false;
             }

             @Override
             public void onFailure(@NonNull Call<ScoresFeedResponse> call, @NonNull Throwable t) {
                 // empty view stuff ?
                 recyclerView.setVisibility(View.GONE);
                 emptyView.setVisibility(View.VISIBLE);
                 emptyView.setText("Network not available. Retry later.");
                 swipeRefreshLayout.setRefreshing(false);
                 isLoading = false;
             }
         });

     }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
