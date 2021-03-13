package com.jiahan.fave.favecomponent.entity;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class BaseDeal {
    @SerializedName("id")
    public long mId;

    @SerializedName("featured_image")
    public String mLogo;

    @SerializedName("name")
    public String mName;

    @SerializedName("original_price")
    public String mOriginalPrice;

    @SerializedName("discounted_price")
    @Nullable
    public String mDiscountedPrice;

    @SerializedName("discount_percentage")
    public int mDiscountPercentage;

    @SerializedName("category")
    public Category mCategory;

    public class Category {
        @SerializedName("id")
        public long mId;

        @SerializedName("name")
        public String mName;

        @SerializedName("type")
        public String mType;
    }
}