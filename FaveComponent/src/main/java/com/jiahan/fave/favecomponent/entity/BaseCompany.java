package com.jiahan.fave.favecomponent.entity;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BaseCompany {
    @SerializedName("id")
    public long mId;

    @SerializedName("logo")
    public String mLogo;

    @SerializedName("name")
    public String mName;

    @SerializedName("rating")
    public double mRating;

    @SerializedName("description")
    @Nullable
    public String mDescription;

    @SerializedName("tags")
    @Nullable
    public List<String> mTags;

    @SerializedName("price_range")
    @Nullable
    public String mPriceRange;

    @SerializedName("partner_cashback")
    @Nullable
    public String mPartnerCashback;
}