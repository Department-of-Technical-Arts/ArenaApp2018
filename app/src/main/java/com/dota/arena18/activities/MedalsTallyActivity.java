package com.dota.arena18.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.dota.arena18.R;
import com.dota.arena18.api.CollegeDetails;
import com.dota.arena18.api.ScoresInterface;
import com.dota.arena18.api.TestApiClient;

import java.util.ArrayList;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.model.TableColumnWeightModel;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedalsTallyActivity extends AppCompatActivity {

    private static final String TAG = MedalsTallyActivity.class.getSimpleName();

    private final int COLUMN_RANK = 0;
    private final int COLUMN_NAME = 1;
    private final int COLUMN_GOLD = 2;
    private final int COLUMN_SILVER = 3;
    private final int COLUMN_BRONZE = 4;
    private final int COLUMN_OTHERS = 5;

    private final String HEADER_RANK = "Rank";
    private final String HEADER_NAME = "College";
    private final String HEADER_GOLD = "Gold";
    private final String HEADER_SILVER = "Silver";
    private final String HEADER_BRONZE = "Bronze";
    private final String HEADER_OTHERS = "Others";

    private final String[] TABLE_HEADERS = {HEADER_RANK, HEADER_NAME, HEADER_GOLD, HEADER_SILVER, HEADER_BRONZE, HEADER_OTHERS};

    private TableView<String[]> tableView;
    private ArrayList<CollegeDetails> tableData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medals_tally);

        tableView = findViewById(R.id.table_tally);

        TableColumnWeightModel columnModel = new TableColumnWeightModel(6);
        columnModel.setColumnWeight(COLUMN_RANK, 1);
        columnModel.setColumnWeight(COLUMN_NAME, 3);
        columnModel.setColumnWeight(COLUMN_GOLD, 1);
        columnModel.setColumnWeight(COLUMN_SILVER, 1);
        columnModel.setColumnWeight(COLUMN_BRONZE, 1);
        columnModel.setColumnWeight(COLUMN_OTHERS, 1);
        tableView.setColumnModel(columnModel);

        testResponse();
    }

    void testResponse() {
        ScoresInterface scores = TestApiClient.getClient().create(ScoresInterface.class);
        Call<ArrayList<CollegeDetails>> call = scores.getLeaderboard();
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

    void loadData() {
//        String[][] DATA_TO_SHOW = {{"1", "BITS Hyderabad", "2", "2", "1", "1"}, {"2", "Harvard University", "2", "0", "1", "0"}};

//        tableView.setHeaderAdapter(new MedalsTableHeaderAdapter(this));
        tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(this, TABLE_HEADERS));
        tableView.setDataAdapter(new SimpleTableDataAdapter(this, getList(tableData)));
    }

    ArrayList<String[]> getList(ArrayList<CollegeDetails> list) {
        ArrayList<String[]> arr = new ArrayList<>();
        for (CollegeDetails col : list) {
            arr.add(col.toStringArray());
            Log.i(TAG, "getList: " + col.toString());
        }
        return arr;
    }
}
