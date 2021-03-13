package com.jiahan.fave.core.location;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class BaseLocationInteractor {
    @Inject
    GetCityAPI mGetCityAPI;

    @Inject
    public BaseLocationInteractor() {

    }

    public Observable<CityByCoordinateResponse> getCityByCoordinate(final double latitude, final double longitude) {
        return mGetCityAPI.getCityByCoordinate(latitude, longitude);
    }
}