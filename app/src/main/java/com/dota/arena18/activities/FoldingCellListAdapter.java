package com.dota.arena18.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dota.arena18.R;
import com.dota.arena18.api.EventDetails;
import com.dota.arena18.database.Model;
import com.ramotion.foldingcell.FoldingCell;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by lenovo on 12/19/2017.
 */

public class FoldingCellListAdapter extends RecyclerView.Adapter<FoldingCellListAdapter.MyViewHolder> {

    private static final String TAG = "FoldingCellListAdapter";
    final private ListItemClickListener listItemClickListener;
    private HashSet<Integer> unfoldedindexes = new HashSet<>();

    Context context;
    LayoutInflater inflater;
    ArrayList<Model> objects;

    public FoldingCellListAdapter(Context context, ArrayList<Model> objects, ListItemClickListener listener) {
        this.context = context;
        inflater=LayoutInflater.from(context);
        this.objects = objects;
        listItemClickListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(inflater.inflate(R.layout.cell,parent,false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {


        holder.TitleName.setText(objects.get(position).getDb_eventname());
        holder.ContentName.setText(objects.get(position).getDb_eventname());
        holder.prizemoney.setText(objects.get(position).getDb_prizemoney());


    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public void registerToggle(int position) {
        if (unfoldedindexes.contains(position))
            registerFold(position);
        else
            registerUnfold(position);
    }

    public void registerFold(int position) {
        unfoldedindexes.remove(position);
    }

    public void registerUnfold(int position) {
        unfoldedindexes.add(position);
    }

    public interface ListItemClickListener{
        void OnListItemClick(int clickedItemIndex, View view);
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView TitleName;
        TextView ContentName;
        TextView prizemoney;
        TextView rules;
        RelativeLayout locationlayout;
        RelativeLayout contactcaptainlayout;
        RelativeLayout ruleslayout;
        RelativeLayout prizelayout;


        public MyViewHolder(View itemView) {
            super(itemView);

            TitleName = (TextView) itemView.findViewById(R.id.title_eventname);
            ContentName = (TextView) itemView.findViewById(R.id.content_eventname);
            prizemoney =  (TextView)itemView.findViewById(R.id.cell_content_prize);
            rules = (TextView) itemView.findViewById(R.id.content_rules_view);
            locationlayout = (RelativeLayout) itemView.findViewById(R.id.content_location);

            prizelayout = (RelativeLayout) itemView.findViewById(R.id.prize_layout);
            ruleslayout = (RelativeLayout) itemView.findViewById(R.id.content_rules);
            contactcaptainlayout = (RelativeLayout) itemView.findViewById(R.id.content_contact_captain);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            listItemClickListener.OnListItemClick(clickedPosition,itemView);
        }
    }
}
