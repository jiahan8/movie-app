package com.jiahan.fave.favecomponent.entity;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Assortment {
    @SerializedName("id")
    public Long mId;

    @SerializedName("name")
    public String mName;

    @SerializedName("app_image")
    public String mFeatureImage;

    @SerializedName("ribbon_label")
    @Nullable
    public String mRibbonLabel;

    @SerializedName("assortment_types")
    public List<String> mAssortmentTypeList;
}