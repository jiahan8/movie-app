package com.jiahan.fave.core.network;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Interceptor to intercept 204 and 404 Resources not found / not available
 * */
public class EmptyBodyInterceptor implements Interceptor {
    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {

        Response response = chain.proceed(chain.request());
        if (response.code() == 204 || response.code() == 404) {
            if (response.body() != null && response.body().contentLength() > 0) {
                return response.newBuilder().code(200).build();
            }

            ResponseBody emptyBody = ResponseBody.create(MediaType.get("text/plain"), "");

            return response
                    .newBuilder()
                    .code(200)
                    .body(emptyBody)
                    .build();
        } else return response;
    }
}