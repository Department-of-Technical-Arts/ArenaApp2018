package com.dota.arena18.activities;

import android.graphics.Bitmap;

/**
 * Created by ashwik on 09-01-2018.
 */

public class Imagedata {
   int image;
    String title;

    public Imagedata(int image, String title) {
        this.image = image;
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
