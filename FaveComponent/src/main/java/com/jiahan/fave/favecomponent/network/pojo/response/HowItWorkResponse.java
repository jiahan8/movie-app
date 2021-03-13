package com.jiahan.fave.favecomponent.network.pojo.response;

import com.google.gson.annotations.SerializedName;
import com.jiahan.fave.favecomponent.entity.HowItWorkData;

import java.util.List;

public class HowItWorkResponse {
    @SerializedName("how_it_works")
    public List<HowItWorkData> mItWorkDataList;
}