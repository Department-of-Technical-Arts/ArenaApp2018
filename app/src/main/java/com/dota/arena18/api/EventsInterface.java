package com.dota.arena18.api;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ashwik on 12/29/17.
 */

public interface EventsInterface {

    @GET("events")
    Call<ArrayList<EventDetails>> getEvents();
}
