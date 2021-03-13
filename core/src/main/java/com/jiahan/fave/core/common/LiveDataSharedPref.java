package com.jiahan.fave.core.common;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.util.Set;


public class LiveDataSharedPref {
    private final SharedPreferences sharedPrefs;

    @NotNull
    public final LiveDataSharedPreference<Integer> getInt(@NotNull String key, int defValue) {
        return new LiveDataSharedPreference<Integer>(this.sharedPrefs, key, defValue , null , null);
    }

    @NotNull
    public final LiveDataSharedPreference<String> getString(@NotNull String key, @NotNull String defValue) {
        return new LiveDataSharedPreference<String>(this.sharedPrefs, key, defValue , null , null);
    }

    @NotNull
    public final LiveDataSharedPreference<Boolean> getBoolean(@NotNull String key, boolean defValue) {
        return new LiveDataSharedPreference<Boolean>(this.sharedPrefs, key, defValue , null , null);
    }

    @NotNull
    public final LiveDataSharedPreference<Float> getFloat(@NotNull String key, float defValue) {
        return new LiveDataSharedPreference<Float>(this.sharedPrefs, key, defValue , null , null);
    }

    @NotNull
    public final LiveDataSharedPreference<Long> getLong(@NotNull String key, long defValue) {
        return new LiveDataSharedPreference<Long>(this.sharedPrefs, key, defValue , null , null);
    }

    @NotNull
    public final LiveDataSharedPreference<Set> getStringSet(@NotNull String key, @NotNull Set defValue) {
        return new LiveDataSharedPreference<Set>(this.sharedPrefs, key, defValue , null , null);
    }

    @NotNull
    public final <T> LiveDataSharedPreference<T> getObject(@NotNull String key, T defValue, @NotNull Gson gson, @NotNull TypeToken<T> token) {
        return new LiveDataSharedPreference<T>(this.sharedPrefs, key, defValue , gson , token);
    }

    public LiveDataSharedPref(@NotNull SharedPreferences sharedPrefs) {
        this.sharedPrefs = sharedPrefs;
    }
}
