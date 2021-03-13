package com.jiahan.fave.favecomponent.entity;

import com.google.gson.annotations.SerializedName;
import com.jiahan.fave.core.entity.City;

import java.util.List;

public class Country {
    @SerializedName("name")
    public String mName;

    @SerializedName("icon")
    public String mIcon;

    @SerializedName("cities")
    public List<City> mCityList;
}