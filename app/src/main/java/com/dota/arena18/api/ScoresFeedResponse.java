package com.dota.arena18.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ashwik on 24-01-2018.
 */

public class ScoresFeedResponse {
    @SerializedName("page")
    private int page;

    @SerializedName("total")
    private String totalresults;

    @SerializedName("docs")
    List<ScoresFeed> docs;

    @SerializedName("pages")
    int totalPages;

    public ScoresFeedResponse(int page, String totalresults, List<ScoresFeed> docs,int totalPages) {
        this.page = page;
        this.totalresults = totalresults;
        this.docs = docs;
        this.totalPages = totalPages;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getTotalresults() {
        return totalresults;
    }

    public void setTotalresults(String totalresults) {
        this.totalresults = totalresults;
    }

    public List<ScoresFeed> getDocs() {
        return docs;
    }

    public void setDocs(List<ScoresFeed> docs) {
        this.docs = docs;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
