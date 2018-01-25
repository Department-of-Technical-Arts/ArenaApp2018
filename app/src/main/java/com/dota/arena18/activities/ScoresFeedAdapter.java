package com.dota.arena18.activities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dota.arena18.R;
import com.dota.arena18.api.ScoresFeed;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashwik on 23-01-2018.
 */

public class ScoresFeedAdapter extends RecyclerView.Adapter<ScoresFeedAdapter.ScoresFeedViewHolder> {


    List<ScoresFeed> list = new ArrayList<>();
    private Context c ;

    public ScoresFeedAdapter(List<ScoresFeed> list, Context c) {
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
        holder.title.setText(list.get(position).getSport());
        holder.text.setText(list.get(position).getScorestext());

    }

    @Override
    public int getItemCount() {

        return (list!= null) ? list.size() : 0;
    }

    public class ScoresFeedViewHolder extends RecyclerView.ViewHolder{

        TextView title ,text ,time;

        public ScoresFeedViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.sport_title);
            text = itemView.findViewById(R.id.text_feed);
            time = itemView.findViewById(R.id.time_feed);
        }
    }

}
