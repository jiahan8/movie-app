package com.jiahan.fave.favecomponent.network.pojo.response;

import com.google.gson.annotations.SerializedName;
import com.jiahan.fave.core.network.pojo.response.BaseResponse;
import com.jiahan.fave.favecomponent.entity.Company;

public class ECardDetailResponse  extends BaseResponse {

    @SerializedName("company")
    public Company mCompany;
}
