package com.jiahan.fave.core.data;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jiahan.fave.core.common.LiveDataSharedPref;
import com.jiahan.fave.core.common.LiveDataSharedPreference;

public class DataManagerImpl implements DataManager {
    private final   Gson               mGson;
    protected final LiveDataSharedPref mLiveDataSharedPref;

    public DataManagerImpl(@NonNull final LiveDataSharedPref preferences,
                           @NonNull final Gson gson) {
        mLiveDataSharedPref = preferences;
        mGson = gson;
    }

    protected <T> LiveDataSharedPreference<T> getGsonObject(@NonNull final String key,
                                                            @NonNull final T defaultValue,
                                                            final TypeToken<T> token) {
        return mLiveDataSharedPref.getObject(key, defaultValue, mGson, token);
    }
}