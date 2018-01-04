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

    int rank = -1;

    public CollegeDetails(String name, String[] gold, String[] silver, String[] bronze, String[] others) {
        this.name = name;
        this.gold = gold;
        this.silver = silver;
        this.bronze = bronze;
        this.others = others;
    }

    @Override
    public String toString() {
        return "#" + rank + ": " + name + " G: " + gold.length + ", S: " + silver.length + ", B: " + bronze.length + ", O: " + others.length;
    }

    public int getPriority(){
        return gold.length + silver.length + bronze.length + others.length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getGold() {
        return gold;
    }

    public int getGoldCount() {
        return gold.length;
    }

    public void setGold(String[] gold) {
        this.gold = gold;
    }

    public String[] getSilver() {
        return silver;
    }

    public int getSilverCount() {
        return silver.length;
    }

    public void setSilver(String[] silver) {
        this.silver = silver;
    }

    public String[] getBronze() {
        return bronze;
    }

    public int getBronzeCount() {
        return bronze.length;
    }

    public void setBronze(String[] bronze) {
        this.bronze = bronze;
    }

    public String[] getOthers() {
        return others;
    }

    public int getOthersCount() {
        return others.length;
    }

    public void setOthers(String[] others) {
        this.others = others;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getMessage() {
        return this.toString();
    }
}
