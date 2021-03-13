package com.jiahan.fave.favecomponent.network.pojo.response;

import com.google.gson.annotations.SerializedName;
import com.jiahan.fave.core.network.pojo.response.ListingResponse;
import com.jiahan.fave.favecomponent.entity.Assortment;

import java.util.List;

public class AssortmentsResponse extends ListingResponse {
    @SerializedName("assortments")
    public List<Assortment> mAssortments;
}