package com.jiahan.fave.core.network.pojo.response;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class BaseErrorResponse {
    @SerializedName("message")
    public String mMessage;

    @Nullable
    @SerializedName("sentry_id")
    public String mSentryId;
}