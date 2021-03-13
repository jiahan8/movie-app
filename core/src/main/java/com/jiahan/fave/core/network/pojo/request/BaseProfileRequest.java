package com.jiahan.fave.core.network.pojo.request;

import androidx.annotation.Nullable;

import org.immutables.gson.Gson;
import org.immutables.value.Value;

@Gson.TypeAdapters
@Value.Immutable
public interface BaseProfileRequest {
    @Nullable
    String country_iso_code();

    @Nullable
    String phone_country_code();

    @Nullable
    String phone_national_number();

    @Nullable
    String date_of_birth();

    @Nullable
    String gender();

    @Nullable
    String uploaded_image();

    @Nullable
    Boolean push_notifications();

    @Nullable
    String advertising_id();
}