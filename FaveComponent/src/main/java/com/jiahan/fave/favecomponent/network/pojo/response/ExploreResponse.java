package com.jiahan.fave.favecomponent.network.pojo.response;

import com.google.gson.annotations.SerializedName;
import com.jiahan.fave.core.network.pojo.response.BaseResponse;
import com.jiahan.fave.favecomponent.entity.UseCase;

import java.util.List;

public class ExploreResponse extends BaseResponse {
    @SerializedName("fragments")
    public List<UseCase> mUseCaseList;

    @SerializedName("fragment_titles")
    public List<String> mTitleList;
}