package com.jiahan.fave.core.data.location;

import android.text.TextUtils;

import com.jiahan.fave.core.data.app.ApplicationDataManager;
import com.jiahan.fave.core.data.city.CityManager;
import com.jiahan.fave.core.entity.Coordinates;
import com.jiahan.fave.core.utils.LocationUtil;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * Can guarantee that will always emit a nonnull LatLng
 */
public class LatLngParamSelector {
    private final LocationClientAPI      mLocationClientAPI;
    private final CityManager            mCityManager;
    private final ApplicationDataManager mApplicationDataManager;

    public LatLngParamSelector(final LocationClientAPI locationClientAPI,
                               final CityManager cityManager,
                               final ApplicationDataManager applicationDataManager) {
        mLocationClientAPI = locationClientAPI;
        mCityManager = cityManager;
        mApplicationDataManager = applicationDataManager;
    }

    public Observable<Coordinates> getAppLocation() {
        return mCityManager.getCurrentCity()
                .map(city -> {
                    String lastCoordinate = mApplicationDataManager.lastCoordinate().get();
                    if (!TextUtils.isEmpty(lastCoordinate)) {
                        Coordinates coordinates = LocationUtil.fromCoordinatesString(lastCoordinate);
                        if (!(coordinates.getLongitude() == 0 && coordinates.getLongitude() == 0)) {
                            return coordinates;
                        }
                    }
                    return LocationUtil.fromCoordinatesString(city.coordinates);
                });
    }

    public Observable<Coordinates> getUserLocation() {
        return mLocationClientAPI.getLastKnownLocation()
                .subscribeOn(Schedulers.io())
                .map(location -> new Coordinates(location.getLatitude(), location.getLongitude()))
                .onErrorResumeWith(getAppLocation())
                .switchIfEmpty(getAppLocation());
    }

    public Observable<Coordinates> refreshAndGetUserLocation() {
        Observable<Coordinates> defaultLatLng = mCityManager.getCurrentCity()
                .map(city -> {
                    String coordinate = TextUtils.isEmpty(mApplicationDataManager.lastCoordinate().get()) ?
                            city.coordinates :
                            mApplicationDataManager.lastCoordinate().get();
                    return LocationUtil.fromCoordinatesString(coordinate);
                });
        return mLocationClientAPI.getLocationUpdates()
                .subscribeOn(Schedulers.io())
                .map(location -> new Coordinates(location.getLatitude(), location.getLongitude()))
                .onErrorResumeWith(defaultLatLng)
                .switchIfEmpty(defaultLatLng);
    }
}