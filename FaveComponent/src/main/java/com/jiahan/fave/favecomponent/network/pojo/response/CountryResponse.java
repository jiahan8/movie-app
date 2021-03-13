package com.jiahan.fave.favecomponent.network.pojo.response;

import com.google.gson.annotations.SerializedName;
import com.jiahan.fave.core.network.pojo.response.BaseResponse;
import com.jiahan.fave.favecomponent.entity.Country;

import java.util.List;

public class CountryResponse extends BaseResponse {
    @SerializedName("countries")
    public List<Country> mCountryList;
}