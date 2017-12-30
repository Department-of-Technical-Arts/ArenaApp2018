package com.dota.arena18.activities.Retrofit2;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ashwik on 12/29/17.
 */

public class Apiclient {

    public static final String baseurl = "http://www.bits-arena.com/api/";
    public static Retrofit retrofit = null;

    public static Retrofit getClient()
    {
        if(retrofit==null)
        {
            retrofit = new Retrofit.Builder().baseUrl(baseurl).
                    addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

}
