package com.jiahan.fave.core.data.user;

import com.jiahan.fave.core.common.LiveDataSharedPreference;
import com.jiahan.fave.core.data.DataManager;
import com.jiahan.fave.core.entity.User;

public interface UserDataManager<T extends User> extends DataManager {
    LiveDataSharedPreference<T> user();

    void clear();
}