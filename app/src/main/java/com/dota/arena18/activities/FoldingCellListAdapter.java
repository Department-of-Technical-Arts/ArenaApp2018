package com.dota.arena18.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dota.arena18.R;
import com.dota.arena18.database.Model;
import com.ramotion.foldingcell.FoldingCell;

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
        final String eventTitle = objects.get(position).getDb_eventname();
        holder.TitleName.setText(eventTitle);
        holder.ContentName.setText(objects.get(position).getDb_eventname());
        holder.prizemoney.setText(objects.get(position).getDb_prizemoney());
        holder.venue.setText(objects.get(position).getDb_venue());
        holder.foldedImage.setImageResource(getFoldedImage(eventTitle.toLowerCase()));
        holder.unfoldedImage.setImageResource(getUnfoldedImage(eventTitle.toLowerCase()));

        holder.prizelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Do nothing
            }
        });
        holder.one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Do nothing
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
                // Do nothing
            }
        });
        holder.ruleslayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,DetailsActivity.class);
                i.putExtra("api_id",objects.get(position).getApi_id());
                i.putExtra("eventid",String.valueOf(objects.get(position).getId()));
                i.putExtra("event_name",eventTitle);
                context.startActivity(i);
            }
        });
        holder.three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Do nothing
            }
        });
        holder.locationlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                permCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION);
                if (permCheck == PERMISSION_DENIED) {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
                } else {
                    Intent i = new Intent(context, MapsActivity.class);
                    context.startActivity(i);
                }
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

    /**
     * The file names are wrong, the method is correct. Couldn't refactor so many files easily
     *
     * @param event String <b>must</b> be lowercase
     * @return Drawable resId for corresponding image
     */
    private @DrawableRes int getUnfoldedImage(String event){
        int resId = R.drawable.download; // default value

        if (event.contains("athletics")) {
            resId = R.drawable.athletics1;

        }
        else if (event.contains("badminton")) {
            if (event.contains("boys")) {
                resId = R.drawable.event_badminton_boys_fold;
            } else if (event.contains("girls")) {
                resId = R.drawable.event_badminton_girls_fold;
            } else {
                resId = R.drawable.event_badminton_mixed_fold;
            }
        }
        else if (event.contains("basketball")) {
            if (event.contains("boys")) {
                resId = R.drawable.event_basketball_boys_fold;
            } else {
                resId = R.drawable.event_basketball_girls_fold;
            }
        }
        else if (event.contains("body building")) {
            resId = R.drawable.event_bodybuilding_fold;
        }
        else if (event.contains("carrom")) {
            resId = R.drawable.event_carroms_fold;
        }
        else if (event.contains("chess")) {
            resId = R.drawable.event_chess_fold;
        }
        else if (event.contains("cricket")) {
            resId = R.drawable.event_cricket_fold;
        }
        else if (event.contains("duathlon")) {
            resId = R.drawable.event_duathlon;
        }
        else if (event.contains("football")) {
            if (event.contains("boys")) {
                resId = R.drawable.event_football_boys_fold;
            } else {
                resId = R.drawable.event_football_girls_fold;
            }
        }
        else if (event.contains("hockey")) {
            resId = R.drawable.event_hockey_fold;
        }
        else if (event.contains("kabaddi")) {
            resId = R.drawable.event_kabaddi_fold;
        }
        else if (event.contains("pool")) {
            resId = R.drawable.event_pool_unfold;
        }
        else if (event.contains("power lifting")) {
            resId = R.drawable.event_powerlifting_fold;
        }
        else if (event.contains("snooker")) {
            resId = R.drawable.event_snooker_unfold;
        }
        else if (event.contains("squash")) {
            resId = R.drawable.event_snooker_unfold;
        }
        else if (event.contains("table tennis")) {
            resId = R.drawable.event_tabletennis_fold;
        }
        else if (event.contains("tennis")) {
            // Keep it after table tennis, or it will assign the wrong image to TT
            resId = R.drawable.event_tennis_fold;
        }
        else if (event.contains("throwball")) {
            resId = R.drawable.event_throwball_unfold;
        }
        else if (event.contains("volleyball")) {

        }

        return resId;
    }

    /**
     *The file names are wrong, the method is correct. Couldn't refactor so many files easily
     *
     * @param event String <b>must</b> be lowercase
     * @return Drawable resId for corresponding image
     */
    private @DrawableRes int getFoldedImage(String event){
        int resId = R.drawable.head_image; // default value

        if (event.contains("athletics")) {
            resId = R.drawable.athletics2;
        }
        else if (event.contains("badminton")) {
            if (event.contains("boys")) {
                resId = R.drawable.event_badminton_boys_unfold;
            } else if (event.contains("girls")) {
                resId = R.drawable.event_badminton_girls_unfold;
            } else {
                resId = R.drawable.event_badminton_mixed_unfold;
            }
        }
        else if (event.contains("basketball")) {
            if (event.contains("boys")) {
                resId = R.drawable.event_basketball_boys_unfold;
            } else {
                resId = R.drawable.event_basketball_girls_unfold;
            }
        }
        else if (event.contains("body building")) {
            resId = R.drawable.event_bodybuilding_unfold;
        }
        else if (event.contains("carrom")) {
            resId = R.drawable.event_carroms_unfold;
        }
        else if (event.contains("chess")) {
            resId = R.drawable.event_chess_unfold;
        }
        else if (event.contains("cricket")) {
            resId = R.drawable.event_cricket_unfold;
        }
        else if (event.contains("duathlon")) {
            resId = R.drawable.event_duathlon_fold;
        }
        else if (event.contains("football")) {
            if (event.contains("boys")) {
                resId = R.drawable.event_football_boys_unfold;
            } else {
                resId = R.drawable.event_football_girls_unfold;
            }
        }
        else if (event.contains("hockey")) {
            resId = R.drawable.event_hockey_unfold;
        }
        else if (event.contains("kabaddi")) {
            resId = R.drawable.event_kabaddi_unfold;
        }
        else if (event.contains("pool")) {
            resId = R.drawable.event_pool_fold;
        }
        else if (event.contains("power lifting")) {
            resId = R.drawable.event_powerlifting_unfold;
        }
        else if (event.contains("snooker")) {
            resId = R.drawable.event_snooker_fold;
        }
        else if (event.contains("squash")) {
            resId = R.drawable.event_squash_fold;
        }
        else if (event.contains("table tennis")) {
            resId = R.drawable.event_tabletennis_unfold;
        }
        else if (event.contains("tennis")) {
            // Keep it after table tennis, or it will assign the wrong image to TT
            resId = R.drawable.event_tennis_unfold;
        }
        else if (event.contains("throwball")) {
            resId = R.drawable.event_throwball_fold;
        }
        else if (event.contains("volleyball")) {

        }

        return resId;
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
        ImageView one;
        ImageView two;
        ImageView three;
        ImageView foldedImage;
        ImageView unfoldedImage;
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
            foldedImage = itemView.findViewById(R.id.folded_image);
            unfoldedImage = itemView.findViewById(R.id.unfolded_image);

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
