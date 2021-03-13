package com.jiahan.fave.favecomponent.network.pojo.response;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;
import com.jiahan.fave.core.entity.City;

public class SearchResultResponse extends PlaceResponse {
    @SerializedName("city")
    @Nullable
    public City mCity;
}