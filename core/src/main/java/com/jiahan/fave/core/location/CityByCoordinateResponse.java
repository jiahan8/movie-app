package com.jiahan.fave.core.location;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;
import com.jiahan.fave.core.entity.City;

public class CityByCoordinateResponse {
    @SerializedName("city")
    public City mCity;

    @SerializedName("place")
    public String mLocation;

    @Nullable
    public Double mLatitude;

    @Nullable
    public Double mLongitude;
}