package com.jiahan.fave.favecomponent.manager.user;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jiahan.fave.core.common.LiveDataSharedPref;
import com.jiahan.fave.core.common.LiveDataSharedPreference;
import com.jiahan.fave.core.data.user.UserDataManagerImpl;
import com.jiahan.fave.core.entity.Place;
import com.jiahan.fave.favecomponent.entity.FaveUser;

import java.util.ArrayList;

public class FaveUserDataManagerImpl extends UserDataManagerImpl<FaveUser> implements FaveUserDataManager {
    private static final String SOCIAL_PROFILE_IMAGE_URL = "SOCIAL_PROFILE_IMAGE_URL";
    private static final String RECENT_SEARCHED          = "RECENT_SEARCHED";
    private static final String RECENT_SEARCHES_LOCATION = "RECENT_SEARCHES_LOCATION";
    private static final String ENABLE_REMINDER          = "ENABLE_REMINDER";
    private static final String ANALYTIC                 = "ANALYTIC";

    public FaveUserDataManagerImpl(@NonNull final LiveDataSharedPref liveDataSharedPreferences, @NonNull final Gson gson) {
        super(liveDataSharedPreferences, gson, TypeToken.get(FaveUser.class));
    }

    @Override
    public LiveDataSharedPreference<String> socialProfileImageUrl() {
        return mLiveDataSharedPref.getString(SOCIAL_PROFILE_IMAGE_URL, "");
    }

    @Override
    public LiveDataSharedPreference<Boolean> enableReminder() {
        return mLiveDataSharedPref.getBoolean(ENABLE_REMINDER, true);
    }

    @Override
    public LiveDataSharedPreference<String> selectedFilter(@NonNull String key) {

        return mLiveDataSharedPref.getString(key, "");
    }

    @Override
    public LiveDataSharedPreference<String> selectedSorted(@NonNull final String key) {
        return mLiveDataSharedPref.getString(key, "");
    }

    @Override
    public LiveDataSharedPreference<String> analyticSelectedFilter(@NonNull String key) {
        return mLiveDataSharedPref.getString(key + ANALYTIC, "");
    }

    @Override
    public LiveDataSharedPreference<ArrayList<String>> recentSearched() {
        return getGsonObject(RECENT_SEARCHED, new ArrayList<>(), new TypeToken<ArrayList<String>>() {
        });
    }

    @Override
    public LiveDataSharedPreference<ArrayList<Place>> recentSearchesLocation() {
        return getGsonObject(RECENT_SEARCHES_LOCATION, new ArrayList<>(), new TypeToken<ArrayList<Place>>() {
        });
    }

    @Override
    public void clear() {
        super.clear();
        socialProfileImageUrl().delete();
        recentSearched().delete();
        recentSearchesLocation().delete();
        enableReminder().delete();
    }
}