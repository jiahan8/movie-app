package com.jiahan.fave.favecomponent.entity;

import com.google.gson.annotations.SerializedName;

public class Movie {

    @SerializedName("id")
    public int id;

    @SerializedName("poster_path")
    public String poster_path;

    @SerializedName("title")
    public String title;

    @SerializedName("popularity")
    public float popularity;

}
