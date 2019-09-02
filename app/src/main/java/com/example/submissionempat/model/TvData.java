package com.example.submissionempat.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class TvData implements Parcelable {

    private int id;
    private String name;
    private int popularity;
    private String overview;
    private String poster_path;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


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
            int id = object.getInt("id");
            String name = object.getString("name");
            String overview = object.getString("overview");
            String poster_path = object.getString("poster_path");
            String finalPosterPath = "https://image.tmdb.org/t/p/w500"+poster_path;

            this.id = id;
            this.name = name;
            this.poster_path = finalPosterPath;
            this.overview = overview;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.poster_path);
        dest.writeString(this.overview);
    }

    protected TvData(Parcel in){
        this.id = in.readInt();
        this.name =in.readString();
        this.poster_path = in.readString();
        this.overview = in.readString();
    }

    public static final Parcelable.Creator<TvData> CREATOR = new Parcelable.Creator<TvData>() {
        @Override
        public TvData createFromParcel(Parcel source) {
            return new TvData(source);
        }

        @Override
        public TvData[] newArray(int size) {
            return new TvData[size];
        }
    };

}
