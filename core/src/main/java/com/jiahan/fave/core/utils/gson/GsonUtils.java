package com.jiahan.fave.core.utils.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtils {
    private static Gson GSON;

    private GsonUtils() {

    }

    public static synchronized Gson getGson() {
        if (GSON == null) {
            final GsonBuilder gsonBuilder = new GsonBuilder();
            GSON = gsonBuilder.create();
        }
        return com.jiahan.fave.core.utils.gson.GsonUtils.GSON;
    }
}