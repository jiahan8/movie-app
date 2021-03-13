package com.jiahan.fave.favecomponent.entity;

import com.google.gson.annotations.SerializedName;

public class Menu {
    @SerializedName("image")
    public String mImage;

    @SerializedName("name")
    public String mName;

    @SerializedName("id")
    public long mId;

    @SerializedName("image_thumbnail")
    public String mImageThumbnail;
}