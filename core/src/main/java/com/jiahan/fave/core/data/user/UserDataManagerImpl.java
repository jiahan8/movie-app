package com.jiahan.fave.core.data.user;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jiahan.fave.core.common.LiveDataSharedPref;
import com.jiahan.fave.core.common.LiveDataSharedPreference;
import com.jiahan.fave.core.data.DataManagerImpl;
import com.jiahan.fave.core.entity.User;

public class UserDataManagerImpl<T extends User> extends DataManagerImpl implements UserDataManager<T> {
    private static final String    USER = "USER";
    private final        TypeToken<T> mTypeToken;
    LiveDataSharedPreference<T> user;

    public UserDataManagerImpl(@NonNull final LiveDataSharedPref liveDataSharedPreferences, @NonNull final Gson gson, TypeToken<T> typeToken) {
        super(liveDataSharedPreferences, gson);
        mTypeToken = typeToken;
    }

    @Override
    public LiveDataSharedPreference<T> user() {
        if (user == null)
            user = getGsonObject(USER, (T) new User(), mTypeToken);
        return user;
    }

    @Override
    public void clear() {
        user().delete();
    }
}