package com.jiahan.fave.favecomponent.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieDetail {

    @SerializedName("id")
    public int id;

    @SerializedName("poster_path")
    public String poster_path;

    @SerializedName("title")
    public String title;

    @SerializedName("popularity")
    public float popularity;

    @SerializedName("overview")
    public String overview;

    @SerializedName("genres")
    public List<Genre> genres;

    @SerializedName("original_language")
    public String original_language;

    @SerializedName("runtime")
    public int runtime;

}
