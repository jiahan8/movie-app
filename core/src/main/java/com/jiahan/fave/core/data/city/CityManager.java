package com.jiahan.fave.core.data.city;

import androidx.annotation.NonNull;

import com.jiahan.fave.core.entity.City;

import io.reactivex.rxjava3.core.Observable;

public interface CityManager {
//    Observable<City> refreshCity();

    Observable<City> getCurrentCity();

    void setCity(@NonNull City city);

    void deleteCity();
}