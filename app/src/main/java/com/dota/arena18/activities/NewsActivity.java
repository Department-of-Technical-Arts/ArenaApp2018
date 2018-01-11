package com.dota.arena18.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
                        Intent i;
                        switch (position){
                            case 0 : // Arena tally
                                i = new Intent(NewsActivity.this, MedalsTallyActivity.class);
                                i.putExtra("flag", 0);
                                break;
                            case 1 : // InterBITS tally
                                i = new Intent(NewsActivity.this, MedalsTallyActivity.class);
                                i.putExtra("flag", 1);
                                break;
                            case 2: // News Feed
//                                i = new Intent(NewsActivity.this, MedalsTallyActivity.class);
//                                break;
                                return;
                            case 3: // Newsletters
                                i = new Intent(NewsActivity.this, ArticlesActivity.class);
                                break;
                            case 4: // Tweets
                                i = new Intent(NewsActivity.this, TweetsActivity.class);
                                break;
                            default:
                                Log.i("NewsActivity", "onItemClick: unexpected position passed.");
                                return;
                        }
                        startActivity(i);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                })
        );
    }
}
