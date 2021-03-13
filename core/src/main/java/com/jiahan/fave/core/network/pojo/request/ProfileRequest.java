package com.jiahan.fave.core.network.pojo.request;

import org.immutables.gson.Gson;
import org.immutables.value.Value;

@Gson.TypeAdapters
@Value.Immutable
public interface ProfileRequest {
    ProfileRequest user();
}