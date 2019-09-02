package com.example.submissionempat.model;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONObject;

public class MovieData implements Parcelable {

    private int id;
    private String title;
    private int popularity;
    private String overview;
    private String poster_path;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public MovieData(JSONObject object){
        try {
            String title = object.getString("title");
            int id = object.getInt("id");
            String overview = object.getString("overview");
            String poster_path = object.getString("poster_path");
            String finalPosterPath = "https://image.tmdb.org/t/p/w500"+poster_path;

            Log.d("cetakTitle",title);


            this.id = id;
            this.title = title;
            this.popularity = popularity;
            this.poster_path = finalPosterPath;
            this.overview = overview;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MovieData(){

    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.poster_path);
        dest.writeString(this.overview);
    }

    protected MovieData(Parcel in){
        this.id = in.readInt();
        this.title =in.readString();
        this.poster_path = in.readString();
        this.overview = in.readString();
    }

    public static final Parcelable.Creator<MovieData> CREATOR = new Parcelable.Creator<MovieData>() {
        @Override
        public MovieData createFromParcel(Parcel source) {
            return new MovieData(source);
        }

        @Override
        public MovieData[] newArray(int size) {
            return new MovieData[size];
        }
    };
}
