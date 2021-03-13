package com.jiahan.fave.favecomponent.entity;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class UserAuthenticationMethod {
    @Nullable
    @SerializedName("provider")
    public String provider;
}
