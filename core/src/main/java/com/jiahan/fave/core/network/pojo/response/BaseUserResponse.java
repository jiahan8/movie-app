package com.jiahan.fave.core.network.pojo.response;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;
import com.jiahan.fave.core.entity.User;

public class BaseUserResponse {
    @Nullable
    @SerializedName("user")
    public User user;

    @Nullable
    @SerializedName("new_registration")
    public Boolean mIsNew;

    @Nullable
    @SerializedName("error")
    public String mError;
}