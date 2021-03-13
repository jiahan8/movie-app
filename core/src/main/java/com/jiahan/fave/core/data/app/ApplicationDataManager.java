package com.jiahan.fave.core.data.app;

import com.jiahan.fave.core.common.LiveDataSharedPreference;
import com.jiahan.fave.core.data.DataManager;
import com.jiahan.fave.core.entity.City;

public interface ApplicationDataManager extends DataManager {
    LiveDataSharedPreference<City> city();

    LiveDataSharedPreference<String> lastCoordinate();

    LiveDataSharedPreference<String> lastPlaceName();
}