package com.jiahan.fave.core.data.city;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jiahan.fave.core.entity.City;

import io.reactivex.rxjava3.core.Observable;

public interface CityStore {
    void saveCity(@NonNull City city);

    @Nullable
    Observable<City> getCity();
}