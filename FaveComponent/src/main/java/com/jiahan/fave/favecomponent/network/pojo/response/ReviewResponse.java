package com.jiahan.fave.favecomponent.network.pojo.response;

import com.google.gson.annotations.SerializedName;
import com.jiahan.fave.favecomponent.entity.RatingOverall;
import com.jiahan.fave.favecomponent.entity.Review;

import java.util.List;

public class ReviewResponse {
    @SerializedName("reviews")
    public List<Review> mReviewList;

    @SerializedName("ratings")
    public RatingOverall mRating;
}