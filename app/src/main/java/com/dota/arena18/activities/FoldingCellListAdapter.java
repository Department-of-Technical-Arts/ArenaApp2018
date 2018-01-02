package com.dota.arena18.activities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

public class FoldingCellListAdapter extends ArrayAdapter<Model> {

    private HashSet<Integer> unfoldedindexes = new HashSet<>();


    public FoldingCellListAdapter(Context context, ArrayList<Model> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final Model eventItem = getItem(position);
        FoldingCell foldingCell = (FoldingCell) convertView;
        ViewHolder viewHolder;
        if(foldingCell == null){
            viewHolder = new ViewHolder();
            LayoutInflater li = LayoutInflater.from(getContext());
            foldingCell = (FoldingCell) li.inflate(R.layout.cell,parent,false);

            viewHolder.TitleName = foldingCell.findViewById(R.id.title_eventname);
            viewHolder.ContentName = foldingCell.findViewById(R.id.content_eventname);
            viewHolder.prizemoney =  foldingCell.findViewById(R.id.cell_content_prize);
            viewHolder.rules = foldingCell.findViewById(R.id.content_rules_view);
            viewHolder.locationlayout = foldingCell.findViewById(R.id.content_location);

            viewHolder.locationlayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast toast = Toast.makeText(getContext(), "To maps", Toast.LENGTH_SHORT);
                    toast.show();
                }
            });

            viewHolder.contactcaptainlayout = foldingCell.findViewById(R.id.content_contact_captain);

            viewHolder.contactcaptainlayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast toast = Toast.makeText(getContext(), "To captain's contacts", Toast.LENGTH_SHORT);
                    toast.show();
                }
            });

            viewHolder.ruleslayout = foldingCell.findViewById(R.id.content_rules);

            viewHolder.ruleslayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast toast = Toast.makeText(getContext(), "To rules", Toast.LENGTH_SHORT);
                    toast.show();
                }
            });


            foldingCell.setTag(viewHolder);
        }else {
            if (unfoldedindexes.contains(position)){
                foldingCell.unfold(true);
            }else {
                foldingCell.fold(true);
            }
            viewHolder = (ViewHolder) foldingCell.getTag();
        }
        viewHolder.TitleName.setText(eventItem.getDb_eventname());
        viewHolder.ContentName.setText(eventItem.getDb_eventname());
        viewHolder.prizemoney.setText(eventItem.getDb_prizemoney());

        viewHolder.rules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(),DetailsActivity.class);
                i.putExtra("rules",eventItem.getDb_rules());
               getContext().startActivity(i);
            }
        });



        return foldingCell;
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

    private static class ViewHolder{
        TextView TitleName;
        TextView ContentName;
        TextView prizemoney;
        TextView rules;
        RelativeLayout locationlayout;
        RelativeLayout contactcaptainlayout;
        RelativeLayout ruleslayout;


    }
}
