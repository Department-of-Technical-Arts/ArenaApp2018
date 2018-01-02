package com.dota.arena18.api;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by TheGamer007 on 2/1/18.
 */

public class CollegeDetails {
    @SerializedName("name")
    String name;

    @SerializedName("gold")
    String[] gold;

    @SerializedName("silver")
    String[] silver;

    @SerializedName("bronze")
    String[] bronze;

    @SerializedName("others")
    String[] others;

    public CollegeDetails(String name, String[] gold, String[] silver, String[] bronze, String[] others) {
        this.name = name;
        this.gold = gold;
        this.silver = silver;
        this.bronze = bronze;
        this.others = others;
    }

    @Override
    public String toString() {
        return name + " G: " + gold.length + ", S: " + silver.length + ", B: " + bronze.length + ", O: " + others.length;
    }

    public String[] toStringArray() {
        int count_gold = gold.length;
        int count_silver = silver.length;
        int count_bronze = bronze.length;
        int count_others = others.length;

        return new String[]{("" + (count_gold + count_silver + count_bronze + count_others)), name, ("" + count_gold), ("" + count_silver), ("" + count_bronze), ("" + count_others)};
    }
}
