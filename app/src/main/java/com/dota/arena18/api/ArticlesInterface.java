package com.dota.arena18.api;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by TheGamer007 on 11/1/18.
 */

public interface ArticlesInterface {
    @GET("news/index")
    Call<ArrayList<ArticleDetails>> getArticlesList();

    @GET("news/{id}")
    Call<ArticleDetails> getArticle(@Path("id") String id);
}
