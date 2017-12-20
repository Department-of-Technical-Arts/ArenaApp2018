package com.dota.arena18.activities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dota.arena18.R;
import com.ramotion.foldingcell.FoldingCell;

import java.util.HashSet;
import java.util.List;

/**
 * Created by lenovo on 12/19/2017.
 */

public class FoldingCellListAdapter extends ArrayAdapter<EventItem> {

    private HashSet<Integer> unfoldedindexes = new HashSet<>();


    public FoldingCellListAdapter(Context context, List<EventItem> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        EventItem eventItem = getItem(position);
        FoldingCell foldingCell = (FoldingCell) convertView;
        ViewHolder viewHolder;
        if(foldingCell == null){
            viewHolder = new ViewHolder();
            LayoutInflater li = LayoutInflater.from(getContext());
            foldingCell = (FoldingCell) li.inflate(R.layout.cell,parent,false);

            viewHolder.TitleName=(TextView) foldingCell.findViewById(R.id.title_eventname);
            viewHolder.ContentName=(TextView) foldingCell.findViewById(R.id.content_eventname);
            foldingCell.setTag(viewHolder);
        }else {
            if (unfoldedindexes.contains(position)){
                foldingCell.unfold(true);
            }else {
                foldingCell.fold(true);
            }
            viewHolder = (ViewHolder) foldingCell.getTag();
        }
        viewHolder.TitleName.setText(eventItem.getEventname());
        viewHolder.ContentName.setText(eventItem.getEventname());

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
    }
}
