package com.dota.arena18.activities;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.dota.arena18.R;
import com.dota.arena18.api.CollegeDetails;
import com.dota.arena18.api.ScoresInterface;
import com.dota.arena18.api.TestApiClient;
import com.yarolegovich.lovelydialog.LovelyInfoDialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import de.codecrafters.tableview.SortableTableView;
import de.codecrafters.tableview.listeners.SwipeToRefreshListener;
import de.codecrafters.tableview.listeners.TableDataClickListener;
import de.codecrafters.tableview.model.TableColumnWeightModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedalsTallyActivity extends AppCompatActivity {

    private static final String TAG = MedalsTallyActivity.class.getSimpleName();

    public static final int COLUMN_RANK = 0;
    public static final int COLUMN_NAME = 1;
    public static final int COLUMN_GOLD = 2;
    public static final int COLUMN_SILVER = 3;
    public static final int COLUMN_BRONZE = 4;

    private SortableTableView<CollegeDetails> sortableTableView;
    private ArrayList<CollegeDetails> tableData;

    private boolean isInterBITS = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medals_tally);
        ActionBar actionBar =getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        Log.i(TAG, "onCreate: flag: " + getIntent().getIntExtra("flag", 0));
        isInterBITS = (getIntent().getIntExtra("flag", 0) == 1);

        TextView tv_points = findViewById(R.id.tv_points);
        
        if (isInterBITS) {
            Log.i(TAG, "onCreate: isInterBits");
            tv_points.setVisibility(View.VISIBLE);
        } else {
            tv_points.setVisibility(View.GONE);
        }

        sortableTableView = findViewById(R.id.table_tally);
        sortableTableView.setSwipeToRefreshEnabled(true);

        // Define a column weight model to specify width distribution
        TableColumnWeightModel columnModel = new TableColumnWeightModel(5);
        columnModel.setColumnWeight(COLUMN_RANK, 1);
        columnModel.setColumnWeight(COLUMN_NAME, 5);
        columnModel.setColumnWeight(COLUMN_GOLD, 1);
        columnModel.setColumnWeight(COLUMN_SILVER, 1);
        columnModel.setColumnWeight(COLUMN_BRONZE, 1);
        sortableTableView.setColumnModel(columnModel);

        // Add comparators to define sorting
        sortableTableView.setColumnComparator(COLUMN_RANK, new Comparator<CollegeDetails>() {
            @Override
            public int compare(CollegeDetails cd1, CollegeDetails cd2) {
                int rank1 = cd1.getRank();
                int rank2 = cd2.getRank();

                // -1 implies order rank1 before rank2
                // 1 implies rank2 before rank1
                // 0 implies equal

                if (rank1 < rank2) return -1;
                else if (rank1 > rank2) return 1;
                else return 0;
            }
        });

        sortableTableView.setColumnComparator(COLUMN_NAME, new Comparator<CollegeDetails>() {
            @Override
            public int compare(CollegeDetails cd1, CollegeDetails cd2) {
                return cd1.getName().compareToIgnoreCase(cd2.getName());
            }
        });

        sortableTableView.setColumnComparator(COLUMN_GOLD, new Comparator<CollegeDetails>() {
            @Override
            public int compare(CollegeDetails cd1, CollegeDetails cd2) {
                int count1 = cd1.getGoldCount();
                int count2 = cd2.getGoldCount();

                if (count1 < count2) return -1;
                else if (count1 > count2) return 1;
                else return 0;
            }
        });

        sortableTableView.setColumnComparator(COLUMN_SILVER, new Comparator<CollegeDetails>() {
            @Override
            public int compare(CollegeDetails cd1, CollegeDetails cd2) {
                int count1 = cd1.getSilverCount();
                int count2 = cd2.getSilverCount();

                if (count1 < count2) return -1;
                else if (count1 > count2) return 1;
                else return 0;
            }
        });

        sortableTableView.setColumnComparator(COLUMN_BRONZE, new Comparator<CollegeDetails>() {
            @Override
            public int compare(CollegeDetails cd1, CollegeDetails cd2) {
                int count1 = cd1.getBronzeCount();
                int count2 = cd2.getBronzeCount();

                if (count1 < count2) return -1;
                else if (count1 > count2) return 1;
                else return 0;
            }
        });

        // Add a click listener to each row of the table
        sortableTableView.addDataClickListener(new TableDataClickListener<CollegeDetails>() {
            @Override
            public void onDataClicked(int rowIndex, CollegeDetails clickedData) {
                new LovelyInfoDialog(MedalsTallyActivity.this)
                        .setTopColorRes(R.color.colorPrimary)
                        .setTopTitleColor(Color.WHITE)
                        .setTopTitle(clickedData.getName())
                        .setMessage(clickedData.getMessage())
                        .show();
            }
        });

        testResponse();

        sortableTableView.setSwipeToRefreshListener(new SwipeToRefreshListener() {
            @Override
            public void onRefresh(RefreshIndicator refreshIndicator) {
                Log.d(TAG, "onRefresh: called");
                testResponse(refreshIndicator);
            }
        });
    }

    void testResponse() {
        ScoresInterface scores = TestApiClient.getClient().create(ScoresInterface.class);
        Call<ArrayList<CollegeDetails>> call;

        if (isInterBITS) {
            call = scores.getBitsLeaderboard();
        } else {
            call = scores.getArenaLeaderboard();
        }

        call.enqueue(new Callback<ArrayList<CollegeDetails>>() {
            @Override
            public void onResponse(Call<ArrayList<CollegeDetails>> call, Response<ArrayList<CollegeDetails>> response) {
                tableData = response.body();
                loadData();
            }

            @Override
            public void onFailure(Call<ArrayList<CollegeDetails>> call, Throwable t) {
                Log.i(TAG, "onFailure: " + call.request().url());
            }
        });
    }

    void testResponse(final SwipeToRefreshListener.RefreshIndicator ref) {
        ScoresInterface scores = TestApiClient.getClient().create(ScoresInterface.class);
        Call<ArrayList<CollegeDetails>> call;

        if (isInterBITS) {
            call = scores.getBitsLeaderboard();
        } else {
            call = scores.getArenaLeaderboard();
        }

        call.enqueue(new Callback<ArrayList<CollegeDetails>>() {
            @Override
            public void onResponse(Call<ArrayList<CollegeDetails>> call, Response<ArrayList<CollegeDetails>> response) {
                tableData = response.body();
                loadData();
                ref.hide();
            }

            @Override
            public void onFailure(Call<ArrayList<CollegeDetails>> call, Throwable t) {
                Log.i(TAG, "onFailure: " + call.request().url());
                ref.hide();
            }
        });
    }

    void loadData() {
        sortableTableView.setHeaderAdapter(new MedalsTableHeaderAdapter(this));
        sortableTableView.setDataAdapter(new MedalsTableDataAdapter(this, getRankedList(tableData)));
    }

    ArrayList<CollegeDetails> getRankedList(ArrayList<CollegeDetails> list) {
        ArrayList<CollegeDetails> sorted = new ArrayList<>(list);
        Collections.sort(sorted, new Comparator<CollegeDetails>() {
            @Override
            public int compare(CollegeDetails cd1, CollegeDetails cd2) {
                // -1 implies order cd1 before cd2
                // 1 implies cd2 before cd1
                // 0 implies equal

                if (isInterBITS) {
                    int count1 = cd1.getPoints();
                    int count2 = cd2.getPoints();

                    if (count1 < count2) return 1;
                    else if (count1 > count2) return -1;
                    else return -1 * cd1.getScoreString().compareToIgnoreCase(cd2.getScoreString());
                } else {
                    // ie ARENA
                    return -1 * cd1.getScoreString().compareToIgnoreCase(cd2.getScoreString());
                }
            }
        });

        for (int i = 1; i <= sorted.size(); i++) {
            CollegeDetails col = sorted.get(i-1);
            col.setRank(i);
            Log.i(TAG, "getRankedList: " + col.toString());
        }
        return sorted;
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
