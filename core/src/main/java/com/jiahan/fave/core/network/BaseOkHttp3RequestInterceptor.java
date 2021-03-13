package com.jiahan.fave.core.network;

import android.content.Context;

import androidx.annotation.NonNull;

import com.jiahan.fave.core.utils.VersionUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class BaseOkHttp3RequestInterceptor implements Interceptor {
    private final String mProductName;
    private final String mDisplayVersion;
    private final String mDeviceId;

    public BaseOkHttp3RequestInterceptor(final Context context) {
        mProductName = VersionUtil.getInternalProductName();
        mDisplayVersion = VersionUtil.getInternalVersion(context);
        mDeviceId = HTTPHeaderUtils.getDeviceIdHeader(context);
    }

    @NonNull
    @Override
    public Response intercept(@NonNull final Chain chain) throws IOException {
        final Request.Builder requestBuilder = chain.request().newBuilder();
        final String userAgentHeader = HTTPHeaderUtils.getUserAgentHeader(mProductName, mDisplayVersion, HTTPHeaderUtils.DEFAULT_USER_AGENT);
        requestBuilder.addHeader("User-Agent", userAgentHeader)
                .addHeader("Accept-Language", HTTPHeaderUtils.getAcceptLanguageHeader())
                .addHeader(HTTPHeaderUtils.FAVE_CUSTOM_DEVICE_ID_HEADER, mDeviceId);
        return chain.proceed(requestBuilder.build());
    }
}