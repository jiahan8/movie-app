package com.jiahan.fave.favecomponent.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BaseOutlet {
    @SerializedName("id")
    public long mId;

    @SerializedName("name")
    public String mName;

    @SerializedName("address")
    public String mAddress;

    @SerializedName("distance")
    public String mDistance;

    @SerializedName("cashback_rate")
    public int mCashbackRate;

    @SerializedName("gallery_images")
    public List<String> mGalleryImages;

    @SerializedName("has_favepay")
    public boolean mHasFavePay;

}