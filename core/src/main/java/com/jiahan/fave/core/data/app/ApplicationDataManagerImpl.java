package com.jiahan.fave.core.data.app;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jiahan.fave.core.common.LiveDataSharedPref;
import com.jiahan.fave.core.common.LiveDataSharedPreference;
import com.jiahan.fave.core.data.DataManagerImpl;
import com.jiahan.fave.core.entity.City;

public class ApplicationDataManagerImpl extends DataManagerImpl implements ApplicationDataManager {
    private static final String CITY            = "CITY";
    private static final String LAST_COORDINATE = "LAST_COORDINATE";
    private static final String LAST_PLACE_NAME = "LAST_PLACE_NAME";

    public ApplicationDataManagerImpl(@NonNull final LiveDataSharedPref mLiveDataSharedPref, @NonNull final Gson gson) {
        super(mLiveDataSharedPref, gson);
    }

    @Override
    public LiveDataSharedPreference<City> city() {
        return getGsonObject(CITY, new City(), TypeToken.get(City.class));
    }

    @Override
    public LiveDataSharedPreference<String> lastCoordinate() {
        return mLiveDataSharedPref.getString(LAST_COORDINATE, "");
    }

    @Override
    public LiveDataSharedPreference<String> lastPlaceName() {
        return mLiveDataSharedPref.getString(LAST_PLACE_NAME, "");
    }
}