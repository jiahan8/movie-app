package com.jiahan.fave.favecomponent.entity;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class Profile {
    @Nullable
    @SerializedName("country_iso_code")
    public String country_iso_code;

    @Nullable
    @SerializedName("phone")
    public String phone;

    @Nullable
    @SerializedName("date_of_birth")
    public String date_of_birth;

    @Nullable
    @SerializedName("gender")
    public String gender;

    @Nullable
    @SerializedName("uploaded_image_url")
    public String uploaded_image_url;

    @Nullable
    @SerializedName("advertising_id")
    public   String advertising_id;

    @Nullable
    @SerializedName("push_notifications")
    public  Boolean push_notifications;
}