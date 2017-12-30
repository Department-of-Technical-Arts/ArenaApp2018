package com.dota.arena18.activities.Retrofit2;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ashwik on 12/29/17.
 */

public class events {

    @SerializedName("name")
    String eventname;

    @SerializedName("about")
    String rules;

    @SerializedName("venue")
    String venue;

    @SerializedName("prize")
    String prize;

    @SerializedName("startTime")
    String starttime;

    @SerializedName("endTime")
    String endtime;

    public events(String eventname, String rules, String venue, String prize, String starttime, String endtime) {
        this.eventname = eventname;
        this.rules = rules;
        this.venue = venue;
        this.prize = prize;
        this.starttime = starttime;
        this.endtime = endtime;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
}
