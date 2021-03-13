package com.jiahan.fave.core.utils;

import android.text.TextUtils;

import androidx.annotation.RawRes;

import com.google.gson.Gson;
import com.jiahan.fave.core.common.CoreApplication;
import com.jiahan.fave.core.utils.gson.GsonUtils;

import java.io.InputStream;
import java.util.Scanner;

public class JsonLoader {
    private JsonLoader() {
        // Ensure Singleton
    }

    public static <T> T serialisation(@RawRes final int fileName, final Class<T> fixtureClass) {
        InputStream inputStream = CoreApplication.getInstance().getResources().openRawResource(fileName);
        String jsonString = new Scanner(inputStream).useDelimiter("\\A").next();
        if (TextUtils.isEmpty(jsonString)) {
            return null;
        }
        final Gson gson = GsonUtils.getGson();
        return gson.fromJson(jsonString, fixtureClass);
    }
}