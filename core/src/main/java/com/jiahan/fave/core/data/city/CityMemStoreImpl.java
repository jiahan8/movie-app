package com.jiahan.fave.core.data.city;

import androidx.annotation.NonNull;

import com.jiahan.fave.core.entity.City;

import io.reactivex.rxjava3.core.Observable;


public class CityMemStoreImpl implements CityStore {
    private City mCity;

    @Override
    public void saveCity(@NonNull final City city) {
        mCity = city;
    }

    @Override
    public Observable<City> getCity() {
        if (mCity == null) {
            return Observable.empty();
        }
        return Observable.just(mCity);
    }
}