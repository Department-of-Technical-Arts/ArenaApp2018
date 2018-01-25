package com.dota.arena18.api;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by TheGamer007 on 2/1/18.
 */

public interface ScoresInterface {

    @GET("scores/leaderboard")
    Call<ArrayList<CollegeDetails>> getArenaLeaderboard();

    @GET("scores/leaderboard/bits")
    Call<ArrayList<CollegeDetails>> getBitsLeaderboard();

    @GET("scores/feed")
    Call<ScoresFeedResponse> getScoresfeed(@Query("page") int num, @Query("sort") String key, @Query("direction") String ord);
}
