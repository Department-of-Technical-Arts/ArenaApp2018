package com.dota.arena18.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.dota.arena18.R;
import com.yarolegovich.lovelydialog.LovelyInfoDialog;

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
        ActionBar actionBar =getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        recyclerView = (RecyclerView)findViewById(R.id.news_recyclerview);
        list.add(new Imagedata(R.drawable.ic_arena_leaderboard,"Arena Leaderboard"));
        list.add(new Imagedata(R.drawable.ic_interbits_leaderboard,"Inter-BITS Leaderboard"));
        list.add(new Imagedata(R.drawable.ic_newsfeed,"Live Feed"));
        list.add(new Imagedata(R.drawable.ic_newsletter,"Newsletters"));

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
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
