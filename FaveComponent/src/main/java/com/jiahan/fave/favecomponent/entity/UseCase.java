package com.jiahan.fave.favecomponent.entity;

import com.google.gson.annotations.SerializedName;

public class UseCase {
    @SerializedName("app_icon")
    public String mIcon;

    @SerializedName("name")
    public String mTitle;

    @SerializedName("deeplink")
    public String mDeeplink;

    @SerializedName("fragment_type")
    public String mType;
}