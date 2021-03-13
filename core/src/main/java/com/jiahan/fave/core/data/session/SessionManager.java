package com.jiahan.fave.core.data.session;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;

import okhttp3.Interceptor;

public interface SessionManager<T> {
    @Nullable
    T getUserSession();

    @NonNull
    Interceptor getOkHttpRequestInterceptor();

    @Nullable
    Pair<String, String> getAuthHeader();
}