package com.jiahan.fave.favecomponent.network.pojo.response;

import com.google.gson.annotations.SerializedName;
import com.jiahan.fave.favecomponent.entity.Movie;

import java.util.List;

public class MoviesResponse extends MovieListingResponse {

    @SerializedName("results")
    public List<Movie> movies;

}
