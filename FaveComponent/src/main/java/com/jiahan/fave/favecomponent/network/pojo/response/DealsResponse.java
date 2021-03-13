package com.jiahan.fave.favecomponent.network.pojo.response;

import com.google.gson.annotations.SerializedName;
import com.jiahan.fave.core.network.pojo.response.ListingResponse;
import com.jiahan.fave.favecomponent.entity.BaseECard;
import com.jiahan.fave.favecomponent.entity.Deal;

import java.util.List;

public class DealsResponse extends ListingResponse {
    @SerializedName("deals")
    public List<Deal> deals;

    @SerializedName("e_card")
    public BaseECard eCard;
}