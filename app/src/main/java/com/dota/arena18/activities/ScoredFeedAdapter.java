package com.dota.arena18.activities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dota.arena18.R;
import com.dota.arena18.api.ScoresFeed;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashwik on 23-01-2018.
 */

public class ScoredFeedAdapter extends RecyclerView.Adapter<ScoredFeedAdapter.ScoresFeedViewHolder> {


    List<ScoresFeed> list = new ArrayList<>();
    Context c ;

    public ScoredFeedAdapter(List<ScoresFeed> list, Context c) {
        this.list = list;
        this.c = c;
    }

    @Override
    public ScoresFeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.scoresfeed_content,parent,false);
        ScoresFeedViewHolder viewHolder = new ScoresFeedViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ScoresFeedViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {

        if(list!=null){return list.size();}

        return 0;
    }

    public class ScoresFeedViewHolder extends RecyclerView.ViewHolder{

        public ScoresFeedViewHolder(View itemView) {
            super(itemView);
        }
    }

}
