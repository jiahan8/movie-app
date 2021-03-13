package com.jiahan.fave.core.network.pojo.response;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BaseResponse {
    @SerializedName("code")
    public int mCode;

    @SerializedName("message")
    public String mMessage;

    @SerializedName("errors")
    @Nullable
    public List<BaseErrorResponse> mErrorList;
}