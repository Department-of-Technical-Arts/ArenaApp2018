package com.dota.arena18.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ashwik on 23-01-2018.
 */

public class TestApiClient {

    public static final String baseurl = "http://139.59.79.46:8000/api/";

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
