package com.dota.arena18.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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

import static android.support.v4.content.PermissionChecker.PERMISSION_DENIED;

/**
 * Created by ashwik on 09-01-2018.
 */

public class AboutActiivtyAdapter extends RecyclerView.Adapter<aboutviewholder> {
    List<Imagedata> list = new ArrayList<>();
    Context context ;
    AboutActivity aboutActivity = new AboutActivity();

    public AboutActiivtyAdapter(Context context , List<Imagedata> list) {
        this.list = list;
        this.context = context;
    }
;
    @Override
    public aboutviewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_about_content,null, false);
        aboutviewholder customviewholder = new aboutviewholder(v);
        return customviewholder;
    }

    @Override
    public void onBindViewHolder(aboutviewholder holder,final int position) {
        holder.textView.setText(list.get(position).getTitle());
        holder.imageView.setImageResource(list.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class aboutviewholder extends RecyclerView.ViewHolder{
    ImageView imageView;
    TextView textView;
    LinearLayout layout;
    public aboutviewholder(View itemView) {
        super(itemView);
        imageView = (ImageView)itemView.findViewById(R.id.about_image);
        textView  = (TextView)itemView.findViewById(R.id.about_text);
        layout = itemView.findViewById(R.id.about_layout);
    }
}
