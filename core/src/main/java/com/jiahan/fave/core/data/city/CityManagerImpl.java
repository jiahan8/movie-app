package com.jiahan.fave.core.data.city;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jiahan.fave.core.common.LiveDataSharedPref;
import com.jiahan.fave.core.common.LiveDataSharedPreference;
import com.jiahan.fave.core.data.DataManagerImpl;
import com.jiahan.fave.core.entity.City;

import io.reactivex.rxjava3.core.Observable;


public class CityManagerImpl extends DataManagerImpl implements CityManager {
//    private final Context           mContext;
//    private final LocationClientAPI mLocationClientAPI;
//    private final CityStore         mMemoryStore;
//    private final CityStore         mDiskStore;
//    private final UserManager       mUserManager;
//    private final LocationConnector mLocationConnector;
//    private final UserConnector     mUserConnector;

    private static final String CITY           = "CITY";
    private static final String TEMPORARY_CITY = "TEMPORARY_CITY";

    LiveDataSharedPreference<City> mCityPref;
    LiveDataSharedPreference<City> mTempCityPref;

    public CityManagerImpl(@NonNull final LiveDataSharedPref preferences,
                           @NonNull final Gson gson) {
        super(preferences, gson);
        mCityPref = getGsonObject(CITY, new City(), TypeToken.get(City.class));
        mTempCityPref = getGsonObject(TEMPORARY_CITY, new City(), TypeToken.get(City.class));
    }

    @Override
    public Observable<City> getCurrentCity() {
        return Observable.just(mCityPref.get());
    }

    /***
     * Avoid using this, main application will not get this value
     * unless change the memstore to diskstore
     * @param city
     */
    @Override
    public void setCity(@NonNull final City city) {
        mTempCityPref.set(city);
        mCityPref.set(city);
    }

    @Override
    public void deleteCity() {
        mCityPref.delete();
        mTempCityPref.delete();
    }
//    private Observable<City> diskStore() {
//        return mDiskStore.getCity();
//    }Z
//    private Observable<City> network() {
//        if (LocationHelper.isGpsEnabled(mContext)) {
//            return mLocationClientAPI.getLastKnownLocation()
//                    .doOnNext(location -> {
//                        User user = mUserManager.getCurrentUser().blockingFirst();
//                        mUserConnector.updateUserLocation(user.id(), location.getLatitude(), location.getLongitude());
//                    })
//                    .flatMap( location ->
//                            mLocationConnector.getLocation(location.getLatitude(), location.getLongitude())
//                                    .map(LocationResponse::city))
//                    .doOnNext(city -> {
//                        mDiskStore.saveCity(city);
//                        mMemoryStore.saveCity(city);
//                    });
//
//        }
//        else {
//            return getCurrentCity();
//        }
//    }
}