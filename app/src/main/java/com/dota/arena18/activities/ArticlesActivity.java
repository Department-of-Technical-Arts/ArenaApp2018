package com.dota.arena18.activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dota.arena18.R;
import com.dota.arena18.api.ApiClient;
import com.dota.arena18.api.ArticleDetails;
import com.dota.arena18.api.ArticlesInterface;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticlesActivity extends AppCompatActivity {

    private SwipeRefreshLayout mRefresh;
    private RecyclerView mRecycler;
    private ArticlesAdapter mAdapter;
    private TextView emptyView;
    private ImageView jc_link;
    private String download_link = "https://www.facebook.com/JournalClubBPHC/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);
        ActionBar actionBar =getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        mRefresh = findViewById(R.id.articles_swiperefresh);
        mRecycler = findViewById(R.id.articles_recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        jc_link = findViewById(R.id.journal_club_link);
        emptyView = findViewById(R.id.articles_empty);

        jc_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(download_link));
                    startActivity(myIntent);
                } catch (ActivityNotFoundException e) {
                    new StyleableToast.Builder(ArticlesActivity.this)
                            .text("Web browser not found")
                            .textColor(Color.RED)
                            .show();
                    e.printStackTrace();
                }
            }
        });

        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshArticlesList();
            }
        });

        refreshArticlesList();
    }

    private void refreshArticlesList() {
        mRefresh.setRefreshing(true);
        ArticlesInterface articles = ApiClient.getClient().create(ArticlesInterface.class);
        Call<ArrayList<ArticleDetails>> call = articles.getArticlesList("_id,title");

        call.enqueue(new Callback<ArrayList<ArticleDetails>>() {
            @Override
            public void onResponse(Call<ArrayList<ArticleDetails>> call, Response<ArrayList<ArticleDetails>> response) {
                //Log.i("Articles", "onResponse: " + call.request().url());
                mRefresh.setRefreshing(false);
                ArrayList<ArticleDetails> articles_list = response.body();

                if (articles_list == null || articles_list.size() == 0) {
                    mRecycler.setVisibility(View.GONE);
                    emptyView.setVisibility(View.VISIBLE);
                } else {
                    emptyView.setVisibility(View.GONE);
                    mRecycler.setVisibility(View.VISIBLE);


                    mAdapter = new ArticlesAdapter(ArticlesActivity.this, articles_list);
                    mRecycler.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ArticleDetails>> call, Throwable t) {
                mRefresh.setRefreshing(false);
                //Log.i("ArticlesActivity", "onFailure: " + call.request().url());
                mRecycler.setVisibility(View.GONE);
                emptyView.setText("Network not available. Retry later.");
                emptyView.setVisibility(View.VISIBLE);
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
