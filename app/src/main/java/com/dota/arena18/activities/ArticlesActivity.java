package com.dota.arena18.activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

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
    private ImageView jc_link;
    private String download_link = "https://www.facebook.com/JournalClubBPHC/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);
        ActionBar actionBar =getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        mRecycler = findViewById(R.id.articles_recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        jc_link = findViewById(R.id.journal_club_link);

        jc_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(download_link));
                    startActivity(myIntent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(ArticlesActivity.this, "No application can handle this request."
                            + " Please install a webbrowser",  Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });

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
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
