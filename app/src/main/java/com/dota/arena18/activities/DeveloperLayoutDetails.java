package com.dota.arena18.activities;

import java.util.ArrayList;

/**
 * Created by TheGamer007 on 22/1/18.
 */

public class DeveloperLayoutDetails {
    String title;
    ArrayList<DeveloperDetails> devs;

    public DeveloperLayoutDetails(String title, ArrayList<DeveloperDetails> devs) {
        this.title = title;
        this.devs = devs;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<DeveloperDetails> getDevs() {
        return devs;
    }

    public void setDevs(ArrayList<DeveloperDetails> devs) {
        this.devs = devs;
    }
}
