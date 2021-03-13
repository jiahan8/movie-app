package com.jiahan.fave.favecomponent.network.pojo.response;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;
import com.jiahan.fave.core.network.pojo.response.ListingResponse;
import com.jiahan.fave.favecomponent.entity.BaseECard;
import com.jiahan.fave.favecomponent.entity.Deal;
import com.jiahan.fave.favecomponent.entity.Outlet;

import java.util.List;

public class AssortmentTypeResponse extends ListingResponse {
    @SerializedName("deals")
    @Nullable
    public List<Deal> mDealList;

    @SerializedName("outlets")
    @Nullable
    public List<Outlet> mOutletList;

    @SerializedName("e_cards")
    @Nullable
    public List<BaseECard> mECardList;
}