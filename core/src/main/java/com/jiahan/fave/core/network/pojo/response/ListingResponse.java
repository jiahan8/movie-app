package com.jiahan.fave.core.network.pojo.response;

import com.google.gson.annotations.SerializedName;

public class ListingResponse extends BaseResponse {
    @SerializedName("total_count")
    public int mTotalCount;

    @SerializedName("page")
    public int mPage;

    @SerializedName("title")
    public String mTitle;

    @SerializedName("description")
    public String mDescription;
}