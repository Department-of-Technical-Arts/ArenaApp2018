package com.dota.arena18.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ashwik on 12/29/17.
 */

public class EventDetails {

    @SerializedName("_id")
    String api_id;

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

    public EventDetails(String api_id,String eventname, String rules, String venue, String prize, String starttime, String endtime) {
        this.api_id = api_id;
        this.eventname = eventname;
        this.rules = rules;
        this.venue = venue;
        this.prize = prize;
        this.starttime = starttime;
        this.endtime = endtime;
    }

    public String getApi_id() {
        return api_id;
    }

    public void setApi_id(String api_id) {
        this.api_id = api_id;
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
