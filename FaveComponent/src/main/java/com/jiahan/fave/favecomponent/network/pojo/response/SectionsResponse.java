package com.jiahan.fave.favecomponent.network.pojo.response;

import com.google.gson.annotations.SerializedName;
import com.jiahan.fave.core.network.pojo.response.BaseResponse;
import com.jiahan.fave.favecomponent.entity.Section;

import java.util.List;

public class SectionsResponse extends BaseResponse {
    @SerializedName("sections")
    public List<Section> mSectionList;
}