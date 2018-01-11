package com.dota.arena18.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.dota.arena18.R;
import com.dota.arena18.api.ArticleDetails;
import com.dota.arena18.api.ArticlesInterface;
import com.dota.arena18.api.TestApiClient;

import java.util.ArrayList;

import javax.security.auth.login.LoginException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticlesActivity extends AppCompatActivity {

    private RecyclerView mRecycler;
    private ArticlesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);

        mRecycler = findViewById(R.id.articles_recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));

        ArticlesInterface articles = TestApiClient.getClient().create(ArticlesInterface.class);
        Call<ArrayList<ArticleDetails>> call = articles.getArticlesList();

        call.enqueue(new Callback<ArrayList<ArticleDetails>>() {
            @Override
            public void onResponse(Call<ArrayList<ArticleDetails>> call, Response<ArrayList<ArticleDetails>> response) {
                ArrayList<ArticleDetails> articles_list = response.body();
                mAdapter = new ArticlesAdapter(ArticlesActivity.this, articles_list);
                mRecycler.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<ArticleDetails>> call, Throwable t) {
                Log.i("ArticlesActivity", "onFailure: " + call.request().url());
            }
        });
    }
}
