package com.example.submissionempat.model;

import org.json.JSONObject;

public class TvData {

    private String name;
    private int popularity;
    private String overview;
    private String poster_path;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public TvData(JSONObject object){
        try {
            String name = object.getString("name");
            String overview = object.getString("overview");
            String poster_path = object.getString("poster_path");
            String finalPosterPath = "https://image.tmdb.org/t/p/w500"+poster_path;

            this.name = name;
            this.poster_path = finalPosterPath;
            this.overview = overview;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
