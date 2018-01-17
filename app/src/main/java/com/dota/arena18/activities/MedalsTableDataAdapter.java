package com.dota.arena18.activities;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dota.arena18.api.CollegeDetails;

import java.util.List;

import de.codecrafters.tableview.TableDataAdapter;

import static com.dota.arena18.activities.MedalsTallyActivity.COLUMN_RANK;
import static com.dota.arena18.activities.MedalsTallyActivity.COLUMN_NAME;
import static com.dota.arena18.activities.MedalsTallyActivity.COLUMN_GOLD;
import static com.dota.arena18.activities.MedalsTallyActivity.COLUMN_SILVER;
import static com.dota.arena18.activities.MedalsTallyActivity.COLUMN_BRONZE;

/**
 * Created by TheGamer007 on 4/1/18.
 */

public class MedalsTableDataAdapter extends TableDataAdapter<CollegeDetails> {

    private int paddingLeft = 20;
    private int paddingTop = 15;
    private int paddingRight = 20;
    private int paddingBottom = 15;
    private int textSize = 18;
    private int typeface = Typeface.NORMAL;
    private int textColor = 0x99000000;

    public MedalsTableDataAdapter(Context context, List<CollegeDetails> data) {
        super(context, data);
    }

    @Override
    public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        CollegeDetails currentRow = getRowData(rowIndex);

        final TextView textView = new TextView(getContext());
        textView.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        textView.setTypeface(textView.getTypeface(), typeface);
        textView.setTextSize(textSize);
        textView.setTextColor(textColor);
        textView.setSingleLine();
        textView.setEllipsize(TextUtils.TruncateAt.END);

        switch (columnIndex) {
            case COLUMN_RANK:
                textView.setText("" + currentRow.getRank());
                break;
            case COLUMN_NAME:
                textView.setText("" + currentRow.getName());
                break;
            case COLUMN_GOLD:
                textView.setText("" + currentRow.getGoldCount());
                break;
            case COLUMN_SILVER:
                textView.setText("" + currentRow.getSilverCount());
                break;
            case COLUMN_BRONZE:
                textView.setText("" + currentRow.getBronzeCount());
                break;
        }

        return textView;
    }
}
