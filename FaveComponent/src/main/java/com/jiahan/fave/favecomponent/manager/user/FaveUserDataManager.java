package com.jiahan.fave.favecomponent.manager.user;

import androidx.annotation.NonNull;

import com.jiahan.fave.core.common.LiveDataSharedPreference;
import com.jiahan.fave.core.data.user.UserDataManager;
import com.jiahan.fave.core.entity.Place;
import com.jiahan.fave.favecomponent.entity.FaveUser;

import java.util.ArrayList;

public interface FaveUserDataManager extends UserDataManager<FaveUser>  {
    LiveDataSharedPreference<String> socialProfileImageUrl();

    LiveDataSharedPreference<Boolean> enableReminder();

    LiveDataSharedPreference<String> selectedFilter(@NonNull String key);

    LiveDataSharedPreference<String> selectedSorted(@NonNull String key);

    LiveDataSharedPreference<String> analyticSelectedFilter(@NonNull String key);

    LiveDataSharedPreference<ArrayList<String>> recentSearched();

    LiveDataSharedPreference<ArrayList<Place>> recentSearchesLocation();
}