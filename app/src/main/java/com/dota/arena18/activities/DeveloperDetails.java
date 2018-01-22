package com.dota.arena18.activities;

import android.support.annotation.DrawableRes;

/**
 * Created by TheGamer007 on 22/1/18.
 */

public class DeveloperDetails {
    String name;
    int profileImg;
    String[] links = {null, null, null};

    public DeveloperDetails(String name, @DrawableRes int profileImg, String[] links) {
        this.name = name;
        this.profileImg = profileImg;
        this.links = links;
    }

    public String getName() {
        return name;
    }

    public int getProfileImg() {
        return profileImg;
    }

    public String[] getLinks() {
        return links;
    }

    public void setLinks(String[] links) {
        this.links = links;
    }
}
