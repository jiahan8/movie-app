package com.jiahan.fave.favecomponent.network.pojo.response;

import com.google.gson.annotations.SerializedName;
import com.jiahan.fave.core.network.pojo.response.ListingResponse;
import com.jiahan.fave.favecomponent.entity.ECardOnboarding;

import java.util.List;

public class ECardOnboardingResponse extends ListingResponse {
    @SerializedName("onboarding")
    public List<ECardOnboarding> mECardOnboardingList;
}