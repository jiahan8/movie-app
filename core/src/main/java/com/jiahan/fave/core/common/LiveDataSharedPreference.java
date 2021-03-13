package com.jiahan.fave.core.common;

import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LiveDataSharedPreference<T> extends LiveData<T> {
    protected final SharedPreferences.OnSharedPreferenceChangeListener mPreferenceChangeListener;
    protected final SharedPreferences                                  mSharedPreferences;
    protected final String                                             mKey;
    protected final Object                                             mDefValue;
    protected final Gson                                               mGson;
    protected final TypeToken                                          mTypeToken;

    public LiveDataSharedPreference(@NotNull SharedPreferences sharedPreferences,
                                    @NotNull String key,
                                    @NonNull T defValue,
                                    @Nullable Gson gson,
                                    @Nullable TypeToken typeToken) {
        mSharedPreferences = sharedPreferences;
        mKey = key;
        mDefValue = defValue;
        mGson = gson;
        mTypeToken = typeToken;
        mPreferenceChangeListener = ((sp, key1) -> {
            if (key1.equals(mKey)) {
                postValue(getValueFromPreferences(key1, (T) mDefValue));
            }
        });
        this.postValue(this.getValueFromPreferences(mKey, (T) mDefValue));
        mSharedPreferences.registerOnSharedPreferenceChangeListener(mPreferenceChangeListener);
    }

    @SuppressWarnings("unchecked")
    public T getValueFromPreferences(@NotNull String key, T defValue) {
        if (defValue instanceof String) {
            return (T) mSharedPreferences.getString(key, (String) defValue);
        }
        else if (defValue instanceof Integer) {
            Integer ret = mSharedPreferences.getInt(key, (Integer) defValue);
            return (T) ret;
        }
        else if (defValue instanceof Long) {
            Long ret = mSharedPreferences.getLong(key, (Long) defValue);
            return (T) ret;
        }
        else if (defValue instanceof Float) {
            Float ret = mSharedPreferences.getFloat(key, (Float) defValue);
            return (T) ret;
        }
        else if (defValue instanceof Boolean) {
            Boolean ret = mSharedPreferences.getBoolean(key, (Boolean) defValue);
            return (T) ret;
        }
        else if (defValue instanceof List) {
            return mGson.fromJson(mSharedPreferences.getString(key, new JsonArray().toString()), mTypeToken.getType());
        }
        else {
            return mGson.fromJson(mSharedPreferences.getString(key, new JsonObject().toString()), mTypeToken.getType());
        }
    }

    public void set(T newValue) {
        if (mDefValue instanceof String) {
            mSharedPreferences.edit().putString(mKey, (String) newValue).apply();
        }
        else if (mDefValue instanceof Integer) {
            mSharedPreferences.edit().putInt(mKey, (Integer) newValue).apply();
        }
        else if (mDefValue instanceof Long) {
            mSharedPreferences.edit().putLong(mKey, (Long) newValue).apply();
        }
        else if (mDefValue instanceof Float) {
            mSharedPreferences.edit().putFloat(mKey, (Float) newValue).apply();
        }
        else if (mDefValue instanceof Boolean) {
            mSharedPreferences.edit().putBoolean(mKey, (Boolean) newValue).apply();
        }
        else {
            mSharedPreferences.edit().putString(mKey, mGson.toJson(newValue)).apply();
        }
    }

    public final void delete() {
        this.mSharedPreferences.edit().remove(mKey).apply();
    }

    public final T get() {
        return getValueFromPreferences(mKey, (T) mDefValue) ;
    }

    protected void onActive() {
        super.onActive();
        this.postValue(this.getValueFromPreferences(mKey, (T) mDefValue));
        this.mSharedPreferences.registerOnSharedPreferenceChangeListener(mPreferenceChangeListener);
    }

    protected void onInactive() {
        this.mSharedPreferences.unregisterOnSharedPreferenceChangeListener(mPreferenceChangeListener);
        super.onInactive();
    }

}