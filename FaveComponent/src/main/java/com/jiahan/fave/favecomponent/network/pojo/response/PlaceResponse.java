package com.jiahan.fave.favecomponent.network.pojo.response;

import com.google.gson.annotations.SerializedName;
import com.jiahan.fave.core.entity.Place;
import com.jiahan.fave.core.network.pojo.response.ListingResponse;

import java.util.List;

public class PlaceResponse extends ListingResponse {
    @SerializedName("places")
    public List<Place> mPlaceList;
}