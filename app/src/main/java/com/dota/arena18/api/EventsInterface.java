package com.dota.arena18.api;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by ashwik on 12/29/17.
 */

public interface EventsInterface {

    @GET("events?sort=name")
    Call<ArrayList<EventDetails>> getEventsfromapi();

    @GET("events/index?sort=name")
    Call<ArrayList<EventDetails>> getEvents();

    @GET("events/{id}")
    Call<EventDetails> getevent(@Path("id") String id);
}
