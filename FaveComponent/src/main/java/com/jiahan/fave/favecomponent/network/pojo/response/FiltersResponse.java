package com.jiahan.fave.favecomponent.network.pojo.response;

import com.google.gson.annotations.SerializedName;
import com.jiahan.fave.core.network.pojo.response.BaseResponse;
import com.jiahan.fave.favecomponent.entity.Filter;

import java.util.List;

public class FiltersResponse extends BaseResponse {
    @SerializedName("sort_by")
    public Filter mSortBy;

    @SerializedName("filters")
    public List<Filter> mFilters;

    @SerializedName("label_attributes")
    public List<Filter> mLabelAttributes;
}