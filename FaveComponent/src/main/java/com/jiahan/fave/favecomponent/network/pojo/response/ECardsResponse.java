package com.jiahan.fave.favecomponent.network.pojo.response;

import com.google.gson.annotations.SerializedName;
import com.jiahan.fave.core.network.pojo.response.ListingResponse;
import com.jiahan.fave.favecomponent.entity.BaseECard;

import java.util.List;

public class ECardsResponse extends ListingResponse {
    @SerializedName("e_cards")
    public List<BaseECard> mECards;
}