package com.dota.arena18.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Switch;

import com.dota.arena18.R;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    NewsActivityAdapter adapter;
    List<Imagedata> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        recyclerView = (RecyclerView)findViewById(R.id.news_recyclerview);
        list.add(new Imagedata(R.drawable.news_leaderboard,"Arena Leaderboard"));
        list.add(new Imagedata(R.drawable.news_lead,"Inter-BITS Leaderboard"));
        list.add(new Imagedata(R.drawable.news_newsfeed,"Live Feed"));
        list.add(new Imagedata(R.drawable.news_newsletter,"Newsletters"));
        list.add(new Imagedata(R.drawable.news_tweet,"Tweets"));

        adapter = new NewsActivityAdapter(this,list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent i = new Intent(NewsActivity.this, MedalsTallyActivity.class);
                        switch (position){
                            case 0 : i.putExtra("flag", 0);break;
                            case 1 : i.putExtra("flag", 1);break;
                        }
                        startActivity(i);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                })
        );
    }

    public void openTally(View v) {
        /*Intent i = new Intent(this, MedalsTallyActivity.class);

        switch (v.getId()) {
            case R.id.btn_arena:
                i.putExtra("flag", 0);
                break;
            case R.id.btn_interbits:
                i.putExtra("flag", 1);
                break;
        }

        startActivity(i);*/
    }

    public void openTweets(View v) {
        startActivity(new Intent(this, TweetsActivity.class));
    }
}
