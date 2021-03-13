package com.jiahan.fave.favecomponent.interactor;

import androidx.annotation.Keep;

import com.jiahan.fave.core.data.city.CityManager;
import com.jiahan.fave.core.data.location.LatLngParamSelector;
import com.jiahan.fave.favecomponent.manager.user.FaveUserDataManager;

import javax.inject.Inject;

public class BaseInteractor {
    @Inject
    FaveUserDataManager mUserDataManager;

    @Inject
    CityManager mCityManager;

    @Inject
    LatLngParamSelector mSelector;

    @Keep
    public enum FavoriteType {
        DEAL("deal"),

        COMPANY("company");

        private final String mValue;

        public String getValue() {
            return mValue;
        }

        FavoriteType(String value) {
            mValue = value;
        }
    }

    @Inject
    public BaseInteractor() {

    }

    public boolean isUserLogin() {
        return mUserDataManager.user().get().mId != 0;
    }
}