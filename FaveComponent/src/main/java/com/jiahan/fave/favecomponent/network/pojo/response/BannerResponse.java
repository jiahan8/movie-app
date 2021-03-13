package com.jiahan.fave.favecomponent.network.pojo.response;

import com.google.gson.annotations.SerializedName;
import com.jiahan.fave.core.network.pojo.response.ListingResponse;
import com.jiahan.fave.favecomponent.entity.Banner;

import java.util.List;

public class BannerResponse extends ListingResponse {
    @SerializedName("banners")
    public List<Banner> mBannerList;
}