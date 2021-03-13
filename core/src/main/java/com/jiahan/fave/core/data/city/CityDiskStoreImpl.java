package com.jiahan.fave.core.data.city;

import androidx.annotation.NonNull;

import com.jiahan.fave.core.data.app.ApplicationDataManager;
import com.jiahan.fave.core.entity.City;

import io.reactivex.rxjava3.core.Observable;

public class CityDiskStoreImpl implements CityStore {
    private ApplicationDataManager mApplicationDataManager;

    public CityDiskStoreImpl(final ApplicationDataManager applicationDataManager) {
        mApplicationDataManager = applicationDataManager;
    }

    @Override
    public void saveCity(@NonNull final City city) {
        mApplicationDataManager.city().set(city);
    }

    @Override
    public Observable<City> getCity() {
        final City city = mApplicationDataManager.city().get();
//        if (city.id == 0) {
//            return Observable.empty();
//        }
        return Observable.just(city);
    }
}