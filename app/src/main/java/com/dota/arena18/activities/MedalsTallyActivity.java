package com.dota.arena18.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.dota.arena18.R;
import com.dota.arena18.api.CollegeDetails;
import com.dota.arena18.api.ScoresInterface;
import com.dota.arena18.api.TestApiClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import de.codecrafters.tableview.SortableTableView;
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

    private SortableTableView<String[]> sortableTableView;
    private ArrayList<CollegeDetails> tableData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medals_tally);

        sortableTableView = findViewById(R.id.table_tally);

        TableColumnWeightModel columnModel = new TableColumnWeightModel(6);
        columnModel.setColumnWeight(COLUMN_RANK, 1);
        columnModel.setColumnWeight(COLUMN_NAME, 3);
        columnModel.setColumnWeight(COLUMN_GOLD, 1);
        columnModel.setColumnWeight(COLUMN_SILVER, 1);
        columnModel.setColumnWeight(COLUMN_BRONZE, 1);
        columnModel.setColumnWeight(COLUMN_OTHERS, 1);
        sortableTableView.setColumnModel(columnModel);

        // Add comparators to define sorting
        sortableTableView.setColumnComparator(COLUMN_RANK, new Comparator<String[]>() {
            @Override
            public int compare(String[] strings1, String[] strings2) {
                int rank1 = Integer.parseInt(strings1[COLUMN_RANK]);
                int rank2 = Integer.parseInt(strings2[COLUMN_RANK]);

                // -1 implies order rank1 before rank2
                // 1 implies rank2 before rank1
                // 0 implies equal

                if (rank1 < rank2) return -1;
                else if (rank1 > rank2) return 1;
                else return 0;
            }
        });

        sortableTableView.setColumnComparator(COLUMN_NAME, new Comparator<String[]>() {
            @Override
            public int compare(String[] strings1, String[] strings2) {
                return strings1[COLUMN_NAME].compareToIgnoreCase(strings2[COLUMN_NAME]);
            }
        });

        sortableTableView.setColumnComparator(COLUMN_GOLD, new Comparator<String[]>() {
            @Override
            public int compare(String[] strings1, String[] strings2) {
                int count1 = Integer.parseInt(strings1[COLUMN_GOLD]);
                int count2 = Integer.parseInt(strings2[COLUMN_GOLD]);

                if (count1 < count2) return -1;
                else if (count1 > count2) return 1;
                else return 0;
            }
        });

        sortableTableView.setColumnComparator(COLUMN_SILVER, new Comparator<String[]>() {
            @Override
            public int compare(String[] strings1, String[] strings2) {
                int count1 = Integer.parseInt(strings1[COLUMN_SILVER]);
                int count2 = Integer.parseInt(strings2[COLUMN_SILVER]);

                if (count1 < count2) return -1;
                else if (count1 > count2) return 1;
                else return 0;
            }
        });

        sortableTableView.setColumnComparator(COLUMN_BRONZE, new Comparator<String[]>() {
            @Override
            public int compare(String[] strings1, String[] strings2) {
                int count1 = Integer.parseInt(strings1[COLUMN_BRONZE]);
                int count2 = Integer.parseInt(strings2[COLUMN_BRONZE]);

                if (count1 < count2) return -1;
                else if (count1 > count2) return 1;
                else return 0;
            }
        });

        sortableTableView.setColumnComparator(COLUMN_OTHERS, new Comparator<String[]>() {
            @Override
            public int compare(String[] strings1, String[] strings2) {
                int count1 = Integer.parseInt(strings1[COLUMN_OTHERS]);
                int count2 = Integer.parseInt(strings2[COLUMN_OTHERS]);

                if (count1 < count2) return -1;
                else if (count1 > count2) return 1;
                else return 0;
            }
        });

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

//        sortableTableView.setHeaderAdapter(new MedalsTableHeaderAdapter(this));
        sortableTableView.setHeaderAdapter(new SimpleTableHeaderAdapter(this, TABLE_HEADERS));
        sortableTableView.setDataAdapter(new SimpleTableDataAdapter(this, getList(tableData)));
    }

    ArrayList<String[]> getList(ArrayList<CollegeDetails> list) {
        ArrayList<CollegeDetails> sorted = new ArrayList<>(list);
        Collections.sort(sorted, new Comparator<CollegeDetails>() {
            @Override
            public int compare(CollegeDetails cd1, CollegeDetails cd2) {
                // higher priority means first in the order. i.e. desc order of priority
                // -1 implies order cd1 before cd2
                // 1 implies cd2 before cd1
                // 0 implies equal

                if (cd1.getPriority() > cd2.getPriority()) return -1;
                else if (cd1.getPriority() < cd2.getPriority()) return 1;
                else return 0;
            }
        });

        ArrayList<String[]> arr = new ArrayList<>();

        for (int i = 1; i <= sorted.size(); i++) {
            CollegeDetails col = sorted.get(i-1);
            String[] col_arr = new String[]{"" + i, col.getName(), "" + col.getGoldCount(), "" + col.getSilverCount(), "" + col.getBronzeCount(), "" + col.getOthersCount()};
            arr.add(col_arr);
            Log.i(TAG, "getList: " + col.toString());
        }
        return arr;
    }
}
