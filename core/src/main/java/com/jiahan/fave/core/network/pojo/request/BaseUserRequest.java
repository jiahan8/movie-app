package com.jiahan.fave.core.network.pojo.request;

import androidx.annotation.Nullable;

import org.immutables.gson.Gson;
import org.immutables.value.Value;

@Gson.TypeAdapters
@Value.Immutable
public interface BaseUserRequest {
    String name();

    String email();

    @Nullable
    String city_name();

    @Nullable
    String city();

    @Nullable
    String phone_verification_token();

    @Nullable
    String invited_promo_code();

    @Nullable
    Boolean accepted_tnc();
}