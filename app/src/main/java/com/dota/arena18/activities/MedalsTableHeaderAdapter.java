package com.dota.arena18.activities;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dota.arena18.R;

import de.codecrafters.tableview.TableHeaderAdapter;

import static android.util.TypedValue.COMPLEX_UNIT_SP;

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
        tv.setTextColor(Color.WHITE);
        tv.setPadding(8,0,0,0);
        tv.setTextSize(COMPLEX_UNIT_SP,14);
        parentView.setPadding(24,24,24,24);

        switch (columnIndex) {
            case 0:
                tv.setText("Rank");
                break;
            case 1:
                tv.setText("College");
                break;
            case 2:
                ImageView iv_gold = new ImageView(parentView.getContext());
                iv_gold.setImageResource(R.drawable.ic_medal_gold);
//                iv_gold.setBackgroundColor(Color.BLACK);
                return iv_gold;
            case 3:
                ImageView iv_silver = new ImageView(parentView.getContext());
//                iv_silver.setBackgroundColor(Color.BLACK);
                iv_silver.setImageResource(R.drawable.ic_medal_silver);
                return iv_silver;
            case 4:
                ImageView iv_bronze = new ImageView(parentView.getContext());
//                iv_bronze.setBackgroundColor(Color.BLACK);
                iv_bronze.setImageResource(R.drawable.ic_medal_bronze);
                return iv_bronze;
        }

        return tv;
    }
}
