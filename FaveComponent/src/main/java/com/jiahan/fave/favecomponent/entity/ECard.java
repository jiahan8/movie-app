package com.jiahan.fave.favecomponent.entity;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class ECard extends BaseECard{
    @SerializedName("validity")
    public String validity;

    @SerializedName("most_popular")
    public boolean mostPopular;

    @Nullable
    @SerializedName("share")
    public Share mShare;
}