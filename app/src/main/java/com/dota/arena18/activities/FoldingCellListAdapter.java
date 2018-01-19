package com.dota.arena18.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dota.arena18.R;
import com.dota.arena18.api.EventDetails;
import com.dota.arena18.database.Model;
import com.ramotion.foldingcell.FoldingCell;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashSet;

import static android.support.v4.content.PermissionChecker.PERMISSION_DENIED;
import static com.dota.arena18.activities.AboutActivity.permCheck;

/**
 * Created by lenovo on 12/19/2017.
 */

public class FoldingCellListAdapter extends RecyclerView.Adapter<FoldingCellListAdapter.MyViewHolder> {

    private static final int REQUEST_LOCATION = 101;
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
        holder.venue.setText(objects.get(position).getDb_venue());
        holder.prizelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.contactcaptainlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ContactsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("event_index",position);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        holder.two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.ruleslayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,DetailsActivity.class);
                i.putExtra("api_id",objects.get(position).getApi_id());
                i.putExtra("eventid",String.valueOf(objects.get(position).getId()));
                context.startActivity(i);
            }
        });
        holder.three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.locationlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* permCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION);
                if (permCheck == PERMISSION_DENIED) {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
                } else {
                    Intent i = new Intent(context, MapsActivity.class);
                    context.startActivity(i);
                }*/
            }
        });




            if(holder.foldingCell.isUnfolded())
            {
                holder.foldingCell.fold(true);
            }


    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public void registerToggle(int position) {
        if (unfoldedindexes.contains(position))
        {
         registerFold(position);

        }
        else
        {
            registerUnfold(position);
        }
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
        TextView venue;
        LinearLayout locationlayout;
        RelativeLayout contactcaptainlayout;
        RelativeLayout ruleslayout;
        RelativeLayout prizelayout;
        RelativeLayout PrizeLayout;
        ImageView one;
        ImageView two;
        ImageView three;
        FoldingCell foldingCell;


        public MyViewHolder(View itemView) {
            super(itemView);

            TitleName = (TextView) itemView.findViewById(R.id.title_eventname);
            ContentName = (TextView) itemView.findViewById(R.id.content_eventname);
            prizemoney =  (TextView)itemView.findViewById(R.id.cell_content_prize);
            rules = (TextView) itemView.findViewById(R.id.content_rules_view);
            venue = (TextView) itemView.findViewById(R.id.cell_content_venue);
            locationlayout = itemView.findViewById(R.id.content_location);

            prizelayout =  itemView.findViewById(R.id.prize_layout);
            ruleslayout = itemView.findViewById(R.id.content_rules);
            contactcaptainlayout = itemView.findViewById(R.id.content_contact_captain);
            one = (ImageView) itemView.findViewById(R.id.firstline);
            two = (ImageView) itemView.findViewById(R.id.secondline);
            three = (ImageView) itemView.findViewById(R.id.thirdline);
            foldingCell = itemView.findViewById(R.id.folding_cell);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            listItemClickListener.OnListItemClick(clickedPosition,itemView);
        }
    }
}
