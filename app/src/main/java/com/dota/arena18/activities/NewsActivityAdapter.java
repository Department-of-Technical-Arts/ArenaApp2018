package com.dota.arena18.activities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.dota.arena18.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashwik on 09-01-2018.
 */

public class NewsActivityAdapter extends RecyclerView.Adapter<NewsActivityAdapter.CustomViewHolder>{

    List<Imagedata> list = new ArrayList<>();
    Context context ;
    public NewsActivityAdapter(Context context , List<Imagedata> list) {

        this.list = list;
        this.context = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_news_content, parent, false);
        CustomViewHolder customviewholder = new CustomViewHolder(v);
        return customviewholder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, final int position) {
        holder.textView.setText(list.get(position).getTitle());
        holder.imageView.setImageResource(list.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return list.size();}


    public class CustomViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        LinearLayout layout;
        public CustomViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.news_image);
            textView  = itemView.findViewById(R.id.news_text);
            layout = itemView.findViewById(R.id.news_layout);
        }

    }
}
