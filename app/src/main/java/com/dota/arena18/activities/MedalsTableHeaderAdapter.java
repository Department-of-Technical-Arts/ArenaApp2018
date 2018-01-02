package com.dota.arena18.activities;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dota.arena18.R;

import de.codecrafters.tableview.TableHeaderAdapter;

/**
 * Created by TheGamer007 on 2/1/18.
 */

public class MedalsTableHeaderAdapter extends TableHeaderAdapter {

    public MedalsTableHeaderAdapter(Context context) {
        super(context);
    }

    @Override
    public View getHeaderView(int columnIndex, ViewGroup parentView) {

        TextView tv = new TextView(parentView.getContext());
//        tv.setBackgroundColor(Color.BLUE);
        parentView.setPadding(24,24,24,24);

        switch (columnIndex) {
            case 0:
                tv.setText("Col 1");
                break;
            case 1:
                tv.setText("Col 2");
                break;
            case 2:
                ImageView iv = new ImageView(parentView.getContext());
                iv.setImageResource(R.drawable.menu_icon);
                iv.setBackgroundColor(Color.BLACK);
                iv.setPadding(0,8,0,8);
                return iv;
            case 3:
                tv.setText("Col 4");
                break;
            case 4:
                tv.setText("Col 5");
                break;
            case 5:
                tv.setText("Col 6");
                break;
        }

        return tv;
    }
}
