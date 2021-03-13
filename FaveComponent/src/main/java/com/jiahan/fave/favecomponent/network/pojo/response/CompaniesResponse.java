package com.jiahan.fave.favecomponent.network.pojo.response;

import com.google.gson.annotations.SerializedName;
import com.jiahan.fave.core.network.pojo.response.ListingResponse;
import com.jiahan.fave.favecomponent.entity.Outlet;

import java.util.List;

public class CompaniesResponse extends ListingResponse {
    @SerializedName("companies")
    public List<Outlet> mOutletList;
}