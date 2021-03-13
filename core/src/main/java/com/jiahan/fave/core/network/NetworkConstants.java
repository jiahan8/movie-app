package com.jiahan.fave.core.network;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jiahan.fave.core.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.logging.HttpLoggingInterceptor;

public class NetworkConstants {
    public static final long DEFAULT_CONNECT_TIMEOUT_MS = TimeUnit.SECONDS.toMillis(10);
    public static final long DEFAULT_READ_TIMEOUT_MS    = TimeUnit.SECONDS.toMillis(30);

    public static final int FORCE_UPDATE_HTTP_CODE = 800;
    public static final int FORCE_LOGOUT_HTTP_CODE = 801;

    @Nullable
    public static Interceptor getHttpLoggingInterceptor() {
        if (BuildConfig.DEBUG) {
            return null;
        }
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }

    @NonNull
    public static String getBaseUrl() {
        if (BuildConfig.DEBUG) {
            return "https://fnb-staging.dev.fave.ninja";
        }
        return "https://api.myfave.com";
    }
}
