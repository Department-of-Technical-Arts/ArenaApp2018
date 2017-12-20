package com.dota.arena18.activities;

import android.content.ClipData;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by lenovo on 12/19/2017.
 */

public class EventItem {
    private String eventname;
    private View.OnClickListener requestBtnClickListener;


    public EventItem() {

    }

    public EventItem(String eventname) {
        this.eventname = eventname;
    }


    public String getEventname() {
        return eventname;
    }

    public void setRequestBtnClickListener(View.OnClickListener requestBtnClickListener) {
        this.requestBtnClickListener = requestBtnClickListener;
    }

    public View.OnClickListener getRequestBtnClickListener() {

        return requestBtnClickListener;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public static ArrayList<EventItem> gettestinglist(){
        ArrayList<EventItem> events = new ArrayList<>();
        events.add(new EventItem("Basketball"));
        events.add(new EventItem("Cricket"));
        events.add(new EventItem("Football"));

        return events;

    }
}
