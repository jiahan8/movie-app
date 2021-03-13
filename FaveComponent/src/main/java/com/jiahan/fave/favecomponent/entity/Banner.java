package com.jiahan.fave.favecomponent.entity;

import com.google.gson.annotations.SerializedName;

public class Banner {

    @SerializedName("title")
    public String mName;

    @SerializedName("app_image")
    public String mAppImageUrl;

    @SerializedName("web_image")
    public String mWebImageUrl;

    @SerializedName("deeplink")
    public String mDeeplink;
}
