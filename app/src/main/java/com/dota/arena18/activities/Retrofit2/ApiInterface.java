package com.dota.arena18.activities.Retrofit2;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ashwik on 12/29/17.
 */

public interface ApiInterface {

    @GET("events")
    Call<ArrayList<events>> getEvents();
}
