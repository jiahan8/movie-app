package com.jiahan.fave.favecomponent.network.pojo.response;

import com.google.gson.annotations.SerializedName;
import com.jiahan.fave.core.network.pojo.response.BaseResponse;

public class MovieListingResponse extends BaseResponse {

    @SerializedName("total_pages")
    public int total_pages;

}
