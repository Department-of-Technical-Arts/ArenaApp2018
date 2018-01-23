package com.dota.arena18.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ashwik on 23-01-2018.
 */

public class ScoresFeed {

    @SerializedName("_id")
    String id;

    @SerializedName("sport")
    String sport;

    @SerializedName("text")
    String scorestext;

    @SerializedName("team1")
    String team1;

    @SerializedName("team2")
    String team2;

    @SerializedName("__v")
    String win;

    public ScoresFeed(String id, String sport, String scorestext, String team1, String team2, String win) {
        this.id = id;
        this.sport = sport;
        this.scorestext = scorestext;
        this.team1 = team1;
        this.team2 = team2;
        this.win = win;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getScorestext() {
        return scorestext;
    }

    public void setScorestext(String scorestext) {
        this.scorestext = scorestext;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }
}
