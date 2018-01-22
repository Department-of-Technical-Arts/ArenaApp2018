package com.dota.arena18.activities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dota.arena18.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashwik on 09-01-2018.
 */

public class AboutActivityAdapter extends RecyclerView.Adapter<AboutActivityAdapter.AboutViewHolder> {
    List<Imagedata> list = new ArrayList<>();
    Context context ;
    AboutActivity aboutActivity = new AboutActivity();

    public AboutActivityAdapter(Context context , List<Imagedata> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public AboutViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_about_content, parent, false);
        return new AboutViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AboutViewHolder holder, final int position) {
        holder.textView.setText(list.get(position).getTitle());
        holder.imageView.setImageResource(list.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class AboutViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public AboutViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.about_image);
            textView  = itemView.findViewById(R.id.about_text);
        }
    }
}
