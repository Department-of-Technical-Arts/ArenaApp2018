package com.dota.arena18.api;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ashwik on 12/29/17.
 */

public interface EventsInterface {

    @GET("events")
    Call<ArrayList<EventDetails>> getAllEvents(@Query("sort") String sortBy);

    @GET("events")
    Call<ArrayList<EventDetails>> getEventList(@Query("sort") String sortBy, @Query("fields") String[] fields);

    @GET("events/{id}")
    Call<EventDetails> getEvent(@Path("id") String id);
}
