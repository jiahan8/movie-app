package com.jiahan.fave.favecomponent.entity;

import com.google.gson.annotations.SerializedName;

public class Review {
    @SerializedName("updated_at")
    public String mReviewedTimeStamp;

    @SerializedName("user_name")
    public String mName;

    @SerializedName("rating")
    public double mRating;

    @SerializedName("comment")
    public String mComment;

    @SerializedName("shop_response")
    public String mCompanyReplied;
}