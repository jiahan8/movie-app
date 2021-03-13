package com.jiahan.fave.core.network.pojo.request;

import androidx.annotation.Nullable;

import org.immutables.gson.Gson;
import org.immutables.value.Value;

@Gson.TypeAdapters
@Value.Immutable
public interface SessionRequest {
    @Nullable
    String code();

    @Nullable
    String request_id();

    @Nullable
    String city();
}